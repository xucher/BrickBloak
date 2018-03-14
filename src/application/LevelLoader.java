package application;

import java.util.Random;

import objects.Brick;

public class LevelLoader {
	private Random random = new Random();
	private final int brickGapX = Constant.COMMON_BRICK_WIDTH + 2;
	private final int brickGapY = Constant.COMMON_BRICK_HEIGTH + 2;
	private GameScene scene;
	private double brickMinHeight;
	
	public LevelLoader(GameScene scene) {
		this.scene = scene;
		brickMinHeight = scene.getHeight() - 250;
	}
	
	public void load(int level) {
		int i = 0;
		for (i = 0;i < 7;i++) {
			createBrick(i * brickGapX + 5, brickMinHeight);
		}
		for (i = 0;i < 4;i++) {
			createBrick(5, brickMinHeight - i * brickGapY - 50);
			createBrick(5 + 6 * brickGapX, brickMinHeight - i * brickGapY - 50);
		}
		createBrick(5 + brickGapX, brickMinHeight - 3 * brickGapY - 50);
		createBrick(5 + 4 * brickGapX + brickGapX, brickMinHeight - 3 * brickGapY - 50);
		for (i = 0;i < 4;i++) {
			createBrick(5 + 1.5 * brickGapX, brickMinHeight - (i + 4) * brickGapY - 50);
			createBrick(5 + 4.5 * brickGapX, brickMinHeight - (i + 4) * brickGapY - 50);
		}
		createBrick(5 + 2.5 * brickGapX, brickMinHeight - 7 * brickGapY - 50);
		createBrick(5 + 3.5 * brickGapX, brickMinHeight - 7 * brickGapY - 50);
		// ÖÐ¼äµÄ
		for (i = 0;i < 3;i++) {
			createBrick(5 + (2 + i) * brickGapX, brickMinHeight - 0 * brickGapY - 50);
		}
	}
	
	private void createBrick(double x, double y) {
		Brick brick = null;
		int color = random.nextInt(6);
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
		brick.setLayoutX(x);
		brick.setLayoutY(y);
		scene.getBricks().add(brick);
		scene.addChild(brick);
	}
}
