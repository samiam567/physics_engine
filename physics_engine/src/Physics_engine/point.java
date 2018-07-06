package Physics_engine;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Area;

public class point extends Physics_drawable {
	private int id;
	private double thetaXY, thetaZX, thetaZY; //angle relative to the center of rotation
	private double theta,phi;
	private double r; //distance from the center of rotation
	private int quadrant; //the quad this point is in (if applicable)
	
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
	
	
	
	public boolean isIn(Physics_3DPolygon pObject) { //returns true if the point is inside the passed object
		
		Area areaXY = pObject.getAreaXY();		

		return areaXY.contains(xReal,yReal) && ( Math.abs(zReal - pObject.centerZ) < (pObject.zSize + drawer.frameStep) );
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
		x = (int) Math.round(xReal);
		y = (int) Math.round(yReal);
		z = (int) Math.round(zReal);
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
		if (Settings.displayObjectNames) page.drawString(name, x, y); //display the name of the point
		page.drawLine(x, y, x, y); //draw the point
	}

	

	
}
