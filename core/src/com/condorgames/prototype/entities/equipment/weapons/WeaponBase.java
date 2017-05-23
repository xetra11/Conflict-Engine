package com.condorgames.prototype.entities.equipment.weapons;

import com.condorgames.prototype.audio.AudioManager;
import com.condorgames.prototype.entities.equipment.weapons.eventlistener.*;

public abstract class WeaponBase implements Weapon {

  private WeaponState weaponState;
  private int ammoCount;
  private final int maxAmmo;
  private static float remainingReloadTime;
  private static float remainingCadenceTime;

  private WeaponFiredListener weaponFiredListener;
  private WeaponReloadListener weaponReloadListener;
  private WeaponReloadedListener weaponReloadedListener;
  private WeaponEmptyListener weaponEmptyListener;
  private WeaponJammedListener weaponJammedListener;

  protected WeaponBase(int maxAmmo) {
    this.maxAmmo = maxAmmo;
    ammoCount = maxAmmo;
    weaponState = WeaponState.READY;
  }

  @Override
  public void fireWeapon(float deltaTime) {

    if (ammoCount <= 0 && weaponState.equals(WeaponState.READY)) {
      weaponState = WeaponState.NO_AMMO;
      weaponEmptyListener.onEmpty();
    }

    if (remainingCadenceTime > 0 && weaponState.equals(WeaponState.CADENCE)) {
      remainingCadenceTime -= deltaTime;
    }

    if (remainingCadenceTime < 0 && weaponState.equals(WeaponState.CADENCE)) {
      weaponState = WeaponState.READY;
    }

    // TODO Jammed weapon state and event

    if (weaponState.equals(WeaponState.READY)) {
      ammoCount--;
      weaponFiredListener.onFired();
      weaponState = WeaponState.CADENCE;
      remainingCadenceTime = getCadence();
    }

    if (weaponState.equals(WeaponState.NO_AMMO)) {
      weaponState = WeaponState.RELOADING;
      AudioManager.playReloading2WithBackground();
      remainingReloadTime = getReloadTime();
      weaponReloadListener.onReload();
    }
    if (remainingReloadTime > 0 && weaponState.equals(WeaponState.RELOADING)) {
      remainingReloadTime -= deltaTime;
    }
    if (remainingReloadTime < 0 && weaponState.equals(WeaponState.RELOADING)) {
      reloadWeapon();
      weaponState = WeaponState.READY;
      System.out.println("Weapon reloaded!");
    }
  }

  //<editor-fold desc="Interface Implementation">
  @Override
  public WeaponState getState() {
    return weaponState;
  }

  @Override
  public void setState(WeaponState state) {
    this.weaponState = state;
  }

  @Override
  public int getAmmoCount() {
    return ammoCount;
  }

  @Override
  public void reloadWeapon() {
    ammoCount = maxAmmo;
    weaponReloadedListener.onReloadFinished();
  }
  //</editor-fold>

  public void setWeaponFiredListener(WeaponFiredListener weaponFiredListener) {
    this.weaponFiredListener = weaponFiredListener;
  }

  public void setWeaponReloadListener(WeaponReloadListener weaponReloadListener) {
    this.weaponReloadListener = weaponReloadListener;
  }

  public void setWeaponReloadedListener(WeaponReloadedListener weaponReloadedListener) {
    this.weaponReloadedListener = weaponReloadedListener;
  }

  public void setWeaponEmptyListener(WeaponEmptyListener weaponEmptyListener) {
    this.weaponEmptyListener = weaponEmptyListener;
  }

  public void setWeaponJammedListener(WeaponJammedListener weaponJammedListener) {
    this.weaponJammedListener = weaponJammedListener;
  }
}
