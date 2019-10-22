package pole_position;


import java.awt.Color;

import Physics_engine.Physics_engine_toolbox;
import Physics_engine.PointSet;
import Physics_engine.Settings;
import Physics_engine.Square;
import Physics_engine.Vector3D;
import Physics_engine.object_draw;
import Physics_engine.point;

public class Car extends PointSet {
	
	private boolean playerControlled;
	public static double turningRadius = 5,fudge = 1,OpponentSpeed = 20, speed = 50;
	
	private static double targXPrev = -0.123421351632,targYPrev,targZPrev;
	 
	private double deviationFromTargPos = 100,targX;
	
	private point targetPoint;
	private int targetPointIndx;
	public Car(object_draw drawer1,double cenX, double cenY,double cenZ, boolean PlayerControlled) {
		super(drawer1);
		double size = 100;
		setPos(cenX,cenY,cenZ);
		
		isFilled = true;

		playerControlled = PlayerControlled;
		hasNormalCollisions = false;
		
		targetPoint = new point(drawer,0,0,0);
		
		if (playerControlled) {
			setColor(Color.magenta);
		}else {
			setColor(Color.blue);
		}
		
		
		addPoint(new point(drawer,cenX - size/2, cenY + size/2,cenZ));
		addPoint(new point(drawer,cenX + size/2, cenY + size/2,cenZ));
		addPoint(new point(drawer,cenX, cenY - size/2,cenZ));
		
		initialize();
		finish();

	}
	
	public void frameUpdate3(double frames) {
	
		//driving toward center of track
		if (! playerControlled) {
			
			
			
			Track trackL = Pole_position_runner.tLc;
			Track trackR = Pole_position_runner.tRc;
			
				
			if (targXPrev == -0.123421351632) { //code that means this is the first time this method was called
				targXPrev = (trackL.getPoints()[targetPointIndx].getXReal()+trackR.getPoints()[targetPointIndx].getXReal()) /2;
				targYPrev = (trackL.getPoints()[targetPointIndx].getYReal()+trackR.getPoints()[targetPointIndx].getYReal()) /2;
				targZPrev = (trackL.getPoints()[targetPointIndx].getZReal()+trackR.getPoints()[targetPointIndx].getZReal()) /2;
			}
			
			targetPoint.setPos((trackL.getPoints()[targetPointIndx].getXReal()+trackR.getPoints()[targetPointIndx].getXReal()) /2,(trackL.getPoints()[targetPointIndx].getYReal()+trackR.getPoints()[targetPointIndx].getYReal()) /2,(trackL.getPoints()[targetPointIndx].getZReal()+trackR.getPoints()[targetPointIndx].getZReal()) /2);
			
			
			
			double r = Physics_engine_toolbox.distance(center, targetPoint);
			
			
			if (r <= 1.5 * Physics_engine_toolbox.distance(trackL.getPoints()[targetPointIndx],trackR.getPoints()[targetPointIndx])/2) {
				targetPointIndx++;
			
				if (targetPointIndx >= trackL.getPoints().length) {
					targetPointIndx = 0;
				}
			}
			
			
			setSpeed((-targXPrev+targetPoint.getXReal())/0.01 + OpponentSpeed * (targetPoint.getXReal()-getXReal())/r,(-targYPrev+targetPoint.getYReal())/0.01 + OpponentSpeed * (targetPoint.getYReal()-getYReal())/r,(-targZPrev+targetPoint.getZReal())/0.01 + OpponentSpeed * (targetPoint.getZReal()-getZReal())/r);
			
			
			
			targXPrev = targetPoint.getXReal();
			targYPrev = targetPoint.getYReal();
			targZPrev = targetPoint.getZReal();
		
		
		}else {
			setPos(Settings.width/2,Settings.height/2,0);
		}
		
	
	}
}
