package heavenland.resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import heavenland.world.RegionData;
import heavenland.world.Tile;
import heavenland.world.TileData;

public class Loader {

	public void load() {
		
		// TILES
		try {
			
			Res.TEXTURES.add(Res.GROUND, new TileData(ImageIO.read(getClass().getResourceAsStream("/tile/ground.png")), false));
			Res.TEXTURES.add(Res.WALL, new TileData(ImageIO.read(getClass().getResourceAsStream("/tile/wall.png")), true));
		
		} catch (IOException e) {
			System.out.println("[Loader] : Can't load tiles / file not found");
			e.printStackTrace();
		}
		
		// SPRITE
		try {
			
			Res.SPRITE.add(Res.PLAYER_U, ImageIO.read(getClass().getResourceAsStream("/entity/player_up.png")));
			Res.SPRITE.add(Res.PLAYER_D, ImageIO.read(getClass().getResourceAsStream("/entity/player_down.png")));
			Res.SPRITE.add(Res.PLAYER_L, ImageIO.read(getClass().getResourceAsStream("/entity/player_left.png")));
			Res.SPRITE.add(Res.PLAYER_R, ImageIO.read(getClass().getResourceAsStream("/entity/player_right.png")));
			
		} catch (IOException e) {
			System.out.println("[Loader] : Can't load sprites / file not found");
			e.printStackTrace();
		}
		
		// REGION
		try {
			
			InputStream is = getClass().getResourceAsStream("/map/farm.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			String temp[] = br.readLine().split(" ");
			int regionWidth = Integer.parseInt(temp[0]);
			int regionHeight = Integer.parseInt(temp[1]);
			
			
//			System.out.println(regionWidth + " " + regionHeight);
			
			Tile[][] tiles = new Tile[regionWidth][regionHeight];
			
			for(int row = 0; row < regionWidth; row++) {
				
				
				String numbers[] = br.readLine().split(" ");
//				System.out.println(numbers.length);
//				System.out.println(row);
				
				for(int col = 0; col < regionHeight; col++) {

					byte tileID = (byte) Integer.parseInt(numbers[col]);
					tiles[row][col] = new Tile(tileID, col*Tile.SIZE, row*Tile.SIZE, Res.TEXTURES.get(tileID).getCollision());
				}

			}
			br.close();
			Res.MAP.add(tiles);
			
		} catch (IOException e) {
			System.out.println("[Loader] : Can't load maps / file not found");
			e.printStackTrace();
		}
	}
}
