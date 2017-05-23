package com.condorgames.prototype.entities;

import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.condorgames.prototype.Helper;

public class Sensor implements Location<Vector2> {
  private Body body;

  private float maxLinearSpeed = 0.3f;
  private float maxLinearAcceleration = 0.3f;
  private float maxAngularSpeed = 0.5f;
  private float maxAngularAcceleration = 0.5f;


  public Sensor(Body body) {
    this.body = body;
    body.getFixtureList().first().setSensor(true);
  }

  public Body getBody() {
    return body;
  }

  public void setBody(Body body) {
    body.getFixtureList().first().setSensor(true);
    this.body = body;
  }


  @Override
  public Vector2 getPosition() {
    return getBody().getPosition();
  }

  @Override
  public float getOrientation() {
    return getBody().getAngle();
  }

  @Override
  public void setOrientation(float orientation) {
    getBody().setTransform(getPosition(), orientation);
  }

  @Override
  public float vectorToAngle(Vector2 vector) {
    return Helper.vectorToAngle(vector);
  }

  @Override
  public Vector2 angleToVector(Vector2 outVector, float angle) {
    return Helper.angleToVector(outVector, angle);
  }

  @Override
  public Location<Vector2> newLocation() {
    return new Location<Vector2>() {
      @Override
      public Vector2 getPosition() {
        return Sensor.this.getPosition();
      }

      @Override
      public float getOrientation() {
        return Sensor.this.getOrientation();
      }

      @Override
      public void setOrientation(float orientation) {
        Sensor.this.setOrientation(orientation);
      }

      @Override
      public float vectorToAngle(Vector2 vector) {
        return Sensor.this.vectorToAngle(vector);
      }

      @Override
      public Vector2 angleToVector(Vector2 outVector, float angle) {
        return Sensor.this.angleToVector(outVector, angle);
      }

      @Override
      public Location<Vector2> newLocation() {
        return Sensor.this.newLocation();
      }
    };
  }
}
