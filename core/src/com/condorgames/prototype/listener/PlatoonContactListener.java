package com.condorgames.prototype.listener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.condorgames.prototype.CondorAiPrototype;
import com.condorgames.prototype.audio.AudioManager;
import com.condorgames.prototype.entities.Platoon;
import com.condorgames.prototype.entities.SteerablePlatoonEntity;
import com.condorgames.prototype.battleresolver.BattleSituation;

public class PlatoonContactListener implements ContactListener {
  private CondorAiPrototype context;

  public PlatoonContactListener(CondorAiPrototype context) {

    this.context = context;
  }

  @Override
  public void beginContact(Contact contact) {
    Platoon activeContact = null;
    Platoon passiveContact = null;
    if (contact.getFixtureA().getUserData().equals("LOS")) {
      activeContact = (Platoon) contact.getFixtureA().getBody().getUserData();
      passiveContact = (Platoon) contact.getFixtureB().getBody().getUserData();
    } else {
      activeContact = (Platoon) contact.getFixtureB().getBody().getUserData();
      passiveContact = (Platoon) contact.getFixtureA().getBody().getUserData();
    }
    triggerEnemyContact(activeContact, passiveContact);
  }

  private void triggerEnemyContact(SteerablePlatoonEntity activeContact, SteerablePlatoonEntity passiveContact) {
    // Stop Entity
    activeContact.setTagged(true);
    activeContact.getBody().setLinearVelocity(0f, 0f);
    // Mock Health loss
    context.getTextFieldHealthFriendly().setText(String.valueOf(activeContact.getStrength()));
    AudioManager.playAssault();
    //Create BattleSituation
    BattleSituation battleSituation = BattleSituation.createBattleSituation(activeContact, passiveContact);
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
