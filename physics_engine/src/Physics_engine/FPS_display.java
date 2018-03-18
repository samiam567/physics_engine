package Physics_engine;

public class FPS_display extends ScoreBoard {
	public FPS_display(object_draw drawer1, int x, int y) {
		super(drawer1, x, y, "FPS:", drawer1.getWaitTime());
		roundScore = false;
	}

	
	
	public void secondaryUpdate() {
		try {
			setScore(1000000/( drawer.getWaitTime()*(351) ) );	
		}catch(ArithmeticException a) {}
	}
}
