package com.condorgames.prototype.creator;

import com.condorgames.prototype.entities.equipment.weapons.StandardWeapon;
import com.condorgames.prototype.entities.equipment.weapons.interfaces.Weapon;
import com.condorgames.prototype.entities.equipment.weapons.interfaces.WeaponProperties;
import com.condorgames.prototype.entities.equipment.weapons.interfaces.WeaponProperties.Type;
import com.condorgames.prototype.entities.equipment.weapons.StandardWeaponProperties;

public abstract class WeaponCreator {

  public static Weapon createRifle() {
    // TODO Make a weapon immutable by its properties
    WeaponProperties weaponPropertiesImpl = new StandardWeaponProperties(5, Type.RIFLE);
    weaponPropertiesImpl.setReloadTime(10f);
    weaponPropertiesImpl.setCadence(2f);
    Weapon weapon = new StandardWeapon(weaponPropertiesImpl);
    return weapon;
  }
}
