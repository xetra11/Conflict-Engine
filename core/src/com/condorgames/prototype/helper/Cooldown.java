package com.condorgames.prototype.helper;


public class Cooldown {

  private final boolean ticking;
  float timeToWaitFor;
  float resetTime;

  public Cooldown(float timeToWaitFor){
    this.timeToWaitFor = timeToWaitFor;
    this.resetTime = timeToWaitFor;
    this.ticking = false;
  }

  public Cooldown(float timeToWaitFor, boolean ticking){
    this.timeToWaitFor = timeToWaitFor;
    this.resetTime = timeToWaitFor;
    this.ticking = ticking;
  }


  public boolean isDone(float deltaTime, CooldownFinishedListener cooldownFinishedListener){
    timeToWaitFor -= deltaTime;
    if(timeToWaitFor <= 0){
      cooldownFinishedListener.onFinish();
      if (ticking) {
        reset();
      }
      return true;
    }
    return false;
  }

  public void resetWith(float newCooldownTime){
    timeToWaitFor = newCooldownTime;
  }

  public void reset(){
    timeToWaitFor = resetTime;
  }

  @FunctionalInterface
  public interface CooldownFinishedListener {
    void onFinish();
  }
}



