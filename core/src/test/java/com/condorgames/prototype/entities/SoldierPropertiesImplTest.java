package com.condorgames.prototype.entities;

import com.condorgames.prototype.battleresolver.Morale.MoraleBase;
import com.condorgames.prototype.entities.soldier.SoldierPropertiesImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static com.condorgames.prototype.entities.soldier.SoldierProperties.*;
import static org.junit.Assert.*;

public class SoldierPropertiesImplTest {

  private SoldierPropertiesImpl soldierProperties;

  @Before
  public void setup() {
    soldierProperties = new SoldierPropertiesImpl(MoraleBase.NORMAL, Health.OK, "Peter");
  }

  @Ignore
  @Test
  public void shouldDecreaseMoraleOneLower() throws Exception {
    MoraleBase expected = MoraleBase.LOW;
    soldierProperties.decreaseMorale();
    MoraleBase actual = soldierProperties.getMoraleBase();
    assertEquals(expected, actual);
  }

  @Ignore
  @Test
  public void shouldRaiseMoraleOneHigher() throws Exception {
    MoraleBase expected = MoraleBase.HIGH;
    soldierProperties.raiseMorale();
    MoraleBase actual = soldierProperties.getMoraleBase();
    assertEquals(expected, actual);
  }

  @Ignore
  @Test
  public void shouldNotLowerBeyondLast() {
    MoraleBase expected = MoraleBase.PINNED_DOWN;
    soldierProperties.decreaseMorale();
    soldierProperties.decreaseMorale();
    soldierProperties.decreaseMorale();
    soldierProperties.decreaseMorale();
    soldierProperties.decreaseMorale();
    soldierProperties.decreaseMorale();
    soldierProperties.decreaseMorale();
    MoraleBase actual = soldierProperties.getMoraleBase();
    assertEquals(expected, actual);
  }

  @Ignore
  @Test
  public void shouldNotRaiseBeyondFirst() {
    MoraleBase expected = MoraleBase.FANATIC;
    soldierProperties.raiseMorale();
    soldierProperties.raiseMorale();
    soldierProperties.raiseMorale();
    soldierProperties.raiseMorale();
    soldierProperties.raiseMorale();
    soldierProperties.raiseMorale();
    soldierProperties.raiseMorale();
    MoraleBase actual = soldierProperties.getMoraleBase();
    assertEquals(expected, actual);
  }

  @Ignore
  @Test
  public void shouldReturnValueOfMorale() {
    int expected = 3;
    int actual = soldierProperties.getMoraleBase().getValue();
    assertEquals(expected, actual);
    soldierProperties.raiseMorale();
    expected = 4;
    actual = soldierProperties.getMoraleBase().getValue();
    assertEquals(expected, actual);
  }


}