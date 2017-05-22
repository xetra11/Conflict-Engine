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
    return 0;
  }

  @Override
  public float getCadence() {
    return 0;
  }
}
