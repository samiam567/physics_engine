package Physics_engine;

import java.awt.Color;

public abstract class Physics_drawable extends physics_object implements movable, drawable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9189940711362964625L;

	protected double centerX,centerY, centerZ;
	
	protected point center;
	
	protected double xSpeed,ySpeed,zSpeed,xAccel,yAccel,zAccel,xSize,ySize, zSize,xSizeAppearance,ySizeAppearance,zSizeAppearance;
	
	protected double parallaxValue = 1;
	
	protected point[] points = null; //there are no points
	int[] pointRenderOrder = null;
	
//	protected int x,y,z;
	protected double xReal,yReal,zReal;


	protected Color color = Color.BLACK;
	public String drawMethod = "paint";
	
	protected movable parent_object; //this object will move and act relative to it's parent object (useful for making complex objects out of multiple shapes)
	
	public boolean hasParentObject = false, isAnchored = false,isFilled = false, isVisible = true, isAlwaysVisible = false;
	
	@Deprecated
	public Physics_drawable() {
		super(physics_runner.drawer);
		
	}
	
	
	public Physics_drawable(object_draw drawer1) {
		super(drawer1);
		
	}
	
	protected void calculateCenter() {
	
		centerX = xReal + xSize/2;
		centerY = yReal + ySize/2;
		centerZ = zReal + zSize/2;
	
		updateCenter();
	}
	
	protected void updateCenter() {
		try {
			center.setPos(centerX, centerY,centerZ);
		}catch(NullPointerException n) {
			center = new point(drawer,centerX,centerY,centerZ);
		}
	}
	
	public boolean getIsAnchored() {
		return isAnchored;
	}
	
	public void updateSize() {
		//as z gets bigger, the object gets further away from the viewer, and the object appears to be smaller
		parallaxValue = (Settings.distanceFromScreen) / (centerZ + Settings.distanceFromScreen);
		if (Double.isNaN(parallaxValue) ) parallaxValue = 1;
		xSizeAppearance = parallaxValue * xSize;
		ySizeAppearance = parallaxValue * ySize;
		zSizeAppearance = parallaxValue * zSize;
	}
	
	public double getCenterX() { //finds the x coordinate of the object's center
		return centerX;
	}
	public double getCenterY() { //finds the y coordinate of the object's center
		return centerY;
	}
	public double getCenterZ() {
		return centerZ;
	}

	protected void updatePos() {
		xReal = centerX - xSize/2;
		yReal = centerY - ySize/2;
		zReal = centerZ - zSize/2;
		
	}
	
	public void setSize(double xSize1,double ySize1,double zSize1) { //sets the size of the object
		xSize = xSize1;
		ySize = ySize1;
		zSize = zSize1;
		updateCenter();
		updateSize();
	}
	
	public void setPos(double centerX1,double centerY1,double centerZ1) {
		centerX = centerX1;
		centerY = centerY1;
		centerZ = centerZ1;
		updatePos();
	}
	
	public void setSpeed(double xSpeed1, double ySpeed1, double zSpeed1) { //sets the speed of the object
		xSpeed = xSpeed1;
		ySpeed = ySpeed1;
		zSpeed = zSpeed1;
	}
	
	public void addSpeed(double xSpeed1, double ySpeed1, double zSpeed1) {
		xSpeed += xSpeed1;
		ySpeed += ySpeed1;
		zSpeed += zSpeed1;
	}
	
	public void setAccel(double xAccel1, double yAccel1, double zAccel1) { //sets the acceleration of the object
		xAccel = xAccel1;
		yAccel = yAccel1;
		zAccel = zAccel1;
	}
	
	public void addAccel(double xAccel1, double yAccel1, double zAccel1) { //sets the acceleration of the object
		xAccel += xAccel1;
		yAccel += yAccel1;
		zAccel += zAccel1;
	}
	
	@Deprecated
	public void setCenter(double centerX1,double centerY1, double centerZ1) { //same as setPos
		centerX = centerX1;
		centerY = centerY1;
		centerZ = centerZ1;
		
		try {
			center.setPos(centerX1, centerY1, centerZ1);
		}catch(NullPointerException n) {
			center = new point(drawer,centerX1,centerY1,centerZ1);
		}
		
		updatePos();
	}

	
	public int getX() {
		return (int) Math.round(xReal);
	}
	
	public int getY() {
		return (int) Math.round(yReal);
	}
	
	public int getZ() {
		return (int) Math.round(zReal);
	}
	
	public double getXReal() {
		return xReal;
	}
	
	public double getYReal() {
		return yReal;
	}
	
	public double getZReal() {
		return zReal;
	}
	
	public double getXSize() {
		return xSize;
	}
	
	public double getYSize() {
		return ySize;
	}

	public double getZSize() {
		return zSize;
	}
	
	public double getXSizeAppearance() {
		return xSizeAppearance;
	}
	
	public double getYSizeAppearance() {
		return ySizeAppearance;
	}

	public double getZSizeAppearance() {
		return zSizeAppearance;
	}
	
	public double getXSpeed() {
		return xSpeed;
	}
	
	public double getYSpeed() {
		return ySpeed;
	}
	
	public double getZSpeed() {
		return zSpeed;
	}
	
	
	public Color getColor() {
		return color;
	}

	public void setParentObject(Physics_drawable newParent) { //links this object to the object passed into this method (by setting it as the parent_object)
		parent_object = newParent;
		hasParentObject = true;
	}
	
	public void setColor(Color color1) {
		color = color1;
	}


	@Override
	public double getXAccel() {
		return xAccel;
	}

	@Override
	public double getYAccel() {
		return yAccel;
	}

	@Override
	public double getZAccel() {
		return zAccel;
	}

	@Override
	public boolean getIsVisible() {
		return isVisible;
	}
	
	@Override
	public boolean getIsAlwaysVisible() {
		return isAlwaysVisible;
	}

	@Override
	public String getDrawMethod() {
		return drawMethod;
	}


	@Override
	public int[] getPointRenderOrder() {
		return pointRenderOrder;
	}
	
	@Override
	public boolean hasParentObject() {
		return hasParentObject;
	}
	

	public movable getParentObject() {
		return parent_object;
	}

}
