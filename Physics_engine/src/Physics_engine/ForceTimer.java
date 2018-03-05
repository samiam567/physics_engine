package Physics_engine;

import java.util.ArrayList;

public class ForceTimer extends Timer { //waits for a certain amount of time, then applies a counter force to essentially remove the force that it is timing
	double xComponent,yComponent,zComponent; 
	massive object;
	
	public ForceTimer(object_draw drawer1, double magnatude1,String type1,double xComponent1,double yComponent1,double zComponent1,massive object1) {
		super(drawer1, magnatude1,type1);
		xComponent = xComponent1;
		yComponent = yComponent1;
		zComponent = zComponent1;
		object = object1;
	}
	
	protected void endTimer() {	
		object.applyComponentForce(-xComponent, -yComponent, -zComponent);
	}
	

	

}
