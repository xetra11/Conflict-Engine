package com.condorgames.prototype.entities.equipment.weapons;

public class WeaponPropertiesBase implements WeaponProperties {

  private Status status;
  private Type type;
  private int ammoCount;
  private final int maxAmmo;
  private static float remainingReloadTime;
  private static float remainingCadenceTime;
  private float reloadTime;
  private float cadence;

  protected WeaponPropertiesBase(int maxAmmo, Type type) {
    this.maxAmmo = maxAmmo;
    this.type = type;
    this.status = Status.READY;
    ammoCount = maxAmmo;
  }

  @Override
  public Status getState() {
    return status;
  }

  @Override
  public void setState(Status state) {
    this.status = state;
  }

  @Override
  public int getAmmoCount() {
    return ammoCount;
  }

  @Override
  public void setAmmoCount(int amount) {
    this.ammoCount = amount;
  }

  @Override
  public int getMaxAmmo() {
    return maxAmmo;
  }

  @Override
  public float getReloadTime() {
    return reloadTime;
  }

  public void setReloadTime(float reloadTime){
    this.reloadTime = reloadTime;
  }

  @Override
  public float getCadence() {
    return cadence;
  }

  public void setCadence(float cadence){
    this.cadence = cadence;
  }

  @Override
  public Type getType() {
    return type;
  }

  @Override
  public void setType(Type type) {
    this.type = type;
  }
}
