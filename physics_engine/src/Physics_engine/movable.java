package Physics_engine;

import Physics_engine.physics_object.object_types;

public interface movable extends drawable { // the ability to be moved
	public object_types object_type = object_types.movable;
	
	/* vars needed:
	public boolean isAnchored = false;
	double xSpeed=0,ySpeed=0,zSpeed=0,xAccel=0,yAccel=0,zAccel=0;
	*/
	
	public boolean getIsAnchored();
	public double getXSpeed();
	public double getYSpeed();
	public double getZSpeed();
	
	public double getXAccel();
	public double getYAccel();
	public double getZAccel();
	public void setSpeed(double xComponent, double yComponent, double zComponent);
	public void addSpeed(double xSpeed, double ySpeed, double zSpeed);
	public void setAccel(double xAccel, double yAccel, double zAccel);
	public void addAccel(double xAccel, double yAccel, double zAccel);
	public void setPos(double centX, double centY, double centZ);
}
	
