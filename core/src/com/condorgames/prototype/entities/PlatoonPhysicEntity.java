package com.condorgames.prototype.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.condorgames.prototype.entities.equipment.weapons.Weapon;

public abstract class PlatoonPhysicEntity extends Platoon{
  private Body body;

  public PlatoonPhysicEntity(Body body) {
    this(Morale.NORMAL, Faction.ALLY, body);
  }

  public PlatoonPhysicEntity(Body body, Faction faction) {
    this(Morale.NORMAL, faction, body);
  }

  public PlatoonPhysicEntity(Morale morale, Faction faction, Body body) {
    super(morale, faction);
    body.setUserData(this);
    this.body = body;
  }

  public Body getBody() {
    return body;
  }

  public void setBody(Body body) {
    this.body = body;
  }
}
