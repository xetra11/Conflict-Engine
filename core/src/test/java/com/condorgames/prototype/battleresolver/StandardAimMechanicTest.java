package com.condorgames.prototype.battleresolver;

import com.condorgames.prototype.entities.equipment.weapons.StandardAimMechanic;
import com.condorgames.prototype.entities.equipment.weapons.interfaces.AimMechanic;
import com.sun.javafx.animation.TickCalculation;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.time.Clock;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class StandardAimMechanicTest {

  AimMechanic aimMechanic;

  @Before
  public void setup() {
    aimMechanic = new StandardAimMechanic();
  }

  @Test
  public void shouldExecuteAimListenerInTime() throws Exception {
    float aimCooldownTime = 1.5f;
    final boolean[] bool = {false};
    aimMechanic.aim(1.5f, () -> bool[0] = true);
    assertTrue(bool[0]);
  }

}