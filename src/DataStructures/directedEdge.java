//package WoWPS;


public class directedEdge
{
	private vertex to;
	private vertex from;
	private double weight;

	publix directedEdge(vertex to, vertex from)
	{
		this.to = to;
		this.from = from;
		this.weight = to.distTo(from);
	}

	public vertex to()
	{
		return to;
	}

	public vertex from()
	{
		return from;
	}

	public double weight()
	{
		return weight;
	}
