package application;

import objects.Brick;

public class LevelLoader {
	private GameScene scene;
	public LevelLoader(GameScene scene) {
		this.scene = scene;
	}
	public void load(int level) {
		for (int i = 0;i < 4;i++) {
			for (int j =0;j < 7;j++) {
				Brick brick = new Brick(Brick.Style.RED, 1);
				brick.setLayoutX(j * brick.getWidth() + 50);
				brick.setLayoutY(i * brick.getHeight() + 20);
				
				scene.getBricks().add(brick);
				scene.addChild(brick);
			}
		}
	}
}
