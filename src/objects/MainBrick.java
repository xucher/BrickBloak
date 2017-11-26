package objects;

import application.Constant;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class MainBrick extends Rectangle {
	public  MainBrick() {
		setWidth(Constant.MAIN_BRICK_WIDTH);
		setHeight(Constant.MAIN_BRICK_HEIGHT);
	}
	
	// 让Brick跟着鼠标移动，将中心与鼠标对齐，控制Brick不超过边界
	public void onMouseMove(MouseEvent event) {
		if (event.getX() > getWidth()/2 && 
				event.getX() < Constant.GAME_SCENE_WIDTH - getWidth()/2) {
			setLayoutX(event.getX() - getWidth()/2);
		}
	}
}
