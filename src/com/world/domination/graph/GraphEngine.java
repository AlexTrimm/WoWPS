import java.io.*;
import java.util.*;
import org.jgrapht.graph.*;

/*
 * I combined a lot of Al's stuff into this class. A lot of it was removed by switching to the JGraphT library, but I copied over 
 * most of the logic directly for generating vertices. Edges are done a bit differently though.
 */
public class GraphEngine {
	// Storage variables.
	SimpleWeightedGraph<VertexBean, DefaultWeightedEdge> g;
	ArrayList<VertexBean> vertexList;
	
	// Set at the top of the page for fiddling. 
	double MAX_EDGE_WEIGHT = 105.65;
	
	// Constructor. Instantiate our variables and start the generation process.
	public GraphEngine(String fileName){
		g = new SimpleWeightedGraph<VertexBean, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		vertexList = new ArrayList<VertexBean>();
		generateGraphFromFile(fileName);
	}
	
	// Generates both vertices and final edges. 
	public void generateGraphFromFile(String fileName){
		BufferedReader reader = null;
		String line = "";
		int vertexID = 0;
		
		// Reads file and adds vertices
		try {
			reader = new BufferedReader(new FileReader(fileName));
			line = reader.readLine();	// skip header
			line = reader.readLine(); // Load the first line
			
			while (line != null) {
				String separator = ",";
				String[] values = line.split(separator, 4);
				
				// Add to vertex list for reasons explained in Test.java
				vertexList.add(new VertexBean(values[0], values[1], Double.parseDouble(values[2]), Double.parseDouble(values[3]), vertexID));
				// Add to the actual graph. Note the vertexID is the index for the VertexList
				g.addVertex(vertexList.get(vertexID));
				
				// Advance the vertexId and the reader.
				vertexID++;
				line = reader.readLine();
			}
		} catch (Exception e) { // Catch all exceptions because I don't give a fuck. 
			e.printStackTrace();
		} finally { // Al's closing code
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		//----------------------------------------------------------------------------
		// Time to create some edges.
		//----------------------------------------------------------------------------
		
		// Info for iterating over the vertices
		int vertexNum = g.vertexSet().size();
		
		// Creating a simple array from the VertexSet because fuck set iterators. 
		VertexBean[] vertexArray = new VertexBean[vertexNum];
		g.vertexSet().toArray(vertexArray);
		
		// For every vertex, iterate over all vertices that haven't had their turn already. (Basically iterate over vertices after the current one.)
		for(int i = 0; i<vertexNum;i++){
			for(int j = i+1; j< vertexNum; j++){
				// Pull our two vertices.
				VertexBean vertex1 = vertexArray[i];
				VertexBean vertex2 = vertexArray[j];
				
				// Calculate the hypothetical edge distance. 
				double weight = vertex1.getDistanceTo(vertex2);
				
				// If the distance isn't too much, add the edge to the graph.
				if(weight < MAX_EDGE_WEIGHT){
					// This library is a little wonky. They have you add the edge and then go back and set the weight with a separate method call (wtf).
					DefaultWeightedEdge edge = g.addEdge(vertex1, vertex2);
					g.setEdgeWeight(edge, weight);
				}
			}
		}
	}
	
	// Return the graph from our storage variable.
	public SimpleWeightedGraph<VertexBean, DefaultWeightedEdge> getGraph(){
		return g;
	}
	// Return the VertexList from our storage variable.
	public ArrayList<VertexBean> getVertexList(){
		return vertexList;
	}
}