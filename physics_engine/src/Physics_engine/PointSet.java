package Physics_engine;

import java.util.ArrayList;

public class PointSet extends Physics_3DPolygon {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3055858664592563443L;
	protected ArrayList<point> pointsAL;
	
	public PointSet(object_draw drawer1) {
		super(drawer1);
		drawMethod = "paint";
		setSize(100,100,100);
		setPos(200,200,200);
		
		setRotation(0,0,0);
		
		points = new point[0];
		
		pointsAL = new ArrayList<point>();
		
	}
	
	public void addPoint(point nP) {
		setRotation(0,0,0);
		
		pointsAL.add(nP);
	}
	
	public void initialize() {
		
		setRotation(0,0,0);
		
		points = new point[pointsAL.size()];
		
		for (int i = 0; i < points.length; i++) {
			points[i] = pointsAL.get(i);
		}
		
		setPoints(points);
		
		
		
	
	}
	
	public void finish() {	
		
		setRotation(0,0,0);
		
		
		
		setMass(10);
		
		calculateCenter();
		
		updatePointOfRotation();
		calculateSize(); //this breaks it
		calculatePointValues();	
		
		updatePointOfRotation();
		
	
	
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
				}
				
				if (cP.getY() < smallestY) {
					smallestY = cP.getY();
				}else if (cP.getY() > largestY) {
					largestY = cP.getY();
				}
				
				if (cP.getZ() < smallestZ) {
					smallestZ = cP.getZ();
				}else if (cP.getZ() > largestZ) {
					largestZ = cP.getZ();
				}
			}
				setSize(largestX-smallestX,largestY-smallestY,1 +largestZ-smallestZ);
				
				System.out.println("Size: " + xSize + "," + ySize + "," + zSize);
	}
	
}
