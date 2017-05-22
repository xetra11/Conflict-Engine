package com.condorgames.prototype.battleresolver;

public interface BattleParticipant {
  public enum Morale {
    FANATIC(5) {
      @Override
      public Morale raise() {
        return this;
      }
    },
    HIGH(4),
    NORMAL(3),
    LOW(2),
    FLEEING(1),
    PINNED_DOWN(0) {
      @Override
      public Morale decrease() {
        return this;
      }
    };

    private final int moraleIndex;

    public int getValue() {
      return this.moraleIndex;
    }

    public Morale decrease() {
      return values()[ordinal() + 1];
    }

    public Morale raise() {
      return values()[ordinal() - 1];
    }

    Morale(int morale) {
      this.moraleIndex = morale;
    }
  }

  void setMorale(Morale morale);
  void decreaseMorale();
  void raiseMorale();
  void fire(float deltaTime);
  Morale getMorale();
}
