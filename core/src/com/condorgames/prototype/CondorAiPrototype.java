
package com.condorgames.prototype;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ai.fma.Formation;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.ai.steer.behaviors.BlendedSteering;
import com.badlogic.gdx.ai.steer.behaviors.LookWhereYouAreGoing;
import com.badlogic.gdx.ai.steer.behaviors.ReachOrientation;
import com.badlogic.gdx.ai.steer.limiters.AngularLimiter;
import com.badlogic.gdx.ai.steer.limiters.NullLimiter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.condorgames.prototype.ai.TestFormationPattern;
import com.condorgames.prototype.audio.AudioManager;
import com.condorgames.prototype.battleresolver.BattleResolver;
import com.condorgames.prototype.creator.*;
import com.condorgames.prototype.entities.Sensor;
import com.condorgames.prototype.entities.platoon.Platoon;
import com.condorgames.prototype.entities.squad.Squad;
import com.condorgames.prototype.helper.Helper;
import com.condorgames.prototype.helper.UILogger;
import com.condorgames.prototype.listener.SquadContactListener;

public class CondorAiPrototype extends ApplicationAdapter implements InputProcessor {
  // AI & Physics
  private Box2DDebugRenderer debugRenderer;
  private Matrix4 debugMatrix;
  private Sensor targetCrosshair;
  private World world;
  private OrthographicCamera camera;
  private UILogger uiLogger;

  private Squad axisSquadOne, axisSquadTwo;
  private Squad enemyOne, enemyTwo, enemyThree;
  private Sensor moveTarget;

  private Formation<Vector2> formation;


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
  private Platoon platoon;

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
    battleResolver.resolve(Gdx.graphics.getDeltaTime(), false);
    axisSquadOne.update();
    axisSquadTwo.update();
    platoon.update();
    formation.updateSlots();

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
    textFieldHealthFriendly.setText(String.valueOf(axisSquadOne.getStrength()));
    textFieldHealthEnemy.setText(String.valueOf(enemyOne.getStrength()));

    textFieldMoraleFriendly.setText(String.valueOf(axisSquadOne.getMorale()));
    textFieldMoraleEnemy.setText(String.valueOf(enemyOne.getMorale()));

    textFieldAmmoFriendly.setText(String.valueOf(axisSquadOne.getAmmo()));
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

    formation = new Formation<Vector2>(platoon, new TestFormationPattern());
    formation.addMember(axisSquadOne);
    formation.addMember(axisSquadTwo);

    Arrive<Vector2> platoonFollowTarget = new Arrive<Vector2>(platoon, targetCrosshair);
    platoonFollowTarget.setArrivalTolerance(0.01f);
    platoonFollowTarget.setDecelerationRadius(0.5f);
    platoonFollowTarget.setTimeToTarget(0.1f);

    addFollowAndOrientateBehaviour(axisSquadOne);
    addFollowAndOrientateBehaviour(axisSquadTwo);

    platoon.setSteeringBehavior(platoonFollowTarget);
  }

  private void addFollowAndOrientateBehaviour(Squad squad) {
    Arrive<Vector2> arriveBehaviour = new Arrive<Vector2>(squad, platoon);
    arriveBehaviour.setArrivalTolerance(0.01f);
    arriveBehaviour.setDecelerationRadius(0.5f);
    arriveBehaviour.setTimeToTarget(0.1f);

    ReachOrientation<Vector2> reachOrientation = new ReachOrientation<Vector2>(squad, platoon);
    reachOrientation
            .setLimiter(new AngularLimiter(100, 20)) //
            .setTimeToTarget(0.1f) //
            .setAlignTolerance(0.001f) //
            .setDecelerationRadius(MathUtils.PI);

    LookWhereYouAreGoing<Vector2> lookWhereYouAreGoing = new LookWhereYouAreGoing<>(squad);
    lookWhereYouAreGoing
            .setLimiter(new AngularLimiter(100, 20)) //
            .setTimeToTarget(0.1f) //
            .setAlignTolerance(0.001f) //
            .setDecelerationRadius(MathUtils.PI);

    BlendedSteering<Vector2> reachPositionAndOrientationSB = new BlendedSteering<Vector2>(squad)
            .setLimiter(NullLimiter.NEUTRAL_LIMITER) //
            .add(arriveBehaviour, 1f) //
            .add(reachOrientation, 1f) //
            .add(lookWhereYouAreGoing, 1f);

    squad.setSteeringBehavior(reachPositionAndOrientationSB);
  }

  private void createEntities() {
    platoon = PlatoonCreator.createPlatoon(world, new Vector2(2f, 2f));
    axisSquadOne = SquadCreator.createSteerableSquadEntity(world, new Vector2(3f, 2f));
    axisSquadTwo = SquadCreator.createSteerableSquadEntity(world, new Vector2(4f, 2f));
    enemyOne = EnemyCreator.createSteerableEnemyEntity(world, new Vector2(4f, 7f));
//    enemyTwo = EnemyCreator.createSteerableEnemyEntity(world, new Vector2(6f, 5f));
//    enemyThree = EnemyCreator.createSteerableEnemyEntity(world, new Vector2(9f, 9f));

    platoon.addSquad((Squad) axisSquadOne);
    platoon.addSquad((Squad) axisSquadTwo);

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
    world.setContactListener(new SquadContactListener(this));
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
