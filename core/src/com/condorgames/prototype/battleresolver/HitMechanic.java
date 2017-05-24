package com.condorgames.prototype.battleresolver;

public class HitMechanic {

  private float hitThreshold = 0.95f;
  private float surpressingHitThreshold = 0.5f;
  double random;

  public enum HitType {
    HIT,
    SURPRESSING_HIT,
    SKY
  }

  public HitType hit() {
    random = Math.random();
    if (isHit()) {
      return HitType.HIT;
    } else if (isSurpressingHit()) {
      return HitType.SURPRESSING_HIT;
    } else {
      return HitType.SKY;
    }
  }

  private boolean isHit() {
    return random > hitThreshold;
  }

  private boolean isSurpressingHit() {
    return random > surpressingHitThreshold;
  }

}
