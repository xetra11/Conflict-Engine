
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
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.condorgames.prototype.audio.AudioManager;
import com.condorgames.prototype.creator.EnemyCreator;
import com.condorgames.prototype.creator.PlatoonCreator;
import com.condorgames.prototype.creator.SensorCreator;
import com.condorgames.prototype.entities.*;
import com.condorgames.prototype.battleresolver.BattleResolver;
import com.condorgames.prototype.entities.platoon.SteerableSquadEntity;
import com.condorgames.prototype.helper.Helper;
import com.condorgames.prototype.helper.UILogger;
import com.condorgames.prototype.listener.PlatoonContactListener;

public class CondorAiPrototype extends ApplicationAdapter implements InputProcessor {
  // AI & Physics
  private Box2DDebugRenderer debugRenderer;
  private Matrix4 debugMatrix;
  private Sensor targetCrosshair;
  private World world;
  private OrthographicCamera camera;
  private UILogger uiLogger;

  private SteerableSquadEntity friendly;
  private SteerableSquadEntity enemyOne, enemyTwo, enemyThree;
  private Sensor moveTarget;

  //UI
  private Stage stage;
  private SpriteBatch spriteBatch;
  private Skin skin;

  private Label labelHealthFriendly, labelHealthEnemy, labelFPS;
  private TextField textFieldHealthFriendly, textFieldHealthEnemy, textFieldFPS;
  private TextArea textAreaLogger;
  private ScrollPane scrollPane;

  private Label labelMoraleFriendly;
  private TextField textFieldMoraleFriendly;
  private Label labelMoraleEnemy;
  private TextField textFieldMoraleEnemy;
  //Battle
  private BattleResolver battleResolver;
  private Label labelAmmoFriendly;
  private TextField textFieldAmmoFriendly;
  private TextField textFieldAmmoEnemy;
  private Label labelAmmoEnemy;

  @Override
  public void create() {
    createMeta();
    createEntities();
    createUI();
    setupAI();
  }

  @Override
  public void render() {
    camera.update();
    world.step(1f / 60f, 6, 2);
    battleResolver.resolve(Gdx.graphics.getDeltaTime(), false );
    friendly.update();
    updateUI();

    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    spriteBatch.begin();
    stage.act();
    stage.draw();
    spriteBatch.end();

    //render FPS
    textFieldFPS.setText(String.valueOf(1 / Gdx.graphics.getDeltaTime()));

    debugRenderer.render(world, debugMatrix);
  }

  private void updateUI() {
    textFieldHealthFriendly.setText(String.valueOf(friendly.getStrength()));
    textFieldHealthEnemy.setText(String.valueOf(enemyOne.getStrength()));

    textFieldMoraleFriendly.setText(String.valueOf(friendly.getMorale()));
    textFieldMoraleEnemy.setText(String.valueOf(enemyOne.getMorale()));

    textFieldAmmoFriendly.setText(String.valueOf(friendly.getAmmo()));
    textFieldAmmoEnemy.setText(String.valueOf(enemyOne.getAmmo()));
  }

  private void createUI() {
    BitmapFont bitmapFont = skin.get(BitmapFont.class);
    bitmapFont.getData().setScale(0.5f);
    createAmmoUI();
    createHealthUI();
    createMoraleUI();
    createFPSUI();
  }

  private void createUILoggerTextArea() {
    textAreaLogger = new TextArea("", skin, "default");

    scrollPane = new ScrollPane(textAreaLogger, skin, "default");
    scrollPane.setX(Gdx.graphics.getWidth() - 200);
    scrollPane.setY(Gdx.graphics.getHeight() - 225);
    scrollPane.setWidth(200);
    scrollPane.setHeight(200);

    scrollPane.addAction(Actions.sequence(Actions.delay(0.0f), new Action() {
      @Override
      public boolean act(float delta) {
        scrollPane.setScrollPercentY(1.0f);
        return true;
      }
    }));

    stage.addActor(scrollPane);
  }

  private void createFPSUI() {
    labelFPS = new Label("FPS:", skin, "default");
    textFieldFPS = new TextField("", skin, "default");
    textFieldFPS.setPosition(Gdx.graphics.getWidth() - 50f, Gdx.graphics.getHeight() - 25f);
    labelFPS.setPosition(Gdx.graphics.getWidth() - 100f, Gdx.graphics.getHeight() - 25f);
    stage.addActor(labelFPS);
    stage.addActor(textFieldFPS);
  }

  private void createAmmoUI() {
    //Friendly
    labelAmmoFriendly = new Label("Friendly.Ammo:", skin, "default");
    textFieldAmmoFriendly = new TextField("", skin, "default");
    textFieldAmmoFriendly.setPosition(Helper.getMeterToPixel(5.2f), Helper.getMeterToPixel(0f));
    labelAmmoFriendly.setPosition(Helper.getMeterToPixel(4f), Helper.getMeterToPixel(0f));
    stage.addActor(labelAmmoFriendly);
    stage.addActor(textFieldAmmoFriendly);

    //Enemy
    labelAmmoEnemy = new Label("Enemy.Ammo:", skin, "default");
    textFieldAmmoEnemy = new TextField("", skin, "default");
    textFieldAmmoEnemy.setPosition(Helper.getMeterToPixel(7.7f), Helper.getMeterToPixel(0f));
    labelAmmoEnemy.setPosition(Helper.getMeterToPixel(6.5f), Helper.getMeterToPixel(0f));
    stage.addActor(labelAmmoEnemy);
    stage.addActor(textFieldAmmoEnemy);
  }

  private void createMoraleUI() {
    //Friendly
    labelMoraleFriendly = new Label("Friendly.Morale:", skin, "default");
    textFieldMoraleFriendly = new TextField("", skin, "default");
    textFieldMoraleFriendly.setPosition(Helper.getMeterToPixel(1.5f), Helper.getMeterToPixel(0.3f));
    labelMoraleFriendly.setPosition(Helper.getMeterToPixel(0f), Helper.getMeterToPixel(0.3f));
    stage.addActor(labelMoraleFriendly);
    stage.addActor(textFieldMoraleFriendly);

    //Enemy
    labelMoraleEnemy = new Label("Enemy.Morale:", skin, "default");
    textFieldMoraleEnemy = new TextField("", skin, "default");
    textFieldMoraleEnemy.setPosition(Helper.getMeterToPixel(4f), Helper.getMeterToPixel(0.3f));
    labelMoraleEnemy.setPosition(Helper.getMeterToPixel(2.5f), Helper.getMeterToPixel(0.3f));
    stage.addActor(labelMoraleEnemy);
    stage.addActor(textFieldMoraleEnemy);
  }

  private void createHealthUI() {
    //Friendly
    labelHealthFriendly = new Label("Friendly.Strength:", skin, "default");
    textFieldHealthFriendly = new TextField("", skin, "default");
    textFieldHealthFriendly.setPosition(Helper.getMeterToPixel(1.5f), Helper.getMeterToPixel(0f));
    labelHealthFriendly.setPosition(Helper.getMeterToPixel(0f), Helper.getMeterToPixel(0f));
    stage.addActor(labelHealthFriendly);
    stage.addActor(textFieldHealthFriendly);

    //Enemy
    labelHealthEnemy = new Label("Enemy.Strength:", skin, "default");
    textFieldHealthEnemy = new TextField("", skin, "default");
    textFieldHealthEnemy.setPosition(Helper.getMeterToPixel(3.5f), Helper.getMeterToPixel(0f));
    labelHealthEnemy.setPosition(Helper.getMeterToPixel(2f), Helper.getMeterToPixel(0f));
    stage.addActor(labelHealthEnemy);
    stage.addActor(textFieldHealthEnemy);
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
//    enemyTwo = EnemyCreator.createSteerableEnemyEntity(world, new Vector2(6f, 5f));
//    enemyThree = EnemyCreator.createSteerableEnemyEntity(world, new Vector2(9f, 9f));
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


  public Label getLabelHealthFriendly() {
    return labelHealthFriendly;
  }

  public void setLabelHealthFriendly(Label labelHealthFriendly) {
    this.labelHealthFriendly = labelHealthFriendly;
  }

  public TextField getTextFieldHealthFriendly() {
    return textFieldHealthFriendly;
  }

  public void setTextFieldHealthFriendly(TextField textFieldHealthFriendly) {
    this.textFieldHealthFriendly = textFieldHealthFriendly;
  }

  public BattleResolver getBattleResolver() {
    return battleResolver;
  }
}
