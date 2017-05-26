package com.condorgames.prototype.creator;

import com.condorgames.prototype.entities.equipment.weapons.Weapon;
import com.condorgames.prototype.entities.equipment.weapons.WeaponProperties;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WeaponCreatorTest {
  @Test
  public void createRifle() throws Exception {
    Weapon rifle = WeaponCreator.createRifle();
    assertEquals(WeaponProperties.Type.RIFLE, rifle.getProperties().getType());
    assertEquals(5, rifle.getProperties().getAmmoCapacity());
    assertEquals(2f, rifle.getProperties().getCadence(), 0.001f);
    assertEquals(10f, rifle.getProperties().getReloadTime(),0.001f);
  }

}