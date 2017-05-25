package com.condorgames.prototype.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.condorgames.prototype.battleresolver.AimMechanic;
import com.condorgames.prototype.creator.WeaponCreator;
import com.condorgames.prototype.entities.equipment.weapons.Fireable;
import com.condorgames.prototype.entities.equipment.weapons.WeaponBase;
import com.condorgames.prototype.entities.equipment.weapons.WeaponExecutorBase;

public class Platoon extends SteerablePlatoonEntity {
  private WeaponBase weapon;

  public Platoon(Body body, Faction faction) {
    super(body, faction);
    weapon = WeaponCreator.createRifle();
    weapon.setWeaponFiredListener(() -> System.out.println(faction + " fired!"));
    weapon.setWeaponReloadedListener(() -> System.out.println(faction + " reloaded!"));
    weapon.setWeaponReloadListener(() -> System.out.println(faction + " reloading..."));
    weapon.setWeaponEmptyListener(() -> System.out.println(faction + " Empty Ammo!"));
    weapon.setWeaponJammedListener(() -> System.out.println(faction + " Jammed!"));
  }

  @Override
  public void fire(float deltaTime, HitListener hitListener) {
    weapon.fire(deltaTime, hitListener);
  }
}
