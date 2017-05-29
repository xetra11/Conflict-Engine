package com.condorgames.prototype.creator;

import com.condorgames.prototype.entities.soldier.Soldier;

import java.util.ArrayList;
import java.util.List;

public abstract class SquadCreator {

  public static List<Soldier> createRifleSquad(){
    List<Soldier> soldiers = new ArrayList<>();
    soldiers.add(new Soldier(60, WeaponCreator.createRifle()));
    soldiers.add(new Soldier(60, WeaponCreator.createRifle()));
    soldiers.add(new Soldier(60, WeaponCreator.createRifle()));
    soldiers.add(new Soldier(60, WeaponCreator.createRifle()));
    soldiers.add(new Soldier(60, WeaponCreator.createRifle()));
    soldiers.add(new Soldier(60, WeaponCreator.createRifle()));
    soldiers.add(new Soldier(60, WeaponCreator.createRifle()));
    soldiers.add(new Soldier(60, WeaponCreator.createRifle()));
    soldiers.add(new Soldier(60, WeaponCreator.createRifle()));
    return soldiers;
  }
}
