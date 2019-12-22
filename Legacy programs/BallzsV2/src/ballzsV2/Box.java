package ballzsV2;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.JComponent;

public class Box extends JComponent implements Serializable{
	public int x,y,size,health,id,centerX,centerY;
	public Color color;
	public Color[] Colors = { Color.black, Color.RED, Color.DARK_GRAY, Color.BLUE, Color.YELLOW };
	public boolean isVisible = true;
	public Font bigFont = new Font("TimesRoman", Font.BOLD,(int) Math.round(.6 * Settings.boxSize) );
	
	public Box(int x2, int y2, int size2, int health2, int id2) {
		x = x2;
		y = y2;
		size = size2;
		health = health2;
		id = id2;
		centerX = x + size/2;
		centerY = y + size/2;
		
		// use a while loop to cycle through a list of colors and give each box a different color
		int color_inc = 0;
		int color_key = 0;
		while (color_inc != id) {
			if (color_key == Colors.length) color_key = 0;
			color = Colors[color_key];
			color_inc++;
			color_key++;
		}
		
	}
	
	public void paint(Graphics page) {
		// draw the box
		page.setColor(Color.CYAN);
		page.fillRect(x, y, size, size);
		page.setColor(Color.MAGENTA);
		page.drawRect(x, y, size, size); // outline in red

		// put numbers inside the box
		page.setFont(bigFont);
		String id_str = String.valueOf(health);
		
		page.drawString(id_str,(int) Math.round(x + 0.2 * size), (int) Math.round(y + 0.7 * size));
	
		
	}
	public void delete() { //unfinished
		//delete the box
	}
	
	public void hit() {
		health--;
		//System.out.println("Box" + id + " has " + health + " hp left");
		if (health <= 0) {
			isVisible = false;
			delete();
		}
		
		
	}
	
}
