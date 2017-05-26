package com.condorgames.prototype.entities.equipment.weapons;

import com.condorgames.prototype.listener.*;

import java.util.Objects;

public class WeaponBase implements Fireable, Reloadable, WeaponEvent {

  private FiringMechanic firingMechanic;
  private WeaponProperties weaponProperties;

  protected WeaponBase(int maxAmmo, WeaponProperties.Type type) {
    this.weaponProperties = new WeaponPropertiesImpl(maxAmmo, type);
    this.firingMechanic = new FiringMechanic(weaponProperties);
  }

  public WeaponBase(WeaponProperties weaponProperties) {
    this.weaponProperties = weaponProperties;
    this.firingMechanic = new FiringMechanic(weaponProperties);
  }

  @Override
  public void fire(float deltaTime, HitListener hitListener) {
    Objects.requireNonNull(firingMechanic, "No WeaponExecuter set in " + this);
    firingMechanic.fire(deltaTime, hitListener);
  }

  @Override
  public int reload(int amount) {
    int ammoCapacity = weaponProperties.getAmmoCapacity();
    if(amount- ammoCapacity > 0){
      weaponProperties.setAmmoCount(ammoCapacity);
      return amount - ammoCapacity;
    }else{
      weaponProperties.setAmmoCount(amount);
      return 0;
    }
  }

  @Override
  public void setWeaponFiredListener(WeaponFiredListener weaponFiredListener) {
    firingMechanic.setWeaponFiredListener(weaponFiredListener);
  }

  @Override
  public void setWeaponReloadListener(WeaponReloadListener weaponReloadListener) {
    firingMechanic.setWeaponReloadListener(weaponReloadListener);
  }

  @Override
  public void setWeaponReloadedListener(WeaponReloadedListener weaponReloadedListener) {
    firingMechanic.setWeaponReloadedListener(weaponReloadedListener);
  }

  @Override
  public void setWeaponEmptyListener(WeaponEmptyListener weaponEmptyListener) {
    firingMechanic.setWeaponEmptyListener(weaponEmptyListener);
  }

  @Override
  public void setWeaponJammedListener(WeaponJammedListener weaponJammedListener) {
    firingMechanic.setWeaponJammedListener(weaponJammedListener);
  }
}
