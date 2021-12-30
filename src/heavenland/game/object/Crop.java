package heavenland.game.object;

import java.awt.Graphics2D;

import heavenland.game.world.Tile;
import heavenland.resource.Res;

public class Crop extends Object {
	
	private int maxAge;
	private int age;
	private int state;

	public Crop(byte id, int x, int y) {
		super(id, x, y, 16, 32, false, true, false);
		
		this.age = 0;
		this.state = 0;
		this.objectType = ObjectType.CROP;
		
		switch(id) {
		case Res.CORN_0:
		case Res.EGGPLANT_0: maxAge = 13; break;
		case Res.CARROT_0:
		case Res.RADISH_0: maxAge = 6; break;
		case Res.CABBAGE_0:
		case Res.CAULIFLOWER_0:maxAge = 9; break;
		}
	}
	
	public void incrementAge() {
		
		if(age < maxAge) {
			age++;
			updateState();
		}
	}
	
	public void updateState() {
		
		if(age == 1) {
			state = 1;
			objectID++;
		}
		else if(age == maxAge/3) {
			state = 2;
			objectID++;
		}
		else if(age == 2*maxAge/3) {
			state = 3;
			objectID++;
		}
		else if(age == maxAge) {
			state = 4;
			objectID++;
		}
	}

}
