package Physics_engine;

import java.awt.Color;
import java.util.ArrayList;

public class Physics_drawable extends physics_object implements movable, drawable {
	
	protected double centerX,centerY, centerZ;
	
	protected point center;
	
	double xSpeed,ySpeed,zSpeed,xAccel,yAccel,zAccel,xSize, ySize, zSize,xSizeAppearance, ySizeAppearance, zSizeAppearance;
	
	point[] points = null; //there are no points
	
	int x,y,z;
	double xReal,yReal,zReal;
	boolean isVisible = true,isFilled = false;
	Color color = Color.BLACK;
	public String drawMethod = "paint";
	
	protected movable parent_object; //this object will move and act relative to it's parent object (usefull for making complex objects out of multiple shapes)
	
	protected boolean hasParentObject = false, isAnchored = false;; //if the object is linked to a parent object
	 
	
	public Physics_drawable(object_draw drawer1) {
		super(drawer1);
	}
	
	protected void calculateCenter() {
		
		 //vector method
		/*
		try {
			System.out.println(name + ">>" + points[0].getR());
			
			Vector centerVector = new Vector(points[0].getR(),Math.PI + zRotation + points[0].getThetaXY(),Math.PI + yRotation + points[0].getThetaZX(),Math.PI + xRotation + points[0].getThetaZY(),"thetaZY",this);
			centerVector.setPos(points[0].getXReal(), points[0].getYReal(), points[0].getZReal());
			
			centerX = centerVector.getVectorTip().getXReal();
			centerY = centerVector.getVectorTip().getYReal();
			centerZ = centerVector.getVectorTip().getZReal();
		}catch (ArrayIndexOutOfBoundsException a) {
			centerX = xReal + xSize/2;
			centerY = yReal + ySize/2;
			centerZ = zReal + zSize/2;
		}
		*/
		
		//center of mass by point coordinate averaging method
			//this method of finding the center uses physics instead of geometry to find the center. It attempts to estimate the center of mass of the object by using the points as an estimate to where mass of the object is.	
		try {
			
			double temp = points[0].getXReal(); //this will throw an error and trigger the catch statement if there are no points
			
			System.out.println(">>>");
			System.out.println(name);
			// the sums of all the x,y,and z coordinates of the points
			double totalX = 0;
			double totalY = 0;
			double totalZ = 0;
			
			for (point cPoint : points) { //loop through the points and add their coordinates to the totals
				System.out.println(cPoint.getXReal() + "," + cPoint.getYReal());
				totalX += cPoint.getXReal();
				totalY += cPoint.getYReal();
				totalZ += cPoint.getZReal();
			}
			double centerXX = totalX/points.length;
			double centerYY = totalY/points.length;
			System.out.println("cenXX: " + centerXX);
			System.out.println("cenYY: " + centerYY);
			
		
			//divide by the number of points to get the average
			centerX = totalX / points.length;
			centerY = totalY / points.length;
			centerZ = totalZ / points.length;
			
	
			
			
			
		}catch (ArrayIndexOutOfBoundsException a) { //if the object doesn't have a points list, use the default method of finding the center
			System.out.println(name + " has no points");
			centerX = xReal + xSize/2;
			centerY = yReal + ySize/2;
			centerZ = zReal + zSize/2;
		}catch (NullPointerException n) {
			//if this is not a pointed object
			centerX = xReal + xSize/2;
			centerY = yReal + ySize/2;
			centerZ = zReal + zSize/2;
		}
			
		try {
			center.setPos(centerX, centerY,centerZ);
		}catch(NullPointerException n) {
			center = new point(drawer,centerX,centerY,centerZ);
		}
		
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
	
	protected void updateSize() {
		//as z gets bigger, the object gets further away from the viewer, and the object appears to be smaller
		xSizeAppearance = (Settings.distanceFromScreen * xSize ) / (zReal + Settings.distanceFromScreen);
		ySizeAppearance = (Settings.distanceFromScreen * ySize ) / (zReal + Settings.distanceFromScreen);
		zSizeAppearance = (Settings.distanceFromScreen * zSize ) / (zReal + Settings.distanceFromScreen);		
	}
	public double getCenterX() { //finds the x coordinate of the object's center
		return centerX;
	}


	public double getCenterY() { //finds the y coordinate of the object's center
		return centerY;
	}
	
	protected void updatePos() {
		xReal = centerX - xSize/2;
		yReal = centerY - ySize/2;
		zReal = centerZ - zSize/2;
		x = (int) Math.round(xReal);
		y = (int) Math.round(yReal);
		z = (int) Math.round(zReal);
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
	
	public void setAccel(double xAccel1, double yAccel1, double zAccel1) { //sets the acceleration of the object
		xAccel = xAccel1;
		yAccel = yAccel1;
		zAccel = zAccel1;
	}
	

	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getZ() {
		return z;
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
}
