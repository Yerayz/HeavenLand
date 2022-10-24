package heavenland.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import heavenland.framework.Window;
import heavenland.game.item.Item;
import heavenland.resource.Res;

public class GInventButton extends GButton {

	BufferedImage selectedBox = Res.GUI.get(Res.SELECTED);
	Item item;
	public boolean isSelected = false;
	private double buttonScale;
	
	public GInventButton(BufferedImage normal, BufferedImage pressed, int x, int y, int buttonScale) {
		super(normal, pressed, x, y, buttonScale);
	}
	
	public GInventButton(BufferedImage normal, int x, int y) {
		super(normal, x, y);
		
		this.buttonScale = 1;
	}
	
	public GInventButton(BufferedImage normal, int x, int y, double buttonScale) {
		super(normal, x, y);
		
		this.buttonScale = buttonScale;
	}
	
	public void setItem(Item item) {
		
		this.item = item;
	}
	
	@Override
	public void render(Graphics2D g2d) {
		
		if(!isPressed || pressed == null)
			g2d.drawImage(normal, x, y, (int)(width*Window.SCALE*buttonScale), (int)(height*Window.SCALE*buttonScale), null);
		else
			g2d.drawImage(pressed, x, y, (int)(width*Window.SCALE*buttonScale), (int)(height*Window.SCALE*buttonScale), null);
		
		if(item != null) {
			g2d.drawImage(item.getImage(), x+Window.SCALE, y+Window.SCALE, (int)(16*Window.SCALE*buttonScale), (int)(16*Window.SCALE*buttonScale), null);
			if(item.isStackable && item.quantity > 1) {

				g2d.setColor(Color.white);
				g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, (int)(8*buttonScale)));
				g2d.drawString(String.valueOf(item.quantity), x+(int)(Window.SCALE*buttonScale), y+(int)(16*Window.SCALE*buttonScale));
			}
		}
		
		if(isSelected)
			g2d.drawImage(selectedBox, x, y, width*Window.SCALE, height*Window.SCALE, null);
	}

}
