package com.condorgames.prototype.battleresolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BattleResolver implements SituationResolver {

  private List<ResolvableSituation> battleSituations = new ArrayList<>();

  public void addSituations(List<ResolvableSituation> battleSituations) {
    battleSituations.addAll(battleSituations);
  }

  @Override
  public void addSituations(ResolvableSituation... battleSituations) {
    this.battleSituations.addAll(Arrays.asList(battleSituations));
  }

  public void resolve(float deltaTime, boolean parallel) {
    if (battleSituations.isEmpty() == false) {
      if (parallel) {
        battleSituations.parallelStream().forEach(battleSituation -> battleSituation.resolve(deltaTime));
      } else {
        battleSituations.forEach(battleSituation -> battleSituation.resolve(deltaTime));
      }
    }
  }
}
