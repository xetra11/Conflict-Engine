package com.condorgames.prototype.entities;

import com.condorgames.prototype.battleresolver.Morale;

public interface SoldierProperties extends Morale{

  public enum Health{
    OK,
    LIGHT_WOUND,
    SEVERE_WOUND,
    DEAD
  }

  void setHealth(Health health);
  Health getHealth();

}
