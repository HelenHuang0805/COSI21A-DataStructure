/**
 * Trains
 * Known Bugs: None
 * 
 * @author Yichun Huang
 * yichunhuang@brandeis.edu
 * March 14th, 2023
 * COSI 21A PA1
 */
package main;

public class Train {

	public static final int TOTAL_PASSENGERS = 10;
	public Rider[] passengers;
	public int passengerIndex;//Number of Riders on Train
	private String currentStation;
	private boolean isNorth;//Train's direction
	private String[] removeList;//Disembark Passengers' List (Updated at different stations)
	private int numRemove;//Number  of Disembark Passengers (Updated at different stations)
	
	//Initialize an empty Train
	public Train(String currentStation, int direction) {
		this.passengers = new Rider[TOTAL_PASSENGERS];
		this.passengerIndex = 0;
		this.currentStation = currentStation;
		this.isNorth = (direction == 0 ? true : false);
	}
	
	//Get Train's direction
	public boolean goingNorth() {
		return this.isNorth;
	}
	
	//Swap Train's direction
	public void swapDirection() {
		this.isNorth = !this.isNorth;
	}
	
	//Print Passengers' info, who are currently on Train
	public String currentPassengers() {
		String res = "";
		for(int i = 0; i < this.passengerIndex; i++) {
			Rider p = this.passengers[i];
			//Print Riders' ID and Destination Station
			res += p.toString() + ", " + p.getDestination() + "\n";
		}
		return res;
	}
	
	//Add Rider to Train
	public boolean addPassenger(Rider r) {
		/**
		 * Check:
		 * 1. Rider's Starting Station = this.currentStation
		 * 2. Same direction
		 * 3. Train has space for Passenger
		 */
		if(r.getStarting().equals(this.currentStation) && r.goingNorth() == this.goingNorth() && this.hasSpaceForPassengers()) {
			this.passengers[passengerIndex] = r;
			this.passengerIndex++;
			return true;
		} else {
			return false;
		}
	}
	
	//Check whether Train has space for passengers
	public boolean hasSpaceForPassengers() {
		if(this.passengerIndex < TOTAL_PASSENGERS) {
			return true;
		} else {
			return false;
		}
	}
	
	//Remove passengers who has get to their destination
	public String disembarkPassengers() {
		int tmpIndex =  this.passengerIndex;//Fix the original passengerIndex
		Rider[] tmpList = new Rider[TOTAL_PASSENGERS];//Temperate List for Passengers stays on Train
		this.removeList = new String[TOTAL_PASSENGERS];//List for Passengers disembarked
		this.numRemove = 0;//Number of Passengers disembarked
		String res = "";
		for(int i = 0; i < tmpIndex; i++) {
			Rider tmp = this.passengers[i];
			//Check whether this Rider's destinationStation matched with this.currentStation
			if(tmp.getDestination().equals(this.currentStation)) {
				//Put Rider's ID in the removeList
				this.removeList[numRemove] = tmp.getRiderID();
				//Print Rider's ID and currentStation
				res += tmp.getRiderID() + ", " + this.currentStation + "\n";
				//Decrement number of Passengers on Train
				this.passengerIndex--;
				//Increment number of Passengers disembarked
				numRemove++;
			} else {
				//Put Rider into tmpList
				tmpList[i - numRemove] = tmp;
			}
			//Remove original in this.passengers
			this.passengers[i] = null;
		}
		//Put Passengers who still stay on Train back to this.passengers
		for(int i = 0; i < this.passengerIndex; i++) {
			this.passengers[i] = tmpList[i];
		}
		return res;
	}
	
	//Update Train's currentStation
	public void updateStation(String s) {
		if(s == null || s.equals("")) {
			throw new IllegalArgumentException("Invalid Station Name");
		} else {
			this.currentStation = s;
		}
	}
	
	//Get Train's currentStation
	public String getStation() {
		return this.currentStation;
	}
	
	//Get Train's basic info
	@Override
	public String toString() {
		return "This is a Train with " + this.passengerIndex + " Riders, currently in Station: " + this.currentStation;
	}
	
	//Get list of Train's Disembarked Passengers' ID
	public String toString_RemoveList() {
		String res = "";
		for(int i = 0; i < this.numRemove; i++) {
			res += this.removeList[i] + "\n";
		}
		return res;
	}



}
