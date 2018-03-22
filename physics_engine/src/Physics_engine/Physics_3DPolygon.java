package Physics_engine;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.util.ArrayList;

import Physics_engine.Physics_engine_toolbox.faces;
import Physics_engine.Physics_engine_toolbox.pointOfRotationPlaces;

public class Physics_3DPolygon extends Physics_shape implements pointed, rotatable, massive {
	

	int[] pointXs = {}; //all of the x coordinates of the points in the object
	int[] pointYs = {}; //all of the y coordinates of the points in the object
	int[] pointZs = {}; //all of the y coordinates of the points in the object
	
	double[] pointXReals = {}; //all of the x coordinates of the points in the object
	double[] pointYReals = {}; //all of the y coordinates of the points in the object
	double[] pointZReals = {}; //all of the y coordinates of the points in the object
	
	protected int[] pointRenderOrder = {}; //the order in which lines will be drawn from point to point (for listedPointAlgorithm)
	
	public Polygon_point polyPointsStart; //the start of the linked list of polyPoints
	
	//for massive
	protected double mass;
	double friction_coefficient;
		
	private Polygon polyXY, polyZY;
	private Area areaXY,areaZY;
	
	public double elasticity = Settings.elasticity;
	
	double xRotation,yRotation,zRotation,angularVelocityX, angularVelocityY, angularVelocityZ, angularAccelX, angularAccelY, angularAccelZ;
	public boolean isRotatable = true,isTangible = true, affectedByBorder = true;
	point pointOfRotation = null; //the point that the object rotates around
	pointOfRotationPlaces pointOfRotationPlace = pointOfRotationPlaces.center;  //the place that that point is
	
	double xSizePrev,ySizePrev,zSizePrev,xSizeInit,ySizeInit,zSizeInit; //used to see if the size has changed
	
	
	
	private class Polygon_point extends point {
		
	
		private double xComponent,yComponent,zComponent;
		private double initialXComponent, initialYComponent, initialZComponent;
		private Physics_3DPolygon parent;
		
		private Polygon_point nextPoint;
		
		public Polygon_point(Physics_3DPolygon parent, double x1, double y1, double z1) {
			super(parent.drawer, x1, y1, z1);
			xReal = x1;
			yReal = y1;
			zReal = z1;
		}
		
		public void setNext(Polygon_point next) {
			nextPoint = next;
		}
	}
	
	public Physics_3DPolygon(object_draw drawer1) {
		super(drawer1);
		points = new Polygon_point[0]; //all of the points in the object
		updatePolygons();
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
			page.fillPolygon(polyXY);
		}else {
			page.drawPolygon(polyXY);
		}
		
	
	
	}
	
	protected void updateAreas() {
		updatePolygons();
		
		areaXY = new Area(polyXY);
		areaZY = new Area(polyZY);
	}
	
	public void updatePolygons() {
		
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


	public double[] calculateRotation(double x, double y, double angle) {
		Vector2D pointVector = new Vector2D(drawer,new point(drawer,x,y,0),pointOfRotation);
		
		pointVector.setTheta(pointVector.getTheta() + angle);
		
		return new double[] {pointVector.getXComponent(),pointVector.getYComponent()};
	}
	
	public void updatePoints() { //creates a vector from the pointOfRotation to each point in the object			
		
		if (isRotatable){
			Polygon_point cPoint = polyPointsStart;
			int pointCounter = 0;
			double[] rotComponents;
			
			double xI = centerX;
			double yI = centerY;
			double zI = centerZ;
			
		
			setPos(0,0,0);
			
			updateCenter();
			
			updatePointOfRotation();
			
			updateSize();
			
			do {
				
			
				cPoint.setPos(pointOfRotation.getXReal() + cPoint.initialXComponent * xSizeAppearance/xSizeInit, pointOfRotation.getYReal() + cPoint.initialYComponent * ySizeAppearance/ySizeInit, pointOfRotation.getZReal() + cPoint.initialZComponent * zSizeAppearance/zSizeInit);
					
				
				
				
				//zRotation
				rotComponents = calculateRotation(cPoint.getXReal(),cPoint.getYReal(),zRotation);
				cPoint.setPos(pointOfRotation.getXReal() + rotComponents[0],pointOfRotation.getYReal() + rotComponents[1],cPoint.getZReal() );
				
				
				//xRotation
				rotComponents = calculateRotation(cPoint.getZReal(),cPoint.getYReal(),xRotation);
				cPoint.setPos(cPoint.getXReal(), pointOfRotation.getYReal() + rotComponents[1], pointOfRotation.getZReal() + rotComponents[0]);
				
			
				//yRotation
				rotComponents = calculateRotation(cPoint.getXReal(),cPoint.getZReal(),yRotation);
				cPoint.setPos(pointOfRotation.getXReal() + rotComponents[0] + xI, cPoint.getYReal() + yI, pointOfRotation.getZReal() + rotComponents[1] + zI);
				
				

				points[pointCounter].setPos(cPoint.getXReal() , cPoint.getYReal(), cPoint.getZReal() );
				
				
				
				
				
				
				
				cPoint = cPoint.nextPoint;
				
				pointCounter++;
			} while (cPoint != null);
			
			setPos(xI,yI,zI);
	
		}else {
			double xChange = xReal - points[0].getX();
			double yChange = yReal - points[0].getY();
			double zChange = zReal - points[0].getZ();
			
			for (int i = 0; i < points.length; i++) {
				points[i].setPos(points[i].getXReal() + xChange, points[i].getYReal() + yChange, points[i].getZReal() + zChange);
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
	
	public point[] getPoints() {
		return points;
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
	
	public void calculatePointValues() { 
		
		Polygon_point cPoint;
		try {
			int pointOfRotationx = pointOfRotation.getX();
		}catch(NullPointerException n) {
			pointOfRotation = center;
		}
		
		xSizeInit = xSize;
		ySizeInit = ySize;
		zSizeInit = zSize;
		
		polyPointsStart = new Polygon_point(this,points[0].getXReal(),points[0].getYReal(),points[0].getZReal());
		
		cPoint = polyPointsStart;
		
		cPoint.initialXComponent = cPoint.getXReal() - pointOfRotation.getXReal();
		cPoint.initialYComponent = cPoint.getYReal() - pointOfRotation.getYReal();
		cPoint.initialZComponent = cPoint.getZReal() - pointOfRotation.getZReal();
		
		points[0].setPos(cPoint.getXReal(), cPoint.getYReal(), cPoint.getZReal());
		
		for (int i = 1; i < points.length; i++) {
			
			cPoint.setNext(new Polygon_point(this,points[i].getXReal(),points[i].getYReal(),points[i].getZReal()));
			
			cPoint = cPoint.nextPoint;
			
			cPoint.initialXComponent = cPoint.getXReal() - pointOfRotation.getXReal();
			cPoint.initialYComponent = cPoint.getYReal() - pointOfRotation.getYReal();
			cPoint.initialZComponent = cPoint.getZReal() - pointOfRotation.getZReal();
			
			
			points[i].setPos(cPoint.xReal, cPoint.yReal, cPoint.zReal);
		}
		
	}
	
	
	public void checkForCollision(massive current_physics_object,ArrayList<massive> objects) { 
		
		if (Settings.collision_algorithm == 5) {
			updatePointXsYsAndZs();
			updateAreas();
			drawer.frameStep = 0.001;
			drawer.setFrameTime(1000000);
			point cPoint;
			double force, reflec_const, time = 1;	
			
			try {
				faces side = faces.none;
				for (int i = 0; i < ((pointed) current_physics_object).getPoints().length; i++) {
					cPoint = ((pointed) current_physics_object).getPoints()[i];
		
					
					if (cPoint.isIn(this)) {
						
						try {
							current_physics_object = (border_bounce) current_physics_object;
						}catch(ClassCastException c) {
							
							System.out.println(name + " has hit " + current_physics_object.getObjectName());
							
							
							isCollided((physics_object) current_physics_object,side);
							current_physics_object.isCollided(this, side);
							
							
							
						}
						
					}
				}
			}catch(ClassCastException c) {
				System.out.println("catch: " + name);
			}
			
			
		}else {
			checkForCollision1((massive) current_physics_object, objects);
		}
		
	
	}
	
	public void checkForCollisions(ArrayList<massive> objects) { // calls the checkForCollision method for every object in the objects list
		
		if (isTangible) {
			for (massive current_pObject : objects) {
				
				try {
					if ( (! equals(current_pObject) ) && (((massive) current_pObject).getIsTangible()) && (isTangible) ) ((massive) current_pObject).checkForCollision(this,objects);
				}catch(ClassCastException c) {
					try {
						c.printStackTrace();
					}catch(ClassCastException q) {
						System.out.println("not drawable");
					}
				}
			}	
		}
	}
	
	public boolean getIsAffectedByBorder() {
		return affectedByBorder;
	}
	

	public Object checkForCollision1(massive current_object,ArrayList<massive> objects) {
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
	
	public void applyImpulse(double r, double theta, double z_magn, double time,String units) { //applies a force to the object
		zAccel += z_magn/mass; //updating the zAccel with the z component of the force vector
		
		//calculating the components of the force vectors
		double xComponent = r * Math.cos(theta);
		double yComponent = r * Math.sin(theta);
		
		//updating the x and y Accel with the respective x and y components of the force vector
		xAccel += xComponent/mass; 
		yAccel += yComponent/mass;
		drawer.add(new ForceTimer(drawer,time,units,xComponent,yComponent,z_magn,this));
	}
	
	public void applyComponentForce(double x_magn, double y_magn, double z_magn) {
		xAccel += x_magn/mass; //updating the xAccel with the x component of the force vector
		yAccel += y_magn/mass; //updating the yAccel with the y component of the force vector
		zAccel += z_magn/mass; //updating the zAccel with the z component of the force vector
	}
	
	public void applyComponentImpulse(double x_magn, double y_magn, double z_magn, double time,String units) {
		xAccel += x_magn/mass; //updating the xAccel with the x component of the force vector
		yAccel += y_magn/mass; //updating the yAccel with the y component of the force vector
		zAccel += z_magn/mass; //updating the zAccel with the z component of the force vector
		drawer.add(new ForceTimer(drawer,time,units,x_magn,y_magn,z_magn,this));
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
	
	protected double[] calculateDeflectionAngle(Physics_3DPolygon current_object) { //this shouldn't have to get overridden
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
	
	@Override
	public void setFrictionCoefficient(double frictionCoeff) {
		friction_coefficient = frictionCoeff;
		
	}


	@Override
	public double getAngularVelocityX() {
		return angularVelocityX;
	}

	@Override
	public double getAngularVelocityY() {
		return angularVelocityY;
	}

	@Override
	public double getAngularVelocityZ() {
		return angularVelocityZ;
	}

	@Override
	public double getAngularAccelX() {
		return angularAccelX;
	}

	@Override
	public double getAngularAccelY() {
		return angularAccelY;
	}

	@Override
	public double getAngularAccelZ() {
		return angularAccelZ;
	}

	@Override
	public boolean getIsRotatable() {
		return isRotatable;
	}

	@Override
	public double getElasticity() {
		return elasticity;
	}
	
}

