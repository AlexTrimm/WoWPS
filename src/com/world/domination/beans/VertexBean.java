/*
 * I changed a lot of the names to match proper java naming conventions cause it was bothering me. I also added the getDirectionTo method.
 */
public class VertexBean{
	private String locationName;
	private String type;
	private double xCoord;
	private double yCoord;
	private int ID;
	
	// Constructor for easy creation.
	public VertexBean(String locationName, String type, double xCoord, double yCoord, int ID)
	{
		this.locationName = locationName;
		this.type = type;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.ID = ID;
	}

	// Unchanged. Used to alphabetize our display lists on the submission page.
	public int compareTo(VertexBean v)
	{
		return locationName.compareTo(v.getLocationName());
	}

	// Unchanged.
	public double getDistanceTo(VertexBean v)
	{
		double xDist = v.xCoord-xCoord;
		double yDist = v.yCoord-yCoord;		
		return Math.sqrt(Math.pow(xDist,2)+Math.pow(yDist,2));
	}
	
	// Calculates the cardinal direction to another point.
	public String getDirectionTo(VertexBean v){
		double xDist = v.xCoord-xCoord;
		double yDist = v.yCoord-yCoord;	
		double degrees = Math.atan2(yDist, xDist) + Math.PI;
		degrees = degrees*180/Math.PI;
		//^^ Calculates the degrees out of 360
		
		int direction;
		if((degrees-22.5)<0){
			// Special case that defeats the formula below, so assigned manually.
			direction = 7;
		}
		else{
			// Reduces the degrees in any range to a whole number corresponding to that range's direction.
			direction = (int)((degrees-22.5)/45);
		}
		
		String directionString = "";
		
		// Simple switch converting the result from the formula above to text.
		switch(direction){
		case 0:  directionString = "Northwest";
		break;
		case 1:  directionString = "North";
		break;
		case 2:  directionString = "Northeast";
		break;
		case 3:  directionString = "East";
		break;
		case 4:  directionString = "Southeast";
		break;
		case 5:  directionString = "South";
		break;
		case 6:  directionString = "Southwest";
		break;
		case 7:  directionString = "West";
		break;
		}

		return directionString;
	}

	// Changed name
	public String getLocationName()
	{
		return locationName;
	}

	// Changed name
	public String getType()
	{
		return type;
	}

	// Changed name
	public double getXCoord()
	{
		return xCoord;
	}

	// Changed name
	public double getYCoord()
	{
		return yCoord;
	}

	// Changed name
	public int getID()
	{
		return ID;
	}
}