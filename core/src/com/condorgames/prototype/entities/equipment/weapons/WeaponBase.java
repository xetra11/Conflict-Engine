package com.condorgames.prototype.entities.equipment.weapons;

public abstract class WeaponBase implements Weapon {

  private WeaponState weaponState;
  private int ammoCount;
  private final int maxAmmo;
  private static float remainingReloadTime;

  protected WeaponBase(int maxAmmo) {
    this.maxAmmo = maxAmmo;
    ammoCount = maxAmmo;
    weaponState = WeaponState.READY;
  }

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
  public void fireWeapon(float deltaTime, WeaponFiredListener weaponFiredListener) {


    if (ammoCount <= 0 && weaponState.equals(WeaponState.READY)) {
      weaponState = WeaponState.NO_AMMO;
      System.out.println("No Ammo!");
    }

    if (weaponState.equals(WeaponState.READY)) {
      ammoCount--;
      weaponFiredListener.onFire(this);
    }

    if (weaponState.equals(WeaponState.NO_AMMO)) {
      weaponState = WeaponState.RELOADING;
      remainingReloadTime = getReloadTime();
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
}
