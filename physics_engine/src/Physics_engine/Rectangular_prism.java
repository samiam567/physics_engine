package Physics_engine;


import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;

import Physics_engine.Physics_3DPolygon.Polygon_point;
import Physics_engine.Physics_engine_toolbox.faces;



public class Rectangular_prism extends Physics_3DPolygon {
	
	private Face face1, face2, face3, face4, face5,face6;

	private boolean polysCreated = false;
	public Rectangular_prism(object_draw drawer1,double x, double y, double z, double xSize, double ySize,double zSize, double mass) {
		super(drawer1);
		setPos(x,y,z);
		setSize(xSize,ySize,zSize);
		
		
		updatePos();
		
		setRotation(0,0,0);
		
		drawMethod = "paint";
				
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
		
		face1 = new Face(this, new point[] {points[0],points[1],points[2],points[3]});
		face2 = new Face(this, new point[] {points[1],points[5],points[2],points[6]});
		face3 = new Face(this, new point[] {points[4],points[5],points[6],points[7]});
		face4 = new Face(this, new point[] {points[0],points[4],points[7],points[3]});
		face5 = new Face(this, new point[] {points[2],points[3],points[7],points[6]});
		face6 = new Face(this, new point[] {points[0],points[1],points[5],points[4]});
		polysCreated = true;
		
	}
	
	@Override
	public void updateShading(Polygon_point cPoint) {}
	
	@Override
	public void updatePolygons() {
		super.updatePolygons();
		
		if (polysCreated) {
			face1.Update();
			face2.Update();
			face3.Update();
			face4.Update();
			face5.Update();
			face6.Update();
		}
		
			
		
		
	
		
		
	
		
	}
	@Override
	public void paint(Graphics page) {
		face1.paint(page);
		face2.paint(page);
		face3.paint(page);
		face4.paint(page);
		face5.paint(page);
		face6.paint(page);
	}
}
	

