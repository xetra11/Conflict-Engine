package com.condorgames.prototype.creator;

import com.condorgames.prototype.entities.equipment.weapons.WeaponBase;
import com.condorgames.prototype.entities.equipment.weapons.WeaponProperties;
import com.condorgames.prototype.entities.equipment.weapons.WeaponPropertiesImpl;

public abstract class WeaponCreator {

  public static WeaponBase createRifle() {
    WeaponPropertiesImpl weaponPropertiesImpl = new WeaponPropertiesImpl(5, WeaponProperties.Type.RIFLE);
    weaponPropertiesImpl.setReloadTime(10f);
    weaponPropertiesImpl.setCadence(2f);
    WeaponBase weaponBase = new WeaponBase(weaponPropertiesImpl);
    return weaponBase;
  }
}
