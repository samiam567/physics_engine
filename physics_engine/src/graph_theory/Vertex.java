package graph_theory;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import Physics_engine.Physics_drawable;
import Physics_engine.object_draw;

public class Vertex extends Physics_drawable {
	private Graph graph;
	public int id;
	public double edgeCost; //costDump for travel method of edge
	public ArrayList<Edge> Edges = new ArrayList<Edge>();
	public boolean isUsed;
	protected int timesUsed = 0;
	
	public Vertex(object_draw drawer1, Graph g1, int id1) {
		drawer = drawer1;
		graph = g1;
		id = id1;
		name = "" + id;
	}
	
	public void runHamiltonConnection(int currentVertexCounter,ArrayList<Integer> currentPath,double cost1) {
		try {
			Thread.sleep(GraphTheoryRunner.waitTime);
		}catch(Exception e) {}
		
		int timesUsedTemp = timesUsed; //used to reset timesUsed after this method
		boolean isUsedTemp = isUsed;
		double cost = cost1;
		isUsed = true;
		timesUsed++;
		setColor(Color.pink);
		isFilled = true;
		
		if (graph.allVertexesUsed()) {			
			//going back to start
			if (id != 0) {
				for (Edge cEdge : Edges) {
					if ((cEdge.getVertex(0).id == 0) || (cEdge.getVertex(1).id == 0)) {
						if (! cEdge.isUsed) {
							Double newCost = new Double(cost);
							Object[] travelDump = cEdge.travelFrom(this);
							newCost += (double) travelDump[1];
							currentPath.add( ((Vertex)travelDump[0]).id);
							graph.registerPath(currentPath,newCost);
							System.out.println(";" + cost + " ");
						}
					}
				}
				/////	
			}else {
				graph.registerPath(currentPath,cost);
				System.out.println(";" + cost + " ");
			}
			
		}else {
			for (Edge cEdge : Edges) {
				if (! cEdge.isUsed) {
					
					ArrayList<Integer> newCurrentPath = new ArrayList<Integer>();
					for (Integer i : currentPath) newCurrentPath.add(i);
					double newCost = cost;
					Object[] travelDump = cEdge.travelFrom(this);
					Vertex nextVertex = (Vertex) travelDump[0];
					
					if (nextVertex.timesUsed < 1) {
						cEdge.isUsed = true;
						cEdge.setColor(Color.WHITE);
						newCost += (double) travelDump[1];
						newCurrentPath.add(graph.vertexes.indexOf(nextVertex));
						
						nextVertex.runHamiltonConnection(currentVertexCounter + 1, newCurrentPath, new Double(newCost));
					}
				}
			}
			for (Edge cEdge : Edges) { 
				cEdge.isUsed = false;
				cEdge.setColor(Color.magenta);
			}
			currentPath.remove(this);
			
		}
		
		isUsed = isUsedTemp;
		isFilled = false;
		timesUsed = timesUsedTemp;
		setColor(Color.YELLOW);
	}
	
	protected void connectBack(Edge newEdge) {
		Edges.add(newEdge);
	}
	
	public void connectTo(Vertex nextVertex, double cost) {
		Edge newEdge = new Edge(graph,this,nextVertex, cost);
		Edges.add(newEdge);
		nextVertex.connectBack(newEdge);
		drawer.add(newEdge);
	}

	
	public void connectTo(Vertex nextVertex) {
		Edge newEdge = new Edge(graph,this,nextVertex);
		Edges.add(newEdge);
		nextVertex.connectBack(newEdge);
		drawer.add(newEdge);
	}

	@Override
	public void paint(Graphics page) {
		page.drawString(name, getX()+12, getY());
		if (isFilled) {
			page.fillRect(getX()-5,getY()-5,10,10);		
		}else {
			page.drawRect(getX()-5,getY()-5,10,10);	
		}
	}
}	
