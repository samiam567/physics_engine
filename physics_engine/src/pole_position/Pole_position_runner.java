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
	
	private static Car PlayerCar;
	
	private static Track track;
	
	public static void main(String[]  args) {
		frame = new Physics_frame();
		drawer = new object_draw(frame);
		run();
	}
	
	private static void init() {
		PlayerCar = new Car(drawer,Settings.width * 0.5,Settings.height * 0.7,10,true);
		PlayerCar.setRotation(-Math.PI/16, 0, 1*Math.PI/5);
		
		track = new Track(drawer,0);
		track.setHasNormalCollisions(false);
		track.generateTrack();
		
		drawer.addMouseListener(new MouseAdapter() {
			public void MouseClicked() {
				
			}
		});
		
		drawer.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent m) {
				switch(m.getKeyCode()) {
			
	        	  	case(87): //w
	        	  		track.setSpeed(0,3, -1);
	        	  		break;
	        	  	
	        	  	case(65): //a
	        	  		PlayerCar.setRotation(PlayerCar.getXRotation(), Math.PI/8, PlayerCar.getZRotation());
	        	  	break;
	        	  	
	        	  	case(83): //s
	        	  		track.setSpeed(0,0, 0);
	        	  	break;
	        	  	
	        	  	case(68): //d
	        	  		PlayerCar.setRotation(PlayerCar.getXRotation(), -Math.PI/8, PlayerCar.getZRotation());
	        	  		break;
	        	  
				}
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				drawer.inactivity_timer = 0;
				
				int key = arg0.getKeyCode();
				
				//add timers so that the paddle will stop when the key is released
				if ((key == 87) || (key == 65) || (key == 83) || (key == 68)) {
					PlayerCar.setRotation(PlayerCar.getXRotation(), 0, PlayerCar.getZRotation());
				}else if ((key == 38) || (key == 40) || (key == 37) || (key == 39)) {
				
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
		drawer.add(track);
		drawer.start();
	
		
	}
	

}
