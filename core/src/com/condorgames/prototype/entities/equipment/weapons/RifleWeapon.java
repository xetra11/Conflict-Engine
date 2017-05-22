package com.condorgames.prototype.entities.equipment.weapons;

public class RifleWeapon extends WeaponBase {
  private float reloadTime;
  private float cadence;

  public RifleWeapon() {
    super(12);
    this.reloadTime = 6f;
    this.cadence = 1f;
  }

  @Override
  public float getReloadTime() {
    return reloadTime;
  }

  @Override
  public float getCadence() {
    return cadence;
  }
}
