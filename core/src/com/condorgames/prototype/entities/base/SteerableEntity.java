package com.condorgames.prototype.entities.base;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.condorgames.prototype.helper.Helper;

public abstract class SteerableEntity extends PhysicalEntity implements Steerable<Vector2> {
  private SteeringBehavior steeringBehavior;
  private SteeringAcceleration<Vector2> steeringOutput;

  private float maxLinearSpeed = 1f;
  private float maxLinearAcceleration = 1f;
  private float maxAngularSpeed = 0.5f;
  private float maxAngularAcceleration = 0.5f;
  private boolean tagged;
  //TODO CTOR param
  private boolean independentFacing = false;

  //TODO add speech library?

  public SteerableEntity(Body body) {
    super(body);
    steeringOutput = new SteeringAcceleration<Vector2>(new Vector2());
  }

  public void setSteeringBehavior(SteeringBehavior steeringBehavior) {
    this.steeringBehavior = steeringBehavior;
  }

  public void applySteering(float deltaTime) {
    boolean anyAccelerations = false;

    steeringBehavior.calculateSteering(steeringOutput);
    if (isTagged() == false) {
// Update position and linear velocity.
      if (!steeringOutput.linear.isZero()) {
        // this method internally scales the force by deltaTime
        getBody().applyForceToCenter(steeringOutput.linear, true);
        anyAccelerations = true;
      }

      // Update orientation and angular velocity
      if (isIndependentFacing()) {
        if (steeringOutput.angular != 0) {
          // this method internally scales the torque by deltaTime
          getBody().applyTorque(steeringOutput.angular, true);
          anyAccelerations = true;
        }
      } else {
        // If we haven't got any velocity, then we can do nothing.
        Vector2 linVel = getLinearVelocity();
        if (!linVel.isZero(getZeroLinearSpeedThreshold())) {
          float newOrientation = vectorToAngle(linVel);
          getBody().setAngularVelocity((newOrientation - getAngularVelocity()) * deltaTime); // this is superfluous if independentFacing is always true
          getBody().setTransform(getBody().getPosition(), newOrientation);
        }
      }
    }
  }

  public void update(float deltaTime) {
    applySteering(deltaTime);
  }


  @Override
  public Vector2 getLinearVelocity() {
    return getBody().getLinearVelocity();
  }

  //<editor-fold desc="Interface Implementations">
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
    return this.tagged;
  }

  @Override
  public void setTagged(boolean tagged) {
    this.tagged = tagged;
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
    return new Scene2dLocation() ;
  }

  public boolean isIndependentFacing () {
    return independentFacing;
  }

  //</editor-fold>
}
