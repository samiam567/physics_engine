package vex_robot_auton_simulator;

import java.awt.Color;
import java.awt.Graphics;

import Physics_engine.Physics_drawable;
import Physics_engine.Settings;
import Physics_engine.object_draw;

public class Field extends Physics_drawable {
	
	public Field(object_draw drawer) {
		super(drawer);
		setPos(0,0,-0.02);
		addCubes();
	
	}
	@Override
	public void paint(Graphics page) {
		
		//draw grid squares
		for (int x = 0; x < Auton_simulator_runner.sqFieldSize; x++) {
			for (int y = 0; y < Auton_simulator_runner.sqFieldSize; y++) {
				page.drawRect(getX() +(int)( x * Auton_simulator_runner.squareSize),getY() + (int)( y * Auton_simulator_runner.squareSize),(int)( Auton_simulator_runner.squareSize),(int)( Auton_simulator_runner.squareSize));
			}
		}
		
		double s = Auton_simulator_runner.squareSize; //Square size of 100 pixels
		double i = Auton_simulator_runner.inchToPixelConversion; //Inches to pixels, predefined
		
		page.setColor(Color.red);
		//red unprotected goal
		page.drawRect((int) (0), (int) (0), (int) (s/2), (int) (s/2));
		page.drawRect((int) (0), (int) (0), (int) (s/2-s/16), (int) (s/2-s/16));

		//red protected goal
		page.drawRect((int) (6*s-s/2), (int) (0), (int) (s/2), (int) (3*s/4));
		page.drawRect((int) (6*s-s/2 + s/16), (int) (0), (int) (s/2-s/16), (int) (3*s/4-s/16));
		
		
		page.setColor(Color.blue);
		//blue unprotected goal
		page.drawRect((int) (0), (int) (6*s-s/2), (int) (s/2), (int) (s/2));
		page.drawRect((int) (0), (int) (6*s-s/2-s/16), (int) (s/2+s/16), (int) (s/2+s/16));

		//red protected goal
		page.drawRect((int) (6*s-s/2), (int) (6*s-3*s/4), (int) (s/2), (int) (3*s/4));
		page.drawRect((int) (6*s-s/2 + s/16), (int) (6*s-3*s/4 + s/16), (int) (s/2-s/16), (int) (3*s/4-s/16));
		
		
		//towers
		page.setColor(color.white);
		double towerDiameter = s/4;		
		double t = towerDiameter;
		
		page.drawOval((int)(3*s-towerDiameter/2),(int)(s-towerDiameter/2),(int)(towerDiameter),(int)(towerDiameter));
		
		page.drawOval((int)(1.5*s-t/2),(int)(3*s-t/2),(int)(t),(int)(t));
		page.drawOval((int)(3*s-t/2),(int)(3*s-t/2),(int)(t),(int)(t));
		page.drawOval((int)(4.5*s-t/2),(int)(3*s-t/2),(int)(t),(int)(t));
		
		page.drawOval((int)(3*s-t/2),(int)(5*s-t/2),(int)(t),(int)(t));
		
		
		
	
		
		
		
		
	}
	
	public void addCubes() {
		double s = Auton_simulator_runner.squareSize; //Square size of 100 pixels
		double i = Auton_simulator_runner.inchToPixelConversion; //Inches to pixels, predefined
		double cS = Cube.cubeSize;
		
		//line of 4 near left (blue side)
		drawer.add(new Cube(drawer, s+cS/2, 5*s-cS-cS/2));
		drawer.add(new Cube(drawer, s+cS/2, 5*s-2*cS-cS/2));
		drawer.add(new Cube(drawer, s+cS/2, 5*s-3*cS-cS/2));
		drawer.add(new Cube(drawer, s+cS/2, 5*s-4*cS-cS/2));
		

		//line of 3 near side (blue)
		drawer.add(new Cube(drawer, 2*s+cS/2, 5*s-2*cS-cS/2));
		drawer.add(new Cube(drawer, 2*s+cS/2, 5*s-3*cS-cS/2));
		
		drawer.add(new Cube(drawer, 2*s+cS/2, 5*s-4*cS-cS/2));
		drawer.add(new Cube(drawer, 2*s+cS/2+1, 5*s-4*cS-cS/2+1));
		
		//line of 2 near side (blue)
		drawer.add(new Cube(drawer, 3*s+cS/2, 5*s-3*cS-cS/2));
		
		drawer.add(new Cube(drawer, 3*s+cS/2, 5*s-4*cS-cS/2));
		drawer.add(new Cube(drawer, 3*s+cS/2+1, 5*s-4*cS-cS/2+1));
		drawer.add(new Cube(drawer, 3*s+cS/2+2, 5*s-4*cS-cS/2+2));

		
		//line of 1 near side (blue)
		drawer.add(new Cube(drawer, 4*s+cS/2, 5*s-4*cS-cS/2));
		drawer.add(new Cube(drawer, 4*s+cS/2+1, 5*s-4*cS-cS/2+1));
		drawer.add(new Cube(drawer, 4*s+cS/2+2, 5*s-4*cS-cS/2+2));
		drawer.add(new Cube(drawer, 4*s+cS/2+3, 5*s-4*cS-cS/2+3));
		
		
		//protected zone cubes
		drawer.add(new Cube(drawer, 4*s+cS/2, 6*s-4*cS-cS/2));
		drawer.add(new Cube(drawer, 5*s+cS/2, 6*s-4*cS-cS/2));
		
		
		//line of 4 far left (red side)
		drawer.add(new Cube(drawer, s+cS/2, s+cS+cS/2));
		drawer.add(new Cube(drawer, s+cS/2, s+2*cS+cS/2));
		drawer.add(new Cube(drawer, s+cS/2, s+3*cS+cS/2));
		drawer.add(new Cube(drawer, s+cS/2, s+4*cS+cS/2));
		
		//line of 3 far side (red)
		drawer.add(new Cube(drawer, 2*s+cS/2, s+2*cS+cS/2));
		drawer.add(new Cube(drawer, 2*s+cS/2, s+3*cS+cS/2));
		
		drawer.add(new Cube(drawer, 2*s+cS/2, s+4*cS+cS/2));
		drawer.add(new Cube(drawer, 2*s+cS/2 + 1, s+4*cS+cS/2 +1));
		
		//line of 2 far side (red)
		drawer.add(new Cube(drawer, 3*s+cS/2, s+3*cS+cS/2));
		
		drawer.add(new Cube(drawer, 3*s+cS/2, s+4*cS+cS/2));
		drawer.add(new Cube(drawer, 3*s+cS/2+1, s+4*cS+cS/2+1));
		drawer.add(new Cube(drawer, 3*s+cS/2+2, s+4*cS+cS/2+2));
				
		
		//line of 1 far side (red)
		drawer.add(new Cube(drawer, 4*s+cS/2, s+4*cS+cS/2));
		drawer.add(new Cube(drawer, 4*s+cS/2+1, s+4*cS+cS/2+1));
		drawer.add(new Cube(drawer, 4*s+cS/2+2, s+4*cS+cS/2+2));
		drawer.add(new Cube(drawer, 4*s+cS/2+3, s+4*cS+cS/2+3));
		
		//protected zone cubes (red)
		drawer.add(new Cube(drawer, 4*s+cS/2, 4*cS+cS/2));
		drawer.add(new Cube(drawer, 5*s+cS/2, 4*cS+cS/2));
	}

}
