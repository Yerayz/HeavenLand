package heavenland.game.item;

import java.awt.image.BufferedImage;

import heavenland.game.item.ItemData.ItemType;
import heavenland.resource.Res;

public class Item {

	private byte itemID;
	public ItemType type;
	public boolean isStackable;
	public int quantity;
	
	public Item(byte itemID, ItemType type, boolean isStackable) {
		
		this.itemID = itemID;
		this.type = type;
		this.isStackable = isStackable;
	}
	
	public Item(byte itemID) {
		
		ItemData data = Res.ITEM.get(itemID);
		this.itemID = itemID;
		this.type = data.type;
		this.isStackable = data.isStackable;
	}
	
	public byte getID() {
		
		return itemID;
	}
	
	public BufferedImage getImage() {
		
		return Res.ITEM.get(itemID).getImage();
	}
}
