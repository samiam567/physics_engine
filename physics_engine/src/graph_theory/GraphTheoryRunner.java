package graph_theory;

import java.awt.Color;

import Physics_engine.FCPS_display;
import Physics_engine.FPS_display;
import Physics_engine.Physics_frame;
import Physics_engine.Settings;
import Physics_engine.object_draw;
import Physics_engine.physicsRunner;

public class GraphTheoryRunner extends physicsRunner {

	
	public static void main(String[] args) {
		Settings.frameColor = Color.blue;
		frame = new Physics_frame();
		drawer = new object_draw(frame);
		
		frame.setVisible(true);
		
		FPS_display fps = new FPS_display(drawer,30,30);
		drawer.add(fps);

		FCPS_display fcps = new FCPS_display(drawer,30,50);
		drawer.add(fcps);
		
		/*
		int[][][] adjacencyMatrix = { 
				{{0},{1,20},{1,15},{1,10}}, //0
				{{1,20},{0},{1,30},{1,30}}, //1
				{{0},{1,30},{0},{1,10}}, //2
				{{1,10},{1,30},{1,10},{0}}, //3		
		};
		*/
		
		int[][][] adjacencyMatrix = {
			{{0},{1,10},{0},{0},{0},{1,20}}, //0
			{{1,10},{0},{1,20},{0},{1,10},{1,15}}, //1
			{{0},{1,20},{0},{1,5},{1,15},{1,30}}, //2
			{{0},{0},{1,5},{0},{1,10},{0}}, //3
			{{0},{1,10},{1,15},{1,10},{0},{1,5}}, //4
			{{1,20},{1,15},{1,30},{0},{1,5},{0}} //5
		};
		
		Graph newGraph = new Graph(drawer,adjacencyMatrix);
		drawer.start();
		drawer.pause();
		newGraph.optimizedHamiltonCircut();
	}
}
