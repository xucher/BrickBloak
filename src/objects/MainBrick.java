package objects;

import application.BrickBlockGame;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MainBrick extends BaseObject {
	private Rectangle mRectangle;
	private BoxBlur mBlur;
	
	private final double WIDTH = 150;
	private final double HEIGHT = 25;
	private final double ARC_SIZE = 20;
	private final double BLUR_SIZE = 5;
	
	public  MainBrick() {
		mRectangle = new Rectangle();
		mRectangle.widthProperty().bindBidirectional(getWidthProperty());
		mRectangle.heightProperty().bindBidirectional(getHeightProperty());
		mRectangle.xProperty().bindBidirectional(getXProperty());
		mRectangle.yProperty().bindBidirectional(getYProperty());
		mRectangle.setArcWidth(ARC_SIZE);
		mRectangle.setArcHeight(ARC_SIZE);
		mRectangle.setFill(Color.YELLOW);
		
		mBlur = new BoxBlur();
		mBlur.setWidth(BLUR_SIZE);
		mBlur.setHeight(BLUR_SIZE);
		
		mRectangle.setEffect(mBlur);
		setWidth(WIDTH);
		setHeight(HEIGHT);
		getChildren().add(mRectangle);
	}
	
	// 让Brick跟着鼠标移动，将中心与鼠标对齐，控制Brick不超过边界
	public void onMouseMove(MouseEvent event) {
		if (event.getX() > getWidth()/2 && 
				event.getX() < BrickBlockGame.WIDTH - getWidth()/2) {
			setX(event.getX() - getWidth()/2);
		}
	}
}
