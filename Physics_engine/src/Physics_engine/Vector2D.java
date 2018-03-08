package Physics_engine;

import java.awt.Graphics;

public class Vector2D extends Physics_shape {
	private double xComponent,yComponent; //rectangular variables
	private double theta,r; //polar variables
	private physics_object parent;
	private point vectorTip = new point(drawer,0,0,0);	
	
	public Vector2D(object_draw drawer1,double xComponent1, double yComponent1) {
		super(drawer1);
		xComponent = xComponent1;
		yComponent = yComponent1;
		rectangularToPolar();
	}
	
	public Vector2D(object_draw drawer1,double r1, double theta1, String Polar) {
		super(drawer1);
		assert Polar == "polar";
		
		r = r1;
		theta = theta1;
		
		polarToRectangular();
	}
	
	private void rectangularToPolar() {
		theta = Math.atan(yComponent/xComponent);
		r = Math.sqrt(Math.pow(xComponent, 2) + Math.pow(yComponent, 2));
		updatePoints();
	}
	
	private void polarToRectangular() {
		xComponent = r * Math.cos(theta);
		yComponent = r * Math.sin(theta);
		updatePoints();
	}
	
	public void paint(Graphics page) {
		int ovalSize = (int) ( r/10 );
		page.fillOval(points[0].getX() - (int) (ovalSize/2), points[0].getY()- (int) (ovalSize/2), ovalSize, ovalSize ); //draw a point at the base of the vector
		
		page.drawLine(points[0].getX(),points[0].getY(),vectorTip.getX(),vectorTip.getY());  //these two lines should do the same thing
		//page.drawLine(x, y , x + (int) Math.round(xComponent),y + (int) Math.round(yComponent));
	}
	
	public double getYComponent() {
		return yComponent;	
	}
	
	public double getXComponent() {
		return xComponent;	
	}
	public double getR() {
		return r;
	}
	public double getTheta() {
		return theta;
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
	
	
	public void updatePoints() {
		
		vectorTip.setPos(xReal + xComponent, yReal + yComponent, zReal);
		
		try {
			points[0].setPos(xReal, yReal, zReal);
			points[1] = vectorTip;
		}catch(NullPointerException n) {
			points = new point[2];
			points[0] = new point(drawer,xReal,yReal,zReal);
			points[1] = vectorTip;
		}catch(ArrayIndexOutOfBoundsException a) {
			points = new point[2];
			points[0] = new point(drawer,xReal,yReal,zReal);
			points[1] = vectorTip;
		}
	}
}
