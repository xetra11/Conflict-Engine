package com.condorgames.prototype.entities.equipment.weapons;

import com.badlogic.gdx.Gdx;
import com.condorgames.prototype.audio.AudioManager;
import com.condorgames.prototype.entities.equipment.weapons.WeaponProperties.Status;
import com.condorgames.prototype.helper.Cooldown;
import com.condorgames.prototype.listener.*;

public class WeaponExecutorBase implements WeaponEvent, WeaponExecutor {

  private int ammoCount;
  private float remainingCadenceTime;
  private float remainingReloadTime;
  private Cooldown reloadCooldown;
  private WeaponProperties weaponProperties;

  private WeaponFiredListener weaponFiredListener;
  private WeaponReloadListener weaponReloadListener;
  private WeaponReloadedListener weaponReloadedListener;
  private WeaponEmptyListener weaponEmptyListener;
  private WeaponJammedListener weaponJammedListener;

  public WeaponExecutorBase(WeaponProperties weaponProperties) {
    this.weaponProperties = weaponProperties;
    this.ammoCount = weaponProperties.getAmmoCount();
    reloadCooldown = new Cooldown(weaponProperties.getAmmoCount());
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
      // set new state
      weaponProperties.setState(Status.RELOADING);
      // play sudio
      AudioManager.playReloading2WithBackground();
      // reloading callback
      if (weaponReloadListener != null) {
        weaponReloadListener.onReload();
      }
    }

    if (isInReloadState()) {
      reloadCooldown.isDone(deltaTime, () -> {
        weaponProperties.setState(Status.READY);
        ammoCount = weaponProperties.getMaxAmmo();
        reloadCooldown.reset();

        // reloaded callback
        if (weaponReloadedListener != null) {
          weaponReloadedListener.onReloadFinished();
        }
      });

    }


//    if (isInReloadState()) {
//      handleReload(deltaTime);
//    }
//    if (hasFinishedReloading()) {
//      handleFinishedReloading();
//    }
  }

  private void handleFinishedReloading() {
    weaponProperties.setAmmoCount(weaponProperties.getMaxAmmo());
    weaponProperties.setState(Status.READY);
    ammoCount = weaponProperties.getMaxAmmo();
    System.out.println("Weapon reloaded!");
  }

  private boolean isInReloadState() {
    return weaponProperties.getState().equals(Status.RELOADING);
  }

  private boolean isInAmmoEmptyState() {
    return weaponProperties.getState().equals(Status.NO_AMMO);
  }

  private void handleReady() {
    ammoCount--;
    System.out.print("Fired: ");
    hitResolveMock();
    if (weaponFiredListener != null) {
      weaponFiredListener.onFired();
    }
    weaponProperties.setState(Status.CADENCE);
    remainingCadenceTime = weaponProperties.getCadence();
  }

  private void hitResolveMock() {
    double random = Math.random();
    if (random > 0.95f) {
      System.out.println("hit! " + random);
    } else if (random > 0.5f) {
      System.out.println("surpressing hit! " + random);
    } else {
      System.out.println("no effect - bullet to the sky!");
    }
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
