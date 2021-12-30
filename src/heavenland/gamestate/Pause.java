package heavenland.gamestate;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import heavenland.framework.Window;

public class Pause extends GameState {

	public Pause(GameStateManager manager) {
		super(manager);

		
	}

	@Override
	protected void tick() {
		
		
	}

	@Override
	protected void render(Graphics2D g2d) {
		
		gameStateManager.renderPreviousState(g2d);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)0.3));
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_E) {
			gameStateManager.backToPreviousState();
		}
	}

}
