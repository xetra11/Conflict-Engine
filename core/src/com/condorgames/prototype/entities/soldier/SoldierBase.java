package com.condorgames.prototype.entities.soldier;

import com.condorgames.prototype.battleresolver.Morale;
import com.condorgames.prototype.entities.soldier.Skillset.AimSkill;
import com.condorgames.prototype.entities.soldier.Skillset.WeaponSkill;

import static com.condorgames.prototype.entities.soldier.SoldierProperties.*;

public abstract class SoldierBase implements Morale, Identity {
  private SoldierProperties soldierProperties;
  private Skillset skillset;

  public SoldierBase() {
    this(AimSkill.NORMAL, WeaponSkill.NORMAL, MoraleBase.NORMAL, Health.OK, "NoName");
  }

  public SoldierBase(AimSkill aimSkill, String name) {
    this(aimSkill, WeaponSkill.NORMAL, MoraleBase.NORMAL, Health.OK, name);
  }

  public SoldierBase(AimSkill aimSkill, WeaponSkill weaponSkill, String name) {
    this(aimSkill, weaponSkill, MoraleBase.NORMAL, Health.OK, name);
  }

  public SoldierBase(AimSkill aimSkill, WeaponSkill weaponSkill, MoraleBase moraleState, Health health, String name) {
    this.soldierProperties = new SoldierPropertiesImpl(moraleState, health, name);
    this.skillset = new SkillsetImpl(aimSkill, weaponSkill);
  }

  public Skillset getSkills() {
    return skillset;
  }

  public SoldierProperties getProperties() {
    return soldierProperties;
  }


  public abstract void wound();

  @Override
  public String getName() {
    return soldierProperties.getName();
  }

  @Override
  public void setMoraleBase(MoraleBase morale) {
    soldierProperties.setMoraleBase(morale);
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
  public MoraleBase getMoraleBase() {
    return soldierProperties.getMoraleBase();
  }
}
