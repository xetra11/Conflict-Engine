package com.condorgames.prototype.listener;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.condorgames.prototype.CondorAiPrototype;
import com.condorgames.prototype.audio.AudioManager;
import com.condorgames.prototype.battleresolver.ResolvableSituation;
import com.condorgames.prototype.entities.squad.Squad;
import com.condorgames.prototype.entities.squad.SteerableSquad;
import com.condorgames.prototype.battleresolver.BattleSituation;

public class PlatoonContactListener implements ContactListener {
  private CondorAiPrototype context;

  public PlatoonContactListener(CondorAiPrototype context) {
    this.context = context;
  }

  @Override
  public void beginContact(Contact contact) {
    Squad activeContact = null;
    Squad passiveContact = null;
    if (contact.getFixtureA().getUserData().equals("LOS")) {
      activeContact = (Squad) contact.getFixtureA().getBody().getUserData();
      passiveContact = (Squad) contact.getFixtureB().getBody().getUserData();
    } else {
      activeContact = (Squad) contact.getFixtureB().getBody().getUserData();
      passiveContact = (Squad) contact.getFixtureA().getBody().getUserData();
    }
    triggerEnemyContact(activeContact, passiveContact);
  }

  private void triggerEnemyContact(SteerableSquad activeContact, SteerableSquad passiveContact) {
    // Stop Entity
    activeContact.setTagged(true);
    activeContact.getBody().setLinearVelocity(0f, 0f);
    // Mock SoldierHealth loss
    context.getTextFieldHealthFriendly().setText(String.valueOf(activeContact.getStrength()));
    AudioManager.playAssault();
    //Create BattleSituation
    ResolvableSituation battleSituation = BattleSituation.createBattleSituation(activeContact, passiveContact);
    context.getBattleResolver().addSituations(battleSituation);
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
