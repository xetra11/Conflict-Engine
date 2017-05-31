package com.condorgames.prototype.entities.platoon;

import com.condorgames.prototype.entities.soldier.SoldierProperties;

public interface PlatoonProperties {
  public enum PlatoonHealth {
    SCATTERED,
    PINNED_DOWN,
    STRESSED,
    COMBAT_READY;
  }

  void setPlatoonHealth(PlatoonHealth soldierHealth);
  PlatoonHealth getPlatoonHealth();

}
