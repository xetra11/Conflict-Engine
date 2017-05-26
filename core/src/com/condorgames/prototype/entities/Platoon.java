package com.condorgames.prototype.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.condorgames.prototype.battleresolver.Morale;
import com.condorgames.prototype.creator.WeaponCreator;
import com.condorgames.prototype.entities.SoldierProperties.Health;
import com.condorgames.prototype.entities.equipment.weapons.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    soldiers.forEach(soldier -> soldier.fire(deltaTime, hitListener));
  }

  @Override
  public int getAmmo() {
    return soldiers.stream().mapToInt(Soldier::getAmmo).sum();
  }

  @Override
  public int getStrength() {
    return Math.toIntExact(soldiers.stream()
            .filter(soldier -> isNotAbleToFight(soldier))
            .count());
  }

  @Override
  public void setMorale(MoraleState morale) {
    randomSoldier().setMorale(morale);
  }

  @Override
  public void decreaseMorale() {
    randomSoldier().decreaseMorale();
  }

  @Override
  public void raiseMorale() {
    randomSoldier().raiseMorale();
  }

  @Override
  public MoraleState getMorale() {
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
    } else if(platoonMorale >= UPPER_PINNEDDOWN_THRESHOLD){
      return MoraleState.FLEEING;
    } else {
      return MoraleState.PINNED_DOWN;
    }
  }

  private int getPlatoonMorale() {
    return soldiers.stream().mapToInt(soldier -> soldier.getMorale().getValue()).sum();
  }

  private Soldier randomSoldier() {
    // TODO Only soldiers who are alive/not wounded
    Random random = new Random();
    int index = random.nextInt(soldiers.size());
    return soldiers.get(index);
  }

  private boolean isNotAbleToFight(Soldier soldier) {
    return hasSevereWound(soldier) == false &&
            isDead(soldier) == false;
  }

  private boolean isDead(Soldier soldier) {
    return soldier.getProperties().getHealth().equals(Health.DEAD);
  }

  private boolean hasSevereWound(Soldier soldier) {
    return soldier.getProperties().getHealth().equals(Health.SEVERE_WOUND);
  }
}
