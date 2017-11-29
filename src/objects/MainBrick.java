package objects;

import application.Constant;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class MainBrick extends Rectangle {
	public  MainBrick() {
		setWidth(Constant.MAIN_BRICK_WIDTH);
		setHeight(Constant.MAIN_BRICK_HEIGHT);
		
		Image image = new Image("/asserts/images/main_brick.jpg", getWidth(), getHeight(), false, false);
		ImageInput imageInput = new ImageInput(image);
		setEffect(imageInput);
	}
	
	// ��Brick��������ƶ����������������룬����Brick�������߽�
	public void onMouseMove(MouseEvent event) {
		if (event.getX() > getWidth()/2 && 
				event.getX() < Constant.GAME_SCENE_WIDTH - getWidth()/2) {
			setLayoutX(event.getX() - getWidth()/2);
		}
	}
}
