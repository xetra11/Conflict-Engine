package com.condorgames.prototype.battleresolver;

import com.condorgames.prototype.audio.AudioManager;
import com.condorgames.prototype.battleresolver.HitMechanic.HitType;
import com.condorgames.prototype.entities.PlatoonEntityBase;
import com.condorgames.prototype.entities.equipment.weapons.Fireable;
import com.condorgames.prototype.helper.Cooldown;

public class BattleSituation {
  private PlatoonEntityBase activeContact, passiveContact;
  private Cooldown wakeupCooldown;

  private BattleSituation(PlatoonEntityBase activeContact, PlatoonEntityBase passiveContact) {
    this.activeContact = activeContact;
    this.passiveContact = passiveContact;
    wakeupCooldown = new Cooldown(10f);
  }

  public static BattleSituation createBattleSituation(PlatoonEntityBase activeContact,
                                                      PlatoonEntityBase passiveContact) {
    return new BattleSituation(activeContact, passiveContact);
  }

  public void resolve(float deltaTime) {
    activeContact.fire(deltaTime, hitType -> {
      if (hitType.equals(HitType.HIT)) {
        passiveContact.decreaseStrength(1);
        passiveContact.decreaseMorale();
      }
    });

    wakeupCooldown.isDone(deltaTime, () -> passiveContact.fire(deltaTime, hitType -> {
      if (hitType.equals(HitType.HIT)) {
        activeContact.decreaseStrength(1);
        activeContact.decreaseMorale();
        AudioManager.playCasualty();
      } else if (hitType.equals(HitType.SURPRESSING_HIT)) {
        activeContact.decreaseMorale();
        AudioManager.playTakingFire();
      }
    }));
  }
}
