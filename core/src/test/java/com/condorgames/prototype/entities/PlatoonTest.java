package com.condorgames.prototype.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.condorgames.prototype.creator.BodyCreator;
import com.condorgames.prototype.entities.platoon.Platoon;
import com.condorgames.prototype.entities.platoon.PlatoonEntityBase;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Ignore
public class PlatoonTest {

  private Platoon UUT;
  private Body body;

  public PlatoonTest() {
    body = mock(Body.class);

//    when(Gdx.graphics.getWidth()).thenReturn(200);
//    when(Gdx.graphics.getHeight()).thenReturn(200);

    UUT = new Platoon(body, PlatoonEntityBase.Faction.AXIS);
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