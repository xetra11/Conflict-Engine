package com.condorgames.prototype.entities.equipment.weapons;

import com.condorgames.prototype.battleresolver.HitMechanic;
import com.condorgames.prototype.battleresolver.HitMechanic.HitType;
import com.condorgames.prototype.listener.WeaponEvent;

public interface Fireable{
  void fire(float deltaTime, HitListener hitListener);

  @FunctionalInterface
  public interface HitListener {
    public void onHit(HitType hitType);
  }

}
