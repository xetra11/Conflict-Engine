package com.condorgames.prototype;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public abstract class BodyFactory {

  public static Body createRectangleBody(World world, BodyDef.BodyType type) {
    return BodyFactory.createRectangleBody(2f, 2f, new Vector2(0f, 0f), world, type);
  }

  public static Body createRectangleBody(float width, float height, World world, BodyDef.BodyType type) {
    return BodyFactory.createRectangleBody(width, height, new Vector2(0f, 0f), world, type);
  }

  public static Body createRectangleBody(Vector2 position, World world, BodyDef.BodyType type) {
    return BodyFactory.createRectangleBody(2f, 2f, position, world, type);
  }

  public static Body createRectangleBody(float width, float height, Vector2 position, World world, BodyDef.BodyType type) {
    PolygonShape shape = new PolygonShape();
    float boxHalfWidth = width / 2;
    float boxHalfHeight = height / 2;
    shape.setAsBox(boxHalfWidth, boxHalfHeight);
    return createBody(shape, position, world, type);
  }

  public static Body createCircleBody(float radius, Vector2 position, World world, BodyDef.BodyType type){
    CircleShape shape = new CircleShape();
    shape.setRadius(radius);
    return createBody(shape, position, world, type);
  }

  private static Body createBody(Shape shape, Vector2 position, World world, BodyDef.BodyType type){
    BodyDef bodyDef = new BodyDef();
    FixtureDef fixtureDef = new FixtureDef();

    bodyDef.position.set(Helper.getMappedScene2DToBox2DPosition(position));
    bodyDef.type = type;

    fixtureDef.density = 1.0f;
    fixtureDef.friction = 1.0f;
    fixtureDef.shape = shape;

    Body body = world.createBody(bodyDef);
    body.createFixture(fixtureDef);

    return body;
  }
}
