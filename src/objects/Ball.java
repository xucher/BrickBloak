package objects;

import application.CollisionChecker;
import application.CollisionChecker.CollisionType;
import application.Constant;
import javafx.geometry.Bounds;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

@SuppressWarnings("unused")
public class Ball extends Circle {
	private CollisionChecker collisionChecker;
	private double speedX = 0.5;
	private double speedY = -0.5;
	private int power = 1;
	
	public Ball() {
		super(Constant.BALL_RADIUS);
		collisionChecker = new CollisionChecker(this);
//		Image image = new Image("/asserts/images/ball.png", 30, 30, false, false,true);
//		ImageInput imageInput = new ImageInput(image);
//		setEffect(imageInput);
	}

	public CollisionType getCollisionType(Rectangle rectangle) { return collisionChecker.withRectangle(rectangle); }
	public double getSpeedX() { return speedX; }
	public void setSpeedX(double speedX) { this.speedX = speedX; }
	public double getSpeedY() { return speedY; }
	public void setSpeedY(double speedY) { this.speedY = speedY; }
	public int getPower() { return power;} 
	public void reset() {
		speedX = 0.5;
		speedY = -0.5;
	}
	public void move() {
		// 左右边界
		if (getCenterX() <= getRadius() ||
				getCenterX() >= Constant.GAME_SCENE_WIDTH - getRadius())
			speedX = -speedX;
		// 上下边界
		if (getCenterY() <= getRadius() ||
				getCenterY() >= Constant.GAME_SCENE_HEIGHT -getRadius())
			speedY = -speedY;
		
		 setCenterX(getCenterX() + speedX);
		 setCenterY(getCenterY() + speedY);
	}
	public void reverseSpeedX() { speedX = -speedX; }
	public void reverseSpeedY() { speedY = -speedY; }
}