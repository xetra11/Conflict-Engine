package com.condorgames.prototype.entities.soldier;

import com.condorgames.prototype.entities.equipment.weapons.interfaces.Fireable;
import com.condorgames.prototype.entities.equipment.weapons.interfaces.Weapon;

public class Soldier extends SoldierBase implements Fireable {
  Weapon weapon;
  int ammo;

  public Soldier(int ammo, Weapon weapon) {
    super();
    this.ammo = ammo;
    this.weapon = weapon;

    weapon.setWeaponFiredListener(() -> System.out.println("Fired"));
    weapon.setWeaponEmptyListener(() -> System.out.println("Empty"));
    weapon.setWeaponReloadListener(() -> System.out.println("Reloading..."));
    weapon.setWeaponReloadedListener(() -> {
      this.ammo = this.weapon.reload(this.ammo);
      System.out.println("Reloaded");
    });
    weapon.setWeaponJammedListener(() -> System.out.println("Jammed"));
  }

  public int getAmmo() {
    return ammo;
  }

  public void setAmmo(int ammo) {
    this.ammo = ammo;
  }

  @Override
  public void fire(float deltaTime, HitListener hitListener) {
    weapon.fire(deltaTime, hitListener);
  }
}
