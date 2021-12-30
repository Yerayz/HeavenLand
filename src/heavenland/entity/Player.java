package heavenland.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import heavenland.framework.KeyHandler;
import heavenland.framework.Window;
import heavenland.game.item.Item;
import heavenland.game.object.Object;
import heavenland.game.world.Region;
import heavenland.game.world.Tile;
import heavenland.gamestate.Playing;
import heavenland.resource.Res;

public class Player extends Entity {

	public final int absScreenX;
	public final int absScreenY;
	public int screenX;
	public int screenY;
	public boolean isMoving;
	public Item[][] inventory;
	public int selectedItem;
	public int energy;
	public int gold;
	
	int moveCounter;
	
	public Player() {
		
		this.absScreenX = Window.WIDTH/2 - Tile.SIZE/2;
		this.absScreenY = Window.HEIGHT/2 - Tile.SIZE; 
		 
		this.screenX = absScreenX;
		this.screenY = absScreenY;
		
		this.solidArea = new Rectangle(8, 16, 32, 32);
		this.inventory = new Item[2][12];
		
		setDefault();
	}
	
	public void setDefault() {
		
		this.mapX = 432 + Window.CENTER_SCREEN_X;
		this.mapY = 192 + Window.CENTER_SCREEN_Y;
		this.speed = 4;
		this.isMoving = false;
		this.direction = Direction.DOWN;
		this.inventory[0][0] = new Item(Res.WATERING_CAN);
		this.inventory[0][1] = new Item(Res.HOE);
		this.inventory[0][2] = new Item(Res.AXE);
		this.inventory[0][3] = new Item(Res.PICKAXE);
//		this.inventory[0][4] = new Item(Res.SCYTHE);
		this.inventory[0][5] = new Item(Res.CORN_SEED);
		this.inventory[0][6] = new Item(Res.CARROT_SEED);
		this.inventory[0][7] = new Item(Res.CABBAGE_SEED);
		this.inventory[0][8] = new Item(Res.EGGPLANT_SEED);
		this.inventory[0][9] = new Item(Res.RADISH_SEED);
		this.inventory[0][10] = new Item(Res.CAULIFLOWER_SEED);
		this.selectedItem = 0;
		this.energy = 150;
		this.gold = 500;
		this.moveCounter = 1;
	}
	
	public void tick(Region region, boolean upPressed, boolean downPressed, boolean leftPressed, boolean rightPressed) {
		
		move(region, upPressed, downPressed, leftPressed, rightPressed);
		if(Playing.ticks % 10 == 0) {
			moveCounter++;
			if(moveCounter == 5)
				moveCounter = 1;
		}
		
	}
	
	public void render(Graphics2D g2d) {
		
		BufferedImage image = null;
		int imageID = 0;
		
		switch(direction) {
		case UP:
			imageID = Res.PLAYER_U;
			break;
		case DOWN:
			imageID = Res.PLAYER_D;
			break;
		case LEFT:
			imageID = Res.PLAYER_L;
			break;
		case RIGHT:
			imageID = Res.PLAYER_R;
			break;
		}
		if(isMoving)
			image = Res.SPRITE.get(imageID + moveCounter);
		else
			image = Res.SPRITE.get(imageID);
		
		g2d.drawImage(image, screenX, screenY, Tile.SIZE, Tile.SIZE*2, null);
//		g2d.drawRect(screenX+solidArea.x, screenY+solidArea.y+Tile.SIZE, solidArea.width, solidArea.height);
	}
	
	public void move(Region region, boolean upPressed, boolean downPressed, boolean leftPressed, boolean rightPressed) {

		isMoving = false;
		if(upPressed == true && downPressed == false) {
			isMoving = true;
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
			isMoving = true;
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
			isMoving = true;
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
			isMoving = true;
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
	
	public void handleCollisionWithObject(Object o, Region region) {
		if(!o.isSolid)
			return;
		Rectangle playerArea = new Rectangle(mapX+solidArea.x, mapY+solidArea.y+Tile.SIZE, solidArea.width, solidArea.height);
		Rectangle objectArea = new Rectangle(o.x+(o.solidArea.x%16)*Window.SCALE, o.y+o.solidArea.y*Window.SCALE, o.solidArea.width*Window.SCALE, o.solidArea.height*Window.SCALE);
		if(region.currentReg == Res.FARMHOUSE) {
			objectArea.x += Window.CENTER_SCREEN_X;
			objectArea.y += Window.CENTER_SCREEN_Y;
		}
		Rectangle intersection = playerArea.intersection(objectArea);
		if(intersection.isEmpty())
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
	
	public void handleCollisionWithTile(Tile t, Region region) {
		if(!t.getCollision())
			return;
		Rectangle playerArea = new Rectangle(mapX+solidArea.x, mapY+solidArea.y+Tile.SIZE, solidArea.width, solidArea.height);
		Rectangle tileArea = new Rectangle(t.x, t.y, Tile.SIZE, Tile.SIZE);
		if(region.currentReg == Res.FARMHOUSE) {
			tileArea.x += Window.CENTER_SCREEN_X;
			tileArea.y += Window.CENTER_SCREEN_Y;
		}
		Rectangle intersection = playerArea.intersection(tileArea);
		if(intersection.isEmpty())
			return;
			
		if(intersection.width > intersection.height) {
			if(playerArea.y < tileArea.y)
				mapY = tileArea.y - 2*Tile.SIZE;
			else
				mapY = tileArea.y + tileArea.height - solidArea.y - Tile.SIZE;
		}
		else {
			if(playerArea.x < tileArea.x)
				mapX = tileArea.x - Tile.SIZE + solidArea.x;
			else {
				mapX = tileArea.x + tileArea.width - solidArea.x;
//				System.out.println("x right");
			}
		}
	}
	
	public Item getSelectedItem() {
		
		int row = selectedItem/12;
		int col = selectedItem%12;
		return inventory[row][col];
	}
	
	public byte getSelectedItemID() {
		
		if(getSelectedItem() != null) 
			return getSelectedItem().getID();
		return -1;
	}
	
	public void setLocation(int x, int y) {
		
		this.mapX = x;
		this.mapY = y;
	}
	
	public void setDirection(Direction dirr) {
		
		this.direction = dirr;
	}
 }
