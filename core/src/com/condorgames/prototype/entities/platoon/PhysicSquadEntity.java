package com.condorgames.prototype.entities.platoon;

import com.badlogic.gdx.physics.box2d.Body;

public abstract class PhysicSquadEntity extends SquadEntityBase {
  private Body body;

  public PhysicSquadEntity(Body body) {
    this(MoraleBase.NORMAL, Faction.ALLY, body);
  }

  public PhysicSquadEntity(Body body, Faction faction) {
    this(MoraleBase.NORMAL, faction, body);
  }

  public PhysicSquadEntity(MoraleBase morale, Faction faction, Body body) {
    super(faction);
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
