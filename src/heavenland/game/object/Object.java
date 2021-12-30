package heavenland.game.object;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import heavenland.framework.Window;

public class Object {

	byte objectID;
	public int x;
	public int y;
	public int width;
	public int height;
	public Rectangle solidArea;
	public Rectangle interactArea;
	public boolean isSolid;
	public boolean isFragile;
	public boolean isInteractable;
	public ObjectType objectType;
	
	public enum ObjectType {
		NORMAL, CROP, FURNITURE;
	}
	
	public Object(byte id, int x, int y, int width, int height, boolean isSolid, boolean isFragile, boolean isInteractable) {
		
		this.objectID = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.isSolid = isSolid;
		this.isFragile = isFragile;
		this.objectType = ObjectType.NORMAL;
	}
	
	public Object(byte id, int x, int y, int width, int height, Rectangle solidArea, Rectangle interactArea, boolean isSolid, boolean isFragile, boolean isInteractable) {
		
		this.objectID = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.solidArea = solidArea;
		this.interactArea = interactArea;
		this.isSolid = isSolid;
		this.isFragile = isFragile;
		this.isInteractable = isInteractable;
	}
	
	public boolean isInObject(int x, int y) {
		
		if(interactArea != null) {
			if(x >= this.x + interactArea.x*Window.SCALE && x <= this.x + interactArea.x*Window.SCALE + interactArea.width*Window.SCALE && 
					y >= this.y + interactArea.y*Window.SCALE && y <= this.y + interactArea.y*Window.SCALE + interactArea.height*Window.SCALE)
				return true;
		}
		else {
			if(x >= this.x && x <= this.x + width*Window.SCALE && y >= this.y && y <= this.y + height*Window.SCALE)
				return true;
		}
		return false;
	}
	
	public byte getID() {
		
		return objectID;
	}

}
