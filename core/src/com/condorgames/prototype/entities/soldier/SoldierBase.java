package com.condorgames.prototype.entities.soldier;

import com.condorgames.prototype.battleresolver.Morale;
import com.condorgames.prototype.entities.soldier.Skillset.AimSkill;
import com.condorgames.prototype.entities.soldier.Skillset.WeaponSkill;

public class SoldierBase implements Morale {
  private SoldierProperties soldierProperties;
  private Skillset skillset;

  public SoldierBase() {
    this(AimSkill.NORMAL, WeaponSkill.NORMAL, MoraleState.NORMAL, SoldierProperties.Health.OK);
  }

  public SoldierBase(AimSkill aimSkill) {
    this(aimSkill, WeaponSkill.NORMAL, MoraleState.NORMAL, SoldierProperties.Health.OK);
  }

  public SoldierBase(AimSkill aimSkill, WeaponSkill weaponSkill) {
    this(aimSkill, weaponSkill, MoraleState.NORMAL, SoldierProperties.Health.OK);
  }

  public SoldierBase(AimSkill aimSkill, WeaponSkill weaponSkill, MoraleState moraleState, SoldierProperties.Health health) {
    this.soldierProperties = new SoldierPropertiesImpl(moraleState, health);
    this.skillset = new SkillsetImpl(aimSkill, weaponSkill);
  }

  public Skillset getSkills() {
    return skillset;
  }

  public SoldierProperties getProperties() {
    return soldierProperties;
  }

  public void wound() {
    soldierProperties.setHealth(soldierProperties.getHealth().decrease());
  }

  @Override
  public void setMorale(MoraleState morale) {
    soldierProperties.setMorale(morale);
  }

  @Override
  public void decreaseMorale() {
    soldierProperties.decreaseMorale();
  }

  @Override
  public void raiseMorale() {
    soldierProperties.raiseMorale();
  }

  @Override
  public MoraleState getMorale() {
    return soldierProperties.getMorale();
  }
}
