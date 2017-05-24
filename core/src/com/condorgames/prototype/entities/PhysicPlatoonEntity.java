package com.condorgames.prototype.entities;

import com.badlogic.gdx.physics.box2d.Body;

public abstract class PhysicPlatoonEntity extends PlatoonEntityBase {
  private Body body;

  public PhysicPlatoonEntity(Body body) {
    this(MoraleState.NORMAL, Faction.ALLY, body);
  }

  public PhysicPlatoonEntity(Body body, Faction faction) {
    this(MoraleState.NORMAL, faction, body);
  }

  public PhysicPlatoonEntity(MoraleState morale, Faction faction, Body body) {
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
