package com.condorgames.prototype.entities;

import com.condorgames.prototype.battleresolver.Morale;
import com.condorgames.prototype.battleresolver.Morale.MoraleState;
import com.condorgames.prototype.entities.soldier.SoldierProperties;
import com.condorgames.prototype.entities.soldier.SoldierPropertiesImpl;
import org.junit.Before;
import org.junit.Test;

import static com.condorgames.prototype.entities.soldier.SoldierProperties.*;
import static org.junit.Assert.*;

public class SoldierPropertiesImplTest {

  private SoldierPropertiesImpl soldierProperties;

  @Before
  public void setup() {
    soldierProperties = new SoldierPropertiesImpl(MoraleState.NORMAL, Health.OK);
  }

  @Test
  public void shouldDecreaseMoraleOneLower() throws Exception {
    MoraleState expected = MoraleState.LOW;
    soldierProperties.decreaseMorale();
    MoraleState actual = soldierProperties.getMorale();
    assertEquals(expected, actual);
  }

  @Test
  public void shouldRaiseMoraleOneHigher() throws Exception {
    MoraleState expected = MoraleState.HIGH;
    soldierProperties.raiseMorale();
    MoraleState actual = soldierProperties.getMorale();
    assertEquals(expected, actual);
  }

  @Test
  public void shouldNotLowerBeyondLast() {
    MoraleState expected = MoraleState.PINNED_DOWN;
    soldierProperties.decreaseMorale();
    soldierProperties.decreaseMorale();
    soldierProperties.decreaseMorale();
    soldierProperties.decreaseMorale();
    soldierProperties.decreaseMorale();
    soldierProperties.decreaseMorale();
    soldierProperties.decreaseMorale();
    MoraleState actual = soldierProperties.getMorale();
    assertEquals(expected, actual);
  }

  @Test
  public void shouldNotRaiseBeyondFirst() {
    MoraleState expected = MoraleState.FANATIC;
    soldierProperties.raiseMorale();
    soldierProperties.raiseMorale();
    soldierProperties.raiseMorale();
    soldierProperties.raiseMorale();
    soldierProperties.raiseMorale();
    soldierProperties.raiseMorale();
    soldierProperties.raiseMorale();
    MoraleState actual = soldierProperties.getMorale();
    assertEquals(expected, actual);
  }

  @Test
  public void shouldReturnValueOfMorale() {
    int expected = 3;
    int actual = soldierProperties.getMorale().getValue();
    assertEquals(expected, actual);
    soldierProperties.raiseMorale();
    expected = 4;
    actual = soldierProperties.getMorale().getValue();
    assertEquals(expected, actual);
  }


}