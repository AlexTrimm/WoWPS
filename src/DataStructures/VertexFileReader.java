import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
 
public class VertexFileReader {

	private String filePath;
	private LinkedList<vertex> vertices;
 
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
	}

	public VertexFileReader(String filePath)
	{
		this.filePath = filePath;
		vertices = new LinkedList<vertex>();
	}
 
	public void generateVertices()
	{
		BufferedReader reader = null;
		String line = "";
		int vertexID = 0;
		vertices.clear();

		try {
			reader = new BufferedReader(new FileReader(filePath));
			line = reader.readLine();	// skip header

			while ((line = reader.readLine()) != null) {
				vertices.add(parseLine(line, vertexID));
				vertexID++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private vertex parseLine(String line, int vertexID)
	{
		String separator = ",";
		String[] values = line.split(separator, 4);

		return new vertex(values[0], values[1], Double.parseDouble(values[2]),
			Double.parseDouble(values[3]), vertexID);
	}

	public vertex[] vertices()
	{
		return vertices.toArray(new vertex[vertices.size()]);
	}
}
