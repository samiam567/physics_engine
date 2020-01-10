package zortex;

import java.awt.Color;

import Physics_engine.Square;
import Physics_engine.object_draw;

public class Enemy_explosion_particles extends Square {
	
	/**
	 * 
	 */
	private static double particleSize = 50;
	public static double particleNumber = 15;
	private static double speed = 20;
	
	private double counter = 0;
	public Enemy_explosion_particles(object_draw drawer1, double EnX, double EnY, double i) {
		super(drawer1, EnX + 7*Math.cos(i) ,EnY +  7*Math.sin(i), 0, particleSize, 0.01);
		setSpeed(speed * Math.cos(i), speed * Math.sin(i),0);
		isTangible = false;
		setColor(Color.GRAY);
		isFilled = true;
		
		
		
	}
	
	@Override
	public void tertiaryUpdate() {
		float size = (float) (particleSize/Math.pow(counter*drawer.getFrameStep()*10000,0.4));
		setSize(size,size,0.001);
		counter++;
		
		if (counter > 1000000) {
			drawer.remove(this);
		}
	}
	
	
}
