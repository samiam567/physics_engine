package graph_theory;

import java.awt.Color;
import java.awt.Graphics;

import Physics_engine.Physics_drawable;

public class Edge extends Physics_drawable{
	public boolean isUsed = false;
	private Vertex[] vertexes = new Vertex[2];
	public double cost = 0;
	private Graph graph;
	
	public Edge(Graph graphForEdge, Vertex startVertex, Vertex endVertex) {
		graph = graphForEdge;
		vertexes[0] = startVertex;
		vertexes[1] = endVertex;
		setColor(Color.MAGENTA);
	}
	
	public Edge(Graph graphForEdge, Vertex startVertex, Vertex endVertex, double cost) {
		graph = graphForEdge;
		vertexes[0] = startVertex;
		vertexes[1] = endVertex;
		this.cost = cost;
		setColor(Color.MAGENTA);
	}
	
	public Vertex getVertex(int indx) {
		if ((0 <= indx) && (indx < vertexes.length)) {
			return vertexes[indx];
		}else {
			System.out.println("ERROR: Bad index for getVertex: " + indx);
			return null;
		}
	}
	
	public Object[] travelFrom(Vertex v1) {
		
		if (vertexes[0].id == v1.id) {
			System.out.print(v1.id + "->");
			System.out.print(vertexes[1].id);
			return  new Object[] {vertexes[1],cost};
		}else if (vertexes[1].id == v1.id){
			System.out.print(v1.id + "->");
			System.out.print(vertexes[0].id);
			return new Object[] {vertexes[0],cost};
		}else {
			System.out.println("ERROR in Edge");
			return null;
		}
	}

	@Override
	public void paint(Graphics page) {
		page.drawLine(vertexes[0].getX(), vertexes[0].getY(),vertexes[1].getX(), vertexes[1].getY());
		page.drawString("" + cost, (vertexes[0].getX() + vertexes[1].getX())/2,(vertexes[0].getY()+vertexes[1].getY())/2);
	}

}
