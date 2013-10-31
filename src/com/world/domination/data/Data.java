package com.world.domination.data;

import java.util.ArrayList;

import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.world.domination.beans.StepBean;
import com.world.domination.beans.VertexBean;

public class Data {
	private static Data instance = null;
	private SimpleWeightedGraph<VertexBean, DefaultWeightedEdge> graph;
	private ArrayList<VertexBean> vertexList;
	
	protected Data() {
		// Exists only to defeat instantiation.
	}
	
	public static Data getInstance() {
		if(instance == null) {
			instance = new Data();
		}
		return instance;
	}

	public SimpleWeightedGraph<VertexBean, DefaultWeightedEdge> getGraph() {
		return graph;
	}

	public void setGraph(SimpleWeightedGraph<VertexBean, DefaultWeightedEdge> graph) {
		this.graph = graph;
	}

	public ArrayList<VertexBean> getVertexList() {
		return vertexList;
	}

	public void setVertexList(ArrayList<VertexBean> vertexList) {
		this.vertexList = vertexList;
	}

	public ArrayList<StepBean> processSteps(GraphPath<VertexBean, DefaultWeightedEdge> path){
		ArrayList<StepBean> steps = new ArrayList<StepBean>();
		ArrayList<VertexBean> vertexList = new ArrayList<VertexBean>(Graphs.getPathVertexList(path));
		
		for(int i=0; i<vertexList.size()-1; i++){
			VertexBean source = vertexList.get(i);//graph.getEdgeSource(edge);
			VertexBean target = vertexList.get(i+1);//graph.getEdgeTarget(edge);
			steps.add(new StepBean(source.getLocationName(), target.getLocationName(), source.getDirectionTo(target)));
		}
		
		return steps;
	}
}
