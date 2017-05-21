package com.condorgames.prototype.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.condorgames.prototype.FilterCategories;
import com.condorgames.prototype.entities.BodyCreator;
import com.condorgames.prototype.entities.SensorEntity;

public abstract class EnemyCreator {

  public static SensorEntity createSteerableEnemyEntity(World world, Vector2 position) {
    final Body rectangleBody = BodyCreator.createRectangleBody(0.25f, 0.25f, position, world,
            BodyDef.BodyType.StaticBody,
            FilterCategories.ENEMY,
            FilterCategories.LOS);
    PolygonShape shape = new PolygonShape();
    return new SensorEntity(rectangleBody);
  }
}
