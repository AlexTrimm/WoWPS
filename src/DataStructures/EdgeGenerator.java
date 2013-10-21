import java.util.LinkedList;


public class EdgeGenerator
{
	public static final double DEFAULT_EDGE_WEIGHT_LIMIT = 250;

	private LinkedList<directedEdge> edges;
	private final vertex[] VERTICES;
	private final double EDGE_WEIGHT_LIMIT;
 
	public static void main(String[] args)
	{
		String vertexFile = "Eastern Kingdom Coordinates - Sheet1.csv";
		VertexFileReader vertexGenerator = new VertexFileReader(vertexFile);
		vertexGenerator.generateVertices();
		vertex[] vertices = vertexGenerator.vertices();

		System.out.println("\n***All vertices generated from '" + vertexFile +
			"':\n");
		for (vertex v : vertices) {
			System.out.println(v.loc() + " | " + v.type() + " | " +
				v.xCoord() + " | " + v.yCoord() + " | " + v.ID());
		}

		System.out.println("\n***Expected number of directed edges before " +
			"any filtering: " + vertices.length * (vertices.length - 1));

		EdgeGenerator edgeGenerator = new EdgeGenerator(vertices);
		edgeGenerator.generateEdges();

		System.out.println("***Actual number of directed edges generated " +
			"before any filtering: " + edgeGenerator.edges.size() + "\n");
	}

	public EdgeGenerator(vertex[] vertices)
	{
		this(vertices, DEFAULT_EDGE_WEIGHT_LIMIT);
	}

	public EdgeGenerator(vertex[] vertices, double edgeWeightLimit)
	{
		edges = new LinkedList<directedEdge>();
		this.VERTICES = vertices;
		this.EDGE_WEIGHT_LIMIT = edgeWeightLimit;
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

		for (directedEdge edge : filteredList)
		{
			if (isHeavyEdge(edge) && !isEssentialEdge(edge))
			{
				filteredList.remove(edge);
			} 
		}
		return filteredList;
	}

	public boolean isHeavyEdge(directedEdge edge)
	{
		return edge.weight() > EDGE_WEIGHT_LIMIT;
	}

	public boolean isEssentialEdge(directedEdge edge)
	{
		return true; // --FIXME
	}

	public directedEdge[] edges()
	{
		return edges.toArray(new directedEdge[edges.size()]);
	}
}
