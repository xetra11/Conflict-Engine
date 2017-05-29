package com.condorgames.prototype.entities.soldier;

import com.condorgames.prototype.battleresolver.Morale;
import com.condorgames.prototype.entities.soldier.Skillset.AimSkill;
import com.condorgames.prototype.entities.soldier.Skillset.WeaponSkill;

import java.util.Random;

public class SoldierBase implements Morale {
  private SoldierProperties soldierProperties;
  private Skillset skillset;

  public SoldierBase() {
    this(AimSkill.NORMAL, WeaponSkill.NORMAL, MoraleState.NORMAL, SoldierProperties.Health.OK);
  }

  public SoldierBase(AimSkill aimSkill) {
    this(aimSkill, WeaponSkill.NORMAL, MoraleState.NORMAL, SoldierProperties.Health.OK);
  }

  public SoldierBase(AimSkill aimSkill, WeaponSkill weaponSkill) {
    this(aimSkill, weaponSkill, MoraleState.NORMAL, SoldierProperties.Health.OK);
  }

  public SoldierBase(AimSkill aimSkill, WeaponSkill weaponSkill, MoraleState moraleState, SoldierProperties.Health health) {
    this.soldierProperties = new SoldierPropertiesImpl(moraleState, health);
    this.skillset = new SkillsetImpl(aimSkill, weaponSkill);
  }

  public Skillset getSkills() {
    return skillset;
  }

  public SoldierProperties getProperties() {
    return soldierProperties;
  }

  public void wound() {
    Random random = new Random();
    int index = random.nextInt(Health.values().length - 1);

    switch (index) {
      case 1:
        //if soldier already is lightly wounded - make him severely wounded!
        if(soldierProperties.getHealth().equals(Health.LIGHT_WOUND)){
          soldierProperties.setHealth(Health.SEVERE_WOUND);
          System.out.println("already lightly wounded! -> severe wound!");
        }else{
          soldierProperties.setHealth(Health.LIGHT_WOUND);
          System.out.println("lightly wounded!");
        }
        break;
      case 2:
        soldierProperties.setHealth(Health.SEVERE_WOUND);
        System.out.println("severe wounded!");
        break;
      case 3:
        soldierProperties.setHealth(Health.DEAD);
        System.out.println("dead wounded!");
        break;
    }
  }

  @Override
  public void setMorale(MoraleState morale) {
    soldierProperties.setMorale(morale);
  }

  @Override
  public void decreaseMorale() {
    soldierProperties.decreaseMorale();
  }

  @Override
  public void raiseMorale() {
    soldierProperties.raiseMorale();
  }

  @Override
  public MoraleState getMorale() {
    return soldierProperties.getMorale();
  }
}
