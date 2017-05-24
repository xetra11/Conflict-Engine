package com.condorgames.prototype.entities;

import com.badlogic.gdx.Gdx;
import com.condorgames.prototype.battleresolver.BattleParticipantBase;

public abstract class PlatoonEntityBase extends BattleParticipantBase {
  public enum Faction {
    AXIS,
    ALLY
  }

  private static short platoonCount = 0;

  private short platoonID;
  private float health;
//  private byte strength;
  private short strength;
  private Faction faction;

  public PlatoonEntityBase(MoraleState morale, Faction faction) {
    super(morale);
    this.faction = faction;
    health = 100;
    strength = 9;
    platoonID = ++platoonCount;
    Gdx.app.log("PlatoonEntityBase", "created new PlatoonEntityBase with ID: " + platoonID + ", Faction: " + faction.name());
  }

  public short getStrength() {
    return strength;
  }

  public void setStrength(short strength) {
    this.strength = strength;
  }

  public void decreaseStrength(int amount){
    this.strength -= amount;
  }

  public short getPlatoonID() {
    return platoonID;
  }

  public Faction getFaction() {
    return faction;
  }
}
