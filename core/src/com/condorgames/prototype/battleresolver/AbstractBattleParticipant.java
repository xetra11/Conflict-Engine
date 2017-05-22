package com.condorgames.prototype.battleresolver;

public abstract class AbstractBattleParticipant implements BattleParticipant {
  protected Morale morale;

  public AbstractBattleParticipant(Morale morale) {
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
