package Physics_engine;

import java.awt.Graphics;

public class FPS_display extends ScoreBoard {
	
	private long lastRenderTime = System.nanoTime();
	public FPS_display(object_draw drawer1, int x, int y) {
		super(drawer1, x, y, "FPS:", drawer1.getWaitTime());
		roundScore = true;
		name = "Unnamed FPSDisplay";
	}

	public void paint(Graphics page) {
		setScore(1000000000/((double)(System.nanoTime() - lastRenderTime))); //set the displayValue to the frequency this object is being painted
		
		lastRenderTime = System.nanoTime();
		super.paint(page);
	}

	
	
}
