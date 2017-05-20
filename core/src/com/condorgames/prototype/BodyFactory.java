package com.condorgames.prototype;

import com.badlogic.gdx.physics.box2d.*;

public abstract class BodyFactory {

  public static Body createRectangleBody(World world, BodyDef.BodyType type){
    BodyDef bodyDef = new BodyDef();
    FixtureDef fixtureDef = new FixtureDef();
    PolygonShape shape = new PolygonShape();

    bodyDef.position.set(0f,0f);
    bodyDef.type = type;

    shape.setAsBox(1f, 1f);

    fixtureDef.density = 1.0f;
    fixtureDef.friction = 1.0f;
    fixtureDef.shape = shape;

    Body body = world.createBody(bodyDef);
    body.createFixture(fixtureDef);

    return body;
  }
}
