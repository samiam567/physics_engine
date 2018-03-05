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
		object_draw drawer = new object_draw(frame);
		
		drawer.setFrameTime(1000000000); 
		drawer.frameStep = 100;
		
		drawer.start();
		
		for (int i = 0; i < monsters.length; i++) {
			monsters[i] = new Monster(drawer,i,-40*i,(int) Math.pow(i, 2));
		}
		System.out.println("Monsters created");
		
		for (Monster cMon : monsters) {
			drawer.add(cMon);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void paint(Graphics page) {
		page.drawPolygon(polyXY);
		System.out.println("gasdf");
		
	}
}
