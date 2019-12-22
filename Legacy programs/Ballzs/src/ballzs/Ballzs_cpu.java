package ballzs;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ballzs.*;

public class Ballzs_cpu extends Canvas {
	
	public Settings settings = new Settings();
	
	

	public void Wait(double time) { // Wait(1000) evaluates to about 20 seconds

		double Time = time * 10000000;
		double t = 0;
		while (t < Time)
			t++;
	}
	
	public void paint(Graphics page) {
		

		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		
		class Game implements Serializable { //Game class -=-=-=-=-=-=-=
			
			public String player_name;
			
			public Font bigFont = new Font("TimesRoman", Font.BOLD,(int) Math.round(.6 * settings.boxSize) );
			public Font smallFont = new Font("TimesRoman", Font.BOLD,(int) Math.round(.4 * settings.boxSize) );
			
			public Ball[] balls1 = {};
			public ArrayList<Ball> balls = new ArrayList<Ball>(Arrays.asList(balls1));
			
			public Box[] boxes1 = {};
			public ArrayList<Box> boxes = new ArrayList<Box>(Arrays.asList(boxes1));
			
			
			
			public int ball_amount = 0,ball_stash, level, high_score = 0;
			
			public int current_health = (int) ( 1+(level + Math.round((ball_stash*Math.pow(level, 2) - 5 * level ) / ( Math.pow(level,2) + 5*level))) );
			
			public boolean game_on = true;
			
			public Collision_handler collision_handler = new Collision_handler();
			
			public void setup() {
				player_name = JOptionPane.showInputDialog("What is your name?");
				boxes.add(new Box( (int) (Math.random()*(settings.width - settings.boxSize)),0,settings.boxSize,current_health ,0) );
				level = 1;
				ball_stash = level;
			}
			public void drawBackground(Color color) {
				page.setColor(color);
				page.fillRect(0, 0, settings.width, settings.height);
			}
			
			public void drawBall(Ball current_ball) {
				page.setColor(Color.MAGENTA);
				page.fillOval(current_ball.x, current_ball.y, current_ball.size, current_ball.size);
			}
			
			public void drawBox(Box current_box) {
				// draw the box
				page.setColor(Color.CYAN);
				page.fillRect(current_box.x, current_box.y, current_box.size, current_box.size);
				page.setColor(Color.MAGENTA);
				page.drawRect(current_box.x, current_box.y, current_box.size, current_box.size); // outline in red

				// put numbers inside the box
				page.setFont(bigFont);
				String id_str = String.valueOf(current_box.health);
				
				page.drawString(id_str,(int) Math.round(current_box.x + 0.2 * current_box.size), (int) Math.round(current_box.y + 0.7 * current_box.size));
			
				
			}
			
			public void drawFrame() {
				drawBackground(Color.blue);
				
				//score-board thing
				page.setColor(Color.WHITE);
				page.setFont(bigFont);
				page.drawString("Score: " + level, (int) (0.05 * settings.width),(int) ( settings.height-2*settings.boxSize));
				page.drawString("High Score: " + high_score, (int) (0.65 * settings.width),(int) ( settings.height-2*settings.boxSize));
				
				for (Ball current_ball : balls ) {
					if (current_ball.isVisible) {
						drawBall(current_ball);
						current_ball.move();
						ball_amount = collision_handler.checkForCollision(current_ball,balls,boxes,ball_amount); //will check for collisions and delete balls if they hit a box
					}
			
				}
				
				for (Box current_box : boxes) {
					if (current_box.isVisible) {
						drawBox(current_box);
					}
				}
	
				Wait(1);

			}	
			
		}
	
		
		
		Save_file save = null;	
		Game game = new Game();
		Ballzs_runner run = new Ballzs_runner();
		
		//Start Screen >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		game.drawBackground(Color.blue);
		page.setColor(Color.CYAN);
		page.setFont(new Font("TimesRoman", Font.BOLD,settings.boxSize));
		page.drawString("Ballzs",(int) Math.round( settings.width/2 - ( settings.boxSize * 51/35) ),(int) ( settings.height * 0.3));
		
		
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
			game.high_score = save.high_score; //load the high score no matter what
			
		if (JOptionPane.showConfirmDialog(window, "Load Game from save file?", "Load Game?", 1, 1, null) == 0) {
			game.player_name = save.player_name;
			game.level = save.level;
			game.ball_stash = save.ball_stash;
			game.balls = save.balls;
			game.boxes = save.boxes;
			
			
			
			
			if ( (game.balls).size() == 0) {
				JOptionPane.showMessageDialog(window, "-Empty Save File-");
				game.setup();
			}
			
		} else {
			
			game.setup();
		}
		
		
		//Starts here -----------------------------
	
		
		System.out.println("Your name is: " + game.player_name);
		

		System.out.println(game.level);
		while (game.game_on) {
			
			game.drawFrame();
			
			System.out.println("Level: " + game.level);
			System.out.println("Ball Stash: " + game.ball_stash);
			System.out.println("Cube health: " + game.current_health);
			
		
			int times;
			double xSpeed = 0,ySpeed = 0;
			
			
			
			if (settings.human) {
				boolean do_over = true;
				int firing_angle_int = 0;
				
				
				while (do_over) {
					
					//long process of finding out where the user wants the balls to be fires ***********************************************************************************************
					String firing_angle = JOptionPane.showInputDialog(window, "What is the firing angle? (0-180) type ok when you are ready to fire.");
					
					try {		
						firing_angle_int = Integer.parseInt(firing_angle);
						if ( (firing_angle_int == 0) || (firing_angle_int == 180) || (firing_angle_int == 1) || (firing_angle_int == 2) || (firing_angle_int == 179) || (firing_angle_int == 178)) {
							do_over = true;
							firing_angle_int = 90;
							JOptionPane.showMessageDialog(window, "The firing angle must be an integer that is not 0 or 180 degrees","Invalid Firing Angle", 0);
						}
							
							
						double firing_angle_rad = firing_angle_int * Math.PI / 180; //change angle from degrees to radians
						xSpeed = settings.ballSpeed * Math.cos(firing_angle_rad);  //find x component of the velocity of the ball
						ySpeed = - Math.abs( settings.ballSpeed * Math.sin(firing_angle_rad)); //find y component of the velocity of the ball
						
						//draw a line of balls to show the predicted trajectory of the ball
						int ballX = settings.width/2,ballY = (int) Math.round(settings.height - (.1 * settings.height));
						game.drawFrame();
						Ball aim = new Ball(ballX,ballY,xSpeed,ySpeed,0,settings.ballSize/2);
						while (aim.y >= 0) {
							page.setColor(Color.WHITE);
							game.drawBall(aim);
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
				
				// *************************************************************************************************************************************************************************
			
				//recalculating firing variables to avoid variable not instantiated errors
				double firing_angle_rad = firing_angle_int * Math.PI / 180;				
				xSpeed = settings.ballSpeed * Math.cos(firing_angle_rad);
				ySpeed = - Math.abs( settings.ballSpeed * Math.sin(firing_angle_rad));
				int ballX = settings.width/2,ballY = (int) Math.round(settings.height - (.1 * settings.height));
				
				System.out.println("xSpeed: " + xSpeed);
				System.out.println("ySpeed: " + ySpeed);
				
			}
			
			//adding balls to the balls list  (firing the balls)
			int ball_inc = game.ball_amount;
			while (ball_inc <= game.ball_stash) {
				if (! settings.human) {
					xSpeed = Math.round( (Math.random()+1) * settings.ballSpeed/3 ) ;
					ySpeed = - Math.round( Math.sqrt( Math.pow(settings.ballSpeed,2) - Math.pow(xSpeed,2) ) );
					if (game.level % 2 == 0) {
						xSpeed = -xSpeed;
					}
				}
				
				(game.balls).add( new Ball(settings.width/2,(int) Math.round(settings.height - (.1 * settings.height)),xSpeed,ySpeed,game.ball_amount,settings.ballSize) );
				game.ball_amount++;
				ball_inc++;
		
				times = 0;
				while (times < 4) {
					Wait(1);
					game.drawFrame();
					times++;
				}
			}
		
				
				while (game.ball_amount > 0) {
					game.drawFrame();
				}
				game.drawFrame(); //draw one more frame after loop ends 
		
				
				// move boxes down
				for (Box current_box : game.boxes) {
					if (current_box.isVisible) {
						current_box.y += current_box.size;
						current_box.centerY = current_box.y + current_box.size/2;
						game.drawFrame();
						
						if ((current_box.y + current_box.size) > settings.height - (.1 * settings.height) ) { //check if box hit bottom
							game.game_on = false;
						}
					}
				}
				
				
				
	
			
				
			if (game.game_on) {
				game.level++;
				game.ball_stash += 1;
				
				//add next box
				game.current_health = (int) (game.level + Math.round((game.ball_stash*Math.pow(game.level, 2) - 5 * game.level ) / ( Math.pow(game.level,2) + 5*game.level))); //logistically calculate the health of the next box
				(game.boxes).add(new Box( (int) (Math.random()*(settings.width - (settings.boxSize+15))),0,settings.boxSize,game.current_health ,0) );
				game.drawFrame();
				
				//Save the game -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
				try {
					if (game.level > game.high_score) game.high_score = game.level; //change the high score to the current score if the current score is higher than the high score
		
					run.saveBZSave(game.player_name,game.level,game.ball_stash,game.balls,game.boxes,game.high_score); //run the save-game method
				}catch(IOException e) {
					System.out.println("IOException in the save process");
				}
						
				
				//-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
			}else {
				JOptionPane.showMessageDialog(window, "Your score was: " + game.level,"GAME OVER", 0);
				//force clear the save file 
				System.out.println("Force clearing the save file...");
				try {
					Ball[] balls1 = {};
					ArrayList<Ball> balls = new ArrayList<Ball>(Arrays.asList(balls1));
					
					Box[] boxes1 = {};
					ArrayList<Box> boxes = new ArrayList<Box>(Arrays.asList(boxes1));
					
					run.saveBZSave(game.player_name,0,0,balls,boxes,game.high_score);
					
				}catch(IOException e) {
					System.out.println("IOException in the save process");
				}
			}
		
		}
	}
}
