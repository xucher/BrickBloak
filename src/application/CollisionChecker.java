package application;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class CollisionChecker {
	public enum CollisionType {
		POINT, HORIZON, VERTICAL, NO
	}
	private Node object;
	public CollisionChecker(Node object) {
		this.object = object;
	}
	
	public CollisionType withRectangle(Rectangle rectangle) {
		// 判断检测物形状
		if (object instanceof Circle) {
			return rectAndCirTest(rectangle);
		} else if (object instanceof Rectangle) {
			return rectangleTest(rectangle);
		}  else {
			return CollisionType.NO;
		}
	}
	

	// 圆形碰撞检测
//	private boolean circleTest(Circle circle) {
//		double distant = square(ball.getCenterX() - circle.getCenterX())
//				+ square(ball.getCenterY() - circle.getCenterY());
//		if (distant <= square(ball.getRadius() + circle.getRadius())) {
//			return true;
//		}
//		return false;
//	}
	
	// 圆形和矩形碰撞检测
	private CollisionType rectAndCirTest(Rectangle rectangle) {
		Bounds objBounds = object.getBoundsInParent();
		double radius = objBounds.getWidth() / 2;
		double centerX = objBounds.getMinX() + objBounds.getWidth() / 2;
		double centerY = objBounds.getMinY() + objBounds.getHeight() / 2;
		Bounds rectBounds = rectangle.getBoundsInParent();
		
		// 判断是否单象限碰撞，以圆心为原点
		if ((rectBounds.getMinX() - centerX) *
				(rectBounds.getMaxX() - centerX) > 0 &&
				(rectBounds.getMinY() - centerY) *
				(rectBounds.getMaxY() - centerY) > 0) {
			Double distant1 = square(rectBounds.getMinX() - centerX)
					+ square(rectBounds.getMinY() - centerY);
			Double distant2 = square(rectBounds.getMinX() - centerX)
					+ square(rectBounds.getMaxY() - centerY);
			Double distant3 = square(rectBounds.getMaxX() - centerX)
					+ square(rectBounds.getMinY() - centerY);
			Double distant4 = square(rectBounds.getMaxX() - centerX)
					+ square(rectBounds.getMaxY() - centerY);
			Double distant = square(radius);
			if (distant1 <= distant || distant2 <= distant ||
					distant3 <= distant || distant4 <= distant) {
				return CollisionType.POINT;
			}
		}
		// 不是单象限碰撞
		return rectangleTest(rectangle);
	}
	
	// 矩形碰撞检测
	private CollisionType rectangleTest(Rectangle rectangle) {
		Bounds objBounds = object.getBoundsInParent();
		double centerX = objBounds.getMinX() + objBounds.getWidth() / 2;
		Bounds rectBounds = rectangle.getBoundsInParent();
	// 不判断方向可以进入
		if (rectBounds.getMinX() <= objBounds.getMaxX()&&
				objBounds.getMinX() <= rectBounds.getMaxX() &&
				rectBounds.getMinY() <= objBounds.getMaxY() && 
				objBounds.getMinY() <= rectBounds.getMaxY()) {
			// TODO 判断方向, 只适用于球
			if (centerX >= rectBounds.getMinX() &&
					centerX <= rectBounds.getMaxX())
				return CollisionType.VERTICAL;
			else
				return CollisionType.HORIZON;
		}
		return CollisionType.NO;
	}
	
	private double square(double num) { return Math.pow(num, 2); }
}
