package com.condorgames.prototype.entities.platoon;

import com.badlogic.gdx.physics.box2d.Body;
import com.condorgames.prototype.entities.Sensor;
import com.condorgames.prototype.entities.equipment.weapons.interfaces.Fireable;
import com.condorgames.prototype.entities.squad.Squad;

import java.util.ArrayList;
import java.util.List;

public class Platoon extends PlatoonBase {

  public Platoon(Body body){
    super(body);
  }

  private List<Squad> squads = new ArrayList<>(4);

  public List<Squad> getSquads() {
    return squads;
  }

  public void addSquad(Squad squad) {
    this.squads.add(squad);
  }

  @Override
  public void setPlatoonHealth(PlatoonHealth soldierHealth) {

  }

  @Override
  public PlatoonHealth getPlatoonHealth() {
    return null;
  }
}
