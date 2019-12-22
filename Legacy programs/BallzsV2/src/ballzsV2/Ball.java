package ballzsV2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JComponent;

public class Ball extends JComponent implements Serializable {
	public int x,y;
	public double x_speed,y_speed,centerX,centerY,xReal,yReal; 
	public int id;
	public int size;
	public boolean isVisible = true;
	public Color color;
	
	public Ball(int x2, int y2, double x_speed2, double y_speed2, int id2, int size2, Color color2) {
		x = x2;
		y = y2;
		x_speed = x_speed2;
		y_speed = y_speed2;
		id = id2;
		size = size2;
		centerX = x + size/2;
		centerY = y + size/2;
		color = color2;
	}
	
	public void paint(Graphics page) {
		System.out.println("Painting ball");
		page.setColor(color);
		page.fillOval(x, y, size, size);
		
	}
	
	public void delete(ArrayList<Ball> Balls) {
		//delete the ball
		isVisible = false;
		
		

	}
	
	public void move() { //updates the ball variables
		xReal = x + x_speed;
		yReal = y + y_speed;
		x = (int) Math.round(xReal);
		y = (int) Math.round(yReal);
		centerX = xReal + size/2;
		centerY = yReal + size/2;
	
		
	}
	
	
}
