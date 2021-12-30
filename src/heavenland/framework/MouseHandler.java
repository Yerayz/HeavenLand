package heavenland.framework;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import heavenland.gamestate.GameStateManager;

public class MouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener {
	
	private GameStateManager gameStateManager;
	
	public MouseHandler(GameStateManager gameStateManager) {
		
		this.gameStateManager = gameStateManager;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		gameStateManager.mouseClicked(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		gameStateManager.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		gameStateManager.mouseReleased(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		gameStateManager.mouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		gameStateManager.mouseExited(e);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		gameStateManager.mouseWheelMoved(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		gameStateManager.mouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		gameStateManager.mouseMoved(e);
	}

}
