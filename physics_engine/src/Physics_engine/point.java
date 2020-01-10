package Physics_engine;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Point2D;

public class point extends Physics_drawable {
	private int id;
	private double thetaXY, thetaZX, thetaZY; //angle relative to the center of rotation
	private double theta,phi;
	private double r; //distance from the center of rotation
	private int quadrant; //the quad this point is in (if applicable)
	
	//multipurpose lists for rotation
	private static double[] rotMagsStat = new double[3];
	private static double[] rotComps = new double[2]; 
	public point(object_draw drawer1,double[] dimensions,int id1) { //dimensions = {x,y,z}
		super(drawer1);
		setPos(dimensions[0],dimensions[1],dimensions[2]);
		setId(id1);
		xSize = 0;
		ySize = 0;
		zSize = 0;
		center = this;
	}
	
	public point(object_draw drawer1,double xReal1, double yReal1, double zReal1) {
		super(drawer1);
		setPos(xReal1,yReal1,zReal1);
		xSize = 0;
		ySize = 0;
		zSize = 0;
		center = this;
	}
	
	
	public void setX(double x1) {
		xReal = x1;
		updatePos();
	}
	
	public void setY(double y1) {
		yReal = y1;
		updatePos();
	}
	
	public void setZ(double z1) {
		zReal = z1;
		updatePos();
	}
	
	
//rotation -------------------------------------------
	private static double[] calculateRotation(double x, double y, double angle) {
		long nanoStart1 = System.nanoTime();
		double[] polar = Vector2D.rectangularToPolar(x, y);
		return Vector2D.polarToRectangular(polar[0], polar[1] + angle);	
	}
	
	public static double[] rotate(double x, double y, double z,Vector3D rotVector) { //rotates about the given vector, <the length of the vector> radians
		
		//rotate to the plane of the vector ~~~~~
		rotMagsStat[0] = -rotVector.getTheta();
		rotMagsStat[1] = -rotVector.getPhi();
		
		//zRotation (theta)
		rotComps = calculateRotation(x,y,rotMagsStat[0]);
		x = rotComps[0];
		y = rotComps[1];
		
		//yRotation (phi)
		rotComps = calculateRotation(x,z,rotMagsStat[1]);
		x = rotComps[0];
		z = rotComps[1];
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		
		
		//rotate in that vector's plane
		rotComps = calculateRotation(x,y,rotVector.getR());
		x = rotComps[0];
		y = rotComps[1];
		
		
		
		//rotating back to the original plane ~~~~
		rotMagsStat[0] = rotVector.getTheta();
		rotMagsStat[1] = rotVector.getPhi();
		
		//yRotation (phi)
		rotComps = calculateRotation(x,z,rotMagsStat[1]);
		x = rotComps[0];
		z = rotComps[1];
		
		//zRotation (theta)
		rotComps = calculateRotation(x,y,rotMagsStat[0]);
		x = rotComps[0];
		y = rotComps[1];
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		return (new double[] {x,y,z});
	}
	
	public static double[] rotate(double x, double y, double z, double[] rotMags) {
		
		//zRotation
		rotComps = calculateRotation(x,y,rotMags[2]);
		x = rotComps[0];
		y = rotComps[1];
		
		//xRotation
		rotComps = calculateRotation(z,y,rotMags[0]);
		z = rotComps[0];
		y = rotComps[1];
		
		//yRotation
		rotComps = calculateRotation(x,z,rotMags[1]);
		x = rotComps[0];
		z = rotComps[1];
		
		return new double[] {x,y,z};
	}
	
	
	public double[] rotate(Vector3D rotMagn) {
		rotMagsStat = point.rotate(getXReal(),getYReal(),getZReal(),rotMagn);
		setPos(rotMagsStat[0],rotMagsStat[1],rotMagsStat[2]);
		return rotMagsStat;
	}
	
	public double[] rotate(double[] rotMags) {
		rotMagsStat = point.rotate(getXReal(),getYReal(),getZReal(),rotMags);
		setPos(rotMagsStat[0],rotMagsStat[1],rotMagsStat[2]);
		return rotMagsStat;
	}
	
	
//-------------------------------------------------------------
	
	
	public boolean isIn(Physics_3DPolygon pObject) { //returns true if the point is inside the passed object
			
		return pObject.getAreaXY().contains(getXReal(),getYReal()) && ( Math.abs(getZReal() - pObject.getCenterZ()) < (pObject.getZSize() + drawer.getFrameStep()) );
	}
	
	public void setAngle(double thetaXY1, double thetaZX1, double thetaZY1) {
		thetaXY = thetaXY1;
		thetaZX = thetaZX1;
		thetaZY = thetaZY1;
	}
	
	public void setAngle(double theta1, double phi1) {
		theta = theta1;
		phi = phi1;
	}
	
	public void setPos(double x1, double y1, double z1) {
		xReal = x1;
		yReal = y1;
		zReal = z1;
		updatePos();
	}
	
	protected void updateCenter() {
		center = this;
	}
	
	protected void updatePos() {
		centerX = xReal;
		centerY = yReal;
		centerZ = zReal;
	}

	public void setR(double r1) {
		r = r1;
	}
	
	public void setQuadrant(int quad1) {
		quadrant = quad1;
	}
	
	public int getQuadrant() {
		return quadrant;
	}

	public double getThetaXY() {
		return thetaXY;
	}

	public double getThetaZX() {
		return thetaZX;
	}
	
	public double getThetaZY() {
		return thetaZY;
	}
	
	public double getR() {
		return r;
	}
	public double getTheta() {
		return theta;
	}
	public double getPhi() {
		return phi;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void paint(Graphics page) {
		if (Settings.displayObjectNames) page.drawString(name, getX(), getY()); //display the name of the point
		page.drawLine(getX(), getY(), getX(), getY()); //draw the point
	}

	public Point get2DPoint() {
		Point pp = new Point();
		pp.setLocation(getXReal(),getYReal());
		return pp;
	}

	

	
}
