package com.condorgames.prototype.helper;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.condorgames.prototype.creator.BodyCreator;
import org.junit.Ignore;
import org.junit.Test;

import static com.condorgames.prototype.helper.Helper.FACTOR;
import static org.junit.Assert.*;

public class HelperTest {

  private static final float TEST_X = 5f;
  private static final float TEST_Y = 5f;
  public static final Vector2 TEST_VEC = new Vector2(TEST_X, TEST_Y);

  @Test
  public void shouldReturnMeterAmountForGivenPixel() throws Exception {
    Vector2 expected = new Vector2(TEST_X / FACTOR, TEST_Y / FACTOR);
    Vector2 actual = Helper.getPixelToMeter(TEST_X, TEST_Y);
    assertEquals(expected, actual);
  }

  @Test
  public void shouldReturnMeterAmountForGivenPixelWhenPassingVector2() throws Exception {
    Vector2 expected = new Vector2(TEST_X / FACTOR, TEST_Y / FACTOR);
    Vector2 actual = Helper.getPixelToMeter(TEST_VEC);
    assertEquals(expected, actual);
  }

  @Test
  public void shouldReturnPixelAmountForGivenMeter() throws Exception {
    Vector2 expected = new Vector2(TEST_X * FACTOR, TEST_Y * FACTOR);
    Vector2 actual = Helper.getMeterToPixel(TEST_X, TEST_Y);
    assertEquals(expected, actual);
  }

  @Test
  public void shouldReturnPixelAmountForGivenMeterWhenPassingVec2() throws Exception {
    Vector2 expected = new Vector2(TEST_X * FACTOR, TEST_Y * FACTOR);
    Vector2 actual = Helper.getMeterToPixel(TEST_VEC);
    assertEquals(expected, actual);
  }

  @Test
  public void shouldReturnPixelFromMeterValuePassed() throws Exception {
    float expected = TEST_X * FACTOR;
    float actual = Helper.getMeterToPixel(TEST_X);
    assertEquals(expected, actual, 0.001f);
  }

  @Test
  public void shouldReturnMeterFromPixelValuePassed() throws Exception {
    float expected = TEST_X / FACTOR;
    float actual = Helper.getPixelToMeter(TEST_X);
    assertEquals(expected, actual, 0.001f);
  }

  @Test
  @Ignore
  public void getConvertedScene2DToBox2DPosition() throws Exception {
    //TODO GDX muss gemocked werden
    Vector2 expected = new Vector2(TEST_X, TEST_Y);
    Vector2 actual = Helper.getConvertedScene2DToBox2DPosition(TEST_VEC);
  }

  @Test
  @Ignore
  public void setClickedPositionForBox2D() throws Exception {
    //TODO GDX muss gemocked werden
    Body testBody = BodyCreator.createRectangleBody(new World(new Vector2(0f, 0f), false), BodyDef.BodyType.StaticBody);
    Vector2 expectedPosition = new Vector2(TEST_X / FACTOR, TEST_Y / FACTOR);
    Helper.setClickedPositionForBox2D(TEST_X, TEST_Y, testBody);
    Vector2 actualPosition = testBody.getTransform().getPosition();
    float actualRotation = testBody.getTransform().getRotation();
    assertEquals(expectedPosition, actualPosition);
  }

  @Test
  public void vectorToAngle() throws Exception {
    float expected = -0.7853982f;
    float actual = Helper.vectorToAngle(TEST_VEC);
    assertEquals(expected, actual, 0.001f);
  }

}