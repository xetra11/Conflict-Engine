package com.condorgames.prototype.entities.platoon;

import com.badlogic.gdx.physics.box2d.Body;
import com.condorgames.prototype.battleresolver.Morale;
import com.condorgames.prototype.battleresolver.Morale.MoraleState;
import com.condorgames.prototype.creator.SquadCreator;
import com.condorgames.prototype.entities.equipment.weapons.interfaces.Fireable;
import com.condorgames.prototype.entities.soldier.Soldier;
import com.condorgames.prototype.entities.soldier.SoldierProperties.Health;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Platoon extends SteerablePlatoonEntity {
  private static final int UPPER_HIGH_THRESHOLD = 36;
  private static final int UPPER_NORMAL_THRESHOLD = 27;
  private static final int UPPER_LOW_THRESHOLD = 18;
  private static final int UPPER_FLEEING_THRESHOLD = 9;
  public static final int UPPER_PINNEDDOWN_THRESHOLD = 0;
  private List<Soldier> soldiers = new ArrayList<>();

  public Platoon(Body body, PlatoonEntityBase.Faction faction) {
    super(body, faction);

    soldiers.addAll(SquadCreator.createRifleSquad());
    soldiers.addAll(SquadCreator.createRifleSquad());
    soldiers.addAll(SquadCreator.createRifleSquad());
    soldiers.addAll(SquadCreator.createRifleSquad());
  }

  //TODO Salvage Ammo from dead comrades!

  @Override
  public void fire(float deltaTime, Fireable.HitListener hitListener) {
    getActiveSoldiers()
            .forEach(soldier -> soldier.fire(deltaTime, hitListener));
  }

  private Stream<Soldier> getActiveSoldiers() {
    return soldiers.stream()
            .filter(this::isAbleToFight);
  }

  @Override
  public int getAmmo() {
    return soldiers.stream()
            .mapToInt(Soldier::getAmmo)
            .sum();
  }

  @Override
  public int getStrength() {
    return Math.toIntExact(getActiveSoldiers()
            .count());
  }

  @Override
  public void takeCasualty() {
    System.out.println("Casualty!");
    Objects.requireNonNull(randomActiveSoldier(),
            "Could not resolve a random soldier, platoon seems to be empty!");
    randomActiveSoldier().wound();
  }

  @Override
  public void setMorale(MoraleState morale) {
    randomActiveSoldier().setMorale(morale);
  }

  @Override
  public void decreaseMorale() {
    randomActiveSoldier().decreaseMorale();
  }

  @Override
  public void raiseMorale() {
    randomActiveSoldier().raiseMorale();
  }

  @Override
  public MoraleState getMorale() {
    //TODO Add morale debuffs here so they can be aggregated
    int platoonMorale = getPlatoonMorale();
    return getPlatoonMoraleState(platoonMorale);
  }

  private MoraleState getPlatoonMoraleState(int platoonMorale) {

    /***
     * 45 = Fanatic
     * 36 = High
     * 27 = Normal
     * 18 = Low
     * 9 = Fleeing
     * 0 = Pinned_Down
     */

    if (platoonMorale > getActiveSoldiers().count() * MoraleState.HIGH.getValue()) {
      return MoraleState.FANATIC;
    } else if (platoonMorale > getActiveSoldiers().count() * MoraleState.NORMAL.getValue()) {
      return MoraleState.HIGH;
    } else if (platoonMorale > getActiveSoldiers().count() * MoraleState.LOW.getValue()) {
      return MoraleState.NORMAL;
    } else if (platoonMorale > getActiveSoldiers().count() * MoraleState.FLEEING.getValue()) {
      return MoraleState.LOW;
    } else if (platoonMorale > getActiveSoldiers().count() * MoraleState.PINNED_DOWN.getValue()) {
      return MoraleState.FLEEING;
    } else {
      return MoraleState.PINNED_DOWN;
    }
  }

  private int getPlatoonMorale() {
    return getActiveSoldiers().mapToInt(soldier -> soldier.getMorale().getValue()).sum();
  }

  private Soldier randomActiveSoldier() {
    Random random = new Random();
    int bound = (int) getActiveSoldiers().count();
    if(bound > 0){
      int index = random.nextInt(bound);
      return getActiveSoldiers()
              .collect(Collectors.toList())
              .get(index);
    }else{
      return null;
    }
  }

  private boolean isAbleToFight(Soldier soldier) {
    return !isNotAbleToFight(soldier);
  }

  private boolean isNotAbleToFight(Soldier soldier) {
    return hasSevereWound(soldier) || isDead(soldier);
  }

  private boolean isDead(Soldier soldier) {
    return soldier.getProperties().getHealth().equals(Health.DEAD);
  }

  private boolean hasSevereWound(Soldier soldier) {
    return soldier.getProperties().getHealth().equals(Health.SEVERE_WOUND);
  }
}
