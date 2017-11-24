package application;

import javafx.scene.paint.Color;
import objects.Brick;

public class LeverLoader {
	private static int[][] level1 = {
			{ 0, 1, 1, 1, 1, 1, 0 },
			{ 0, 1, 1, 1, 1, 1, 0 },
			{ 0, 1, 1, 1, 1, 1, 0 },
			{ 0, 1, 1, 1, 1, 1, 0 }
	};
	
	public static void load(GameScene scene, int level) {
		int[][] datas = getLevel(level);
		for (int i = 0;i < datas.length;i++) {
			for (int j =0;j < datas[0].length;j++) {
				if (datas[i][j] != 0) {
					Brick brick = new Brick(Color.RED, datas[i][j]);
					brick.setWidth(108.0D);
					brick.setX((760 - 7 * brick.getWidth()) / 2.0D +
							j * (brick.getWidth()));
					brick.setY(i * (brick.getHeight()) + 100);
					
					scene.getBricks().add(brick);
					scene.addChild(brick);
				}
			}
		}
	}
	
	public static int[][] getLevel(int i) {
		if (i == 1) {
			return level1;
		}
		return (int[][])null;
	}
}
