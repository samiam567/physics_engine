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
		
		Square square2 = new Square(drawer,400,200,0,100,10);
		square2.setColor(Color.MAGENTA);
		
		Square square1 = new Square(drawer,200,200,0,100,10);
		
		square2.setParentObject(square1);
		square1.setName("square1", 1);
		
		square2.setPointOfRotationPlace(pointOfRotationPlaces.parentCenter);
		//square2.setPointOfRotation(new point(drawer,200,200,0));
		
		drawer.setFrameTimeMultiplier(10);
		
		drawer.add(square1);
		drawer.add(square2);

		drawer.start();
		
		waitForEnd();
		
		
		
	}
	

}
