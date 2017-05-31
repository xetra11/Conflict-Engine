package com.condorgames.prototype.battleresolver;

import com.condorgames.prototype.audio.AudioManager;
import com.condorgames.prototype.battleresolver.HitMechanic.HitType;
import com.condorgames.prototype.entities.platoon.SquadEntityBase;
import com.condorgames.prototype.helper.Cooldown;

public class BattleSituation implements ResolvableSituation{
  private SquadEntityBase activeContact, passiveContact;
  private Cooldown wakeupCooldown;
  private Cooldown moraleTick;

  private BattleSituation(SquadEntityBase activeContact, SquadEntityBase passiveContact) {
    this.activeContact = activeContact;
    this.passiveContact = passiveContact;
    wakeupCooldown = new Cooldown(10f);
    moraleTick = new Cooldown(2f, true);
  }

  public static ResolvableSituation createBattleSituation(SquadEntityBase activeContact,
                                                      SquadEntityBase passiveContact) {
    return new BattleSituation(activeContact, passiveContact);
  }


  // TODO declare environment here to avoid hits!
  public void resolve(float deltaTime) {


    if (activeContact.getStrength() <= 0) {
      System.out.println("+++ ALLIED WON BATTLE! +++");
    } else if (passiveContact.getStrength() <= 0) {
      System.out.println("+++ AXIS WON BATTLE! +++");
    } else {
      moraleTick.isDone(deltaTime, () -> {
        activeContact.raiseMorale();
        passiveContact.raiseMorale();
      });

      activeContact.fire(deltaTime, hitType -> {
        if (hitType.equals(HitType.HIT)) {
          resolveHit(passiveContact);
        } else if (hitType.equals(HitType.SURPRESSING_HIT)) {
          passiveContact.decreaseMorale();
        }
      });

      wakeupCooldown.isDone(deltaTime, () -> passiveContact.fire(deltaTime, hitType -> {
        if (hitType.equals(HitType.HIT)) {
          resolveHit(activeContact);
        } else if (hitType.equals(HitType.SURPRESSING_HIT)) {
          activeContact.decreaseMorale();
          AudioManager.playTakingFire();
        }
      }));
    }
  }

  private void resolveHit(SquadEntityBase contact) {
    System.out.print(">> " + contact.getFaction() + ": ");

    //Double morale decrease due hit!
    contact.decreaseMorale();
    contact.decreaseMorale();
    contact.takeCasualty();
    AudioManager.playCasualty();
  }
}
