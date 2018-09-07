package Physics_engine;


import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ConcurrentModificationException;
import javax.swing.JFrame;

import Physics_engine.Physics_engine_toolbox.pointOfRotationPlaces;
import circle_tessellation.Tessellation_runner;
import pole_position.Track;

public class physics_runner extends physicsRunner {

	
	public static void main(String[] args) {
		frame = new Physics_frame();
		drawer = new object_draw(frame);
		run();
	}
	
	public static void setDrawer(object_draw drawer1) {
		frame =  new Physics_frame();
		drawer = drawer1;
		drawer.setFrame(frame);
	}
	
	public static void run() {
		boundries = new border_bounce(drawer);
		drawer.add(boundries);
		
		frame.setVisible(true);
	
		FPS_display fps = new FPS_display(drawer,30,30);
		drawer.add(fps);

		FCPS_display fcps = new FCPS_display(drawer,30,50);
		drawer.add(fcps);
		
		resize();
		
			
		New_object_listeners newObs = new New_object_listeners(drawer);
		
		Square sq = new Square(drawer, 500,200,0,50,10);
		
		Sphere sp = new Sphere(drawer,Settings.width/2 + 50 ,Settings.height/2 - 50,Settings.depth/2 - 50 ,100,10,Settings.thetaStep);
		
		sp.isTangible = false;
		sp.isShaded = false;
		
		Square sq2 = new Square(drawer, Settings.width/2,Settings.height/2,0,50,10);
		
		
		sp.setPointOfRotation(new point(drawer,Settings.width/2,Settings.height/2,Settings.depth/2));
		
		PointSet xAxis = new PointSet(drawer);
		xAxis.addPoint(new point(drawer,Settings.width/2,Settings.height/2,Settings.depth/2));
		xAxis.addPoint(new point(drawer,2*Settings.width/3,1 + Settings.height/2,Settings.depth/2));
		xAxis.initialize();
		xAxis.finish();
		xAxis.setPointOfRotation(new point(drawer,Settings.width/2,Settings.height/2,Settings.depth/2));
		xAxis.setParentObject(sp);
	
		PointSet yAxis = new PointSet(drawer);
		yAxis.addPoint(new point(drawer,Settings.width/2,Settings.height/2,Settings.depth/2));
		yAxis.addPoint(new point(drawer,1+Settings.width/2,Settings.height/3,Settings.depth/2));
		yAxis.initialize();
		yAxis.finish();
		yAxis.setParentObject(xAxis);
		yAxis.setPointOfRotation(new point(drawer,Settings.width/2,Settings.height/2,Settings.depth/2));
		
		
		PointSet zAxis = new PointSet(drawer);
		zAxis.addPoint(new point(drawer,Settings.width/2,Settings.height/2,Settings.depth/2));
		zAxis.addPoint(new point(drawer,1+Settings.width/2,1+Settings.height/2,Settings.depth/2 - Settings.height/3));
		zAxis.initialize();
		zAxis.finish();
		zAxis.setParentObject(xAxis);
		zAxis.setPointOfRotation(new point(drawer,Settings.width/2,Settings.height/2,Settings.depth/2));
		
		
		drawer.add(xAxis);
		drawer.add(yAxis);
		drawer.add(zAxis);
		drawer.add(sp);
		

		drawer.start();
		
		sp.setAngularVelocity(0.1, 0, 0.1);
		
		
		waitForEnd();
		
		
		
	}
	

}
