package application;

import javafx.scene.shape.Rectangle;
import objects.Ball;
import objects.Brick;
import objects.MainBrick;

// ����С�����ײ�¼�
public class CollisionHandler {
	private Ball ball;
	public CollisionHandler(Ball ball) {
		this.ball = ball;
	}
	
	public boolean withMainBrick(MainBrick mainBrick) {
		if (rectangleTest(mainBrick)) {
			ball.reverseSpeedY();
			return true;
		}
		return false;
	}
	public boolean withGeneralBrick(Brick brick) {
		if (rectangleTest(brick)) {
			ball.reverseSpeedY();
			brick.setHp(brick.getHp() - ball.getPower());
			return true;
		}
		return false;
	}
	
	// ������ײ���
	private boolean rectangleTest(Rectangle rectangle) {
	// ���жϷ�����Խ���
			if (rectangle.getLayoutX() <= ball.getCenterX() + ball.getRadius() &&
					ball.getCenterX() - ball.getRadius() <= rectangle.getLayoutX() + rectangle.getWidth() &&
					rectangle.getLayoutY() <= ball.getCenterY() + ball.getRadius() && 
					ball.getCenterY() - ball.getRadius() <= rectangle.getLayoutY() + rectangle.getHeight()) {
				return true;
			}
			return false;
	}
}
