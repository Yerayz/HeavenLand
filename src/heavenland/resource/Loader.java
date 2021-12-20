package heavenland.resource;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import heavenland.item.ItemData;
import heavenland.item.ItemData.ItemType;
import heavenland.object.Object;
import heavenland.object.ObjectData;
import heavenland.world.RegionData;
import heavenland.world.Tile;
import heavenland.world.TileData;

public class Loader {

	public void load() {
		
		// GUI
		try {
			
			Res.GUI.add(ImageIO.read(getClass().getResourceAsStream("/gui/mainmenu_bg.jpeg")));
			BufferedImage btnSheet = ImageIO.read(getClass().getResourceAsStream("/gui/button_tilesheet.png"));
			Res.GUI.add(btnSheet.getSubimage(0, 0, 64, 16));
			Res.GUI.add(btnSheet.getSubimage(64, 0, 64, 16));
			Res.GUI.add(btnSheet.getSubimage(0, 16, 64, 16));
			Res.GUI.add(btnSheet.getSubimage(64, 16, 64, 16));
			Res.GUI.add(btnSheet.getSubimage(0, 32, 64, 16));
			Res.GUI.add(btnSheet.getSubimage(64, 32, 64, 16));
			
			Res.GUI.add(ImageIO.read(getClass().getResourceAsStream("/gui/inventory.png")));
			Res.GUI.add(ImageIO.read(getClass().getResourceAsStream("/gui/selected.png")));
			
		} catch (IOException e) {
			System.out.println("[Loader] : Can't load gui / file not found");
			e.printStackTrace();
		}
		
		// TILES
		try {
			
			BufferedImage grassSheet = ImageIO.read(getClass().getResourceAsStream("/tile/grass_tilesheet.png"));
			
			Res.TEXTURES.add(new TileData(grassSheet.getSubimage(16, 16, 16, 16), false));
			Res.TEXTURES.add(Res.GRASS, new TileData(ImageIO.read(getClass().getResourceAsStream("/tile/grass.png")), true));
			Res.TEXTURES.add(new TileData(grassSheet.getSubimage(0, 0, 16, 16), false));
			Res.TEXTURES.add(new TileData(grassSheet.getSubimage(16, 0, 16, 16), false));
			Res.TEXTURES.add(new TileData(grassSheet.getSubimage(32, 0, 16, 16), false));
			Res.TEXTURES.add(new TileData(grassSheet.getSubimage(0, 16, 16, 16), false));
			Res.TEXTURES.add(new TileData(grassSheet.getSubimage(32, 16, 16, 16), false));
			Res.TEXTURES.add(new TileData(grassSheet.getSubimage(0, 32, 16, 16), false));
			Res.TEXTURES.add(new TileData(grassSheet.getSubimage(16, 32, 16, 16), false));
			Res.TEXTURES.add(new TileData(grassSheet.getSubimage(32, 32, 16, 16), false));
			
//			Res.TEXTURES.add(Res.LAND, new TileData(ImageIO.read(getClass().getResourceAsStream("/tile/land.png")), false));
//			Res.TEXTURES.add(Res.GRASS, new TileData(ImageIO.read(getClass().getResourceAsStream("/tile/grass.png")), true));
//			Res.TEXTURES.add(Res.WATER, new TileData(ImageIO.read(getClass().getResourceAsStream("/tile/water.png")), true));
		
		} catch (IOException e) {
			System.out.println("[Loader] : Can't load tiles / file not found");
			e.printStackTrace();
		}
		
		// OBJECT
		try {
			Res.OBJECT.add(new ObjectData(ImageIO.read(getClass().getResourceAsStream("/tile/blank.png")), 16, 16, false, false));
			
			BufferedImage fenceSheet = ImageIO.read(getClass().getResourceAsStream("/object/fence_tilesheet.png"));
			
			Rectangle fenceArea = new Rectangle(4, 5, 7, 7);
			Res.OBJECT.add(new ObjectData(fenceSheet.getSubimage(0, 0, 16, 32), 16, 32, new Rectangle(4, 5, 8, 7), true, false));
			Res.OBJECT.add(new ObjectData(fenceSheet.getSubimage(16, 0, 16, 32), 16, 32, new Rectangle(4, 5, 12, 7), true, false));
			Res.OBJECT.add(new ObjectData(fenceSheet.getSubimage(32, 0, 16, 32), 16, 32, new Rectangle(0, 5, 16, 7), true, false));
			Res.OBJECT.add(new ObjectData(fenceSheet.getSubimage(48, 0, 16, 32), 16, 32, new Rectangle(0, 5, 12, 7), true, false));
			Res.OBJECT.add(new ObjectData(fenceSheet.getSubimage(64, 0, 16, 32), 16, 32, new Rectangle(4, 5, 8, 7), true, false));
			Res.OBJECT.add(new ObjectData(fenceSheet.getSubimage(80, 0, 16, 32), 16, 32, new Rectangle(4, 5, 12, 7), true, false));
			Res.OBJECT.add(new ObjectData(fenceSheet.getSubimage(96, 0, 16, 32), 16, 32, new Rectangle(0, 5, 16, 7), true, false));
			Res.OBJECT.add(new ObjectData(fenceSheet.getSubimage(112, 0, 16, 32), 16, 32, new Rectangle(0, 5, 12, 7), true, false));
			
			Rectangle treeArea = new Rectangle(16, 0, 16, 16);
			Res.OBJECT.add(new ObjectData(ImageIO.read(getClass().getResourceAsStream("/object/tree.png")), 48, 80, treeArea, true, true));
			
			Res.OBJECT.add(new ObjectData(ImageIO.read(getClass().getResourceAsStream("/object/house.png")), 112, 128, new Rectangle(0, 0, 112, 32), true, false));
			Res.OBJECT.add(new ObjectData(ImageIO.read(getClass().getResourceAsStream("/object/mailbox.png")), 16, 32, new Rectangle(2, 8, 11, 7), true, false));
			
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
			
		} catch (IOException e) {
			System.out.println("[Loader] : Can't load items / file not found");
			e.printStackTrace();
		}
		
		// SPRITE
		try {
			
			Res.SPRITE.add(Res.PLAYER_U, ImageIO.read(getClass().getResourceAsStream("/entity/player_up.png")));
			Res.SPRITE.add(Res.PLAYER_D, ImageIO.read(getClass().getResourceAsStream("/entity/player_down.png")));
			Res.SPRITE.add(Res.PLAYER_L, ImageIO.read(getClass().getResourceAsStream("/entity/player_left.png")));
			Res.SPRITE.add(Res.PLAYER_R, ImageIO.read(getClass().getResourceAsStream("/entity/player_right.png")));
			
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
					tiles[row][col] = new Tile(tileID, col*Tile.SIZE, row*Tile.SIZE, Res.TEXTURES.get(tileID).getCollision());
				}

			}
			br.close();
			Res.MAP.add(tiles);
			
			// MAP OBJECT
			is = getClass().getResourceAsStream("/map/farm_o.txt");
			br = new BufferedReader(new InputStreamReader(is));
			
			String temp2[] = br.readLine().split(" ");
			regionHeight = Integer.parseInt(temp2[0]);
			regionWidth = Integer.parseInt(temp2[1]);
			
//			System.out.println(regionWidth + " " + regionHeight);
			
			Object[][] map_object = new Object[regionHeight][regionWidth];
			
			for(int row = 0; row < regionHeight; row++) {
				
				String numbers[] = br.readLine().split(" ");
//				System.out.println(numbers.length);
//				System.out.println(row);
				
				for(int col = 0; col < regionWidth; col++) {

					byte objectID = (byte) Integer.parseInt(numbers[col]);
					ObjectData object = Res.OBJECT.get(objectID);
					map_object[row][col] = new Object(objectID, col*Tile.SIZE, row*Tile.SIZE, object.width, object.height, object.solidArea,
							object.isSolid, object.isFragile);
				}

			}
			br.close();
			Res.MAP_OBJECT.add(map_object);
			
		} catch (IOException e) {
			System.out.println("[Loader] : Can't load maps / file not found");
			e.printStackTrace();
		}
	}
}
