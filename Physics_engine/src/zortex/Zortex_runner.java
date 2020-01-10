package zortex;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;

import Physics_engine.FCPS_display;
import Physics_engine.FPS_display;
import Physics_engine.Physics_drawable;
import Physics_engine.Physics_engine_toolbox;
import Physics_engine.Physics_engine_toolbox.pointOfRotationPlaces;
import Physics_engine.Physics_frame;
import Physics_engine.Rectangular_prism;
import Physics_engine.ScoreBoard;
import Physics_engine.Settings;
import Physics_engine.Vector3D;
import Physics_engine.drawable;
import Physics_engine.movable;
import Physics_engine.object_draw;
import Physics_engine.physicsRunner;
import Physics_engine.point;
import Physics_engine.pointed;
import Physics_engine.rectangle;
import Physics_engine.rotatable;


public class Zortex_runner extends physicsRunner {
	
public static final String Version = "3.0.0";
	
	public static int Score = 0;
	
	public static Gun gun;
	private static ScoreBoard scoreboard;

	public static boolean game_over = false;
	
	public static int wallZDist = 200;
	
	public static int prevMouseX = Settings.width/2, prevMouseY = Settings.height/2;
	
	public static int wallXSize = 2*Settings.width;
	public static int wallYSize = 2*Settings.height;
	
	public static int charSpeed = 100;
	


	public static double wallZLength = 20500;
	
	public static WallRect startWallRect, endWallRect;

	public static double  bodyXSize = Settings.width/4, bodyYSize = Settings.height/4;
	
	public static void main(String[] args) {
		
		frame = new Physics_frame();
		run();
		
	}
	
	public static void setDrawer(object_draw drawer1) {
		frame =  new Physics_frame();
		drawer = drawer1;
		drawer.setFrame(frame);
	
		
	}
	
	public static void reset() {
		resize(); //this resets all of the enemies' positions 
		Score = 0;
		game_over = false;
	}
	
	public static void addObjects() {
		
		Settings.fixedFPS_FStep = false;
		Settings.frameStep = 0.01;
		gun = new Gun(drawer);
		drawer.add(gun);
		

		FPS_display fps = new FPS_display(drawer,30,30);
		fps.setColor(Color.DARK_GRAY);
		drawer.add(fps);

		FCPS_display fcps = new FCPS_display(drawer,30,50);
		fcps.setColor(Color.DARK_GRAY);
		drawer.add(fcps);
	
		scoreboard = new ScoreBoard(drawer,Settings.width * 0.9,Settings.height * 0.1,"Score:",0);
		scoreboard.setColor(Color.green);
		drawer.add(scoreboard);
		
		
				
		//add walls
		for (int i = 0; i <= wallZLength; i+= wallZDist) {
			endWallRect = new WallRect(drawer,i);
			object_draw.WaitNanotime(20000000);
		}
				
		drawer.add(new Enemy(drawer,Enemy.zSet));
		
				
				
	}
	
	public static void run() {
		
		drawer = new object_draw(frame);
		
		Settings.frameTime = 100;
		
		frame.setVisible(true);
		
		frame.setTitle("Zortex V" + Version);
		frame.setColor(Color.black);
		
		drawer.start();
		
		addObjects();
				
		
				
		drawer.addMouseMotionListener( new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {	
				//nothing
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				Vector3D mouseMoveVec = new Vector3D(drawer,prevMouseY-e.getY(),e.getX()-prevMouseX,0);
				mouseMoveVec.multiply(0.001);
				for (drawable cOb : drawer.getDrawables()) {
					if (cOb.getType().equals("enviro-move")) {
						try {
						((rotatable)cOb).addVectorRotation(mouseMoveVec);
						}catch(ClassCastException c) {}
						
					}
					
					
				}
				for (drawable cOb : drawer.getDrawables()) {
					if (cOb.getType().equals("enviro-move")) {
						try {
							cOb.Update(0.000000000000001);
						}catch(ClassCastException c) {}
						
					}
					
					
				}
				
			
				
				prevMouseX = e.getX();
				prevMouseY = e.getY();
			}
			
		});
		
		drawer.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {
				gun.fire();
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {}});
		
		frame.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent m) {
				System.out.println("keypressed");
				switch(m.getKeyCode()) {
			
	        	  	case(87): //w
	        	  		
	        	  		
	        	  		break;
	        	  	
	        	  	case(65): //a
	        	  		for (drawable cOb : drawer.getDrawables()) {
	    					if (cOb.getType().equals("enviro-move")) {
	    						((movable) cOb).setSpeed(charSpeed,((movable) cOb).getYSpeed(),((movable) cOb).getZSpeed());
	    					}
	    				}
	        	  	
		        	  	for (drawable cOb : drawer.getDrawables()) {
							if (cOb.getType().equals("enviro-move")) {
								try {
									cOb.Update(0.000000000000001);
								}catch(ClassCastException c) {}
								
							}
							
							
						}
	        	  	break;
	        	  	
	        	  	case(83): //s
	        	  		
	        	  	break;
	        	  	
	        	  	case(32): //space
	        	  		for (drawable cOb : drawer.getDrawables()) {
	    					if (cOb.getType().equals("enviro-move")) {
	    						((movable) cOb).setSpeed(((movable) cOb).getXSpeed(),charSpeed,((movable) cOb).getZSpeed());
	    					}
	    				}
	        	  	
		        	  	for (drawable cOb : drawer.getDrawables()) {
							if (cOb.getType().equals("enviro-move")) {
								try {
									cOb.Update(0.000000000000001);
								}catch(ClassCastException c) {}
								
							}
							
							
						}

	        	  	break;
	        	  	
	        	  	case(16): //Shift
	        	  		for (drawable cOb : drawer.getDrawables()) {
	    					if (cOb.getType().equals("enviro-move")) {
	    						((movable) cOb).setSpeed(((movable) cOb).getXSpeed(),-charSpeed,((movable) cOb).getZSpeed());
	    					}
	    				}
		        	  		
		        	  	for (drawable cOb : drawer.getDrawables()) {
							if (cOb.getType().equals("enviro-move")) {
								try {
									cOb.Update(0.000000000000001);
								}catch(ClassCastException c) {}
								
							}
							
							
						}
	        	  	break;
	        	  	
	        	  	
	        	  	case(10): //ENTER
	        	  		drawer.add(new Enemy(drawer,Enemy.zSet));
	        	  	break;
	        	  	
	        	  	case(68): //d
	        	  		for (drawable cOb : drawer.getDrawables()) {
	    					if (cOb.getType().equals("enviro-move")) {
	    						((movable) cOb).setSpeed(-charSpeed,((movable) cOb).getYSpeed(),((movable) cOb).getZSpeed());
	    					}
	        	  		}
	        	  	
		        	  	for (drawable cOb : drawer.getDrawables()) {
							if (cOb.getType().equals("enviro-move")) {
								try {
									cOb.Update(0.000000000000001);
								}catch(ClassCastException c) {}
								
							}
							
							
						}
	    			
	        	  	break;
	        	  	
				
				}
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				drawer.inactivity_timer = 0;
				for (drawable cOb : drawer.getDrawables()) {
					if (cOb.getType().equals("enviro-move")) {
						((movable) cOb).setSpeed(0,0,((movable)cOb).getZSpeed());
					}
				}
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println("keytyped");
				
				switch(e.getKeyChar()) {
					
					case('w'):
						for (drawable cOb : drawer.getDrawables()) {
							if (cOb.getType().equals("enviro-move")) {
								((movable) cOb).addSpeed(0,0,-charSpeed);
							}
						}
		        	  	for (drawable cOb : drawer.getDrawables()) {
							if (cOb.getType().equals("enviro-move")) {
								try {
									cOb.Update(0.000000000000001);
								}catch(ClassCastException c) {}
								
							}
						}
					break;
					
					case('s'):
						for (drawable cOb : drawer.getDrawables()) {
							if (cOb.getType().equals("enviro-move")) {
								((movable) cOb).addSpeed(0,0,charSpeed);
							}
						}
		        	  	for (drawable cOb : drawer.getDrawables()) {
							if (cOb.getType().equals("enviro-move")) {
								try {
									cOb.Update(0.000000000000001);
								}catch(ClassCastException c) {}
								
							}
						}
		        	break;
		        	
			}
			}
			
		});
		
		
	//setting up	
		
		resize();

		
		
		while(frame.isActive()) {
			scoreboard.setScore(Score);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (game_over) {
				drawer.pause();
				JOptionPane.showMessageDialog(drawer, "GAME OVER\nYour score was " + Score);

				if (JOptionPane.showConfirmDialog(null,"Do you want to play another game?", "Another?", 1, 1, null) == 0) {
					//playing again
					reset();
					resize();
					drawer.clearObjects();
					
					addObjects();
					drawer.resume();
				}else { 
					//ending game
					frame.dispose();
					break;			
				}
			}
		}
		
		
	}
	
	public static void resize() {
		physicsRunner.resize(frame);
		
		scoreboard.setPos(Settings.width * 0.9,Settings.height * 0.1,0);
	}
}
