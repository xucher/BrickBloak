package components;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public interface IXMLLayout {
	default void loadXML(Pane pane, String xml) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/asserts/fxml/" + xml + ".fxml"));
		fxmlLoader.setRoot(pane);
		fxmlLoader.setController(pane);
		
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
