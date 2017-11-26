package components;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GameLayout extends BorderPane implements IXMLLayout {
	@FXML private VBox scoreBoard;
	@FXML private Pane gameScene;
	
	public GameLayout(Pane gameScene, Pane scoreBoard) {
		loadXML(this, "GameLayout");
		
		this.gameScene.getChildren().add(gameScene);
		this.scoreBoard.getChildren().add(scoreBoard);
	}
}
