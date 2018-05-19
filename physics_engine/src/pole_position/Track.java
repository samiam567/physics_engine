package pole_position;

import Physics_engine.Physics_3DPolygon;
import Physics_engine.PointSet;
import Physics_engine.object_draw;
import Physics_engine.point;
import calculator.Settings;

public class Track extends PointSet {
	
	private double T, accuracy = 80, theta = Math.PI/6;

	public Track(object_draw drawer1,double x) {
		super(drawer1);
		T = 0;
		setPos(x,0,0);
		drawMethod  = "pai";
		setSize(Settings.width/2,Settings.height * 2,1);
		isRotatable = false;
		setHasNormalCollisions(false);
	}
	
	public void tertiaryUpdate() {
		

		
	//	System.out.println(centerX);
		if (centerY >= Settings.height) {
		
			System.out.println("generating track");
			generateTrack();
		}
	}
	
	public void generateTrack() {
		
	
		if (points.length > 0) {
			setPos((points[0].getXReal()-Settings.width/4 * Math.sin((T)/Settings.height / 10)),0,0);
		}
		
		pointsAL.clear();
		System.out.println(centerX + "," + centerY + "," + centerZ);
		
		for (double t = -Settings.height;t < Settings.height + accuracy; t += accuracy) {	
			T+= Math.abs(t);
			addPoint(new point(drawer,centerX + Settings.width/4 * Math.sin(T/Settings.height / 10),t,centerZ));
			
		}
		
		initialize();
			
		setPos(centerX,0,0);
		finalize();
		
		setPos(centerX,0,0);
		
	}
	
	public void setT(double t) {
		T = t;
	}
	
	public double getT() {
		return T;
	}
	
}
