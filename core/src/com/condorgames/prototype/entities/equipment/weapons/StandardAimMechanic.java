package com.condorgames.prototype.entities.equipment.weapons;

import com.condorgames.prototype.entities.equipment.weapons.interfaces.AimMechanic;
import com.condorgames.prototype.helper.Cooldown;

public class StandardAimMechanic implements AimMechanic {
  private static final float MULTIPLIER = 3f;
  private static final float INIT = 1.5f;
  private Cooldown aimCooldown = new Cooldown(INIT);

  public StandardAimMechanic() {
    aimCooldown = new Cooldown(1f);
  }

  public void aim(float deltaTime, AimingListener onAimedListener) {
    aimCooldown.isDone(deltaTime, () -> {
      onAimedListener.OnAimed();
      float newAimTime = (float) Math.random() * MULTIPLIER;
      aimCooldown.resetWith(newAimTime);
    });
  }
}

