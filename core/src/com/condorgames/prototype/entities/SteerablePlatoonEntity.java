package com.condorgames.prototype.entities;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.condorgames.prototype.Helper;
import com.condorgames.prototype.entities.equipment.weapons.Weapon;
import com.condorgames.prototype.creator.WeaponCreator;
import com.condorgames.prototype.entities.equipment.weapons.WeaponExecutorBase;

public class SteerablePlatoonEntity extends PlatoonPhysicEntity implements Steerable<Vector2> {

  private SteeringBehavior steeringBehavior;
  private SteeringAcceleration<Vector2> steeringOutput;

  private float maxLinearSpeed = 1f;
  private float maxLinearAcceleration = 1f;
  private float maxAngularSpeed = 0.5f;
  private float maxAngularAcceleration = 0.5f;
  private boolean tagged;
  private Weapon weapon;
  private WeaponExecutorBase weaponExecutor;

  //TODO add speech library?

  public SteerablePlatoonEntity(Body body, Faction faction) {
    super(body, faction);
    weapon = WeaponCreator.createRifle();
    steeringOutput = new SteeringAcceleration<Vector2>(new Vector2());
//    weapon.setWeaponFiredListener(() -> System.out.println("fired"));
  }

  public void setSteeringBehavior(SteeringBehavior steeringBehavior) {
    this.steeringBehavior = steeringBehavior;
  }

  public void applySteering() {
    if (isTagged() == false) {
      steeringBehavior.calculateSteering(steeringOutput);
      if (steeringOutput.isZero() == false) {
        if (!steeringOutput.linear.isZero()) {
          getBody().setLinearVelocity(steeringOutput.linear);
        }
      }
    }
  }

  public void update() {
    applySteering();
  }

  @Override
  public void fire(float deltaTime) {
    weapon.fireWeapon(deltaTime);
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
    return new Location<Vector2>() {
      @Override
      public Vector2 getPosition() {
        return SteerablePlatoonEntity.this.getPosition();
      }

      @Override
      public float getOrientation() {
        return SteerablePlatoonEntity.this.getOrientation();
      }

      @Override
      public void setOrientation(float orientation) {
        SteerablePlatoonEntity.this.setOrientation(orientation);
      }

      @Override
      public float vectorToAngle(Vector2 vector) {
        return SteerablePlatoonEntity.this.vectorToAngle(vector);
      }

      @Override
      public Vector2 angleToVector(Vector2 outVector, float angle) {
        return SteerablePlatoonEntity.this.angleToVector(outVector, angle);
      }

      @Override
      public Location<Vector2> newLocation() {
        return SteerablePlatoonEntity.this.newLocation();
      }
    };
  }
  //</editor-fold>
}
