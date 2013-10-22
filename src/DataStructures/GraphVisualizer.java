import java.awt.Component;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Scanner;

public class GraphVisualizer extends Component
{
	private static final int SCALE_FACTOR = 2;

	private final vertex[] VERTICES;
	private final directedEdge[] EDGES;

	public static void main(String[] args)
	{
		String vertexFile = "Eastern Kingdom Coordinates - Sheet1.csv";
		VertexGenerator vertexGenerator = new VertexGenerator(vertexFile);
		vertexGenerator.generateVertices();
		vertex[] vertices = vertexGenerator.vertices();

		System.out.println("\n***Number of vertices generated from '" +
			vertexFile + "': " + vertices.length + "\n");

		System.out.println("***Expected number of directed edges without " +
			"any filtering: " + vertices.length * (vertices.length - 1));

		EdgeGenerator edgeGenerator = new EdgeGenerator(vertices,
			Double.POSITIVE_INFINITY);
		edgeGenerator.generateEdges();
		directedEdge[] edges = edgeGenerator.edges();

		System.out.println("***Actual number of directed edges after " +
			"filtering out all edges weighing more than " +
			edgeGenerator.maxEdgeWeight() + ": " + edges.length);

		edgeGenerator = new EdgeGenerator(vertices);
		edgeGenerator.generateEdges();
		directedEdge[] filteredEdges = edgeGenerator.edges();

		System.out.println("***Actual number of directed edges after " +
			"filtering out all edges weighing more than " +
			edgeGenerator.maxEdgeWeight() + ": " + filteredEdges.length + "\n");

		TarjanSCCComputer sccComputer = new TarjanSCCComputer(vertices,
			edgeGenerator.edges());
		sccComputer.computeSCCs();
		vertex[][] sccs = sccComputer.sccs();

		System.out.println("***Number of strongly connected components " +
			"(SCCs) in the graph using these " + vertices.length +
			" vertices and " + filteredEdges.length + " edges (must be 1 to " +
			"guarantee a path to every vertex): " + sccs.length);

		System.out.print("***Number of vertices in the SCC(s):");
		for (vertex[] scc : sccs)
		{
			System.out.print(" " + scc.length);
		}
		System.out.println();

		System.out.println("View the graph (y/n)?");
		Scanner scan = new Scanner(System.in);
		String answer = scan.nextLine();
		
		if (answer != null && answer.length() > 0 &&
			answer.substring(0,1).toUpperCase().equals("Y"))
		{
			GraphVisualizer.visualize(vertices, filteredEdges);
		}
	}

	public static void visualize(vertex[] vertices, directedEdge[] edges)
	{
		int frameWidth = 757 / SCALE_FACTOR;
		int frameHeight = 1450 / SCALE_FACTOR;
		javax.swing.JFrame frame = new javax.swing.JFrame();
		frame.setSize(frameWidth, frameHeight);
		frame.setVisible(true);
		frame.getContentPane().add(new GraphVisualizer(vertices,
			edges));
	}
	
	public GraphVisualizer(vertex[] vertices, directedEdge[] edges)
	{
		VERTICES = vertices;
		EDGES = edges;
	}

	public void paint(Graphics g) {
		g.setColor(Color.BLACK);

		drawVertices(g);
		drawEdges(g);
	}

	private void drawVertices(Graphics g)
	{
		for (vertex v : VERTICES)
		{
			int x = (int) v.xCoord();
			int y = (int) v.yCoord();

			g.fillOval((x / SCALE_FACTOR) - 2, (y / SCALE_FACTOR) - 2, 5, 5);
		}
	}

	private void drawEdges(Graphics g)
	{
		for (directedEdge edge : EDGES)
		{
			vertex from = edge.from();
			int fromX = (int) from.xCoord();
			int fromY = (int) from.yCoord();

			vertex to = edge.to();
			int toX = (int) to.xCoord();
			int toY = (int) to.yCoord();

			g.drawLine((fromX / SCALE_FACTOR), (fromY / SCALE_FACTOR),
				(toX / SCALE_FACTOR), (toY / SCALE_FACTOR));
		}
	}
}
