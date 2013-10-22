import java.util.LinkedList;


public class EdgeGenerator
{
	public static final double DEFAULT_MAX_EDGE_WEIGHT = 105.65;

	private LinkedList<directedEdge> edges;
	private final vertex[] VERTICES;
	private final double MAX_EDGE_WEIGHT;
 
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

		System.out.println("***Actual number of directed edges after " +
			"filtering out all edges weighing more than " +
			edgeGenerator.maxEdgeWeight() + ": " + edgeGenerator.edges.size());

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
	}

	public EdgeGenerator(vertex[] vertices)
	{
		this(vertices, DEFAULT_MAX_EDGE_WEIGHT);
	}

	public EdgeGenerator(vertex[] vertices, double maxEdgeWeight)
	{
		edges = new LinkedList<directedEdge>();
		this.VERTICES = vertices;
		this.MAX_EDGE_WEIGHT = maxEdgeWeight;
	}

	public void generateEdges()
	{
		edges.clear();
		edges = filterByWeight(generateRawEdges());
	}

	private LinkedList<directedEdge> generateRawEdges()
	{
		LinkedList<directedEdge> rawEdgeList = new LinkedList<directedEdge>();

		for (int i=0; i < VERTICES.length; i++)
		{
			for (int j=0; j < VERTICES.length; j++)
			{
				if (i==j)
				{
					continue;
				}
				rawEdgeList.add(new directedEdge(VERTICES[i], VERTICES[j]));
			}
		}
		return rawEdgeList;
	}

	private LinkedList<directedEdge> filterByWeight(
		LinkedList<directedEdge> edgeList)
	{
		LinkedList<directedEdge> filteredList =
			new LinkedList<directedEdge>(edgeList);

		for (directedEdge edge : edgeList)
		{
			if (isHeavyEdge(edge) && !isEssentialEdge(edge))
			{
				filteredList.remove(edge);
			} 
		}
		return filteredList;
	}

	private boolean isHeavyEdge(directedEdge edge)
	{
		return edge.weight() > MAX_EDGE_WEIGHT;
	}

	private boolean isEssentialEdge(directedEdge edge)
	{
		return false; // --FIXME?
	}

	public double maxEdgeWeight()
	{
		return MAX_EDGE_WEIGHT;
	}

	public directedEdge[] edges()
	{
		return edges.toArray(new directedEdge[edges.size()]);
	}
}
