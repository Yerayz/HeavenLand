package heavenland.world;

import java.awt.Rectangle;

public class Tile extends Rectangle {

	public static final int SIZE = 48;
	
	private byte tileID;
	private boolean collision;
	
	public Tile(byte id, int x, int y, boolean collision) {
		
		this.x = x;
		this.y = y;
		
		this.tileID = id;
		this.collision = collision;
	}
	
	public byte getID() {
		
		return tileID;
	}
	
	public boolean getCollision() {
		
		return collision;
	}
	
}

