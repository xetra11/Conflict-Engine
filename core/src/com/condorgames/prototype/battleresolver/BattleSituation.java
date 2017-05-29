package com.condorgames.prototype.battleresolver;

import com.condorgames.prototype.audio.AudioManager;
import com.condorgames.prototype.battleresolver.HitMechanic.HitType;
import com.condorgames.prototype.entities.platoon.PlatoonEntityBase;
import com.condorgames.prototype.helper.Cooldown;

public class BattleSituation implements ResolvableSituation{
  private PlatoonEntityBase activeContact, passiveContact;
  private Cooldown wakeupCooldown;

  private BattleSituation(PlatoonEntityBase activeContact, PlatoonEntityBase passiveContact) {
    this.activeContact = activeContact;
    this.passiveContact = passiveContact;
    wakeupCooldown = new Cooldown(10f);
  }

  public static ResolvableSituation createBattleSituation(PlatoonEntityBase activeContact,
                                                      PlatoonEntityBase passiveContact) {
    return new BattleSituation(activeContact, passiveContact);
  }


  // TODO declare environment here to avoid hits!
  public void resolve(float deltaTime) {

    if (activeContact.getStrength() <= 0) {
      System.out.println("+++ ALLIED WON BATTLE! +++");
    } else if (passiveContact.getStrength() <= 0) {
      System.out.println("+++ AXIS WON BATTLE! +++");
    } else {
      activeContact.fire(deltaTime, hitType -> {
        System.out.print(">AXIS: ");
        if (hitType.equals(HitType.HIT)) {
          resolveHit(passiveContact);
        } else if (hitType.equals(HitType.SURPRESSING_HIT)) {
          passiveContact.decreaseMorale();
        }
      });

      //TODO decrease Strength & Morale needs to be shifted down to concrete class
      wakeupCooldown.isDone(deltaTime, () -> passiveContact.fire(deltaTime, hitType -> {
        System.out.print(">ALLIED: ");
        if (hitType.equals(HitType.HIT)) {
          resolveHit(activeContact);
        } else if (hitType.equals(HitType.SURPRESSING_HIT)) {
          activeContact.decreaseMorale();
          AudioManager.playTakingFire();
        }
      }));
    }
  }

  private void resolveHit(PlatoonEntityBase contact) {
    //Double morale decrease due hit!
    contact.decreaseMorale();
    contact.decreaseMorale();
    contact.takeCasualty();
    AudioManager.playCasualty();
  }
}
