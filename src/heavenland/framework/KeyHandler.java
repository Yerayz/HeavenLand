package heavenland.framework;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import heavenland.gamestate.GameStateManager;

public class KeyHandler implements KeyListener {

	public GameStateManager gameStateManager;
	
	public KeyHandler(GameStateManager gameStateManager) {
		
		this.gameStateManager = gameStateManager;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		gameStateManager.keyTyped(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		gameStateManager.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		gameStateManager.keyReleased(e);
	}

}
