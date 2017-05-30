package com.condorgames.prototype.entities.platoon;

import com.badlogic.gdx.physics.box2d.Body;
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

    if (faction.equals(Faction.AXIS)) {
      soldiers.addAll(SquadCreator.createAxisRifleSquad());
    } else {
      soldiers.addAll(SquadCreator.createAlliedRifleSquad());
    }

  }

  //TODO Salvage Ammo from dead comrades!

  @Override
  public void fire(float deltaTime, Fireable.HitListener hitListener) {

    salvageAmmoOfInactiveSoldiers();

    getActiveSoldiers()
            .forEach(soldier -> soldier.fire(deltaTime, hitListener));
  }

  private void salvageAmmoOfInactiveSoldiers() {
    getActiveSoldiers()
            .filter(soldier -> soldier.getAmmo() <= 0)
            .forEach(soldier -> {
              int amountAmmo = getAmmoOfDeadSoldier();
              soldier.setAmmo(amountAmmo);
              System.out.println(soldier.getName() + " ammo salvaged: " + amountAmmo);
            });
  }

  private Stream<Soldier> getActiveSoldiers() {
    return soldiers.stream()
            .filter(this::isAbleToFight);
  }

  private Stream<Soldier> getInactiveSoldiers() {
    return soldiers.stream()
            .filter(this::isNotAbleToFight);
  }


  @Override
  public int getAmmo() {
    //all soldiers, even not active ones, since they carry ammo aswell
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
    if (bound > 0) {
      int index = random.nextInt(bound);
      return getActiveSoldiers()
              .collect(Collectors.toList())
              .get(index);
    } else {
      return null;
    }
  }

  private Soldier randomInactiveSoldierWithAmmo() {
    Random random = new Random();
    int bound = (int) getInactiveSoldiers().count();
    if (bound > 0) {
      int index = random.nextInt(bound);
      return getInactiveSoldiers().filter(soldier -> soldier.getAmmo() > 0)
              .collect(Collectors.toList())
              .get(index);
    } else {
      return null;
    }
  }

  private int getAmmoOfDeadSoldier() {
    Soldier inactiveSoldier = randomInactiveSoldierWithAmmo();
    int ammoTaken = inactiveSoldier.getAmmo();
    inactiveSoldier.setAmmo(0);
    return ammoTaken;
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
