package com.condorgames.prototype.entities.base;

import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;
import com.condorgames.prototype.helper.Helper;

public class Scene2dLocation implements Location<Vector2> {

  Vector2 position;
  float orientation;

  public Scene2dLocation() {
    this.position = new Vector2();
    this.orientation = 0;
  }

  @Override
  public Vector2 getPosition() {
    return position;
  }

  @Override
  public float getOrientation() {
    return orientation;
  }

  @Override
  public void setOrientation(float orientation) {
    this.orientation = orientation;
  }

  @Override
  public Location<Vector2> newLocation() {
    return new Scene2dLocation();
  }

  @Override
  public float vectorToAngle(Vector2 vector) {
    return Helper.vectorToAngle(vector);
  }

  @Override
  public Vector2 angleToVector(Vector2 outVector, float angle) {
    return Helper.angleToVector(outVector, angle);
  }

}
