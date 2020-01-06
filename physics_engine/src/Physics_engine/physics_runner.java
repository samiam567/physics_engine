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
		Settings.frameColor = Color.blue;
		frame = new Physics_frame();
		drawer = new object_draw(frame);
		run();
	}
	
	public static void setDrawer(object_draw drawer1) {
		Settings.frameColor = Color.blue;
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
		
		
		
		Rectangular_prism shape1 = new Rectangular_prism(drawer,200,200,0,50,50,50,1);
		
		
		
		
		drawer.add(shape1);
	
		shape1.setColor(Color.red);
		
		shape1.setVectorAngularVelocity(new Vector3D(drawer,0.00002,0.00002,0.00002));

		
		drawer.start();
		
		resize(frame);
		
		

		
		
	
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	

	drawer.repaint();
	waitForEnd();
		
	
		
		
		
		
	}
	

}
