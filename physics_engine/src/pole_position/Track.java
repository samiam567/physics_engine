package pole_position;


import java.awt.Color;

import Physics_engine.Physics_3DPolygon;
import Physics_engine.Physics_engine_toolbox.pointOfRotationPlaces;
import Physics_engine.PointSet;
import Physics_engine.PolarObject;
import Physics_engine.object_draw;
import Physics_engine.point;
import Physics_engine.Settings;
import Physics_engine.Square;

public class Track extends PolarObject {
	
	private double T, accuracy = 70, theta = Math.PI/6;
	

	
	public Track(object_draw drawer1,double size) {
		super(drawer1,0,0,0,size,"circle");
	

	
		isTangible = false;
	
		
		
		setPointOfRotation(new point(drawer,Settings.width/2,Settings.height/2,0));
	}

	
	
	/*
	public void tertiaryUpdate() {
			
		
		if ((points[0].getYReal() >= 0) && (points[0].getXReal() >= 0) && (points[0].getXReal() <= Settings.width)  ) {
			setPos(centerX,0,centerY);
			System.out.println("Finished");
			T += Settings.height;
			updatePoints();
			generateTrack();
			updatePoints();
			
		}
	}
	
	
	public void generateTrack() {
		
		double xRotTemp = getXRotation(),yRotTemp = getYRotation(),zRotTemp = getZRotation();
		setRotation(0,0,0);
		if (points.length > 1) {
			updatePoints();
		}
		
		setPos(Settings.width/2,0,0);
			
		pointsAL.clear();
		System.out.println(centerX + "," + centerY + "," + centerZ);
		
		if (points.length > 0) {

		//	setPos( centerX - points[points.length-1].getXReal(),0,0);
		}
		
		/*
		for (double t = Settings.height + accuracy;t >= -5 * Settings.height; t -= accuracy) {
			T-= accuracy;
			System.out.println("y:" + t + " T:" + T + " x: " + (double) (centerX + getXT(T)));
			addPoint(new point(drawer,centerX + getXT(T),t,centerZ));
			
		}
		
		
		
     	for (double t = -Settings.height;t < Settings.height + accuracy; t += accuracy) {
			T+= accuracy;
			System.out.println("y:" + t + " T:" + T + " x: " + (double) (centerX + getXT(T)));
			addPoint(new point(drawer,centerX + getXT(T),t,centerZ));
     	}
     	
		initialize();
		
		finish();
		
		setRotation(xRotTemp,yRotTemp,zRotTemp);
		
		setPos(Settings.width/2,0,0);
		
		pointRenderOrder = new int[points.length];
		for (int i = 0; i < points.length; i++) {
			pointRenderOrder[i] = i;
		}
		
		System.out.println(centerX + "," + centerY + "," + centerZ);
		
	
	
	}
	*/
	public int getPointIndex(double Y, int lower, int upper) {
		
		double val = points[(upper+lower)/2].getYReal();
		
		if ((upper - lower) <= 1) {
			return -1;
		}
		if (Math.abs(Y-val) <= accuracy ) {
			return (upper+lower)/2;
		}else if (Y > val) {
			return getPointIndex(Y,(upper+lower)/2,upper);
		}else {
			return getPointIndex(Y,lower,(upper+lower)/2);
		}
	}
	
	public double getXAtY(double Y) {
		int x = getPointIndex(Y,0,points.length);
		if (x > 1) {
			return  points[x].getXReal();
		}else {
			return -1;
		}
	}
	
	public double getXT(double T) {
		return  Settings.width/2 * Math.sin(T/Settings.height/5 );
	}
	
	public void setT(double t) {
		T = t;
	}
	
	public double getT() {
		return T;
	}
	
}
