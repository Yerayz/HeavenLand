package heavenland.world;

import java.awt.image.BufferedImage;

public class TileData {
	
	private BufferedImage image;
	private boolean collision;
	
	public TileData(BufferedImage image, boolean collision) {
		
		this.image = image;
		this.collision = collision;
	}
	
	public BufferedImage getImage() {
		
		return image;
	}
	
	public boolean getCollision() {
		
		return collision;
	}
}
