package Physics_engine;

public class FCPS_display extends ScoreBoard {  //frames calculated per second
	public FCPS_display(object_draw drawer1, int x, int y) {
		super(drawer1, x, y, "FStep:", drawer1.getWaitTime());
		roundScore = false;
	}

	
	
	public void secondaryUpdate() {
		try {
			setScore(drawer.getFrameStep());	
		}catch(ArithmeticException a) {}
	}
}
