package com.condorgames.prototype.ai;

import com.badlogic.gdx.ai.fma.FormationPattern;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;

public class TestFormationPattern implements FormationPattern<Vector2> {
  @Override
  public void setNumberOfSlots(int numberOfSlots) {
    // nothing yet
  }

  @Override
  public Location<Vector2> calculateSlotLocation(Location<Vector2> outLocation, int slotNumber) {

    // just test logic to see that something happens
    if (slotNumber == 0) {
      outLocation.setOrientation(2f);
    } else {
      outLocation.setOrientation(5f);
    }
    return outLocation;
  }

  @Override
  public boolean supportsSlots(int slotCount) {
    return true;
  }
}
