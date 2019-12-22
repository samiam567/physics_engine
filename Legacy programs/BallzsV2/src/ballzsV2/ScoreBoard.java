package ballzsV2;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JComponent;

public class ScoreBoard extends JComponent{
	Game game;
	
	public ScoreBoard(Game current_game) {
		game = current_game;
	}
	public void paint(Graphics page) {
		//score-board thing
			page.setColor(Color.WHITE);
			page.setFont(game.bigFont);
			page.drawString("Score: " + game.level, (int) (0.05 * Settings.width),(int) ( Settings.height-2*Settings.boxSize));
			page.drawString("High Score: " + game.high_score, (int) (0.65 * Settings.width),(int) ( Settings.height-2*Settings.boxSize));
	}
}
