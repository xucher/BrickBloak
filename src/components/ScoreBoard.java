package components;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import application.GameScene.DataProperties;

public class ScoreBoard extends AnchorPane implements IXMLLayoutLoader {
	@FXML private Label label_level;
	@FXML private Label label_score;
	private IntegerProperty level = new SimpleIntegerProperty();
	private DoubleProperty score = new SimpleDoubleProperty();
	
	public ScoreBoard() {
		loadXML(this, "ScoreBoard");
		initLabelValue();
	}
	
	private void initLabelValue() {
		label_level.setText("1");
		label_score.setText("0");
	}
	public void bindValue(DataProperties data) {
		this.level.bind(data.levelProperty);
		this.score.bind(data.scoreProperty);
		level.addListener((observable, oldValue, newValue) -> {
			label_level.setText("" + newValue.intValue());
		});
		score.addListener((observable, oldValue, newValue) -> {
			label_score.setText("" + newValue.intValue());
		});
	}
}
