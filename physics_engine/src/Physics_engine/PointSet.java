package Physics_engine;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class PointSet extends Physics_3DPolygon {
	private ArrayList<point> pointsAL;
	
	public PointSet(object_draw drawer1) {
		super(drawer1);
		drawMethod = "paint";
		setSize(1,1,1);
		setPos(200,200,200);
		setMass(1);
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
		
		calculateCenter();
		
		updatePointOfRotation();
		
		calculatePointValues();	
	
	}
	
}
