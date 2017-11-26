package components;

import application.Constant;
import application.StateMachine;
import application.StateMachine.State;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class WelcomeScene extends AnchorPane implements IXMLLayout {
	@FXML private Button btn_begin;
	
	public WelcomeScene() {
		loadXML(this, "WelcomeScene");
		
		setPrefWidth(Constant.GAME_SCENE_WIDTH);
		setPrefHeight(Constant.GAME_SCENE_HEIGHT);
		
		btn_begin.setOnMouseClicked((MouseEvent event) -> 
			StateMachine.getInstance().changeToState(State.BEGIN)
		);
	}
}
