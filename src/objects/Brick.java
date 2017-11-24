package objects;

import javafx.geometry.Bounds;
import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Brick extends BaseObject {
	private Rectangle mRectangle;
	// ÑªÁ¿
	private int hp;
	private BoxBlur mBlur;
	
	public Brick(Color color, int hp) {
		this.hp = hp;
		mRectangle = new Rectangle();
		mRectangle.widthProperty().bindBidirectional(getWidthProperty());
		mRectangle.heightProperty().bindBidirectional(getHeightProperty());
		mRectangle.xProperty().bindBidirectional(getXProperty());
		mRectangle.yProperty().bindBidirectional(getYProperty());
		mRectangle.setFill(color);
		
		mBlur = new BoxBlur();
		mBlur.setWidth(5);
		mBlur.setHeight(5);
		mRectangle.setEffect(mBlur);
		
		setWidth(100);
		setHeight(25);
		getChildren().add(mRectangle);
	}
	
	public Bounds getBounds() { return this.mRectangle.getLayoutBounds(); }
	public int getHp() { return hp; }
	public void setHp(int hp) { this.hp = hp; }
}
