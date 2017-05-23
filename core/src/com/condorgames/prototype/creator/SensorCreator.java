package com.condorgames.prototype.creator;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.condorgames.prototype.creator.BodyCreator;
import com.condorgames.prototype.entities.SensorEntity;

public abstract class SensorCreator {

  public static SensorEntity createTargetCircleEntity(World world, float radius){
    final Body circleBody = BodyCreator.createCircleBody(radius, new Vector2(1f,1f), world, BodyDef.BodyType.StaticBody);
    return new SensorEntity(circleBody);
  }

}
