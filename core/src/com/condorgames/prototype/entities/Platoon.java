package com.condorgames.prototype.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.condorgames.prototype.audio.AudioManager;
import com.condorgames.prototype.creator.WeaponCreator;
import com.condorgames.prototype.entities.equipment.weapons.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Platoon extends SteerablePlatoonEntity {
  private Weapon weapon;
  private List<Soldier> soldiers = new ArrayList<>(9);

  public Platoon(Body body, Faction faction) {
    super(body, faction);
    weapon = WeaponCreator.createRifle();

    //Soldiers
    soldiers.add(new Soldier(60, WeaponCreator.createRifle()));
    soldiers.add(new Soldier(60, WeaponCreator.createRifle()));
    soldiers.add(new Soldier(60, WeaponCreator.createRifle()));
    soldiers.add(new Soldier(60, WeaponCreator.createRifle()));
    soldiers.add(new Soldier(60, WeaponCreator.createRifle()));
    soldiers.add(new Soldier(60, WeaponCreator.createRifle()));
    soldiers.add(new Soldier(60, WeaponCreator.createRifle()));
    soldiers.add(new Soldier(60, WeaponCreator.createRifle()));
    soldiers.add(new Soldier(60, WeaponCreator.createRifle()));

//    //Listener
//    weapon.setWeaponFiredListener(() -> System.out.println(faction + " fired!"));
//    weapon.setWeaponReloadedListener(() -> {
//      System.out.println(faction + " reloaded!");
//      // passes the whole ammo to the weapon and if a rest exists it will be reassigned as the current ammoCount
//      setAmmo(weapon.reload(getAmmo()));
//    });
//    weapon.setWeaponReloadListener(() -> {
//      System.out.println(faction + " reloading...");
//      AudioManager.playReloading2WithBackground();
//    });
//    weapon.setWeaponEmptyListener(() -> System.out.println(faction + " Empty Ammo!"));
//    weapon.setWeaponJammedListener(() -> System.out.println(faction + " Jammed!"));
  }

  @Override
  public void fire(float deltaTime, HitListener hitListener) {
    soldiers.forEach(soldier -> soldier.fire(deltaTime, hitListener));
  }
}
