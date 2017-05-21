package com.condorgames.prototype.entities;

import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.condorgames.prototype.Helper;

public class TargetEntity implements Location<Vector2> {
  private Body body;

  private float maxLinearSpeed = 0.3f;
  private float maxLinearAcceleration = 0.3f;
  private float maxAngularSpeed = 0.5f;
  private float maxAngularAcceleration = 0.5f;


  public TargetEntity(Body body) {
    this.body = body;
  }

  public Body getBody() {
    return body;
  }

  public void setBody(Body body) {
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
        return TargetEntity.this.getPosition();
      }

      @Override
      public float getOrientation() {
        return TargetEntity.this.getOrientation();
      }

      @Override
      public void setOrientation(float orientation) {
        TargetEntity.this.setOrientation(orientation);
      }

      @Override
      public float vectorToAngle(Vector2 vector) {
        return TargetEntity.this.vectorToAngle(vector);
      }

      @Override
      public Vector2 angleToVector(Vector2 outVector, float angle) {
        return TargetEntity.this.angleToVector(outVector, angle);
      }

      @Override
      public Location<Vector2> newLocation() {
        return TargetEntity.this.newLocation();
      }
    };
  }
}
