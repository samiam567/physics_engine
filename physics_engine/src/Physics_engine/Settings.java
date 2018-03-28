package Physics_engine;

import java.awt.Color;

public class Settings {
	
	public static final String version = "4.6.13";
	
	
	public static int width = 1500;
	public static int height = 1000;
	public static long depth = 10000;

	
	public static final long frameTime = 2500000; //this doesn't do anything anymore
	public static int timeOutTime = 150;
	
	public static final Color frameColor = Color.GRAY;
	
	public static final double elasticity = 1; //a number between 0 and 1
	
	
	public static boolean perspective = false;
	public static double distanceFromScreenMeters = 0.3025; //the distance in meters the viewer is away from the screen
	public static final double distanceFromScreen = distanceFromScreenMeters / 0.000115; //should not be changed
	
	
	public static final boolean displayObjectNames = false; 
	public static final boolean showPointNumbers = false;
	
	public static final boolean autoResizeFrame = true;
	
	//algorithm select
	public static int rotationAlgorithm = 6; // v3,v4, v5, and 0 disables V6 is housed in the Physics_3DPolygon class
	public static final int forceMethod = 1;
	public static int collision_algorithm = 4; //possibles are 1, 2, 3, 4, and 5  as 2 was a complete failure


	
	public static final double thetaStep = Math.PI/10;

	public static final double frameStep = 1; //this is automatically set per object_draw by the computer. This is just the starting value
}