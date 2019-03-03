package graph_theory;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JOptionPane;

import Physics_engine.drawable;
import Physics_engine.object_draw;
import calculator.Settings;

public class Graph {
	
	private object_draw drawer;
	private double lowestCost = 99999999999999999999.0;
	
	//for drawing
	int xPos = Settings.width/2;
	int yPos = Settings.height/2;
	int zPos = 0;
	
	ArrayList<Vertex> vertexes = new ArrayList<Vertex>();
	ArrayList<Integer> lowestPath = new ArrayList<Integer>();
	
	public Graph(object_draw drawer,int[][][] adjacencyMatrix) {
		this.drawer = drawer;
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			vertexes.add(new Vertex(drawer,this,i));
			drawer.add(vertexes.get(i));
			vertexes.get(i).setPos(xPos + (Settings.width/4) * Math.cos(i * 2*Math.PI/adjacencyMatrix.length), yPos + (Settings.height/4) * Math.sin(i * 2*Math.PI/adjacencyMatrix.length), zPos);
			vertexes.get(i).setColor(Color.YELLOW);
		}
		
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			for (int c = i; c < adjacencyMatrix[0].length; c++) {
				for ( int q = 1; q < adjacencyMatrix[i][c].length; q++) {
					System.out.println("connecting " + " to " + " with cost of " + adjacencyMatrix[i][c][q]);
					vertexes.get(i).connectTo(vertexes.get(c),adjacencyMatrix[i][c][q]);
				}
			
			}
			
		}
	}
	
	public Graph(object_draw drawer,int[][] adjacencyMatrix) {
		this.drawer = drawer;
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			vertexes.add(new Vertex(drawer,this,i));
			drawer.add(vertexes.get(i));
			vertexes.get(i).setPos(xPos + Math.cos(i * 360/adjacencyMatrix.length), yPos + Math.sin(i * 360/adjacencyMatrix.length), zPos);
			vertexes.get(i).setColor(Color.YELLOW);
		}
		
		
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			for (int c = i; c < adjacencyMatrix[0].length; c++) {
				for ( int q = 0; q < adjacencyMatrix[i][c]; q++) {
					System.out.println("connecting " + i + " to " + c);
					vertexes.get(i).connectTo(vertexes.get(c));
				}
			}
		}
	}
	
	public Graph(int numVertexes) {
		for (int i = 0; i < numVertexes; i++) {
			vertexes.add(new Vertex(drawer,this,i));
		}
	}
	
	public void optimizedHamiltonCircut() {
		drawer.resume();
		//sort edges
		for (Vertex v1 : vertexes) {
			Collections.sort( v1.Edges, new Comparator<Edge>() {
			     
		        public int compare(Edge o1, Edge o2) {
		            return Double.compare(o1.cost, o2.cost);
		        }
		    });
		}
		
		System.out.print("0");
		ArrayList<Integer> path = new ArrayList<Integer>();
		path.add(0);
		vertexes.get(0).runHamiltonConnection(0,path,0);
		System.out.print("\n{");
		for (Integer i : lowestPath) System.out.print(i + ",");
		System.out.println("}");
		System.out.println(lowestCost);
		
		drawer.pause();
		
		String circutStr = "";
		for (int i = 0; i < lowestPath.size()-1;i++) circutStr += ("" + lowestPath.get(i) + "->");
		circutStr += lowestPath.get(lowestPath.size()-1);
		
		JOptionPane.showMessageDialog(drawer,circutStr,"OptimizedCircut:", 1);
		
	}
	
	public boolean allVertexesUsed() {
		for (Vertex v : vertexes) {
			if (! v.isUsed) return false;
		}
		return true;
	}
	
	public void registerPath(ArrayList<Integer> currentPath, double cost) {
		if (cost < lowestCost) {
			lowestPath = currentPath;
			lowestCost = cost;
		}
	}
}
