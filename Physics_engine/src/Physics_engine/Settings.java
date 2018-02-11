package Physics_engine;

import java.awt.Color;

public class Settings {
	
	public static final String version = "3.6";
	
	
	public static int width = 1000;
	public static int height = 600;
	public static final int depth = 1000;

	public static final double frameStep = 0.01;
	public static final long frameTime = 7000000; 
	public static final int timeOutTime = 500000;
	
	public static final Color frameColor = Color.GREEN;
	
	public static final double elasticity = 1; //a number between 0 and 1
	
	public static final double distanceFromScreenMeters = 0.3025; //the distance in meters the viewer is away from the screen
	public static final double distanceFromScreen = distanceFromScreenMeters / 0.000115; //should not be changed
	
	
	public static final boolean displayObjectNames = false;
	public static final boolean showAxis = false;
	public static final boolean showPointNumbers = false;
	
	public static final boolean autoResizeFrame = true;
	
	//algorithm select
	public static final int rotationAlgorithm = 3; // v1, v2, v3, and 0 disables
	public static final int forceMethod = 1;
	public static final int collision_algorithm = 4; //possibles are 1, 2, 3, and 4 as 2 was a complete failure (4 is wayyy better than the other ones)
	public static final double thetaStep = Math.PI/4;

	
		//v3
//		public static final double prediction_step = 0.5; // only applicable for V3 - this is the distance between intervals for the prediction collision algorithm. (forceMethod 3)
//		public static final int collision_check_distance = 10; //distance to check for a collision in a single frame
		
		
		
}