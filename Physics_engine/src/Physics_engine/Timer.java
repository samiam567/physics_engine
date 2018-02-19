package Physics_engine;

import java.util.ArrayList;

public class Timer extends physics_object {
	protected double magnatude;
	protected boolean timerDone = false; 
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
	
	protected void endTimer() {
		//can be extended by child timers to do something when the timer ends
	}
	
	public void frameUpdate2(double frames) {
		switch(type) {
			case("frames"):
				if (magnatude <= 0) {
					timerDone = true;
					delete = true;
					endTimer();
				}else {
					magnatude-= frames;
				}
			break;
			
			case("seconds"):
				if ((System.nanoTime() - startTime) >= magnatude*Math.pow(10,9)) {
					timerDone = true;
					delete = true;
					endTimer();
				}
			break;
			
			case("nanoSeconds"):
				if ((System.nanoTime() - startTime) >= magnatude) {
					timerDone = true;
					delete = true;
					endTimer();
				}
			break;
				
			default:
				Exception e = new Exception("" + type + " is not a vaid unit of time for forceTimer " + name);
				e.printStackTrace();
			break;
		}
		
		if (delete) {
			drawer.remove(this);
		}
		
	}

	
	public void setMagnatude(int timeLeft1) {
		magnatude = timeLeft1;
	}
	
	public double getMagnatude() {
		return magnatude;
	}
	public boolean isDone() {
		return timerDone;
	}
}

