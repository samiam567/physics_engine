package ballzsV2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;

public class StartScreen extends JComponent {
	public Game game;
	
	public StartScreen(Game current_game) {
		game = current_game;
	}
	public void paint(Graphics page) {
		
		//manually drawing background
		page.setColor(Settings.backgroundColor);
		page.fillRect(0, 0, Settings.width, Settings.height);
		
		page.setColor(Color.CYAN);
		page.setFont(new Font("TimesRoman", Font.BOLD,Settings.boxSize));
		page.drawString("Ballzs",(int) Math.round( Settings.width/2 - ( Settings.boxSize * 51/35) ),(int) ( Settings.height * 0.3));
		
	}
}
