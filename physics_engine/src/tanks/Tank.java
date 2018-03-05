package tanks;

import Physics_engine.Physics_polygon;
import Physics_engine.object_draw;
import Physics_engine.point;

public class Tank extends Physics_polygon {
	public Tank(object_draw drawer1,double x, double y, double size) {
		super(drawer1);
		
		setPos(x,y,0);
		setSize(size,size,0);
		setMass(10);
		setRotation(0,0,0);
		
		
		
		pointRenderOrder = new int[] {0,1,2,3,0};
		point[] points1 = new point[5];
		
		points1[0] = new point(drawer,xReal,yReal,zReal); 
		points1[0].setAngle(3*Math.PI/4, 0,Math.PI/2);
		
		points1[1] = new point(drawer,xReal+xSize/2, yReal-ySize/2,zReal);
		points1[1].setAngle(Math.PI/2, 0,Math.PI/2);
		
		points1[2] = new point(drawer,xReal+xSize,yReal,zReal);
		points1[2].setAngle(Math.PI/4, Math.PI,Math.PI/2);
		
		points1[3] = new point(drawer,xReal+xSize,yReal+ySize,zReal);
		points1[3].setAngle(-Math.PI/4, Math.PI,-Math.PI/2);
		
		points1[4] = new point(drawer,xReal,yReal+ySize,zReal);
		points1[4].setAngle(-3*Math.PI/4, 0,-Math.PI/2);
	
		
		setPoints(points1);
		
		double r = Math.sqrt(Math.pow(xSize,2) + Math.pow(ySize,2) + Math.pow(zSize,2))/2;
		for (point cP : points) {
			cP.setR(r);
		}
		
		calculateCenter();
	
		updatePointOfRotation();
		
		calculatePointValues();  //THIS METHOD IS EVIL SPAWN OF SATAN! |:<


		setPos(x,y,z);
	
	}
}
