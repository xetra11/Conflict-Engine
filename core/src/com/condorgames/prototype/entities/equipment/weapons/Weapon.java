package com.condorgames.prototype.entities.equipment.weapons;

public interface Weapon {

  public enum WeaponState{
    RELOADING,
    READY,
    NO_AMMO,
    JAMMED
  }

  WeaponState getState();
  void setState(WeaponState state);
  int getAmmoCount();
  void reloadWeapon();
  void fireWeapon(float deltaTime, WeaponFiredListener weaponFiredListener);
  float getReloadTime();
  float getCadence();
}
