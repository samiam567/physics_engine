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
import zortex.Gun;

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
		
		
		
		//Gun shape1 = new Gun(drawer);
		
		Rectangular_prism shape1 = new Rectangular_prism(drawer,Settings.width/2, Settings.height/2, 0,50,100,20,1);
		shape1.setPointOfRotation(new point(drawer,0,0,0));
		
		Vector3D vec1 = new Vector3D(drawer,1,1,1);
		vec1.setName("vec1", 1);
		vec1.setR(0.1);
		System.out.println(vec1.getObjectName() + "\ni: " + vec1.getI() + "\nj: " + vec1.getJ() + "\nk: " + vec1.getK() + "\nr: " + vec1.getR());
		
		
		shape1.setVectorAngularVelocity(vec1);	
		//shape1.setAngularVelocity(0.2,0.1, 0.1);
		
		drawer.add(shape1);
	
		shape1.setColor(Color.black);
		
	
		drawer.start();
		
		resize(frame);
		
		

		
		
	
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	
	

	drawer.repaint();
	waitForEnd();
		
	
		
		
		
		
	}
	

}
