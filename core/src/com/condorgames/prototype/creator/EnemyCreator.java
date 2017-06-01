package com.condorgames.prototype.creator;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.condorgames.prototype.entities.squad.Squad;
import com.condorgames.prototype.entities.squad.SquadBase;
import com.condorgames.prototype.helper.FilterCategories;

public abstract class EnemyCreator {

  public static Squad createSteerableEnemyEntity(World world, Vector2 position) {
    final Body rectangleBody = BodyCreator.createRectangleBody(0.25f, 0.25f, position, world,
            BodyDef.BodyType.StaticBody,
            FilterCategories.ENEMY,
            FilterCategories.LOS);
    PolygonShape shape = new PolygonShape();
    Squad squad = new Squad(rectangleBody, SquadBase.Faction.ALLY);
    squad.getSoldiers().addAll(SquadSoldiersCreator.createAlliedRifleSquad());
    return squad;
  }
}
