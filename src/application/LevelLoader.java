package application;

import java.util.Random;

import objects.Brick;

public class LevelLoader {
	private Random random = new Random();
	private final int brickGap = 1;
	private GameScene scene;
	public LevelLoader(GameScene scene) {
		this.scene = scene;
	}
	
	public void load(int level) {
		int color = 0;
		Brick brick = null;
		for (int i = 0;i < 4;i++) {
			for (int j =0;j < 6;j++) {
				color = random.nextInt(6);
				switch (color) {
					case 0:
						brick = new Brick(Brick.Type.RED, 1);break;
					case 1:
						brick = new Brick(Brick.Type.YELLOW, 1);break;
					case 2:
						brick = new Brick(Brick.Type.BLUE, 1);break;
					default:
						brick = new Brick(Brick.Type.GENERAL, 1);break;
				}
				brick.setLayoutX(j * (brick.getWidth() + brickGap) + 50);
				brick.setLayoutY(i * (brick.getHeight() + brickGap) + 60);
				
				scene.getBricks().add(brick);
				scene.addChild(brick);
			}
		}
	}
}
