package objects;

import java.util.ArrayList;
import java.util.List;

import application.Constant;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MainBrick extends Rectangle {
	private List<Object> equipments = new ArrayList<>();
	public  MainBrick() {
		setWidth(Constant.MAIN_BRICK_WIDTH);
		setHeight(Constant.MAIN_BRICK_HEIGHT);
		setFill(Color.BURLYWOOD);
//		Image image = new Image("/asserts/images/main_brick.jpg", getWidth(), getHeight(), false, false);
//		ImageInput imageInput = new ImageInput(image);
//		setEffect(imageInput);
	}
	
	// ��Brick��������ƶ����������������룬����Brick�������߽�
	public void onMouseMove(MouseEvent event) {
		if (event.getX() > getWidth()/2 && 
				event.getX() < Constant.GAME_SCENE_WIDTH - getWidth()/2) {
			setLayoutX(event.getX() - getWidth()/2);
		}
	}
	
	public void addEquipment(Object equipment) {
		this.equipments.add(equipment);
	}
	public void reset() {
		setScaleX(1);
	}
}
