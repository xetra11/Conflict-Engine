package com.condorgames.prototype;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.condorgames.prototype.entities.SteerablePlatoonEntity;
import com.condorgames.prototype.entities.battleresolver.BattleResolver;
import com.condorgames.prototype.entities.battleresolver.BattleSituation;

public class PlatoonContactListener implements ContactListener {
  private CondorAiPrototype context;

  public PlatoonContactListener(CondorAiPrototype context) {

    this.context = context;
  }

  @Override
  public void beginContact(Contact contact) {
    SteerablePlatoonEntity platoon = (SteerablePlatoonEntity) contact.getFixtureA().getBody().getUserData();
    SteerablePlatoonEntity enemy = (SteerablePlatoonEntity) contact.getFixtureB().getBody().getUserData();
    if (platoon.getPlatoonID() == 1) {
      triggerEnemyContact(platoon, enemy);
    }
  }

  private void triggerEnemyContact(SteerablePlatoonEntity platoon, SteerablePlatoonEntity enemy) {
    // Stop Entity
    platoon.setTagged(true);
    platoon.getBody().setLinearVelocity(0f, 0f);
    // Mock Health loss
    platoon.setHealth(90);
    context.getTextFieldHealth().setText(String.valueOf(platoon.getHealth()));
    // Play Radio Report
    Music background = Gdx.audio.newMusic(Gdx.files.internal("combat.wav"));
    Music ammoReport = Gdx.audio.newMusic(Gdx.files.internal("chatter_wounded.wav"));
    background.play();
    ammoReport.play();
    ammoReport.setOnCompletionListener(Music -> {
              background.stop();
              ammoReport.dispose();
              background.dispose();
            }
    );
    //Create BattleSituation
    BattleSituation battleSituation = BattleSituation.createBattleSituation(platoon, enemy);
    context.getBattleResolver().addBattleSituations(battleSituation);
  }

  @Override
  public void endContact(Contact contact) {

  }

  @Override
  public void preSolve(Contact contact, Manifold oldManifold) {

  }

  @Override
  public void postSolve(Contact contact, ContactImpulse impulse) {

  }
}
