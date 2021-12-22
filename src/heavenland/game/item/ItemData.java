package heavenland.game.item;

import java.awt.image.BufferedImage;

public class ItemData {

	private BufferedImage image;
	public ItemType type;
	public boolean isStackable;
	
	public enum ItemType {
		TOOL, SEED, CROP, FURNITURE;
	}
	
	public ItemData(BufferedImage image, ItemType type, boolean isStackable) {
		
		this.image = image;
		this.type = type;
		this.isStackable = isStackable;
	}
	
	public BufferedImage getImage() {
		
		return image;
	}
}
