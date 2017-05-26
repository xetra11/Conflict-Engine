package com.condorgames.prototype.entities;

import com.condorgames.prototype.battleresolver.Morale;

public interface SoldierProperties extends Morale{

  public enum Health{
    OK{
      @Override
      public Health raise() {
        return this;
      }
    },
    LIGHT_WOUND,
    SEVERE_WOUND,
    DEAD{
      @Override
      public Health decrease() {
        return this;
      }
    };

    public Health decrease() {
      return values()[ordinal() + 1];
    }

    public Health raise() {
      return values()[ordinal() - 1];
    }
  }

  void setHealth(Health health);
  Health getHealth();

}
