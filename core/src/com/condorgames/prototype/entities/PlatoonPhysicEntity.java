package com.condorgames.prototype.entities;

import com.badlogic.gdx.physics.box2d.Body;

public abstract class PlatoonPhysicEntity extends Platoon{
  private Body body;

  public PlatoonPhysicEntity(Body body) {
    this(Morale.NORMAL, body);
  }

  public PlatoonPhysicEntity(Morale morale, Body body) {
    super(morale);
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
