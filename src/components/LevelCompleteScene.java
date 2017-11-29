package components;

import application.StateMachine;
import application.StateMachine.GameState;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class LevelCompleteScene extends AnchorPane implements IXMLLayoutLoader {
	@FXML Button btn_nextLevel;
	@FXML Label label_title;
	
	public LevelCompleteScene(boolean isWin) {
		loadXML(this, "LevelCompleteScene");
		
		if (isWin) {
			btn_nextLevel.setOnMouseClicked(event -> {
				StateMachine.getInstance().changeToState(GameState.NEXT_LEVEL);
			});
		} else {
			label_title.setText("����ʧ��");
			btn_nextLevel.setText("���¿�ʼ");
			btn_nextLevel.setOnMouseClicked(event -> {
				StateMachine.getInstance().changeToState(GameState.RESTART);
			});
		}
		
		
	}
}
