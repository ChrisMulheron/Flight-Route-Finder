import org.jgrapht.graph.DefaultWeightedEdge;

public class FlightData extends DefaultWeightedEdge {

	private String departure, destination, flightCode, departureTime, arrivalTime;
	private int cost, duration;
	

	public FlightData(String flightCode, String departureTime, String arrivalTime, int duration, int cost) {
		super();
		this.flightCode = flightCode;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.duration = duration;
		this.cost = cost;

	}
	
	public int getCost() {
		return cost;
	}

	public String getDestination() {
		return destination;
	}

	public String getDeparture() {
		return departure;
	}
	public String getFlightCode() {
		return flightCode;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public int getDuration() {
		return duration;
	}

}
