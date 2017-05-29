package com.condorgames.prototype.battleresolver;

public class HitMechanic {

  private float HIT_THRESHOLD = 0.95f;
  private float SUPHIT_THRESHOLD = 0.5f;
  private double random;

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
    return random > HIT_THRESHOLD;
  }

  private boolean isSurpressingHit() {
    return random > SUPHIT_THRESHOLD;
  }

}
