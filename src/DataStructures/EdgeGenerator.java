import java.util.LinkedList;


public class EdgeGenerator
{
	public static final double DEFAULT_EDGE_WEIGHT_LIMIT = 50;

	private LinkedList<directedEdge> edges;
	private final vertex[] VERTICES;
	private final double EDGE_WEIGHT_LIMIT;

	/* Remove this when finished testing */
	public LinkedList<directedEdge> rawEdges;
	public LinkedList<directedEdge> removedEdges;
 
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

		EdgeGenerator edgeGenerator = new EdgeGenerator(vertices);
		edgeGenerator.generateEdges();

		System.out.println("\n***All " + edgeGenerator.rawEdges.size() +
			" edges generated from these vertices, before any filtering:\n");
		for (directedEdge e : edgeGenerator.rawEdges) {
			System.out.println(e.to().loc() + " | " + e.from().loc() + " | " +
				e.weight());
		}
		System.out.println("\n***All " + edgeGenerator.removedEdges.size() +
			" edges removed from these vertices after  filtering out all " +
			"heavy edges that do not isolate any vertices:\n");
		for (directedEdge e : edgeGenerator.removedEdges) {
			System.out.println(e.to().loc() + " | " + e.from().loc() + " | " +
				e.weight());
		}
		System.out.println("\n***All remaining " + edgeGenerator.edges.size() +
			" edges removed from these vertices after filtering out all " +
			"heavy edges that do not isolate any vertices:\n");
		for (directedEdge e : edgeGenerator.edges) {
			System.out.println(e.to().loc() + " | " + e.from().loc() + " | " +
				e.weight());
		}
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

		/* Remove this when finished testing */
		rawEdges = new LinkedList<directedEdge>();
		removedEdges = new LinkedList<directedEdge>();
	}

	public void generateEdges()
	{
		edges.clear();
		edges = filterByWeight(generateRawEdges());
	}

	private LinkedList<directedEdge> generateRawEdges()
	{
		LinkedList<directedEdge> rawEdgeList = new LinkedList<directedEdge>();

		// use VERTICES to generate every possible directed edge --FIXME



		/* Remove this when finished testing */
		rawEdges.clear();
		rawEdges = new LinkedList<directedEdge>(rawEdgeList);

		return rawEdgeList;
	}

	private LinkedList<directedEdge> filterByWeight(
		LinkedList<directedEdge> edgeList)
	{
		/* Remove this when finished testing */
		removedEdges.clear();

		for (directedEdge edge : edgeList)
		{
			if (isHeavyEdge(edge) && !isEssentialEdge(edge))
			{
				edgeList.remove(edge);

				/* Remove this when finished testing */
				removedEdges.add(edge);
			} 
		}
		return edgeList;
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
