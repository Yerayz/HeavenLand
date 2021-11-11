package heavenland.gamestate;

import java.awt.Graphics;
import java.awt.Graphics2D;

import heavenland.entity.Player;
import heavenland.framework.KeyHandler;

public class Playing extends GameState {

	private Player player;
	
	public Playing(GameStateManager manager, KeyHandler keyH) {
		
		super(manager, keyH);
		
		this.player = new Player();
	}

	@Override
	protected void tick() {
		
		player.tick(keyH);
	}

	@Override
	protected void render(Graphics2D g2d) {
		
		player.render(g2d);
	}

}
