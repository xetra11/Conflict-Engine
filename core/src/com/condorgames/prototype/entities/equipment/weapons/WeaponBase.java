package com.condorgames.prototype.entities.equipment.weapons;

import com.condorgames.prototype.listener.*;

import java.util.Objects;

public class WeaponBase implements Weapon {

  private WeaponExecutor weaponExecutor;
  private WeaponProperties weaponProperties;

  protected WeaponBase(int maxAmmo, WeaponProperties.Type type) {
    this.weaponProperties = new WeaponPropertiesBase(maxAmmo, type);
    this.weaponExecutor = new WeaponExecutorBase(weaponProperties);
  }

  public WeaponBase(WeaponProperties weaponProperties) {
    this.weaponProperties = weaponProperties;
    this.weaponExecutor = new WeaponExecutorBase(weaponProperties);
  }

  @Override
  public void fireWeapon(float deltaTime) {
    Objects.requireNonNull(weaponExecutor, "No WeaponExecuter set in " + this);
    weaponExecutor.execute(deltaTime);
  }

  public WeaponExecutor getWeaponExecutor() {
    return weaponExecutor;
  }

  @Override
  public void setWeaponFiredListener(WeaponFiredListener weaponFiredListener) {
    weaponExecutor.setWeaponFiredListener(weaponFiredListener);
  }

  @Override
  public void setWeaponReloadListener(WeaponReloadListener weaponReloadListener) {
    weaponExecutor.setWeaponReloadListener(weaponReloadListener);
  }

  @Override
  public void setWeaponReloadedListener(WeaponReloadedListener weaponReloadedListener) {
    weaponExecutor.setWeaponReloadedListener(weaponReloadedListener);
  }

  @Override
  public void setWeaponEmptyListener(WeaponEmptyListener weaponEmptyListener) {
    weaponExecutor.setWeaponEmptyListener(weaponEmptyListener);
  }

  @Override
  public void setWeaponJammedListener(WeaponJammedListener weaponJammedListener) {
    weaponExecutor.setWeaponJammedListener(weaponJammedListener);
  }
}
