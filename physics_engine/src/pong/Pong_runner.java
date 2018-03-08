package pong;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Physics_engine.Physics_frame;
import Physics_engine.ScoreBoard;
import Physics_engine.Settings;
import Physics_engine.SpeedTimer;
import Physics_engine.border_bounce;
import Physics_engine.object_draw;
import ballistica.Ballistic_bullet;

public class Pong_runner {

	public static final String Version = "1.5.0";
	
	private static Pong_frame frame = new Pong_frame();
	private static object_draw drawer;
	private static Paddle leftPaddle;
	private static Paddle rightPaddle;
	public static Ball ball;
	public static ScoreBoard lScore, rScore;
	private static double paddleSpeed = 4;
	private static SpeedTimer keyStrokeTimer;
	private static border_bounce borders;
	public static double ballSpeed = 5;
	
	public static void main(String[] args) {
		setSettings();
		drawer = new object_draw(frame);
		
		leftPaddle = new Paddle(drawer,"left");
		leftPaddle.setName("player",1);
		drawer.add(leftPaddle);
	
		rightPaddle = new Paddle(drawer,"right");
		rightPaddle.setName("AI",1);
		drawer.add(rightPaddle);
		
		ball = new Ball(drawer);
		ball.setName("pong ball",1);
		drawer.add(ball);
		
		borders = new border_bounce(drawer);
		drawer.add(borders);
		
		lScore = new ScoreBoard(drawer, 0.3 * Settings.width, 0.1 * Settings.height,"",0);
		lScore.setFont(new Font("TimesRoman", Font.BOLD, 70));
		drawer.add(lScore);
		
		rScore = new ScoreBoard(drawer, 0.7 * Settings.width, 0.1 * Settings.height,"",0);
		rScore.setFont(new Font("TimesRoman", Font.BOLD, 70));
		drawer.add(rScore);
		
		drawer.start();
		
		//key listener
		drawer.addKeyListener(new KeyListener() {
			   
	         @Override
	         public void keyPressed(KeyEvent e) {
	        	  drawer.inactivity_timer = 0;      	     	
	        	  
	        	  drawer.remove(keyStrokeTimer);
	        	  
	        	  switch (e.getKeyCode()) {
	        	  	case(87):
	        	  		System.out.println("w");
	        	  		leftPaddle.setSpeed(0, -paddleSpeed, 0);
	        	  	break;
	        	  	
	        	  	case(65):
	        	  		System.out.println("a");
	        	  		leftPaddle.setSpeed(-paddleSpeed, 0, 0);
	        	  	break;
	        	  	
	        	  	case(83):
	        	  		System.out.println("s");
	        	  		leftPaddle.setSpeed(0, paddleSpeed, 0);
	        	  	break;
	        	  	
	        	  	case(68):
	        	  		System.out.println("d");
	        	  		leftPaddle.setSpeed(paddleSpeed, 0, 0);
	        	  	break;
	        	  	
	        	  	case(32):
	        	  		System.out.println("space");
	        	  		
	        	  	break;	  
	        	  }
	          }

			@Override
			public void keyReleased(KeyEvent arg0) {
				drawer.inactivity_timer = 0;
				
				keyStrokeTimer = new SpeedTimer(drawer,0.2,"seconds",0,0,0,leftPaddle);
				drawer.add(keyStrokeTimer);
			}
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				drawer.inactivity_timer = 0;
				
			}
			
	      });
		
	}
	
	public static void setSettings() {
		Settings.collision_algorithm = 4;
		Settings.rotationAlgorithm = 4;
	}
	
	public static void resize() {
		
		
	}
}
