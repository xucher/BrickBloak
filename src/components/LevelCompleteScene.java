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
			label_title.setText("闯关失败");
			btn_nextLevel.setText("重新开始");
			btn_nextLevel.setOnMouseClicked(event -> {
				StateMachine.getInstance().changeToState(GameState.RESTART);
			});
		}
		
		
	}
}
