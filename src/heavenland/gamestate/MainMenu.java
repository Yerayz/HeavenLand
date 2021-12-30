package heavenland.gamestate;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

import heavenland.framework.KeyHandler;
import heavenland.framework.Window;
import heavenland.gui.GButton;
import heavenland.gui.GComponent;
import heavenland.resource.Res;

public class MainMenu extends GameState {

	ArrayList<GButton> buttons;
	ArrayList<GComponent> components;
	Clip clip;
	
	public MainMenu(GameStateManager manager) {
		super(manager);

		buttons = new ArrayList<>();
		components = new ArrayList<>();
		
		buttons.add(new GButton(Res.GUI.get(Res.NEW_GAME_BTN), Res.GUI.get(Res.NEW_GAME_BTN_P), 184, 400, 2));
		buttons.add(new GButton(Res.GUI.get(Res.LOAD_GAME_BTN), Res.GUI.get(Res.LOAD_GAME_BTN_P), 632, 400, 2));
		buttons.add(new GButton(Res.GUI.get(Res.EXIT_BTN), Res.GUI.get(Res.EXIT_BTN_P), 408, 512, 2));
		
		components.add(new GComponent(Res.GUI.get(Res.MAINMENU_LOGO), (Window.WIDTH-179*Window.SCALE)/2, 50, 179, 99));
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(Res.SOUND.get(Res.RAIN));
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (Exception e) {
			e.printStackTrace();
		}
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	@Override
	protected void tick() {
	}

	@Override
	protected void render(Graphics2D g2d) {

		g2d.setColor(Color.cyan);
		g2d.drawImage(Res.GUI.get(Res.MAINMENU_BG), 0, 0, Window.WIDTH, Window.HEIGHT, null);
		for(GButton i : buttons)
			i.render(g2d);
		for(GComponent i : components)
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
		if(buttons.get(0).isOnButton(x, y) && buttons.get(0).isPressed) {
			clip.stop();
			gameStateManager.addState(new FadeIn(gameStateManager, new Playing(gameStateManager)));
		}
		if(buttons.get(1).isOnButton(x, y) && buttons.get(1).isPressed);
		if(buttons.get(2).isOnButton(x, y) && buttons.get(2).isPressed)
			System.exit(0);
		
		for(GButton i : buttons) 
			i.isPressed = false;
	}

}
