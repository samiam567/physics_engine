package Physics_engine;

import java.awt.Graphics;
import java.util.ArrayList;

import Physics_engine.Physics_engine_toolbox.pointOfRotationPlaces;

public class Vector extends Physics_shape {

	protected double r,xComponent,yComponent,zComponent;
	private double thetaXY, thetaZX, thetaZY;
	private String valueAngle;
	private point vectorTip = new point(drawer,xReal,yReal,zReal);
	private int quadrant = -1;
	private physics_object parent;
	
	public Vector(object_draw drawer1, double xComponent1, double yComponent1, double zComponent1) {
		super(drawer1);
		xComponent = xComponent1;
		yComponent = yComponent1;
		zComponent = zComponent1;

		calculateThetas();
		
		updatePos();
		drawMethod = "paint";
		updatePoints();
		
	}

	public Vector (object_draw drawer1,point point1, point point2) {
		super(drawer1);
		
		xComponent = point2.getXReal() - point1.getXReal();
		yComponent = point2.getYReal() - point1.getYReal();
		zComponent = point2.getZReal() - point1.getZReal();
		

		
		reduceThetas();
		
		updatePos();
		
		updatePoints();
		
		r = Physics_engine_toolbox.distance(points[0], points[1]);
		
		setPos(point1.getXReal(), point1.getYReal(), point1.getZReal());
	}
	
	public Vector (object_draw drawer1,point point1, point point2,physics_object parent1) {
		super(drawer1);
		parent = parent1;
		
		xComponent = point2.getXReal() - point1.getXReal();
		yComponent = point2.getYReal() - point1.getYReal();
		zComponent = point2.getZReal() - point1.getZReal();
		
		calculateThetas();
		
		reduceThetas();
		
		updatePos();
		
		updatePoints();
		
		r = Physics_engine_toolbox.distance(points[0], points[1]);
		
		setPos(point1.getXReal(), point1.getYReal(), point1.getZReal());

	}
	
	public Vector(object_draw drawer1,double r1, double theta, double zComponent1, boolean isPolar) { //old angle system
		super(drawer1);
		assert isPolar == true;
		r = r1;
		xComponent = r * Math.cos(theta);
		yComponent = r * Math.sin(theta);
		zComponent = zComponent1;
		updatePos();
		drawMethod = "paint";
		updatePoints();
		
	}
	
	public Vector(object_draw drawer1,double r1, double thetaXY1, double thetaZX1, double thetaZY1, String valueAngle1)  {
		super(drawer1);
		valueAngle = valueAngle1;
		if (!( (valueAngle == "thetaZX") || (valueAngle == "thetaZY") ) ) {
			
			try {
				throw new NoSuchFieldException("valueAngle is which z angle you want the engine to use to calculate the components. It must be either \"thetaZX\" or \"thetaZY\". \n valueAngle is currently: " + valueAngle + ". Object: " + toString());
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
	
		}
		
		thetaXY = thetaXY1;
		thetaZX = thetaZX1;
		thetaZY = thetaZY1;
		r = r1;
		setPos(0,0,0);
		calculateComponents();		
		updatePos();
		drawMethod = "paint";
		
	}

	public Vector(object_draw drawer1,double r1, double thetaXY1, double thetaZX1, double thetaZY1, String valueAngle1, physics_object parent1)  {
		super(drawer1);
		valueAngle = valueAngle1;
		parent = parent1;
		if (!( (valueAngle == "thetaZX") || (valueAngle == "thetaZY") ) ) {
			
			try {
				throw new NoSuchFieldException("valueAngle is which z angle you want the engine to use to calculate the components. It must be either \"thetaZX\" or \"thetaZY\". \n valueAngle is currently: " + valueAngle + ". Object: " + toString());
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
	
		}
		
		thetaXY = thetaXY1;
		thetaZX = thetaZX1;
		thetaZY = thetaZY1;
		r = r1;
		setPos(0,0,0);
		calculateComponents();		
		updatePos();
		
		drawMethod = "paint";
		
	}
	

	public Vector vectorAdd(Vector cVector)  { //adds two vectors together
		return (new Vector(drawer,xComponent + cVector.getXComponent(),yComponent + cVector.getYComponent(), zComponent + cVector.getZComponent()));
	}

	public Vector vectorAdd(Vector[] vectors) { //adds together a list of vectors
		Vector resultant = new Vector(drawer,0,0,0);	
		for (Vector cVector : vectors) resultant.setComponents(resultant.xComponent + cVector.getXComponent(),resultant.yComponent + cVector.getYComponent(), resultant.zComponent + cVector.getZComponent());
		resultant.calculateThetas();
		resultant.setPos(vectors[0].xReal, vectors[0].yReal, vectors[0].zReal);
		resultant.updatePoints();
		return resultant;
	}
	
	public Vector vectorAdd(double r, Vector[] vectors) { //adds together a list of vectors and sets the r of the resultant to the passed parameter
		Vector resultant = new Vector(drawer,0,0,0);	
		for (Vector cVector : vectors) resultant.setComponents(resultant.xComponent + cVector.getXComponent(),resultant.yComponent + cVector.getYComponent(), resultant.zComponent + cVector.getZComponent());
		resultant.calculateThetas();
		resultant.setPos(vectors[0].xReal, vectors[0].yReal, vectors[0].zReal);
		resultant.updatePoints();
		
		resultant.calculateR();
		
		double resizeFraction = r / resultant.getR(); //the ratio of r's (similar triangular prisms)
	
		//dividing the components to resize the vector
		resultant.xComponent *= resizeFraction;
		resultant.yComponent *= resizeFraction;
		resultant.zComponent *= resizeFraction;
		
		resultant.updatePoints();
		
		return resultant;
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
	
	public void setComponents(double xComponent1, double yComponent1, double zComponent1) {
		xComponent = xComponent1;
		yComponent = yComponent1;
		zComponent = zComponent1;
		calculateThetas();
		updatePos();
		updatePoints();
	}
	

	private void calculateComponents() { //calculating the x,y,and z components of the vector using the angles
		
		reduceThetas();
		
		if (valueAngle == "thetaZX") {

			yComponent = Math.sqrt( 
						(Math.pow(r, 2) * Math.pow(Math.sin(thetaXY),2) * Math.pow(Math.cos(thetaZX),2)) /
						(1 - Math.pow(Math.sin(thetaXY), 2) * Math.pow(Math.sin(thetaZX),2)));

			zComponent = Math.sin(thetaZX) * Math.sqrt(Math.pow(r, 2) - Math.pow(yComponent,2));
			
			xComponent = Math.sqrt(Math.pow(r, 2) - Math.pow(yComponent, 2) - Math.pow(zComponent, 2) );
	
			thetaZY = Math.atan(yComponent/zComponent);

		}else {
			assert valueAngle == "thetaZY";
			

				xComponent = Math.sqrt( 
					(Math.pow(r, 2) * Math.pow(Math.cos(thetaXY),2) * Math.pow(Math.sin(thetaZY),2)) /
					(1 + Math.pow(Math.sin(thetaXY), 2) * Math.pow(Math.sin(thetaZY),2)));

				zComponent = Math.cos(thetaZY) * Math.sqrt(Math.pow(r, 2) - Math.pow(xComponent,2));

				yComponent = Math.sqrt(Math.pow(r, 2) - Math.pow(xComponent, 2) - Math.pow(zComponent, 2) );

				thetaZX = Math.atan(zComponent/xComponent);

		}
		
		reduceThetas();
		
		quadCompAdjustments();
		
		updatePoints();
	}
	
	private void reduceThetas() {  //reduces all of the thetas in the vector to a 0-2pi range and gets rid of negatives
		thetaXY = reduceTheta(thetaXY);
		thetaZX = reduceTheta(thetaZX);
		thetaZY = reduceTheta(thetaZY);
	}
	
	private double reduceTheta(double theta) { //reduces thetas greater than 2pi to a 0-2pi range and gets rid of negatives
		double reducedTheta = theta;
	
		if (theta > 0) {
			reducedTheta = (theta % (2 * Math.PI));
		}else if (theta < 0) {
			reducedTheta = (theta % (-2 * Math.PI));
			reducedTheta += (2 * Math.PI);
		}else if (Math.abs(theta - 6.283185307179585) < 0.0000001) {
			reducedTheta = 0;
		}else {
			reducedTheta = 0;
		}
		
		if (reducedTheta < 0) {
			Exception e = new Exception("reducedTheta is negative");
			e.printStackTrace();
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("theta: " + theta);
			System.out.println("reducedTheta: " + reducedTheta);
		}
		
		return reducedTheta;
	}
	
	
	public int getQuadrantOfAngle(double theta) { //gets the 2d quadrant of an angle in the 0-3 quadrant system
		int quad = -1;
		
		if (( 0 <= theta) && (theta < Math.PI/2)) {
			quad = 0;
		}else if ((Math.PI/2 <= theta) && (theta < Math.PI)) {
			quad = 1;
		}else if ((Math.PI <= theta) && (theta < (3*Math.PI)/2)) {
			quad = 2;
		}else {
			assert ( (3*Math.PI/2 <= theta) && (theta < 2*Math.PI) );
			quad = 3;
		}
		
		return quad;
	}
	
	public void quadCompAdjustments() {

		if (thetaXY != 0) {
			switch(getQuadrantOfAngle(thetaXY)) {
				case(0):
					yComponent = - Math.abs(yComponent);
					break;
				
				case(1):
					xComponent = - Math.abs(xComponent);
					yComponent = - Math.abs(yComponent);
					break;
				
				case(2):
					xComponent = - Math.abs(xComponent);
					break;
				
				case(3):
					//do nothing in this quad
					break;
			}
			
			if ((valueAngle == "thetaZX") && (thetaZX != 0) ) { //if thetaZX is 0 it should be neglected
				switch(getQuadrantOfAngle(thetaZX)) {
				case(0):
					//no zComp negate here
					break;
				
				case(1):
					zComponent = - Math.abs(zComponent);
					break;
				
				case(2):
					zComponent = - Math.abs(zComponent);
					break;
				
				case(3):
					//no zComp negate here
					break;
				
				}
			}else if (thetaZY != 0) { //if thetaZY is 0 it should be neglected
				assert valueAngle == "thetaZY";
				switch(getQuadrantOfAngle(thetaZY)) {
				case(0):
					//no zComp negate here
					break;
				
				case(1):
					
					zComponent = - Math.abs(zComponent);
					break;
				
				case(2):
					zComponent = - Math.abs(zComponent);
					break;
				
				case(3):
					//no zComp negate here
					break;
				
				}
			}
		}else { //thetaXY is 0 and should be neglected
			if ((valueAngle == "thetaZX") && (thetaZX != 0) ) {
				switch(getQuadrantOfAngle(thetaZX)) {
					case(0):
						//no zComp negate here
						break;
					
					case(1):
						xComponent = - Math.abs(xComponent);
						break;
					
					case(2):
						zComponent = - Math.abs(zComponent);
						xComponent = - Math.abs(xComponent);
						break;
					
					case(3):
						zComponent = - Math.abs(zComponent);
						break;
					
				}
			}else if (thetaZY != 0) { //if thetaZY is 0 it should be neglected
				assert valueAngle == "thetaZY";
				switch(getQuadrantOfAngle(thetaZY)) {
				case(0):
					yComponent = - Math.abs(zComponent);
					break;
				
				case(1):
					yComponent = - Math.abs(zComponent);
					break;
				
				case(2):
			
					break;
				
				case(3):
					
					break;
				
				}
			}
		}
			
		
	}
	

	private void calculateR() {
		r = Physics_engine_toolbox.distance(points[0], points[1]);
	}
	
	private void calculateThetas() { 

		if (Math.abs(xComponent) < 0.001) { //atan won't work if xComp is 0 (division by 0)
			thetaXY = 0;
		}else {
			thetaXY = Math.atan(yComponent/xComponent);
		}
		
		if (Math.abs(xComponent) < 0.001)  {
			thetaZX = 0;
		}else {
			thetaZX = Math.atan(zComponent/xComponent);	
		}
		
		if (Math.abs(zComponent) < 0.001) { 
			thetaZY =0;
		}else { //atan won't work if zComp is 0 (division by 0)
			thetaZY = Math.atan(yComponent/zComponent);
		}
		
		quadrantThetaAdjustments();
	}
	
	public void quadrantThetaAdjustments() {

		switch (quadrant) {
			case(0):
				thetaZX = 2*Math.PI - thetaZX;
				thetaZY = Math.PI - thetaZY;
				break;
			
			case(1):
				thetaXY = Math.PI - thetaXY;
				thetaZX = Math.PI + thetaZX;
				thetaZY = Math.PI - thetaZY;
				break;
			
			case(2):
				thetaXY = Math.PI + thetaXY;
				thetaZX = Math.PI + thetaZX;
				thetaZY = Math.PI + thetaZY;
				break;
			
			case(3):
				thetaXY = 2*Math.PI - thetaXY;
				thetaZX = 2*Math.PI - thetaZX;
				thetaZY = Math.PI + thetaZY;
				break;
			
			case(4): 
				//they're all in this quad already
				break;
			
			case(5):
				thetaXY = Math.PI - thetaXY;
				thetaZX = Math.PI - thetaZX;
				break;
			
			case(6):
				thetaXY = Math.PI + thetaXY;
				thetaZX = Math.PI - thetaZX;
				thetaZY = 2*Math.PI - thetaZY;
				break;
			
			case(7):
				thetaXY = 2*Math.PI - thetaXY;
				thetaZY = 2*Math.PI - thetaZY;
				break;
				
		}
	}
	

	public void updatePoints() {
		
		vectorTip.setPos(xReal + xComponent, yReal + yComponent, zReal + zComponent);
		
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
	
	public double getYComponent() {
		return yComponent;	
	}
	
	public double getXComponent() {
		return xComponent;	
	}

	public double getZComponent() {
		return zComponent;	
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
	
	public point getVectorTip() {
		return vectorTip;
	}
	
	public void paint(Graphics page) {
		int ovalSize = (int) ( r/10 );
		page.fillOval(points[0].getX() - (int) (ovalSize/2), points[0].getY()- (int) (ovalSize/2), ovalSize, ovalSize ); //draw a point at the base of the vector
		
		page.drawLine(points[0].getX(),points[0].getY(),vectorTip.getX(),vectorTip.getY());  //these two lines should do the same thing
		//page.drawLine(x, y , x + (int) Math.round(xComponent),y + (int) Math.round(yComponent));
	}
	

	public double getR() {
		return r;
	}

	public void setR(double r1) {

		updatePoints();
		
		calculateR();
		
		double resizeFraction = r/r1; //the ratio of r's (similar triangular prisms)
	
		//dividing the components to resize the vector
		xComponent /= resizeFraction;
		yComponent /= resizeFraction;
		zComponent /= resizeFraction;
		
		updatePoints();
		
	}

}
	