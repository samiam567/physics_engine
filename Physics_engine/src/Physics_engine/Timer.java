package Physics_engine;

import java.util.ArrayList;

public class Timer extends physics_object {
	protected double magnatude;
	protected boolean timerDone = false,delete = false; //delete is for the garbage collector (not made yet)
	protected long startTime;
	protected String type;
	public Timer(object_draw drawer1, double magnatude1,String type1) {
		super(drawer1);
		magnatude = magnatude1;
		type = type1;
	
		switch(type) {
			case("frames"):
				break;
			case("seconds"):
				startTime = System.nanoTime();
				break;
			case("nanoSeconds"):
				startTime = System.nanoTime();
				break;
			default:
				Exception e = new Exception("" + type + " is not a vaid unit of time for forceTimer " + name);
				e.printStackTrace();
				break;
		}
	}
	
	
	
	public void Update(ArrayList<physics_object> objects,double frames) {	
		if (magnatude <= 0) {
			timerDone = true;
			delete = true;
		}else {
			switch(type) {
			case("frames"):
				timeLeft-= frames;
				break;
			case("seconds"):
				break;
			default:
				Exception e = new Exception("" + type + " is not a vaid unit of time for forceTimer " + name);
				e.printStackTrace();
				break;
		}
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

