package com.condorgames.prototype.entities;

import com.badlogic.gdx.Gdx;
import com.condorgames.prototype.battleresolver.Morale;
import com.condorgames.prototype.entities.equipment.weapons.Fireable;

public abstract class PlatoonEntityBase implements Fireable, Morale {
  public enum Faction {
    AXIS,
    ALLY
  }

  private static short platoonCount = 0;

  private short platoonID;
  private Faction faction;

  public PlatoonEntityBase(Faction faction) {
    this.faction = faction;
    platoonID = ++platoonCount;
    Gdx.app.log("PlatoonEntityBase", "created new PlatoonEntityBase with ID: " + platoonID + ", Faction: " + faction.name());
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
