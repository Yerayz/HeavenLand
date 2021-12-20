package heavenland.gamestate;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import heavenland.framework.KeyHandler;

public abstract class GameState {

	protected GameStateManager gameStateManager;
	
	public GameState(GameStateManager manager) {
		
		this.gameStateManager = manager;
	}
	
	protected abstract void tick();
	
	protected abstract void render(Graphics2D g2d);

	
	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}
	
	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
}
