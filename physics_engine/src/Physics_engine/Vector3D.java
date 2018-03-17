package Physics_engine;

import java.awt.Graphics;

/* Source for the info on how to do conversions:
 * https://blog.demofox.org/2013/10/12/converting-to-and-from-polar-spherical-coordinates-made-easy/
 */

public class Vector3D extends Physics_shape  {  // like Vector but uses spherical coordinates
	private double r; //rho, or the length of the vector
	private double theta; //polar angle
	private double phi; //azimuth angle
	
	private point[] points = new point[2];
	
	int[] pointXs = {}; //all of the x coordinates of the points in the object
	int[] pointYs = {}; //all of the y coordinates of the points in the object
	int[] pointZs = {}; //all of the y coordinates of the points in the object
	
	double[] pointXReals = {}; //all of the x coordinates of the points in the object
	double[] pointYReals = {}; //all of the y coordinates of the points in the object
	double[] pointZReals = {}; //all of the y coordinates of the points in the object
	

	double xComponent,yComponent,zComponent;

	
//constructors
	public Vector3D(object_draw drawer, double r1, double theta1, double phi1, String Polar) {
		super(drawer);
		assert Polar == "polar";
		r = r1;
		theta = theta1;
		phi = phi1;
		sphericalToCartesian();
	}
	
	public Vector3D(object_draw drawer, double xComponent1, double yComponent1, double zComponent1) {
		super(drawer);
		xComponent = xComponent1;
		yComponent = yComponent1;
		zComponent = zComponent1;
		cartesianToSpherical();
	}
	
	
	
	public Vector3D(object_draw drawer, point base, point tip) {
		super(drawer);
		points[0] = base;
		points[1] = tip;
		
		xComponent = tip.getXReal() - base.getXReal();
		yComponent = tip.getYReal() - base.getYReal();
		zComponent = tip.getZReal() - base.getZReal();
		
		cartesianToSpherical();
	}

	
	
//calculations	
	public void sphericalToCartesian() {
		zComponent = Math.cos(theta) * Math.cos(phi) * r;
		xComponent = Math.sin(theta) * Math.cos(phi) * r;
		yComponent = Math.sin(phi) * r;
		updatePoints();
	}
	
	public void cartesianToSpherical() {
		r = Math.sqrt(Math.pow(xComponent, 2) + Math.pow(yComponent, 2) + Math.pow(zComponent, 2));
		theta = Math.atan2(xComponent, zComponent);
		phi = Math.acos(yComponent / r);
	}

	
	
//overriding/overloading
	public void paint(Graphics page) {
		int ovalSize = (int) ( r/10 );
		page.fillOval(x - (int) (ovalSize/2), y - (int) (ovalSize/2), ovalSize, ovalSize ); //draw a point at the base of the vector
	
		page.drawLine(x, y , x + (int) Math.round(xComponent),y + (int) Math.round(yComponent));
	}
	
	public void updatePoints() {
		try {	
			points[0].setPos(xReal, yReal, zReal);
			points[1].setPos(xReal + xComponent, yReal + yComponent, zReal + zComponent);
		}catch (NullPointerException n) {
			points[0] = new point(drawer,xReal,yReal,zReal);
			points[1] = new point(drawer,xReal + xComponent, yReal + yComponent, zReal + zComponent);
		}
	}
	
	public void updatePointXsYsAndZs() {
		try {
			for (int i = 0; i < points.length; i++) {
				pointXs[i] = points[i].getX();
				pointYs[i] = points[i].getY();
				pointZs[i] = points[i].getZ();
				
				pointXReals[i] = points[i].getXReal();
				pointYReals[i] = points[i].getYReal();
				pointZReals[i] = points[i].getZReal();
			}
		}catch(ArrayIndexOutOfBoundsException a) {
			pointXs = new int[points.length];
			pointYs = new int[points.length];
			pointZs = new int[points.length];
			
			pointXReals = new double[points.length];
			pointYReals = new double[points.length];
			pointZReals = new double[points.length];
			
			for (int i = 0; i < points.length; i++) {
				pointXs[i] = points[i].getX();
				pointYs[i] = points[i].getY();
				pointZs[i] = points[i].getZ();
				
				pointXReals[i] = points[i].getXReal();
				pointYReals[i] = points[i].getYReal();
				pointZReals[i] = points[i].getZReal();
			}
		}
	}
	public void setPos(double xReal1, double yReal1, double zReal1 ) {
		xReal = xReal1;
		yReal = yReal1;
		zReal = zReal1;
		x = (int) Math.round(xReal);
		y = (int) Math.round(yReal);
		z = (int) Math.round(zReal);
		updatePoints();
	}
	

//getter/setter methods
	public double getYComponent() {
		return yComponent;	
	}
	
	public double getXComponent() {
		return xComponent;	
	}

	public double getZComponent() {
		return zComponent;	
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
	public point[] getPoints() {
		return points;
	}
	
	
	public void setComponents(double xComponent1, double yComponent1, double zComponent1) {
		xComponent = xComponent1;
		yComponent = yComponent1;
		zComponent = zComponent1;
		cartesianToSpherical();
	}
	public void setR(double r1) {
		r = r1;
		sphericalToCartesian();
	}
	public void setSpherical(double r1, double theta1, double phi1) {
		r = r1;
		theta = theta1;
		phi = phi1;
		sphericalToCartesian();
	}
	public void setPoints(point[] points1) {
		points = points1;
	}

	public point getVectorTip() {
		return points[1];
	}

	
}
