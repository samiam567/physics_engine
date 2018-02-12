package Physics_engine;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Area;

import Physics_engine.Physics_engine_toolbox.pointOfRotationPlaces;

public class Physics_polygon extends Physics_shape implements pointed, rotatable{
	
	public point[] points = {}; //all of the points in the object
	int[] pointXs = {}; //all of the x coordinates of the points in the object
	int[] pointYs = {}; //all of the y coordinates of the points in the object
	int[] pointZs = {}; //all of the y coordinates of the points in the object
	
	double[] pointXReals = {}; //all of the x coordinates of the points in the object
	double[] pointYReals = {}; //all of the y coordinates of the points in the object
	double[] pointZReals = {}; //all of the y coordinates of the points in the object
	
	int[] pointRenderOrder = {}; //the order in which lines will be drawn from point to point (for listedPointAlgorithm)
	
	private Polygon polyXY, polyZY;
	private Area areaXY,areaZY;
	
	double xRotation=0,yRotation=0,zRotation=0,angularVelocityX=0, angularVelocityY=0, angularVelocityZ=0, angularAccelX=0, angularAccelY=0, angularAccelZ=0;
	public boolean isRotatable = true;
	point pointOfRotation = null; //the point that the object rotates around
	pointOfRotationPlaces pointOfRotationPlace = pointOfRotationPlaces.center;  //the place that that point is
	
	public Physics_polygon(object_draw drawer1) {
		super(drawer1);
	}

	public void paint(Graphics page) {	
		if (Settings.displayObjectNames) page.drawString(name,(int) Math.round(points[0].getXReal()), (int) Math.round(points[0].getYReal())); //displaying the name of the object
		
		
		if (Settings.showPointNumbers) {
			point current_point;
			for (int i = 0; i < points.length; i++) {
				current_point = points[i];
				page.drawString("" + i, current_point.getX(), current_point.getY()); //display the point numbers
			}
		}		
		
		updatePointXsYsAndZs();
		updateAreas();
	
		if (isFilled) {
			page.fillPolygon(pointXs, pointYs, points.length);
		}else {
			page.drawPolygon(pointXs, pointYs, points.length);
		}
			
	
	}
	
	public point[] getPoints() {
		return points;
	}
	
	protected void updateAreas() {
		updatePolygons();
		
		areaXY = new Area(polyXY);
		areaZY = new Area(polyZY);
		
	}
	
	private void updatePolygons() {
		
		try {
			polyXY.xpoints = pointXs;
			polyXY.ypoints = pointYs;
			polyXY.npoints = pointXs.length;
			
			polyZY.xpoints = pointZs;
			polyZY.ypoints = pointYs;
			polyXY.npoints = pointYs.length;
			
		
		}catch(NullPointerException n) { //if the polys don't exist, then create them
			polyXY = new Polygon();
			polyZY = new Polygon();
			
			polyXY.xpoints = pointXs;
			polyXY.ypoints = pointYs;
			polyXY.npoints = pointXs.length;
			
			polyZY.xpoints = pointZs;
			polyZY.ypoints = pointYs;
			polyXY.npoints = pointYs.length;
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


	public void setParentObject(physics_object newParent) { //links this object to the object passed into this method (by setting it as the parent_object)
		parent_object = newParent;
		hasParentObject = true;
	}
	
	public void setRotation(double xRotation1, double yRotation1, double zRotation1) { //this is not a wise method to use as it frequently results in impossible rotations.
		xRotation = xRotation1;
		yRotation = yRotation1;
		zRotation = zRotation1;
	}
	
	public void setPointOfRotation(point newPointOfRotation) {
		pointOfRotation = newPointOfRotation;
		
		pointOfRotationPlace = pointOfRotationPlaces.custom;
		
		calculatePointValues();
		
		
		
	}
	
	public void setPointOfRotationPlace(pointOfRotationPlaces newPlace) {
		pointOfRotationPlace = newPlace;

		updatePointOfRotation();
	}
	
	public void updatePointOfRotation() { //this doesn't need to be updated every time the pointOfRotation changes because the pointOfRotation is an alias of the original point. (unless it's a custom pointOfRotation)
		if (pointOfRotationPlace.equals(pointOfRotationPlaces.center)) {
			pointOfRotation = center;
		}else if(pointOfRotationPlace.equals(pointOfRotationPlaces.parentCenter)) {
			pointOfRotation = parent_object.center;
		}else if(pointOfRotationPlace.equals(pointOfRotationPlaces.parentsPlace)) {
			pointOfRotation = parent_object.pointOfRotation;
		}
		
		
	}
	
	public Polygon getPolyXY() {
		return polyXY;
	}
	
	public Polygon getPolyZY() {
		return polyZY;
	}
	
	public Area getAreaXY() {
		
		return areaXY;
	}


	public Area getAreaZY() {
	
		return areaZY;
	}
	

	public double getXRotation() {
		return xRotation;
	}
	
	public double getYRotation() {
		return yRotation;
	}
	
	public double getZRotation() {
		return zRotation;
	}
	
	protected void updatePoints() {				
		if (isRotatable){
			double r;
			
			//creating a vector from the pointOfRotation to each point in the object
			Vector pointVector;
			point cPoint;
			 
			for (int i = 0; i < points.length ; i++) {
				cPoint = points[i];
				r = points[i].getR();
				
				pointVector = new Vector(r, cPoint.getThetaXY() + zRotation, cPoint.getThetaZX() + yRotation, cPoint.getThetaZY() + xRotation,"thetaZY",this);
				
				try {
					pointVector.setPos(pointOfRotation.getXReal(), pointOfRotation.getYReal(), pointOfRotation.getZReal());			
				}catch(NullPointerException n) {} //this will throw if the object has not been finished being constructed yet
						
				points[i].setPointVector(pointVector); //sets the vector AND updates the point's pos automatically
				
			}
		
			
		}
		
	}
	
	public void setAngularVelocity(double angVX, double angVY, double angVZ) {
		angularVelocityX = angVX;
		angularVelocityY = angVY;
		angularVelocityZ = angVZ;
	}
	
	public void setAngularAccel(double angAccelX, double angAccelY, double angAccelZ) {
		angularAccelX = angAccelX;
		angularAccelY = angAccelY;
		angularAccelZ = angAccelZ;
	}
	
	public void setPoints(point[] points1) {
		points = points1;
	}
	
	protected void calculatePointValues() {  //this method has BIGG issues
		Vector tempVec;
		for (point cPoint : points) {
		
			try {
				cPoint.setR(distance(cPoint,pointOfRotation));
			}catch(NullPointerException n) { //this will be caught if pointOfRotation doesn't exist yet.
				pointOfRotation = new point(centerX,centerY,centerZ); //create pointOfRotation and set it to the center of the object using the default method
				cPoint.setR(distance(cPoint,pointOfRotation));
			}
		
			
			tempVec = new Vector(cPoint,pointOfRotation);
			
			System.out.println("-");
			System.out.println("point: " + cPoint.getThetaXY() + "," + cPoint.getThetaZX() + "," + cPoint.getThetaZY() );
		//	cPoint.setAngle(tempVec.getThetaXY(), tempVec.getThetaZX(), tempVec.getThetaZY());  //this line is what makes this method not work!!
			
			System.out.println("point: " + cPoint.getThetaXY() + "," + cPoint.getThetaZX() + "," + cPoint.getThetaZY() );
			System.out.println("-");
	
		}
	}
}

