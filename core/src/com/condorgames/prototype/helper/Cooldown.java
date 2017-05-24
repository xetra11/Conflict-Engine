package com.condorgames.prototype.helper;

public class Cooldown {

  float timeToWaitFor;

  public Cooldown(float timeToWaitFor){
    this.timeToWaitFor = timeToWaitFor;
  }

  public boolean isDone(float deltaTime){
    timeToWaitFor -= deltaTime;
    return timeToWaitFor <= 0;
  }

}
