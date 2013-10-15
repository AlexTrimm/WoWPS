//package WoWPS;

public class vertex
{
	private String loc;
	public double xCoord;
	public double yCoord;
	public int ID;
	private static enum locType {CITY, DUNGEON};
	
	public vertex(String loc, double xCoord, double yCoord, int ID)
	{
		this.loc = loc;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.ID = ID;
	}

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
		return Math.	sqrt(Math.pow(xDist,2)+Math.pow(yDist,2));
	}
}
