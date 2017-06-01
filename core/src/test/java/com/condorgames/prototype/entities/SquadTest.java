package com.condorgames.prototype.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.condorgames.prototype.entities.squad.Squad;
import com.condorgames.prototype.entities.squad.SquadBase;
import org.junit.Ignore;
import org.junit.Test;

import static org.mockito.Mockito.mock;

@Ignore
public class SquadTest {

  private Squad UUT;
  private Body body;

  public SquadTest() {
    body = mock(Body.class);

//    when(Gdx.graphics.getWidth()).thenReturn(200);
//    when(Gdx.graphics.getHeight()).thenReturn(200);

    UUT = new Squad(body, SquadBase.Faction.AXIS);
  }

  @Test
  public void fire() throws Exception {
  }

  @Test
  public void getAmmo() throws Exception {
  }

  @Test
  public void getStrength() throws Exception {
  }

  @Test
  public void takeCasualty() throws Exception {
  }

  @Test
  public void setMorale() throws Exception {
  }

  @Test
  public void decreaseMorale() throws Exception {
  }

  @Test
  public void raiseMorale() throws Exception {
  }

  @Test
  public void getMorale() throws Exception {
  }

}