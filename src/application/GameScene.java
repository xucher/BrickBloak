package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import application.CollisionChecker.CollisionType;
import application.StateMachine.GameState;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import objects.Ball;
import objects.Brick;
import objects.MainBrick;
import plugins.TrackIndicator;
import objects.Brick.Type;

public class GameScene extends Pane {
	private MainBrick mainBrick = new MainBrick();
	private Ball ball = new Ball();
	private DataProperties dataProperties;
	private Timeline timeline;
	private LevelLoader levelLoader;
	private List<Brick> bricks = Collections.synchronizedList(new ArrayList<>());
	
	public GameScene() {
		setPrefWidth(Constant.GAME_SCENE_WIDTH);
		setPrefHeight(Constant.GAME_SCENE_HEIGHT);
		
		dataProperties = new DataProperties();
	}
	public void loadGame() {
		initGameObjects();
		initGameHelper();
		// 初始化砖块
		levelLoader.load(1);
		initTimeLine();
		setOnMouseClicked(event -> startTimeLine());
	}
	public class DataProperties {
		public DoubleProperty scoreProperty = new SimpleDoubleProperty(0);
		public IntegerProperty levelProperty = new SimpleIntegerProperty(1);
		public IntegerProperty lifeProperty = new SimpleIntegerProperty(Constant.INITIAL_LIVES);
		public DoubleProperty lifeProgressProperty = new SimpleDoubleProperty(1.0);
		
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
			int leftLives = lifeProperty.get();
			if (leftLives <= 0) {
				showSummary(false);
			} else {
				lifeProperty.set(leftLives - 1);
				lifeProgressProperty.set((leftLives - 1.0) / Constant.INITIAL_LIVES);
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
	}
	private void initObjectLayout() {
		mainBrick.setLayoutX((getWidth() - mainBrick.getWidth())/ 2);
		mainBrick.setLayoutY(getHeight() - mainBrick.getHeight() - 20);
		ball.setCenterX(getWidth() / 2);
		ball.setCenterY(getHeight() - 5 * mainBrick.getHeight());
	}
	private void initTimeLine() {
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame keyFrame = new KeyFrame(Duration.millis(2), (event) -> {
			ball.move();
			checkIfLoseLife();
			handleCollision();
			if (bricks.size() <= 0) // TODO 延时弹出
				showSummary(true);
		});
		timeline.getKeyFrames().add(keyFrame);
	}
	
	private void checkIfLoseLife() {
		if (ball.getBoundsInParent().getMaxY() >= getHeight())
			dataProperties.loseLive();
	}
	private void handleCollision() {
		CollisionType type = ball.getCollisionType(mainBrick);
		if (type != CollisionType.NO)
			changeSpeed(type);
		
		for (Brick brick: bricks) {
			type = ball.getCollisionType(brick);
			if (type != CollisionType.NO) {
				changeSpeed(type);
				brick.setHp(brick.getHp() - ball.getPower());
				if (brick.getHp() <= 0) {
					dataProperties.addScore((double) brick.getScore());
					handleSpecialBrick(brick);
					destroyObject(brick);
				}
				break;
			}
		}
	}
	private void changeSpeed(CollisionType type) {
		switch(type) {
			case HORIZON: ball.reverseSpeedX();break;
			case VERTICAL: ball.reverseSpeedY();break;
			case POINT: ball.reverseSpeedX();ball.reverseSpeedY();break;
			default: break;
		}
	}
	private void handleSpecialBrick(Brick brick) {
		if (brick.getType() == Type.BLUE) {
			new TrackIndicator(this, ball).showTracker();
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
	
	public void restartGame() {
		bricks.clear();
		levelLoader.load(dataProperties.getLevel());
		
		initObjectLayout();
		ball.reset();
	}
	public void showSummary(boolean isWin) {
		if (isWin) {
			StateMachine.getInstance().changeToState(GameState.LEVEL_COMPLETE);
		} else {
			StateMachine.getInstance().changeToState(GameState.GAME_OVER);
		}
		pauseTimeLine();
		initObjectLayout();
		ball.reset();
	}
	public void enterNextLevel() {
		dataProperties.loseLive();
		dataProperties.nextLevel();
		levelLoader.load(dataProperties.getLevel()); 
	}
	
	public void addChild(Node node) { getChildren().add(node); }
	public List<Brick> getBricks() { return bricks; }
}
