package victorian_integral;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ConcurrentModificationException;

import javax.swing.JOptionPane;

import Physics_engine.*;

///
// LINE COUNT:
// 852
///
public class Victorian_adventure {

	private static Physics_frame frame = new Physics_frame();
	public static boolean newsPaperOpen = false;
	public static ScoreBoard Score;
	
	
	public static void main(String[] args) throws ConcurrentModificationException {

		Thread main = Thread.currentThread();
		object_draw drawer = new object_draw(frame);
		
	
		
		//mouseListener +==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+
		MouseAdapter mouse =  new MouseAdapter() {

		public void mouseClicked(MouseEvent arg0) {
			drawer.inactivity_timer = 0;
			
		}
		public void mouseEntered(MouseEvent arg0) {
			drawer.inactivity_timer = 0;
				
		}
			
		public void mouseExited(MouseEvent arg0) {
			drawer.inactivity_timer = 0;
		}

		
		public void mousePressed(MouseEvent arg0) {
			drawer.inactivity_timer = 0;
				
		
		}

		public void mouseReleased(MouseEvent arg0) {
			drawer.inactivity_timer = 0;
			
		}
		};

		//==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+
		
		
		drawer.addMouseListener(mouse);	
		

		
		
		border_bounce boundries = new border_bounce();
		boundries.setName("boundries",1);
		boundries.isVisible = false;
		
		Square leftSquare = new Square(30,Settings.height/2,0, 60,1);
		leftSquare.setAngularVelocity(0,0,-0.02);
		leftSquare.setSpeed(1, 0, 0);
		leftSquare.setName("leftSquare",1);
		
		Square rightSquare = new Square(Settings.width-50,Settings.height/2,0,60,1);
		rightSquare.setAngularVelocity(0,0,0.02);
		rightSquare.setSpeed(-1, 0, 0);
		rightSquare.setName("rightSquare", 1);
	
		Player player = new Player("You", Settings.width/2 - 20,Settings.height / 2,0, Settings.height/15,10);
		drawer.objects.add(player);
		player.isVisible = false;
		player.showName = true;
		player.setColor(Color.BLUE);
		
		//adding objects to the object list
	
		drawer.objects.add(boundries);
		drawer.objects.add(leftSquare);
		drawer.objects.add(rightSquare);
		
		
	
		drawer.start();
		
		frame.setColor(Color.CYAN);
		
		//start screen
		Text title = new Text(4*Settings.width/11,Settings.height/10,"Victorian Adventure",new Font("TimesRoman", Font.BOLD,(int) Math.round(.05 * Settings.height) ));
		object_draw.objects.add(title);
		
		Text by = new Text(4*Settings.width/10,Settings.height/6,"By: Alec Pannunzio and David Lian",new Font("BrushScriptMT", Font.PLAIN,(int) Math.round(.02 * Settings.height) ));
		object_draw.objects.add(by);
		
		int timer = 0;
		int start;
		do {		
			drawer.Wait(70);
			start = JOptionPane.showConfirmDialog(frame, "Start the game?");
			if (start == 2) {
				frame.dispose();
				System.exit(1);
			}		
		}while (start != 0);
		drawer.objects.remove(leftSquare);
		drawer.objects.remove(rightSquare);
		
		
		//clear the frame
		for (physics_object cObject : object_draw.objects ) {
			cObject.isVisible = false;
			cObject.isTangible = false;
		}
		
		Score = new ScoreBoard(Settings.width - 100, 50, "Score", 0);
		drawer.objects.add(Score);	
		Score.isAnchored = true;
		Score.setScore(0);
		Score.setTargetScore(1000);
		Score.setScoreSpeed(30);
		
		JOptionPane.showMessageDialog(frame, "The time is 8:00 am");
		
		
		String[] Classes = {"Working Class", "Middle Class", "High Class"};

		VictorianHouse House = new VictorianHouse(Settings.width/2, Settings.height/4, 0, Settings.width/5);
		House.setName("Your House", 1);
		
		Human Ernest = new Human("Ernest", Settings.width /2 - 100,Settings.height /2, 0, Settings.height/15, 1);
		Ernest.showName = true;
		drawer.objects.add(Ernest);
		Ernest.setColor(Color.BLUE);


		
		player.isVisible = true;
		
	
		
		
		
		JOptionPane.showMessageDialog(frame, "Hey, did you hear that now all men with property worth 10 pounds or more can vote? You should go over to the voting booth to register!", "Ernest:",1);
		JOptionPane.showMessageDialog(frame, "You did not know this.");
		String visit_or_not = "";
		visit_or_not = (String) JOptionPane.showInputDialog(frame, "Do you want to go vote right now?", "Yes or No");
		
		visit_or_not = visit_or_not.toLowerCase();
		
		if (visit_or_not.startsWith("y"))
		{
			JOptionPane.showMessageDialog(frame, "You decided to go to the Voting Booth.");
		}
		else if (visit_or_not.startsWith("n"))
		{
			JOptionPane.showMessageDialog(frame,  "Well too bad!!! Your legs take you to the Voting Booth without your consent!");
		}
		else
		{
			if (visit_or_not.contains("y")) {
				JOptionPane.showMessageDialog(frame, "You decided to go to the Voting Booth.");
			} else if (visit_or_not.contains("n")) {
				JOptionPane.showMessageDialog(frame,  "Well too bad!!! Your legs take you to the Voting Booth without your consent!");
			} else {
				JOptionPane.showMessageDialog(frame,  "I didn't understand that response... but you're going to the voting booth whether you like it or not \\_o_/");
			}
		}
		
		player.setSpeed(4, 4, -4); //player walks off
		
		//wait until player leaves the screen
		while (player.getCenterY() < Settings.height) { 
			try {
				main.sleep(1);
			} catch (InterruptedException e) {
			e.printStackTrace();
			}
		}
		
		
		
		//clear out the previous scene
		House.isVisible = false;
		Ernest.isVisible = false;
		
		//load the voting-booth scene
		VotingRightsPlace voteBooth = new VotingRightsPlace(player, drawer);
		voteBooth.launch();
		voteBooth.run(); //this method should occupy this frame until voting is finished
		
		//setup the newspaper scene
		JOptionPane.showMessageDialog(frame, "You see a newspaper on the desk...  \n you decide to take a look", "Oh Look!",1);
		
		//load the newspaper scene
		Victorian_newspaper news = new Victorian_newspaper(drawer);
		newsPaperOpen = true;
		news.init();
	
		while (newsPaperOpen) {
			try {
				main.sleep(1);
			} catch (InterruptedException e) {
			e.printStackTrace();
			}
		}; //wait until the newspaper is closed

	
	
	//load factory mini-game
		
		//reset objects list
		for (physics_object cObject : object_draw.objects ) {
			cObject.isVisible = false;
			cObject.isTangible = false;
		}
		Score.isVisible = true; //...except for the scoreboard
		
		
		Score.setScoreSpeed(250); // make the score speed faster for all that button mashing
		
		Factory_Minigame facMini = new Factory_Minigame(drawer);
		facMini.setName("Hammer", 1);

		//notify the user that they are starting a mini game
		JOptionPane.showMessageDialog(frame, "Industrialization and factories were a large part of the Victorian era, so you decide to see what it's like to operate the new machinery. \n type any key on the keyboard to turn run the machine and earn points!", "MINI GAME!",1);
	
		double startScore = Score.getTargetScore();
	
		long miniStart = System.nanoTime();
		
		while ((System.nanoTime() - miniStart)/5 < 999999999) {
			try {
				main.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} // add a timer for the minigame
		
		facMini.end(); //end the mini game
		
		Score.setScoreSpeed(30); // return the score speed to normal
		
		//display how many points the player earned
		double pointsEarned = Score.getTargetScore() - startScore;
		JOptionPane.showMessageDialog(frame, "You earned " + pointsEarned + " points!", "Game Over",1);
		
		
		
		
		//clear the frame
		for (physics_object cObject : object_draw.objects ) {
			cObject.isVisible = false;
			cObject.isTangible = false;
		}
		
		//re-display title & by
		title.isVisible = true;
		by.isVisible = true;
		
		
		//game over 
		Text GOver = new Text(4*Settings.width/11,Settings.height/10,"GAME OVER",new Font("TimesRoman", Font.BOLD,(int) Math.round(.05 * Settings.height) ));
		object_draw.objects.add(GOver);
		
		title.setPos(title.getXReal(), title.getYReal() + Settings.height/7, 0);
		
		by.setPos(by.getXReal(), by.getYReal() + Settings.height/7, 0);
		
		
		//close rescources
//		drawer.end();	
//		frame.dispose();
		
	
	}

}

