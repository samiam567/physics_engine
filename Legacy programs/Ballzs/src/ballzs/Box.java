package ballzs;

import java.awt.Color;
import java.io.Serializable;

public class Box implements Serializable{
	public int x,y,size,health,id,centerX,centerY;
	public Color color;
	public Color[] Colors = { Color.black, Color.RED, Color.DARK_GRAY, Color.BLUE, Color.YELLOW };
	public boolean isVisible = true;
	
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
