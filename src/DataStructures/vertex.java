//package WoWPS;

public class vertex
{
	private String loc;
	private int xCoord;
	private int yCoord;
	private static enum locType {CITY, DUNGEON};
	
	public String loc()
	{
		return locationName;
	}

	public int compareTo(vertex)
	{
		locationName.compareTo(vertex.loc());
	}
}
