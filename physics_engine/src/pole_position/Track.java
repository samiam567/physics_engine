package pole_position;

import Physics_engine.Physics_3DPolygon;
import Physics_engine.PointSet;
import Physics_engine.object_draw;
import Physics_engine.point;
import calculator.Settings;

public class Track extends PointSet {
	
	private double T, accuracy = 50, theta = Math.PI/6;

	public Track(object_draw drawer1,double startT) {
		super(drawer1);
		T = startT;
		setPos(Settings.width/2,Settings.height * 0.7,0);
		drawMethod  = "ListedPointsAlgorithm";
		setSize(Settings.width/2,Settings.height/2,1);
	}
	
	public void tertiaryUpdate() {
		System.out.println(centerY);
		
		if (centerY >= Settings.height/2) {
			T+= Settings.height/2;
			System.out.println("generating track");
			generateTrack();
		}
	}
	
	public void generateTrack() {
		pointsAL.clear();

		setPos(centerX,-200,centerZ);
		for (double t = Settings.height + 100; t > -100; t -= accuracy) {	
			T+=t;
			addPoint(new point(drawer,centerX + Settings.width/4 * Math.sin(T/Settings.height),centerY + t,centerZ));
			
		}
		setPos(centerX,-200,centerZ);
		
		initialize();
		
		finalize();
		
	}
	
	public void setT(double t) {
		T = t;
	}
	
	public double getT() {
		return T;
	}
	
}
