package com.condorgames.prototype;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class CondorAiPrototype extends ApplicationAdapter {
  public static final float PIXEL_TO_METERS = 100f;
  private SpriteBatch batch;
  private Box2DDebugRenderer debugRenderer;
  private Matrix4 debugMatrix;
  private Body testBody;
  private World world;
  private OrthographicCamera camera;

  @Override
  public void create() {
    createMeta();
    debugMatrix = camera.combined.cpy().scale(PIXEL_TO_METERS, PIXEL_TO_METERS, 0f);
    testBody = BodyFactory.createRectangleBody(world, BodyDef.BodyType.DynamicBody);
  }

  @Override
  public void render() {
    camera.update();
    world.step(1f/60f, 6, 2);

    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    debugRenderer.render(world, debugMatrix);
  }

  @Override
  public void dispose() {
  }

  private void createMeta() {
    batch = new SpriteBatch();
    debugRenderer = new Box2DDebugRenderer();
    world = new World(new Vector2(0f,0f), false);
    camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
  }
}
