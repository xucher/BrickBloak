package application;

import application.StateMachine.Attr;
import components.WelcomeScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BrickBlockGame extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		WelcomeScene welcomeScene = new WelcomeScene();
		primaryStage.setTitle("BrickBlock");
		primaryStage.setScene(new Scene(welcomeScene));
		primaryStage.show();
		
		StateMachine.getInstance().setAttr(Attr.STAGE, primaryStage);
	}
	
	public static void main(String[] args) { launch(args); }
}
