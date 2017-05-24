package com.condorgames.prototype.battleresolver;

public abstract class BattleParticipantBase implements BattleParticipant {
  protected MoraleState morale;

  public BattleParticipantBase(MoraleState morale) {
    this.morale = morale;
  }

  @Override
  public void setMorale(MoraleState morale) {
    this.morale = morale;
  }

  @Override
  public MoraleState getMorale() {
    return morale;
  }

  @Override
  public void decreaseMorale() {
    morale = morale.decrease();
  }

  @Override
  public void raiseMorale() {
    morale = morale.raise();
  }
}
