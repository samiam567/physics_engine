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
		
	
		@SuppressWarnings("unused")
		New_object_listeners News = new New_object_listeners(drawer);
		
		frame.setVisible(true);
	
		FPS_display fps = new FPS_display(drawer,30,30);
		drawer.add(fps);

		FCPS_display fcps = new FCPS_display(drawer,30,50);
		drawer.add(fcps);
		
		Square sq1 = new Square(drawer, Settings.width/2, Settings.height/2, 0,50,1);
		sq1.setAngularVelocity(0, 0, 0.5);
		
		
		Sphere sp1 = new Sphere(drawer, Settings.width/3,Settings.height/2,0,50, 1, Settings.thetaStep);
	//	sp1.setParentObject(sq1);
		sp1.setPointOfRotation(sq1.center);
		sp1.setSpeed(1, 20, 0);
		sp1.setAngularVelocity(0, 0, -0.5);
		
		drawer.add(sq1);
		drawer.add(sp1);
		
	//	Physics_frame frame2 = new Physics_frame();
		
	//	Map_object_draw drawer2 = new Map_object_draw(frame2,drawer,sq1,10000,10000);
		
	//	drawer2.start();
		
		
		
		resize();
		
		drawer.start();

		waitForEnd();
		
	
		
		
		
		
	}
	

}
