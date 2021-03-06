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
	
	public static String version = "1.0.7";
	
	public static Physics_frame frame;
	
	private static String shape = "circle";
	private static int levels = 4,size = 200,startX=0,startY=0,endX=Settings.width,endY=Settings.height;
	private static double[] lSizes;
	
	private static border_bounce boundries;
	
	public static object_draw drawer;
	
	public static void main(String[] args) {
		
		frame = new Physics_frame();
		
		drawer = new object_draw(frame);
		
		frame.setColor(Color.black);
		
		boundries = new border_bounce(drawer);
		boundries.setName("boundries", 1);
		drawer.add(boundries);


		FPS_display fps = new FPS_display(drawer,30,30);
		drawer.add(fps);

		FCPS_display fcps = new FCPS_display(drawer,30,50);
		drawer.add(fcps);
		
		drawer.start();
		
		
		drawTessellation(shape,size,levels,startX,startY,endX,endY);
		
		for (massive pO : drawer.getTangibles()) {
			
		}
		
		
		
	
	  
		resize();
		
		
		resize();
		
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
		drawTessBaseLevel();
	}
	
	private static void calculateLSizes() {
		lSizes = new double[(int) (Math.pow(levels,4) + 1)];
		
		lSizes[0] = size;
		for (double i = 1; i <= (double) (Math.pow(levels,4)); i++) {
			lSizes[(int) i] = lSizes[(int) (i-1)] * (i/6);
		}
		
	}
	
	
	private static void drawTessBaseLevel() {
		Physics_3DPolygon cObject;
	
		for (int y = startY; (y-1) <= ((endY - size)/size); y++) {
			double yXAdd =  0.5 * size * (1+Math.sin(Math.PI * 0.5 * y));
			for (int x = startX; (x-1) <= ((endX - size)/size); x++) {
				cObject = new PolarObject(drawer,x * size + yXAdd ,y * Math.pow(3, 0.5) * size/2 + size/2 ,0,size/2,shape);
				cObject.setColor(Color.green);
				cObject.isFilled = true;
				drawer.add(cObject);
				
				for (int i = 0; i < 2; i++) {
					drawTessLevel(1,1,0,cObject,Math.PI/2 - i * 2*Math.PI/6);
				}
				
			}
		}
	}
	
	private static void drawTessLevel(int level,int sizeIndx,int sideLevel,Physics_3DPolygon parent, double angle) {
		if (level < levels) {
			double lSize = lSizes[level];
		
			double yInc = 0;

			yInc = lSizes[level - 1]/2 + lSize/2;
			

			
			Vector2D lvlVec = new Vector2D(drawer,yInc,angle,"polar");
		
			Physics_3DPolygon cObject = new PolarObject(drawer,parent.getCenterX() + lvlVec.getXComponent(),parent.getCenterY() + lvlVec.getYComponent() ,parent.getCenterZ(),lSizes[sizeIndx]/2,shape);
			cObject.setColor(Physics_engine_toolbox.colors[level]);
			cObject.isFilled = true;
			
			drawer.add(cObject);
			
		    System.out.println("level: " + level + " , side: "+ sideLevel);
			
			if (level == 2) {
				sideLevel = 1;
			}
			if (sideLevel <= 1) {
				drawTessLevel(level + 1,sizeIndx + 1,sideLevel,cObject,angle);
				for (int i = 1; i < 3; i++) {
					drawTessLevel(level + 1,sizeIndx + 1 + sideLevel,sideLevel + 1,cObject,angle + i * 2*Math.PI/3);
				}
			}else {
				for (int i = 1; i < 3; i++) {
					drawTessLevel(level + 2,sizeIndx * sideLevel,sideLevel + 1,cObject,angle + i * 2*Math.PI/3);
				}
			}
			
			
			
			
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
