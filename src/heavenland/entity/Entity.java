package heavenland.entity;

import java.awt.Rectangle;

public class Entity {

	public int mapX, mapY;
	protected int speed;
	protected Direction direction;
	protected Rectangle solidArea;
	
	public enum Direction {
		UP, DOWN, LEFT, RIGHT;
	}
	
	
}
