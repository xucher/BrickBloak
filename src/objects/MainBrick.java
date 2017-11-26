package objects;

import application.Constant;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class MainBrick extends Rectangle {
	public  MainBrick() {
		setWidth(Constant.MAIN_BRICK_WIDTH);
		setHeight(Constant.MAIN_BRICK_HEIGHT);
	}
	
	// ��Brick��������ƶ����������������룬����Brick�������߽�
	public void onMouseMove(MouseEvent event) {
		if (event.getX() > getWidth()/2 && 
				event.getX() < Constant.GAME_SCENE_WIDTH - getWidth()/2) {
			setLayoutX(event.getX() - getWidth()/2);
		}
	}
}
