package com.condorgames.prototype.battleresolver;

public class BattleSituation {
  private BattleParticipant activeContact, passiveContact;
  private float wakeupTimer;

  private BattleSituation(BattleParticipant activeContact, BattleParticipant passiveContact) {
    this.activeContact = activeContact;
    this.passiveContact = passiveContact;
    wakeupTimer = 10f;
  }

  public static BattleSituation createBattleSituation(BattleParticipant activeContact,
                                                      BattleParticipant passiveContact) {
    return new BattleSituation(activeContact, passiveContact);
  }

  public void resolve(float deltaTime) {
    activeContact.fire(deltaTime);
    if (wakeupTimer <= 0) {
      passiveContact.fire(deltaTime);
    } else {
      wakeupTimer -= deltaTime;
    }
  }
}
