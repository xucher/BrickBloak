package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BrickBlockGame extends Application{
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		GameScene root = new GameScene(WIDTH, HEIGHT);
		primaryStage.setTitle("BrickBlock");
		primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
		primaryStage.show();
	}
	public static void main(String[] args) { launch(args); }
}
