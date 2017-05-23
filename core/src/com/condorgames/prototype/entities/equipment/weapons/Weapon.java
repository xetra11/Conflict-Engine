package com.condorgames.prototype.entities.equipment.weapons;

import com.condorgames.prototype.entities.equipment.weapons.eventlistener.*;

public interface Weapon extends WeaponEvent{

  public enum WeaponState{
    RELOADING,
    READY,
    CADENCE,
    NO_AMMO,
    JAMMED
  }

  WeaponState getState();
  void setState(WeaponState state);
  int getAmmoCount();
  void reloadWeapon();
  void fireWeapon(float deltaTime);
  float getReloadTime();
  float getCadence();
}
