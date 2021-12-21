package heavenland.resource;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import heavenland.item.ItemData;
import heavenland.object.Object;
import heavenland.object.ObjectData;
import heavenland.world.Region;
import heavenland.world.RegionData;
import heavenland.world.Tile;
import heavenland.world.TileData;

public class Res {
	
	public static final ArrayList<BufferedImage> GUI = new ArrayList<>();
	public static final ArrayList<TileData> TEXTURES = new ArrayList<>();
	public static final ArrayList<ObjectData> OBJECT = new ArrayList<>();
	public static final ArrayList<ItemData> ITEM = new ArrayList<>();
	public static final ArrayList<BufferedImage> SPRITE = new ArrayList<>();
	public static final ArrayList<Tile[][]> MAP = new ArrayList<>();
	public static final ArrayList<Object[][]> MAP_OBJECT = new ArrayList<>();
	
	// GUI
	public static final byte MAINMENU_BG = 0;
	public static final byte MAINMENU_LOGO = 1;
	public static final byte NEW_GAME_BTN = 2;
	public static final byte NEW_GAME_BTN_P = 3;
	public static final byte LOAD_GAME_BTN = 4;
	public static final byte LOAD_GAME_BTN_P = 5;
	public static final byte EXIT_BTN = 6;
	public static final byte EXIT_BTN_P = 7;
	public static final byte INVENTORY_BAR = 8;
	public static final byte INVENTORY_BTN = 9;
	public static final byte SELECTED = 10;
	
	// TILES
	public static final byte LAND = 0;
	public static final byte GRASS = 1;
	public static final byte GRASS_TL = 2;
	public static final byte GRASS_TM = 3;
	public static final byte GRASS_TR = 4;
	public static final byte GRASS_ML = 5;
	public static final byte GRASS_MR = 6;
	public static final byte GRASS_DL = 7;
	public static final byte GRASS_DM = 8;
	public static final byte GRASS_DR = 9;
	public static final byte WATER = 10;
	
	// OBJECT
	public static final byte BLANK = 0;
	public static final byte FENCE = 1;
	public static final byte FENCE_R = 2;
	public static final byte FENCE_RL = 3;
	public static final byte FENCE_L = 4;
	public static final byte FENCE_U = 5;
	public static final byte FENCE_UR = 6;
	public static final byte FENCE_URL = 7;
	public static final byte FENCE_UL = 8;
	public static final byte TREE = 9;
	public static final byte HOUSE = 10;
	public static final byte MAILBOX = 11;
	
	// ITEM
	public static final byte WATERING_CAN = 0;
	public static final byte AXE = 1;
	public static final byte PICKAXE = 2;
	public static final byte SCYTHE = 3;
	
	// SPRITE
	public static final byte PLAYER_U = 0;
	public static final byte PLAYER_D = 1;
	public static final byte PLAYER_L = 2;
	public static final byte PLAYER_R = 3;
	
	// REGION
	public static final byte FARM = 0;
	
	public static BufferedImage getGUI(int i) {
		return GUI.get(i);
	}
	
	public static TileData getTextures(int i) {
		return TEXTURES.get(i);
	}
	
	public static ObjectData getObject(int i) {
		return OBJECT.get(i);
	}
	
	public static ItemData getItem(int i) {
		return ITEM.get(i);
	}
	
	public static BufferedImage getSprite(int i) {
		return SPRITE.get(i);
	}
	
	public static Tile[][] getMap(int i) {
		return MAP.get(i);
	}
	
	public static Object[][] getMapObject(int i) {
		return MAP_OBJECT.get(i);
	}
}
