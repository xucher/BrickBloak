package application;

import java.util.concurrent.CopyOnWriteArrayList;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import objects.Ball;
import objects.BaseObject;
import objects.Brick;
import objects.MainBrick;

public class GameScene extends Parent{
	private int width;
	private int height;
	private Rectangle background;
	private MainBrick mainBrick = new MainBrick();
	private Ball ball = new Ball(15, 15, 15);
	
	private Timeline timeline;
	private KeyFrame keyFrame;
	private CopyOnWriteArrayList<Brick> bricks = new CopyOnWriteArrayList<>();
	
	public GameScene(int width, int height) {
		this.width = width;
		this.height =height;
		initGameObjects();
		initLevel();
		initTimeLine();
	}

	private void initGameObjects() {
		background = new Rectangle(0, 0, width, height);
		background.setOnMouseMoved(event -> mainBrick.onMouseMove(event));
		background.setFill(Color.BLACK);
		
		mainBrick.setX(0.0D);
		mainBrick.setY(height - mainBrick.getHeight());
		
		ball.setX((mainBrick.getWidth() - ball.getWidth())/2);
		ball.setY(height - mainBrick.getHeight() - ball.getHeight());
		
		getChildren().add(background);
		getChildren().add(mainBrick);
		getChildren().add(ball);
	}
	
	private void initTimeLine() {
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		keyFrame = new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				 ball.moveX(ball.getSpeedX());
				 ball.moveY(ball.getSpeedY());
				
				// 到达左右边界
				if (ball.getX() <= 0.0D ||
						ball.getX() >= BrickBlockGame.WIDTH - ball.getWidth())
					ball.setSpeedX(-ball.getSpeedX());
				
				// 到达上下边界
				if (ball.getY() <= 0.0D ||
						ball.getY() >= BrickBlockGame.HEIGHT - ball.getHeight())
					ball.setSpeedY(-ball.getSpeedY());
				
			if (ball.isCollisionWith(mainBrick))
				ball.setSpeedY(-ball.getSpeedY());
				for (Brick brick: bricks) {
					if (ball.isCollisionWith(brick)) {
						ball.setSpeedY(-ball.getSpeedY());
						if (bricks.size() > 6) {
							brick.setHp(brick.getHp() - 1);
							if (brick.getHp() <= 0) {
								destroyObject(brick);
							}
						}
						break;
					}
				}
			}
		});
		timeline.getKeyFrames().add(keyFrame);
		timeline.play();
	}
	
	// 参数不能改变值
	private void destroyObject(final BaseObject brick) {
		FadeTransition fade = new FadeTransition(Duration.millis(200.0D), brick);  
    fade.setFromValue(1.0D);  
    fade.setToValue(0.0D);  
    fade.setOnFinished(new EventHandler<ActionEvent>() {  
        public void handle(ActionEvent t) {  
            getChildren().remove(brick);  
        }  
    });  
    this.bricks.remove((Brick) brick);  
    fade.play();
	}
	
	private void initLevel() { LeverLoader.load(this, 1); }
	
	public void addChild(Parent parent) {
		getChildren().add(parent);
	}
	
	public CopyOnWriteArrayList<Brick> getBricks() {  
    return this.bricks;  
	} 
}
