package Physics_engine;

import java.util.ArrayList;

public class SpeedTimer extends FrameTimer{ //waits for a certain number of frames, then applies a speed
	double xComponent,yComponent,zComponent; 
	movable object;
	
	public SpeedTimer(object_draw drawer1, int timeLeft1,double xComponent1,double yComponent1,double zComponent1,movable object1) {
		super(drawer1,timeLeft1);
		xComponent = xComponent1;
		yComponent = yComponent1;
		zComponent = zComponent1;
		object = object1;
	}
	
	public void secondaryUpdate() {	
		if (! timerDone) {
			if (timeLeft <= 0) {
				assert timeLeft == 0;
				timerDone = true;
				object.setSpeed(xComponent, yComponent, zComponent);
			}else{
				timeLeft--;
			}
		}
	}
	

}