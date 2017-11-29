package test;

import objects.Brick;

public class TestBrick {
	public static void getBrickInfo(Brick brick) {
		System.out.println("HP: " + brick.getHp());
		System.out.println("Size: (" + brick.getWidth() + ", " + brick.getHeight() + ")");
		System.out.println("layout = (" + brick.getLayoutX() + ", " + brick.getLayoutY() + ")");
	}
}
