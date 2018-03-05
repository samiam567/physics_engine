package calculator;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class Axis extends graph {
	public void paint(Graphics page) {
		page.setColor(Color.BLACK);
		page.drawLine(Settings.width/2, 0, Settings.width/2, Settings.height);
		page.drawLine(0, Settings.height/2, Settings.width, Settings.height/2);
	}
}
