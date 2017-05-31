package com.condorgames.prototype.entities.soldier;

public class SoldierPropertiesImpl implements SoldierProperties {
  private MoraleBase moraleBase;
  private float morale;
  private Health health;
  private String name;

  public SoldierPropertiesImpl(MoraleBase morale, Health health, String name) {
    this.moraleBase = morale;
    this.health = health;
    this.name = name;
    this.morale = 100f;
  }

  @Override
  public void setMoraleBase(MoraleBase morale) {
    this.moraleBase = morale;
  }

  @Override
  public void decreaseMorale() {
    switch (getMoraleBase()) {
      case FANATIC: {
        morale -= 0.5f;
        break;
      }
      case HIGH:{
        morale -= 0.8f;
        break;
      }
      case NORMAL:{
        morale -= 1.0f;
        break;
      }
      case LOW: {
        morale -= 1.5f;
        break;
      }
    }
  }

  @Override
  public void raiseMorale() {
    switch (getMoraleBase()) {
      case FANATIC: {
        morale += 1.5f;
        break;
      }
      case HIGH:{
        morale += 1.2f;
        break;
      }
      case NORMAL:{
        morale += 1.0f;
        break;
      }
      case LOW: {
        morale += 0.5f;
        break;
      }
    }
  }

  @Override
  public MoraleBase getMoraleBase() {
    return moraleBase;
  }

  @Override
  public float getMorale() {
    return morale;
  }

  @Override
  public void setMorale(float morale) {
    this.morale = morale;
  }

  @Override
  public void setHealth(Health health) {
    this.health = health;
  }

  @Override
  public Health getHealth() {
    return health;
  }

  @Override
  public String getName() {
    return name;
  }
}
