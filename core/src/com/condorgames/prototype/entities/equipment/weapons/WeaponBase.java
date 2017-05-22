package com.condorgames.prototype.entities.equipment.weapons;

public abstract class WeaponBase implements Weapon {

  private WeaponState weaponState;
  private int ammoCount;
  private final int maxAmmo;

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
  public void fireWeapon(WeaponFiredListener weaponFiredListener) {
    if(ammoCount < 0){
      weaponState = WeaponState.NO_AMMO;
      System.out.println("No Ammo!");
    }else{
      ammoCount--;
    }
    if(weaponState.equals(WeaponState.READY)){
      weaponFiredListener.onFire(this);
    }
  }
}
