package vex_robot_auton_simulator;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import Physics_engine.FCPS_display;
import Physics_engine.FPS_display;
import Physics_engine.Physics_frame;
import Physics_engine.Settings;
import Physics_engine.Text;
import Physics_engine.border_bounce;
import Physics_engine.drawable;
import Physics_engine.movable;
import Physics_engine.object_draw;
import Physics_engine.physicsRunner;

public class Auton_simulator_runner extends physicsRunner {
	
	public static final double inchToPixelConversion = 5;
	
	public static final int sqFieldSize = 6;
	
	public static double squareSize = 23.2 * inchToPixelConversion;
	public static Robot robot;
	public static Field field;
	public static Text console;
	
	public static void main(String[] args) {
		Settings.frameColor = Color.GRAY;
		frame = new Physics_frame();
		drawer = new object_draw(frame);
		run();
	}
	
	public static void setDrawer(object_draw drawer1) {
		Settings.frameColor = Color.GRAY;
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
		
	
		Settings.perspective = false;
		
		console = new Text(drawer, 3 * Settings.width/4, 0.1 * Settings.height,"Hello! I'm pocket-Ryan!!\n");
		drawer.add(console);
		robot = new Robot(drawer,Robot.robSqX * Auton_simulator_runner.squareSize,Robot.robSqY * Auton_simulator_runner.squareSize);
		drawer.add(robot);
		
		field = new Field(drawer);
		drawer.add(field);
	
		drawer.start();
		
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Autonomous.autonomous();
		addListeners();
		resize(frame);
		
		

		
		
	
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		


	drawer.repaint();
	waitForEnd();
		

		
	}
	
	public static void addListeners() {
		frame.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent m) {
				System.out.println("keypressed");
				switch(m.getKeyCode()) {
			
	        	  	case(87): //w
	        	  		robot.move(2);
	        	  		
	        	  		break;
	        	  	
	        	  	case(65): //a
	        	  		
							
						
	        	  	break;
	        	  	
	        	  	case(83): //s
	        	  		
	        	  	break;
	        	  	
	        	  	case(32): //space
	        	  		
	        	  	break;
	        	  	
	        	  	case(16): //Shift
	        	  		
	        	  	break;
	        	  	
	        	  	
	        	  	case(10): //ENTER
	        	  		
	        	  	break;
	        	  	
	        	  	case(8): //Backspace
	        	  		
	        	  		break;
	        	  	case(68): //d
	        	  		
	        	  	break;
	        	  	
				
				}
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				drawer.inactivity_timer = 0;
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			
			
		});
	}
	
}
