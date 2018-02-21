package calculator;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;




public class graph extends Canvas {
	public String name = "unNamed";
	private point[] points1 = {};
	public ArrayList<point> points = new ArrayList<point>(Arrays.asList(points1));
	protected Color color;
	
	public graph() {
		color = Calculator_runner.colors[Calculator_runner.colorCounter];
		
		if (Calculator_runner.colorCounter >= Calculator_runner.colors.length) {
			Calculator_runner.colorCounter = 0;
		}else {
			Calculator_runner.colorCounter++;
		}
		
	}

	public void paint(Graphics page) {
		page.setColor(color);
		point current_point;
		point next_point;
		for (int i = 0 ; i <  points.size()-1 ; i++) {		
			current_point = points.get(i);	
			next_point = points.get(i+1);
		
			page.drawLine(current_point.x, current_point.y, next_point.x, next_point.y);
		}
	}
	
	public void addPoint(point newPoint) {
		points.add(newPoint);
	}
}
