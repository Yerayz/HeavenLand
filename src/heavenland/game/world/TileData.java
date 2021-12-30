package heavenland.game.world;

import java.awt.image.BufferedImage;

public class TileData {
	
	private BufferedImage image;
	private boolean isSolid;
	
	public TileData(BufferedImage image, boolean isSolid) {
		
		this.image = image;
		this.isSolid = isSolid;
	}
	
	public BufferedImage getImage() {
		
		return image;
	}
	
	public boolean isSolid() {
		
		return isSolid;
	}
}
