package test;

import com.sun.javafx.geom.Point2D;

import application.GameScene;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import objects.Ball;
import objects.Brick;
import objects.Brick.Type;
import plugins.TrackIndicator;

public class TestGame extends Application{
	Group root;
	@Override
	public void start(Stage primaryStage) throws Exception {
		root = new Group();
//		addBall();
		addBrick();
//		addPolyline();
//		GameScene gameScene = new GameScene();
//		System.out.println(gameScene.getPrefWidth());
//		System.out.println(gameScene.getPrefHeight());
//		Ball ball = new Ball();
//		ball.setCenterX(200);
//		ball.setCenterY(300);
//		TrackIndicator tracker = new TrackIndicator(gameScene, ball);
//		
//		gameScene.getChildren().add(tracker);
//		tracker.updateTrack();
		
		
		
		primaryStage.setTitle("BrickBlock");
		primaryStage.setScene(new Scene(root, 800, 600));
		primaryStage.show();
	}
	
	public void addPolyline() {
		Polyline line = new Polyline();
		line.setFill(Color.BLACK);
		Point2D point = new Point2D(15, 15);
		line.getPoints().addAll(new Double[] {
				15.0, 15.0,
				35.0, 60.0
		});
		root.getChildren().add(line);
	}
	
	
	
	
	
	
	
	
	
	public void addBall() {
		Ball ball = new Ball();
		ball.setCenterX(200);
		ball.setCenterY(300);
		Bounds bounds = ball.getBoundsInParent();
		System.out.println(bounds.getMinX());
		System.out.println(bounds.getMinY());
		System.out.println(bounds.getMaxX());
		System.out.println(bounds.getMaxY());
		root.getChildren().add(ball);
	}
	public void addBrick() {
		Brick brick = new Brick(Type.RED, 1);
//		brick.setWidth(200);
//		brick.setHeight(50);
		brick.setLayoutX(200);
		brick.setLayoutY(300);
		TestBrick.getBrickInfo(brick);
		Bounds bounds = brick.getBoundsInParent();
		System.out.println(bounds.getMinX());
		root.getChildren().add(brick);
	}
	public static void main(String[] args) { launch(args); }
}
