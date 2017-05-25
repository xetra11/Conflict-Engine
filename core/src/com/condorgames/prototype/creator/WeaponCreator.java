package com.condorgames.prototype.creator;

import com.condorgames.prototype.entities.equipment.weapons.Fireable;
import com.condorgames.prototype.entities.equipment.weapons.WeaponBase;
import com.condorgames.prototype.entities.equipment.weapons.WeaponProperties;
import com.condorgames.prototype.entities.equipment.weapons.WeaponPropertiesBase;

public abstract class WeaponCreator {

  public static Fireable createRifle() {
    WeaponPropertiesBase weaponPropertiesBase = new WeaponPropertiesBase(5, WeaponProperties.Type.RIFLE);
    weaponPropertiesBase.setReloadTime(10f);
    weaponPropertiesBase.setCadence(2f);
    WeaponBase weaponBase = new WeaponBase(weaponPropertiesBase);
    return weaponBase;
  }
}
