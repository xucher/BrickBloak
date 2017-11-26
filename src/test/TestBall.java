package test;

import objects.Ball;

public class TestBall {
	public static void getBallInfo(Ball ball) {
		System.out.println("layout(x, y) = (" + ball.getLayoutX() + ", " + ball.getLayoutY() + ")");
		System.out.println("center(x, y) = (" + ball.getCenterX() + ", " + ball.getCenterY() + ")");
		System.out.println("Radius: " + ball.getRadius());
		System.out.println("speedX: " + ball.getSpeedX());
		System.out.println("speedY: " + ball.getSpeedY());
	}

}
