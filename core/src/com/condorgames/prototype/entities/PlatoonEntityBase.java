package com.condorgames.prototype.entities;

import com.badlogic.gdx.Gdx;
import com.condorgames.prototype.battleresolver.AbstractBattleParticipant;
import com.condorgames.prototype.entities.equipment.weapons.Weapon;

public abstract class PlatoonEntityBase extends AbstractBattleParticipant {

  public enum Faction {
    AXIS,
    ALLY
  }

  private static short platoonCount = 0;

//  // TODO: assigning Fresh_Group does not work as intedend
//  private static final byte RADIOMAN  = (byte) 00000001;
//  private static final byte RIFLE_1   = (byte) 00000010;
//  private static final byte RIFLE_2   = (byte) 00000100;
//  private static final byte RIFLE_3   = (byte) 00001000;
//  private static final byte RIFLE_4   = (byte) 00010000;
//  private static final byte MG_1      = (byte) 00100000;
//  private static final byte MG_2      = (byte) 01000000;
//  private static final byte ANTI_TANK = (byte) 10000000;
//  private static final byte FRESH_GROUP = (byte) 11111111;

  private short platoonID;
  private float health;
//  private byte strength;
  private short strength;
  private Faction faction;

  public PlatoonEntityBase(Morale morale, Faction faction) {
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
