package com.condorgames.prototype.helper;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CooldownTest {

  private Cooldown cooldown;

  @Before
  public void setup(){
    cooldown = new Cooldown(2);
  }


  @Test
  public void shouldBeDoneAndCallListenerIfDeltaTimeIsEqualOrHigherThanCDTime() throws Exception {
    final boolean[] bool = {false};
    float deltaTimePassed = 2f;
    cooldown.isDone(deltaTimePassed, () -> bool[0] = true);
    assertTrue(bool[0]);
  }

  @Test
  public void shouldNOTBeDoneAndCallListenerIfDeltaTimeIsEqualOrHigherThanCDTime() throws Exception {
    final boolean[] bool = {false};
    float deltaTimePassed = 1.9f;
    cooldown.isDone(deltaTimePassed, () -> bool[0] = true);
    assertFalse(bool[0]);
  }

  @Test
  public void shouldNotExecuteListenerIfCDTimeHasNotBeenMoveThroughDeltaTime() throws Exception {
    final boolean[] bool = {false};
    float deltaTimePassed = 1.9f;
    cooldown.isDone(deltaTimePassed, () -> bool[0] = true);
    assertFalse(bool[0]);
  }

  @Test
  public void shouldCallListenerAfterDeltaTimeTickSimulationHasFinished() throws Exception {
    final boolean[] bool = {false};
    float deltaTimePassed = 0.8f;
    cooldown.isDone(deltaTimePassed, () -> bool[0] = true);
    assertFalse(bool[0]);
    cooldown.isDone(deltaTimePassed, () -> bool[0] = true);
    assertFalse(bool[0]);
    cooldown.isDone(deltaTimePassed, () -> bool[0] = true);
    assertTrue(bool[0]);
  }

  @Test
  public void shouldResetCooldownWithNewTime() throws Exception {
    final boolean[] bool = {false};
    float deltaTimePassed = 2.0f;
    cooldown.isDone(deltaTimePassed, () -> bool[0] = true);
    assertTrue(bool[0]);
    cooldown.resetWith(3f);
    deltaTimePassed = 3.0f;
    cooldown.isDone(deltaTimePassed, () -> bool[0] = true);
    assertTrue(bool[0]);
  }

  @Test
  public void shouldResetTimerToInitialTime() throws Exception {
    final boolean[] bool = {false};
    float deltaTimePassed = 1.5f;
    cooldown.isDone(deltaTimePassed, () -> bool[0] = true);
    assertFalse(bool[0]);
    cooldown.reset();
    //Now time is 2.0f again
    cooldown.isDone(deltaTimePassed, () -> bool[0] = true);
    assertFalse(bool[0]);
    cooldown.isDone(deltaTimePassed, () -> bool[0] = true);
    assertTrue(bool[0]);
  }

}