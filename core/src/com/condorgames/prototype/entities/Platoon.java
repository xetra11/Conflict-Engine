package com.condorgames.prototype.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.condorgames.prototype.creator.WeaponCreator;
import com.condorgames.prototype.entities.equipment.weapons.Weapon;
import com.condorgames.prototype.entities.equipment.weapons.WeaponExecutorBase;

public class Platoon extends SteerablePlatoonEntity{
  private Weapon weapon;

  public Platoon(Body body, Faction faction) {
    super(body, faction);
    weapon = WeaponCreator.createRifle();
  }

  @Override
  public void fire(float deltaTime) {
    weapon.fireWeapon(deltaTime);
  }
}
