package pong;

import java.awt.Color;
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

	public static final String Version = "1.6.0";
	
	public static double gameSpeed = 1; //speeds up or slows down all aspects of the game
	
	private static Pong_frame frame = new Pong_frame();
	private static object_draw drawer;
	private static Paddle leftPaddle;
	private static Paddle rightPaddle;
	public static Ball ball;
	public static ScoreBoard lScore, rScore;
	private static double paddleSpeed = 4 * gameSpeed;
	private static SpeedTimer keyStrokeTimer;
	private static border_bounce borders;
	public static double ballSpeed = 5 * gameSpeed ;
	
	
	
	public static void main(String[] args) {

		
		
		frame.setColor(Color.BLUE);

		drawer = new object_draw(frame);
		
		leftPaddle = new Paddle(drawer,"left");
		leftPaddle.setName("player",1);
		drawer.add(leftPaddle);
	
		rightPaddle = new Paddle(drawer,"right");
		rightPaddle.setName("AI",1);
		drawer.add(rightPaddle);
		
		ball = new Ball(drawer);
		ball.setName("pong ball",1);
		ball.setColor(Color.white);
		drawer.add(ball);
		
		borders = new border_bounce(drawer);
		borders.isVisible = false;
		drawer.add(borders);
		
		lScore = new ScoreBoard(drawer, 0.3 * Settings.width, 0.1 * Settings.height,"",0);
		lScore.setFont(new Font("TimesRoman", Font.BOLD, 70));
		drawer.add(lScore);
		
		rScore = new ScoreBoard(drawer, 0.7 * Settings.width, 0.1 * Settings.height,"",0);
		rScore.setFont(new Font("TimesRoman", Font.BOLD, 70));
		drawer.add(rScore);
		
		setSettings();
		
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
	        	  		drawer.inactivity_timer = 0;
	        	  		break;
	        	  	
	        	  	case(65):
	        	  		System.out.println("a");
	        	  		leftPaddle.setSpeed(-paddleSpeed, 0, 0);
	        	  		drawer.inactivity_timer = 0;
	        	  	break;
	        	  	
	        	  	case(83):
	        	  		System.out.println("s");
	        	  		leftPaddle.setSpeed(0, paddleSpeed, 0);
	        	  		drawer.inactivity_timer = 0;
	        	  	break;
	        	  	
	        	  	case(68):
	        	  		System.out.println("d");
	        	  		leftPaddle.setSpeed(paddleSpeed, 0, 0);
	        	  		drawer.inactivity_timer = 0;
	        	  	break;
	        	  	
	        	  	case(32):
	        	  		System.out.println("space");
	        	  		
	        	  	break;	  
	        	  }
	          }

			@Override
			public void keyReleased(KeyEvent arg0) {
				drawer.inactivity_timer = 0;
				
				keyStrokeTimer = new SpeedTimer(drawer,0.1/gameSpeed,"seconds",0,0,0,leftPaddle);
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
		drawer.frameStep = 0.001;
		drawer.setFrameTime(5000000);
		Settings.timeOutTime = 5000000;
	}
	
	public static void resize() {
		
		
	}
}
