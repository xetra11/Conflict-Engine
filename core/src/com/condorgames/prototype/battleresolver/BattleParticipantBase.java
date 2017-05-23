package com.condorgames.prototype.battleresolver;

import com.condorgames.prototype.entities.equipment.weapons.Weapon;

public abstract class BattleParticipantBase implements BattleParticipant {
  protected Morale morale;

  public BattleParticipantBase(Morale morale) {
    this.morale = morale;
  }

  @Override
  public void setMorale(Morale morale) {
    this.morale = morale;
  }

  @Override
  public Morale getMorale() {
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
