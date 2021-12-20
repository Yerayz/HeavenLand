package heavenland.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import heavenland.framework.KeyHandler;
import heavenland.framework.Window;
import heavenland.item.Item;
import heavenland.object.Object;
import heavenland.resource.Res;
import heavenland.world.Region;
import heavenland.world.Tile;

public class Player extends Entity {

	public final int absScreenX;
	public final int absScreenY;
	public int screenX;
	public int screenY;
	public Item[][] inventory;
	public int selectedItem;
	
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
		this.inventory = new Item[2][12];
		
		setDefault();
	}
	
	public void setDefault() {
		
		this.mapX = 1200;
		this.mapY = 1296;
		this.speed = 5;
		this.direction = Direction.DOWN;
		this.inventory[0][0] = new Item(Res.WATERING_CAN);
		this.inventory[0][1] = new Item(Res.AXE);
		this.inventory[0][2] = new Item(Res.PICKAXE);
		this.inventory[0][3] = new Item(Res.SCYTHE);
		this.selectedItem = 0;
	}
	
	public void tick(Region region, boolean upPressed, boolean downPressed, boolean leftPressed, boolean rightPressed) {
		
		move(region, upPressed, downPressed, leftPressed, rightPressed);
		
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
		g2d.drawRect(screenX+solidArea.x, screenY+solidArea.y+Tile.SIZE, solidArea.width, solidArea.height);
	}
	
	public void move(Region region, boolean upPressed, boolean downPressed, boolean leftPressed, boolean rightPressed) {

		if(upPressed == true && downPressed == false) {
			
			direction = Direction.UP;
			mapY -= speed;
			if(mapY < absScreenY) 
				screenY = mapY;
			else if(mapY >= region.maxRegionY - Window.CENTER_SCREEN_Y - Tile.SIZE)
				screenY = mapY - (region.maxRegionY - 2*Window.CENTER_SCREEN_Y);
			else
				screenY = absScreenY;
		}
		if(downPressed == true && upPressed == false) {
			
			direction = Direction.DOWN;
			mapY += speed;
			if(mapY <= absScreenY) 
				screenY = mapY;
			else if(mapY > region.maxRegionY - Window.CENTER_SCREEN_Y - Tile.SIZE)
				screenY = mapY - (region.maxRegionY - 2*Window.CENTER_SCREEN_Y);
			else
				screenY = absScreenY;
		}
		if(leftPressed == true && rightPressed == false) {
			
			direction = Direction.LEFT;
			mapX -= speed;
			if(mapX < absScreenX)
				screenX = mapX;
			else if(mapX >= region.maxRegionX - Window.CENTER_SCREEN_X - Tile.SIZE/2)
				screenX = mapX - (region.maxRegionX - 2*Window.CENTER_SCREEN_X);
			else
				screenX = absScreenX;
		}
		if(rightPressed == true  && leftPressed == false) {
			
			direction = Direction.RIGHT;
			mapX += speed;
			if(mapX <= absScreenX)
				screenX = mapX;
			else if(mapX > region.maxRegionX - Window.CENTER_SCREEN_X - Tile.SIZE/2)
				screenX = mapX - (region.maxRegionX - 2*Window.CENTER_SCREEN_X);
			else
				screenX = absScreenX;
		}
	}
	
	public void handleCollisionWithObject(Object o) {
		Rectangle playerArea = new Rectangle(mapX+solidArea.x, mapY+solidArea.y+Tile.SIZE, solidArea.width, solidArea.height);
		Rectangle objectArea = new Rectangle(o.x+(o.solidArea.x%16)*Window.SCALE, o.y+o.solidArea.y*Window.SCALE, o.solidArea.width*Window.SCALE, o.solidArea.height*Window.SCALE);
		Rectangle intersection = playerArea.intersection(objectArea);
		if(intersection.isEmpty() || !o.isSolid)
			return;
			
		if(intersection.width > intersection.height) {
			if(playerArea.y < objectArea.y)
				mapY = objectArea.y - 2*Tile.SIZE;
			else
				mapY = objectArea.y + objectArea.height - solidArea.y - Tile.SIZE;
		}
		else {
			if(playerArea.x < objectArea.x)
				mapX = objectArea.x - Tile.SIZE + solidArea.x;
			else {
				mapX = objectArea.x + objectArea.width - solidArea.x;
//				System.out.println("x right");
			}
		}
	}
	
}
