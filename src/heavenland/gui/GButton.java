package heavenland.gui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import heavenland.framework.Window;

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
	
	public GButton(BufferedImage normal, int x, int y) {
		
		this.normal = normal;
		this.x = x;
		this.y = y;
		this.width = normal.getWidth();
		this.height = normal.getHeight();
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
		
		if(!isPressed || pressed == null)
			g2d.drawImage(normal, x, y, width*Window.SCALE, height*Window.SCALE, null);
		else
			g2d.drawImage(pressed, x, y, width*Window.SCALE, height*Window.SCALE, null);
	}
	
	public boolean isOnButton(int x, int y) {
		
		if(x >= this.x && x <= this.x + width*Window.SCALE && y >= this.y && y <= this.y + height*Window.SCALE)
			return true;
		return false;
	}
}
