package com.condorgames.prototype.entities.battleresolver;

public interface BattleParticipant {
  public enum Morale {
    FANATIC,
    HIGH,
    NORMAL,
    LOW,
    FLEEING,
    PINNED_DOWN
  }

  void setMorale(Morale morale);
  Morale getMorale();
  void setHealth(float health);
  float getHealth();

}
