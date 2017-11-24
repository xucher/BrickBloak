package objects;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Parent;

public class BaseObject extends Parent {
	private DoubleProperty width = new SimpleDoubleProperty(0);
	private DoubleProperty height = new SimpleDoubleProperty(0);
	private DoubleProperty x = new SimpleDoubleProperty(0);
	private DoubleProperty y = new SimpleDoubleProperty(0);
	
	public DoubleProperty getWidthProperty() { return width;}
	public double getWidth() { return width.get(); }
	public void setWidth(double width) { this.width.set(width); }
	
	public DoubleProperty getHeightProperty() { return height; }
	public double getHeight() { return height.get(); }
	public void setHeight(double height) { this.height.set(height); }
	
	public DoubleProperty getXProperty() { return x; }
	public double getX() { return x.get(); }
	public void setX(double x) { this.x.set(x); }
	public void moveX(double x) { this.x.set(getX() + x); }
	
	public DoubleProperty getYProperty() { return y; }
	public double getY() { return y.get(); }
	public void setY(double y) { this.y.set(y); }
	public void moveY(double y) { this.y.set(getY() + y); }
	
	public boolean isCollisionWith(BaseObject anotherObject) {
		// ¾ØÐÎÅö×²
		if (anotherObject.getX() <= getX() + getWidth() &&
				getX() <= anotherObject.getX() + anotherObject.getWidth() &&
				anotherObject.getY() <= getY() + getHeight() && 
				getY() <= anotherObject.getY() + anotherObject.getHeight())
			return true;
		return false;
	}
	
}
