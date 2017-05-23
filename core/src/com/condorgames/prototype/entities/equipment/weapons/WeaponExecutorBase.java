package com.condorgames.prototype.entities.equipment.weapons;

import com.condorgames.prototype.audio.AudioManager;
import com.condorgames.prototype.entities.equipment.weapons.WeaponProperties.Status;
import com.condorgames.prototype.entities.equipment.weapons.eventlistener.*;

public class WeaponExecutorBase implements WeaponEvent, WeaponExecutor {

  private int ammoCount;
  private float remainingCadenceTime;
  private float remainingReloadTime;
  private WeaponProperties weaponProperties;

  private WeaponFiredListener weaponFiredListener;
  private WeaponReloadListener weaponReloadListener;
  private WeaponReloadedListener weaponReloadedListener;
  private WeaponEmptyListener weaponEmptyListener;
  private WeaponJammedListener weaponJammedListener;

  public WeaponExecutorBase(WeaponProperties weaponProperties) {
    this.weaponProperties = weaponProperties;
    this.ammoCount = weaponProperties.getAmmoCount();
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

    // TODO Jammed weaponProperties state and event

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
    weaponProperties.setAmmoCount(weaponProperties.getMaxAmmo());
    weaponProperties.setState(Status.READY);
    System.out.println("Weapon reloaded!");
  }

  private void handleReload(float deltaTime) {
    remainingReloadTime -= deltaTime;
  }

  private boolean hasFinishedReloading() {
    return remainingReloadTime < 0 && weaponProperties.getState().equals(Status.RELOADING);
  }

  private boolean isInReloadState() {
    return remainingReloadTime > 0 && weaponProperties.getState().equals(Status.RELOADING);
  }

  private void handleReload() {
    weaponProperties.setState(Status.RELOADING);
    AudioManager.playReloading2WithBackground();
    remainingReloadTime = weaponProperties.getReloadTime();
    if (weaponReloadListener != null) {
      weaponReloadListener.onReload();
    }
  }

  private boolean isInAmmoEmptyState() {
    return weaponProperties.getState().equals(Status.NO_AMMO);
  }

  private void handleReady() {
    ammoCount--;
    System.out.println("Fired");
    if (weaponFiredListener != null) {
      weaponFiredListener.onFired();
    }
    weaponProperties.setState(Status.CADENCE);
    remainingCadenceTime = weaponProperties.getCadence();
  }

  private boolean isReadyState() {
    return weaponProperties.getState().equals(Status.READY);
  }

  private void handleHasLeftCadenceState() {
    weaponProperties.setState(Status.READY);
  }

  private void handleInCadence(float deltaTime) {
    remainingCadenceTime -= deltaTime;
  }

  private boolean hasLeftCadenceState() {
    return remainingCadenceTime < 0 && weaponProperties.getState().equals(Status.CADENCE);
  }

  private boolean isInCadenceState() {
    return remainingCadenceTime > 0 && weaponProperties.getState().equals(Status.CADENCE);
  }

  private void handleAmmoEmpty() {
    weaponProperties.setState(Status.NO_AMMO);
    if (weaponEmptyListener != null) {
      weaponEmptyListener.onEmpty();
    }
  }

  private boolean isAmmoEmpty() {
    return ammoCount <= 0 && (weaponProperties.getState().equals(Status.READY) || weaponProperties.getState().equals(Status.CADENCE));
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

  public void setWeaponProperties(WeaponProperties weaponProperties) {
    this.weaponProperties = weaponProperties;
  }
}
