package com.condorgames.prototype.entities.equipment.weapons;

import com.condorgames.prototype.battleresolver.HitMechanic.HitType;

public interface Fireable{
  void fire(float deltaTime, HitListener hitListener);

  @FunctionalInterface
  public interface HitListener {
    public void onHit(HitType hitType);
  }

}
