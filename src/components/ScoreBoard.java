package components;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import application.Constant;
import application.GameScene.DataProperties;

public class ScoreBoard extends VBox implements IXMLLayout {
	@FXML private Label label_level;
	@FXML private Label label_lives;
	@FXML private Label label_score;
	private IntegerProperty level = new SimpleIntegerProperty();
	private IntegerProperty lives = new SimpleIntegerProperty();
	private DoubleProperty score = new SimpleDoubleProperty();
	
	public ScoreBoard() {
		loadXML(this, "ScoreBoard");
		initLabelValue();
	}
	
	private void initLabelValue() {
		label_level.setText("1");
		label_lives.setText("" + Constant.INITIAL_LIVES);
		label_score.setText("0");
	}
	public void bindValue(DataProperties data) {
		this.level.bind(data.levelProperty);
		this.score.bind(data.scoreProperty);
		this.lives.bind(data.livesProperty);
		level.addListener((observable, oldValue, newValue) -> {
			label_level.setText("" + newValue.intValue());
		});
		score.addListener((observable, oldValue, newValue) -> {
			label_score.setText("" + newValue.intValue());
		});
		lives.addListener((observable, oldValue, newValue) -> {
			label_lives.setText("" + newValue.intValue());
		});
	}
}
