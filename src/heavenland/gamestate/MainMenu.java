package heavenland.gamestate;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import heavenland.framework.KeyHandler;
import heavenland.framework.Window;
import heavenland.gui.GButton;
import heavenland.resource.Res;

public class MainMenu extends GameState {

	ArrayList<GButton> buttons;
	BufferedImage newBtn = Res.GUI.get(Res.NEW_GAME_BTN);
	BufferedImage newBtnPressed = Res.GUI.get(Res.NEW_GAME_BTN_P);
	BufferedImage loadBtn = Res.GUI.get(Res.LOAD_GAME_BTN);
	BufferedImage loadBtnPressed = Res.GUI.get(Res.LOAD_GAME_BTN_P);
	BufferedImage exitBtn = Res.GUI.get(Res.EXIT_BTN);
	BufferedImage exitBtnPressed = Res.GUI.get(Res.EXIT_BTN_P);
	
	boolean newPressed;
	boolean loadPressed;
	boolean exitPressed;
	
	public MainMenu(GameStateManager manager) {
		super(manager);

		buttons = new ArrayList<>();
		buttons.add(new GButton(Res.GUI.get(Res.NEW_GAME_BTN), Res.GUI.get(Res.NEW_GAME_BTN_P), 184, 400, 6));
		buttons.add(new GButton(Res.GUI.get(Res.LOAD_GAME_BTN), Res.GUI.get(Res.LOAD_GAME_BTN_P), 632, 400, 6));
		buttons.add(new GButton(Res.GUI.get(Res.EXIT_BTN), Res.GUI.get(Res.EXIT_BTN_P), 408, 512, 6));
		newPressed = false;
		loadPressed = false;
		exitPressed = false;
	}

	@Override
	protected void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void render(Graphics2D g2d) {

		g2d.setColor(Color.cyan);
		g2d.drawImage(Res.GUI.get(Res.MAINMENU_BG), 0, 0, Window.WIDTH, Window.HEIGHT, null);
//		if(!newPressed)
//			g2d.drawImage(newBtn, 184, 400, newBtn.getWidth()*Window.SCALE*2, newBtn.getHeight()*Window.SCALE*2, null);
//		else
//			g2d.drawImage(newBtnPressed, 184, 400, newBtn.getWidth()*Window.SCALE*2, newBtn.getHeight()*Window.SCALE*2, null);
//		if(!loadPressed)
//			g2d.drawImage(loadBtn, 632, 400, newBtn.getWidth()*Window.SCALE*2, newBtn.getHeight()*Window.SCALE*2, null);
//		else
//			g2d.drawImage(loadBtnPressed, 632, 400, newBtn.getWidth()*Window.SCALE*2, newBtn.getHeight()*Window.SCALE*2, null);
//		if(!exitPressed)
//			g2d.drawImage(exitBtn, 408, 512, newBtn.getWidth()*Window.SCALE*2, newBtn.getHeight()*Window.SCALE*2, null);
//		else
//			g2d.drawImage(exitBtnPressed, 408, 512, newBtn.getWidth()*Window.SCALE*2, newBtn.getHeight()*Window.SCALE*2, null);
		for(GButton i : buttons)
			i.render(g2d);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		int x = e.getX();
		int y = e.getY();
		for(GButton i : buttons) 
			if(i.isOnButton(x, y))
				i.isPressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		int x = e.getX();
		int y = e.getY();
		if(buttons.get(0).isOnButton(x, y))
			gameStateManager.addState(new Playing(gameStateManager));
		if(buttons.get(1).isOnButton(x, y));
		if(buttons.get(2).isOnButton(x, y))
			System.exit(0);
		
		for(GButton i : buttons) 
			i.isPressed = false;
	}

}
