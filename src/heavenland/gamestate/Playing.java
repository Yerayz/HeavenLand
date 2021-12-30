package heavenland.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import heavenland.entity.Entity.Direction;
import heavenland.entity.Player;
import heavenland.framework.KeyHandler;
import heavenland.framework.Window;
import heavenland.game.GTime;
import heavenland.game.item.ItemData.ItemType;
import heavenland.game.world.Region;
import heavenland.gui.GButton;
import heavenland.gui.GComponent;
import heavenland.gui.GInventButton;
import heavenland.resource.Res;

public class Playing extends GameState {

	private GTime time;
	private Player player;
	private Region region;
	private ArrayList<GButton> buttons;
	private ArrayList<GComponent> backComponents;
	private ArrayList<GComponent> frontComponents;
	public static int ticks;
	private Font gameFont;
	private Clip clip;
	
	public Playing(GameStateManager manager) {
		super(manager);
		
		this.player = new Player();
		this.region = new Region(Res.FARMHOUSE);
		this.buttons = new ArrayList<>();
		this.backComponents = new ArrayList<>();
		this.frontComponents = new ArrayList<>();
		this.time = new GTime();
		ticks = 0;
		setInterface();
		
		try {
			gameFont = Font.createFont(Font.TRUETYPE_FONT, Res.FONT.get(Res.PRESS_START_2P).openStream());
			gameFont = gameFont.deriveFont(Font.PLAIN, 32);
//			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//			ge.registerFont(font);
		} catch (FontFormatException e) {
			e.printStackTrace();
			gameFont = new Font("TimesRoman", Font.PLAIN, 32);
		} catch (IOException e) {
			e.printStackTrace();
			gameFont = new Font("TimesRoman", Font.PLAIN, 32);
		}
		
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(Res.SOUND.get(Res.DRY));
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void tick() {
		
		updateInterface();
		if(ticks % 20 == 0) time.incrementMinute();
		ticks++;
		if(ticks == 60) {
			System.out.println(time);
			ticks = 0;
		}
		if(time.getHour() == 7 || time.getHour() == 12)
			clip.start();
		
		if(time.getHour() == 18)
			newDay();
		
		player.tick(region, upPressed, downPressed, leftPressed, rightPressed);
		for(int i = 0; i < region.getCurrentRegionObject().length; i++) {
			for(int j = 0; j < region.getCurrentRegionObject()[0].length; j++) {
				if(region.getCurrentRegionObject()[i][j].getID() != 0)
					player.handleCollisionWithObject(region.getCurrentRegionObject()[i][j], region);
			}
		}
		for(int i = 0; i < region.getCurrentRegionMap().length; i++) {
			for(int j = 0; j < region.getCurrentRegionMap()[0].length; j++) {
				if(region.getCurrentRegionMap()[i][j].getID() != 0)
					player.handleCollisionWithTile(region.getCurrentRegionMap()[i][j], region);
			}
		}
		region.leaveRegionCheck(player);
	}

	@Override
	protected void render(Graphics2D g2d) {
		
		region.renderMap(g2d, player);
		region.renderMapTerrain(g2d, player);
		region.renderMapObject(g2d, player);
		player.render(g2d);
		region.renderMapObjectForeGround(g2d, player);
		renderInterface(g2d);
		
		g2d.setFont(gameFont);
		g2d.setColor(Color.black);
		g2d.drawString("DAY " + time.getDay(), Window.WIDTH-240-10+21, 16+10+21+20);
		g2d.setFont(gameFont.deriveFont(Font.PLAIN, 24));
		g2d.drawString(time.toStringGame(), Window.WIDTH-240-10+21, 16+10+21+16+32+12);
		g2d.setFont(gameFont.deriveFont(Font.PLAIN, 16));
		g2d.drawString(String.valueOf(player.gold), 10+12, 26+11);
	}
	
	public void setInterface() {
		
		BufferedImage image;
		int imageWidth;
		int imageHeight;
		
		image = Res.GUI.get(Res.INVENTORY_BAR);
		imageWidth = image.getWidth()*Window.SCALE;
		imageHeight = image.getHeight()*Window.SCALE;
		backComponents.add(new GComponent(Res.GUI.get(Res.INVENTORY_BAR),(Window.WIDTH-imageWidth)/2, (Window.HEIGHT-imageHeight)-10));
		backComponents.add(new GComponent(Res.GUI.get(Res.ENERGY), Window.WIDTH-48-10, Window.HEIGHT-194-10));
		backComponents.add(new GComponent(Res.GUI.get(Res.INFORMATION_BAR), Window.WIDTH-240-10, 10));
		backComponents.add(new GComponent(Res.GUI.get(Res.MONEY_BAR), 10, 10));
		
		frontComponents.add(new GComponent(Res.GUI.get(Res.ENERGY_BAR), Window.WIDTH-39-10, Window.HEIGHT-185-10));
		
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
		
		frontComponents.get(0).height = (backComponents.get(1).height-5)*player.energy/150;
		frontComponents.get(0).y = backComponents.get(1).y+9+(backComponents.get(1).height*Window.SCALE-15)-frontComponents.get(0).height*Window.SCALE;
		
	}
	
	public void renderInterface(Graphics2D g2d) {
		
		for(GComponent i : backComponents)
			i.render(g2d);
		for(GButton i : buttons)
			i.render(g2d);
		for(GComponent i : frontComponents)
			i.render(g2d);
	}
	
	public void newDay() {

		gameStateManager.addState(new FadeOut(gameStateManager));
		time.incrementDay();
		time.resetTime();
		
		player.setLocation(432 + Window.CENTER_SCREEN_X, 192 + Window.CENTER_SCREEN_Y);
		player.setDirection(Direction.DOWN);
		
		region.updateCrop();
		
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
		
		switch(code) {
		case KeyEvent.VK_1: player.selectedItem = 0; break;
		case KeyEvent.VK_2: player.selectedItem = 1; break;
		case KeyEvent.VK_3: player.selectedItem = 2; break;
		case KeyEvent.VK_4: player.selectedItem = 3; break;
		case KeyEvent.VK_5: player.selectedItem = 4; break;
		case KeyEvent.VK_6: player.selectedItem = 5; break;
		case KeyEvent.VK_7: player.selectedItem = 6; break;
		case KeyEvent.VK_8: player.selectedItem = 7; break;
		case KeyEvent.VK_9: player.selectedItem = 8; break;
		case KeyEvent.VK_0: player.selectedItem = 9; break;
		case KeyEvent.VK_MINUS: player.selectedItem = 10; break;
		case KeyEvent.VK_EQUALS: player.selectedItem = 11; break;
		}
		
		if(code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_E) {
			gameStateManager.addState(new Pause(gameStateManager));
			upPressed = false;
			downPressed = false;
			leftPressed = false;
			rightPressed = false;
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
		
		int x = e.getX(); 
		int y = e.getY();
		int clickButton = e.getButton();
		if(clickButton == MouseEvent.BUTTON1) {
			if(player.getSelectedItemID() != -1) {
				
				if(player.getSelectedItemID() == Res.PICKAXE) {
					region.addTerrain(player, x, y, 0);
					player.energy -= 2;
				}
				else if(player.getSelectedItemID() == Res.HOE) {
					region.addTerrain(player, x, y, 1);
					player.energy -= 2;
				}
				else if(player.getSelectedItemID() == Res.WATERING_CAN) {
					region.addTerrain(player, x, y, 2);
					player.energy -= 2;
				}
				else if(player.getSelectedItem().type == ItemType.SEED) {
					region.addCrop(player, x, y);
				}
			}
		}
		else if(clickButton == MouseEvent.BUTTON3) {
			
			
			if(region.checkObjectInteract(player, x, y) == Res.HOUSE) {
				
				region.setRegion(Res.FARMHOUSE);
				player.setLocation(192 + Window.CENTER_SCREEN_X, 512 + Window.CENTER_SCREEN_Y);
				player.setDirection(Direction.UP);
			}
			if(region.checkObjectInteract(player, x, y) == Res.BED) {
				
				newDay();
			}
		}
	}

	public boolean mouseIsPressed;
	@Override
	public void mousePressed(MouseEvent e) {
		
		mouseIsPressed = true;
		int x = e.getX();
		int y = e.getY();
		for(GButton i : buttons)
			if(i.isOnButton(x, y))
				i.isPressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		mouseIsPressed = false;
		int x = e.getX();
		int y = e.getY();
		for(int i = 0; i < 12; i++)
			if(buttons.get(i).isPressed && buttons.get(i).isOnButton(x, y)) {
				player.selectedItem = i;
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
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		
		player.selectedItem += e.getWheelRotation();
		if(player.selectedItem >= 12)
			player.selectedItem = 0;
		else if(player.selectedItem < 0)
			player.selectedItem = 11;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
	
}
