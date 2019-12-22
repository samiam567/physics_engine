package ballzsV2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JComponent;

public class gameFrame extends JComponent {
	public Game game;
	
	public gameFrame(Game current_game) {
		game = current_game;
	}
	public void paint(Graphics page) {
		page.setColor(Settings.backgroundColor);
		page.fillRect(0, 0, Settings.width, Settings.height);
		
	    (game.mainframe).add(new ScoreBoard(game));
		
		for (Ball current_ball : game.balls ) {
			if (current_ball.isVisible) {
				current_ball.move();
				game.ball_amount = game.collision_handler.checkForCollision(current_ball,game.balls,game.boxes,game.ball_amount); //will check for collisions and delete balls if they hit a box
			}
	
		}
		
		for (Box current_box : game.boxes) {
			if (current_box.isVisible) {
				(game.mainframe).add(current_box);
			}
		}
	
		game.Wait(1);
		


	}
}
