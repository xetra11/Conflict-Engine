package com.condorgames.prototype.creator;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.condorgames.prototype.helper.FilterCategories;
import com.condorgames.prototype.entities.platoon.Platoon;
import com.condorgames.prototype.entities.platoon.PlatoonEntityBase;
import com.condorgames.prototype.entities.platoon.SteerablePlatoonEntity;

public abstract class EnemyCreator {

  public static SteerablePlatoonEntity createSteerableEnemyEntity(World world, Vector2 position) {
    final Body rectangleBody = BodyCreator.createRectangleBody(0.25f, 0.25f, position, world,
            BodyDef.BodyType.StaticBody,
            FilterCategories.ENEMY,
            FilterCategories.LOS);
    PolygonShape shape = new PolygonShape();
    return new Platoon(rectangleBody, PlatoonEntityBase.Faction.ALLY);
  }
}
