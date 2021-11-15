package heavenland.resource;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import heavenland.world.Region;
import heavenland.world.RegionData;
import heavenland.world.Tile;
import heavenland.world.TileData;

public class Res {
	
	public static final ArrayList<TileData> TEXTURES = new ArrayList<>();
	public static final ArrayList<BufferedImage> SPRITE = new ArrayList<>();
	public static final ArrayList<Tile[][]> MAP = new ArrayList<>();
	
	// TILES
	public static final byte GROUND = 0;
	public static final byte WALL = 1;
	
	
	// SPRITE
	public static final byte PLAYER_U = 0;
	public static final byte PLAYER_D = 1;
	public static final byte PLAYER_L = 2;
	public static final byte PLAYER_R = 3;
	
	// REGION
	public static final byte FARM = 0;
	
}
