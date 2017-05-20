package com.condorgames.prototype;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

import javax.xml.bind.ValidationEvent;

public abstract class Helper {
  private static float FACTOR = 100f;

  public static Vector2 getPixelToMeter(float x, float y) {
    return new Vector2(x / FACTOR, y / FACTOR);
  }

  public static Vector2 getPixelToMeter(Vector2 vector2) {
    return new Vector2(vector2.x / FACTOR, vector2.y / FACTOR);
  }

  public static Vector2 getMeterToPixel(float x, float y) {
    return new Vector2(x * FACTOR, y * FACTOR);
  }

  public static Vector2 getMeterToPixel(Vector2 vector2) {
    return new Vector2(vector2.x * FACTOR, vector2.y * FACTOR);
  }

  public static float getMeterToPixel(float pixelValue) {
    return pixelValue * FACTOR;
  }

  public static float getPixelToMeter(float pixelValue) {
    return pixelValue / FACTOR;
  }

  public static Vector2 getMappedScene2DToBox2DPosition(Vector2 position) {
    Vector2 scene2DPosition = new Vector2();
    scene2DPosition.x = position.x - Helper.getPixelToMeter(Gdx.graphics.getWidth() / 2);
    scene2DPosition.y = position.y - Helper.getPixelToMeter(Gdx.graphics.getHeight() / 2);
    return scene2DPosition;
  }

}
