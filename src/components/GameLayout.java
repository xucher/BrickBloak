package components;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class GameLayout extends BorderPane implements IXMLLayoutLoader {
	@FXML private Pane scoreBoard;
	@FXML private Pane gameScene;
	@FXML private Pane stateBar;
	
	public GameLayout(Pane gameScene, Pane scoreBoard, Pane stateBar) {
		loadXML(this, "GameLayout");
		
		this.gameScene.getChildren().add(gameScene);
		this.scoreBoard.getChildren().add(scoreBoard);
		this.stateBar.getChildren().add(stateBar);
	}
}
