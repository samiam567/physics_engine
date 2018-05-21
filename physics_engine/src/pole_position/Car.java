package pole_position;


import Physics_engine.PointSet;
import Physics_engine.Settings;
import Physics_engine.object_draw;
import Physics_engine.point;

public class Car extends PointSet {
	
	private boolean playerControlled;
	public static double turningRadius = 5,fudge = 1,speed = 20;
	
	private double deviationFromTargPos = 100,targX;
	
	public Car(object_draw drawer,double cenX, double cenY,double cenZ, boolean PlayerControlled) {
		super(drawer);
		double size = 100;
		setPos(cenX,cenY,cenZ);
		setMass(100);
		playerControlled = PlayerControlled;
		
		hasNormalCollisions= false;
		addPoint(new point(drawer,cenX - size/2, cenY + size/2,cenZ));
		addPoint(new point(drawer,cenX + size/2, cenY + size/2,cenZ));
		addPoint(new point(drawer,cenX, cenY - size/2,cenZ));
		
		initialize();
		finalize();
	
	}
	
	public void frameUpdate3(double frames) {
	
		//driving toward center of track
		if (! playerControlled) {
			
			if (centerY > Settings.height + 100) {
				setPos(Pole_position_runner.trackL.getXAtY(centerY),-100,centerZ);
			}
			
			double targX1 = (Pole_position_runner.trackL.getXAtY(centerY) + Pole_position_runner.trackR.getXAtY(centerY))/2 ;
			
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
		}
		
		
	}
}
