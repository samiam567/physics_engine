package circle_tessellation;


import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ConcurrentModificationException;
import javax.swing.JFrame;

import Physics_engine.Physics_engine_toolbox.pointOfRotationPlaces;
import Physics_engine.*;

public class Tessellation_runner {
	
	private static Physics_frame frame = new Physics_frame();
	private static boolean mouseIsPressed = false;
	private static int mouseStartX;
	private static int mouseStartY;
	
	private static String shape = "sphere";
	private static int levels = 5,size = 300,startX,startY,endX,endY;
	private static double[] lSizes;
	
	private static border_bounce boundries;

	public static Vector vec1;
	
	public static Vector3D Vec;
	
	static object_draw drawer;
	
	public static void main(String[] args) {
		
		
	
		drawer = new object_draw(frame);
		
		boundries = new border_bounce(drawer);
		boundries.setName("boundries", 1);
		drawer.add(boundries);


		FPS_display fps = new FPS_display(drawer,30,30);
		drawer.add(fps);

		FCPS_display fcps = new FCPS_display(drawer,30,50);
		drawer.add(fcps);
		
		
		
		drawTessellation(shape,size,levels,0,0,200,200);

		
	
		
		
		for (massive pO : drawer.getTangibles()) {
			((pointed) pO).setAngularVelocity(0.1,0.1,0.1);
		}
		
		
		drawer.start();
	  
		resize();
		
		//drawer.pause();
	  
		
		while (frame.isShowing()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.exit(1);
		
	}
	
	public static void drawTessellation(String shape1,int size1, int levels1, int startX1, int startY1, int endX1, int endY1) {
		shape = shape1;
		size = size1;
		levels = levels1;
		startX = startX1;
		startY = startY1;
		endX = endX1;
		endY = endY1;
		calculateLSizes();
		drawTessLevel(1);
	}
	
	private static void calculateLSizes() {
		lSizes = new double[(int) (Math.pow(levels,2) + 1)];
		
		lSizes[0] = 0;
		
		for (int i = 1; i <= (int) (Math.pow(levels,2)); i++) {
			lSizes[i] = size * Math.pow(0.333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333/2,i-1);
		}
		
	}
	
	private static void drawTessLevel(int level) {
		if (level <= levels) {
			double lSize = lSizes[level];
			
			System.out.println("size: " + lSize);
		
			double yInc = 0;
			
			for (int l = 0; l < level; l++) {
				for (int i = l+level-1; i > l; i--) {
					yInc += lSizes[i]/2;
				}
			}
			
	
			
			
			for (int y = 0; y <= ((Settings.height - size)/size); y++) {
				double yXAdd =  0.5 * size * (1+Math.sin(Math.PI * 0.5 * y));
				for (int x = 0; x <= ((Settings.width - size)/size); x++) {
					drawer.add(new PolarObject(drawer,x * size + yXAdd ,y * 5*size/6 + size/2 + yInc,0,lSize/2,shape));
				}
			}
		
			drawTessLevel(level + 1);
			
		}else {
			return;
		}
	}

	public static void resize() {
		//resize stuff
		System.out.println("Resizing");
	
		try {
			boundries.resize();
		}catch(NullPointerException n) {}
	}

}
