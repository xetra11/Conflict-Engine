package com.condorgames.prototype.entities.equipment.weapons;

import com.condorgames.prototype.listener.WeaponEvent;

public interface WeaponExecutor extends WeaponEvent {
  void execute(float deltaTime);
}
