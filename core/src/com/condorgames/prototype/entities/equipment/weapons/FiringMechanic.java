package com.condorgames.prototype.entities.equipment.weapons;

import com.condorgames.prototype.battleresolver.HitMechanic;
import com.condorgames.prototype.entities.equipment.weapons.WeaponProperties.Status;
import com.condorgames.prototype.helper.Cooldown;
import com.condorgames.prototype.listener.*;

public class FiringMechanic implements Fireable, WeaponEvent {

  private Cooldown reloadCooldown;
  private Cooldown cadenceCooldown;
  private AimMechanic aimMechanic;
  private HitMechanic hitMechanic;

  private WeaponProperties weaponProperties;

  private WeaponFiredListener weaponFiredListener;
  private WeaponReloadListener weaponReloadListener;
  private WeaponReloadedListener weaponReloadedListener;
  private WeaponEmptyListener weaponEmptyListener;
  private WeaponJammedListener weaponJammedListener;

  public FiringMechanic(WeaponProperties weaponProperties) {
    this.weaponProperties = weaponProperties;
    reloadCooldown = new Cooldown(weaponProperties.getReloadTime());
    cadenceCooldown = new Cooldown(weaponProperties.getCadence());
    aimMechanic = new AimMechanic();
    hitMechanic = new HitMechanic();
  }

  public void fire(float deltaTime, HitListener hitListener) {

    if (isAmmoEmpty()) {
      handleAmmoEmpty();
    }

    if (isAmmoEmptyState()) {
      startReloading();
    }

    if (isReloading()) {
      handleReloadingCooldown(deltaTime);
    }

    if (isReadyState()) {
      handleFire(deltaTime, hitListener);
    }

    if (isCadenceState()) {
      handleCadenceCooldown(deltaTime);
    }

    // TODO Jammed weaponProperties state and event

  }

  private boolean isCadenceState() {
    return weaponProperties.getState().equals(Status.CADENCE);
  }

  private void handleCadenceCooldown(float deltaTime) {
    cadenceCooldown.isDone(deltaTime, () -> {
      weaponProperties.setState(Status.READY);
      cadenceCooldown.reset();
    });
  }

  private void handleReloadingCooldown(float deltaTime) {
    reloadCooldown.isDone(deltaTime, () -> {
      weaponProperties.setState(Status.READY);
      reloadCooldown.reset();

      // reloaded callback
      if (weaponReloadedListener != null) {
        weaponReloadedListener.onReloadFinished();
      }
    });
  }

  private void startReloading() {
    // set new state
    weaponProperties.setState(Status.RELOADING);
    // reloading callback
    if (weaponReloadListener != null) {
      weaponReloadListener.onReload();
    }
  }

  private boolean isReloading() {
    return weaponProperties.getState().equals(Status.RELOADING);
  }

  private boolean isAmmoEmptyState() {
    return weaponProperties.getState().equals(Status.NO_AMMO);
  }

  private void handleFire(float deltaTime, HitListener hitListener) {
    aimMechanic.aim(deltaTime, () -> {
      weaponProperties.setAmmoCount(weaponProperties.getAmmoCount()-1);
      if (weaponFiredListener != null) {
        weaponFiredListener.onFired();
      }
      weaponProperties.setState(Status.CADENCE);
      hitListener.onHit(hitMechanic.hit());
    });
  }

  private boolean isReadyState() {
    return weaponProperties.getState().equals(Status.READY);
  }

  private void handleAmmoEmpty() {
    weaponProperties.setState(Status.NO_AMMO);
    if (weaponEmptyListener != null) {
      weaponEmptyListener.onEmpty();
    }
  }

  private boolean isAmmoEmpty() {
    return weaponProperties.getAmmoCount() <= 0 && isAbleToShoot();
  }

  private boolean isAbleToShoot() {
    return isReadyState() || isCadenceState();
  }

  //<editor-fold desc="Listener">
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
  //</editor-fold>
}
