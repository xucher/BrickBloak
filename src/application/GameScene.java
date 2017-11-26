package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import application.StateMachine.State;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import objects.Ball;
import objects.Brick;
import objects.MainBrick;

public class GameScene extends Pane {
	private MainBrick mainBrick = new MainBrick();
	private Ball ball = new Ball();
	private DataProperties dataProperties;
	private Timeline timeline;
	private LevelLoader levelLoader;
	private CollisionHandler collisionHandler;
	private List<Brick> bricks = Collections.synchronizedList(new ArrayList<>());
	
	public GameScene() {
		setPrefWidth(Constant.GAME_SCENE_WIDTH);
		setPrefHeight(Constant.GAME_SCENE_HEIGHT);
		setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, null, null)));
		
		dataProperties = new DataProperties();
	}
	public void loadGame() {
		initGameObjects();
		initGameHelper();
		// ³õÊ¼»¯×©¿é
		levelLoader.load(1);
		initTimeLine();
		setOnMouseClicked(event -> startTimeLine());
	}
	public class DataProperties {
		public DoubleProperty scoreProperty = new SimpleDoubleProperty(0);
		public IntegerProperty levelProperty = new SimpleIntegerProperty(1);
		public IntegerProperty livesProperty = new SimpleIntegerProperty(Constant.INITIAL_LIVES);
		
		private DataProperties() { }
		public Double getScore() { return scoreProperty.get(); }
		public void addScore(Double score) { scoreProperty.set(getScore() + score);}
		public int getLevel() { return levelProperty.get(); }
		public void nextLevel() {
			int nowLevel = levelProperty.get();
			if (nowLevel >= Constant.MAX_LEVEL) {
				
			} else {
				levelProperty.set(levelProperty.get() + 1);
			}
		}
		public void loseLive() {
			int leftLives = livesProperty.get();
			if (leftLives <= 0) {
				StateMachine.getInstance().changeToState(State.GAME_OVER);
			} else {
				livesProperty.set(leftLives - 1);
			}
		}
	}
	public DataProperties getData() { return dataProperties; }

	private void initGameObjects() {
		getChildren().add(mainBrick);
		getChildren().add(ball);
		initObjectLayout();
	}
	private void initGameHelper() {
		levelLoader = new LevelLoader(this);
		collisionHandler = new CollisionHandler(ball);
	}
	private void initObjectLayout() {
		mainBrick.setLayoutX((getWidth() - mainBrick.getWidth())/ 2);
		mainBrick.setLayoutY(getHeight() - mainBrick.getHeight());
		ball.setCenterX(getWidth() / 2);
		ball.setCenterY(getHeight() - 5 * mainBrick.getHeight());
	}
	private void initTimeLine() {
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame keyFrame = new KeyFrame(Duration.millis(1), (event) -> {
			if (bricks.size() == 0) enterNextLevel();
			ball.move();
			handleCollision();
		});
		timeline.getKeyFrames().add(keyFrame);
	}
	
	private void handleCollision() {
		collisionHandler.withMainBrick(mainBrick);
		
		for (Brick brick: bricks) {
			if (collisionHandler.withGeneralBrick(brick)) {
				if (brick.getHp() <= 0) {
					dataProperties.addScore((double) brick.getScore());
					destroyObject(brick);
				}
				break;
			}
		}
	}
	private void destroyObject(final Brick brick) {
		FadeTransition fade = new FadeTransition(Duration.millis(100.0D), brick);  
    fade.setFromValue(1.0D);  
    fade.setToValue(0.0D);
    fade.setOnFinished((event) -> getChildren().remove(brick));  
    bricks.remove(brick);  
    fade.play();
	}
	
	private void startTimeLine() { 
		setOnMouseMoved(event -> mainBrick.onMouseMove(event));
		timeline.play();
	}
	public void pauseTimeLine() { timeline.pause(); }
	public void resumeTimeLine() { timeline.play(); }
	
	public void enterNextLevel() { 
		pauseTimeLine();
		dataProperties.nextLevel();
		
		initObjectLayout();
		levelLoader.load(dataProperties.getLevel()); 
	}
	
	public void addChild(Node node) { getChildren().add(node); }
	public List<Brick> getBricks() { return bricks; }
}
