package test;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import objects.Ball;
import objects.Brick;
import objects.Brick.Style;

public class TestGame extends Application{
	Group root;
	@Override
	public void start(Stage primaryStage) throws Exception {
		root = new Group();
		
//		addBall();
		addBrick();
		primaryStage.setTitle("BrickBlock");
		primaryStage.setScene(new Scene(root, 800, 600));
		primaryStage.show();
	}
	
	public void addBall() {
		Ball ball = new Ball();
		ball.setCenterX(200);
		ball.setCenterY(300);
		root.getChildren().add(ball);
	}
	public void addBrick() {
		Brick brick = new Brick(Style.RED, 1);
		brick.setStrokeWidth(5);
		brick.setStroke(Color.YELLOW);
		brick.setFill(Color.RED);
		Image image = new Image("/asserts/images/bg_brick.jpg", 200, 25, false, true, true);
		ImageInput imageInput = new ImageInput(image);
		brick.setEffect(imageInput);
		System.out.println(brick.getWidth());
		brick.setLayoutX(200);
		brick.setLayoutY(300);
//		TestBrick.getBrickInfo(brick);
		root.getChildren().add(brick);
	}
	public static void main(String[] args) { launch(args); }
}
