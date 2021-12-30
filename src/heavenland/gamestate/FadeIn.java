package heavenland.gamestate;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import heavenland.framework.Window;

public class FadeIn extends GameState {

	GameState nextState;
	float alphaValue;
	
	public FadeIn(GameStateManager manager, GameState nextState) {
		super(manager);
		
		this.nextState = nextState;
		this.alphaValue = 0;
	}
	
	public FadeIn(GameStateManager manager) {
		super(manager);
		
		this.alphaValue = 0;
	}

	@Override
	protected void tick() {
		
		alphaValue += 0.02;
		if(alphaValue >= 1) {
			alphaValue = 1;
			gameStateManager.backToPreviousState();
			if(nextState != null) {
				gameStateManager.addState(nextState);
				gameStateManager.addState(new FadeOut(gameStateManager, nextState));
			}
			else
				gameStateManager.addState(new FadeOut(gameStateManager));
		}
	}

	@Override
	protected void render(Graphics2D g2d) {
		
		gameStateManager.renderPreviousState(g2d);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
	}
	
}
