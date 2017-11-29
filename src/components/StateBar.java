package components;

import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;

public class StateBar extends AnchorPane implements IXMLLayoutLoader {
	@FXML private Label label_shield_value;
	@FXML private ProgressBar life_bar;
	@FXML private Label label_return;
//	private IntegerProperty shield = new SimpleIntegerProperty();
	
	public StateBar() {
		loadXML(this, "StateBar");
		// TODO shield绑定
		label_return.setOnMouseClicked(event -> {
			// TODO 游戏界面返回事件
		});
	}
	
	public void bindValue(DoubleProperty life) {
		life_bar.progressProperty().bind(life);
	}
}
