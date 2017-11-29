package application;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import application.GameScene.DataProperties;
import components.GameLayout;
import components.LevelCompleteScene;
import components.ScoreBoard;
import components.StateBar;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StateMachine {
	private static StateMachine instance = new StateMachine();
	private StateMachine() {}
	public static StateMachine getInstance() { return instance; }
	
	public enum GameState {
		BEGIN, RESTART, LOADING, PAUSE, GAME_OVER, EXIT,
		LEVEL_COMPLETE, NEXT_LEVEL
	}
	public enum Attr {
		MAINSTAGE, GAMESCENE, SCOREBOARD, STATEBAR, SUMMARY
	}
	private Map<Attr, Object> attrs = Collections.synchronizedMap(new HashMap<Attr, Object>());
	public Object getAttr(Attr name) { return attrs.get(name); }
	public Object removeAttr(Attr name) { return attrs.remove(name); }
	public void setAttr(Attr name, Object attr) { attrs.put(name, attr); }
	public Set<Attr> getAttrNames() { return attrs.keySet(); }
	
	public void changeToState(GameState newState) {
		switch(newState) {
			case BEGIN: beginGame();break;
			case PAUSE: pauseGame();break;
			case RESTART: restartGame();break;
			case LEVEL_COMPLETE: showSummary();break;
			case NEXT_LEVEL: enterNextLevel();break;
			case GAME_OVER: GameOver();break;
			case EXIT: exitGame();break;
			default: break;
		}
	}
	
	private void restartGame() {
		((Stage) getAttr(Attr.SUMMARY)).close();
		((GameScene) getAttr(Attr.GAMESCENE)).restartGame();
	}
	private void showSummary() {
		LevelCompleteScene summary = new LevelCompleteScene(true);
		Stage sumStage = new Stage();
		// TODO 禁止直接关闭Summary
		sumStage.setScene(new Scene(summary));
		
		((Stage) getAttr(Attr.MAINSTAGE)).toBack();
		sumStage.show();
		setAttr(Attr.SUMMARY, sumStage);
	}
	private void beginGame() {
		GameScene gameScene = new GameScene();
		ScoreBoard scoreBoard = new ScoreBoard();
		StateBar stateBar = new StateBar();
		GameLayout gameLayout = new GameLayout(gameScene, scoreBoard, stateBar);
		((Stage) getAttr(Attr.MAINSTAGE)).setScene(new Scene(gameLayout));
		setAttr(Attr.SCOREBOARD, scoreBoard);
		setAttr(Attr.GAMESCENE, gameScene);
		setAttr(Attr.STATEBAR, stateBar);
		
		gameScene.loadGame();
		DataProperties dataProperties = gameScene.getData();
		((ScoreBoard) getAttr(Attr.SCOREBOARD)).bindValue(dataProperties);
		((StateBar) getAttr(Attr.STATEBAR)).bindValue(dataProperties.lifeProgressProperty);
	}

	private void pauseGame() {
		((GameScene) getAttr(Attr.GAMESCENE)).pauseTimeLine();;
	}
	private void enterNextLevel() {
		((GameScene) getAttr(Attr.GAMESCENE)).enterNextLevel();
		((Stage) getAttr(Attr.SUMMARY)).close();
	}
	private void GameOver() {
		LevelCompleteScene summary = new LevelCompleteScene(false);
		Stage sumStage = new Stage();
		// TODO 禁止直接关闭Summary
		sumStage.setScene(new Scene(summary));
		
		((Stage) getAttr(Attr.MAINSTAGE)).toBack();
		sumStage.show();
		setAttr(Attr.SUMMARY, sumStage);
	}
	private void exitGame() {
		((Stage) getAttr(Attr.MAINSTAGE)).close();
	}
}
