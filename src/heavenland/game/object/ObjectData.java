package heavenland.game.object;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class ObjectData {

	private BufferedImage image;
	public int width;
	public int height;
	public Rectangle solidArea;
	public Rectangle interactArea;
	public boolean isSolid;
	public boolean isFragile;
	public boolean isInteractable;
	
	public ObjectData(BufferedImage image, int width, int height, boolean isSolid, boolean isFragile, boolean isInteractable) {
		
		this.image = image;
		this.width = width;
		this.height = height;
		this.isSolid = isSolid;
		this.isFragile = isFragile;
		this.isInteractable = isInteractable;
		if(!isSolid)
			this.solidArea = new Rectangle(0,0,0,0);
		if(!isInteractable)
			this.interactArea = new Rectangle(0,0,0,0);
	}
	
	public ObjectData(BufferedImage image, int width, int height, Rectangle solidArea, Rectangle interactArea, boolean isSolid, boolean isFragile, boolean isInteractable) {
		
		this.image = image;
		this.width = width;
		this.height = height;
		this.solidArea = solidArea;
		this.interactArea = interactArea;
		this.isSolid = isSolid;
		this.isFragile = isFragile;
		this.isInteractable = isInteractable;
	}
	
	public ObjectData(BufferedImage image, int width, int height, Rectangle Area, boolean isSolid, boolean isFragile, boolean isInteractable) {
		
		this.image = image;
		this.width = width;
		this.height = height;
		this.isSolid = isSolid;
		this.isFragile = isFragile;
		this.isInteractable = isInteractable;
		
		if(isSolid)
			this.solidArea = Area;
		else
			this.solidArea = new Rectangle(0,0,0,0);
		
		if(isInteractable)
			this.interactArea = Area;
		else
			this.interactArea = new Rectangle(0,0,0,0);
	}
	
	public BufferedImage getImage() {
		
		return image;
	}
}
