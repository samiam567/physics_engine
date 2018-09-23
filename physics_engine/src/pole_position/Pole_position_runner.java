package pole_position;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Physics_engine.New_object_listeners;
import Physics_engine.Physics_frame;
import Physics_engine.SpeedTimer;
import Physics_engine.Square;
import Physics_engine.border_bounce;
import Physics_engine.object_draw;
import Physics_engine.physicsRunner;
import Physics_engine.Physics_3DPolygon;
import calculator.Settings;

public class Pole_position_runner extends physicsRunner {

	public static object_draw drawer;
	
	static Car PlayerCar;

	private static Car AI1;
	
	private static String Version = "1.0.3";
	
	static Physics_3DPolygon trackL, trackR;
	
	static Track tLc,tRc;
	
	private static Sign sign1;
	
	public static void main(String[]  args) {
		frame = new Physics_frame();
		frame.setTitle("Pole Position V" + Version + "           Programed by Alec Pannunzio");
		drawer = new object_draw(frame);
		frame.setColor(Color.green);
		run();
	}
	
	private static void init() {
		
	
		PlayerCar = new Car(drawer,Settings.width * 0.5,Settings.height * 0.6,10,true);
		PlayerCar.setName("playerCar", 1);
		
		AI1 = new Car(drawer,Settings.width * 0.5, Settings.height * 0.5,10,false);
		
		tLc = new Track(drawer,Settings.width * 0.4);
		
		tRc = new Track(drawer,Settings.width * 0.9);
		
		trackL = tLc;
		
		trackR = tRc;
		
		
		sign1 = new Sign(drawer,Settings.height/2,tLc);
		
		drawer.addMouseListener(new MouseAdapter() {
			public void MouseClicked() {
				
			}
		});
		
		drawer.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent m) {
				switch(m.getKeyCode()) {
			
	        	  	case(87): //w
	        	  		trackL.setSpeed(trackL.getXSpeed(),Car.speed, 0);
	        	 		trackR.setSpeed(trackR.getXSpeed(),Car.speed, 0);
	        	 	
	        	  		break;
	        	  	
	        	  	case(65): //a
	        	  		PlayerCar.setRotation(PlayerCar.getXRotation(), PlayerCar.getYRotation(), -Math.PI/8);
	        	  		trackL.setAngularVelocity(0, 0, 0.1);
	        	  		trackR.setAngularVelocity(0, 0, 0.1);
	        	  		trackL.setSpeed(0,trackL.getYSpeed(), 0);
	        	  		trackR.setSpeed(0,trackR.getYSpeed(), 0);
	        	  	break;
	        	  	
	        	  	case(83): //s
	        	  		trackL.setSpeed(trackL.getXSpeed(),0, 0);
	        	  		trackR.setSpeed(trackR.getXSpeed(),0, 0);
	        	  	break;
	        	  	
	        	  	case(68): //d
	        	  		PlayerCar.setRotation(PlayerCar.getXRotation(), PlayerCar.getYRotation(), Math.PI/8);
		        	  	trackL.setAngularVelocity(0, 0, -0.05);
	        	  		trackR.setAngularVelocity(0, 0, -0.05);
	        	  		trackL.setSpeed(0,trackL.getYSpeed(), 0);
	        	  		trackR.setSpeed(0,trackR.getYSpeed(), 0);
	        	  		break;
	        	  
				}
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				drawer.inactivity_timer = 0;
				
				int key = arg0.getKeyCode();
				
			
				if ((key == 65) || (key == 68)) {
					PlayerCar.setRotation(PlayerCar.getXRotation(), PlayerCar.getYRotation(), 0);
					trackL.setAngularVelocity(0, 0, 0.0);
        	  		trackR.setAngularVelocity(0, 0, 0.0);
//        	  		drawer.add(new SpeedTimer(drawer,0.2,"seconds",0,tLc.getYSpeed(),0,tLc));
//					drawer.add(new SpeedTimer(drawer,0.2,"seconds",0,tRc.getYSpeed(),0,tRc));
			
				}else if ((key == 87) || (key == 83)) {
//					drawer.add(new SpeedTimer(drawer,0.2,"seconds",tLc.getXSpeed(),0,0,tLc));
//					drawer.add(new SpeedTimer(drawer,0.2,"seconds",tRc.getXSpeed(),0,0,tRc));
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
		drawer.add(tLc);
		drawer.add(tRc);
		drawer.add(AI1);
		drawer.add(sign1);
		drawer.start();
	
		System.out.println(PlayerCar.getXReal() + "," + PlayerCar.getYReal() + "," + PlayerCar.getZReal());
		
	}
	

}
