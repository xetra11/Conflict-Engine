package com.condorgames.prototype.entities.soldier;

import com.condorgames.prototype.entities.equipment.weapons.interfaces.Fireable;
import com.condorgames.prototype.entities.equipment.weapons.interfaces.Weapon;
import com.condorgames.prototype.entities.soldier.SoldierProperties.Health;
import com.sun.scenario.effect.Identity;

import java.util.Random;

public class Soldier extends SoldierBase implements Fireable, Coverable {
  private Weapon weapon;
  private Cover cover;
  private int ammo;

  public Soldier(int ammo, Weapon weapon, String name) {
    super(null, name);
    this.ammo = ammo;
    this.weapon = weapon;

    weapon.setWeaponFiredListener(() -> System.out.println(this.getName() + " fired"));
    weapon.setWeaponEmptyListener(() -> System.out.println(this.getName() + " empty"));
    weapon.setWeaponReloadListener(() -> System.out.println(this.getName() + " reloading..."));
    weapon.setWeaponReloadedListener(() -> {
      this.ammo = this.weapon.reload(this.ammo);
      System.out.println(this.getName() + " reloaded");
    });
    weapon.setWeaponJammedListener(() -> System.out.println(this.getName() + " jammed"));
  }

  public void wound() {
    Random random = new Random();
    int index = random.nextInt(Health.values().length - 1);

    switch (index) {
      case 1:
        //if soldier already is lightly wounded - make him severely wounded!
        if(super.getProperties().getHealth().equals(Health.LIGHT_WOUND)){
          super.getProperties().setHealth(Health.SEVERE_WOUND);
          System.out.println(this.getName() + " already lightly wounded! -> severe wound!");
        }else{
          super.getProperties().setHealth(Health.LIGHT_WOUND);
          System.out.println(this.getName() + " lightly wounded!");
        }
        break;
      case 2:
        super.getProperties().setHealth(Health.SEVERE_WOUND);
        System.out.println(this.getName() + " severe wounded!");
        break;
      case 3:
        super.getProperties().setHealth(Health.DEAD);
        System.out.println(this.getName() + " dead wounded!");
        break;
    }
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

  @Override
  public Cover getCover() {
    return cover;
  }

  @Override
  public void setCover(Cover cover) {
    this.cover = cover;
  }

  @Override
  public String getName() {
    return super.getName();
  }

  @Override
  public float getMorale() {
    return getProperties().getMorale();
  }

  @Override
  public void setMorale(float morale) {
    getProperties().setMorale(morale);
  }
}
