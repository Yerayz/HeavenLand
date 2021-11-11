package heavenland.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import heavenland.framework.KeyHandler;
import heavenland.framework.Window;
import heavenland.resource.Res;
import heavenland.world.Tile;

public class Player extends Entity {

	public final int screenX;
	public final int screenY;
	
	public BufferedImage up, down, left, right;
	
	public Player() {
		
		this.screenX = Window.WIDTH/2 - Tile.SIZE/2;
		this.screenY = Window.HEIGHT/2 - Tile.SIZE;
		
		this.up = Res.TEXTURES.get(Res.PLAYER_U);
		this.down = Res.TEXTURES.get(Res.PLAYER_D);
		this.left = Res.TEXTURES.get(Res.PLAYER_L);
		this.right = Res.TEXTURES.get(Res.PLAYER_R);
		
		this.solidArea = new Rectangle(8, 16, 32, 32);
		
		setDefault();
	}
	
	public void setDefault() {
		
		this.mapX = 345;
		this.mapY = 454;
		this.speed = 4;
		this.direction = Direction.DOWN;
	}
	
	public void tick(KeyHandler keyH) {
		
		if(keyH.upPressed == true && keyH.downPressed == false) {
			direction = Direction.UP;
			mapY -= speed;
		}
		if(keyH.downPressed == true && keyH.upPressed == false) {
			direction = Direction.DOWN;
			mapY += speed;
		}
		if(keyH.leftPressed == true && keyH.rightPressed == false) {
			direction = Direction.LEFT;
			mapX -= speed;
		}
		if(keyH.rightPressed == true  && keyH.leftPressed == false) {
			direction = Direction.RIGHT;
			mapX += speed;
		}
	}
	
	public void render(Graphics2D g2d) {
		
		BufferedImage image = null;
		
		switch(direction) {
		case UP:
			image = up;
			break;
		case DOWN:
			image = down;
			break;
		case LEFT:
			image = left;
			break;
		case RIGHT:
			image = right;
			break;
		}
		
		g2d.drawImage(image, screenX, screenY, Tile.SIZE, Tile.SIZE*2, null);
	}
	
}
