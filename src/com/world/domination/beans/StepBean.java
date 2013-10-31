package com.world.domination.beans;

public class StepBean {
	String startLoc;
	String endLoc;
	String direction;
	
	public StepBean(String startLoc, String endLoc, String direction){
		this.startLoc = startLoc;
		this.endLoc = endLoc;
		this.direction = direction;
	}
	
	public String getStartLoc() {
		return startLoc;
	}
	public void setStartLoc(String startLoc) {
		this.startLoc = startLoc;
	}
	
	public String getEndLoc() {
		return endLoc;
	}
	public void setEndLoc(String endLoc) {
		this.endLoc = endLoc;
	}
	
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
}