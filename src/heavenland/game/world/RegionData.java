package heavenland.game.world;

public class RegionData {

	private byte regionID;
	private Tile[][] tiles;
	
	public RegionData(byte ID, Tile[][] tiles) {
		
		this.regionID = ID;
		this.tiles = tiles;
	}
	
	public byte getID() {
		
		return regionID;
	}
}
