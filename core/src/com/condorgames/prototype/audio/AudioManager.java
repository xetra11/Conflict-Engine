package com.condorgames.prototype.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public abstract class AudioManager {
  private static Music chatterWounded, chatterAmmo, chatterReloading, chatterReloading2;
  private static Music backgroundBattle;
  private static Music assault;
  private static Music casualty;
  private static Music takingFire1;
  private static Music takingFire2;


  public static void loadAudio() {
    chatterAmmo = Gdx.audio.newMusic(Gdx.files.internal("chatter_ammo.wav"));
    chatterWounded = Gdx.audio.newMusic(Gdx.files.internal("chatter_wounded.wav"));
    chatterReloading = Gdx.audio.newMusic(Gdx.files.internal("german_reload_0.wav"));
    chatterReloading2 = Gdx.audio.newMusic(Gdx.files.internal("german_reload_1.wav"));

    casualty = Gdx.audio.newMusic(Gdx.files.internal("german_casualty_3.wav"));
    assault = Gdx.audio.newMusic(Gdx.files.internal("german_move_assault_5.wav"));
    takingFire1 = Gdx.audio.newMusic(Gdx.files.internal("german_taking_fire_12.wav"));
    takingFire2 = Gdx.audio.newMusic(Gdx.files.internal("german_taking_fire_7.wav"));

    backgroundBattle = Gdx.audio.newMusic(Gdx.files.internal("combat.wav"));
  }

  public static void playAssault() {
    assault.play();
  }

  public static void playCasualty() {
    backgroundBattle.play();
    casualty.play();
  }

  public static void playTakingFire() {
    backgroundBattle.play();
    takingFire1.play();
  }

  public static void playWoundedWithBackground() {
    backgroundBattle.play();
    chatterWounded.play();
    chatterWounded.setOnCompletionListener(music -> backgroundBattle.stop());
  }

  public static void playAmmoWithBackground() {
    backgroundBattle.play();
    chatterAmmo.play();
    chatterAmmo.setOnCompletionListener(music -> backgroundBattle.stop());
  }

  public static void playReloadingWithBackground() {
    backgroundBattle.play();
    chatterReloading.play();
    chatterReloading.setOnCompletionListener(music -> backgroundBattle.stop());
  }

  public static void playReloading2WithBackground() {
    backgroundBattle.play();
    chatterReloading2.play();
    chatterReloading2.setOnCompletionListener(music -> backgroundBattle.stop());
  }
}
