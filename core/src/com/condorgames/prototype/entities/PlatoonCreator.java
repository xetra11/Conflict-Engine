package com.condorgames.prototype.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public abstract class PlatoonCreator {

  public static SteerablePlatoonEntity createSteerablePlatoonEntity(World world, Vector2 position){
    final Body rectangleBody = BodyCreator.createRectangleBody(0.5f, 0.25f, position, world, BodyDef.BodyType.DynamicBody);
    PolygonShape shape = new PolygonShape();
    return new SteerablePlatoonEntity(rectangleBody);
  }
}
