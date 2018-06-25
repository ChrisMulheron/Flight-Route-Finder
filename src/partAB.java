
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import org.jgrapht.Graph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class partAB extends DefaultWeightedEdge {

	public static void main(String[] args) {
		
		SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> airport = 
				new SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		String [] vertices = {"Edinburgh", "Heathrow", "Amsterdam", "Boston", "Chicago", "Montreal", "Toronto", "New Delhi", "Shanghai", "Hong Kong"};
		
		System.out.println(" ");
		System.out.println("The following destinations are used: " + "\n");

		
		 for (String s : vertices) {
		        airport.addVertex(s);
		        System.out.println("Added vertex:  " + s);
		    }
		   
		System.out.println("");
        
		/*
		Flight EH1 = new FLight("EH1", "1000", "1200", "100");
		airport.addEdge("source", "target", EH1);
		airport.setEdgeWeight(EH1, 110);
		*/
		
		System.out.print("");
		
		
		DefaultWeightedEdge EH1 = airport.addEdge("Edinburgh", "Heathrow");
		DefaultWeightedEdge HE1 = airport.addEdge("Heathrow", "Edinburgh");
		airport.setEdgeWeight(EH1, 110);
		airport.setEdgeWeight(HE1, 110);
		
		DefaultWeightedEdge HA2 = airport.addEdge("Heathrow", "Amsterdam");
		DefaultWeightedEdge AH2 = airport.addEdge("Amsterdam","Heathrow");
		airport.setEdgeWeight(HA2, 100);
		airport.setEdgeWeight(AH2, 100);
		
		DefaultWeightedEdge HB3 = airport.addEdge("Heathrow", "Boston");
		DefaultWeightedEdge BH3 = airport.addEdge("Boston","Heathrow");
		airport.setEdgeWeight(HB3, 230);
		airport.setEdgeWeight(BH3, 230);
		
		DefaultWeightedEdge BC4 = airport.addEdge("Boston", "Chicago");
		DefaultWeightedEdge CB4 = airport.addEdge("Chicago","Boston");
		airport.setEdgeWeight(BC4, 150);
		airport.setEdgeWeight(CB4, 150);
		
		DefaultWeightedEdge BM5 = airport.addEdge("Boston", "Montreal");
		DefaultWeightedEdge MB5 = airport.addEdge("Montreal","Boston");
		airport.setEdgeWeight(BM5, 100);
		airport.setEdgeWeight(MB5, 100);
		
		DefaultWeightedEdge MT6 = airport.addEdge("Montreal", "Toronto");
		DefaultWeightedEdge TM6 = airport.addEdge("Toronto","Montreal");
		airport.setEdgeWeight(MT6, 90);
		airport.setEdgeWeight(TM6, 90);
		
		DefaultWeightedEdge EC7 = airport.addEdge("Edinburgh", "Chicago");
		DefaultWeightedEdge CE7 = airport.addEdge("Chicago","Edinburgh");
		airport.setEdgeWeight(EC7, 560);
		airport.setEdgeWeight(CE7, 560);
		
		DefaultWeightedEdge N_DS8 = airport.addEdge("New Delhi", "Shanghai");
		DefaultWeightedEdge SN_D8 = airport.addEdge("Shanghai","New Delhi");
		airport.setEdgeWeight(N_DS8, 430);
		airport.setEdgeWeight(SN_D8, 430);
		
		DefaultWeightedEdge SH_K9 = airport.addEdge("Shanghai", "Hong Kong");
		DefaultWeightedEdge H_KS9 = airport.addEdge("Hong Kong", "Shanghai");
		airport.setEdgeWeight(SH_K9, 230);
		airport.setEdgeWeight(H_KS9, 230);
		
		
		System.out.println("");

		for(String v : vertices){
			for (DefaultWeightedEdge e : airport.outgoingEdgesOf(v)) {
		   			System.out.println(airport.getEdgeSource(e) + 
		   					" --> " + airport.getEdgeTarget(e) + " £" + airport.getEdgeWeight(e));
		   	}
		}
		
		System.out.println("------");
		System.out.println(airport.vertexSet());
		System.out.println(airport.edgeSet());
		System.out.println("------");
		
		System.out.println("");
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the location you're departing from: ");	
		String departure = scan.nextLine();
		System.out.println("Enter the location to travel to: ");
		String destination = scan.nextLine();
		
		shortestPath(departure, destination, airport);
		scan.close();
		
		System.out.println("");
		System.out.println(airport.vertexSet());
		System.out.println(airport.edgeSet());
		
		
	}
	
	public static void shortestPath(String departure, String destination, Graph<String, DefaultWeightedEdge> airport){	
		List<DefaultWeightedEdge> path = DijkstraShortestPath.findPathBetween(airport, departure, destination);

		int totalCost = 0;
		// iterate through the path and print flight data of current flight in
		// path. Calculate total cost and time.
		for (Iterator<DefaultWeightedEdge> iter = path.iterator(); iter.hasNext();) {
				DefaultWeightedEdge e = iter.next();
				System.out.println(airport.getEdgeSource(e) + " -> " + airport.getEdgeTarget(e) );
				totalCost = (int) (totalCost + airport.getEdgeWeight(e));
		}
		List<DefaultWeightedEdge> pathReturn = DijkstraShortestPath.findPathBetween(airport, destination, departure);
		
		for (Iterator<DefaultWeightedEdge> iter = pathReturn.iterator(); iter.hasNext();) {
			DefaultWeightedEdge e = iter.next();
			System.out.println(airport.getEdgeSource(e) + " -> " + airport.getEdgeTarget(e) );
			totalCost = (int) (totalCost + airport.getEdgeWeight(e));
	}
		System.out.println("total cost: £" + totalCost);
		
	}

}
