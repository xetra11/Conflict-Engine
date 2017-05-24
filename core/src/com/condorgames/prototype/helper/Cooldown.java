package com.condorgames.prototype.helper;


public class Cooldown {

  float timeToWaitFor;
  float resetTime;

  public Cooldown(float timeToWaitFor){
    this.timeToWaitFor = timeToWaitFor;
    this.resetTime = timeToWaitFor;
  }

  public boolean isDone(float deltaTime, CooldownFinishedListener cooldownFinishedListener){
    timeToWaitFor -= deltaTime;
    if(timeToWaitFor <= 0){
      cooldownFinishedListener.onFinish();
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



