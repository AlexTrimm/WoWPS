package com.world.domination.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.world.domination.beans.VertexBean;
import com.world.domination.beans.VertexBeanComparator;
import com.world.domination.data.Data;
import com.world.domination.graph.GraphEngine;

/**
 * Servlet implementation class Submit
 */
@WebServlet("/Submit")
public class Submit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Submit() {
        super();
        // Nothing to do here
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GraphEngine engine = new GraphEngine("..\\eclipseApps\\WoWProject\\WEB-INF\\Eastern Kingdom Coordinates - Sheet1.csv");

		Data data = Data.getInstance();
		data.setGraph(engine.getGraph());
		data.setVertexList(engine.getVertexList());
		
		ArrayList<VertexBean> vertexList = new ArrayList<VertexBean>(data.getVertexList());
		Collections.sort(vertexList,new VertexBeanComparator());
		
		request.setAttribute("locations",vertexList);
		request.getRequestDispatcher("/WEB-INF/Submit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Nothing to do here
	}
}