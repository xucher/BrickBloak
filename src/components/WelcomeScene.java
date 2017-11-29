package components;

import application.Constant;
import application.StateMachine;
import application.StateMachine.GameState;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class WelcomeScene extends AnchorPane implements IXMLLayoutLoader {
	@FXML private Button btn_begin;
	@FXML private Button btn_end;
	
	public WelcomeScene() {
		loadXML(this, "WelcomeScene");
		
		setPrefWidth(Constant.GAME_SCENE_WIDTH);
		setPrefHeight(Constant.GAME_SCENE_HEIGHT);
		btn_begin.setOnMouseClicked((MouseEvent event) -> 
			StateMachine.getInstance().changeToState(GameState.BEGIN)
		);
		btn_begin.hoverProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue == true) {
				btn_begin.setScaleX(0.9);
				btn_begin.setScaleY(0.9);
			} else {
				btn_begin.setScaleX(1);
				btn_begin.setScaleY(1);
			}
		});
		btn_end.setOnMouseClicked((MouseEvent event) -> 
			StateMachine.getInstance().changeToState(GameState.EXIT)
		);
		btn_end.hoverProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue == true) {
				btn_end.setScaleX(0.9);
				btn_end.setScaleY(0.9);
			} else {
				btn_end.setScaleX(1);
				btn_end.setScaleY(1);
			}
		});
	}
}
