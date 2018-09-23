package pole_position;


import java.awt.Color;

import Physics_engine.PointSet;
import Physics_engine.Settings;
import Physics_engine.Square;
import Physics_engine.object_draw;
import Physics_engine.point;

public class Car extends PointSet {
	
	private boolean playerControlled;
	public static double turningRadius = 5,fudge = 1,speed = 20;
	
	private double deviationFromTargPos = 100,targX;
	
	public Car(object_draw drawer1,double cenX, double cenY,double cenZ, boolean PlayerControlled) {
		super(drawer1);
		double size = 100;
		setPos(cenX,cenY,cenZ);
		
		isFilled = true;

		playerControlled = PlayerControlled;
		hasNormalCollisions = false;
		
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
			
			if (centerY > Settings.height + 100) {
				setPos(Pole_position_runner.tLc.getXAtY(centerY),-100,centerZ);
			}
			
			double targX1 = (Pole_position_runner.tLc.getXAtY(centerY) + Pole_position_runner.tRc.getXAtY(centerY))/2 ;
			
			targX = targX1;
			
			if (Math.abs(deviationFromTargPos) > fudge) {
				if (deviationFromTargPos > 0) {
					deviationFromTargPos -= turningRadius * frames;
				}else {
					deviationFromTargPos += turningRadius * frames;
				}
				setSpeed(xSpeed,Pole_position_runner.trackL.getYSpeed() - speed * 0.7,zSpeed);
				
				setPos(targX + deviationFromTargPos,centerY,centerZ);
			}else {
				setSpeed(xSpeed,Pole_position_runner.trackL.getYSpeed() - speed * 0.7,zSpeed);
				setPos(targX + deviationFromTargPos,centerY,centerZ);
			}
		}else {
			setPos(Settings.width/2,Settings.height/2,0);
		}
		
	
	}
}
