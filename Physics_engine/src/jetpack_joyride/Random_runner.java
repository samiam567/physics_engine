package jetpack_joyride;

import java.applet.Applet;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.util.ArrayList;

public class Random_runner extends Applet{
	
	static Polygon polyXY = new Polygon();
	
	public static void go(String[] args) {
		
		polyXY.xpoints = new int[] {0,50,50,0};
		polyXY.ypoints = new int[] {0,0,50,50};
		polyXY.npoints = 4;
		
		System.out.println(polyXY.contains(1, 1));
		
	}
	
	public void paint(Graphics page) {
		page.drawPolygon(polyXY);
		System.out.println("gasdf");
		
	}
}
