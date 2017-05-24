package com.condorgames.prototype;

import com.badlogic.gdx.math.Vector2;
import com.condorgames.prototype.helper.Helper;
import org.junit.Test;

import static org.junit.Assert.*;

public class HelperTest {

  @Test
  public void getPixelToMeterVector() throws Exception {
    Vector2 expected = new Vector2(0.01f, 0.01f);
    Vector2 actual = Helper.getPixelToMeter(new Vector2(1f, 1f));
    assertEquals(expected, actual);
  }

  @Test
  public void getPixelToMeterFloats() throws Exception {
    Vector2 expected = new Vector2(0.01f, 0.01f);
    Vector2 actual = Helper.getPixelToMeter(1f, 1f);
    assertEquals(expected, actual);
  }

  @Test
  public void getMeterToPixelVector() throws Exception {
    Vector2 expected = new Vector2(1f, 1f);
    Vector2 actual = Helper.getMeterToPixel(new Vector2(0.01f, 0.01f));
    assertEquals(expected, actual);
  }

  @Test
  public void getMeterToPixelFloats() throws Exception {
    Vector2 expected = new Vector2(1f, 1f);
    Vector2 actual = Helper.getMeterToPixel(0.01f, 0.01f);
    assertEquals(expected, actual);
  }

}
