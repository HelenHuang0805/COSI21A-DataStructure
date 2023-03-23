/**
 * Station, which is contained in Railway, and contains Trains and Riders
 * Known Bugs: None
 * 
 * @author Yichun Huang
 * yichunhuang@brandeis.edu
 * March 14th, 2023
 * COSI 21A PA1
 */
package main;

import java.util.NoSuchElementException;

public class Station {

	//Waiting Line(Queue) for Trains / Riders
	public Queue<Rider> northBoundRiders;
	public Queue<Rider> southBoundRiders;
	public Queue<Train> northBoundTrains;
	public Queue<Train> southBoundTrains;
	private String name;//Station's name
	private static final int RIDER_QUEUE_CAPACITY = 20;
	private static final int TRAIN_QUEUE_CAPACITY = 20;
	
	//Initialize a Station with empty Queues
	public Station(String name) {
		this.name = name;
		this.northBoundRiders = new Queue<Rider>(RIDER_QUEUE_CAPACITY);
		this.southBoundRiders = new Queue<Rider>(RIDER_QUEUE_CAPACITY);
		this.northBoundTrains = new Queue<Train>(TRAIN_QUEUE_CAPACITY);
		this.southBoundTrains = new Queue<Train>(TRAIN_QUEUE_CAPACITY);
	}
	
	//Add Rider to corresponding Riders' Queue
	public boolean addRider(Rider r) { 
		if(r.getStarting().equals(this.name)) {
			if(r.goingNorth()) {
				this.northBoundRiders.enqueue(r);
			} else {
				this.southBoundRiders.enqueue(r);
			}
			return true;
		} else {
			return false;
		}
	}
	
	//Add Train to corresponding Trains' Queue
	public String addTrain(Train t) {
		t.updateStation(this.name);
		t.disembarkPassengers();
		if(t.goingNorth()) {
			this.northBoundTrains.enqueue(t);
		} else {
			this.southBoundTrains.enqueue(t);
		}
		String res = this.name + "Disembarking Passengers: " + "\n";
		res += t.toString_RemoveList();
		return res;
	}
	
	//Board as many Riders in southBoundRiders to the first train in southBoundRTrains as possible
	public Train southBoardTrain() {
		if(this.southBoundTrains.size() == 0) return null;
		Train tmpT = this.southBoundTrains.front();
		this.southBoundTrains.dequeue();
		while(tmpT.hasSpaceForPassengers() && this.southBoundRiders.size() > 0) {
			Rider tmpR = this.southBoundRiders.front();
			this.southBoundRiders.dequeue();
			tmpT.addPassenger(tmpR);
		}
		return tmpT;
	}
	
	//Board as many Riders in northBoundRiders to the first train in northBoundRTrains as possible
	public Train northBoardTrain() {
		if(this.northBoundTrains.size() == 0) return null;
		Train tmpT = this.northBoundTrains.front();
		this.northBoundTrains.dequeue();
		while(tmpT.hasSpaceForPassengers() && this.northBoundRiders.size() > 0) {
			Rider tmpR = this.northBoundRiders.front();
			this.northBoundRiders.dequeue();
			tmpT.addPassenger(tmpR);
		}
		return tmpT;
	}
	
	//Train turns around at the most north terminal
	public void moveTrainNorthToSouth() {
		if(this.northBoundTrains.size() == 0) {
			throw new NoSuchElementException("No Train Waiting to Go North");
		}
		Train tmp = this.northBoundTrains.front();
		this.northBoundTrains.dequeue();
		tmp.swapDirection();
		this.southBoundTrains.enqueue(tmp);
	}
	
	//Train turns around at the most south terminal
	public void moveTrainSouthToNorth() {
		if(this.southBoundTrains.size() == 0) {
			throw new NoSuchElementException("No Train Waiting to Go South");
		}
		Train tmp = this.southBoundTrains.front();
		this.southBoundTrains.dequeue();
		tmp.swapDirection();
		this.northBoundTrains.enqueue(tmp);
	}
	
	//Print info of Station's Queues
	@Override
	public String toString() {
		String res = "\nStation: " + this.name + "\n";
		res += this.northBoundTrains.size() + " north-bound trains waiting" + "\n";
		res += this.southBoundTrains.size() + " south-bound trains waiting" + "\n";
		res += this.northBoundRiders.size() + " north-bound passengers waiting" + "\n";
		res += this.southBoundRiders.size() + " south-bound passengers waiting" + "\n";
		return res;
	}
	
	//Get Station's name
	public String stationName() {
		return this.name;
	}
	
	//Compare 2 Stations
	@Override
	public boolean equals(Object o) {
		if(o instanceof Station) {
			Station tmp = (Station) o;
			return this.name.equals(tmp.name);
		}
		return false;
	}
}
