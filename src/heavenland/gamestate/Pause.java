package heavenland.gamestate;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import heavenland.entity.Player;
import heavenland.framework.Window;
import heavenland.gui.GButton;
import heavenland.gui.GComponent;
import heavenland.gui.GInventButton;
import heavenland.resource.Res;

public class Pause extends GameState {
	
	Player player;
	private ArrayList<GButton> buttons;
	private ArrayList<GComponent> backComponents;
	private ArrayList<GComponent> frontComponents;
	private int state;
	
	int[] buySeed;

	public Pause(GameStateManager manager, Player player) {
		super(manager);

		this.player = player;
		
		this.buttons = new ArrayList<>();
		this.backComponents = new ArrayList<>();
		this.frontComponents = new ArrayList<>();
		this.state = 1;
		setInterface();
		resetBuyValue();
	}

	@Override
	protected void tick() {
		
//		updateInterface();
	}

	@Override
	protected void render(Graphics2D g2d) {
		
		gameStateManager.renderPreviousState(g2d);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)0.6));
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)1));
		
		renderInterface(g2d);
	}
	
	public void setInterface() {
		
		BufferedImage image;
		int imageWidth;
		int imageHeight;
		
		image = Res.GUI.get(Res.PAUSE_BAR);
		imageWidth = image.getWidth()*Window.SCALE;
		imageHeight = image.getHeight()*Window.SCALE;
		backComponents.add(new GComponent(image,(Window.WIDTH-imageWidth)/2, (Window.HEIGHT-imageHeight)/2));
		
		buttons.add(new GButton(Res.GUI.get(Res.PAUSE_INVENTORY_BTN), backComponents.get(0).x+18, backComponents.get(0).y-39));
		buttons.add(new GButton(Res.GUI.get(Res.PAUSE_BUY_BTN), buttons.get(0).x+57, buttons.get(0).y));
		buttons.add(new GButton(Res.GUI.get(Res.PAUSE_OUT_BTN), buttons.get(1).x+57, buttons.get(1).y));
		
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 12; j++) {
				
				buttons.add(new GInventButton(Res.GUI.get(Res.INVENTORY_BTN), backComponents.get(0).x+48+j*81, backComponents.get(0).y+96+i*81, 1.5));
			}
		}
		image = Res.GUI.get(Res.EXIT_BTN);
		imageWidth = image.getWidth()*(int)(Window.SCALE*2);
		imageHeight = image.getHeight()*(int)(Window.SCALE*2);
		buttons.add(new GButton(Res.GUI.get(Res.EXIT_BTN), Res.GUI.get(Res.EXIT_BTN_P), (Window.WIDTH-imageWidth)/2, (Window.HEIGHT-imageHeight)/2, 2));
		
		
		
		frontComponents.add(new GComponent(Res.GUI.get(Res.PAUSE_INVENTORY_BTN_P), backComponents.get(0).x+18, backComponents.get(0).y-39));
		frontComponents.add(new GComponent(Res.GUI.get(Res.PAUSE_BUY_BTN_P), frontComponents.get(0).x+57, frontComponents.get(0).y));
		frontComponents.add(new GComponent(Res.GUI.get(Res.PAUSE_OUT_BTN_P), frontComponents.get(1).x+57, frontComponents.get(0).y));
		
		frontComponents.add(new GComponent(Res.ITEM.get(Res.CORN_SEED).getImage(), backComponents.get(0).x+96, backComponents.get(0).y+53, 1.5));
		frontComponents.add(new GComponent(Res.ITEM.get(Res.CARROT_SEED).getImage(), backComponents.get(0).x+96, frontComponents.get(3).y+88, 1.5));
		frontComponents.add(new GComponent(Res.ITEM.get(Res.CABBAGE_SEED).getImage(), backComponents.get(0).x+96, frontComponents.get(4).y+88, 1.5));

		frontComponents.add(new GComponent(Res.ITEM.get(Res.EGGPLANT_SEED).getImage(), frontComponents.get(5).x+192, backComponents.get(0).y+53, 1.5));
		frontComponents.add(new GComponent(Res.ITEM.get(Res.RADISH_SEED).getImage(), frontComponents.get(5).x+192, frontComponents.get(3).y+88, 1.5));
		frontComponents.add(new GComponent(Res.ITEM.get(Res.CAULIFLOWER_SEED).getImage(), frontComponents.get(5).x+192, frontComponents.get(4).y+88, 1.5));
		
		buttons.add(new GButton(Res.GUI.get(Res.PLUS_BTN), Res.GUI.get(Res.PLUS_BTN_P), frontComponents.get(3).x+72, frontComponents.get(3).y+24));
		buttons.add(new GButton(Res.GUI.get(Res.MINUS_BTN), Res.GUI.get(Res.MINUS_BTN_P), buttons.get(28).x+63, buttons.get(28).y));
		buttons.add(new GButton(Res.GUI.get(Res.PLUS_BTN), Res.GUI.get(Res.PLUS_BTN_P), frontComponents.get(4).x+72, frontComponents.get(4).y+24));
		buttons.add(new GButton(Res.GUI.get(Res.MINUS_BTN), Res.GUI.get(Res.MINUS_BTN_P), buttons.get(30).x+63, buttons.get(30).y));
		buttons.add(new GButton(Res.GUI.get(Res.PLUS_BTN), Res.GUI.get(Res.PLUS_BTN_P), frontComponents.get(5).x+72, frontComponents.get(5).y+24));
		buttons.add(new GButton(Res.GUI.get(Res.MINUS_BTN), Res.GUI.get(Res.MINUS_BTN_P), buttons.get(32).x+63, buttons.get(32).y));

		buttons.add(new GButton(Res.GUI.get(Res.PLUS_BTN), Res.GUI.get(Res.PLUS_BTN_P), frontComponents.get(6).x+72, frontComponents.get(6).y+24));
		buttons.add(new GButton(Res.GUI.get(Res.MINUS_BTN), Res.GUI.get(Res.MINUS_BTN_P), buttons.get(34).x+63, buttons.get(34).y));
		buttons.add(new GButton(Res.GUI.get(Res.PLUS_BTN), Res.GUI.get(Res.PLUS_BTN_P), frontComponents.get(7).x+72, frontComponents.get(7).y+24));
		buttons.add(new GButton(Res.GUI.get(Res.MINUS_BTN), Res.GUI.get(Res.MINUS_BTN_P), buttons.get(36).x+63, buttons.get(36).y));
		buttons.add(new GButton(Res.GUI.get(Res.PLUS_BTN), Res.GUI.get(Res.PLUS_BTN_P), frontComponents.get(8).x+72, frontComponents.get(8).y+24));
		buttons.add(new GButton(Res.GUI.get(Res.MINUS_BTN), Res.GUI.get(Res.MINUS_BTN_P), buttons.get(38).x+63, buttons.get(38).y));
		
		updateInterface();
	}
	
	public void updateInterface() {
		GInventButton btn;
		for(int i = 3; i < 27; i++) {
			btn = ((GInventButton)buttons.get(i));
			btn.setItem(player.inventory[(i-3)/12][(i-3)%12]);
		}
		
	}
	
	public void renderInterface(Graphics2D g2d) {
		
		for(GComponent i : backComponents)
			i.render(g2d);
		buttons.get(0).render(g2d);
		buttons.get(1).render(g2d);
		buttons.get(2).render(g2d);
		if(state == 1) {
			for(int i = 3; i < 27; i++)
				buttons.get(i).render(g2d);
			frontComponents.get(0).render(g2d);
		}
		else if(state == 2) {
			frontComponents.get(1).render(g2d);
			frontComponents.get(3).render(g2d);
			frontComponents.get(4).render(g2d);
			frontComponents.get(5).render(g2d);
			frontComponents.get(6).render(g2d);
			frontComponents.get(7).render(g2d);
			frontComponents.get(8).render(g2d);
			for(int i = 28; i < buttons.size(); i++)
				buttons.get(i).render(g2d);
			
			g2d.drawString(String.valueOf(buySeed[0]), buttons.get(28).x+27, buttons.get(28).y+21);
			g2d.drawString(String.valueOf(buySeed[1]), buttons.get(30).x+27, buttons.get(30).y+21);
			g2d.drawString(String.valueOf(buySeed[2]), buttons.get(32).x+27, buttons.get(32).y+21);
			g2d.drawString(String.valueOf(buySeed[3]), buttons.get(34).x+27, buttons.get(34).y+21);
			g2d.drawString(String.valueOf(buySeed[4]), buttons.get(36).x+27, buttons.get(36).y+21);
			g2d.drawString(String.valueOf(buySeed[5]), buttons.get(38).x+27, buttons.get(38).y+21);
		}
		else if(state == 3) {
			buttons.get(27).render(g2d);
			frontComponents.get(2).render(g2d);
		}
	}
	
	public void resetBuyValue() {
		
		this.buySeed = new int[6];
		for(int i = 0; i < 6; i++)
			buySeed[i] = 0;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_E) {
			gameStateManager.backToPreviousState();
		}
	}
	
	boolean mouseIsPressed;
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		mouseIsPressed = true;
		int x = e.getX();
		int y = e.getY();
		
		if(buttons.get(0).isOnButton(x, y)) buttons.get(0).isPressed = true;
		if(buttons.get(1).isOnButton(x, y)) buttons.get(1).isPressed = true;
		if(buttons.get(2).isOnButton(x, y)) buttons.get(2).isPressed = true;
		
		if(state == 1) {
			for(int i = 3; i < 27; i++)
				if(buttons.get(i).isOnButton(x, y)) 
					buttons.get(i).isPressed = true;
		}
		else if(state == 2) {
			for(int i = 28; i < buttons.size(); i++)
				if(buttons.get(i).isOnButton(x, y)) 
					buttons.get(i).isPressed = true;
		}
		else if(state == 3) {
			if(buttons.get(27).isOnButton(x, y)) buttons.get(27).isPressed = true;
		}
		
//		for(GButton i : buttons)
//			if(i.isOnButton(x, y))
//				i.isPressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		mouseIsPressed = false;
		int x = e.getX();
		int y = e.getY();
		
		if(buttons.get(0).isPressed && buttons.get(0).isOnButton(x, y)) {
			state = 1;
		}
		else if(buttons.get(1).isPressed && buttons.get(1).isOnButton(x, y)) {
			state = 2;
		}
		else if(buttons.get(2).isPressed && buttons.get(2).isOnButton(x, y)) {
			state = 3;
		}
		else if(buttons.get(27).isPressed && buttons.get(27).isOnButton(x, y)) {
			System.exit(1);
		}
		else if(buttons.get(28).isPressed && buttons.get(28).isOnButton(x, y)) buySeed[0]++;
		else if(buttons.get(29).isPressed && buttons.get(29).isOnButton(x, y)) buySeed[0]--;
		else if(buttons.get(30).isPressed && buttons.get(30).isOnButton(x, y)) buySeed[1]++;
		else if(buttons.get(31).isPressed && buttons.get(31).isOnButton(x, y)) buySeed[1]--;
		else if(buttons.get(32).isPressed && buttons.get(32).isOnButton(x, y)) buySeed[2]++;
		else if(buttons.get(33).isPressed && buttons.get(33).isOnButton(x, y)) buySeed[2]--;
		else if(buttons.get(34).isPressed && buttons.get(34).isOnButton(x, y)) buySeed[3]++;
		else if(buttons.get(35).isPressed && buttons.get(35).isOnButton(x, y)) buySeed[3]--;
		else if(buttons.get(36).isPressed && buttons.get(36).isOnButton(x, y)) buySeed[4]++;
		else if(buttons.get(37).isPressed && buttons.get(37).isOnButton(x, y)) buySeed[4]--;
		else if(buttons.get(38).isPressed && buttons.get(38).isOnButton(x, y)) buySeed[5]++;
		else if(buttons.get(39).isPressed && buttons.get(39).isOnButton(x, y)) buySeed[5]--;
		for(GButton i : buttons) 
			i.isPressed = false;
	}

}
