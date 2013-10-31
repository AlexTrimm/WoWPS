import java.util.*;
import org.jgrapht.*;
import org.jgrapht.alg.*;
import org.jgrapht.graph.*;

/*
 * Just a simple test class. Runs the graph engine, pulls the resulting graph and VertexList, prints some data, and conducts a directions search. 
 */
public class Test {
	public static void main(String[] args) {
		// Start a timer just for curiosity.
		long start = Calendar.getInstance().getTimeInMillis();
		
		// Processes the graph data from the given file name and creates a graph.
		GraphEngine engine = new GraphEngine("Eastern Kingdom Coordinates - Sheet1.csv");
		
		//Get the graph from the engine. This is just pulling it out of storage. The processing was done on the engine's instantiation. 
		SimpleWeightedGraph<VertexBean, DefaultWeightedEdge> graph = engine.getGraph();
		// Pull the VertexList out of storage. This is just for convenience so that we can reference vertex's by their ID, which happens to be the index they're located at in the list. 
		ArrayList<VertexBean> vertexList = engine.getVertexList();
		
		// Print info
		System.out.println("Graph Info: ");
		System.out.println("Number of vertices: " + graph.vertexSet().size());
		System.out.println("Number of edges: " + graph.edgeSet().size());
		System.out.println("Connected: " + new ConnectivityInspector<VertexBean, DefaultWeightedEdge>(graph).isGraphConnected()); 
		// !!!!^^^^ This one is extremely important! If the MAX_EDGE_WEIGHT is set lower than 105.64 in the GraphEngine, the graph will not be fully connected. 
		// If you mess with trying to make the jumps smaller, this is an easy indicator of your success. We may be able to do something in GraphEngine with 
		// pulling connected components and then connecting the subgraphs. 

		// This is tha actual search. Shortest path from one vertex to another. 
		DijkstraShortestPath<VertexBean, DefaultWeightedEdge> dsp0 = new DijkstraShortestPath<VertexBean, DefaultWeightedEdge>(graph, vertexList.get(15), vertexList.get(17));
		// GraphPath has some nice extra data, so I used it instead of their convenience method.
		GraphPath<VertexBean, DefaultWeightedEdge> path0 = dsp0.getPath();
		
		// Print from where to where.
		System.out.println("\nDirections from " + vertexList.get(15).getLocationName() + " to " + vertexList.get(17).getLocationName() +":");
		
		// Pull the vertices along the path and put them into an ArrayList.
		ArrayList<VertexBean> steps = new ArrayList<VertexBean>(Graphs.getPathVertexList(path0));
		// Process the vertices into print data. We need to access the current vertex and the next one to calculate the direction. 
		for(int i = 0; i<steps.size()-1;i++){
			System.out.println("Go " + steps.get(i).getDirectionTo(steps.get(i+1)) + " to " + steps.get(i+1).getLocationName());
		}
		
		// Print total distance for curiosity.
		System.out.println("\nTotal path distance: " + path0.getWeight());

		// End our timer and print the results.
		long end = Calendar.getInstance().getTimeInMillis();
		System.out.println("\nIt took this long to complete this stuff: " + (end - start) + "ms");
	}
}