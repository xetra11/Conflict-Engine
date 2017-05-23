package com.condorgames.prototype.entities.equipment.weapons;

import com.condorgames.prototype.audio.AudioManager;
import com.condorgames.prototype.entities.equipment.weapons.Weapon.WeaponState;
import com.condorgames.prototype.entities.equipment.weapons.eventlistener.*;

public class WeaponExecutor implements WeaponEvent{

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

  public void execute(float deltaTime){

    if (ammoCount <= 0 && (weapon.getState().equals(Weapon.WeaponState.READY) || weapon.getState().equals(Weapon.WeaponState.CADENCE))) {
      weapon.setState(WeaponState.NO_AMMO);
      if (weaponEmptyListener != null) {
        weaponEmptyListener.onEmpty();
      }
    }

    if (remainingCadenceTime > 0 && weapon.getState().equals(Weapon.WeaponState.CADENCE)) {
      remainingCadenceTime -= deltaTime;
    }

    if (remainingCadenceTime < 0 && weapon.getState().equals(Weapon.WeaponState.CADENCE)) {
      weapon.setState(WeaponState.READY);
    }

    // TODO Jammed weapon state and event

    if (weapon.getState().equals(Weapon.WeaponState.READY)) {
      ammoCount--;
      if (weaponFiredListener != null) {
        weaponFiredListener.onFired();
      }
      weapon.setState(WeaponState.CADENCE);
      remainingCadenceTime = weapon.getCadence();
    }

    if (weapon.getState().equals(Weapon.WeaponState.NO_AMMO)) {
      weapon.setState(WeaponState.RELOADING);
      AudioManager.playReloading2WithBackground();
      remainingReloadTime = weapon.getReloadTime();
      if (weaponReloadListener != null) {
        weaponReloadListener.onReload();
      }
    }
    if (remainingReloadTime > 0 && weapon.getState().equals(Weapon.WeaponState.RELOADING)) {
      remainingReloadTime -= deltaTime;
    }
    if (remainingReloadTime < 0 && weapon.getState().equals(Weapon.WeaponState.RELOADING)) {
      weapon.reloadWeapon();
      weapon.setState(WeaponState.READY);
      System.out.println("Weapon reloaded!");
    }
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
