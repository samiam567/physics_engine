package xfight;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;

import javax.swing.JOptionPane;

import Physics_engine.FCPS_display;
import Physics_engine.FPS_display;
import Physics_engine.New_object_listeners;
import Physics_engine.Physics_engine_toolbox;
import Physics_engine.Physics_frame;
import Physics_engine.PointSet;
import Physics_engine.SpeedTimer;
import Physics_engine.Square;
import Physics_engine.Vector2D;
import Physics_engine.border_bounce;
import Physics_engine.object_draw;
import Physics_engine.physicsRunner;
import Physics_engine.physics_object;
import Physics_engine.point;
import Physics_engine.pointed;
import calculator.Settings;
import pole_position.Car;

public class XFight_runner extends physicsRunner {
	
	public static final String Version = "V1.0.1";
	
	public static final int speed = 7;
	public static final int pewSpeed = 20;
	
	private static final String spaceShipFileName = "spaceShip.txt";
	private static final String enemyFileName = "astroid.txt";
	private static final String pewFileName = "spaceShip.txt";
	
	static SpaceShip ship;
	private static point[] enemyBlueprint;
	private static point[] pewBlueprint;
	
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
		drawer.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent m) {
				switch(m.getKeyCode()) {
			
	        	  	case(87): //w
	        	  
	        	  		break;
	        	  	
	        	  	case(65): //a
	        	  		ship.setRotation(ship.getXRotation(), ship.getYRotation(), -Math.PI/16);
	        	  		ship.setSpeed(-SpaceShip.turningSpeed, 0, 0);
	        	  	break;
	        	  	
	        	  	case(83): //s
	     
	        	  	break;
	        	  	
	        	  	case(32): //space
	        	  		
	        	  		Pew newPew = new Pew(drawer,pewBlueprint);
	        	       	drawer.add(newPew);
	        	  		
	        	  	
	        	  	break;
	        	  	
	        	  	
	        	  	case(68): //d
	        	  		ship.setRotation(ship.getXRotation(), ship.getYRotation(), Math.PI/16);
	        	  		ship.setSpeed(SpaceShip.turningSpeed, 0, 0);
	        	  		break;
	        	  
				}
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				drawer.inactivity_timer = 0;
				
				int key = arg0.getKeyCode();
				
		
				if ((key == 65) || (key == 68)) {
					ship.setRotation(ship.getXRotation(), ship.getYRotation(), 0);
					ship.setSpeed(0, 0, 0);
				}else if ((key == 87) || (key == 83)) {
				
				}
				
				
			}
			
		});
		
		
	//setting up
		boundries = new border_bounce(drawer);
		boundries.setName("boundries", 1);
		drawer.add(boundries);
		
		frame.setVisible(true);
	
		FPS_display fps = new FPS_display(drawer,30,30);
		drawer.add(fps);

		FCPS_display fcps = new FCPS_display(drawer,30,50);
		drawer.add(fcps);
		
		resize();
		
		drawer.start();
	
		
	//loading blueprints
		point[] spaceShipBlueprint = ((pointed) Physics_engine_toolbox.loadObjectFromFile(spaceShipFileName)).getPoints();
		ship = new SpaceShip(drawer,spaceShipBlueprint);
		drawer.add(ship);
		
		pewBlueprint = ((pointed) Physics_engine_toolbox.loadObjectFromFile(pewFileName)).getPoints();
		
		enemyBlueprint = ((pointed) Physics_engine_toolbox.loadObjectFromFile(enemyFileName)).getPoints();
		
		Enemy en1 = new Enemy(drawer,enemyBlueprint);	
		drawer.add(en1);
		
		
	
		
		waitForEnd();
		
	}

}
