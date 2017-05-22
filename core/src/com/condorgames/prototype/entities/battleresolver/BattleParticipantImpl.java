package com.condorgames.prototype.entities.battleresolver;

public abstract class BattleParticipantImpl implements BattleParticipant {
  protected Morale morale;

  public BattleParticipantImpl(Morale morale) {
    this.morale = morale;
  }

  @Override
  public abstract void setMorale(Morale morale);

  @Override
  public abstract Morale getMorale();

  @Override
  public abstract void setHealth(float health);

  @Override
  public abstract float getHealth();
}
