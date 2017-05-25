package com.condorgames.prototype.battleresolver;

import com.condorgames.prototype.entities.equipment.weapons.Fireable;
import com.condorgames.prototype.helper.Cooldown;

public class BattleSituation {
  private Fireable activeContact, passiveContact;
  private Cooldown wakeupCooldown;

  private BattleSituation(Fireable activeContact, Fireable passiveContact) {
    this.activeContact = activeContact;
    this.passiveContact = passiveContact;
    wakeupCooldown = new Cooldown(10f);
  }

  public static BattleSituation createBattleSituation(Fireable activeContact,
                                                      Fireable passiveContact) {
    return new BattleSituation(activeContact, passiveContact);
  }

  public void resolve(float deltaTime) {
    activeContact.fire(deltaTime, hitType -> System.out.println(hitType));
    wakeupCooldown.isDone(deltaTime, () -> passiveContact.fire(deltaTime, hitType -> System.out.println(hitType)));
  }
}
