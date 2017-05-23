package com.condorgames.prototype.entities.equipment.weapons;

import java.util.Objects;

public class WeaponBase implements Weapon {

  private WeaponExecutor weaponExecutor;
  private WeaponProperties weaponProperties;

  protected WeaponBase(int maxAmmo, WeaponProperties.Type type) {
    this.weaponProperties = new WeaponPropertiesBase(maxAmmo, type);
    this.weaponExecutor = new WeaponExecutorBase(weaponProperties);
  }

  public WeaponBase(WeaponProperties weaponProperties){
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
}
