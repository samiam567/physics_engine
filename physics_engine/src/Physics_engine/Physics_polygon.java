package Physics_engine;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.util.ArrayList;

import Physics_engine.Physics_engine_toolbox.faces;
import Physics_engine.Physics_engine_toolbox.pointOfRotationPlaces;

public class Physics_polygon extends Physics_shape implements pointed, rotatable, massive {
	
	int[] pointXs = {}; //all of the x coordinates of the points in the object
	int[] pointYs = {}; //all of the y coordinates of the points in the object
	int[] pointZs = {}; //all of the y coordinates of the points in the object
	
	double[] pointXReals = {}; //all of the x coordinates of the points in the object
	double[] pointYReals = {}; //all of the y coordinates of the points in the object
	double[] pointZReals = {}; //all of the y coordinates of the points in the object
	
	protected int[] pointRenderOrder = {}; //the order in which lines will be drawn from point to point (for listedPointAlgorithm)
	
	//for massive
	protected double mass;
	double friction_coefficient;
		
	private Polygon polyXY, polyZY;
	private Area areaXY,areaZY;
	
	public double elasticity = Settings.elasticity;
	
	double xRotation=0,yRotation=0,zRotation=0,angularVelocityX=0, angularVelocityY=0, angularVelocityZ=0, angularAccelX=0, angularAccelY=0, angularAccelZ=0;
	public boolean isRotatable = true,isTangible = true, affectedByBorder = true;
	point pointOfRotation = null; //the point that the object rotates around
	pointOfRotationPlaces pointOfRotationPlace = pointOfRotationPlaces.center;  //the place that that point is
	
	public Physics_polygon(object_draw drawer1) {
		super(drawer1);
		points = new point[0]; //all of the points in the object
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


	
	public void updatePoints() { //creates a vector from the pointOfRotation to each point in the object			
		if (isRotatable){
			double r;

			Vector pointVector,rotateVecX,rotateVecY,rotateVecZ; //for algors 1-4
			Vector3D pointVector3D; //for algor 5
			point cPoint;
			 
			for (int i = 0; i < points.length ; i++) { //cycle through the points in the object
				cPoint = points[i];
				r = points[i].getR();
				
				if (Settings.rotationAlgorithm == 3) {
					pointVector = new Vector(drawer,r, cPoint.getThetaXY() + zRotation, cPoint.getThetaZX() + yRotation, cPoint.getThetaZY() + xRotation,"thetaZY",this);
					
					try {
						pointVector.setPos(pointOfRotation.getXReal(), pointOfRotation.getYReal(), pointOfRotation.getZReal());			
					}catch(NullPointerException n) {} //this will throw if the object has not been finished being constructed yet
					
					points[i].setPointVector(pointVector); //sets the vector AND updates the point's pos automatically
				}else if (Settings.rotationAlgorithm == 4) {
					r = cPoint.getR(); 
					
					//creating the rotation vectors
					rotateVecX = new Vector(drawer,r,cPoint.getThetaXY() + zRotation,0,0,"thetaZX");
					rotateVecY = new Vector(drawer,r,0,yRotation,0,"thetaZX");
					rotateVecZ = new Vector(drawer,r,0,0,cPoint.getThetaZY() + xRotation,"thetaZY");
					
					//add up the vectors
					pointVector = rotateVecX.vectorAdd(new Vector[] {rotateVecX,rotateVecY,rotateVecZ} );
					
					//set the postition of the pointvector
					try {
						pointVector.setPos(pointOfRotation.getXReal(), pointOfRotation.getYReal(), pointOfRotation.getZReal());			
					}catch(NullPointerException n) {} //this will throw if the object has not been finished being constructed yet
					
					
				
			//		drawer.add(pointVector); //this draws the pointVector on the screen (for debugging purposes)
					
					points[i].setPointVector(pointVector); //set the vector to the point
					
				}else if (Settings.rotationAlgorithm == 5) {
					pointVector3D = new Vector3D(drawer,cPoint.getR(),cPoint.getTheta() + yRotation,cPoint.getPhi() + xRotation);
					
					try {
						pointVector3D.setPos(pointOfRotation.getXReal(), pointOfRotation.getYReal(), pointOfRotation.getZReal());			
					}catch(NullPointerException n) {} //this will throw if the object has not been finished being constructed yet
					
					cPoint.setPointVector(pointVector3D);
				}
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
	
	protected void calculatePointValues() { 
		
		if (Settings.rotationAlgorithm < 5) {
			Vector tempVec;
			point cPoint;
			for (int i = 0; i < points.length; i++) {
				cPoint = points[i];
				
				cPoint.setId(i);
				
				try {
					tempVec = new Vector(drawer,pointOfRotation,cPoint);
					cPoint.setR(tempVec.getR());
				}catch(NullPointerException n) { //this will be caught if pointOfRotation doesn't exist yet.
					pointOfRotation = new point(drawer,centerX,centerY,centerZ); //create pointOfRotation and set it to the center of the object using the default method
					tempVec = new Vector(drawer,pointOfRotation,cPoint);
					cPoint.setR(tempVec.getR());
				}
				
				tempVec.setName(name + "_tempVec", 1);
				
	//			drawer.add(tempVec); // this will display the temp vecs
			
				//Physics_engine_toolbox.distance(cPoint,pointOfRotation)
				
				//tempVec.getR()
				
				
			
	//			cPoint.setAngle(tempVec.getThetaXY(), tempVec.getThetaZX(), tempVec.getThetaZY());  //this line is what makes this method not work!!
			}
	
		}else {
			Vector3D tempVec;
			point cPoint;
			for (int i = 0; i < points.length; i++) {
				cPoint = points[i];
				
				cPoint.setId(i);
				
				try {
					tempVec = new Vector3D(drawer,pointOfRotation,cPoint);
					cPoint.setR(tempVec.getR());
				}catch(NullPointerException n) { //this will be caught if pointOfRotation doesn't exist yet.
					pointOfRotation = new point(drawer,centerX,centerY,centerZ); //create pointOfRotation and set it to the center of the object using the default method
					tempVec = new Vector3D(drawer,pointOfRotation,cPoint);
					cPoint.setR(tempVec.getR());
				}
				
				tempVec.setName(name + "_tempVec", 1);
				
//				drawer.add(tempVec); // this will display the temp vecs
			
				//Physics_engine_toolbox.distance(cPoint,pointOfRotation)

				cPoint.setAngle(tempVec.getTheta(), tempVec.getPhi()); 
			}
		}
	}
	
	public void checkForCollision(massive current_physics_object,ArrayList<massive> objects) { //generic checkForCollisions method that is overriden by all tangible pObjects
		
		if (Settings.collision_algorithm == 5) {
			updatePointXsYsAndZs();
			updateAreas();
			
			point cPoint;
			double force, reflec_const, time = 1;	
			
			try {
				for (int i = 0; i < ((pointed) current_physics_object).getPoints().length; i++) {
					cPoint = ((pointed) current_physics_object).getPoints()[i];
		
					
					if (cPoint.isIn(this)) {
						
						try {
							current_physics_object = (border_bounce) current_physics_object;
						}catch(ClassCastException c) {
							
							System.out.println(name + " has hit " + current_physics_object.getObjectName());
							
							//perfectly inellastic
							/*
							Vector cOb_momentum_i = new Vector(drawer,current_physics_object.getXSpeed(),current_physics_object.getYSpeed(),current_physics_object.getZSpeed()); //create speed vector
							cOb_momentum_i.setR(cOb_momentum_i.getR() * current_physics_object.getMass()); //convert that speed vector to a momentum vector by multiplying by mass (momentum = m * v)
							cOb_momentum_i.setPos(cPoint.getX(), cPoint.getY(), cPoint.getZ());
							
							Vector momentum_vector_i = new Vector(drawer,xSpeed,ySpeed,zSpeed);
							momentum_vector_i.setR(momentum_vector_i.getR() * mass);
							
							Vector resultantVector = momentum_vector_i.vectorAdd(cOb_momentum_i);
							
							applyComponentImpulse(resultantVector.getXComponent(),resultantVector.getYComponent(),resultantVector.getZComponent(),1,"seconds");
							((Physics_polygon) current_physics_object).applyComponentImpulse(resultantVector.getXComponent(),resultantVector.getYComponent(),resultantVector.getZComponent(),1,"seconds");
							*/
							
							isCollided((physics_object) current_physics_object,Physics_engine_toolbox.faces.none);
							
							Vector cOb_momentum_i = new Vector(drawer,current_physics_object.getXSpeed(),current_physics_object.getYSpeed(),current_physics_object.getZSpeed()); //create speed vector
							cOb_momentum_i.setR(cOb_momentum_i.getR() * current_physics_object.getMass()); //convert that speed vector to a momentum vector by multiplying by mass (momentum = m * v)
							
							Vector momentum_vector_i = new Vector(drawer,xSpeed,ySpeed,zSpeed);
							momentum_vector_i.setR(momentum_vector_i.getR() * mass);
							
							Vector resultantVector = momentum_vector_i.vectorAdd(cOb_momentum_i);
							
							Vector reflectionVector = new Vector(drawer,center,((Physics_polygon) current_physics_object).getCenter());
							
							applyComponentImpulse(-(reflectionVector.getXComponent() / reflectionVector.getR()) / 0.1,0,0,0.1,"seconds");
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
					if ( (! equals(current_pObject) ) && (((Physics_polygon) current_pObject).getIsTangible()) && (isTangible) ) ((Physics_polygon) current_pObject).checkForCollision(this,objects);
				}catch(ClassCastException c) {
					try {
						System.out.println(((drawable) current_pObject).getObjectName() + " is not massive");
					}catch(ClassCastException q) {
						System.out.println("not drawable");
					}
				}
			}	
		}
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
