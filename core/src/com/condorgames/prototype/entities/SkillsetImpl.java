package com.condorgames.prototype.entities;

public class SkillsetImpl implements Skillset {
  AimSkill aimSkill;
  WeaponSkill weaponSkill;

  public SkillsetImpl(AimSkill aimSkill, WeaponSkill weaponSkill) {
    this.aimSkill = aimSkill;
    this.weaponSkill = weaponSkill;
  }

  @Override
  public AimSkill getAimSkill() {
    return aimSkill;
  }

  @Override
  public WeaponSkill getWeaponSkill() {
    return weaponSkill;
  }

  @Override
  public void setAimSkill(AimSkill aimSkill) {
    this.aimSkill = aimSkill;
  }

  @Override
  public void setWeaponSkill(WeaponSkill weaponSkill) {
    this.weaponSkill = weaponSkill;
  }
}
