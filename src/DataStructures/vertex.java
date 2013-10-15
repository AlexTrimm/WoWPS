//package WoWPS;

public class vertex
{
	private String loc;
	private int xCoord;
	private int yCoord;
	private static enum locType {CITY, DUNGEON};
	
	public String loc()
	{
		return loc;
	}

	public int compareTo(vertex v)
	{
		return loc.compareTo(v.loc());
	}
}
