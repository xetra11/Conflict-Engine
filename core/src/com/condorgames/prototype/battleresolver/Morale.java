package com.condorgames.prototype.battleresolver;

public interface Morale {
  enum MoraleState {
    FANATIC(5) {
      @Override
      public MoraleState raise() {
        return this;
      }
    },
    HIGH(4),
    NORMAL(3),
    LOW(2),
    FLEEING(1),
    PINNED_DOWN(0) {
      @Override
      public MoraleState decrease() {
        return this;
      }
    };

    private final int moraleIndex;

    MoraleState(int morale) {
      this.moraleIndex = morale;
    }

    public int getValue() {
      return this.moraleIndex;
    }

    public MoraleState decrease() {
      return values()[ordinal() + 1];
    }

    public MoraleState raise() {
      return values()[ordinal() - 1];
    }
  }

  void setMorale(MoraleState morale);

  void decreaseMorale();

  void raiseMorale();

  MoraleState getMorale();

}
