package heavenland.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import heavenland.framework.KeyHandler;
import heavenland.framework.Window;
import heavenland.resource.Res;
import heavenland.world.Region;
import heavenland.world.Tile;

public class Player extends Entity {

	public final int absScreenX;
	public final int absScreenY;
	public int screenX;
	public int screenY;
	
	private BufferedImage up, down, left, right;
	
	public Player() {
		
		this.absScreenX = Window.WIDTH/2 - Tile.SIZE/2;
		this.absScreenY = Window.HEIGHT/2 - Tile.SIZE;
		
		this.screenX = absScreenX;
		this.screenY = absScreenY;
		
		this.up = Res.SPRITE.get(Res.PLAYER_U);
		this.down = Res.SPRITE.get(Res.PLAYER_D);
		this.left = Res.SPRITE.get(Res.PLAYER_L);
		this.right = Res.SPRITE.get(Res.PLAYER_R);
		
		this.solidArea = new Rectangle(8, 16, 32, 32);
		
		setDefault();
	}
	
	public void setDefault() {
		
		this.mapX = 1200;
		this.mapY = 1296;
		this.speed = 4;
		this.direction = Direction.DOWN;
	}
	
	public void tick(KeyHandler keyH, Region region) {
		
		if(screenX == absScreenX && screenY == absScreenY)
			System.out.println("aman");
		else
			System.out.println(screenX + " " + screenY + " | " + absScreenX + " " + absScreenY);
		
		if(keyH.upPressed == true && keyH.downPressed == false) {
			
			direction = Direction.UP;
			mapY -= speed;
			if(mapY < absScreenY || mapY >= region.maxRegionY - Window.CENTER_SCREEN_Y - Tile.SIZE) 
				screenY -= speed;
		}
		if(keyH.downPressed == true && keyH.upPressed == false) {
			
			direction = Direction.DOWN;
			mapY += speed;
			if(mapY <= absScreenY || mapY > region.maxRegionY - Window.CENTER_SCREEN_Y - Tile.SIZE) 
				screenY += speed;
		}
		if(keyH.leftPressed == true && keyH.rightPressed == false) {
			
			direction = Direction.LEFT;
			mapX -= speed;
			if(mapX < absScreenX || mapX >= region.maxRegionX - Window.CENTER_SCREEN_X - Tile.SIZE/2)
				screenX -= speed;
		}
		if(keyH.rightPressed == true  && keyH.leftPressed == false) {
			
			direction = Direction.RIGHT;
			mapX += speed;
			if(mapX <= absScreenX || mapX > region.maxRegionX - Window.CENTER_SCREEN_X - Tile.SIZE/2)
				screenX += speed;
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
