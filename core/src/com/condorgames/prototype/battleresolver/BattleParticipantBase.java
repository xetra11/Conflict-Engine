package com.condorgames.prototype.battleresolver;

import com.condorgames.prototype.entities.equipment.weapons.Fireable;

public abstract class BattleParticipantBase implements Fireable, Morale {
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
