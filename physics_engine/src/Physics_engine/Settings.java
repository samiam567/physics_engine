package Physics_engine;

import java.awt.Color;

public class Settings {
	
	public static final String version = "4.15.2";
	
	public static boolean debugMode = true;
	public static double pixelConversion = 1; //37.65;
	
	public static int width = (int) (1000 / pixelConversion);
	public static int height = (int) (800/ pixelConversion);
	public static long depth =  (long) (1000 / pixelConversion);

	public static double timeSpeed = 1;
	
	public static double[] lightSource = {Settings.width/2,Settings.height/2,-1000};
	
	public static long frameTime = 100; //the time (in milliseconds) we allow for a single unit of game time
	public static int timeOutTime = 10000;
	
	public static Color frameColor = Color.GRAY;
	
	public static double elasticity = 0.3; //a number between 0 and 1 which determines what % of kinetic is conserved in a collision
	

	public static boolean perspective = true;
	public static double distanceFromScreenMeters = 0.3025; //the distance in meters the viewer is away from the screen
	public static final double distanceFromScreen = distanceFromScreenMeters / 0.000115; //should not be changed
	

	
	public static boolean displayObjectNames = false; 
	public static final boolean showPointNumbers = false;
	
	public static final boolean autoResizeFrame = true;
	
	//algorithm select
	public static int rotationAlgorithm = 6; // v3,v4, v5, and 0 disables V6 is housed in the Physics_3DPolygon class
	public static final int forceMethod = 1;
	public static int collision_algorithm = 5; //possibles are 1,2,3, 4, and 5



	
	public static final double thetaStep = Math.PI/10;

	public static final double frameStep = 1; //this is automatically set per object_draw by the computer. This is just the starting value
}