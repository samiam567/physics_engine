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
		
	
	//	@SuppressWarnings("unused")
	//	New_object_listeners News = new New_object_listeners(drawer);
		
		
		frame.setVisible(true);
	
		FPS_display fps = new FPS_display(drawer,30,30);
		drawer.add(fps);

		FCPS_display fcps = new FCPS_display(drawer,30,50);
		drawer.add(fcps);
		
		
		Physics_3DShape shape1 = new Shape3D(drawer,Shape3D.shape3dtype.cylinder,500,500,0,70,70,20,0.3);
		Physics_3DShape shape2 = new Shape3D(drawer,Shape3D.shape3dtype.sphere,800,800,0,50,50,500,0.35);
		//shape1.setAngularVelocity(0.2, 0.3, 0.4);
		shape1.setSpeed(0, 0, 0);
	
		
		drawer.add(shape1);
		drawer.add(shape2);
		shape1.setColor(Color.red);
		
		Sphere sp1 = new Sphere(drawer,Settings.width/2,Settings.height/2,Settings.depth/2,Settings.width/15,10,Math.PI/20);
		sp1.isFilled = false;
		sp1.isShaded = false;
		sp1.setAngularVelocity(0.1, 0.1, 0.1);
		//drawer.add(sp1);
		
		drawer.start();
		
		resize();
		
		

		shape1.setRotation(0.5,0.5,0.5);
		
	
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	drawer.pause();
	
	System.out.println("drawer paused.");


		waitForEnd();
		
	
		
		
		
		
	}
	

}
