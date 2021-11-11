package heavenland.gamestate;

import java.awt.Graphics;
import java.awt.Graphics2D;

import heavenland.framework.KeyHandler;

public abstract class GameState {

	protected GameStateManager gameStateManager;
	protected KeyHandler keyH;
	
	public GameState(GameStateManager manager, KeyHandler keyH) {
		
		this.gameStateManager = manager;
		this.keyH = keyH;
	}
	
	protected abstract void tick();
	
	protected abstract void render(Graphics2D g2d);

}
