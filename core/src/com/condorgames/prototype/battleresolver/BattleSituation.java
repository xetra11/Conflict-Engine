package com.condorgames.prototype.battleresolver;

import com.condorgames.prototype.audio.AudioManager;
import com.condorgames.prototype.battleresolver.HitMechanic.HitType;
import com.condorgames.prototype.entities.platoon.PlatoonEntityBase;
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


  // TODO declare environment here to avoid hits!
  public void resolve(float deltaTime) {
    activeContact.fire(deltaTime, hitType -> {
      if (hitType.equals(HitType.HIT)) {
        //Double morale decrease due hit!
        passiveContact.decreaseMorale();
        passiveContact.decreaseMorale();
        passiveContact.takeCasualty();
      } else if (hitType.equals(HitType.SURPRESSING_HIT)) {
        passiveContact.decreaseMorale();
      }
    });

    //TODO decrease Strength & Morale needs to be shifted down to concrete class
    wakeupCooldown.isDone(deltaTime, () -> passiveContact.fire(deltaTime, hitType -> {
      if (hitType.equals(HitType.HIT)) {
        //Double morale decrease due hit!
        activeContact.decreaseMorale();
        activeContact.decreaseMorale();
        activeContact.takeCasualty();
        AudioManager.playCasualty();
      } else if (hitType.equals(HitType.SURPRESSING_HIT)) {
        activeContact.decreaseMorale();
        AudioManager.playTakingFire();
      }
    }));
  }
}
