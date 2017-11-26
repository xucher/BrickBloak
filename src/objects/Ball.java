package objects;

import application.Constant;
import javafx.scene.shape.Circle;

public class Ball extends Circle {
	private double speedX = 0.5;
	private double speedY = -0.5;
	private int power = 1;
	
	public Ball() {
		super(Constant.BALL_RADIUS);
	}

	public double getSpeedX() { return speedX; }
	public void setSpeedX(double speedX) { this.speedX = speedX; }
	public double getSpeedY() { return speedY; }
	public void setSpeedY(double speedY) { this.speedY = speedY; }
	public int getPower() { return power;} 
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
	public void reverseSpeedY() { speedY = -speedY; }
}