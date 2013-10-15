//package WoWPS;

public class vertex
{
	private String loc;
	public double xCoord;
	public double yCoord;
	private static enum locType {CITY, DUNGEON};
	
	public String loc()
	{
		return loc;
	}

	public int compareTo(vertex v)
	{
		return loc.compareTo(v.loc());
	}

	public double distTo(vertex v)
	{
		double xDist = v.xCoord-xCoord;
		double yDist = v.yCoord-yCoord;		
		return Math.sqrt(Math.pow(xDist,2)+Math.pow(yDist,2));
	}
}
