package heavenland.resource;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import heavenland.game.item.ItemData;
import heavenland.game.item.ItemData.ItemType;
import heavenland.game.object.Object;
import heavenland.game.object.ObjectData;
import heavenland.game.world.RegionData;
import heavenland.game.world.Tile;
import heavenland.game.world.TileData;

public class Loader {

	public void load() {
		
		// ICON
		try {
			
			Res.ICON.add(ImageIO.read(getClass().getResourceAsStream("/gui/icon_16.png")));
			Res.ICON.add(ImageIO.read(getClass().getResourceAsStream("/gui/icon_32.png")));
			Res.ICON.add(ImageIO.read(getClass().getResourceAsStream("/gui/icon_64.png")));
			Res.ICON.add(ImageIO.read(getClass().getResourceAsStream("/gui/icon_128.png")));
			Res.ICON.add(ImageIO.read(getClass().getResourceAsStream("/gui/icon_512.png")));
			
		} catch (IOException e) {
			System.out.println("[Loader] : Can't load icon / file not found");
			e.printStackTrace();
		}
		
		// GUI
		try {
			
			Res.GUI.add(ImageIO.read(getClass().getResourceAsStream("/gui/mainmenu_bg.jpeg")));
			Res.GUI.add(ImageIO.read(getClass().getResourceAsStream("/gui/logo menu.png")));
			BufferedImage btnSheet = ImageIO.read(getClass().getResourceAsStream("/gui/button_tilesheet.png"));
			Res.GUI.add(btnSheet.getSubimage(0, 0, 64, 16));
			Res.GUI.add(btnSheet.getSubimage(64, 0, 64, 16));
			Res.GUI.add(btnSheet.getSubimage(0, 16, 64, 16));
			Res.GUI.add(btnSheet.getSubimage(64, 16, 64, 16));
			Res.GUI.add(btnSheet.getSubimage(0, 32, 64, 16));
			Res.GUI.add(btnSheet.getSubimage(64, 32, 64, 16));
			
			Res.GUI.add(ImageIO.read(getClass().getResourceAsStream("/gui/inventory_bar.png")));
			Res.GUI.add(ImageIO.read(getClass().getResourceAsStream("/gui/inventory_button.png")));
			Res.GUI.add(ImageIO.read(getClass().getResourceAsStream("/gui/selected.png")));
			BufferedImage energyBar = ImageIO.read(getClass().getResourceAsStream("/gui/energy_bar.png"));
			Res.GUI.add(energyBar.getSubimage(0, 0, 11, 64));
			Res.GUI.add(energyBar.getSubimage(11, 3, 5, 59));
			Res.GUI.add(ImageIO.read(getClass().getResourceAsStream("/gui/information_bar.png")));
			Res.GUI.add(ImageIO.read(getClass().getResourceAsStream("/gui/money_bar.png")));
			
		} catch (IOException e) {
			System.out.println("[Loader] : Can't load gui / file not found");
			e.printStackTrace();
		}
		
		// TILES
		try {
			
			BufferedImage grassSheet = ImageIO.read(getClass().getResourceAsStream("/tile/grass_tilesheet.png"));
			
			Res.TEXTURES.add(new TileData(grassSheet.getSubimage(16, 16, 16, 16), false));
			Res.TEXTURES.add(Res.GRASS, new TileData(ImageIO.read(getClass().getResourceAsStream("/tile/grass.png")), false));
			Res.TEXTURES.add(new TileData(grassSheet.getSubimage(0, 0, 16, 16), false));
			Res.TEXTURES.add(new TileData(grassSheet.getSubimage(16, 0, 16, 16), false));
			Res.TEXTURES.add(new TileData(grassSheet.getSubimage(32, 0, 16, 16), false));
			Res.TEXTURES.add(new TileData(grassSheet.getSubimage(0, 16, 16, 16), false));
			Res.TEXTURES.add(new TileData(grassSheet.getSubimage(32, 16, 16, 16), false));
			Res.TEXTURES.add(new TileData(grassSheet.getSubimage(0, 32, 16, 16), false));
			Res.TEXTURES.add(new TileData(grassSheet.getSubimage(16, 32, 16, 16), false));
			Res.TEXTURES.add(new TileData(grassSheet.getSubimage(32, 32, 16, 16), false));
			Res.TEXTURES.add(new TileData(grassSheet.getSubimage(48, 0, 16, 16), false));
			Res.TEXTURES.add(new TileData(grassSheet.getSubimage(64, 0, 16, 16), false));
			Res.TEXTURES.add(new TileData(grassSheet.getSubimage(48, 16, 16, 16), false));
			Res.TEXTURES.add(new TileData(grassSheet.getSubimage(64, 16, 16, 16), false));
			
			BufferedImage floorSheet = ImageIO.read(getClass().getResourceAsStream("/tile/house_floor.png"));
			Res.TEXTURES.add(new TileData(floorSheet.getSubimage(0, 0, 16, 16), false));
			Res.TEXTURES.add(new TileData(floorSheet.getSubimage(16, 0, 16, 16), false));
			Res.TEXTURES.add(new TileData(floorSheet.getSubimage(32, 0, 16, 16), false));
			Res.TEXTURES.add(new TileData(floorSheet.getSubimage(0, 16, 16, 16), false));
			Res.TEXTURES.add(new TileData(floorSheet.getSubimage(16, 16, 16, 16), false));
			Res.TEXTURES.add(new TileData(floorSheet.getSubimage(32, 16, 16, 16), false));
			Res.TEXTURES.add(new TileData(floorSheet.getSubimage(0, 32, 16, 16), false));
			Res.TEXTURES.add(new TileData(floorSheet.getSubimage(16, 32, 16, 16), false));
			Res.TEXTURES.add(new TileData(floorSheet.getSubimage(32, 32, 16, 16), false));
			
			BufferedImage wallpaperSheet = ImageIO.read(getClass().getResourceAsStream("/tile/house_wallpaper.png"));
			Res.TEXTURES.add(new TileData(wallpaperSheet.getSubimage(0, 0, 16, 16), true));
			Res.TEXTURES.add(new TileData(wallpaperSheet.getSubimage(0, 16, 16, 16), true));
			Res.TEXTURES.add(new TileData(wallpaperSheet.getSubimage(0, 32, 16, 16), true));
			
			BufferedImage wallSheet = ImageIO.read(getClass().getResourceAsStream("/tile/house_wall.png"));
			Res.TEXTURES.add(new TileData(wallSheet.getSubimage(0, 0, 16, 16), true));
			Res.TEXTURES.add(new TileData(wallSheet.getSubimage(16, 0, 16, 16), true));
			Res.TEXTURES.add(new TileData(wallSheet.getSubimage(32, 0, 16, 16), true));
			Res.TEXTURES.add(new TileData(wallSheet.getSubimage(0, 16, 16, 16), true));
			Res.TEXTURES.add(new TileData(wallSheet.getSubimage(32, 16, 16, 16), true));
			Res.TEXTURES.add(new TileData(wallSheet.getSubimage(0, 32, 16, 16), true));
			Res.TEXTURES.add(new TileData(wallSheet.getSubimage(16, 32, 16, 16), true));
			Res.TEXTURES.add(new TileData(wallSheet.getSubimage(32, 32, 16, 16), true));
			Res.TEXTURES.add(new TileData(wallSheet.getSubimage(48, 0, 16, 16), true));
			Res.TEXTURES.add(new TileData(wallSheet.getSubimage(64, 0, 16, 16), true));
			Res.TEXTURES.add(new TileData(wallSheet.getSubimage(16, 16, 16, 16), true));
			
//			Res.TEXTURES.add(Res.LAND, new TileData(ImageIO.read(getClass().getResourceAsStream("/tile/land.png")), false));
//			Res.TEXTURES.add(Res.GRASS, new TileData(ImageIO.read(getClass().getResourceAsStream("/tile/grass.png")), true));
//			Res.TEXTURES.add(Res.LAND_HOE, new TileData(ImageIO.read(getClass().getResourceAsStream("/terrain/land_hoe.png")), true));
//			Res.TEXTURES.add(Res.LAND_HOE_WATERED, new TileData(ImageIO.read(getClass().getResourceAsStream("/terrain/land_hoe_watered.png")), true));
			
		} catch (IOException e) {
			System.out.println("[Loader] : Can't load tiles / file not found");
			e.printStackTrace();
		}
		
		// TERRAIN
		try {
			
			Res.TERRAIN.add(Res.BLANK, new TileData(ImageIO.read(getClass().getResourceAsStream("/tile/blank.png")), false));
			Res.TERRAIN.add(Res.LAND_HOE, new TileData(ImageIO.read(getClass().getResourceAsStream("/terrain/land_hoe.png")), false));
			Res.TERRAIN.add(Res.LAND_HOE_WATERED, new TileData(ImageIO.read(getClass().getResourceAsStream("/terrain/land_hoe_watered.png")), false));
			
		} catch (IOException e) {
			System.out.println("[Loader] : Can't load tiles / file not found");
			e.printStackTrace();
		}
		
		// OBJECT
		try {
			Res.OBJECT.add(new ObjectData(ImageIO.read(getClass().getResourceAsStream("/tile/blank.png")), 16, 16, false, false, false));
			
			BufferedImage fenceSheet = ImageIO.read(getClass().getResourceAsStream("/object/fence_tilesheet.png"));
			
			Res.OBJECT.add(new ObjectData(fenceSheet.getSubimage(0, 0, 16, 32), 16, 32, new Rectangle(4, 5, 8, 7), true, false, false));
			Res.OBJECT.add(new ObjectData(fenceSheet.getSubimage(16, 0, 16, 32), 16, 32, new Rectangle(4, 5, 12, 7), true, false, false));
			Res.OBJECT.add(new ObjectData(fenceSheet.getSubimage(32, 0, 16, 32), 16, 32, new Rectangle(0, 5, 16, 7), true, false, false));
			Res.OBJECT.add(new ObjectData(fenceSheet.getSubimage(48, 0, 16, 32), 16, 32, new Rectangle(0, 5, 12, 7), true, false, false));
			Res.OBJECT.add(new ObjectData(fenceSheet.getSubimage(64, 0, 16, 32), 16, 32, new Rectangle(4, 5, 8, 7), true, false, false));
			Res.OBJECT.add(new ObjectData(fenceSheet.getSubimage(80, 0, 16, 32), 16, 32, new Rectangle(4, 5, 12, 7), true, false, false));
			Res.OBJECT.add(new ObjectData(fenceSheet.getSubimage(96, 0, 16, 32), 16, 32, new Rectangle(0, 5, 16, 7), true, false, false));
			Res.OBJECT.add(new ObjectData(fenceSheet.getSubimage(112, 0, 16, 32), 16, 32, new Rectangle(0, 5, 12, 7), true, false, false));
			
			Rectangle treeArea = new Rectangle(16, 0, 16, 16);
			Res.OBJECT.add(new ObjectData(ImageIO.read(getClass().getResourceAsStream("/object/tree.png")), 48, 80, treeArea, true, true, false));
			
			Res.OBJECT.add(new ObjectData(ImageIO.read(getClass().getResourceAsStream("/object/house.png")), 112, 128, new Rectangle(0, 0, 112, 32), new Rectangle(31, 0, 16, 32), true, false, true));
			Res.OBJECT.add(new ObjectData(ImageIO.read(getClass().getResourceAsStream("/object/mailbox.png")), 16, 32, new Rectangle(2, 8, 11, 7), true, false, false));
			
			BufferedImage cropSheet = ImageIO.read(getClass().getResourceAsStream("/object/crop_objects.png"));
			Res.OBJECT.add(new ObjectData(cropSheet.getSubimage(0, 0, 16, 32), 16, 32, false, true, false));
			Res.OBJECT.add(new ObjectData(cropSheet.getSubimage(16, 0, 16, 32), 16, 32, false, true, false));
			Res.OBJECT.add(new ObjectData(cropSheet.getSubimage(32, 0, 16, 32), 16, 32, false, true, false));
			Res.OBJECT.add(new ObjectData(cropSheet.getSubimage(48, 0, 16, 32), 16, 32, false, true, false));
			Res.OBJECT.add(new ObjectData(cropSheet.getSubimage(64, 0, 16, 32), 16, 32, false, true, false));
			
			Res.OBJECT.add(new ObjectData(cropSheet.getSubimage(0, 32, 16, 32), 16, 32, false, true, false));
			Res.OBJECT.add(new ObjectData(cropSheet.getSubimage(16, 32, 16, 32), 16, 32, false, true, false));
			Res.OBJECT.add(new ObjectData(cropSheet.getSubimage(32, 32, 16, 32), 16, 32, false, true, false));
			Res.OBJECT.add(new ObjectData(cropSheet.getSubimage(48, 32, 16, 32), 16, 32, false, true, false));
			Res.OBJECT.add(new ObjectData(cropSheet.getSubimage(64, 32, 16, 32), 16, 32, false, true, false));
			
			Res.OBJECT.add(new ObjectData(cropSheet.getSubimage(0, 64, 16, 32), 16, 32, false, true, false));
			Res.OBJECT.add(new ObjectData(cropSheet.getSubimage(16, 64, 16, 32), 16, 32, false, true, false));
			Res.OBJECT.add(new ObjectData(cropSheet.getSubimage(32, 64, 16, 32), 16, 32, false, true, false));
			Res.OBJECT.add(new ObjectData(cropSheet.getSubimage(48, 64, 16, 32), 16, 32, false, true, false));
			Res.OBJECT.add(new ObjectData(cropSheet.getSubimage(64, 64, 16, 32), 16, 32, false, true, false));
			
			Res.OBJECT.add(new ObjectData(cropSheet.getSubimage(0, 96, 16, 32), 16, 32, false, true, false));
			Res.OBJECT.add(new ObjectData(cropSheet.getSubimage(16, 96, 16, 32), 16, 32, false, true, false));
			Res.OBJECT.add(new ObjectData(cropSheet.getSubimage(32, 96, 16, 32), 16, 32, false, true, false));
			Res.OBJECT.add(new ObjectData(cropSheet.getSubimage(48, 96, 16, 32), 16, 32, false, true, false));
			Res.OBJECT.add(new ObjectData(cropSheet.getSubimage(64, 96, 16, 32), 16, 32, false, true, false));
			
			Res.OBJECT.add(new ObjectData(cropSheet.getSubimage(0, 128, 16, 32), 16, 32, false, true, false));
			Res.OBJECT.add(new ObjectData(cropSheet.getSubimage(16, 128, 16, 32), 16, 32, false, true, false));
			Res.OBJECT.add(new ObjectData(cropSheet.getSubimage(32, 128, 16, 32), 16, 32, false, true, false));
			Res.OBJECT.add(new ObjectData(cropSheet.getSubimage(48, 128, 16, 32), 16, 32, false, true, false));
			Res.OBJECT.add(new ObjectData(cropSheet.getSubimage(64, 128, 16, 32), 16, 32, false, true, false));
			
			Res.OBJECT.add(new ObjectData(cropSheet.getSubimage(0, 160, 16, 32), 16, 32, false, true, false));
			Res.OBJECT.add(new ObjectData(cropSheet.getSubimage(16, 160, 16, 32), 16, 32, false, true, false));
			Res.OBJECT.add(new ObjectData(cropSheet.getSubimage(32, 160, 16, 32), 16, 32, false, true, false));
			Res.OBJECT.add(new ObjectData(cropSheet.getSubimage(48, 160, 16, 32), 16, 32, false, true, false));
			Res.OBJECT.add(new ObjectData(cropSheet.getSubimage(64, 160, 16, 32), 16, 32, false, true, false));
			
			Res.OBJECT.add(new ObjectData(ImageIO.read(getClass().getResourceAsStream("/object/bed.png")), 32, 64, new Rectangle(0, 0, 32, 48), true, false, true));

		} catch (IOException e) {
			System.out.println("[Loader] : Can't load objects / file not found");
			e.printStackTrace();
		}
		
		//ITEM
		try {
			
			BufferedImage toolSheet = ImageIO.read(getClass().getResourceAsStream("/item/tools_tilesheet.png"));
			Res.ITEM.add(new ItemData(toolSheet.getSubimage(0, 0, 16, 16), ItemType.TOOL, false));
			Res.ITEM.add(new ItemData(toolSheet.getSubimage(16, 0, 16, 16), ItemType.TOOL, false));
			Res.ITEM.add(new ItemData(toolSheet.getSubimage(32, 0, 16, 16), ItemType.TOOL, false));
			Res.ITEM.add(new ItemData(toolSheet.getSubimage(48, 0, 16, 16), ItemType.TOOL, false));
			Res.ITEM.add(new ItemData(toolSheet.getSubimage(64, 0, 16, 16), ItemType.TOOL, false));
			
			BufferedImage cropSheet = ImageIO.read(getClass().getResourceAsStream("/item/crop_items.png"));
			Res.ITEM.add(new ItemData(cropSheet.getSubimage(0, 0, 16, 16), ItemType.CROP, true));
			Res.ITEM.add(new ItemData(cropSheet.getSubimage(16, 0, 16, 16), ItemType.SEED, true));
			Res.ITEM.add(new ItemData(cropSheet.getSubimage(0, 16, 16, 16), ItemType.CROP, true));
			Res.ITEM.add(new ItemData(cropSheet.getSubimage(16, 16, 16, 16), ItemType.SEED, true));
			Res.ITEM.add(new ItemData(cropSheet.getSubimage(0, 32, 16, 16), ItemType.CROP, true));
			Res.ITEM.add(new ItemData(cropSheet.getSubimage(16, 32, 16, 16), ItemType.SEED, true));
			Res.ITEM.add(new ItemData(cropSheet.getSubimage(0, 48, 16, 16), ItemType.CROP, true));
			Res.ITEM.add(new ItemData(cropSheet.getSubimage(16, 48, 16, 16), ItemType.SEED, true));
			Res.ITEM.add(new ItemData(cropSheet.getSubimage(0, 64, 16, 16), ItemType.CROP, true));
			Res.ITEM.add(new ItemData(cropSheet.getSubimage(16, 64, 16, 16), ItemType.SEED, true));
			Res.ITEM.add(new ItemData(cropSheet.getSubimage(0, 80, 16, 16), ItemType.CROP, true));
			Res.ITEM.add(new ItemData(cropSheet.getSubimage(16, 80, 16, 16), ItemType.SEED, true));
			
		} catch (IOException e) {
			System.out.println("[Loader] : Can't load items / file not found");
			e.printStackTrace();
		}
		
		// SPRITE
		try {
			
			Res.SPRITE.add(Res.PLAYER_U, ImageIO.read(getClass().getResourceAsStream("/entity/player_up.png")));
			Res.SPRITE.add(Res.PLAYER_U_1, ImageIO.read(getClass().getResourceAsStream("/entity/player_up_1.png")));
			Res.SPRITE.add(Res.PLAYER_U_2, ImageIO.read(getClass().getResourceAsStream("/entity/player_up_2.png")));
			Res.SPRITE.add(Res.PLAYER_U_3, ImageIO.read(getClass().getResourceAsStream("/entity/player_up_3.png")));
			Res.SPRITE.add(Res.PLAYER_U_4, ImageIO.read(getClass().getResourceAsStream("/entity/player_up_4.png")));
			Res.SPRITE.add(Res.PLAYER_D, ImageIO.read(getClass().getResourceAsStream("/entity/player_down.png")));
			Res.SPRITE.add(Res.PLAYER_D_1, ImageIO.read(getClass().getResourceAsStream("/entity/player_down_1.png")));
			Res.SPRITE.add(Res.PLAYER_D_2, ImageIO.read(getClass().getResourceAsStream("/entity/player_down_2.png")));
			Res.SPRITE.add(Res.PLAYER_D_3, ImageIO.read(getClass().getResourceAsStream("/entity/player_down_3.png")));
			Res.SPRITE.add(Res.PLAYER_D_4, ImageIO.read(getClass().getResourceAsStream("/entity/player_down_4.png")));
			Res.SPRITE.add(Res.PLAYER_L, ImageIO.read(getClass().getResourceAsStream("/entity/player_left.png")));
			Res.SPRITE.add(Res.PLAYER_L_1, ImageIO.read(getClass().getResourceAsStream("/entity/player_left_1.png")));
			Res.SPRITE.add(Res.PLAYER_L_2, ImageIO.read(getClass().getResourceAsStream("/entity/player_left_2.png")));
			Res.SPRITE.add(Res.PLAYER_L_3, ImageIO.read(getClass().getResourceAsStream("/entity/player_left_3.png")));
			Res.SPRITE.add(Res.PLAYER_L_4, ImageIO.read(getClass().getResourceAsStream("/entity/player_left_4.png")));
			Res.SPRITE.add(Res.PLAYER_R, ImageIO.read(getClass().getResourceAsStream("/entity/player_right.png")));
			Res.SPRITE.add(Res.PLAYER_R_1, ImageIO.read(getClass().getResourceAsStream("/entity/player_right_1.png")));
			Res.SPRITE.add(Res.PLAYER_R_2, ImageIO.read(getClass().getResourceAsStream("/entity/player_right_2.png")));
			Res.SPRITE.add(Res.PLAYER_R_3, ImageIO.read(getClass().getResourceAsStream("/entity/player_right_3.png")));
			Res.SPRITE.add(Res.PLAYER_R_4, ImageIO.read(getClass().getResourceAsStream("/entity/player_right_4.png")));
			
		} catch (IOException e) {
			System.out.println("[Loader] : Can't load sprites / file not found");
			e.printStackTrace();
		}
		
		// REGION
		try {
			// MAP
			InputStream is = getClass().getResourceAsStream("/map/farm.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			String temp[] = br.readLine().split(" ");
			int regionHeight = Integer.parseInt(temp[0]);
			int regionWidth = Integer.parseInt(temp[1]);
			
//			System.out.println(regionWidth + " " + regionHeight);
			
			Tile[][] tiles = new Tile[regionHeight][regionWidth];
			
			for(int row = 0; row < regionHeight; row++) {
				
				String numbers[] = br.readLine().split(" ");
//				System.out.println(numbers.length);
//				System.out.println(row);
				
				for(int col = 0; col < regionWidth; col++) {

					byte tileID = (byte) Integer.parseInt(numbers[col]);
					tiles[row][col] = new Tile(tileID, col*Tile.SIZE, row*Tile.SIZE, Res.TEXTURES.get(tileID).isSolid());
				}

			}
			br.close();
			Res.MAP.add(tiles);
			
			is = getClass().getResourceAsStream("/map/house.txt");
			br = new BufferedReader(new InputStreamReader(is));
			
			String temp2[] = br.readLine().split(" ");
			regionHeight = Integer.parseInt(temp2[0]);
			regionWidth = Integer.parseInt(temp2[1]);
			
//			System.out.println(regionWidth + " " + regionHeight);
			
			tiles = new Tile[regionHeight][regionWidth];
			
			for(int row = 0; row < regionHeight; row++) {
				
				String numbers[] = br.readLine().split(" ");
//				System.out.println(numbers.length);
//				System.out.println(row);
				
				for(int col = 0; col < regionWidth; col++) {

					byte tileID = (byte) Integer.parseInt(numbers[col]);
					tiles[row][col] = new Tile(tileID, col*Tile.SIZE, row*Tile.SIZE, Res.TEXTURES.get(tileID).isSolid());
				}

			}
			br.close();
			Res.MAP.add(tiles);
			
			// MAP OBJECT
			is = getClass().getResourceAsStream("/map/farm_o.txt");
			br = new BufferedReader(new InputStreamReader(is));
			
			String temp3[] = br.readLine().split(" ");
			regionHeight = Integer.parseInt(temp3[0]);
			regionWidth = Integer.parseInt(temp3[1]);
			
//			System.out.println(regionWidth + " " + regionHeight);
			
			Object[][] map_object = new Object[regionHeight][regionWidth];
			
			for(int row = 0; row < regionHeight; row++) {
				
				String numbers[] = br.readLine().split(" ");
//				System.out.println(numbers.length);
//				System.out.println(row);
				
				for(int col = 0; col < regionWidth; col++) {

					byte objectID = (byte) Integer.parseInt(numbers[col]);
					ObjectData object = Res.OBJECT.get(objectID);
					map_object[row][col] = new Object(objectID, col*Tile.SIZE, row*Tile.SIZE, object.width, object.height, object.solidArea, object.interactArea,
							object.isSolid, object.isFragile, object.isInteractable);
				}

			}
			br.close();
			Res.MAP_OBJECT.add(map_object);
			
			is = getClass().getResourceAsStream("/map/house_o.txt");
			br = new BufferedReader(new InputStreamReader(is));
			
			String temp4[] = br.readLine().split(" ");
			regionHeight = Integer.parseInt(temp4[0]);
			regionWidth = Integer.parseInt(temp4[1]);
			
//			System.out.println(regionWidth + " " + regionHeight);
			
			map_object = new Object[regionHeight][regionWidth];
			
			for(int row = 0; row < regionHeight; row++) {
				
				String numbers[] = br.readLine().split(" ");
//				System.out.println(numbers.length);
//				System.out.println(row);
				
				for(int col = 0; col < regionWidth; col++) {

					byte objectID = (byte) Integer.parseInt(numbers[col]);
					ObjectData object = Res.OBJECT.get(objectID);
					map_object[row][col] = new Object(objectID, col*Tile.SIZE, row*Tile.SIZE, object.width, object.height, object.solidArea, object.interactArea,
							object.isSolid, object.isFragile, object.isInteractable);
				}

			}
			br.close();
			Res.MAP_OBJECT.add(map_object);
			
		} catch (IOException e) {
			System.out.println("[Loader] : Can't load maps / file not found");
			e.printStackTrace();
		}
		
		// SOUND
//		try {
			
			Res.SOUND.add(getClass().getResource("/sound/menu.aiff"));
			Res.SOUND.add(getClass().getResource("/sound/sunny.aiff"));
			Res.SOUND.add(getClass().getResource("/sound/rainy.aiff"));
			
//		} catch (IOException e) {
//			System.out.println("[Loader] : Can't load tiles / file not found");
//			e.printStackTrace();
//		}
			
		// FONT
//		try {
				
			Res.FONT.add(getClass().getResource("/font/PressStart2P-Regular.ttf"));
				
//		} catch (IOException e) {
//			System.out.println("[Loader] : Can't load tiles / file not found");
//			e.printStackTrace();
//		}
	}
	
	
}
