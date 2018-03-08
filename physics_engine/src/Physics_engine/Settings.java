package Physics_engine;

import java.awt.Color;

public class Settings {
	
	public static final String version = "4.5.6";
	
	
	public static int width = 1000;
	public static int height = 600;
	public static final int depth = 1000;

	public static final double frameStep = 0.01;
	public static final long frameTime = 20000000; 
	public static int timeOutTime = 500000;
	
	public static final Color frameColor = Color.GRAY;
	
	public static final double elasticity = 1; //a number between 0 and 1
	
	public static final double distanceFromScreenMeters = 0.3025; //the distance in meters the viewer is away from the screen
	public static final double distanceFromScreen = distanceFromScreenMeters / 0.000115; //should not be changed
	
	
	public static final boolean displayObjectNames = false;
	public static final boolean showPointNumbers = false;
	
	public static final boolean autoResizeFrame = true;
	
	//algorithm select
	public static int rotationAlgorithm = 4; // v3,v4, v5, and 0 disables
	public static final int forceMethod = 1;
	public static int collision_algorithm = 5; //possibles are 1, 2, 3, 4, and 5  as 2 was a complete failure
	public static final double thetaStep = Math.PI/4;


		
		
}