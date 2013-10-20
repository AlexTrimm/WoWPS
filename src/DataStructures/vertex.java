public class vertex
{
	private String loc;
	private String type;
	private double xCoord;
	private double yCoord;
	private int ID;
	//private static enum locType {CITY, DUNGEON};
	
	public vertex(String loc, String type, double xCoord, double yCoord, int ID)
	{
		this.loc = loc;
		this.type = type;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.ID = ID;
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

	public String loc()
	{
		return loc;
	}

	public String type()
	{
		return type;
	}

	public double xCoord()
	{
		return xCoord;
	}

	public double yCoord()
	{
		return yCoord;
	}

	public int ID()
	{
		return ID;
	}
}

