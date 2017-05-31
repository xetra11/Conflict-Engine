package com.condorgames.prototype.entities.platoon;

public class PlatoonBase implements PlatoonProperties {

  private PlatoonHealth platoonHealth;

  public PlatoonBase() {
    platoonHealth = PlatoonHealth.COMBAT_READY;
  }

  @Override
  public void setPlatoonHealth(PlatoonHealth platoonHealth) {
    this.platoonHealth = platoonHealth;
  }

  @Override
  public PlatoonHealth getPlatoonHealth() {
    return platoonHealth;
  }
}
