package com.condorgames.prototype.battleresolver;

import com.sun.javafx.animation.TickCalculation;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.time.Clock;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class AimMechanicTest {

  AimMechanic aimMechanic;

  @Before
  public void setup() {
    aimMechanic = new AimMechanic();
  }

  @Test
  public void shouldExecuteAimListenerInTime() throws Exception {
    float aimCooldownTime = 1.5f;
    final boolean[] bool = {false};
    aimMechanic.aim(1.5f, () -> bool[0] = true);
    assertTrue(bool[0]);
  }

}