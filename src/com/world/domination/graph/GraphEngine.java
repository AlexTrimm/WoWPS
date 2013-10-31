package com.world.domination.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import com.world.domination.beans.*;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

public class GraphEngine {
	SimpleWeightedGraph<VertexBean, DefaultWeightedEdge> g;
	ArrayList<VertexBean> vertexList;
	double MAX_EDGE_WEIGHT = 105.65;
	
	public GraphEngine(String fileName){
		g = new SimpleWeightedGraph<VertexBean, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		vertexList = new ArrayList<VertexBean>();
		generateGraphFromFile(fileName);
	}
	
	public void generateGraphFromFile(String fileName){
		BufferedReader reader = null;
		String line = "";
		int vertexID = 0;
		
		// Reads file and adds vertices
		try {
			reader = new BufferedReader(new FileReader(fileName));
			line = reader.readLine();	// skip header

			while ((line = reader.readLine()) != null) {
				String separator = ",";
				String[] values = line.split(separator, 4);

				vertexList.add(vertexID, new VertexBean(values[0], values[1], Double.parseDouble(values[2]), Double.parseDouble(values[3]), vertexID));
				g.addVertex(vertexList.get(vertexID));
				vertexID++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		int vertexNum = g.vertexSet().size();
		
		VertexBean[] vertexArray = new VertexBean[vertexNum];
		g.vertexSet().toArray(vertexArray);
		
		for(int i = 0; i<vertexNum;i++){
			for(int j = i+1; j< vertexNum; j++){
				VertexBean vertex1 = vertexArray[i];
				VertexBean vertex2 = vertexArray[j];
				
				double weight = vertex1.getDistanceTo(vertex2);
				
				if(weight < MAX_EDGE_WEIGHT){
					DefaultWeightedEdge edge = g.addEdge(vertex1, vertex2);
					g.setEdgeWeight(edge, weight);
			
				}
			}
		}
		
	}
	
	public SimpleWeightedGraph<VertexBean, DefaultWeightedEdge> getGraph(){
		return g;
	}
	public ArrayList<VertexBean> getVertexList(){
		return vertexList;
	}
}