import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class partCDEF extends FlightData {

	//create HashMap to store vouchers
	private static HashMap<String, Integer> vouchers = new HashMap<String, Integer>();

	public partCDEF(String flightCode, String departureTime, String arrivalTime, int duration, int cost) {
		super(flightCode, departureTime, arrivalTime, duration, cost);
	}

	public static void main(String[] args) {

		// create graph with parameters string and the flight data class
		SimpleDirectedWeightedGraph<String, FlightData> airport = 
				new SimpleDirectedWeightedGraph<String, FlightData>(FlightData.class);

		vouchers.put("ED30", 30);
		vouchers.put("HR50", 50);

		// create a string array of locations
		String[] vertices = { "Edinburgh", "Heathrow", "Amsterdam",
				"Boston", "Chicago", "Montreal", "Toronto",
				"New Delhi", "Shanghai", "Hong Kong" };

		// add each element of the array as a vertex in the airport graph
		for (String s : vertices) {
			airport.addVertex(s);
		}

		// create new flight data object and add edges and weights to the
		// appropriate flights
		FlightData EH1 = new FlightData("EH1", "10:00", "11:20", 120, 110);
		airport.addEdge("Edinburgh", "Heathrow", EH1);
		airport.setEdgeWeight(EH1, 110);

		FlightData HE1 = new FlightData("HE1", "12:00", "13:20", 120, 110);
		airport.addEdge("Heathrow", "Edinburgh", HE1);
		airport.setEdgeWeight(HE1, 110);

		FlightData HA1 = new FlightData("HA1", "12:00", "14:20", 120, 100);
		airport.addEdge("Heathrow", "Amsterdam", HA1);
		airport.setEdgeWeight(HA1, 100);

		FlightData AH1 = new FlightData("AH1", "12:30", "13:50", 120, 100);
		airport.addEdge("Amsterdam", "Heathrow", AH1);
		airport.setEdgeWeight(AH1, 100);

		FlightData HB1 = new FlightData("HB1", "11:00", "17:20", 620, 230);
		airport.addEdge("Heathrow", "Boston", HB1);
		airport.setEdgeWeight(HB1, 230);

		FlightData BH1 = new FlightData("BH1", "10:00", "16:20", 620, 230);
		airport.addEdge("Boston", "Heathrow", BH1);
		airport.setEdgeWeight(BH1, 230);

		FlightData BC1 = new FlightData("BC1", "13:00", "16:00", 300, 150);
		airport.addEdge("Boston", "Chicago", BC1);
		airport.setEdgeWeight(BC1, 150);

		FlightData CB1 = new FlightData("CB1", "14:00", "17:00", 300, 150);
		airport.addEdge("Chicago", "Boston", CB1);
		airport.setEdgeWeight(CB1, 150);

		FlightData BM1 = new FlightData("BM1", "10:00", "11:15", 115, 100);
		airport.addEdge("Boston", "Montreal", BM1);
		airport.setEdgeWeight(BM1, 100);

		FlightData MB1 = new FlightData("MB1", "11:00", "12:15", 115, 100);
		airport.addEdge("Montreal", "Boston", MB1);
		airport.setEdgeWeight(MB1, 100);

		FlightData MT1 = new FlightData("MT1", "10:00", "11:20", 200, 90);
		airport.addEdge("Montreal", "Toronto", MT1);
		airport.setEdgeWeight(MT1, 90);

		FlightData TM1 = new FlightData("TM1", "10:00", "11:20", 200, 90);
		airport.addEdge("Toronto", "Montreal", TM1);
		airport.setEdgeWeight(TM1, 90);

		FlightData EC1 = new FlightData("EC1", "12:00", "24:00", 500, 560);
		airport.addEdge("Edinburgh", "Chicago", EC1);
		airport.setEdgeWeight(EC1, 560);

		FlightData CE1 = new FlightData("CE1", "10:00", "22:00", 500, 560);
		airport.addEdge("Chicago", "Edinburgh", CE1);
		airport.setEdgeWeight(CE1, 560);

		FlightData ND_S1 = new FlightData("ND_S1", "10:00", "15:00", 500, 430);
		airport.addEdge("New Delhi", "Shanghai", ND_S1);
		airport.setEdgeWeight(ND_S1, 430);

		FlightData S_ND1 = new FlightData("S_ND1", "11:00", "16:00", 500, 430);
		airport.addEdge("Shanghai", "New Delhi", S_ND1);
		airport.setEdgeWeight(S_ND1, 430);

		FlightData S_HK1 = new FlightData("S_HK1", "18:00", "21:00", 300, 230);
		airport.addEdge("Shanghai", "Hong Kong", S_HK1);
		airport.setEdgeWeight(S_HK1, 230);

		FlightData HK_S1 = new FlightData("HK_S1", "15:00", "18:00", 300, 230);
		airport.addEdge("Hong Kong", "Shanghai", HK_S1);
		airport.setEdgeWeight(HK_S1, 230);

		System.out.println("");

		// call flightProgram function where the user can interact with the program
		flightProgram(airport);

	}

	public static void flightProgram(Graph<String, FlightData> airport) {

		System.out.println("*************   PART A   *************");
		System.out.println("");
		System.out.format("%-30s%-20s", "Flight", "Cost");
		System.out.println("");
		// create a set routes of the edges in airport
		Set<FlightData> routes = airport.edgeSet();
		/* iterate through routes and add the source, target and edge weight of
		 	each flight n*/
		for (Iterator<FlightData> i = routes.iterator(); i.hasNext();) {
			while (i.hasNext()) {
				FlightData flight = i.next();
				System.out.format("%10s%15s", airport.getEdgeSource(flight) + "->" + airport.getEdgeTarget(flight),
						"£" + airport.getEdgeWeight(flight) + "\n");
			}
		}

		System.out.println("");
		System.out.println("*************   PART B/C   *************");
		System.out.println("");

		// take input from the keyboard
		Scanner scan = new Scanner(System.in);
		// user enters location to depart from
		System.out.println("Enter the location you're departing from (or type 'exit' to terminate the program): ");
		String departure = scan.nextLine();

		/*
		 * while the graph does not contains the vertex entered, prompt the user
		 * to enter again, otherwise if the user wishes to exit then terminate
		 * program
		 */

		while (!airport.containsVertex(departure)) {
			if (departure.equalsIgnoreCase("exit")) {
				System.out.println("Terminating program");
				System.exit(1);
			}

			System.out.println("Invalid Departure");
			departure = scan.nextLine();
		}
		
		System.out.println("Departure: " + departure);

		System.out.println("Enter the location to travel to (or type 'exit' to terminate the program): ");
		String destination = scan.nextLine();
		while (!airport.containsVertex(destination) || destination.equals(departure)) {
			if (destination.equalsIgnoreCase("exit")) {
				System.out.println("Terminating program");
				System.exit(1);
			}

			System.out.println("Invalid Destination");
			destination = scan.nextLine();
		}
		System.out.println("Destination: " + destination);

		// create a list path to find the shortest path between two locations
		List<FlightData> path = DijkstraShortestPath.findPathBetween(airport, departure, destination);

		/* while path is null, prompt the user to try again, else let the user
		 	terminate program */
		
		while (path == null) {
			System.out.println("This flight is unavailable");
			System.out.println("Would you like to try again? (type 'yes' if you would)");
			String tryAgain = scan.nextLine();
			if (tryAgain.equalsIgnoreCase("yes")) {
				flightProgram(airport);
			}
			if (tryAgain.equalsIgnoreCase("no")) {
				System.out.println("Terminating program");
				System.exit(1);
			}

		}
		
		
		// call method to find shortest path between locations

		System.out.println("");
		System.out.println("*************   PART D  *************");
		System.out.println("");

		System.out.println("Printing itinerary for return flight");
		createItinerary(airport, departure, destination);

		/* ask the user if they would like a return, if so then call
		 	shortestPathReturn method */

		while(scan.hasNextLine()) {
			System.out.println("Would you like to check another journey? yes or no.");
			String checkAgain = scan.nextLine();
			if (checkAgain.equalsIgnoreCase("yes")) {
				System.out.println("Program will be run again");
				flightProgram(airport);
			} 
		} 
		
		System.out.println("Terminating program");
		System.exit(1);
		scan.close();

	}

	// create a method to add an hour after minutes is greater than or equal to
	// 60.

	public static void createItinerary(Graph<String, FlightData> airport, String departure, String destination) {

		List<FlightData> path = DijkstraShortestPath.findPathBetween(airport, departure, destination);
		System.out.format("%10s%15s%10s%15s%15s%15s%15s%15s", "Leg", "Departure", "At", "On", "Destination", "At",
				"Duration", "Cost");
		System.out.println("");

		/* create variables to keep track of the total cost, time, hours and
		 	minutes. */
		int leg = 1;
		int totalCost = 0;
		int totalTime = 0;
		/* iterate through the path and print flight data of current flight in
		 	path. Calculate total cost and time. */
		for (Iterator<FlightData> iter = path.iterator(); iter.hasNext();) {
			while (iter.hasNext()) {
				FlightData flight = iter.next();
				System.out.format("%10s%15s%10s%15s%15s%15s%15s%15s", leg++, airport.getEdgeSource(flight),
						flight.getDepartureTime(), flight.getFlightCode(), airport.getEdgeTarget(flight),
						flight.getArrivalTime(), flight.getDuration(), flight.getCost() + "\n");
				totalCost = totalCost + flight.getCost();
				totalTime = totalTime + flight.getDuration();
			}
		}

		int hours = Integer.parseInt(Integer.toString(totalTime).substring(0, 1));
		int minutes = Integer.parseInt(Integer.toString(totalTime).substring(1, 3));
		hours = hours + parseHours(minutes);		
		minutes = parseMinutes(minutes);
		
		System.out.println("");
		System.out.println("Total cost of shortest single flight: £" + totalCost);
		//System.out.println("Total time for shortest single journey: " + parseHours(totalTime)Edinn + " hrs");
		/* System.out.println("Total time for shortest single journey: " + hours + " hrs " + minutes + " minutes"); */

		System.out.println("");
		System.out.println("");
		System.out.println("*************   PART F  *************");
		System.out.println("");

		Scanner scan = new Scanner(System.in);
		System.out.println("If you have a voucher, please enter the code, otherwise enter no.");
		String voucher = scan.nextLine();
		if (vouchers != null && vouchers.containsKey(voucher.toUpperCase())) {
			System.out.println("Voucher with code " + voucher + " is valid");
			totalCost = totalCost - vouchers.get(voucher.toUpperCase());
			System.out.println("New total cost of journey is: £" + totalCost);
		}

		System.out.println("");
		System.out.println("Would you like a return flight? yes or no.");
		String returnJourney = scan.nextLine();

		if (returnJourney.equalsIgnoreCase("yes")) {
			//create new path for the return itinerary
			List<FlightData> returnPath = DijkstraShortestPath.findPathBetween(airport, destination, departure);
			System.out.println("Printing itinerary for return flight");
			System.out.println("");
			System.out.format("%10s%15s%10s%15s%15s%15s%15s%15s", "Leg", "Departure", "At", "On", "Destination", "At",
					"Duration", "Cost");
			System.out.println("");

			// iterate through the path and print flight data of current flight in path. Calculate total cost and total time.
			for (Iterator<FlightData> iter = returnPath.iterator(); iter.hasNext();) {
				while (iter.hasNext()) {
					FlightData flight = iter.next();
					System.out.format("%10s%15s%10s%15s%15s%15s%15s%15s", leg++, airport.getEdgeSource(flight),
							flight.getDepartureTime(), flight.getFlightCode(), airport.getEdgeTarget(flight),
							flight.getArrivalTime(), flight.getDuration(), flight.getCost() + "\n");
					totalCost = totalCost + flight.getCost();
					totalTime = totalTime + flight.getDuration();
				}
			}

			System.out.println("");
			System.out.println("Cost of return journey: £" + totalCost);
			//System.out.println("Time for return journey: " + parseHours(totalTime) + " hrs");
			//System.out.println("Total time for shortest single journey: " + parseHours(hours) + " hrs " + parseMinutes(minutes) + " minutes");
			System.out.println("");

		}
		scan.close();
		System.out.println("");
	}

	//create method to add an hour once minutes is greater than or equal to 60
	public static int parseHours(int minutes) {
		int i = 0;
		while (minutes >= 60) {
			minutes = minutes - 60;
			i++;
		}
		return i;
	}

	// create a method to reset the minutes once it has reached an hour(60 minutes)
	public static int parseMinutes(int minutes) {
		while (minutes >= 60) {
			minutes = minutes - 60;
		}
		return minutes;
	}
}
