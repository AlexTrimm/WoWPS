import java.util.LinkedList;
import java.util.HashSet;
import java.util.TreeSet;

public class TarjanSCCComputer
{
	private final vertex[] VERTICES;
	private final directedEdge[] EDGES;
	private HashSet<HashSet<vertex>> sccs;
	private int[] indices;
	private int[] lowlinks;
	private LinkedList<vertex> stack;
	private int index;

	public static void main(String[] args)
	{
		String vertexFile = "Eastern Kingdom Coordinates - Sheet1.csv";
		VertexGenerator vertexGenerator = new VertexGenerator(vertexFile);
		vertexGenerator.generateVertices();
		vertex[] vertices = vertexGenerator.vertices();

		EdgeGenerator edgeGenerator = new EdgeGenerator(vertices,
			Double.POSITIVE_INFINITY);
		edgeGenerator.generateEdges();
		directedEdge[] edges = edgeGenerator.edges();

		TarjanSCCComputer sccComputer = new TarjanSCCComputer(vertices, edges);
		sccComputer.computeSCCs();
		vertex[][] sccs = sccComputer.sccs();
	}

	public TarjanSCCComputer(vertex[] vertices, directedEdge[] edges)
	{
		this.VERTICES = vertices;
		this.EDGES = edges;
		sccs = new HashSet<HashSet<vertex>>();
	}

	public void computeSCCs()
	{
		sccs.clear();
		indices = new int[VERTICES.length];
		lowlinks = new int[VERTICES.length];
		
		for (int i=0; i < indices.length; i++)
		{
			indices[i] = -1;
		}

		index = 0;
		stack = new LinkedList<vertex>();

		for (vertex v : VERTICES)
		{
			if (indices[v.ID()] == -1)
			{
				computeSCC(v);
			}
		}
	}

	private void computeSCC(vertex v)
	{
		int vID = v.ID();

		indices[vID] = index;
		lowlinks[vID] = index;
		index++;
		stack.push(v);

		for (directedEdge e : EDGES)
		{
			if (e.from().ID() == vID)
			{
				vertex w = e.to();
				int wID = w.ID();

				if (indices[wID] < 0)
				{
					computeSCC(w);
					if (lowlinks[vID] > lowlinks[wID])
					{
						lowlinks[vID] = lowlinks[wID];
					}
				}
				else if (stack.contains(w))
				{
					if (lowlinks[vID] > indices[wID])
					{
						lowlinks[vID] = indices[wID];
					}
				}
			}
		}

		if (lowlinks[vID] == indices[vID])
		{
			HashSet<vertex> scc = new HashSet<vertex>();
			vertex w;

			while (stack.size() > 0 && ((w = stack.pop()).ID() != vID))
			{
				scc.add(w);
			}
			scc.add(v);
			sccs.add(scc);
		}
	}

	public vertex[][] sccs()
	{
		LinkedList<vertex[]> result = new LinkedList<vertex[]>();

		for (HashSet<vertex> scc : sccs)
		{
			LinkedList<vertex> vertices = new LinkedList<vertex>();

			for (vertex v : scc)
			{
				vertices.add(v);
			}
			if (vertices.size() > 0)
			{
				result.add(vertices.toArray(new vertex[vertices.size()]));
			}
		}
		return result.toArray(new vertex[result.size()][]);
	}
}
