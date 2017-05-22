package com.condorgames.prototype.entities.battleresolver;

public class BattleSituation {
  private BattleParticipant participantA, participantB;

  private BattleSituation(BattleParticipant participantA, BattleParticipant participantB){
    this.participantA = participantA;
    this.participantB = participantB;
  }

  public BattleSituation createBattleSituation(BattleParticipant participantA, BattleParticipant participantB){
    return new BattleSituation(participantA, participantB);
  }

  public void resolve(){

  }
}
