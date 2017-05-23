package com.condorgames.prototype.listener;

public interface WeaponEvent {
  public void setWeaponFiredListener(WeaponFiredListener weaponFiredListener);

  public void setWeaponReloadListener(WeaponReloadListener weaponReloadListener);

  public void setWeaponReloadedListener(WeaponReloadedListener weaponReloadedListener);

  public void setWeaponEmptyListener(WeaponEmptyListener weaponEmptyListener);

  public void setWeaponJammedListener(WeaponJammedListener weaponJammedListener);
}
