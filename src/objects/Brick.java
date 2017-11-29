package objects;

import application.Constant;
import javafx.scene.effect.ImageInput;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

@SuppressWarnings("unused")
public class Brick extends Rectangle {
	// ÑªÁ¿
	private int hp;
	private int score;
	private Type type;
	public enum Type {
		RED, YELLOW, BLUE, GENERAL
	}; 
	public Brick(Type type, int hp) {
		this.hp = hp;
		this.score = 15;
		this.type = type;
		setDefaultStyle();
		if (type != Type.GENERAL)
			setAdditionalStyle(type);
	}
	
	public int getHp() { return hp; }
	public void setHp(int hp) { this.hp = hp; }
	public int getScore() { return score; }
	public Type getType() { return type;}
	private void setDefaultStyle() {
		setWidth(Constant.COMMON_BRICK_WIDTH);
		setHeight(Constant.COMMON_BRICK_HEIGTH);
		setFill(Color.BEIGE);	
		Light.Distant light = new Light.Distant();
		light.setElevation(120);
		Lighting lighting = new Lighting(light);
		lighting.setSurfaceScale(2);
		setEffect(lighting);
	}
	
	private void setAdditionalStyle(Type type) {
		switch(type) {
			case RED:
				setStyle(getStyle() + "-fx-fill: red;");break;
			case YELLOW:
				setStyle(getStyle() + "-fx-fill: yellow;");break;
			case BLUE:
				setStyle(getStyle() + "-fx-fill: blue;");break;
			default:
				break;
		}
	}
}
