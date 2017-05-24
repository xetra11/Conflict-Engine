package com.condorgames.prototype.helper;

public interface FilterCategories {

  public static short COMMON_BODIES = 0x1;
  public static short ENEMY = 0x1 << 1;
  public static short ALLY = 0x1 << 2;
  public static short LOS = 0x1 << 3;
}
