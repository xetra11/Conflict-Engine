
package com.condorgames.prototype;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.condorgames.prototype.entities.*;

public class CondorAiPrototype extends ApplicationAdapter implements InputProcessor{
  public static final float PIXEL_TO_METERS = 100f;
  private Box2DDebugRenderer debugRenderer;
  private Matrix4 debugMatrix;
  private SensorEntity targetCrosshair;
  private World world;
  private OrthographicCamera camera;

  private SteerablePlatoonEntity friendly;
  private SensorEntity enemy;
  private SensorEntity moveTarget;

  @Override
  public void create() {
    createMeta();
    friendly = PlatoonCreator.createSteerablePlatoonEntity(world, new Vector2(2f, 1f));
    enemy = EnemyCreator.createSteerableEnemyEntity(world, new Vector2(2.5f, 4f));
    targetCrosshair = SensorCreator.createTargetCircleEntity(world, 0.05f);

    Arrive<Vector2> arrive = new Arrive<Vector2>(friendly, targetCrosshair);
    arrive.setArrivalTolerance(0.01f);
    arrive.setDecelerationRadius(0.5f);
    arrive.setTimeToTarget(0.1f);

    friendly.setSteeringBehavior(arrive);
  }

  @Override
  public void render() {
    camera.update();
    world.step(1f/60f, 6, 2);
    friendly.update();

    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    debugRenderer.render(world, debugMatrix);
  }

  @Override
  public void dispose() {
  }

  private void createMeta() {
    debugRenderer = new Box2DDebugRenderer();
    world = new World(new Vector2(0f,0f), false);
    camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    debugMatrix = camera.combined.cpy().scale(PIXEL_TO_METERS, PIXEL_TO_METERS, 0f);

    Gdx.input.setInputProcessor(this);
  }

  //<editor-fold desc="InputProcessing">
  @Override
  public boolean keyDown(int keycode) {
    return false;
  }

  @Override
  public boolean keyUp(int keycode) {
    return false;
  }

  @Override
  public boolean keyTyped(char character) {
    return false;
  }

  @Override
  public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    Vector3 vector3 = new Vector3(screenX, screenY, 0);
    camera.unproject(vector3);
    Helper.setClickedPositionForBox2D(vector3.x, vector3.y, targetCrosshair.getBody());
    return true;
  }

  @Override
  public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    return false;
  }

  @Override
  public boolean touchDragged(int screenX, int screenY, int pointer) {
    return false;
  }

  @Override
  public boolean mouseMoved(int screenX, int screenY) {
    return false;
  }

  @Override
  public boolean scrolled(int amount) {
    return false;
  }
  //</editor-fold>
}
