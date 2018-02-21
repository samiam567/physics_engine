package calculator;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;


public class Grapher extends Canvas {
	private static final graph[] graphs1 = {};
	public static ArrayList<graph> graphs = new ArrayList<graph>(Arrays.asList(graphs1));
	static JFrame frame = new JFrame();
	
	public void paint(Graphics page) {
		for (graph current_graph : graphs) {
			current_graph.paint(page);
		}
	}
	
	public void updateGraph() {
		frame.dispose();
		frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(Settings.width,Settings.height);
		frame.getContentPane().add(this);
	}
	
	
	public void openGraph() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(Settings.width,Settings.height);
		frame.getContentPane().add(this);
	}
	
	public void addGraph(graph newGraph) {
		graphs.add(newGraph);
	}
	
	public void addEquation(algebreic_calc newEq) {
		graphs.add(newEq.getGraph());
	}
}
