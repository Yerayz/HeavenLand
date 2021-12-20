package heavenland.world;

import java.awt.Color;
import java.awt.Graphics2D;

import heavenland.entity.Player;
import heavenland.framework.Window;
import heavenland.resource.Res;
import heavenland.object.Object;

public class Region {

	private byte currentReg;
	public Tile[][] tiles;
	public Object[][] objects;
	
	public int maxRegionX;
	public int maxRegionY;
	
	public Region() {
	}
	
	public Region(byte currentReg) {
		
		this.currentReg = currentReg;
		loadRegion();
	}
	
	public void setRegion(byte currentReg) {
		
		this.currentReg = currentReg;
	}
	
	public void loadRegion() {
		
		this.tiles = Res.MAP.get(currentReg);
		this.maxRegionX = Tile.SIZE * tiles[0].length;
		this.maxRegionY = Tile.SIZE * tiles.length;
		
		this.objects = Res.MAP_OBJECT.get(currentReg);
	}
	
	
	public void renderMap(Graphics2D g2d, Player player) {
		
		try {
			
			for(int worldRow = 0; worldRow < tiles.length; worldRow++) {
				
				for(int worldCol = 0; worldCol < tiles[0].length; worldCol++) {
					
					int worldX = worldCol * Tile.SIZE;
					int worldY = worldRow * Tile.SIZE;
					int screenX;
					int screenY;
					int borderLeftX, borderRightX;
					int borderUpY, borderDownY;
					
					if(player.mapX < player.absScreenX) {
						borderLeftX = 0;
						borderRightX = Window.WIDTH;
						screenX = worldX;
					}
					else if(player.mapX > maxRegionX - player.absScreenX - Tile.SIZE) {
//						System.out.println("border");
						borderLeftX = maxRegionX - Window.WIDTH;
						borderRightX = maxRegionX;
						screenX = worldX - borderLeftX;
					}
					else {
//						System.out.println("Normal");
						borderLeftX = player.mapX - player.absScreenX - Tile.SIZE;
						borderRightX = player.mapX + player.absScreenX + Tile.SIZE;
						screenX = worldX - player.mapX + player.absScreenX;
					}
					
					if(player.mapY < player.absScreenY) {
						borderUpY = 0;
						borderDownY = Window.HEIGHT;
						screenY = worldY;
					}
					else if(player.mapY > maxRegionY - player.absScreenY - 2*Tile.SIZE) {
						borderUpY = maxRegionY - Window.HEIGHT;
						borderDownY = maxRegionY;
						screenY = worldY - borderUpY;
					}
					else {
						borderUpY = player.mapY - player.absScreenY - Tile.SIZE;
						borderDownY = player.mapY + player.absScreenY + 2*Tile.SIZE;
						screenY = worldY - player.mapY + player.absScreenY;
					}
						
					if(worldX >= borderLeftX &&
							worldX <= borderRightX &&
							worldY >= borderUpY &&
							worldY <= borderDownY) {
							
						g2d.drawImage(Res.TEXTURES.get(tiles[worldRow][worldCol].getID()).getImage(), screenX, screenY, Tile.SIZE, Tile.SIZE, null);
						g2d.setColor(Color.black);
						g2d.drawRect(screenX, screenY, Tile.SIZE, Tile.SIZE);
					}
				}
			}

		} catch (Exception e) {
			System.out.println("[Region]");
			e.printStackTrace();
		}
	}
	
	public void renderMapObject(Graphics2D g2d, Player player) {
		
		try {
			
			for(int worldRow = 0; worldRow < objects.length; worldRow++) {
				
				for(int worldCol = 0; worldCol < objects[0].length; worldCol++) {
					
					Object currObject = objects[worldRow][worldCol];
					
					if(currObject.getID() != 0) {
						int worldX = worldCol * Tile.SIZE;
						int worldY = worldRow * Tile.SIZE;
						int screenX;
						int screenY;
						int borderLeftX, borderRightX;
						int borderUpY, borderDownY;
						
						if(player.mapX < player.absScreenX) {
							borderLeftX = 0;
							borderRightX = Window.WIDTH;
							screenX = worldX;
						}
						else if(player.mapX > maxRegionX - player.absScreenX - Tile.SIZE) {
							borderLeftX = maxRegionX - Window.WIDTH;
							borderRightX = maxRegionX;
							screenX = worldX - borderLeftX;
						}
						else {
							borderLeftX = player.mapX - player.absScreenX - Tile.SIZE;
							borderRightX = player.mapX + player.absScreenX + Tile.SIZE;
							screenX = worldX - player.mapX + player.absScreenX;
						}
						
						if(player.mapY < player.absScreenY) {
							borderUpY = 0;
							borderDownY = Window.HEIGHT;
							screenY = worldY;
						}
						else if(player.mapY > maxRegionY - player.absScreenY - 2*Tile.SIZE) {
							borderUpY = maxRegionY - Window.HEIGHT;
							borderDownY = maxRegionY;
							screenY = worldY - borderUpY;
						}
						else {
							borderUpY = player.mapY - player.absScreenY - Tile.SIZE;
							borderDownY = player.mapY + player.absScreenY + 2*Tile.SIZE;
							screenY = worldY - player.mapY + player.absScreenY;
						}
//							
//						if(worldX >= borderLeftX &&
//								worldX <= borderRightX &&
//								worldY >= borderUpY &&
//								worldY <= borderDownY) {
						if(player.mapY+Tile.SIZE+player.solidArea.y > currObject.y) {
							int objWidth = Res.OBJECT.get(currObject.getID()).width*Window.SCALE;
							int objHeight = Res.OBJECT.get(currObject.getID()).height*Window.SCALE;
							screenX = screenX-(Res.OBJECT.get(currObject.getID()).solidArea.x*Window.SCALE/Tile.SIZE*Tile.SIZE);
							screenY = screenY-(objHeight-Tile.SIZE)+(Res.OBJECT.get(currObject.getID()).solidArea.height*Window.SCALE-Tile.SIZE)/Tile.SIZE*Tile.SIZE;
							
							g2d.drawImage(Res.OBJECT.get(currObject.getID()).getImage(), screenX, screenY, objWidth, objHeight, null);
						}
					}
				}
			}
			
		} catch (Exception e) {
			System.out.println("[Region]");
			e.printStackTrace();
		}
	}
	
public void renderMapObjectForeGround(Graphics2D g2d, Player player) {
		
		try {
			
			for(int worldRow = 0; worldRow < objects.length; worldRow++) {
				
				for(int worldCol = 0; worldCol < objects[0].length; worldCol++) {
					
					Object currObject = objects[worldRow][worldCol];
					
					if(currObject.getID() != 0) {
						int worldX = worldCol * Tile.SIZE;
						int worldY = worldRow * Tile.SIZE;
						int screenX;
						int screenY;
						int borderLeftX, borderRightX;
						int borderUpY, borderDownY;
						
						if(player.mapX < player.absScreenX) {
							borderLeftX = 0;
							borderRightX = Window.WIDTH;
							screenX = worldX;
						}
						else if(player.mapX > maxRegionX - player.absScreenX - Tile.SIZE) {
							borderLeftX = maxRegionX - Window.WIDTH;
							borderRightX = maxRegionX;
							screenX = worldX - borderLeftX;
						}
						else {
							borderLeftX = player.mapX - player.absScreenX - Tile.SIZE;
							borderRightX = player.mapX + player.absScreenX + Tile.SIZE;
							screenX = worldX - player.mapX + player.absScreenX;
						}
						
						if(player.mapY < player.absScreenY) {
							borderUpY = 0;
							borderDownY = Window.HEIGHT;
							screenY = worldY;
						}
						else if(player.mapY > maxRegionY - player.absScreenY - 2*Tile.SIZE) {
							borderUpY = maxRegionY - Window.HEIGHT;
							borderDownY = maxRegionY;
							screenY = worldY - borderUpY;
						}
						else {
							borderUpY = player.mapY - player.absScreenY - Tile.SIZE;
							borderDownY = player.mapY + player.absScreenY + 2*Tile.SIZE;
							screenY = worldY - player.mapY + player.absScreenY;
						}
//							
//						if(worldX >= borderLeftX &&
//								worldX <= borderRightX &&
//								worldY >= borderUpY &&
//								worldY <= borderDownY) {
						if(player.mapY+Tile.SIZE+player.solidArea.y <= currObject.y) {
							int objWidth = Res.OBJECT.get(currObject.getID()).width*Window.SCALE;
							int objHeight = Res.OBJECT.get(currObject.getID()).height*Window.SCALE;
							screenX = screenX-(Res.OBJECT.get(currObject.getID()).solidArea.x*Window.SCALE/Tile.SIZE*Tile.SIZE);
							screenY = screenY-(objHeight-Tile.SIZE)+(Res.OBJECT.get(currObject.getID()).solidArea.height*Window.SCALE-Tile.SIZE)/Tile.SIZE*Tile.SIZE;
							
							g2d.drawImage(Res.OBJECT.get(currObject.getID()).getImage(), screenX, screenY, objWidth, objHeight, null);
						}
					}
				}
			}
			
		} catch (Exception e) {
			System.out.println("[Region]");
			e.printStackTrace();
		}
	}
}
