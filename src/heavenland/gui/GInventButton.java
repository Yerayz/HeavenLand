package heavenland.gui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import heavenland.framework.Window;
import heavenland.item.Item;
import heavenland.resource.Res;

public class GInventButton extends GButton {

	BufferedImage selectedBox = Res.GUI.get(Res.SELECTED);
	Item item;
	public boolean isSelected = false;
	
	public GInventButton(BufferedImage normal, BufferedImage pressed, int x, int y, int buttonScale) {
		super(normal, pressed, x, y, buttonScale);
	}
	
	public GInventButton(BufferedImage normal, int x, int y) {
		super(normal, x, y);
	}
	
	public void setItem(Item item) {
		
		this.item = item;
	}
	
	@Override
	public void render(Graphics2D g2d) {
		
		if(!isPressed || pressed == null)
			g2d.drawImage(normal, x, y, width*Window.SCALE, height*Window.SCALE, null);
		else
			g2d.drawImage(pressed, x, y, width*Window.SCALE, height*Window.SCALE, null);
		
		if(item != null)
			g2d.drawImage(item.getImage(), x+Window.SCALE, y+Window.SCALE, 16*Window.SCALE, 16*Window.SCALE, null);
		
		if(isSelected)
			g2d.drawImage(selectedBox, x, y, width*Window.SCALE, height*Window.SCALE, null);
	}

}
