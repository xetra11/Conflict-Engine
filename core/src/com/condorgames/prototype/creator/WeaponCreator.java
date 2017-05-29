package com.condorgames.prototype.creator;

import com.condorgames.prototype.entities.equipment.weapons.StandardWeapon;
import com.condorgames.prototype.entities.equipment.weapons.interfaces.Weapon;
import com.condorgames.prototype.entities.equipment.weapons.interfaces.WeaponProperties;
import com.condorgames.prototype.entities.equipment.weapons.interfaces.WeaponProperties.Type;
import com.condorgames.prototype.entities.equipment.weapons.StandardWeaponProperties;

public abstract class WeaponCreator {

  public static Weapon createRifle() {
    WeaponProperties weaponPropertiesImpl = new StandardWeaponProperties(5, Type.RIFLE);
    weaponPropertiesImpl.setReloadTime(10f);
    weaponPropertiesImpl.setCadence(2f);
    return new StandardWeapon(weaponPropertiesImpl);
  }
}
