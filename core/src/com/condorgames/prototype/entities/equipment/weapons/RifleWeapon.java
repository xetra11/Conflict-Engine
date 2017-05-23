package com.condorgames.prototype.entities.equipment.weapons;

public class RifleWeapon extends WeaponBase {
  private float reloadTime;
  private float cadence;


  public RifleWeapon() {
    super(12, WeaponProperties.Type.RIFLE);
    this.reloadTime = 6f;
    this.cadence = 1f;
  }

  public float getReloadTime() {
    return reloadTime;
  }

  public float getCadence() {
    return cadence;
  }

//  @Override
//  public void setWeaponFiredListener(WeaponFiredListener weaponFiredListener) {
//    getWeaponExecutor().setWeaponFiredListener(weaponFiredListener);
//  }
//
//  @Override
//  public void setWeaponReloadListener(WeaponReloadListener weaponReloadListener) {
//    getWeaponExecutor().setWeaponReloadListener(weaponReloadListener);
//  }
//
//  @Override
//  public void setWeaponReloadedListener(WeaponReloadedListener weaponReloadedListener) {
//    getWeaponExecutor().setWeaponReloadedListener(weaponReloadedListener);
//  }
//
//  @Override
//  public void setWeaponEmptyListener(WeaponEmptyListener weaponEmptyListener) {
//    getWeaponExecutor().setWeaponEmptyListener(weaponEmptyListener);
//  }
//
//  @Override
//  public void setWeaponJammedListener(WeaponJammedListener weaponJammedListener) {
//    getWeaponExecutor().setWeaponJammedListener(weaponJammedListener);
//  }
}
