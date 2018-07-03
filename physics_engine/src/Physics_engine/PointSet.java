package Physics_engine;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class PointSet extends Physics_3DPolygon {
	protected ArrayList<point> pointsAL;
	
	public PointSet(object_draw drawer1) {
		super(drawer1);
		drawMethod = "paint";
		setSize(1,1,1);
		setPos(200,200,200);
		
		setRotation(0,0,0);
		
		points = new point[0];
		
		pointsAL = new ArrayList<point>();
		
	}
	
	public void addPoint(point nP) {
		pointsAL.add(nP);
	}
	
	public void initialize() {
		points = new point[pointsAL.size()];
		
		for (int i = 0; i < points.length; i++) {
			points[i] = pointsAL.get(i);
		}
		
		setPoints(points);
		
		
	
	}
	
	public void finish() {	
				
		
		//calculateSize(); this breaks it
		setMass(10);
		
		calculateCenter();
		
		updatePointOfRotation();
		
		calculatePointValues();	
		
	
		System.out.println("finalized");

	}
	
	private void calculateSize() {
		//calculating the size of the object
			double smallestY=points[0].getYReal(),largestY=points[0].getYReal(),smallestX=points[0].getXReal(),largestX=points[0].getXReal(),smallestZ=points[0].getZReal(),largestZ=points[0].getZReal();
			for (point cP : points) {
				if (cP.getX() < smallestX) {
					smallestX = cP.getX();
				}else if (cP.getX() > largestX) {
					largestX = cP.getX();
				}else if (cP.getY() < smallestY) {
					smallestY = cP.getY();
				}else if (cP.getY() > largestY) {
					largestY = cP.getY();
				}else if (cP.getZ() < smallestZ) {
					smallestZ = cP.getZ();
				}else if (cP.getZ() > largestZ) {
					largestZ = cP.getZ();
				}
			}
				setSize(largestX-smallestX,largestY-smallestY,largestZ-smallestZ);
	}
	
}
