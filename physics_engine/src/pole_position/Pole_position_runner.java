package pole_position;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Physics_engine.Physics_frame;
import Physics_engine.SpeedTimer;
import Physics_engine.Square;
import Physics_engine.border_bounce;
import Physics_engine.object_draw;
import Physics_engine.physicsRunner;
import calculator.Settings;

public class Pole_position_runner extends physicsRunner {

	public static object_draw drawer;
	
	private static Car PlayerCar,AI1;
	
	private static String Version = "1.0.2";
	
	public static double carSpeed = 20;
	
	static Track trackL;

	private static Track trackR;
	
	private static Sign sign1;
	
	public static void main(String[]  args) {
		frame = new Physics_frame();
		frame.setTitle("Pole Position V" + Version + "           Programed by Alec Pannunzio");
		drawer = new object_draw(frame);
		run();
	}
	
	private static void init() {
		PlayerCar = new Car(drawer,Settings.width * 0.5,Settings.height * 0.7,10,true);
	//	PlayerCar.setRotation(Math.PI/4, 0, Math.PI/16);
		
		AI1 = new Car(drawer,Settings.width * 0.5, Settings.height * 0.5,10,false);
		
		trackL = new Track(drawer,Settings.width * 0.4);
		trackL.generateTrack();
		
		trackR = new Track(drawer,Settings.width * 0.9);
		trackR.generateTrack();
		
		sign1 = new Sign(drawer,Settings.height/2,trackL);
		
		drawer.addMouseListener(new MouseAdapter() {
			public void MouseClicked() {
				
			}
		});
		
		drawer.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent m) {
				switch(m.getKeyCode()) {
			
	        	  	case(87): //w
	        	  		trackL.setSpeed(trackL.getXSpeed(),carSpeed, 0);
	        	  		trackR.setSpeed(trackR.getXSpeed(),carSpeed, 0);
	        	  		break;
	        	  	
	        	  	case(65): //a
	        	  		PlayerCar.setRotation(PlayerCar.getXRotation(), PlayerCar.getYRotation(), -Math.PI/8);
	        	  		trackL.setSpeed(5,trackL.getYSpeed(), 0);
	        	  		trackR.setSpeed(5,trackR.getYSpeed(), 0);
	        	  	break;
	        	  	
	        	  	case(83): //s
	        	  		trackL.setSpeed(trackL.getXSpeed(),0, 0);
	        	  		trackR.setSpeed(trackR.getXSpeed(),0, 0);
	        	  	break;
	        	  	
	        	  	case(68): //d
	        	  		PlayerCar.setRotation(PlayerCar.getXRotation(), PlayerCar.getYRotation(), Math.PI/8);
	    	  			trackL.setSpeed(-5,trackL.getYSpeed(), 0);
	    	  			trackR.setSpeed(-5,trackR.getYSpeed(), 0);
	        	  		break;
	        	  
				}
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				drawer.inactivity_timer = 0;
				
				int key = arg0.getKeyCode();
				
				//add timers so that the paddle will stop when the key is released
				if ((key == 65) || (key == 68)) {
					PlayerCar.setRotation(PlayerCar.getXRotation(), PlayerCar.getYRotation(), 0);
					drawer.add(new SpeedTimer(drawer,0.2,"seconds",0,trackL.getYSpeed(),0,trackL));
					drawer.add(new SpeedTimer(drawer,0.2,"seconds",0,trackR.getYSpeed(),0,trackR));
				}else if ((key == 87) || (key == 83)) {
					drawer.add(new SpeedTimer(drawer,0.2,"seconds",trackL.getXSpeed(),0,0,trackL));
					drawer.add(new SpeedTimer(drawer,0.2,"seconds",trackR.getXSpeed(),0,0,trackR));
				}
				
				
			}
			
		});
	}
	
	public static void run() {
		init();
		runGame();
		
		waitForEnd();
		System.exit(1);
	}
	
	private static void runGame() {
		drawer.add(PlayerCar);
		drawer.add(trackL);
		drawer.add(trackR);
		drawer.add(AI1);
	//	drawer.add(sign1);
		drawer.start();
	
		
	}
	

}
