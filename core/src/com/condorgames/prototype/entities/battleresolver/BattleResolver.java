package com.condorgames.prototype.entities.battleresolver;

import java.util.Arrays;
import java.util.List;

public class BattleResolver {

  private List<BattleSituation> battleSituations;

  public void addBattleSituations(List<BattleSituation> battleSituations) {
    battleSituations.addAll(battleSituations);
  }

  public void addBattleSituations(BattleSituation... battleSituations) {
    this.battleSituations.addAll(Arrays.asList(battleSituations));
  }

  public void resolve(boolean parallel) {
    if (parallel) {
      battleSituations.parallelStream().forEach(BattleSituation::resolve);
    } else {
      battleSituations.forEach(BattleSituation::resolve);
    }
  }
}
