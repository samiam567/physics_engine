package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.colorchooser.ColorSelectionModel;

import Physics_engine.Physics_engine_toolbox;
import Physics_engine.Physics_frame;
import Physics_engine.ScoreBoard;
import Physics_engine.Settings;
import Physics_engine.SpeedTimer;
import Physics_engine.array;
import Physics_engine.border_bounce;
import Physics_engine.object_draw;
import ballistica.Ballistic_bullet;

public class Pong_runner {

	public static final String Version = "1.7.5";
	
	
	public static boolean cheatMode = false;
	
	
	public static boolean p2AI = false;
	public static double gameSetSpeed = 1; //speeds up or slows down all aspects of the game
	public static double gameSpeed;
	private static Pong_frame frame = new Pong_frame();
	private static object_draw drawer;
	private static Paddle leftPaddle;
	private static Paddle rightPaddle;
	public static Ball ball;
	public static ScoreBoard lScore, rScore;
	public static double paddleSpeed = 4 * gameSpeed, ballSpeed = 5 * gameSpeed, AI_difficulty = 1;
	private static SpeedTimer keyStrokeTimer1, keyStrokeTimer2;
	private static border_bounce borders;
	private static String[] yesOrNo = {"yes","no"};
	
	public static double diagonal = Math.sqrt(Math.pow(Settings.width,2) + Math.pow(Settings.height,2));
	
	
	
	public static void main(String[] args) {

		gameSpeed = gameSetSpeed * diagonal/1000;
		
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
		
		
		
		lScore = new ScoreBoard(drawer, 0.3 * Settings.width, 0.1 * Settings.height,"",0);
		lScore.setFont(new Font("TimesRoman", Font.BOLD, 70));
		drawer.add(lScore);
		
		rScore = new ScoreBoard(drawer, 0.7 * Settings.width, 0.1 * Settings.height,"",0);
		rScore.setFont(new Font("TimesRoman", Font.BOLD, 70));
		drawer.add(rScore);
		
		borders = new border_bounce(drawer);
		borders.isVisible = false;
		drawer.add(borders);
		setSettings();
	
		
		resize();
		drawer.start();
		
		//key listener
		drawer.addKeyListener(new KeyListener() {
			   
	         @Override
	         public void keyPressed(KeyEvent e) {
	        	  drawer.inactivity_timer = 0;      	     	
	        	  
	        	  
	      
	        	
	        	  //player1
	        	  switch (e.getKeyCode()) {
	        	  	case(87): //w
	        	  		drawer.remove(keyStrokeTimer1);
	        	  		leftPaddle.setSpeed(0, -paddleSpeed, 0);
	        	  		break;
	        	  	
	        	  	case(65): //a
	        	  		drawer.remove(keyStrokeTimer1);
	        	  		leftPaddle.setSpeed(-paddleSpeed, 0, 0);
	        	  		
	        	  	break;
	        	  	
	        	  	case(83): //s
	        	  		drawer.remove(keyStrokeTimer1);
	        	  		leftPaddle.setSpeed(0, paddleSpeed, 0);
	        	  		
	        	  	break;
	        	  	
	        	  	case(68): //d
	        	  		drawer.remove(keyStrokeTimer2);
	        	  		leftPaddle.setSpeed(paddleSpeed, 0, 0);
	        	  	break;
	        	  }
	        	  
	        	  //special keys
	        	  switch (e.getKeyCode()) {
	        	  
	        	  	case(32): //Space
	        	  		drawer.pause();
	        	  	break;
	        	  	
	        	  	case(10): //Enter
	        	  		drawer.resume();
	        	  	break;
	        	  	
	        	  	case (72): // H
	        	  		drawer.pause();
	        	  		JOptionPane.showMessageDialog(frame, "Press H for this screen \n press / to enter a command \n P1 Controls: WASD \n P2 Controls: Arrow keys \n Press Space to pause \n press Enter to resume", "Pong  V " + Version + "    Programed by Alec Pannunzio", 3);
	        	  		drawer.resume();
	        	  	break;
	        		
	        	  	case(74): //J
	        	  		drawer.pause();
	        	  		JOptionPane.showMessageDialog(frame, "\npress / to enter a command \nHit the ball while moving up/down to put spin on it \nHit the ball while moving left/right to make it go faster \nDon't hit the ball at all to lose the point ;)", "Pong  V " + Version + "    Programed by Alec Pannunzio", 3);
	        	  		drawer.resume();
	        	  	break;
	        	  		        	  	
	        	  	case(47): // /
	        	  		drawer.pause();
	        	  		doCommand();
	        	  		drawer.resume();
	        	  	break;
	        	  		
	        	  }
	        	  
	        	  //player2
	        	  if (! p2AI) {
		        	  switch (e.getKeyCode()) {
			        	  case(38): //Up
			        	  	drawer.remove(keyStrokeTimer2);
			        	  	rightPaddle.setSpeed(0, -paddleSpeed, 0);
			        	  	break;
			        	  	
			        	  case(40): //Down
				        	  	drawer.remove(keyStrokeTimer2);
				        	  	rightPaddle.setSpeed(0, paddleSpeed, 0);
				        	  	break;
				        	  	
			        	  case(37): //Left
				        	  	drawer.remove(keyStrokeTimer2);
				        	  	rightPaddle.setSpeed(-paddleSpeed, 0, 0);
				        	  	break;
				        	  	
			        	  case(39): //right
				        	  	drawer.remove(keyStrokeTimer2);
				        	  	rightPaddle.setSpeed(paddleSpeed, 0, 0);
				        	  	break;
			        	  	
		        	  }
	        	  }
	          }

			@Override
			public void keyReleased(KeyEvent arg0) {
				drawer.inactivity_timer = 0;
				
				int key = arg0.getKeyCode();
				
				//add timers so that the paddle will stop when the key is released
				if ((key == 87) || (key == 65) || (key == 83) || (key == 68)) {
					keyStrokeTimer1 = new SpeedTimer(drawer,0.1/gameSpeed,"seconds",0,0,0,leftPaddle);
					drawer.add(keyStrokeTimer1);
				}else if ((key == 38) || (key == 40) || (key == 37) || (key == 39)) {
					keyStrokeTimer2 = new SpeedTimer(drawer,0.1/gameSpeed,"seconds",0,0,0,rightPaddle);
					drawer.add(keyStrokeTimer2);
				}
				
				
			}
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				drawer.inactivity_timer = 0;
				
			}
			
	      });
		
		init();  //start the game
	}
	
	public static void init() {
		
		JOptionPane.showMessageDialog(frame, "Press H for this screen \n press J for advanced instructions \n P1 Controls: WASD \n P2 Controls: Arrow keys \n Press Space to pause \n press Enter to resume", "Pong  V " + Version + "    Programed by Alec Pannunzio", 3);
		
		
		
		int p2 = JOptionPane.showOptionDialog(frame, "Is player 2 a Human?", "P2", 1, 1, null, yesOrNo, 1);
		System.out.println(p2);
		if (p2 == 1) {
			
			setAIDiff(); //set the difficulty
			
			p2AI = true;
			System.out.println("Pong Terminator Uploaded");
			resize();
		}else{
			p2AI = false;
			
			System.out.println("P2 Pilot Systems Uploaded");
			
			rightPaddle.setSpeed(0, 0, 0);  //stopping the paddle
		}
	}
	
	public static void setAIDiff() {
		String[] difficulties = {"Easy","Normal","Hard","EXTREME"};
		int p2AI_diff_input = JOptionPane.showOptionDialog(frame, "What AI difficulty", "Difficulty", 1, 1, null, difficulties, 1);
		
		switch(p2AI_diff_input) {
			case(0):
				System.out.println("Difficulty: Easy");
				AI_difficulty = 0.7;
				break;
			
			case(1):
				System.out.println("Difficulty: Normal");
				AI_difficulty = 2;
				break;
			
			case(2):
				System.out.println("Difficulty: Hard");
				AI_difficulty = 3.1;
				break;
				
			case(3):
				System.out.println("Difficulty: EXTREME!!");
				AI_difficulty = 3.2;
				gameSetSpeed = 2.3;
				break;
		}
	}
	
	public static void reColor(Color primary, Color secondary, Color tertiary) {
		frame.setColor(primary);
		
		leftPaddle.setColor(secondary);
		rightPaddle.setColor(secondary);
		ball.setColor(secondary);
		
		lScore.setColor(tertiary);
		rScore.setColor(tertiary);
		
		drawer.setFrame(frame);
		
	}
	
	public static void doCommand() {
		String[] commandsStr = {"/help","/AIDifficulty","/cheatMode","/gameSpeed","/switchColors"};
		array commands = new array("String");
		commands.setValues(commandsStr);
		
		String command = JOptionPane.showInputDialog(frame, "Command \n Type /help for a list of possible commands");
		
		switch(command) {
			case("/help"):
				JOptionPane.showMessageDialog(frame, commands);
				break;
				
			case("/AIDifficulty"):
				setAIDiff();
				break;
				
			case("/cheatMode"):
				if (JOptionPane.showOptionDialog(frame, "Do you want cheat mode on?", "cheatMode", 2, 1, null, yesOrNo, 1) == 0) {
					cheatMode = true;
				}else {
					cheatMode = false;
				}
				break;
				
			case("/gameSpeed"):
				try {
					gameSetSpeed = Double.parseDouble(JOptionPane.showInputDialog(frame, "What do you want the gameSpeed to be?"));
					resize();
				}catch(NumberFormatException n) {
					JOptionPane.showMessageDialog(frame, "Invalid gameSpeed","Error", 0);
				}
				break;
			case("/switchColors"):
				reColor(Physics_engine_toolbox.getColorFromUser(frame),Physics_engine_toolbox.getColorFromUser(frame),Physics_engine_toolbox.getColorFromUser(frame));
				break;
				
			default:
				JOptionPane.showMessageDialog(frame, "Either the command you typed doesn't exist or it hasn't been programmed yet","Command Not found", 0);
				break;
		}
		
		resize();
	}
	
	public static void setSettings() {
		Settings.collision_algorithm = 4;
		Settings.rotationAlgorithm = 4;
		drawer.frameStep = 0.01;
		drawer.setFrameTime(40000000);
		Settings.timeOutTime = 5000000;
		
		Settings.width = 1000;
		Settings.height = 600;
	}
	
	public static void resize() {
		diagonal = Math.sqrt(Math.pow(Settings.width,2) + Math.pow(Settings.height,2));
		
		borders.resize();
		
		lScore.setPos(0.2 * Settings.width, 0.1 * Settings.height,0);
		rScore.setPos(0.7 * Settings.width, 0.1 * Settings.height,0);
		
		
		//make the game faster when the frame gets bigger
		gameSpeed = gameSetSpeed * diagonal/300;
		paddleSpeed = 4 * gameSpeed;
		ballSpeed = 5 * gameSpeed;
		Paddle.paddleHomingSpeed = Pong_runner.AI_difficulty * Pong_runner.gameSpeed;
		
		ball.reset();
		
	}
}
