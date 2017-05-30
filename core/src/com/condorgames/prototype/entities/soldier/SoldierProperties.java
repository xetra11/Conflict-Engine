package com.condorgames.prototype.entities.soldier;

import com.condorgames.prototype.battleresolver.Morale;

public interface SoldierProperties extends Morale, Identity {

  public enum Health {
    DEAD,
    SEVERE_WOUND,
    LIGHT_WOUND,
    OK;
  }

  void setHealth(Health health);
  Health getHealth();

}
