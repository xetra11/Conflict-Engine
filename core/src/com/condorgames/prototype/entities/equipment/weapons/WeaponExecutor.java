package com.condorgames.prototype.entities.equipment.weapons;

import com.condorgames.prototype.audio.AudioManager;
import com.condorgames.prototype.entities.equipment.weapons.Weapon.WeaponState;
import com.condorgames.prototype.entities.equipment.weapons.eventlistener.*;

public class WeaponExecutor implements WeaponEvent {

  private Weapon weapon;
  private int ammoCount;
  private float remainingCadenceTime;
  private float remainingReloadTime;

  private WeaponFiredListener weaponFiredListener;
  private WeaponReloadListener weaponReloadListener;
  private WeaponReloadedListener weaponReloadedListener;
  private WeaponEmptyListener weaponEmptyListener;
  private WeaponJammedListener weaponJammedListener;

  public WeaponExecutor(Weapon weapon) {
    this.weapon = weapon;
    this.ammoCount = weapon.getAmmoCount();
  }

  public void execute(float deltaTime) {

    if (isAmmoEmpty()) {
      handleAmmoEmpty();
    }

    if (isInCadenceState()) {
      handleInCadence(deltaTime);
    }

    if (hasLeftCadenceState()) {
      handleHasLeftCadenceState();
    }

    // TODO Jammed weapon state and event

    if (isReadyState()) {
      handleReady();
    }

    if (isInAmmoEmptyState()) {
      handleReload();
    }
    if (isInReloadState()) {
      handleReload(deltaTime);
    }
    if (hasFinishedReloading()) {
      handleFinishedReloading();
    }
  }

  private void handleFinishedReloading() {
    weapon.reloadWeapon();
    weapon.setState(WeaponState.READY);
    System.out.println("Weapon reloaded!");
  }

  private void handleReload(float deltaTime) {
    remainingReloadTime -= deltaTime;
  }

  private boolean hasFinishedReloading() {
    return remainingReloadTime < 0 && weapon.getState().equals(WeaponState.RELOADING);
  }

  private boolean isInReloadState() {
    return remainingReloadTime > 0 && weapon.getState().equals(WeaponState.RELOADING);
  }

  private void handleReload() {
    weapon.setState(WeaponState.RELOADING);
    AudioManager.playReloading2WithBackground();
    remainingReloadTime = weapon.getReloadTime();
    if (weaponReloadListener != null) {
      weaponReloadListener.onReload();
    }
  }

  private boolean isInAmmoEmptyState() {
    return weapon.getState().equals(WeaponState.NO_AMMO);
  }

  private void handleReady() {
    ammoCount--;
    if (weaponFiredListener != null) {
      weaponFiredListener.onFired();
    }
    weapon.setState(WeaponState.CADENCE);
    remainingCadenceTime = weapon.getCadence();
  }

  private boolean isReadyState() {
    return weapon.getState().equals(WeaponState.READY);
  }

  private void handleHasLeftCadenceState() {
    weapon.setState(WeaponState.READY);
  }

  private void handleInCadence(float deltaTime) {
    remainingCadenceTime -= deltaTime;
  }

  private boolean hasLeftCadenceState() {
    return remainingCadenceTime < 0 && weapon.getState().equals(WeaponState.CADENCE);
  }

  private boolean isInCadenceState() {
    return remainingCadenceTime > 0 && weapon.getState().equals(WeaponState.CADENCE);
  }

  private void handleAmmoEmpty() {
    weapon.setState(WeaponState.NO_AMMO);
    if (weaponEmptyListener != null) {
      weaponEmptyListener.onEmpty();
    }
  }

  private boolean isAmmoEmpty() {
    return ammoCount <= 0 && (weapon.getState().equals(WeaponState.READY) || weapon.getState().equals(WeaponState.CADENCE));
  }

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

  public void setWeapon(Weapon weapon) {
    this.weapon = weapon;
  }
}
