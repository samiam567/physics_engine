package jetpack_joyride;

import java.applet.Applet;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.util.ArrayList;

import Physics_engine.Physics_frame;
import Physics_engine.object_draw;

public class Random_runner extends Applet{
	
	public static Polygon polyXY = new Polygon();
	public static Monster[] monsters = new Monster[500];
	
	public static Physics_frame frame = new Physics_frame();
	
	public static void main(String[] args) {
		 
		Object c = new String("Hello");
		String d = new String("Hello");
		System.out.println(c.equals(d));
	}
	
	public void paint(Graphics page) {
		page.drawPolygon(polyXY);
		System.out.println("gasdf");
		
	}
}
