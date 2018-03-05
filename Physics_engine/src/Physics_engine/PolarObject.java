package Physics_engine;

public class PolarObject extends Physics_polygon {
	
	public PolarObject(object_draw drawer1, double x, double y, double z, double size) {
		super(drawer1);
		drawMethod = "ListedPointAlgorithm";
		setPos(x,y,z);
		setSize(size,size,size);
		
		points = new point[(int) (2*Math.PI/Settings.thetaStep )];
		double r = 0,theta;
	
		for (int i = 0; i < points.length; i ++) {
			theta = i * Settings.thetaStep;
			
			r = size * Math.sin(theta);
			points[i] = new point(drawer,centerX,centerY,centerZ);
			points[i].setPointVector(new Vector(drawer,r,theta,0,0,"thetaZX"));
			points[i].setPos(centerX, centerY, centerZ);
		}
		
		setPoints(points);
		
		updatePoints();
		
	}
}
