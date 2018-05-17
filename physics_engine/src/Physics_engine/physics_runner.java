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
		boundries.setName("boundries", 1);
		drawer.add(boundries);
		
		frame.setVisible(true);
	
		FPS_display fps = new FPS_display(drawer,30,30);
		drawer.add(fps);

		FCPS_display fcps = new FCPS_display(drawer,30,50);
		drawer.add(fcps);
		
		resize();
		
		
		New_object_listeners newObs = new New_object_listeners(drawer);
		
		Sphere sphere1 = new Sphere(drawer, Settings.width/2, Settings.height/2, 0, 100, 10, Math.PI/40);
		sphere1.setAngularVelocity(0.0, 0.0, 0.01);
	//	sphere1.isShaded = true;
		sphere1.setPointOfRotation(new point(drawer,Settings.width/2,Settings.height/2,0));
		
		drawer.add(sphere1);
		
		drawer.setFrameTimeMultiplier(100000);
		drawer.start();
		
		waitForEnd();
		
		
		
	}
	

}
