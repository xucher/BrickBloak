package application;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import application.GameScene.DataProperties;
import components.GameLayout;
import components.ScoreBoard;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StateMachine {
	private static StateMachine instance = new StateMachine();
	private StateMachine() {}
	public static StateMachine getInstance() { return instance; }
	
	public enum State {
		BEGIN, LOADING, PAUSE, NEXT_LEVEL, GAME_OVER
	}
	public enum Attr {
		STAGE, GAMESCENE, SCOREBOARD 
	}
	private Map<Attr, Object> attrs = Collections.synchronizedMap(new HashMap<Attr, Object>());
	public Object getAttr(Attr name) { return attrs.get(name); }
	public Object removeAttr(Attr name) { return attrs.remove(name); }
	public void setAttr(Attr name, Object attr) { attrs.put(name, attr); }
	public Set<Attr> getAttrNames() { return attrs.keySet(); }
	
	public void changeToState(State newState) {
		switch(newState) {
			case BEGIN: beginGame();break;
			case PAUSE: pauseGame();break;
			case NEXT_LEVEL: enterNextLevel();break;
			case GAME_OVER: GameOver();break;
			default: break;
		}
	}
	
	private void beginGame() {
		GameScene gameScene = new GameScene();
		ScoreBoard scoreBoard = new ScoreBoard();
		GameLayout gameLayout = new GameLayout(gameScene, scoreBoard);
		((Stage) getAttr(Attr.STAGE)).setScene(new Scene(gameLayout));
		setAttr(Attr.SCOREBOARD, scoreBoard);
		setAttr(Attr.GAMESCENE, gameScene);
		
		gameScene.loadGame();
		DataProperties dataProperties = gameScene.getData();
		((ScoreBoard) getAttr(Attr.SCOREBOARD)).bindValue(dataProperties);
	}

	private void pauseGame() {
		((GameScene) getAttr(Attr.GAMESCENE)).pauseTimeLine();;
	}
	private void enterNextLevel() {
		((GameScene) getAttr(Attr.GAMESCENE)).enterNextLevel();
	}
	private void GameOver() {
		
	}
}
