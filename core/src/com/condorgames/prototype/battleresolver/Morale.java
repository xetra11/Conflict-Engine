package com.condorgames.prototype.battleresolver;

public interface Morale {
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
