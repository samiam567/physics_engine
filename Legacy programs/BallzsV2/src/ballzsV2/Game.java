package ballzsV2;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import physics_simulator.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Game implements Serializable { //Game class -=-=-=-=-=-=-=
	
	public String player_name;
	
	public Font bigFont = new Font("TimesRoman", Font.BOLD,(int) Math.round(.6 * Settings.boxSize) );
	public Font smallFont = new Font("TimesRoman", Font.BOLD,(int) Math.round(.4 * Settings.boxSize) );
	
	public Ball[] balls1 = {};
	public ArrayList<Ball> balls = new ArrayList<Ball>(Arrays.asList(balls1));
	
	public Box[] boxes1 = {};
	public ArrayList<Box> boxes = new ArrayList<Box>(Arrays.asList(boxes1));
	
	public BallzsV2_mainframe mainframe = new BallzsV2_mainframe();
	
	public int ball_amount = 0,ball_stash, level, high_score = 0;
	
	public int current_health = (int) ( 1+(level + Math.round((ball_stash*Math.pow(level, 2) - 5 * level ) / ( Math.pow(level,2) + 5*level))) );
	
	public boolean game_on = true;
	
	public Collision_handler collision_handler = new Collision_handler();
	
	
	public void initiate() {
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.setVisible(true);
		System.out.println("Jarvis is now online");
		mainframe.setSize(Settings.width,Settings.height);
	//	mainframe.setLayout(new FlowLayout());
		mainframe.setTitle("Ballzs V1.0  -  By Alec Pannunzio");
		Ball test = new Ball(10, 10, 10, 10, 10, 10,Color.BLACK);
			
		int timess = 0;
	//	mainframe.add(new Background(Settings.backgroundColor));
		StartScreen start = new StartScreen(this);
		gameFrame frame = new gameFrame(this);
	
		mainframe.add(start);
		mainframe.add(frame);
	
	//	mainframe.add(test);
		/*
		while (timess < 100) {
			test.move();
			timess++;
			collision_handler.checkForCollision(test,balls,boxes,ball_amount); //will check for collisions and delete balls if they hit a box
			Wait(1);
			test.repaint();
		}
*/
		
		System.out.println("Initiating mainframe");
		JFrame MousePad = new JFrame();
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		
		
	
		
		
		Save_file save = null;	
		BallzsV2_runner run = new BallzsV2_runner();
		
	
		//Start Screen >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>		
//		StartScreen start = new StartScreen(this);
		mainframe.add(start);
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			
		
		
			
			
			// loading game from the save file
			try {
				save = run.loadBZSave();
			} catch (ClassNotFoundException e) {
				System.out.println("ClassNotFoundException in load process");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("IOException in load process");
				e.printStackTrace();
			}
			
			//loading vars from save file
			high_score = save.high_score; //load the high score no matter what
			
		if (JOptionPane.showConfirmDialog(window, "Load Game from save file?", "Load Game?", 1, 1, null) == 0) {
			player_name = save.player_name;
			level = save.level;
			ball_stash = save.ball_stash;
			balls = save.balls;
			boxes = save.boxes;
			
			
			
			
			if ( (balls).size() == 0) {
				JOptionPane.showMessageDialog(window, "-Empty Save File-");
				setup();
			}
			
		} else {
			
			setup();
		}
		
		
		//Starts here -----------------------------
	
		
		System.out.println("Your name is: " + player_name);
		
		

		
		//add boxes
	
		
		
		System.out.println(level);

		

	
	
	
		while (game_on) {
			mainframe.repaint();
			frame = new gameFrame(this);
			frame.repaint();
		
			System.out.println("Level: " + level);
			System.out.println("Ball Stash: " + ball_stash);
			System.out.println("Cube health: " + current_health);
			
			//add Balls
			int times;
			double xSpeed = 0,ySpeed = 0;
			
			
			
			if (Settings.human) {
				boolean do_over = true;
				int firing_angle_int = 0;
				
				
				while (do_over) {
					
			//		String firing_angle = JOptionPane.showInputDialog(window, "What is the firing angle? (0-180) type ok when you are ready to fire.");
					String firing_angle = "30";
					try {		
						firing_angle_int = Integer.parseInt(firing_angle);
						if ( (firing_angle_int == 0) || (firing_angle_int == 180) || (firing_angle_int == 1) || (firing_angle_int == 2) || (firing_angle_int == 179) || (firing_angle_int == 178)) {
							do_over = true;
							firing_angle_int = 90;
							JOptionPane.showMessageDialog(window, "The firing angle must be an integer that is not 0 or 180 degrees","Invalid Firing Angle", 0);
						}
							
							
						double firing_angle_rad = firing_angle_int * Math.PI / 180; //change angle from degrees to radians
						xSpeed = Settings.ballSpeed * Math.cos(firing_angle_rad);  //find x component of the velocity of the ball
						ySpeed = - Math.abs( Settings.ballSpeed * Math.sin(firing_angle_rad)); //find y component of the velocity of the ball
						
						//draw a line of balls to show the predicted trajectory of the ball
						int ballX = Settings.width/2,ballY = (int) Math.round(Settings.height - (.1 * Settings.height));
						mainframe.add(new gameFrame(this));
						Ball aim = new Ball(ballX,ballY,xSpeed,ySpeed,0,Settings.ballSize/2,Color.WHITE);
						while (aim.y >= 0) {
							
							mainframe.add(aim);
						
							aim.move();
						}
						
					}catch(NumberFormatException e) { //this will run if the input for firing angle is not an int 
						if (firing_angle.equals("ok")) {
							do_over = false; //exit the loop and fire the ball!
						}else {
							do_over = true; //bad input, start over
							JOptionPane.showMessageDialog(window, "The firing angle must be an integer that is not 0 or 180 degrees","Invalid Firing Angle", 0);
						}
						
					}
				}
			
			
				//recalculating firing variables to avoid variable not instantiated errors
				double firing_angle_rad = firing_angle_int * Math.PI / 180;				
				xSpeed = Settings.ballSpeed * Math.cos(firing_angle_rad);
				ySpeed = - Math.abs( Settings.ballSpeed * Math.sin(firing_angle_rad));
				int ballX = Settings.width/2,ballY = (int) Math.round(Settings.height - (.1 * Settings.height));
				
				System.out.println("xSpeed: " + xSpeed);
				System.out.println("ySpeed: " + ySpeed);
				
			}
			
			//adding balls to the balls list  (firing the balls)
			int ball_inc = ball_amount;
			while (ball_inc <= ball_stash) {
				if (! Settings.human) {
					xSpeed = Math.round( (Math.random()+1) * Settings.ballSpeed/3 ) ;
					ySpeed = - Math.round( Math.sqrt( Math.pow(Settings.ballSpeed,2) - Math.pow(xSpeed,2) ) );
					if (level % 2 == 0) {
						xSpeed = -xSpeed;
					}
				}
				
				(balls).add( new Ball(Settings.width/2,(int) Math.round(Settings.height - (.1 * Settings.height)),xSpeed,ySpeed,ball_amount,Settings.ballSize,Settings.ballColor) );
				ball_amount++;
				ball_inc++;
		
				times = 0;
				while (times < 4) {
					Wait(1);
					frame = new gameFrame(this);
					frame.repaint();
					times++;
				}
			}
		
				
				while (ball_amount > 0) {
					frame = new gameFrame(this);
					frame.repaint();
				}
				frame = new gameFrame(this);
				frame.repaint();//draw one more frame after loop ends 
		
				//draw boxes
				for (Box current_box : boxes) {
					if (current_box.isVisible) {
						current_box.y += current_box.size;
						current_box.centerY = current_box.y + current_box.size/2;
						frame = new gameFrame(this);
						frame.repaint();
						
						if ((current_box.y + current_box.size) > Settings.height - (.1 * Settings.height) ) { //check if box hit bottom
							game_on = false;
						}
					}
				}
				
				
				
	
			
				
			if (game_on) {
				level++;
				ball_stash += 1;
				current_health = (int) (level + Math.round((ball_stash*Math.pow(level, 2) - 5 * level ) / ( Math.pow(level,2) + 5*level))); //logistically calculate the health of the next box
				(boxes).add(new Box( (int) (Math.random()*(Settings.width - (Settings.boxSize+15))),0,Settings.boxSize,current_health ,0) );
				frame = new gameFrame(this);
				frame.repaint();
				//Save the game -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
				try {
					if (level > high_score) high_score = level; //change the high score to the current score if the current score is higher than the high score
		
					run.saveBZSave(player_name,level,ball_stash,balls,boxes,high_score); //run the save-game method
				}catch(IOException e) {
					System.out.println("IOException in the save process");
				}
						
				
				//-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
			}else {
				JOptionPane.showMessageDialog(window, "Your score was: " + level,"GAME OVER", 0);
				//force clear the save file 
				System.out.println("Force clearing the save file...");
				try {
					Ball[] balls1 = {};
					ArrayList<Ball> balls = new ArrayList<Ball>(Arrays.asList(balls1));
					
					Box[] boxes1 = {};
					ArrayList<Box> boxes = new ArrayList<Box>(Arrays.asList(boxes1));
					
					run.saveBZSave(player_name,0,0,balls,boxes,high_score);
					
				}catch(IOException e) {
					System.out.println("IOException in the save process");
				}
			}

		}
	}
	
	public void Wait(double time) { // Wait(1000) evaluates to about 20 seconds

		double Time = time * 10000000;
		double t = 0;
		while (t < Time)
			t++;
	}
	
	
	public void setup() {
		player_name = JOptionPane.showInputDialog("What is your name?");
		boxes.add(new Box( (int) (Math.random()*(Settings.width - Settings.boxSize)),0,Settings.boxSize,current_health ,0) );
		level = 1;
		ball_stash = level;
	}
	
	

	
	public void drawBackground(Color color) {
	
		mainframe.add(new Background(Settings.backgroundColor));
		
	}
	
	

	public void addBall(Ball current_ball) {
		System.out.println("adding ball");
		mainframe.add(new Ball(50, 20, 10, 10, 10, 10,Color.BLACK));
	}	
	
}
