package com.condorgames.prototype.entities.equipment.weapons;

public abstract class WeaponCreator {

  public static Weapon createRifle() {
    WeaponPropertiesBase weaponPropertiesBase = new WeaponPropertiesBase(12, WeaponProperties.Type.RIFLE);
    weaponPropertiesBase.setReloadTime(6f);
    weaponPropertiesBase.setCadence(2f);
    WeaponBase weaponBase = new WeaponBase(weaponPropertiesBase);
    return weaponBase;
  }
}
