package Physics_engine;

import java.util.ArrayList;

public class Sphere extends Physics_3DPolygon {
	
	public double accuracy;
	
	public Sphere(object_draw drawer1, double x1, double y1, double z1, double size,double mass,double accuracy) {
		super(drawer1);
		drawMethod = "paint";
		setPos(x1,y1,z1);
		setSize(size,size,size);
		setRotation(0,0,0);
		
		points = new point[(int) (2*Math.PI/accuracy)];
		
		
		ArrayList<point> pointsAL = new ArrayList<point>();
		
		double t,q,xPos,yPos,zPos;
	
		for (int i = 0; i < points.length; i ++) {
			t = i * accuracy;		
			
			
			for (int z = 0; z < points.length; z++) {
				q = z * accuracy;
				xPos = xSize * Math.sin(t) * Math.cos(q);
				yPos = ySize * Math.sin(t) * Math.sin(q);
				zPos = zSize * Math.cos(t);
				pointsAL.add( new point(drawer,centerX + xPos,centerY - yPos ,centerZ + zPos) );
			}
			

		}
		
		points = new point[pointsAL.size()];
		
		for (int i = 0; i < points.length; i++) {
			points[i] = pointsAL.get(i);
		}
		
		setPoints(points);
		
		
		calculateCenter();
		
		updatePointOfRotation();
		
		calculatePointValues(); 

		setPos(x1,y1,z1);
		
	}
	

}
