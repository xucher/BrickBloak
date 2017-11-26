package objects;

import application.Constant;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Brick extends Rectangle {
	// ÑªÁ¿
	private int hp;
	private int score;
	public enum Style {
		RED, YELLOW
	}; 
	public Brick(Style style, int hp) {
		this.hp = hp;
		this.score = 15;
		
//		setAdditionalStyle(style);
		setDefaultStyle();
		setWidth(Constant.COMMON_BRICK_WIDTH);
		setHeight(Constant.COMMON_BRICK_HEIGTH);
	}
	
	public int getHp() { return hp; }
	public void setHp(int hp) { this.hp = hp; }
	public int getScore() { return score; }
	private void setDefaultStyle() {
		setFill(Color.BEIGE);
		Lighting lighting = new Lighting();
		lighting.setSurfaceScale(135);
		setEffect(lighting);
	}
	@SuppressWarnings("unused")
	private void setAdditionalStyle(Style style) {
		switch(style) {
			case RED:
				setStyle(getStyle() + "-fx-fill: red;");
				break;
			case YELLOW:
				setStyle(getStyle() + "-fx-fill: yellow;");
				break;
			default:
				break;
		}
	}
}
