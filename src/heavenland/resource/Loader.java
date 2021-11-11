package heavenland.resource;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Loader {

	public static void load() {
		
		try {
			
			Res.TEXTURES.add(Res.GROUND, ImageIO.read(new File("res/tile/ground.png")));
			Res.TEXTURES.add(Res.WALL, ImageIO.read(new File("res/tile/wall.png")));
			Res.TEXTURES.add(Res.PLAYER_U, ImageIO.read(new File("res/entity/player_up.png")));
			Res.TEXTURES.add(Res.PLAYER_D, ImageIO.read(new File("res/entity/player_down.png")));
			Res.TEXTURES.add(Res.PLAYER_L, ImageIO.read(new File("res/entity/player_left.png")));
			Res.TEXTURES.add(Res.PLAYER_R, ImageIO.read(new File("res/entity/player_right.png")));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
