package heavenland.gui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import heavenland.framework.Window;

public class GComponent {

	BufferedImage image;
	public int x;
	public int y;
	public int width;
	public int height;
	
	public GComponent(BufferedImage image, int x, int y, int width, int height) {
		
		this.image = image;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public GComponent(BufferedImage image, int x, int y) {
		
		this.image = image;
		this.x = x;
		this.y = y;
		this.width = image.getWidth();
		this.height = image.getHeight();
	}
	
	public void render(Graphics2D g2d) {
		
		g2d.drawImage(image, x, y, width*Window.SCALE, height*Window.SCALE, null);
	}
}
