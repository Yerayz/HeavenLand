package heavenland.game.world;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import heavenland.entity.Player;
import heavenland.entity.Entity.Direction;
import heavenland.framework.Window;
import heavenland.game.item.Item;
import heavenland.game.item.ItemData.ItemType;
import heavenland.game.object.Crop;
import heavenland.game.object.Object;
import heavenland.game.object.ObjectData;
import heavenland.game.object.Object.ObjectType;
import heavenland.gamestate.Playing;
import heavenland.resource.Res;

public class Region {

	public byte currentReg;
	public ArrayList<Tile[][]> tiles;
	public ArrayList<Object[][]> objects;
	public ArrayList<Tile[][]> terrain;
	
	public int maxRegionX;
	public int maxRegionY;
	
	public Region() {
	}
	
	public Region(byte currentReg) {
		
		this.currentReg = currentReg;
		loadRegion();
		setRegion(currentReg);
	}
	
	public void setRegion(byte currentReg) {
		
		this.currentReg = currentReg;
		this.maxRegionX = Tile.SIZE * tiles.get(currentReg)[0].length;
		this.maxRegionY = Tile.SIZE * tiles.get(currentReg).length;
		if(currentReg == Res.FARMHOUSE) {
			this.maxRegionX += Window.WIDTH;
			this.maxRegionY += Window.HEIGHT;
		}
		
	}
	
	public void loadRegion() {
		
		this.tiles = Res.MAP;
		this.objects = Res.MAP_OBJECT;
		this.terrain = new ArrayList<>();
		this.terrain.add(new Tile[tiles.get(Res.FARM).length][tiles.get(Res.FARM)[0].length]);
	}
	
	public Object[][] getCurrentRegionObject() {
		
		return objects.get(currentReg);
	}
	
	public Tile[][] getCurrentRegionMap() {
		
		return tiles.get(currentReg);
	}
	
	public void renderMap(Graphics2D g2d, Player player) {
		
		try {
			
			for(int worldRow = 0; worldRow < tiles.get(currentReg).length; worldRow++) {
				
				for(int worldCol = 0; worldCol < tiles.get(currentReg)[0].length; worldCol++) {
					
					int worldX = worldCol * Tile.SIZE;
					int worldY = worldRow * Tile.SIZE;
					if(currentReg == Res.FARMHOUSE) {
						worldX += Window.CENTER_SCREEN_X;
						worldY += Window.CENTER_SCREEN_Y;
					}
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
							
						g2d.drawImage(Res.TEXTURES.get(tiles.get(currentReg)[worldRow][worldCol].getID()).getImage(), screenX, screenY, Tile.SIZE, Tile.SIZE, null);
//						g2d.setColor(Color.black);
//						g2d.drawRect(screenX, screenY, Tile.SIZE, Tile.SIZE);
					}
				}
			}

		} catch (Exception e) {
			System.out.println("[Region] Map");
			e.printStackTrace();
		}
	}
	
	public void renderMapObject(Graphics2D g2d, Player player) {
		
		try {
			
			for(int worldRow = 0; worldRow < objects.get(currentReg).length; worldRow++) {
				
				for(int worldCol = 0; worldCol < objects.get(currentReg)[0].length; worldCol++) {
					
					Object currObject = objects.get(currentReg)[worldRow][worldCol];
					
					if(currObject.getID() != 0) {
						int worldX = worldCol * Tile.SIZE;
						int worldY = worldRow * Tile.SIZE;
						if(currentReg == Res.FARMHOUSE) {
							worldX += Window.CENTER_SCREEN_X;
							worldY += Window.CENTER_SCREEN_Y;
						}
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
							if(currObject.isSolid) {
								screenX = screenX-(Res.OBJECT.get(currObject.getID()).solidArea.x*Window.SCALE/Tile.SIZE*Tile.SIZE);
								screenY = screenY-(objHeight-Tile.SIZE)+(Res.OBJECT.get(currObject.getID()).solidArea.height*Window.SCALE-Tile.SIZE)/Tile.SIZE*Tile.SIZE;
							}
							else {
//								screenX = screenX;
								screenY = screenY-(objHeight-Tile.SIZE);
							}
							g2d.drawImage(Res.OBJECT.get(currObject.getID()).getImage(), screenX, screenY, objWidth, objHeight, null);
						}
					}
				}
			}
			
		} catch (Exception e) {
			System.out.println("[Region] Back Object");
			e.printStackTrace();
		}
	}
	
	public void renderMapObjectForeGround(Graphics2D g2d, Player player) {
		
		try {
			
			for(int worldRow = 0; worldRow < objects.get(currentReg).length; worldRow++) {
				
				for(int worldCol = 0; worldCol < objects.get(currentReg)[0].length; worldCol++) {
					
					Object currObject = objects.get(currentReg)[worldRow][worldCol];
					
					if(currObject.getID() != 0) {
						int worldX = worldCol * Tile.SIZE;
						int worldY = worldRow * Tile.SIZE;
						if(currentReg == Res.FARMHOUSE) {
							worldX += Window.CENTER_SCREEN_X;
							worldY += Window.CENTER_SCREEN_Y;
						}
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
							if(currObject.isSolid) {
								screenX = screenX-(Res.OBJECT.get(currObject.getID()).solidArea.x*Window.SCALE/Tile.SIZE*Tile.SIZE);
								screenY = screenY-(objHeight-Tile.SIZE)+(Res.OBJECT.get(currObject.getID()).solidArea.height*Window.SCALE-Tile.SIZE)/Tile.SIZE*Tile.SIZE;
							}
							else {
//								screenX = screenX;
								screenY = screenY-(objHeight-Tile.SIZE);
							}
							g2d.drawImage(Res.OBJECT.get(currObject.getID()).getImage(), screenX, screenY, objWidth, objHeight, null);
						}
					}
				}
			}
			
		} catch (Exception e) {
			System.out.println("[Region] Front Object");
			e.printStackTrace();
		}
	}
	
	public void renderMapTerrain(Graphics2D g2d, Player player) {
		
		if(currentReg != Res.FARMHOUSE) {
			try {
				
				for(int worldRow = 0; worldRow < terrain.get(currentReg).length; worldRow++) {
					
					for(int worldCol = 0; worldCol < terrain.get(currentReg)[0].length; worldCol++) {
						
						Tile currTerrain = terrain.get(currentReg)[worldRow][worldCol];
						
						if(currTerrain != null && currTerrain.getID() != 0) {
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
							if(worldX >= borderLeftX &&
									worldX <= borderRightX &&
									worldY >= borderUpY &&
									worldY <= borderDownY) {
								g2d.drawImage(Res.TERRAIN.get(currTerrain.getID()).getImage(), screenX, screenY, Tile.SIZE, Tile.SIZE, null);
	//							g2d.setColor(Color.black);
	//							g2d.drawRect(screenX, screenY, Tile.SIZE, Tile.SIZE);
							}
						}
					}
				}
				
			} catch (Exception e) {
				System.out.println("[Region] terrain.get(currentReg)");
				e.printStackTrace();
			}
		}
	}
	
	public void addTerrain(Player player, int x, int y, int state) {
		
		int terrainMapX = player.mapX + x - player.screenX;
		int terrainMapY = player.mapY + y - player.screenY;
		int terrainCol = terrainMapX/Tile.SIZE;
		int terrainRow = terrainMapY/Tile.SIZE;
		if (Math.abs(x - player.screenX - Tile.SIZE/2) <= 3*Tile.SIZE/2 && Math.abs(y - player.screenY - Tile.SIZE) <= 3*Tile.SIZE/2 && 
				tiles.get(currentReg)[terrainRow][terrainCol].getID() == Res.LAND && (objects.get(currentReg)[terrainRow][terrainCol].getID() == Res.BLANK || objects.get(currentReg)[terrainRow][terrainCol].objectType == ObjectType.CROP)) {
			if(state == 1) {
				if(terrain.get(currentReg)[terrainRow][terrainCol] == null || terrain.get(currentReg)[terrainRow][terrainCol].getID() == 0)
					terrain.get(currentReg)[terrainRow][terrainCol] = new Tile((byte)state, terrainMapX, terrainMapY, false);
			}
			else if(state == 2) {
				if(terrain.get(currentReg)[terrainRow][terrainCol] != null && terrain.get(currentReg)[terrainRow][terrainCol].getID() == 1)
					terrain.get(currentReg)[terrainRow][terrainCol] = new Tile((byte)state, terrainMapX, terrainMapY, false);
			}
			else
				terrain.get(currentReg)[terrainRow][terrainCol] = new Tile((byte)state, terrainMapX, terrainMapY, false);
		}
	}
	
	public void addCrop(Player player, int x, int y) {
		
		int objectMapX = player.mapX + x - player.screenX;
		int objectMapY = player.mapY + y - player.screenY;
		int objectCol = objectMapX/Tile.SIZE;
		int objectRow = objectMapY/Tile.SIZE;
		objectMapX = objectCol*Tile.SIZE;
		objectMapY = objectRow*Tile.SIZE;
		Item currItem = player.getSelectedItem();
		if(objects.get(currentReg)[objectRow][objectCol] != null && terrain.get(currentReg)[objectRow][objectCol] != null && (terrain.get(currentReg)[objectRow][objectCol].getID() == 1 ||terrain.get(currentReg)[objectRow][objectCol].getID() == 2)) {
			switch(currItem.getID()) {
			case Res.CORN_SEED: objects.get(currentReg)[objectRow][objectCol] = new Crop(Res.CORN_0, objectMapX, objectMapY); break;
			case Res.CARROT_SEED: objects.get(currentReg)[objectRow][objectCol] = new Crop(Res.CARROT_0, objectMapX, objectMapY); break;
			case Res.CABBAGE_SEED: objects.get(currentReg)[objectRow][objectCol] = new Crop(Res.CABBAGE_0, objectMapX, objectMapY); break;
			case Res.EGGPLANT_SEED: objects.get(currentReg)[objectRow][objectCol] = new Crop(Res.EGGPLANT_0, objectMapX, objectMapY); break;
			case Res.RADISH_SEED: objects.get(currentReg)[objectRow][objectCol] = new Crop(Res.RADISH_0, objectMapX, objectMapY); break;
			case Res.CAULIFLOWER_SEED: objects.get(currentReg)[objectRow][objectCol] = new Crop(Res.CAULIFLOWER_0, objectMapX, objectMapY); break;
			}
			currItem.quantity--;
			player.setSelectedItem(currItem);
		}
	}
	
	public void updateCrop() {
		
		for(int worldRow = 0; worldRow < objects.get(Res.FARM).length; worldRow++) {
			
			for(int worldCol = 0; worldCol < objects.get(Res.FARM)[0].length; worldCol++) {
				
				Tile currTerrain = terrain.get(Res.FARM)[worldRow][worldCol];
				Object currObject = objects.get(Res.FARM)[worldRow][worldCol];
				if(currObject.objectType == ObjectType.CROP && currTerrain.getID() == Res.LAND_HOE_WATERED) {
					
					((Crop)currObject).incrementAge();
				}
			}
		}
	}
	
	public void updateTerrain() {
		
		Random rand = new Random();
		for(int worldRow = 0; worldRow < terrain.get(Res.FARM).length; worldRow++) {
			
			for(int worldCol = 0; worldCol < terrain.get(Res.FARM)[0].length; worldCol++) {
				
				Tile currTerrain = terrain.get(Res.FARM)[worldRow][worldCol];
				Object currObject = objects.get(Res.FARM)[worldRow][worldCol];
				
				if(currTerrain != null) {
					
					if(currTerrain.getID() == Res.LAND_HOE_WATERED)
						terrain.get(Res.FARM)[worldRow][worldCol] = new Tile(Res.LAND_HOE, worldCol*Tile.SIZE, worldRow*Tile.SIZE, false);
					if(currObject.getID() == 0) {
						
						if(currTerrain.getID() == Res.LAND_HOE) {
							int isBuried = rand.nextInt(2);
							System.out.println(isBuried);
							if(isBuried == 1)
								terrain.get(Res.FARM)[worldRow][worldCol] = null;
						}
					}
					
				}
			}
		}
	}
	
	public Object checkObjectInteract(Playing playing, Player player, int x, int y) {
		
		int cursorMapX = player.mapX + x - player.screenX;
		int cursorMapY = player.mapY + y - player.screenY;
		if(currentReg == Res.FARMHOUSE) {
			cursorMapX -= Window.CENTER_SCREEN_X;
			cursorMapY -= Window.CENTER_SCREEN_Y;
		}
		for(int worldRow = 0; worldRow < objects.get(currentReg).length; worldRow++) {
			
			for(int worldCol = 0; worldCol < objects.get(currentReg)[0].length; worldCol++) {
				
				Object currObject = objects.get(currentReg)[worldRow][worldCol];
				if(currObject.isInteractable) {
					if(currObject.isInObject(cursorMapX, cursorMapY)) {
						if(currObject.getID() == Res.HOUSE) {
							
							setRegion(Res.FARMHOUSE);
							player.setLocation(192 + Window.CENTER_SCREEN_X, 512 + Window.CENTER_SCREEN_Y);
							player.setDirection(Direction.UP);
						}
						else if(currObject.getID() == Res.BED) {
							
							playing.newDay(1);
						}
						else if(currObject.getID() == Res.SELL_BOX) {
							
						}
						else if(currObject.objectType == ObjectType.CROP) {
							
							Item crop = null;
							switch(currObject.getID()) {
							case Res.CORN_4: crop = new Item(Res.CORN, 2); break;
							case Res.CARROT_4: crop = new Item(Res.CARROT, 1); break;
							case Res.CABBAGE_4: crop = new Item(Res.CABBAGE, 1); break;
							case Res.EGGPLANT_4: crop = new Item(Res.EGGPLANT, 2); break;
							case Res.RADISH_4: crop = new Item(Res.RADISH, 1); break;
							case Res.CAULIFLOWER_4: crop = new Item(Res.CAULIFLOWER, 1); break;
							}
							
							boolean isDuplicate = false;
							for(int i = 0; i < 2; i++) {
								for(int j = 0; j < 12; j++) {
									
									if(player.inventory[i][j] != null && player.inventory[i][j].type == ItemType.CROP && player.inventory[i][j].getID() == crop.getID()) {
										isDuplicate = true;
										player.inventory[i][j].quantity += crop.quantity;
										break;
									}
								}
								if(isDuplicate) break;
							}
							
							boolean isFull = true;
							if(!isDuplicate) {
								for(int i = 0; i < 2; i++) {
									for(int j = 0; j < 12; j++) {
										
										if(player.inventory[i][j] == null) {
											isFull = false;
											player.inventory[i][j] = crop;
											break;
										}
									}
									if(!isFull) break;
								}
							}
							
							if(isDuplicate || !isFull) {
								System.out.println(crop.getID());
								ObjectData object = Res.OBJECT.get(Res.BLANK);
								objects.get(Res.FARM)[worldRow][worldCol] = new Object(Res.BLANK, worldCol*Tile.SIZE, worldRow*Tile.SIZE, object.width, object.height, object.solidArea, object.interactArea,
										object.isSolid, object.isFragile, object.isInteractable);
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	public void leaveRegionCheck(Player player) {
		
		if(currentReg == Res.FARMHOUSE) {
			
			if(player.mapY >= 528+Window.CENTER_SCREEN_Y) {
				
				setRegion(Res.FARM);
				player.setLocation(1968, 560);
				player.setDirection(Direction.DOWN);
			}
		}
	}
	
//	public boolean isOnTile(int x, int y) {
//		
//		if(x )
//	}
}
