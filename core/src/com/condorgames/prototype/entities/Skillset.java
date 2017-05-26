package com.condorgames.prototype.entities;

public interface Skillset {
  public enum AimSkill{
    VETERAN,
    EXPERT,
    NORMAL,
    WEAK
  }

  public enum WeaponSkill {
    VETERAN,
    EXPERT,
    NORMAL,
    WEAK
  }

  AimSkill getAimSkill();
  WeaponSkill getWeaponSkill();
  void setAimSkill(AimSkill aimSkill);
  void setWeaponSkill(WeaponSkill weaponSkill);

}
