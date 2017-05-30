package com.condorgames.prototype.entities.soldier;

import com.condorgames.prototype.entities.equipment.weapons.interfaces.Fireable;
import com.condorgames.prototype.entities.equipment.weapons.interfaces.Weapon;
import com.condorgames.prototype.entities.soldier.SoldierProperties.Health;

import java.util.Random;

public class Soldier extends SoldierBase implements Fireable, Coverable {
  private Weapon weapon;
  private Cover cover;
  private int ammo;

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

  public void wound() {
    Random random = new Random();
    int index = random.nextInt(Health.values().length - 1);

    switch (index) {
      case 1:
        //if soldier already is lightly wounded - make him severely wounded!
        if(super.getProperties().getHealth().equals(Health.LIGHT_WOUND)){
          super.getProperties().setHealth(Health.SEVERE_WOUND);
          System.out.println("already lightly wounded! -> severe wound!");
        }else{
          super.getProperties().setHealth(Health.LIGHT_WOUND);
          System.out.println("lightly wounded!");
        }
        break;
      case 2:
        super.getProperties().setHealth(Health.SEVERE_WOUND);
        System.out.println("severe wounded!");
        break;
      case 3:
        super.getProperties().setHealth(Health.DEAD);
        System.out.println("dead wounded!");
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
}
