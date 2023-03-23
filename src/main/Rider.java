/**
 * Rider, Passengers of Trains
 * Known Bugs: None
 * 
 * @author Yichun Huang
 * yichunhuang@brandeis.edu
 * March 14th, 2023
 * COSI 21A PA1
 */
package main;

public class Rider {
	private String riderID;
	private String startingStation;
	private String destinationStation;
	private boolean isNorth;//Rider's direction
	
	//Initialize a Rider
	public Rider(String riderID, String startingStation, String destinationStation) {
		if(riderID != null && !riderID.equals("")) {
			this.riderID = riderID;
		} else {
			throw new IllegalArgumentException("Invalid ID");
		}
		if(startingStation != null && !startingStation.equals("")) {
			this.startingStation = startingStation;
		} else {
			throw new IllegalArgumentException("Invalid Starting Station");
		}
		if(destinationStation != null && !destinationStation.equals("")) {
			this.destinationStation = destinationStation;
		} else {
			throw new IllegalArgumentException("Invalid Destination Station");
		}
		this.isNorth = false;//A Rider must start by traveling south
	}
	
	//Get Rider's startingStation
	public String getStarting() {
		return this.startingStation;
	}
	
	//Get Rider's destinationStation
	public String getDestination() {
		return this.destinationStation;
	}
	
	//Get Rider's riderID
	public String getRiderID() {
		return this.riderID;
	}
	
	//Check whether Rider is heading North
	public boolean goingNorth() {	
		return this.isNorth;
	}
	
	//Swap Rider's direction
	public void swapDirection() {
		this.isNorth = !this.isNorth;
	}
	
	//Return riderId
	@Override
	public String toString() {
		return this.riderID;
	}
	
	//Compare 2 Rider
	@Override
	public boolean equals(Object s) {
		if(s instanceof Rider) {
			Rider tmp = (Rider) s;
			return this.riderID.equals(tmp.riderID);
		}
		return false;
	}
	
}
