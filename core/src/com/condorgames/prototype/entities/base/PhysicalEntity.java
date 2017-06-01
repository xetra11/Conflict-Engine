package com.condorgames.prototype.entities.base;

import com.badlogic.gdx.physics.box2d.Body;
import com.condorgames.prototype.battleresolver.Morale;
import com.condorgames.prototype.entities.platoon.PlatoonBase;
import com.condorgames.prototype.entities.squad.SquadBase;

public abstract class PhysicalEntity{
  private Body body;

  public PhysicalEntity(Body body) {
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
