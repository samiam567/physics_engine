package Physics_engine;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.util.ArrayList;

import Physics_engine.Physics_engine_toolbox.faces;
import Physics_engine.Physics_engine_toolbox.pointOfRotationPlaces;

public class Physics_polygon extends Physics_shape implements pointed, rotatable, massive {
	
	public point[] points = {}; //all of the points in the object
	int[] pointXs = {}; //all of the x coordinates of the points in the object
	int[] pointYs = {}; //all of the y coordinates of the points in the object
	int[] pointZs = {}; //all of the y coordinates of the points in the object
	
	double[] pointXReals = {}; //all of the x coordinates of the points in the object
	double[] pointYReals = {}; //all of the y coordinates of the points in the object
	double[] pointZReals = {}; //all of the y coordinates of the points in the object
	
	int[] pointRenderOrder = {}; //the order in which lines will be drawn from point to point (for listedPointAlgorithm)
	
	//for massive
	double mass,friction_coefficient;
		
	private Polygon polyXY, polyZY;
	private Area areaXY,areaZY;
	
	double xRotation=0,yRotation=0,zRotation=0,angularVelocityX=0, angularVelocityY=0, angularVelocityZ=0, angularAccelX=0, angularAccelY=0, angularAccelZ=0;
	public boolean isRotatable = true,isTangible = true, affectedByBorder = true;
	point pointOfRotation = null; //the point that the object rotates around
	pointOfRotationPlaces pointOfRotationPlace = pointOfRotationPlaces.center;  //the place that that point is
	
	public Physics_polygon(object_draw drawer1) {
		super(drawer1);
	}
	
	public boolean getIsTangible() {
		return isTangible;
	}

	public point getCenter() {
		return center;
	}
	
	public double getMass() {
		return mass;
	}
	
	public pointOfRotationPlaces getPointOfRotationPlace() {
		return pointOfRotationPlace;
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
		try {
			parent_object = (rotatable) parent_object;
			if (pointOfRotationPlace.equals(pointOfRotationPlaces.center)) {
				pointOfRotation = center;
			}else if(pointOfRotationPlace.equals(pointOfRotationPlaces.parentCenter)) {
				pointOfRotation = ((rotatable) parent_object).getCenter();
			}else if(pointOfRotationPlace.equals(pointOfRotationPlaces.parentsPlace)) {
				pointOfRotation = ((rotatable) parent_object).getPointOfRotation();
			}
		}catch(ClassCastException c) {
			System.out.println("Parent object not rotatable for child: " + name);
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
	
	public void updatePoints() {				
		if (isRotatable){
			double r;
			
			//creating a vector from the pointOfRotation to each point in the object
			Vector pointVector;
			point cPoint;
			 
			for (int i = 0; i < points.length ; i++) {
				cPoint = points[i];
				r = points[i].getR();
				
				pointVector = new Vector(drawer,r, cPoint.getThetaXY() + zRotation, cPoint.getThetaZX() + yRotation, cPoint.getThetaZY() + xRotation,"thetaZY",this);
				
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
				cPoint.setR(Physics_engine_toolbox.distance(cPoint,pointOfRotation));
			}catch(NullPointerException n) { //this will be caught if pointOfRotation doesn't exist yet.
				pointOfRotation = new point(drawer,centerX,centerY,centerZ); //create pointOfRotation and set it to the center of the object using the default method
				cPoint.setR(Physics_engine_toolbox.distance(cPoint,pointOfRotation));
			}
		
			
			tempVec = new Vector(drawer,cPoint,pointOfRotation);
			
			System.out.println("-");
			System.out.println("point: " + cPoint.getThetaXY() + "," + cPoint.getThetaZX() + "," + cPoint.getThetaZY() );
		//	cPoint.setAngle(tempVec.getThetaXY(), tempVec.getThetaZX(), tempVec.getThetaZY());  //this line is what makes this method not work!!
			
			System.out.println("point: " + cPoint.getThetaXY() + "," + cPoint.getThetaZX() + "," + cPoint.getThetaZY() );
			System.out.println("-");
	
		}
	}
	
	public void checkForCollision(massive current_physics_object,ArrayList<physics_object> objects) { //generic checkForCollisions method that is overriden by all tangible pObjects
		
		if (Settings.collision_algorithm == 5) {
			updatePointXsYsAndZs();
			updateAreas();
			
			point cPoint;
			double force, reflec_const, time = 0.1;	
			
			try {
				current_physics_object = (pointed) current_physics_object;
				for (int i = 0; i < ((pointed) current_physics_object).getPoints().length; i++) {
					cPoint = ((pointed) current_physics_object).getPoints()[i];
		
					
					if (cPoint.isIn(this)) {
						System.out.println(name + "BOOM!" + current_physics_object.getObjectName());
						isCollided((physics_object) current_physics_object,Physics_engine_toolbox.faces.none);
						Vector reflectionVector = new Vector(drawer,center,cPoint);
						reflec_const = reflectionVector.r;
						Vector momentumVector = new Vector(drawer,current_physics_object.getXSpeed(),current_physics_object.getYSpeed(),current_physics_object.getZSpeed());
						force = momentumVector.r / time;
						applyComponentForce(force * reflectionVector.getXComponent()/reflec_const ,force  * reflectionVector.getYComponent()/reflec_const,force * reflectionVector.getZComponent()/reflec_const,time);
					}
				}
			}catch(ClassCastException c) {}
			
			
		}else {
			checkForCollision1((Physics_polygon) current_physics_object, objects);
		}
		
	
	}
	
	public void checkForCollisions(ArrayList<physics_object> objects) { // calls the checkForCollision method for every object in the objects list
		
		if (isTangible) {
			for (physics_object current_pObject : objects) {
				
				if ( (! equals(current_pObject) ) && (((Physics_polygon) current_pObject).getIsTangible()) && (isTangible) ) ((Physics_polygon) current_pObject).checkForCollision(this,objects);
	
			}	
		}
	}
	

	public Object checkForCollision1(Physics_polygon current_object,ArrayList<physics_object> objects) {
	//for v1-4 collision
		return null;
	}
		
	public void applyForce(double r, double theta, double z_magn) { //theta is an angle from the eastward direction
		zAccel += z_magn/mass; //updating the zAccel with the z component of the force vector
		
		//calculating the components of the force vectors
		double xComponent = r * Math.cos(theta);
		double yComponent = r * Math.sin(theta);
		
		//updating the x and y Accel with the respective x and y components of the force vector
		xAccel += xComponent/mass; 
		yAccel += yComponent/mass;
	
	}
	
	public void applyForce(double r, double theta, double z_magn, double time) { //applies a force to the object
		zAccel += z_magn/mass; //updating the zAccel with the z component of the force vector
		
		//calculating the components of the force vectors
		double xComponent = r * Math.cos(theta);
		double yComponent = r * Math.sin(theta);
		
		//updating the x and y Accel with the respective x and y components of the force vector
		xAccel += xComponent/mass; 
		yAccel += yComponent/mass;
		drawer.add(new ForceTimer(drawer,time,xComponent,yComponent,z_magn,this));
	}
	
	public void applyComponentForce(double x_magn, double y_magn, double z_magn) {
		xAccel += x_magn/mass; //updating the xAccel with the x component of the force vector
		yAccel += y_magn/mass; //updating the yAccel with the y component of the force vector
		zAccel += z_magn/mass; //updating the zAccel with the z component of the force vector
	}
	
	public void applyComponentForce(double x_magn, double y_magn, double z_magn, double time) {
		xAccel += x_magn/mass; //updating the xAccel with the x component of the force vector
		yAccel += y_magn/mass; //updating the yAccel with the y component of the force vector
		zAccel += z_magn/mass; //updating the zAccel with the z component of the force vector
		drawer.add(new ForceTimer(drawer,time,x_magn,y_magn,z_magn,this));
	}
	

	
	public double[] getTrajectory() { //returns a polar representation of the velocity of the object
		double r = Math.sqrt(Math.pow(xSpeed,2) + Math.pow(ySpeed,2) + Math.pow(zSpeed, 2) ); //three-dimensional pythagorean theorem
		double xyTheta = Math.atan(ySpeed/xSpeed);
		double zxTheta = Math.atan(zSpeed/xSpeed);
		double[] trajPolar = {r,xyTheta,zxTheta};
		return trajPolar;
	}
	
	public double[] calculateDeflectionAngle(double angleOfApproach,double zComponent) { //this should prob get overriden
		double angleOfReflection = 180-angleOfApproach;
		return new double[] {angleOfReflection,zComponent};
		
	}
	
	protected double[] calculateDeflectionAngle(Physics_polygon current_object) { //this shouldn't have to get overridden
		double angleOfApproach = Math.atan(current_object.getYSpeed() / current_object.getXSpeed());
		return calculateDeflectionAngle(angleOfApproach,current_object.getZSpeed());
		
	}
	

	public void isCollided(physics_object object, faces side) { //method that gets called when the object hits something. Useful for things like spikes or bullets in a game
		
	}
	
	public void setMass(double mass1) { //update the mass of the object (kg)
		mass = mass1;
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
	public String getDrawMethod() {
		return drawMethod;
	}

	@Override
	public int[] getPointRenderOrder() {
		return pointRenderOrder;
	}

	@Override
	public double getFrictionCoefficient() {
		return friction_coefficient;
	}

	@Override
	public point getPointOfRotation() {

		return pointOfRotation;
	}
	
	
}

