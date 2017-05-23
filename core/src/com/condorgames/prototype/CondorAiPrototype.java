
package com.condorgames.prototype;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.condorgames.prototype.audio.AudioManager;
import com.condorgames.prototype.creator.EnemyCreator;
import com.condorgames.prototype.creator.PlatoonCreator;
import com.condorgames.prototype.creator.SensorCreator;
import com.condorgames.prototype.entities.*;
import com.condorgames.prototype.battleresolver.BattleResolver;
import com.condorgames.prototype.listener.PlatoonContactListener;

public class CondorAiPrototype extends ApplicationAdapter implements InputProcessor {
  // AI & Physics
  private Box2DDebugRenderer debugRenderer;
  private Matrix4 debugMatrix;
  private SensorEntity targetCrosshair;
  private World world;
  private OrthographicCamera camera;
  private UILogger uiLogger;

  private SteerablePlatoonEntity friendly;
  private SteerablePlatoonEntity enemyOne, enemyTwo, enemyThree;
  private SensorEntity moveTarget;

  //UI
  private Stage stage;
  private SpriteBatch spriteBatch;
  private Skin skin;

  private Label labelHealth, labelFPS;
  private TextField textFieldHealth, textFieldFPS;
  private TextArea textAreaLogger;

  //Battle
  private BattleResolver battleResolver;


  @Override
  public void create() {
    createMeta();
    createEntities();
    createUI();
    setupAI();
  }

  private void createUI() {
    BitmapFont bitmapFont = skin.get(BitmapFont.class);
    bitmapFont.getData().setScale(0.5f);
    createHealthUI();
    createFPSUI();
  }

  private void createUILoggerTextArea() {
    textAreaLogger = new TextArea("", skin, "default");

    textAreaLogger.setX(Gdx.graphics.getWidth() - 200);
    textAreaLogger.setY(Gdx.graphics.getHeight() - 225);
    textAreaLogger.setWidth(200);
    textAreaLogger.setHeight(200);

    stage.addActor(textAreaLogger);
  }

  private void createFPSUI() {
    labelFPS = new Label("FPS:", skin, "default");
    textFieldFPS = new TextField("", skin, "default");
    textFieldFPS.setPosition(Gdx.graphics.getWidth() - 50f, Gdx.graphics.getHeight() - 25f);
    labelFPS.setPosition(Gdx.graphics.getWidth() - 100f, Gdx.graphics.getHeight() - 25f);
    stage.addActor(labelFPS);
    stage.addActor(textFieldFPS);
  }

  private void createHealthUI() {
    labelHealth = new Label("Strength:", skin, "default");
    textFieldHealth = new TextField(String.valueOf(friendly.getStrength()), skin, "default");
    textFieldHealth.setPosition(Helper.getMeterToPixel(1.1f), Helper.getMeterToPixel(0f));
    labelHealth.setPosition(Helper.getMeterToPixel(0f), Helper.getMeterToPixel(0f));
    stage.addActor(labelHealth);
    stage.addActor(textFieldHealth);
  }

  @Override
  public void render() {
    camera.update();
    world.step(1f / 60f, 6, 2);
    battleResolver.resolve(false, Gdx.graphics.getDeltaTime());
    friendly.update();


    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    spriteBatch.begin();
    stage.draw();
    spriteBatch.end();

    //render FPS
    textFieldFPS.setText(String.valueOf(1 / Gdx.graphics.getDeltaTime()));

    debugRenderer.render(world, debugMatrix);
  }

  @Override
  public void dispose() {
  }

  private void setupAI() {
    Arrive<Vector2> arrive = new Arrive<Vector2>(friendly, targetCrosshair);
    arrive.setArrivalTolerance(0.01f);
    arrive.setDecelerationRadius(0.5f);
    arrive.setTimeToTarget(0.1f);

    friendly.setSteeringBehavior(arrive);
  }

  private void createEntities() {
    friendly = PlatoonCreator.createSteerablePlatoonEntity(world, new Vector2(3f, 2f));
    enemyOne = EnemyCreator.createSteerableEnemyEntity(world, new Vector2(4f, 7f));
    enemyTwo = EnemyCreator.createSteerableEnemyEntity(world, new Vector2(6f, 5f));
    enemyThree = EnemyCreator.createSteerableEnemyEntity(world, new Vector2(9f, 9f));
    targetCrosshair = SensorCreator.createTargetCircleEntity(world, 0.05f);
  }

  private void createMeta() {
    AudioManager.loadAudio();
    spriteBatch = new SpriteBatch();
    stage = new Stage();
    skin = new Skin(Gdx.files.internal("uiskin.json"));
    debugRenderer = new Box2DDebugRenderer();
    world = new World(new Vector2(0f, 0f), false);
    camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    world.setContactListener(new PlatoonContactListener(this));
    debugMatrix = camera.combined.cpy().scale(Helper.FACTOR, Helper.FACTOR, 0f);
    battleResolver = new BattleResolver();
    createUILoggerTextArea();
    uiLogger = new UILogger(textAreaLogger);

    Gdx.app.setApplicationLogger(uiLogger);
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


  public Label getLabelHealth() {
    return labelHealth;
  }

  public void setLabelHealth(Label labelHealth) {
    this.labelHealth = labelHealth;
  }

  public TextField getTextFieldHealth() {
    return textFieldHealth;
  }

  public void setTextFieldHealth(TextField textFieldHealth) {
    this.textFieldHealth = textFieldHealth;
  }

  public BattleResolver getBattleResolver() {
    return battleResolver;
  }
}
