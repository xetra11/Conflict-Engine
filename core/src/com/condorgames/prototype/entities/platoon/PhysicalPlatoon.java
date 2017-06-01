package com.condorgames.prototype.entities.platoon;

import com.badlogic.gdx.physics.box2d.Body;
import com.condorgames.prototype.battleresolver.Morale;
import com.condorgames.prototype.entities.squad.SquadBase;

public abstract class PhysicalPlatoon extends PlatoonBase{
  private Body body;

  public PhysicalPlatoon(Body body) {
    this(Morale.MoraleBase.NORMAL, SquadBase.Faction.ALLY, body);
  }

  public PhysicalPlatoon(Body body, SquadBase.Faction faction) {
    this(Morale.MoraleBase.NORMAL, faction, body);
  }

  public PhysicalPlatoon(Morale.MoraleBase morale, SquadBase.Faction faction, Body body) {
    //TODO Super CTOR add
//    super(faction);
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
