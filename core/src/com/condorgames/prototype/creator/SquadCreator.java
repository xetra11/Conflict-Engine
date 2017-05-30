package com.condorgames.prototype.creator;

import com.condorgames.prototype.entities.soldier.Soldier;

import java.util.ArrayList;
import java.util.List;

public abstract class SquadCreator {

  public static final int AMMO = 60;

  public static List<Soldier> createAxisRifleSquad(){
    List<Soldier> soldiers = new ArrayList<>();
    soldiers.add(new Soldier(AMMO, WeaponCreator.createRifle(),"Peter"));
    soldiers.add(new Soldier(AMMO, WeaponCreator.createRifle(), "Siggi"));
    soldiers.add(new Soldier(AMMO, WeaponCreator.createRifle(), "Franz"));
    soldiers.add(new Soldier(AMMO, WeaponCreator.createRifle(), "Paul"));
    soldiers.add(new Soldier(AMMO, WeaponCreator.createRifle(), "Karl"));
    soldiers.add(new Soldier(AMMO, WeaponCreator.createRifle(), "Siegmund"));
    soldiers.add(new Soldier(AMMO, WeaponCreator.createRifle(), "Fritz"));
    soldiers.add(new Soldier(AMMO, WeaponCreator.createRifle(), "Franz"));
    soldiers.add(new Soldier(AMMO, WeaponCreator.createRifle(), "Ludwig"));
    return soldiers;
  }

  public static List<Soldier> createAlliedRifleSquad(){
    List<Soldier> soldiers = new ArrayList<>();
    soldiers.add(new Soldier(AMMO, WeaponCreator.createRifle(),"Charlies"));
    soldiers.add(new Soldier(AMMO, WeaponCreator.createRifle(), "Johnny"));
    soldiers.add(new Soldier(AMMO, WeaponCreator.createRifle(), "Steven"));
    soldiers.add(new Soldier(AMMO, WeaponCreator.createRifle(), "Bob"));
    soldiers.add(new Soldier(AMMO, WeaponCreator.createRifle(), "Manny"));
    soldiers.add(new Soldier(AMMO, WeaponCreator.createRifle(), "George"));
    soldiers.add(new Soldier(AMMO, WeaponCreator.createRifle(), "Terrence"));
    soldiers.add(new Soldier(AMMO, WeaponCreator.createRifle(), "Jackson"));
    soldiers.add(new Soldier(AMMO, WeaponCreator.createRifle(), "Topkego"));
    return soldiers;
  }
}
