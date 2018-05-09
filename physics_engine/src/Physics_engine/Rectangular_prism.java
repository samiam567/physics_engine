package Physics_engine;


import java.awt.Graphics;
import java.util.ArrayList;

import Physics_engine.Physics_engine_toolbox.faces;



public class Rectangular_prism extends Physics_3DPolygon {
	
	public Rectangular_prism(object_draw drawer1,double x, double y, double z, double e, double f,double zSize, double mass) {
		super(drawer1);
		setPos(x,y,z);
		setSize(e,f,zSize);
		
		
		updatePos();
		
		setRotation(0,0,0);
		
		drawMethod = "ListedPointAlgorithm";
				
		pointRenderOrder = new int[] {0,1,2,3,0,4,5,6,7,3,2,6,5,4,7,4,5,1}; //this could probably be improved (more efficient)
		
		point[] points = new point[8];
		
		points[0] = new point(drawer,centerX-xSize/2,centerY-getYSize()/2,zReal); 
		
		points[1] = new point(drawer,centerX+xSize/2,centerY-getYSize()/2,zReal);
		
		points[2] = new point(drawer,centerX+xSize/2,centerY+getYSize()/2,zReal);
		
		points[3] = new point(drawer,centerX-xSize/2,centerY+getYSize()/2,zReal );
		
		points[4] = new point(drawer,centerX-xSize/2,centerY-getYSize()/2,centerZ + zSize/2); 
		
		points[5] = new point(drawer,centerX+xSize/2,centerY-getYSize()/2,centerZ + zSize/2);
		
		points[6] = new point(drawer,centerX+xSize/2,centerY+getYSize()/2,centerZ + zSize/2);
		
		points[7] = new point(drawer,centerX-xSize/2,centerY+getYSize()/2,centerZ + zSize/2 );
	
		setPoints(points);
		
		double r = Math.sqrt(Math.pow(xSize,2) + Math.pow(getYSize(),2) + Math.pow(zSize,2))/2;
		for (point cP : points) {
			cP.setR(r);
		}
		
		calculateCenter();
		
		calculatePointValues();
		
		updatePointOfRotation();
		
		setPos(x,y,z);
	
		setMass(mass);
	}
}
	

