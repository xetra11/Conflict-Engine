package com.condorgames.prototype.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.condorgames.prototype.creator.WeaponCreator;
import com.condorgames.prototype.entities.SoldierProperties.Health;
import com.condorgames.prototype.entities.equipment.weapons.Weapon;

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
  private Weapon weapon;
  private List<Soldier> soldiers = new ArrayList<>(9);

  public Platoon(Body body, Faction faction) {
    super(body, faction);

    //Soldiers
    soldiers.add(new Soldier(60, WeaponCreator.createRifle()));
    soldiers.add(new Soldier(60, WeaponCreator.createRifle()));
    soldiers.add(new Soldier(60, WeaponCreator.createRifle()));
    soldiers.add(new Soldier(60, WeaponCreator.createRifle()));
    soldiers.add(new Soldier(60, WeaponCreator.createRifle()));
    soldiers.add(new Soldier(60, WeaponCreator.createRifle()));
    soldiers.add(new Soldier(60, WeaponCreator.createRifle()));
    soldiers.add(new Soldier(60, WeaponCreator.createRifle()));
    soldiers.add(new Soldier(60, WeaponCreator.createRifle()));

  }

  @Override
  public void fire(float deltaTime, HitListener hitListener) {
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
    return Math.toIntExact(getActiveSoldiers().count());
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

    if (platoonMorale > UPPER_HIGH_THRESHOLD) {
      return MoraleState.FANATIC;
    } else if (platoonMorale > UPPER_NORMAL_THRESHOLD) {
      return MoraleState.HIGH;
    } else if (platoonMorale > UPPER_LOW_THRESHOLD) {
      return MoraleState.NORMAL;
    } else if (platoonMorale > UPPER_FLEEING_THRESHOLD) {
      return MoraleState.LOW;
    } else if (platoonMorale >= UPPER_PINNEDDOWN_THRESHOLD) {
      return MoraleState.FLEEING;
    } else {
      return MoraleState.PINNED_DOWN;
    }
  }

  private int getPlatoonMorale() {
    return soldiers.stream().mapToInt(soldier -> soldier.getMorale().getValue()).sum();
  }

  private Soldier randomActiveSoldier() {
    Random random = new Random();
    int bound = (int) getActiveSoldiers().count();
    if(bound > 0){
      int index = random.nextInt((int) getActiveSoldiers().count());
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
