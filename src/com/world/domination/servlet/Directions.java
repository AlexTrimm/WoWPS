package com.world.domination.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.world.domination.beans.StepBean;
import com.world.domination.beans.VertexBean;
import com.world.domination.data.Data;

/**
 * Servlet implementation class Directions
 */
@WebServlet("/Directions")
public class Directions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Directions() {
        super();
        // Nothing to do here
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Nothing to do here
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String startLoc = request.getParameter("startLoc");
		String endLoc = request.getParameter("endLoc");
		
		Data data = Data.getInstance();
		SimpleWeightedGraph<VertexBean, DefaultWeightedEdge> graph = data.getGraph();
		ArrayList<VertexBean> vertexList = data.getVertexList();
		
		DijkstraShortestPath<VertexBean, DefaultWeightedEdge> dsp = new DijkstraShortestPath<VertexBean, DefaultWeightedEdge>(graph, vertexList.get(Integer.parseInt(startLoc)), vertexList.get(Integer.parseInt(endLoc)));

		
		GraphPath<VertexBean, DefaultWeightedEdge> path = dsp.getPath();
		ArrayList<StepBean> steps = data.processSteps(path);

		request.setAttribute("steps", steps);
		
		request.setAttribute("startLoc", path.getStartVertex().getLocationName());
		request.setAttribute("endLoc", path.getEndVertex().getLocationName());
		
		request.getRequestDispatcher("/WEB-INF/Directions.jsp").forward(request, response);		
	}
}