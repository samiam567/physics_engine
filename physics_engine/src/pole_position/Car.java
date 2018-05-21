package pole_position;


import Physics_engine.PointSet;
import Physics_engine.Settings;
import Physics_engine.object_draw;
import Physics_engine.point;

public class Car extends PointSet {
	
	private boolean playerControlled;
	private double turningRadius = 10,fudge = 1;
	
	public Car(object_draw drawer,double cenX, double cenY,double cenZ, boolean PlayerControlled) {
		super(drawer);
		double size = 100;
		setPos(cenX,cenY,cenZ);
		setMass(100);
		playerControlled = PlayerControlled;
		
		addPoint(new point(drawer,cenX - size/2, cenY + size/2,cenZ));
		addPoint(new point(drawer,cenX + size/2, cenY + size/2,cenZ));
		addPoint(new point(drawer,cenX, cenY - size/2,cenZ));
		
		initialize();
		finalize();
	
	}
	
	public void tertiaryUpdate() {
	
		//driving toward center of track
		if (! playerControlled) {
			double targX = Pole_position_runner.trackL.getXAtY(centerY) ;

			if (Math.abs(centerX - targX) > fudge) {
				if (centerX < targX) {
					setSpeed(turningRadius,ySpeed,zSpeed);
				}else {
					setSpeed(-turningRadius,ySpeed,zSpeed);
				}
			}
		}
		
		
	}
}
