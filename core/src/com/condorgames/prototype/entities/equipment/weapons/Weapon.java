package com.condorgames.prototype.entities.equipment.weapons;

public interface Weapon {

  public enum WeaponState{
    RELOADING,
    READY,
    JAMMED
  }

  WeaponState getState();
  void setState(WeaponState state);
  int getAmmoCount();
  void reloadWeapon();
  void fireWeapon(WeaponFiredListener weaponFiredListener);
  float getReloadTime();
  float getCadence();
}
