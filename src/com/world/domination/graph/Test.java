package com.world.domination.graph;

import java.util.ArrayList;
import java.util.Calendar;
import com.world.domination.beans.*;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long start = Calendar.getInstance().getTimeInMillis();
		GraphEngine engine = new GraphEngine("Eastern Kingdom Coordinates - Sheet1.csv");
		
		SimpleWeightedGraph<VertexBean, DefaultWeightedEdge> graph = engine.getGraph();
		ArrayList<VertexBean> vertexList = engine.getVertexList();
		
		System.out.println("Number of vertices: " + graph.vertexSet().size());
		System.out.println("Number of edges: " + graph.edgeSet().size());
		System.out.println("Connected: " + new ConnectivityInspector<VertexBean, DefaultWeightedEdge>(graph).isGraphConnected());

//		DijkstraShortestPath<VertexBean, DefaultWeightedEdge> dsp0 = new DijkstraShortestPath<VertexBean, DefaultWeightedEdge>(graph, vertexList.get(0), vertexList.get(5));
//		GraphPath<VertexBean, DefaultWeightedEdge> path0 = dsp0.getPath();
//		ArrayList<DefaultWeightedEdge> pathSteps0 = new ArrayList<DefaultWeightedEdge>(path0.getEdgeList());
//		
//		System.out.println("\nPath from " +path0.getStartVertex().getLocationName() + " to "+ path0.getEndVertex().getLocationName()+":");
//		for(DefaultWeightedEdge edge:pathSteps0){
//			System.out.println(graph.getEdgeTarget(edge).getLocationName());
//		}
//		
//		System.out.println("\nTotal path distance: " + path0.getWeight());
//		
//		
//		
//		
//		DijkstraShortestPath<VertexBean, DefaultWeightedEdge> dsp1 = new DijkstraShortestPath<VertexBean, DefaultWeightedEdge>(graph, vertexList.get(17), vertexList.get(25));
//		GraphPath<VertexBean, DefaultWeightedEdge> path1 = dsp1.getPath();
//		ArrayList<DefaultWeightedEdge> pathSteps1 = new ArrayList<DefaultWeightedEdge>(path1.getEdgeList());
//		
//		System.out.println("\nPath from " +path1.getStartVertex().getLocationName() + " to "+ path1.getEndVertex().getLocationName()+":");
//		for(DefaultWeightedEdge edge:pathSteps1){
//			System.out.println(graph.getEdgeTarget(edge).getLocationName());
//		}
//		
//		System.out.println("\nTotal path distance: " + path1.getWeight());
//		
//		
//		
//		
//		DijkstraShortestPath<VertexBean, DefaultWeightedEdge> dsp2 = new DijkstraShortestPath<VertexBean, DefaultWeightedEdge>(graph, vertexList.get(40), vertexList.get(70));
//		GraphPath<VertexBean, DefaultWeightedEdge> path2 = dsp2.getPath();
//		ArrayList<DefaultWeightedEdge> pathSteps2 = new ArrayList<DefaultWeightedEdge>(path2.getEdgeList());
//		
//		System.out.println("\nPath from " +path2.getStartVertex().getLocationName() + " to "+ path2.getEndVertex().getLocationName()+":");
//		for(DefaultWeightedEdge edge:pathSteps2){
//			System.out.println(graph.getEdgeTarget(edge).getLocationName());
//		}
//		
//		System.out.println("\nTotal path distance: " + path2.getWeight());
		
		
		
		
		
		
		
		long end = Calendar.getInstance().getTimeInMillis();
		System.out.println("\nIt took this long to complete this stuff: " + (end - start) + "ms");
	}
}