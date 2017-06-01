package com.condorgames.prototype.entities.platoon;

import com.badlogic.gdx.physics.box2d.Body;
import com.condorgames.prototype.entities.base.SteerableEntity;

public abstract class PlatoonBase extends SteerableEntity implements PlatoonProperties {

  private PlatoonHealth platoonHealth;

  public PlatoonBase(Body body) {
    super(body);
    body.setUserData(this);
    platoonHealth = PlatoonHealth.COMBAT_READY;
  }

  @Override
  public void setPlatoonHealth(PlatoonHealth platoonHealth) {
    this.platoonHealth = platoonHealth;
  }

  @Override
  public PlatoonHealth getPlatoonHealth() {
    return platoonHealth;
  }
}
