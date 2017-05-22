package com.condorgames.prototype.entities.battleresolver;

import com.badlogic.gdx.Gdx;

public class BattleSituation {
  private BattleParticipant participantA, participantB;

  private BattleSituation(BattleParticipant participantA, BattleParticipant participantB){
    this.participantA = participantA;
    this.participantB = participantB;
  }

  public static BattleSituation createBattleSituation(BattleParticipant participantA, BattleParticipant participantB){
    return new BattleSituation(participantA, participantB);
  }

  public void resolve(){
    Gdx.app.log("BattleSituation", "resolve called");
    Gdx.app.log("BattleSituation.Participants", participantA.toString());
    Gdx.app.log("BattleSituation.Participants", participantB.toString());
  }
}
