package com.condorgames.prototype.entities.squad;

import com.badlogic.gdx.physics.box2d.Body;

public abstract class PhysicalSquad extends SquadBase {
  private Body body;

  public PhysicalSquad(Body body) {
    this(MoraleBase.NORMAL, Faction.ALLY, body);
  }

  public PhysicalSquad(Body body, Faction faction) {
    this(MoraleBase.NORMAL, faction, body);
  }

  public PhysicalSquad(MoraleBase morale, Faction faction, Body body) {
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
