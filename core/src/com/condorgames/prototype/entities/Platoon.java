package com.condorgames.prototype.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.condorgames.prototype.audio.AudioManager;
import com.condorgames.prototype.creator.WeaponCreator;
import com.condorgames.prototype.entities.equipment.weapons.WeaponBase;

public class Platoon extends SteerablePlatoonEntity {
  private WeaponBase weapon;
  private int rifleAmmo;

  public Platoon(Body body, Faction faction) {
    super(body, faction);
    weapon = WeaponCreator.createRifle();
    rifleAmmo = 60 * getStrength();

    //Listener
    weapon.setWeaponFiredListener(() -> System.out.println(faction + " fired!"));
    weapon.setWeaponReloadedListener(() -> {
      System.out.println(faction + " reloaded!");
      // passes the whole ammo to the weapon and if a rest exists it will be reassigned as the current ammoCount
      rifleAmmo = weapon.reload(rifleAmmo);
    });
    weapon.setWeaponReloadListener(() -> {
      System.out.println(faction + " reloading...");
      AudioManager.playReloading2WithBackground();
    });
    weapon.setWeaponEmptyListener(() -> System.out.println(faction + " Empty Ammo!"));
    weapon.setWeaponJammedListener(() -> System.out.println(faction + " Jammed!"));
  }

  @Override
  public void fire(float deltaTime, HitListener hitListener) {
    weapon.fire(deltaTime, hitListener);
  }
}
