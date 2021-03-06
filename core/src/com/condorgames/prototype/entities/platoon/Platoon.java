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

public class Platoon extends SteerablePlatoonEntity {
  private List<Soldier> soldiers = new ArrayList<>();

  public Platoon(Body body, PlatoonEntityBase.Faction faction) {
    super(body, faction);

    if (faction.equals(Faction.AXIS)) {
      soldiers.addAll(SquadCreator.createAxisRifleSquad());
    } else {
      soldiers.addAll(SquadCreator.createAlliedRifleSquad());
    }

  }

  //TODO Create some more fine grained platoon mechanics due the high amount of methods

  @Override
  public void fire(float deltaTime, Fireable.HitListener hitListener) {

    salvageAmmoOfInactiveSoldiers();

    getActiveSoldiers()
            .forEach(soldier -> soldier.fire(deltaTime, hitListener));
  }

  private void salvageAmmoOfInactiveSoldiers() {
    getActiveSoldiers().stream()
            .filter(soldier -> soldier.getAmmo() <= 0)
            .forEach(soldier -> {
              int amountAmmo = getAmmoOfDeadSoldier();
              soldier.setAmmo(amountAmmo);
              if (amountAmmo > 0) {
                System.out.println(soldier.getName() + " ammo salvaged: " + amountAmmo);
              }
            });
  }

  private List<Soldier> getActiveSoldiers() {
    return soldiers.stream()
            .filter(this::isAbleToFight)
            .collect(Collectors.toList());
  }

  private List<Soldier> getInactiveSoldiers() {
    return soldiers.stream()
            .filter(this::isNotAbleToFight)
            .collect(Collectors.toList());
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
            .stream()
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
  public void setMoraleBase(MoraleBase morale) {
    randomActiveSoldier().setMoraleBase(morale);
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
  public MoraleBase getMoraleBase() {
    //TODO Add morale debuffs here so they can be aggregated

    return null;
  }

  @Override
  public float getMorale() {
    return getPlatoonMorale();
  }

  @Override
  public void setMorale(float morale) {
    //TODO not implemented
  }

  private float getPlatoonMorale() {
    double moraleSum = getActiveSoldiers()
            .stream()
            .mapToDouble(soldier -> soldier.getMorale())
            .sum();
    return (float) (moraleSum / getActiveSoldiers().size());
  }

  private Soldier randomActiveSoldier() {
    Random random = new Random();
    List<Soldier> activeSoldiers = getActiveSoldiers();
    int bound = activeSoldiers.size();
    if (bound > 0) {
      int randomIndex = random.nextInt(bound);
      return activeSoldiers.get(randomIndex);
    } else {
      return null;
    }
  }

  private Soldier randomInactiveSoldierWithAmmo() {
    Random random = new Random();
    List<Soldier> inactiveSoldiersWithAmmo = getInactiveSoldiers().stream()
            .filter(soldier -> soldier.getAmmo() > 0)
            .collect(Collectors.toList());
    int bound = inactiveSoldiersWithAmmo.size();
    if (bound > 0) {
      int randomIndex = random.nextInt(bound);
      return inactiveSoldiersWithAmmo.get(randomIndex);
    } else {
      return null;
    }
  }

  private int getAmmoOfDeadSoldier() {
    Soldier inactiveSoldier = randomInactiveSoldierWithAmmo();
    if (inactiveSoldier != null) {
      int ammoTaken = inactiveSoldier.getAmmo();
      inactiveSoldier.setAmmo(0);
      return ammoTaken;
    }
    return 0;
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
