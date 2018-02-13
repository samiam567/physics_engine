package Physics_engine;

import java.util.ArrayList;

public class FrameTimer extends physics_object {
	protected double timeLeft;
	protected boolean timerDone = false,delete = false; //delete is for the garbage collector (not made yet)
	
	public FrameTimer(object_draw drawer1, double timeLeft1) {
		super(drawer1);
		timeLeft = timeLeft1;
	}
	
	public void updatePoints() {
		//do nothing since there aren't any points
	}
	
	public void Update(ArrayList<physics_object> objects) {	
		if (timeLeft <= 0) {
			timerDone = true;
			delete = true;
		}else {
			timeLeft--;
		}
	}
	
	public void Update(ArrayList<physics_object> objects, double frames) {	
		if (timeLeft <= 0) {
			timerDone = true;
			delete = true;
		}else {
			timeLeft-= frames;
		}
	}
	
	public void setTimeLeft(int timeLeft1) {
		timeLeft = timeLeft1;
	}
	
	public double getTimeLeft() {
		return timeLeft;
	}
	public boolean isDone() {
		return timerDone;
	}
}

