package com.condorgames.prototype.creator;

import com.condorgames.prototype.entities.equipment.weapons.Weapon;
import com.condorgames.prototype.entities.equipment.weapons.WeaponBase;
import com.condorgames.prototype.entities.equipment.weapons.WeaponProperties;
import com.condorgames.prototype.entities.equipment.weapons.WeaponPropertiesBase;

public abstract class WeaponCreator {

  public static Weapon createRifle() {
    WeaponPropertiesBase weaponPropertiesBase = new WeaponPropertiesBase(2, WeaponProperties.Type.RIFLE);
    weaponPropertiesBase.setReloadTime(6f);
    weaponPropertiesBase.setCadence(2f);
    WeaponBase weaponBase = new WeaponBase(weaponPropertiesBase);
    return weaponBase;
  }
}
