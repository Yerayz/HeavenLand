package heavenland.gamestate;

import java.awt.Graphics;
import java.awt.Graphics2D;

import heavenland.entity.Player;
import heavenland.framework.KeyHandler;
import heavenland.world.Region;

public class Playing extends GameState {

	private Player player;
	private Region region;
	
	public Playing(GameStateManager manager, KeyHandler keyH) {
		
		super(manager, keyH);
		
		this.player = new Player();
		this.region = new Region((byte)0);
	}

	@Override
	protected void tick() {
		
		player.tick(keyH, region);
	}

	@Override
	protected void render(Graphics2D g2d) {
		
		region.render(g2d, player);
		player.render(g2d);
	}

}
