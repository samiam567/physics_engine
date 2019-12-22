package chutes_and_ladders;

import java.awt.Color;
import java.io.Serializable;

public class Player implements Serializable {
	public int wins;
	public String name;
	public int id;
	public int space;
	public Color color;
	public Color[] Colors = { Color.black, Color.RED, Color.DARK_GRAY, Color.BLUE, Color.YELLOW };

	public Player(String player_name, int player_id) {
		name = player_name;
		id = player_id;
		wins = 0;

		// use a while loop to cycle through a list of colors and give each player a
				// different color
				int color_inc = 0;
				int color_key = 0;
				while (color_inc != id) {
					if (color_key == Colors.length)
						color_key = 0;
					color = Colors[color_key];
					color_inc++;
					color_key++;
				}

	}

	public void addWin() {
		wins++;
	}

	
	public void setSpace(int new_space) {
		space = new_space;

		System.out.println(name + " has just moved to space " + space);
	}
}
