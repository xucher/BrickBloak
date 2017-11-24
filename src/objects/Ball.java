package objects;

import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends BaseObject {
	private Circle circle;
	private int speedX = 2;
	private int speedY = -2;
	public Ball(int centerX, int centerY, int radius) {
		circle = new Circle(centerX, centerY, radius, Color.LIGHTBLUE);
		circle.translateXProperty().bindBidirectional(getXProperty());
		circle.translateYProperty().bindBidirectional(getYProperty());
		circle.setEffect(new Lighting());
		 
		setWidth(2 * radius);
		setHeight(2 * radius);
		getChildren().add(circle);
	}
	
	public int getSpeedX() { return speedX; }
	public void setSpeedX(int speedX) { this.speedX = speedX; }
	
	public int getSpeedY() { return speedY; }
	public void setSpeedY(int speedY) { this.speedY = speedY; }
}
