package test;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import objects.Ball;

public class TestBall {
	
	public static void getBallInfo(Ball ball) {
//		System.out.println("center(x, y) = (" + ball.getCenterX() + ", " + ball.getCenterY() + ")");
		
		
//		System.out.println("Radius: " + ball.getRadius());
//		System.out.println("speedX: " + ball.getSpeedX());
//		System.out.println("speedY: " + ball.getSpeedY());
		System.out.println("layout(x, y) = (" + ball.getLayoutX() + ", " + ball.getLayoutY() + ")");
		Bounds bounds = ball.getBoundsInParent();
		System.out.println("minx: " + bounds.getWidth());
		System.out.println("layoutx: " + ball.getLayoutX());
		System.out.println("scalex: " + ball.getScaleX());
		System.out.println("translatex: " + ball.getTranslateX());
	}
	public static void main(String[] args) {
		Pane pane = new Pane();
		pane.setPrefWidth(900);
		pane.setPrefHeight(500);
		
		Ball ball = new Ball();
		pane.getChildren().add(ball);
		ball.setCenterX(15);
		getBallInfo(ball);
		ball.setCenterX(150);
		getBallInfo(ball);
	}
}
