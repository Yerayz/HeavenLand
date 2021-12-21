package heavenland.gamestate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import heavenland.entity.Player;
import heavenland.framework.KeyHandler;
import heavenland.framework.Window;
import heavenland.gui.GButton;
import heavenland.gui.GComponent;
import heavenland.gui.GInventButton;
import heavenland.resource.Res;
import heavenland.world.Region;

public class Playing extends GameState {

	private Player player;
	private Region region;
	private ArrayList<GButton> buttons;
	private ArrayList<GComponent> backComponents;
	private ArrayList<GComponent> frontComponents;
	
	public Playing(GameStateManager manager) {
		super(manager);
		
		this.player = new Player();
		this.region = new Region((byte)0);
		this.buttons = new ArrayList<>();
		this.backComponents = new ArrayList<>();
		this.frontComponents = new ArrayList<>();
		
		setInterface();
	}

	@Override
	protected void tick() {
		
		player.tick(region, upPressed, downPressed, leftPressed, rightPressed);
		for(int i = 0; i < region.objects.length; i++) {
			for(int j = 0; j < region.objects[0].length; j++) {
				if(region.objects[i][j].getID() != 0)
					player.handleCollisionWithObject(region.objects[i][j]);
			}
		}
	}

	@Override
	protected void render(Graphics2D g2d) {
		
		region.renderMap(g2d, player);
		region.renderMapObject(g2d, player);
		player.render(g2d);
		region.renderMapObjectForeGround(g2d, player);
		renderInterface(g2d);
	}
	
	public void setInterface() {
		
		BufferedImage image;
		int imageWidth;
		int imageHeight;
		
		image = Res.GUI.get(Res.INVENTORY_BAR);
		imageWidth = image.getWidth()*Window.SCALE;
		imageHeight = image.getHeight()*Window.SCALE;
		backComponents.add(new GComponent(Res.GUI.get(Res.INVENTORY_BAR),(Window.WIDTH-imageWidth)/2, (Window.HEIGHT-imageHeight)-10));
		
		for(int i = 0; i < 12; i++) {
			buttons.add(new GInventButton(Res.GUI.get(Res.INVENTORY_BTN), backComponents.get(0).x+21+i*54, backComponents.get(0).y+21));
		}
		
		updateInterface();
	}
	
	public void updateInterface() {
		GInventButton btn;
		for(int i = 0; i < 12; i++) {
			btn = ((GInventButton)buttons.get(i));
			btn.setItem(player.inventory[0][i]);
			if(player.selectedItem == i)
				btn.isSelected = true;
			else
				btn.isSelected = false;
		}
	}
	
	public void renderInterface(Graphics2D g2d) {
		
		for(GComponent i : backComponents)
			i.render(g2d);
		for(GButton i : buttons)
			i.render(g2d);
	}
	
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = true;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
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
		for(int i = 0; i < 12; i++)
			if(buttons.get(i).isPressed && buttons.get(i).isOnButton(x, y)) {
				player.selectedItem = i;
				updateInterface();
				break;
			}
		
		for(GButton i : buttons) 
			if(i.isOnButton(x, y))
				i.isPressed = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
