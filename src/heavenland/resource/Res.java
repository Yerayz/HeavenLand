package heavenland.resource;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;

import heavenland.game.item.ItemData;
import heavenland.game.object.Object;
import heavenland.game.object.ObjectData;
import heavenland.game.world.Region;
import heavenland.game.world.RegionData;
import heavenland.game.world.Tile;
import heavenland.game.world.TileData;

public class Res {
	
	public static final ArrayList<BufferedImage> ICON = new ArrayList<>();
	public static final ArrayList<BufferedImage> GUI = new ArrayList<>();
	public static final ArrayList<TileData> TEXTURES = new ArrayList<>();
	public static final ArrayList<TileData> TERRAIN = new ArrayList<>();
	public static final ArrayList<ObjectData> OBJECT = new ArrayList<>();
	public static final ArrayList<ItemData> ITEM = new ArrayList<>();
	public static final ArrayList<BufferedImage> SPRITE = new ArrayList<>();
	public static final ArrayList<Tile[][]> MAP = new ArrayList<>();
	public static final ArrayList<Object[][]> MAP_OBJECT = new ArrayList<>();
	public static final ArrayList<URL> SOUND = new ArrayList<>();
	public static final ArrayList<URL> FONT = new ArrayList<>();
	
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
	public static final byte ENERGY = 11;
	public static final byte ENERGY_BAR = 12;
	public static final byte INFORMATION_BAR = 13;
	public static final byte MONEY_BAR = 14;
	public static final byte PAUSE_BAR = 15;
	public static final byte PAUSE_INVENTORY_BTN = 16;
	public static final byte PAUSE_INVENTORY_BTN_P = 17;
	public static final byte PAUSE_BUY_BTN = 18;
	public static final byte PAUSE_BUY_BTN_P = 19;
	public static final byte PAUSE_OUT_BTN = 20;
	public static final byte PAUSE_OUT_BTN_P = 21;
	public static final byte PLUS_BTN = 22;
	public static final byte PLUS_BTN_P = 23;
	public static final byte MINUS_BTN = 24;
	public static final byte MINUS_BTN_P = 25;
	
	
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
	public static final byte GRASS_CTL = 10;
	public static final byte GRASS_CTR = 11;
	public static final byte GRASS_CDL = 12;
	public static final byte GRASS_CDR = 13;
	public static final byte FLOOR_TL = 14;
	public static final byte FLOOR_TM = 15;
	public static final byte FLOOR_TR = 16;
	public static final byte FLOOR_ML = 17;
	public static final byte FLOOR_MM = 18;
	public static final byte FLOOR_MR = 19;
	public static final byte FLOOR_DL = 20;
	public static final byte FLOOR_DM = 21;
	public static final byte FLOOR_DR = 22;
	public static final byte WALLPAPER_T = 23;
	public static final byte WALLPAPER_M = 24;
	public static final byte WALLPAPER_D = 25;
	public static final byte WALL_TL = 26;
	public static final byte WALL_TM = 27;
	public static final byte WALL_TR = 28;
	public static final byte WALL_ML = 29;
	public static final byte WALL_MR = 30;
	public static final byte WALL_DL = 31;
	public static final byte WALL_DM = 32;
	public static final byte WALL_DR = 33;
	public static final byte WALL_CL = 34;
	public static final byte WALL_CR = 35;
	public static final byte WALL_BLANK = 36;
	
	//TERRAIN
	public static final byte LAND_HOE = 1;
	public static final byte LAND_HOE_WATERED = 2;
	
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
	public static final byte CORN_0 = 12;
	public static final byte CORN_1 = 13;
	public static final byte CORN_2 = 14;
	public static final byte CORN_3 = 15;
	public static final byte CORN_4 = 16;
	public static final byte CARROT_0 = 17;
	public static final byte CARROT_1 = 18;
	public static final byte CARROT_2 = 19;
	public static final byte CARROT_3 = 20;
	public static final byte CARROT_4 = 21;
	public static final byte CABBAGE_0 = 22;
	public static final byte CABBAGE_1 = 23;
	public static final byte CABBAGE_2 = 24;
	public static final byte CABBAGE_3 = 25;
	public static final byte CABBAGE_4 = 26;
	public static final byte EGGPLANT_0 = 27;
	public static final byte EGGPLANT_1 = 28;
	public static final byte EGGPLANT_2 = 29;
	public static final byte EGGPLANT_3 = 30;
	public static final byte EGGPLANT_4 = 31;
	public static final byte RADISH_0 = 32;
	public static final byte RADISH_1 = 33;
	public static final byte RADISH_2 = 34;
	public static final byte RADISH_3 = 35;
	public static final byte RADISH_4 = 36;
	public static final byte CAULIFLOWER_0 = 37;
	public static final byte CAULIFLOWER_1 = 38;
	public static final byte CAULIFLOWER_2 = 39;
	public static final byte CAULIFLOWER_3 = 40;
	public static final byte CAULIFLOWER_4 = 41;
	public static final byte BED = 42;
	public static final byte SELL_BOX = 43;
	
	// ITEM
	public static final byte WATERING_CAN = 0;
	public static final byte HOE = 1;
	public static final byte AXE = 2;
	public static final byte PICKAXE = 3;
	public static final byte SCYTHE = 4;
	public static final byte CORN = 5;
	public static final byte CORN_SEED = 6;
	public static final byte CARROT = 7;
	public static final byte CARROT_SEED = 8;
	public static final byte CABBAGE = 9;
	public static final byte CABBAGE_SEED = 10;
	public static final byte EGGPLANT = 11;
	public static final byte EGGPLANT_SEED = 12;
	public static final byte RADISH = 13;
	public static final byte RADISH_SEED = 14;
	public static final byte CAULIFLOWER = 15;
	public static final byte CAULIFLOWER_SEED = 16;
	
	// SPRITE
	public static final byte PLAYER_U = 0;
	public static final byte PLAYER_U_1 = 1;
	public static final byte PLAYER_U_2 = 2;
	public static final byte PLAYER_U_3 = 3;
	public static final byte PLAYER_U_4 = 4;
	public static final byte PLAYER_D = 5;
	public static final byte PLAYER_D_1 = 6;
	public static final byte PLAYER_D_2 = 7;
	public static final byte PLAYER_D_3 = 8;
	public static final byte PLAYER_D_4 = 9;
	public static final byte PLAYER_L = 10;
	public static final byte PLAYER_L_1 = 11;
	public static final byte PLAYER_L_2 = 12;
	public static final byte PLAYER_L_3 = 13;
	public static final byte PLAYER_L_4 = 14;
	public static final byte PLAYER_R = 15;
	public static final byte PLAYER_R_1 = 16;
	public static final byte PLAYER_R_2 = 17;
	public static final byte PLAYER_R_3 = 18;
	public static final byte PLAYER_R_4 = 19;
	
	// REGION
	public static final byte FARM = 0;
	public static final byte FARMHOUSE = 1;
	
	// SOUND
	public static final byte MAINMENU = 0;
	public static final byte DRY = 1;
	public static final byte RAIN = 2;
	
	//FONT
	public static final byte PRESS_START_2P = 0;
	
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
