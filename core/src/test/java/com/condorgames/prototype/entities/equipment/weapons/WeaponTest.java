package com.condorgames.prototype.entities.equipment.weapons;

import com.condorgames.prototype.battleresolver.HitMechanic;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class WeaponTest {
  private WeaponProperties weaponProperties;
  private FiringMechanic firingMechanic;
  private Weapon weapon;

  public WeaponTest() {
    weaponProperties = mock(WeaponProperties.class);
    when(weaponProperties.getAmmoCapacity()).thenReturn(5);
    when(weaponProperties.getAmmoCount()).thenReturn(5);
    weapon = new Weapon(weaponProperties);
  }

  @Test
  public void shouldReloadWeaponWithNotMoreThanCapacityAndReturnRestOfPassedAmmoCount() throws Exception {
    int actual = weapon.reload(10);
    verify(weaponProperties, atLeastOnce()).getAmmoCapacity();
    verify(weaponProperties, atLeastOnce()).setAmmoCount(5);
    assertEquals(5, actual);
  }

}