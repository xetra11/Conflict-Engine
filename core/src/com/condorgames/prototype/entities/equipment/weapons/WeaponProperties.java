package com.condorgames.prototype.entities.equipment.weapons;

public interface WeaponProperties {
  public enum Type {
    RIFLE,
    LMG,
    HMG,
    SMG;
  }

  public enum Status {
    RELOADING,
    READY,
    CADENCE,
    NO_AMMO,
    JAMMED;
  }

  Status getState();

  void setState(Status state);

  int getAmmoCount();

  void setAmmoCount(int amount);

  int getAmmoCapacity();

  float getReloadTime();

  void setReloadTime(float reloadTime);

  float getCadence();

  void setCadence(float cadence);

  Type getType();

}
