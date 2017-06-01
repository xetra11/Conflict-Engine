package com.condorgames.prototype.creator;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.condorgames.prototype.helper.Helper;

import static com.condorgames.prototype.helper.FilterCategories.*;

public abstract class BodyCreator {

  public static Body createRectangleBody(World world, BodyDef.BodyType type) {
    return BodyCreator.createRectangleBody(2f, 2f, new Vector2(0f, 0f), world, type, COMMON_BODIES, COMMON_BODIES);
  }

  public static Body createRectangleBody(Vector2 position, World world, BodyDef.BodyType type) {
    return BodyCreator.createRectangleBody(2f, 2f, position, world, type, COMMON_BODIES, COMMON_BODIES);
  }

  public static Body createRectangleBody(float width, float height, World world, BodyDef.BodyType type) {
    return BodyCreator.createRectangleBody(width, height, new Vector2(0f, 0f), world, type, COMMON_BODIES, COMMON_BODIES);
  }

  public static Body createCircleBody(World world, BodyDef.BodyType type){
    return BodyCreator.createCircleBody(0.5f, new Vector2(0f, 0f), world, type, COMMON_BODIES, COMMON_BODIES);
  }

  public static Body createCircleBody(Vector2 position, World world, BodyDef.BodyType type){
    return BodyCreator.createCircleBody(0.5f, position, world, type, COMMON_BODIES, COMMON_BODIES);
  }

  public static Body createCircleBody(float radius, World world, BodyDef.BodyType type){
    return BodyCreator.createCircleBody(radius, new Vector2(0f, 0f), world, type, COMMON_BODIES, COMMON_BODIES);
  }

  public static Body createCircleBody(float radius, Vector2 position, World world, BodyDef.BodyType type){
    return BodyCreator.createCircleBody(radius, position, world, type, COMMON_BODIES, COMMON_BODIES);
  }

  public static Body createRectangleBody(float width, float height, Vector2 position, World world, BodyDef.BodyType type, short filterCategory, short filterMask) {
    PolygonShape shape = new PolygonShape();
    float boxHalfWidth = width / 2;
    float boxHalfHeight = height / 2;
    shape.setAsBox(boxHalfWidth, boxHalfHeight);
    return createBody(shape, position, world, type, filterCategory, filterMask);
  }

  public static Body createCircleBody(float radius, Vector2 position, World world, BodyDef.BodyType type, short filterCategory, short filterMask){
    CircleShape shape = new CircleShape();
    shape.setRadius(radius);
    return createBody(shape, position, world, type, filterCategory, filterMask);
  }

  private static Body createBody(Shape shape, Vector2 position, World world, BodyDef.BodyType type, short filterCategory, short filterMask){
    BodyDef bodyDef = new BodyDef();
    FixtureDef fixtureDef = new FixtureDef();

    bodyDef.position.set(Helper.getConvertedScene2DToBox2DPosition(position));
    bodyDef.type = type;

    fixtureDef.density = 1.0f;
    fixtureDef.friction = 1.0f;
    fixtureDef.shape = shape;
    fixtureDef.filter.categoryBits = filterCategory;
    fixtureDef.filter.maskBits = filterMask;

    Body body = world.createBody(bodyDef);
    body.createFixture(fixtureDef);

    return body;
  }
}
