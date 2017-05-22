package com.condorgames.prototype.battleresolver;

import com.badlogic.gdx.Gdx;

public class BattleSituation {
  private BattleParticipant participantA, participantB;

  private BattleSituation(BattleParticipant participantA, BattleParticipant participantB) {
    this.participantA = participantA;
    this.participantB = participantB;
  }

  public static BattleSituation createBattleSituation(BattleParticipant participantA, BattleParticipant participantB) {
    return new BattleSituation(participantA, participantB);
  }

  public void resolve() {
    participantA.fire();
  }
}
