package Physics_engine;

import java.util.ArrayList;

public class SpeedTimer extends Timer{ //waits for a certain magnitude of time, then applies a speed
	double xComponent,yComponent,zComponent; 
	movable object;
	
	public SpeedTimer(object_draw drawer1, double magnitude1, String type1, double xComponent1,double yComponent1,double zComponent1,movable object1) {
		super(drawer1,magnitude1, type1);
		xComponent = xComponent1;
		yComponent = yComponent1;
		zComponent = zComponent1;
		object = object1;
	}
	
	protected void endTimer() {
		object.setSpeed(xComponent, yComponent, zComponent);
	}
	

}