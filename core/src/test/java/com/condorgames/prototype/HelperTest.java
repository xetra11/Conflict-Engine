package com.condorgames.prototype;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.JUnit38ClassRunner;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;
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
