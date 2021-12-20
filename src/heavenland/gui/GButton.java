package heavenland.gui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GButton {

	BufferedImage normal;
	BufferedImage pressed;
	int x;
	int y;
	int width;
	int height;
	public boolean isPressed;
	
	public GButton(BufferedImage normal, BufferedImage pressed, int x, int y, int width, int height) {
		
		this.normal = normal;
		this.pressed = pressed;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.isPressed = false;
	}
	
	public GButton(BufferedImage normal, int x, int y, int width, int height) {
		
		this.normal = normal;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.isPressed = false;
	}
	
	public GButton(BufferedImage normal, BufferedImage pressed, int x, int y, int buttonScale) {
		
		this.normal = normal;
		this.pressed = pressed;
		this.x = x;
		this.y = y;
		this.width = normal.getWidth() * buttonScale;
		this.height = normal.getHeight() * buttonScale;
		this.isPressed = false;
	}
	
	public GButton(BufferedImage normal, int x, int y, int buttonScale) {
		
		this.normal = normal;
		this.x = x;
		this.y = y;
		this.width = normal.getWidth() * buttonScale;
		this.height = normal.getHeight() * buttonScale;
		this.isPressed = false;
	}
	
	public void render(Graphics2D g2d) {
		
		if(!isPressed)
			g2d.drawImage(normal, x, y, width, height, null);
		else
			g2d.drawImage(pressed, x, y, width, height, null);
	}
	
	public boolean isOnButton(int x, int y) {
		
		if(x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height)
			return true;
		return false;
	}
}
