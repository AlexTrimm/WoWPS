package com.world.domination.beans;

public class VertexBean
{
	private String locationName;
	private String type;
	private double xCoord;
	private double yCoord;
	private int ID;
	//private static enum locType {CITY, DUNGEON};
	
	public VertexBean(String locationName, String type, double xCoord, double yCoord, int ID)
	{
		this.locationName = locationName;
		this.type = type;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.ID = ID;
	}

	public int compareTo(VertexBean v)
	{
		return locationName.compareTo(v.getLocationName());
	}

	public double getDistanceTo(VertexBean v)
	{
		double xDist = v.xCoord-xCoord;
		double yDist = v.yCoord-yCoord;		
		return Math.sqrt(Math.pow(xDist,2)+Math.pow(yDist,2));
	}
	
	public String getDirectionTo(VertexBean v){
		double xDist = v.xCoord-xCoord;
		double yDist = v.yCoord-yCoord;	
		double degrees = Math.atan2(yDist, xDist) + Math.PI;//.atan(yDist/xDist);
		degrees = degrees*180/Math.PI;
		
		int direction;
		if((degrees-22.5)<0){
			direction = 7;
		}
		else{
			direction = (int)((degrees-22.5)/45);
		}
		
		String directionString = "";
		
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

	public String getLocationName()
	{
		return locationName;
	}

	public String getType()
	{
		return type;
	}

	public double getXCoord()
	{
		return xCoord;
	}

	public double getYCoord()
	{
		return yCoord;
	}

	public int getID()
	{
		return ID;
	}
}