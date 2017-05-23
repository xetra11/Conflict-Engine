package com.condorgames.prototype.entities.equipment.weapons;

import com.condorgames.prototype.audio.AudioManager;
import com.condorgames.prototype.entities.equipment.weapons.eventlistener.*;

import java.util.Objects;

public abstract class WeaponBase implements Weapon {

  private WeaponState weaponState;
  private int ammoCount;
  private final int maxAmmo;
  private static float remainingReloadTime;
  private static float remainingCadenceTime;

  private WeaponExecutor weaponExecutor;

  protected WeaponBase(int maxAmmo) {
    this.maxAmmo = maxAmmo;
    ammoCount = maxAmmo;
    weaponState = WeaponState.READY;
    this.weaponExecutor = new WeaponExecutor(this);
  }

  @Override
  public void fireWeapon(float deltaTime) {
    Objects.requireNonNull(weaponExecutor, "No WeaponExecuter set in " + this);
    weaponExecutor.execute(deltaTime);
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
  }

  @Override
  public int getMaxAmmo() {
    return maxAmmo;
  }

  //</editor-fold>

  public void setWeaponExecutor(WeaponExecutor weaponExecutor) {
    this.weaponExecutor = weaponExecutor;
    weaponExecutor.setWeapon(this);
  }

  public WeaponExecutor getWeaponExecutor() {
    return weaponExecutor;
  }
}
