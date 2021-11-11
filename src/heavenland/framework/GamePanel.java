package heavenland.framework;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import heavenland.gamestate.GameStateManager;

public class GamePanel extends JPanel {

	private GameStateManager gameStateManager;
	
	public GamePanel(GameStateManager gameStateManager) {
		
		this.gameStateManager = gameStateManager;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		this.gameStateManager.render(g2d);
		
		g2d.dispose();
	}
}
