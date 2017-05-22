package com.condorgames.prototype.entities.equipment.weapons;

@FunctionalInterface
public interface WeaponFiredListener {
  void onFire(Weapon weapon);
}