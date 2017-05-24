package com.condorgames.prototype.entities.equipment.weapons;

import com.condorgames.prototype.listener.WeaponEvent;

public interface Weapon extends WeaponEvent {
  void fireWeapon(float deltaTime);
}
