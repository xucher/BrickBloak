package plugins;

import application.GameScene;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;
import objects.Ball;

public class TrackIndicator extends Polyline {
	public final int duration = 1000;
	private GameScene gameScene;
	private Ball referent;
	private Timeline timeline;
	
	public TrackIndicator(GameScene gameScene, Ball ball) {
		this.gameScene = gameScene;
		referent = ball;
		setStyle("-fx-stroke-dash-array: 10 50;");
		setStrokeDashOffset(10);
		initTimeline();
	}
	private void initTimeline() {
		timeline = new Timeline();
		timeline.setCycleCount(duration);
		KeyFrame keyFrame = new KeyFrame(Duration.millis(10), event -> {
			updateTrack();
		});
		timeline.getKeyFrames().add(keyFrame);
		timeline.setOnFinished(event -> {
			removeTracker();
		});
	}
	public void showTracker() {
		gameScene.addChild(this);
		timeline.play();
	}
	public void removeTracker() {
		if (gameScene.getChildren().contains(this)) {
			gameScene.getChildren().remove(this);
		}
	}
	public void updateTrack() {
		getPoints().clear();
		Point startPoint = new Point(referent.getLayoutX() + referent.getRadius()
		, referent.getLayoutY() + referent.getRadius());
		addPoint(startPoint);
		addNextTwoNodes();
	}
	
	private void addNextTwoNodes() {
		double minX = referent.getRadius();
		double maxX = gameScene.getPrefWidth() - referent.getRadius();
		double minY = referent.getRadius();
		double maxY = gameScene.getPrefHeight() - referent.getRadius();
		
		double speedX = referent.getSpeedX();
		double speedY = referent.getSpeedY();
		Point startPoint = getLastPoint();
		
		// 上边界
		double timeTop = (minY - startPoint.y) / speedY;
		// 下边界
		double timeBottom = (maxY - startPoint.y) / speedY;
		// 左边界
		double timeLeft = (minX - startPoint.x) / speedX;
		// 右边界
		double timeRight = (maxX - startPoint.x) / speedX;
		boolean flag = true;  // 标记那个方向要反向， true 代表X，false 代表Y
		if (speedX > 0 && speedY > 0) {
			if (timeRight < timeBottom) {
				addPoint(new Point(maxX, timeRight * speedY + startPoint.y));
			} else {
				addPoint(new Point(timeBottom * speedX + startPoint.x, maxY));
				flag = false;
			}
		}
		
		if (speedX > 0 && speedY < 0) {
			if (timeRight < timeTop) {
				addPoint(new Point(maxX, timeRight * speedY + startPoint.y));
			} else {
				addPoint(new Point(timeTop * speedX + startPoint.x, minY));
				flag = false;
			}
		}
		
		if (speedX < 0 && speedY > 0) {
			if (timeLeft < timeBottom) {
				addPoint(new Point(minX, timeLeft * speedY + startPoint.y));
			} else {
				addPoint(new Point(timeBottom * speedX + startPoint.x, maxY));
				flag = false;
			}
		}

		if (speedX < 0 && speedY < 0) {
			if (timeLeft < timeTop) {
				addPoint(new Point(minX, timeLeft * speedY + startPoint.y));
			} else {
				addPoint(new Point(timeTop * speedX + startPoint.x, minY));
				flag = false;
			}
		}
		if (flag) {
			speedX = -speedX;
		} else {
			speedY = -speedY;
		}
		addPoint(new Point(getLastPoint().x + 150 * speedX, getLastPoint().y + 150 * speedY));
	}
	

	private Point getLastPoint() {
		Point point = new Point();
		if (getPoints().size() >= 2) {
			point.x = getPoints().get(getPoints().size() - 2);
			point.y = getPoints().get(getPoints().size() - 1);
		}
		return point;
	}
	private void addPoint(Point point) {
		getPoints().add(point.x);
		getPoints().add(point.y);
	}
}

class Point {
	public double x;
	public double y;
	public Point() {}
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
}
