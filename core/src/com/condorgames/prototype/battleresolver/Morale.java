package com.condorgames.prototype.battleresolver;

public interface Morale {
  //TODO: Remove since new morale system
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

  public enum MoraleBase {
    FANATIC(0.5f),
    HIGH(0.25f),
    NORMAL(0),
    LOW(-1);

    private final float moraleIndex;

    MoraleBase(float morale) {
      this.moraleIndex = morale;
    }

    public float getValue() {
      return this.moraleIndex;
    }

  }

  void setMoraleBase(MoraleBase morale);

  void decreaseMorale();

  void raiseMorale();

  MoraleBase getMoraleBase();

  float getMorale();

  void setMorale(float morale);
}
