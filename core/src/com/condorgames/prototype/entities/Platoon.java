package com.condorgames.prototype.entities;

import com.badlogic.gdx.Gdx;

public abstract class Platoon {

  private static short platoonCount = 0;

  public enum Morale {
    FANATIC,
    HIGH,
    NORMAL,
    LOW,
    FLEEING,
    PINNED_DOWN
  }

  // TODO: assigning Fresh_Group does not work as intedend
  private static final byte RADIOMAN  = (byte) 00000001;
  private static final byte RIFLE_1   = (byte) 00000010;
  private static final byte RIFLE_2   = (byte) 00000100;
  private static final byte RIFLE_3   = (byte) 00001000;
  private static final byte RIFLE_4   = (byte) 00010000;
  private static final byte MG_1      = (byte) 00100000;
  private static final byte MG_2      = (byte) 01000000;
  private static final byte ANTI_TANK = (byte) 10000000;
  private static final byte FRESH_GROUP = (byte) 11111111;

  private short platoonID;
  private float health;
  private Morale morale;
  private byte strength;

  public Platoon(Morale morale) {
    this.morale = morale;
    health = 100;
    strength = FRESH_GROUP;
    platoonID = ++platoonCount;
    Gdx.app.log("Platoon", "created new Platoon with ID: " + platoonID);
  }

  public float getHealth() {
    return health;
  }

  public void setHealth(float health) {
    this.health = health;
  }

  public Morale getMorale() {
    return morale;
  }

  public void setMorale(Morale morale) {
    this.morale = morale;
  }

  public byte getStrength() {
    return strength;
  }

  public void setStrength(byte strength) {
    this.strength = strength;
  }
}
