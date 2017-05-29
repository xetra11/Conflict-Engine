package com.condorgames.prototype.battleresolver;

public interface SituationResolver {
  void addSituations(ResolvableSituation... resolvableSituations);
  void resolve(float deltaTime, boolean parallel);

}
