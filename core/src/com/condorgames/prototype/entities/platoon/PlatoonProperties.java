package com.condorgames.prototype.entities.platoon;

import com.condorgames.prototype.entities.soldier.SoldierProperties;

public interface PlatoonProperties {
  public enum PlatoonHealth {
    DEAD,
    SEVERE_WOUND,
    LIGHT_WOUND,
    OK;
  }

  void setPlatoonHealth(PlatoonHealth soldierHealth);
  PlatoonHealth getPlatoonHealth();

}
