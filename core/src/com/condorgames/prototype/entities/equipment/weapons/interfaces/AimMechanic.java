package com.condorgames.prototype.entities.equipment.weapons.interfaces;

public interface AimMechanic {
  void aim(float deltaTime, AimingListener onAimedListener);
  @FunctionalInterface
  public interface AimingListener {
    public void OnAimed();
  }
}
