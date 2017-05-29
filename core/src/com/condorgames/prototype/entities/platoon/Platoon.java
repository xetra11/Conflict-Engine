package com.condorgames.prototype.entities.platoon;

import com.badlogic.gdx.physics.box2d.Body;
import com.condorgames.prototype.battleresolver.Morale;
import com.condorgames.prototype.creator.SquadCreator;
import com.condorgames.prototype.entities.equipment.weapons.interfaces.Fireable;
import com.condorgames.prototype.entities.soldier.Soldier;
import com.condorgames.prototype.entities.soldier.SoldierProperties.Health;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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

  @Override
  public void fire(float deltaTime, Fireable.HitListener hitListener) {
    soldiers.stream()
            .filter(this::isAbleToFight)
            .forEach(soldier -> soldier.fire(deltaTime, hitListener));
  }

  @Override
  public int getAmmo() {
    return soldiers.stream()
            .mapToInt(Soldier::getAmmo)
            .sum();
  }


  @Override
  public int getStrength() {
    return Math.toIntExact(soldiers.stream()
            .filter(this::isAbleToFight)
            .count());
  }

  @Override
  public void takeCasualty() {
    System.out.println("Casualty!");
    randomActiveSoldier().wound();
  }

  @Override
  public void setMorale(Morale.MoraleState morale) {
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
  public Morale.MoraleState getMorale() {
    //TODO Add morale debuffs here so they can be aggregated
    int platoonMorale = getPlatoonMorale();
    return getPlatoonMoraleState(platoonMorale);
  }

  private Morale.MoraleState getPlatoonMoraleState(int platoonMorale) {
    /***
     * 45 = Fanatic
     * 36 = High
     * 27 = Normal
     * 18 = Low
     * 9 = Fleeing
     * 0 = Pinned_Down
     */

    if (platoonMorale > UPPER_HIGH_THRESHOLD) {
      return Morale.MoraleState.FANATIC;
    } else if (platoonMorale > UPPER_NORMAL_THRESHOLD) {
      return Morale.MoraleState.HIGH;
    } else if (platoonMorale > UPPER_LOW_THRESHOLD) {
      return Morale.MoraleState.NORMAL;
    } else if (platoonMorale > UPPER_FLEEING_THRESHOLD) {
      return Morale.MoraleState.LOW;
    } else if (platoonMorale >= UPPER_PINNEDDOWN_THRESHOLD) {
      return Morale.MoraleState.FLEEING;
    } else {
      return Morale.MoraleState.PINNED_DOWN;
    }
  }

  private int getPlatoonMorale() {
    return soldiers.stream().mapToInt(soldier -> soldier.getMorale().getValue()).sum();
  }

  private Soldier randomActiveSoldier() {
    Random random = new Random();
    List<Soldier> activeSoldiers = soldiers.stream()
            .filter(this::isAbleToFight)
            .collect(Collectors.toList());
    int index = random.nextInt(activeSoldiers.size());
    return activeSoldiers.get(index);
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
