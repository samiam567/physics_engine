package pole_position;

import Physics_engine.Physics_3DPolygon;
import Physics_engine.PointSet;
import Physics_engine.object_draw;
import Physics_engine.point;
import calculator.Settings;

public class Track extends PointSet {
	
	private double T, accuracy = 50, theta = Math.PI/6;

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
		
	
	
		pointsAL.clear();
		System.out.println(centerX + "," + centerY + "," + centerZ);
		
		
		for (double t = Settings.height + accuracy;t >= -Settings.height; t -= accuracy) {
			T-= accuracy;
			System.out.println("y:" + t + " T:" + T + " x: " + (double) (centerX + getXT(T)));
			addPoint(new point(drawer,centerX + getXT(T),t,centerZ));
			
		}
		
		
		/*
     	for (double t = -Settings.height;t < Settings.height + accuracy; t += accuracy) {
			T+= accuracy;
			System.out.println("y:" + t + " T:" + T + " x: " + (double) (centerX + getXT(T)));
			addPoint(new point(drawer,centerX + getXT(T),t,centerZ));
     	}
     	*/
     	
		
	
	
		
		initialize();
			
		setPos(centerX,0,0);
		
		finalize();
		
		setPos(centerX,0,0);
	
	}
	
	public double getXAtY(double Y) {
		return  ( centerX +  getXT(T + Settings.height + accuracy + Y));
	}
	
	public double getXT(double T) {
		return  Settings.width * Math.sin(T/Settings.height/5 );
	}
	
	public void setT(double t) {
		T = t;
	}
	
	public double getT() {
		return T;
	}
	
}
