package com.condorgames.prototype.entities;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.condorgames.prototype.Helper;
import com.condorgames.prototype.entities.PlatoonPhysicEntity;

public class AutonomousPlatoonEntity extends PlatoonPhysicEntity implements Steerable<Vector2> {

  private SteeringBehavior steeringBehavior;
  private SteeringAcceleration<Vector2> steeringAcceleration;

  private float maxLinearSpeed = 10f;
  private float maxLinearAcceleration = 10f;
  private float maxAngularSpeed = 10f;
  private float maxAngularAcceleration = 10f;

  public AutonomousPlatoonEntity(Body body) {
    super(body);
    steeringAcceleration = new SteeringAcceleration<Vector2>(new Vector2());
  }

  //region Steerable Interface Implementation
  @Override
  public Vector2 getLinearVelocity() {
    return getBody().getLinearVelocity();
  }

  @Override
  public float getAngularVelocity() {
    return getBody().getAngularVelocity();
  }

  @Override
  public float getBoundingRadius() {
    return getBody().getFixtureList().first().getShape().getRadius();
  }

  @Override
  public boolean isTagged() {
    return false;
  }

  @Override
  public void setTagged(boolean tagged) {

  }

  @Override
  public float getZeroLinearSpeedThreshold() {
    return 0;
  }

  @Override
  public void setZeroLinearSpeedThreshold(float value) {

  }

  @Override
  public float getMaxLinearSpeed() {
    return maxLinearSpeed;
  }

  @Override
  public void setMaxLinearSpeed(float maxLinearSpeed) {
    this.maxLinearSpeed = maxLinearSpeed;
  }

  @Override
  public float getMaxLinearAcceleration() {
    return maxLinearAcceleration;
  }

  @Override
  public void setMaxLinearAcceleration(float maxLinearAcceleration) {
    this.maxLinearAcceleration = maxLinearAcceleration;
  }

  @Override
  public float getMaxAngularSpeed() {
    return maxAngularSpeed;
  }

  @Override
  public void setMaxAngularSpeed(float maxAngularSpeed) {
    this.maxAngularSpeed = maxAngularSpeed;
  }

  @Override
  public float getMaxAngularAcceleration() {
    return maxAngularAcceleration;
  }

  @Override
  public void setMaxAngularAcceleration(float maxAngularAcceleration) {
    this.maxAngularAcceleration = maxAngularAcceleration;
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
        return AutonomousPlatoonEntity.this.getPosition();
      }

      @Override
      public float getOrientation() {
        return AutonomousPlatoonEntity.this.getOrientation();
      }

      @Override
      public void setOrientation(float orientation) {
        AutonomousPlatoonEntity.this.setOrientation(orientation);
      }

      @Override
      public float vectorToAngle(Vector2 vector) {
        return AutonomousPlatoonEntity.this.vectorToAngle(vector);
      }

      @Override
      public Vector2 angleToVector(Vector2 outVector, float angle) {
        return AutonomousPlatoonEntity.this.angleToVector(outVector, angle);
      }

      @Override
      public Location<Vector2> newLocation() {
        return AutonomousPlatoonEntity.this.newLocation();
      }
    };
  }
  //endregion
}
