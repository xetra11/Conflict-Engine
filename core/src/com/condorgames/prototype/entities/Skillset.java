package com.condorgames.prototype.entities;

import com.condorgames.prototype.entities.equipment.weapons.WeaponBase;

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
