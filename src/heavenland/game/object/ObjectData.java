package heavenland.game.object;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class ObjectData {

	private BufferedImage image;
	public int width;
	public int height;
	public Rectangle solidArea;
	public boolean isSolid;
	public boolean isFragile;
	
	public ObjectData(BufferedImage image, int width, int height, boolean isSolid, boolean isFragile) {
		
		this.image = image;
		this.width = width;
		this.height = height;
		this.isSolid = isSolid;
		this.isFragile = isFragile;
		if(!isSolid)
			this.solidArea = new Rectangle(0,0,0,0);
	}
	
	public ObjectData(BufferedImage image, int width, int height, Rectangle solidArea, boolean isSolid, boolean isFragile) {
		
		this.image = image;
		this.width = width;
		this.height = height;
		this.solidArea = solidArea;
		this.isSolid = isSolid;
		this.isFragile = isFragile;
	}
	
	public BufferedImage getImage() {
		
		return image;
	}
}
