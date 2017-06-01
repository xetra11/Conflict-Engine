package com.condorgames.prototype.entities.squad;

import com.badlogic.gdx.Gdx;
import com.condorgames.prototype.battleresolver.Morale;
import com.condorgames.prototype.entities.equipment.weapons.interfaces.Fireable;

public abstract class SquadBase implements Fireable, Morale {
  private static short platoonCount = 0;
  public enum Faction {
    AXIS,
    ALLY

  }

  private short platoonID;
  private Faction faction;

  public SquadBase(Faction faction) {
    this.faction = faction;
    platoonID = ++platoonCount;
    Gdx.app.log("SquadBase", "created new SquadBase with ID: " + platoonID + ", Faction: " + faction.name());
  }

  public short getPlatoonID() {
    return platoonID;
  }

  public Faction getFaction() {
    return faction;
  }

  public abstract int getAmmo();

  public abstract int getStrength();

  public abstract void takeCasualty();
}
