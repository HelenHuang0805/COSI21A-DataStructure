/**
 * Main Method to Run the Railway
 * Known Bugs: None
 * 
 * @author Yichun Huang
 * yichunhuang@brandeis.edu
 * March 14th, 2023
 * COSI 21A PA1
 */
package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MBTA {

	public static final int SOUTHBOUND = 1;
	public static final int NORTHBOUND = 0;
	
	static final int TIMES = 6;//Run the Simulation x Times
	static Railway r;
	
	//Main
	public static void main(String[] args) {
		r = new Railway();//Initialize Railway;
		initStations("redLine.txt");//Initialize Stations;
        initRiders("riders.txt");//Initialize Riders;
        initTrains("trains.txt");//Initialize Trains;
        //Print the Initiated Railway
        System.out.println("INITIATED RED LINE");
        System.out.println(r.railway.toString());
        //Print the Simulation Header
        System.out.println("BEGINNING RED LINE SIMULATION \n");
        //Run the Simulation;
        runSimulation();
	}
	
	//Run the simulation for TIMES times;
	public static void runSimulation() {
		for(int i = 0; i < TIMES; i++) {
			//Print ------i------ 
			System.out.println(" ------ " + (i + 1) + " ------ ");
			System.out.println(r.simulate());
		}
	}
	
	//Initiate Trains from file
	public static void initTrains(String trainsFile) {
	    File file = new File(trainsFile);
	    try (Scanner scanner = new Scanner(file)) {
	        String currentStation;
	        int direction;
	        while (scanner.hasNextLine()) {
	            currentStation = scanner.nextLine();
	            direction = Integer.parseInt(scanner.nextLine());
	            Train train = new Train(currentStation, direction);
	            r.addTrain(train);
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	
	//Initialize Riders from file
	public static void initRiders(String ridersFile) {
	    File file = new File(ridersFile);
	    try (Scanner scanner = new Scanner(file)) {
	        String riderID, startingStation, destinationStation;
	        while (scanner.hasNextLine()) {
	            riderID = scanner.nextLine();
	            startingStation = scanner.nextLine();
	            destinationStation = scanner.nextLine();
	            Rider rider = new Rider(riderID, startingStation, destinationStation);
	            r.addRider(rider);
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	}

	
	//Initialize Stations from file
	public static void initStations(String stationsFile) {
	    File file = new File(stationsFile);
	    try (Scanner scanner = new Scanner(file)) {
	        String line;
	        while (scanner.hasNextLine()) {
	            line = scanner.nextLine();
	            Station station = new Station(line);
	            r.addStation(station);
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	}

}
