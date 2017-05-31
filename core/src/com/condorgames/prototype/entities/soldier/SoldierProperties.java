package com.condorgames.prototype.entities.soldier;

import com.condorgames.prototype.battleresolver.Morale;

public interface SoldierProperties extends Morale, Identity {

  public enum SoldierHealth {
    DEAD,
    SEVERE_WOUND,
    LIGHT_WOUND,
    OK;
  }

  void setSoldierHealth(SoldierHealth soldierHealth);
  SoldierHealth getSoldierHealth();

}
