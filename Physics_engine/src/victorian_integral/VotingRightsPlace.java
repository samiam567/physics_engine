package victorian_integral;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Physics_engine.Settings;
import Physics_engine.Text;
import Physics_engine.object_draw;
import Physics_engine.physics_object;
import Physics_engine.rectangle;
import Physics_engine.physics_object.faces;

public class VotingRightsPlace extends physics_object {
	private Player player;
	private object_draw drawer;
	private votingBooth votingBooth;
	private Text votingBoothText;
	private Font votingBoothTextFont = new Font("TimesRoman", Font.PLAIN,(int) Math.round(.045 * Settings.height) ); 
	private boolean voted = false;
	private KeyListener listener;
	
	class votingBooth extends rectangle {
		public votingBooth() {
			super(Settings.width/3  + Settings.width/6,Settings.height/10,0,Settings.width/3,Settings.height/5,1);
			isTangible = false; //this class has built-in collision handling and doesn't need the default
					}
		
		public void isCollided(physics_object object, faces side) { 
				vote();
		}
		
		public Object checkForCollision1(physics_object current_object,ArrayList<physics_object> objects) {
			//is there a collision?
			if ( (Math.abs(getCenterX() - current_object.getCenterX()) < (current_object.getXSize()/2+xSize/2)) && (Math.abs(getCenterY() - current_object.getCenterY()) < (current_object.getYSize()/2+ySize/2)) /* && (Math.abs(centerZ - current_object.centerZ) < (current_object.zSize/2+zSize/2))*/ ) {	
				isCollided(this,faces.none);
			}
			
			return null;
		}
		
	}
	
	public VotingRightsPlace(Player player1,object_draw drawer1) {
		player = player1;
		drawer = drawer1;
		isTangible = false; //this class has built-in collision handling and doesnt need the default
	}
	
	public void launch() {
		//resetting the player
		player.setSpeed(0, 0, 0);
		player.setPos(Settings.height / 2, Settings.width * 4/10, 0);
		
		//creating paint objects
		votingBooth = new votingBooth();
	
		votingBooth.isFilled = true;
		
		votingBoothText = new Text(4*Settings.width/11,Settings.height/10,"Register to Vote here!",votingBoothTextFont);
		
		
		//key listener
		listener = new KeyListener() {
		       @Override
	            public void keyPressed(KeyEvent e) {
	            	int key = e.getKeyCode();
		            	
	            	drawer.inactivity_timer = 0;
	            	
	            	switch(key) {
	            	
	            	case(87): //W
	            		player.setSpeed(0, -7, 0);
	            		break;
	            		
	            	case(38): //up arrow
	            		player.setSpeed(0, -7, 0);
            			break;
            			
            			
	            	case(65): //A
	            		player.setSpeed(-7, 0, 0);
		            	break;
		            		
	            	case(37): //Left arrow
	            		player.setSpeed(-7, 0, 0);
		            	break;
	            		
	            	case(83): //S
	            		player.setSpeed(0, 7, 0);
		            	break;
		            	
	            	case(40): //down arrow
	            		player.setSpeed(0, 7, 0);
		            	break;	
		            		
	            	case(68): //D
	            		player.setSpeed(7, 0, 0);
	            		break;
	            		
	            	case(39): //Right arrow
	            		player.setSpeed(7, 0, 0);
	            		break;
         		
	            	
	            	}
	            }
		             
			@Override
			public void keyReleased(KeyEvent arg0) {
				player.setSpeed(0, 0, 0);
					
			}
			
			@Override
			public void keyTyped(KeyEvent arg0) {
						
				}
			};
	
	
		
		drawer.addKeyListener(listener);
		
		
		object_draw.objects.add(this);
	}
	
	public void run() {
		
		while (! voted) {
			//check if the player has entered the booth			
			votingBooth.checkForCollision1(player, object_draw.objects);
			System.out.println("c");
		}
	}
	
	public void vote() { // put voting prompts here
		player.setSpeed(0,0,0);
		drawer.removeKeyListener(listener);
		player.setSpeed(0,0,0);
		System.out.println("VOTEEEE");
		String[] Genders = {"Boy", "Girl","Apache Attack Helipoter"};
		player.Gender = (String) JOptionPane.showInputDialog(drawer.frame, "What is your gender", "Gender", 2, null, Genders, null);
		String[] Ages = {"18-30", "31-50", "50+"};
		player.Age = (String) JOptionPane.showInputDialog(drawer.frame, "What is your age?", "Age", 3, null, Ages, null);
		String[] Marriage_Choices = {"Single", "Married"};
		player.Marriage_Status = (String) JOptionPane.showInputDialog(drawer.frame, "Are you married or unmarried?", "Marriage_Status", 2, null, Marriage_Choices, null);
		voted = true;
		
		}
	
	
	public void paint(Graphics page) {
		page.setColor(Color.ORANGE);
		votingBooth.paint(page);
		page.setColor(Color.WHITE);
		votingBoothText.paint(page);
	}
}

