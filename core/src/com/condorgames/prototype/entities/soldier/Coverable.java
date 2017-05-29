package com.condorgames.prototype.entities.soldier;

public interface Coverable {
  public enum Cover {
    NONE,
    LIGHT,
    MEDIUM,
    STRONG
  }

  Cover getCover();
  void setCover(Cover cover);
}
