package com.condorgames.prototype.battleresolver;

import com.condorgames.prototype.helper.Cooldown;

public class BattleSituation {
  private BattleParticipant activeContact, passiveContact;
  private Cooldown wakeupCooldown;

  private BattleSituation(BattleParticipant activeContact, BattleParticipant passiveContact) {
    this.activeContact = activeContact;
    this.passiveContact = passiveContact;
    wakeupCooldown = new Cooldown(10f);
  }

  public static BattleSituation createBattleSituation(BattleParticipant activeContact,
                                                      BattleParticipant passiveContact) {
    return new BattleSituation(activeContact, passiveContact);
  }

  public void resolve(float deltaTime) {
    activeContact.fire(deltaTime);
    wakeupCooldown.isDone(deltaTime, () -> passiveContact.fire(deltaTime));
  }
}
