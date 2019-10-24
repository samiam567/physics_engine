package xfight;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import Physics_engine.FCPS_display;
import Physics_engine.FPS_display;
import Physics_engine.Physics_engine_toolbox;
import Physics_engine.Physics_frame;
import Physics_engine.ScoreBoard;
import Physics_engine.object_draw;
import Physics_engine.physicsRunner;
import Physics_engine.point;
import Physics_engine.pointed;
import Physics_engine.Settings;

public class XFight_runner extends physicsRunner {
	
	public static final String Version = "1.0.5";
	
	public static final int speed = 7;
	public static final int pewSpeed = 20;
	

	
	private static final String spaceShipFileName = "spaceShip.txt";
	private static final String enemyFileName = "astroid.txt";
	private static final String pewFileName = "pew.txt";

	public static int Score = 0;
	
	public static SpaceShip ship;
	private static ScoreBoard scoreboard;
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
		//loading blueprints
				point[] spaceShipBlueprint = ((pointed) Physics_engine_toolbox.loadObjectFromFile(spaceShipFileName)).getPoints();
				ship = new SpaceShip(drawer,spaceShipBlueprint);
				drawer.add(ship);
				
				pewBlueprint = ((pointed) Physics_engine_toolbox.loadObjectFromFile(pewFileName)).getPoints();
				
				enemyBlueprint = ((pointed) Physics_engine_toolbox.loadObjectFromFile(enemyFileName)).getPoints();
				
				//adding enemies
				drawer.add(new Enemy(drawer,enemyBlueprint));
				drawer.add(new Enemy(drawer,enemyBlueprint));
				drawer.add(new Enemy(drawer,enemyBlueprint));
				
				Settings.frameTime = 100;
				
		drawer.addMouseMotionListener( new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {	

			}

			@Override
			public void mouseMoved(MouseEvent e) {
				
				
			
				ship.setSpeed((e.getX() - ship.getCenterX() )/10,(-ship.getCenterY() + e.getY())/10,0);
				
				if (e.getX() > ship.getCenterX()) {
					ship.setRotation(0, 0,Math.PI/2 + Math.atan((-ship.getCenterY() + e.getY())/(e.getX() - ship.getCenterX())));
				}else {
					ship.setRotation(0, 0,3*Math.PI/2 + Math.atan((-ship.getCenterY() + e.getY())/(e.getX() - ship.getCenterX())));
				}
			}
			
		});
		
		drawer.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				Pew newPew = new Pew(drawer,pewBlueprint);
				newPew.setRotation(0,0,ship.getZRotation());
    	       	drawer.add(newPew);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {
				Pew newPew = new Pew(drawer,pewBlueprint);
				newPew.setRotation(0,0,ship.getZRotation());
    	       	drawer.add(newPew);
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {}});
		
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
	        	  	
	        	  	
	        	  	case(10): //ENTER
	        	  		drawer.add(new Enemy(drawer,enemyBlueprint));
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
		
		
		frame.setVisible(true);
	
		frame.setTitle("XFight V" + Version);
		
		FPS_display fps = new FPS_display(drawer,30,30);
		drawer.add(fps);

		FCPS_display fcps = new FCPS_display(drawer,30,50);
		drawer.add(fcps);
		
		scoreboard = new ScoreBoard(drawer,Settings.width * 0.9,Settings.height * 0.1,"Score:",0);
		drawer.add(scoreboard);
		
		resize();
		
		drawer.start();
		
	
		
	
		
		
		
		
		while(frame.isActive()) {
			scoreboard.setScore(Score);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
