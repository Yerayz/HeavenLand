package heavenland.object;

import java.awt.Rectangle;

public class Object {

	byte objectID;
	public int x;
	public int y;
	public int width;
	public int height;
	public Rectangle solidArea;
	public boolean isSolid;
	public boolean isFragile;
	
	public Object(byte id, int x, int y, int width, int height, boolean isSolid, boolean isFragile) {
		
		this.objectID = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.isSolid = isSolid;
		this.isFragile = isFragile;
	}
	
	public Object(byte id, int x, int y, int width, int height, Rectangle solidArea, boolean isSolid, boolean isFragile) {
		
		this.objectID = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.solidArea = solidArea;
		this.isSolid = isSolid;
		this.isFragile = isFragile;
	}
	
	public byte getID() {
		
		return objectID;
	}
}
