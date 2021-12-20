package heavenland.gamestate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import heavenland.entity.Player;
import heavenland.framework.KeyHandler;
import heavenland.framework.Window;
import heavenland.resource.Res;
import heavenland.world.Region;

public class Playing extends GameState {

	private Player player;
	private Region region;
	private GameInterface gameI;
	
	public Playing(GameStateManager manager) {
		super(manager);
		
		this.player = new Player();
		this.region = new Region((byte)0);
		this.gameI = new GameInterface(player);
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
		gameI.render(g2d);
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
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	class GameInterface {
		
		Player player;
		
		public GameInterface(Player player) {
			
			this.player = player;
		}
		
		public void render(Graphics2D g2d) {
			
			BufferedImage image;
			int imageWidth;
			int imageHeight;
			
			// INVENTORY
			image = Res.GUI.get(Res.INVENTORY);
			imageWidth = image.getWidth()*Window.SCALE;
			imageHeight = image.getHeight()*Window.SCALE;
			g2d.drawImage(image, (Window.WIDTH-imageWidth)/2, (Window.HEIGHT-imageHeight)-10, imageWidth, imageHeight, null);
			for(int i = 0; i < 12; i++) {
				if(player.inventory[0][i] != null)
					g2d.drawImage(Res.ITEM.get(player.inventory[0][i].getID()).getImage(), (Window.WIDTH-imageWidth)/2+24+i*54, 
							(Window.HEIGHT-imageHeight)-10+24, 48, 48, null);
				if(player.selectedItem == i) {
					g2d.drawImage(Res.GUI.get(Res.SELECTED), (Window.WIDTH-imageWidth)/2+21+i*48, 
							(Window.HEIGHT-imageHeight)-10+21, 54, 54, null);
				}
			}
		}
	}

}
