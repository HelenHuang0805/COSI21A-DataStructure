/**
 * Railway, containing all the Trains, Riders, Stations and its own mechanism
 * Known Bugs: None
 * 
 * @author Yichun Huang
 * yichunhuang@brandeis.edu
 * March 14th, 2023
 * COSI 21A PA1
 */
package main;

public class Railway {

	public DoubleLinkedList<Station> railway;//DLL Containing Node<Station>
	public String[] stationNames;//Array Containing All the Stations' Name
	private static final int MAXIMUM_STATIONS = 18;
	
	//Initialize a Railway, create a null DLL and a null Array
	public Railway() {
		this.railway = new DoubleLinkedList<Station>();
		this.stationNames = new String[MAXIMUM_STATIONS];
	}
	
	//Add a Station to the Railway
	public void addStation(Station s) {
		if(s == null) {
			//Null Station
			throw new IllegalArgumentException("Invalid Station");
	    } else {
	    	//Add Station's name into stationNames([])
	    	this.stationNames[this.railway.size()] = s.stationName();
	    	//Add Station into railway(DLL)
	    	this.railway.insert(s);
	    }
	}
	
	//Add a Rider to the Railway
	public void addRider(Rider r) {
		//Set Rider's direction according to its starting & destination Station
		this.setRiderDirection(r);
		
		this.railway.get(new Station(r.getStarting())).addRider(r);
	}
	
	public void addTrain(Train t) {
		this.railway.get(new Station(t.getStation())).addTrain(t);
	}
	
	//Set Rider's direction according to its starting & destination Station
	public void setRiderDirection(Rider r) {
		int start = -1;//Rider's Starting Station
		int end = -1;//Rider's Destination Station
		//Traverse all the stationNames([]) to get the index of matched starting / destination Station (North: 0)
		for(int i = 0; i < this.railway.size; i++) {
			//Starting
			if(this.stationNames[i].equals(r.getStarting())) {
				start = i;
			}
			//Destination
			if(this.stationNames[i].equals(r.getDestination())) {
				end = i;
			}
		}
		//Didn't find matched starting / destination Station
		if(start == -1 || end == -1) {
			throw new IllegalArgumentException("Rider's Start/Destination Station Invalid");
		}
		//Same starting / destination Station
		if(start == end) {
			throw new IllegalArgumentException("Same Start and Destination Station");
		}
		if(end - start > 0) {
			//From North to South
			if(r.goingNorth()) r.swapDirection();
		} else {
			//From South to North
			if(!r.goingNorth()) r.swapDirection();
		}
	}
	
	//Traverse all the stations (North - South), board trains(a south, a north, if possible), move, disembark, change direction if end.
	public String simulate() {
		String res = "";
		Node<Station> tmp = this.railway.head;//For traverse purpose
		Station Alewife = new Station("Alewife");//For matching purpose
		Station Braintree = new Station("Braintree");//For matching purpose
		for(int i = 0; i < this.railway.size; i++) {
			//Get current Station
			Station s = tmp.getData();
			//Print Station's status
			res += s.toString();
			//Board, Move and Disembark a NorthBound Train if not Alewife
			if(!s.equals(Alewife)) {
				res += simulateNotEnd(tmp, true);
			}
			//Board, Move and Disembark a SouthBound Train if not Braintree
			if(!s.equals(Braintree)) {
				res += simulateNotEnd(tmp, false);
			}
			//If it's Alewife / Braintree
			if(s.equals(new Station("Alewife")) && s.northBoundTrains.size() > 0) {
				//Alewife: move Train North to go South
				s.moveTrainNorthToSouth();
			} else if(s.equals(new Station("Braintree")) && s.southBoundTrains.size() > 0) {
				//Braintree: move Train South to go North
				s.moveTrainSouthToNorth();
			}
			tmp = tmp.getNext();
		}
		res += "\n";
		return res;
	}
	
	//Proceeds a train for Simulation, depend on current station and train's direction
	private String simulateNotEnd(Node<Station> tmp, boolean isNorth) {
		String res = "";
		Station s = tmp.getData();//Current Station
		Train t;//The train going to be dealt with
		Node<Station> d;//The Station that'll get to
		String direction;//Direction (Northbound / Southbound): For Printing Use
		if(isNorth) {
			//Take Northbound Train
			t = s.northBoardTrain();
			d = tmp.getPrev();//Go to North(prev) Station
			direction = "Northbound";
		} else {
			//Take Southbound Train
			t = s.southBoardTrain();
			d = tmp.getNext();//Go to South(next) Station
			direction = "Southbound";
		}
		//Check if there's Trains should be dealt with
		if(t != null) {
			String Name = d.getData().stationName();//Next Station's name
			//Update Train's currentStation
			t.updateStation(d.getData().stationName());
			//Disembark Train's Passengers (Who gets to their destination Station)
			t.disembarkPassengers();
			//Print the IDs of Disembarking Passengers
			res += "\n" + Name + " Disembarking Passengers: \n" + t.toString_RemoveList();
			//Print the IDs and destinationStation of Passengers still on Train
			res += "Direction: " + direction + "\nPassenger: \n" + t.currentPassengers();
			//Add this Train to next Station's Queue
			d.getData().addTrain(t);
			res += "Current station: " + Name + "\n";
		}
		return res;
	}
	
	@Override
	public String toString() {
		return this.railway.toString();
	}
}
