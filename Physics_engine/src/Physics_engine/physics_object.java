package Physics_engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.util.ArrayList;


public abstract class physics_object implements physics_engine_compatible{
	
	public enum object_types {physics_object,drawable,movable,massive,pointed,rotatable};

	
	public boolean  isTangible = true,   affectedByBorder = true;
	//				|will the object	|if it's anchored  | will the object be affected |  can the object be rotated?
	//				|collide with others|it  wont move	   | by a border_bounce?         |   
	
	public String name = "unNamed"; //the name of the object
	protected int x,y,z; 
	protected double xReal,yReal,zReal; 

	protected double centerX,centerY, centerZ,xSize, ySize, zSize,xSizeAppearance, ySizeAppearance, zSizeAppearance, axisThetaXY = 0,axisThetaZX = 0, axisThetaZY = 0,mass,friction_coefficient;
	
	public enum pointOfRotationPlaces {center,parentCenter,parentsPlace,custom};

	
	
	private Polygon polyXY, polyZY;
	
	private Area areaXY,areaZY;
	
	
	
	private physics_object parent_object; //this object will move and act relative to it's parent object (usefull for making complex objects out of multiple shapes)
	private boolean hasParentObject = false; //if the object is linked to a parent object
	
	protected Coordinate_Axis axis;
	
	public enum faces {left,right,top,bottom,far,near,none};
	
	
	
	

	
	public void checkForCollisions(ArrayList<physics_object> objects) { // calls the checkForCollision method for every object in the objects list
		
		if (isTangible) {
			for (physics_object current_pObject : objects) {
				
				if ( (! equals(current_pObject) ) && (current_pObject.isTangible) && (isTangible) ) current_pObject.checkForCollision(this,objects);
	
			}	
		}
	}
	
	public void checkForCollision(physics_object current_physics_object,ArrayList<physics_object> objects) { //generic checkForCollisions method that is overriden by all tangible pObjects
		
			if (Settings.collision_algorithm == 5) {
				updatePointXsYsAndZs();
				updateAreas();
				
				point cPoint;
				double force, reflec_const, time = 0.1;	
				
				
				for (int i = 0; i < current_physics_object.points.length; i++) {
	
					cPoint = current_physics_object.points[i];
		
					
					if (cPoint.isIn(this)) {
						System.out.println(name + "BOOM!" + current_physics_object.getObjectName());
						isCollided(current_physics_object,faces.none);
						Vector reflectionVector = new Vector(center,cPoint);
						reflec_const = reflectionVector.r;
						Vector momentumVector = new Vector(current_physics_object.xSpeed,current_physics_object.ySpeed,current_physics_object.zSpeed);
						force = momentumVector.r / time;
						applyComponentForce(force * reflectionVector.getXComponent()/reflec_const ,force  * reflectionVector.getYComponent()/reflec_const,force * reflectionVector.getZComponent()/reflec_const,time);
					}
					
				}
				
			}else {
				checkForCollision1(current_physics_object, objects);
			}
			
		
	}
	
	public Object checkForCollision1(physics_object current_object,ArrayList<physics_object> objects) {
	//for v1-4 collision
		return null;
	}

	public void Update(ArrayList<physics_object> objects) {	//updates all of the objects values 	
		if (hasParentObject) {
			xSpeed = parent_object.xSpeed;
			ySpeed = parent_object.ySpeed;
			zSpeed = parent_object.zSpeed;
			xAccel = parent_object.xAccel;
			yAccel = parent_object.yAccel;
			zAccel = parent_object.zAccel;
			angularVelocityX = parent_object.angularVelocityX;
			angularVelocityY = parent_object.angularVelocityY;
			angularVelocityZ = parent_object.angularVelocityZ;
			axisThetaXY = parent_object.axisThetaXY;
			axisThetaZX = parent_object.axisThetaZX;
			axisThetaZY = parent_object.axisThetaZY;
			mass = parent_object.mass;
			friction_coefficient = parent_object.friction_coefficient;
			
			//update real pos
			centerX += (xSpeed);
			centerY += (ySpeed);
			centerZ += (zSpeed);
			
			xRotation += angularVelocityX;
			yRotation += angularVelocityY;
			zRotation += angularVelocityZ;
			
			//updating relative values
			updateSize(); //calculate the size of the object based on how far away it is
			updatePos();//update the xReal,yReal,zReal and x,y,z values
			updatePoints();//set the points based on the x and y values and calculate rotation
			updateCenter(); //update the  "center" point
			
			
			
			try {
				axis.UpdateAxis();
			}catch(NullPointerException e) {}
			
		}else {
			if (! isAnchored) { //updating the pos and speed based on the accel
				//update speed
				xSpeed += xAccel;
				ySpeed += yAccel;
				zSpeed += zAccel;
				
				checkForCollisions(objects);
				
				//update real pos
				centerX += xSpeed;
				centerY += ySpeed;
				centerZ += zSpeed;
			}else { //object is anchored and shouldn't move
				xSpeed = 0;
				ySpeed = 0;
				zSpeed = 0;
			}
			
			if (isRotatable) { //rotation shouldn't be updated if the object isn't rotatable
				//updating angular velocity
				angularVelocityX += angularAccelX;
				angularVelocityY += angularAccelY;
				angularVelocityZ += angularAccelZ;
				
				//updating rotation
				xRotation += angularVelocityX;
				yRotation += angularVelocityY;
				zRotation += angularVelocityZ;
				
			}
				
			
				
			//updating relative values
			updateSize(); //calculate the size of the object based on how far away it is
			updatePos();//update the xReal,yReal,zReal and x,y,z values
			updatePoints();//set the points based on the x and y values and calculate rotation
			updateCenter(); //update the  "center" point
			
			try {
				axis.UpdateAxis();
			}catch(NullPointerException e) {}
		}
		
		updatePointXsYsAndZs();
		
		secondaryUpdate(); //this is a subclass-specific update method that can be overridden to allow for each child class to be updated differently
	}
	
	
	public void Update(ArrayList<physics_object> objects,double frames) { //frames is the number of frames the object should update (can be a decimal)

		if (hasParentObject) {
			xSpeed = parent_object.xSpeed;
			ySpeed = parent_object.ySpeed;
			zSpeed = parent_object.zSpeed;
			xAccel = parent_object.xAccel;
			yAccel = parent_object.yAccel;
			zAccel = parent_object.zAccel;
			angularVelocityX = parent_object.angularVelocityX;
			angularVelocityY = parent_object.angularVelocityY;
			angularVelocityZ = parent_object.angularVelocityZ;
			axisThetaXY = parent_object.axisThetaXY;
			axisThetaZX = parent_object.axisThetaZX;
			axisThetaZY = parent_object.axisThetaZY;
			mass = parent_object.mass;
			friction_coefficient = parent_object.friction_coefficient;
			
			//update real pos
			centerX += (xSpeed * frames);
			centerY += (ySpeed * frames);
			centerZ += (zSpeed * frames);
			
			//updating angular velocity
			angularVelocityX += (angularAccelX * frames);
			angularVelocityY += (angularAccelY * frames);
			angularVelocityZ += (angularAccelZ * frames);
			
			//updating rotation
			xRotation += (angularVelocityX * frames);
			yRotation += (angularVelocityY * frames);
			zRotation += (angularVelocityZ * frames);
			 
			//updating relative values
			updateSize(); //calculate the size of the object based on how far away it is
			updatePos();//update the xReal,yReal,zReal and x,y,z values
			updatePoints();//set the points based on the x and y values and calculate rotation
			updateCenter(); //update the  "center" point
			
			try {
				axis.UpdateAxis();
			}catch(NullPointerException e) {}
			
		}else {
			if (! isAnchored) { //updating the pos and speed based on the accel
				//update speed
				xSpeed += (xAccel * frames);
				ySpeed += (yAccel * frames);
				zSpeed += (zAccel * frames);
				
				checkForCollisions(objects);
				
				//update real pos
				centerX += (xSpeed * frames);
				centerY += (ySpeed * frames);
				centerZ += (zSpeed * frames);
			}else { //object is anchored and shouldn't move
				xSpeed = 0;
				ySpeed = 0;
				zSpeed = 0;
			}
			
			if (isRotatable) { //rotation shouldn't be updated if the object isn't rotatable
				//updating angular velocity
				angularVelocityX += (angularAccelX * frames);
				angularVelocityY += (angularAccelY * frames);
				angularVelocityZ += (angularAccelZ * frames);
				
				//updating rotation
				xRotation += (angularVelocityX * frames);
				yRotation += (angularVelocityY * frames);
				zRotation += (angularVelocityZ * frames);
			}	
			
			
				
			//updating relative values
			updateSize(); //calculate the size of the object based on how far away it is
			updatePos();//update the xReal,yReal,zReal and x,y,z values
			updatePoints();//set the points based on the x and y values and calculate rotation
			updateCenter(); //update the  "center" point
			
			try {
				axis.UpdateAxis();
			}catch(NullPointerException e) {}
		}
		
		updatePointXsYsAndZs();
		
		secondaryUpdate(); //this is a subclass-specific update method that can be overridden to allow for each child class to be updated differently
	}
	
	public void UpdateWithoutCollision(ArrayList<physics_object> objects) { //updates all of the objects values without checking for collisions
		
		if (hasParentObject) {
			xSpeed = parent_object.xSpeed;
			ySpeed = parent_object.ySpeed;
			zSpeed = parent_object.zSpeed;
			xAccel = parent_object.xAccel;
			yAccel = parent_object.yAccel;
			zAccel = parent_object.zAccel;
			angularVelocityX = parent_object.angularVelocityX;
			angularVelocityY = parent_object.angularVelocityY;
			angularVelocityZ = parent_object.angularVelocityZ;
			axisThetaXY = parent_object.axisThetaXY;
			axisThetaZX = parent_object.axisThetaZX;
			axisThetaZY = parent_object.axisThetaZY;
			mass = parent_object.mass;
			friction_coefficient = parent_object.friction_coefficient;
			
			//update real pos
			centerX += (xSpeed);
			centerY += (ySpeed);
			centerZ += (zSpeed);
			
			//updating relative values
			updateSize(); //calculate the size of the object based on how far away it is
			updatePos();//update the xReal,yReal,zReal and x,y,z values
			updatePoints();//set the points based on the x and y values and calculate rotation
			updateCenter(); //update the  "center" point
			
			try {
				axis.UpdateAxis();
			}catch(NullPointerException e) {}
			
		}else {
			if (! isAnchored) { //updating the pos and speed based on the accel
				//update speed
				xSpeed += xAccel;
				ySpeed += yAccel;
				zSpeed += zAccel;
				
				//update real pos
				centerX += xSpeed;
				centerY += ySpeed;
				centerZ += zSpeed;
			}else { //object is anchored and shouldn't move
				xSpeed = 0;
				ySpeed = 0;
				zSpeed = 0;
			}
			
			if (isRotatable) { //rotation shouldn't be updated if the object isn't rotatable
				//updating angular velocity
				angularVelocityX += angularAccelX;
				angularVelocityY += angularAccelY;
				angularVelocityZ += angularAccelZ;
			
				//updating rotation
				xRotation += angularVelocityX;
				yRotation += angularVelocityY;
				zRotation += angularVelocityZ;	
			}
				
			//updating relative values
			updateSize(); //calculate the size of the object based on how far away it is
			updatePos();//update the xReal,yReal,zReal and x,y,z values
			updatePoints();//set the points based on the x and y values and calculate rotation
			updateCenter(); //update the  "center" point
			
			try {
				axis.UpdateAxis();
			}catch(NullPointerException e) {}
		}
		
		updatePointXsYsAndZs();
		
		secondaryUpdate(); //this is a subclass-specific update method that can be overridden to allow for each child class to be updated differently
	}
	
	protected void UpdateWithoutCollision(ArrayList<physics_object> objects, double frames) { //updates all of the objects values without checking for collisions
		//frame fraction is the number of frames we should update by (including by a fraction of a frame) for instance if you only want to update the object for half a frame of time, frames would be 0.5
		
		if (hasParentObject) {
			xSpeed = parent_object.xSpeed;
			ySpeed = parent_object.ySpeed;
			zSpeed = parent_object.zSpeed;
			xAccel = parent_object.xAccel;
			yAccel = parent_object.yAccel;
			zAccel = parent_object.zAccel;
			angularVelocityX = parent_object.angularVelocityX;
			angularVelocityY = parent_object.angularVelocityY;
			angularVelocityZ = parent_object.angularVelocityZ;
			axisThetaXY = parent_object.axisThetaXY;
			axisThetaZX = parent_object.axisThetaZX;
			axisThetaZY = parent_object.axisThetaZY;
			mass = parent_object.mass;
			friction_coefficient = parent_object.friction_coefficient;
			
			//update real pos
			centerX += (xSpeed * frames);
			centerY += (ySpeed * frames);
			centerZ += (zSpeed * frames);
			
			//updating relative values
			updatePoints(); //does the whole rotation thing and updates the positions of the point
			updatePos();//update int values of pos
			updateCenter(); //calculates where the center of the object is
			updateSize(); //calculates the size of the object
			
			
			try {
				axis.UpdateAxis();
			}catch(NullPointerException e) {}
			
		}else {
			if (! isAnchored) { //updating the pos and speed based on the accel
				//update speed
				xSpeed += (xAccel*frames);
				ySpeed += (yAccel*frames);
				zSpeed += (zAccel*frames);
				
				//update real pos
				centerX += (xSpeed*frames);
				centerY += (ySpeed*frames);
				centerZ += (zSpeed*frames);
				
			}else { //object is anchored and shouldn't move
				xSpeed = 0;
				ySpeed = 0;
				zSpeed = 0;
			}
			
			if (isRotatable) {
				//updating angular velocity
				angularVelocityX += (angularAccelX * frames);
				angularVelocityY += (angularAccelY * frames);
				angularVelocityZ += (angularAccelZ * frames);
				
				//updating rotation
				xRotation += (angularVelocityX * frames);
				yRotation += (angularVelocityY * frames);
				zRotation += (angularVelocityZ * frames);
			}	
				
			
		

			updatePos();	//update int values of pos
			updatePoints();		 //does the whole rotation thing and updates the positions of the point
			updateCenter();//calculates where the center of the object is
			updateSize();//calculates the size of the object
			
			try {
				axis.UpdateAxis();
			}catch(NullPointerException e) {}			
		}
		
		updatePointXsYsAndZs();
		
		secondaryUpdate(); //this is a subclass-specific update method that can be overridden to allow for each child class to be updated differently
	}
	
	protected void secondaryUpdate() {
		//this is a subclass-specific update method that can be overridden to allow for each child class to be updated differently
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
		object_draw.objects.add(new ForceTimer(time,xComponent,yComponent,z_magn,this));
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
		object_draw.objects.add(new ForceTimer(time,x_magn,y_magn,z_magn,this));
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
	
	protected double[] calculateDeflectionAngle(physics_object current_object) { //this shouldn't have to get overridden
		double angleOfApproach = Math.atan(current_object.ySpeed / current_object.xSpeed);
		return calculateDeflectionAngle(angleOfApproach,current_object.zSpeed);
		
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
	
	public double distance(point point1, point point2) {
		return Math.sqrt( Math.pow(( point2.getXReal() - point1.getXReal() ), 2) + Math.pow(( point2.getYReal() - point1.getYReal() ), 2) + Math.pow(( point2.getZReal() - point1.getZReal() ), 2) );
	}


	public void isCollided(physics_object object, faces side) { //method that gets called when the object hits something. Useful for things like spikes or bullets in a game
		
	}
	
	public void setPos(double centerX1,double centerY1,double centerZ1) {
		centerX = centerX1;
		centerY = centerY1;
		centerZ = centerZ1;
		updatePos();
	}
	
	public void setPoints(point[] points1) {
		points = points1;
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
	
	public void setSize(double xSize1,double ySize1,double zSize1) { //sets the size of the object
		xSize = xSize1;
		ySize = ySize1;
		zSize = zSize1;
		updateCenter();
		updateSize();
	}
	
	public void setMass(double mass1) { //update the mass of the object (kg)
		mass = mass1;
	}
	
	public void setName(String new_name, int i) { //sets the name of the object (this will be shown if Settings.displayNames is true)
//		setName(new_name); //updates the name of the component
		name = new_name;
		
		try {
			axis.setName(name + "_axis", i); //updates the name of this object's coordinate axis (if it has one)
		}catch(NullPointerException e) {}	
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
	


	public void setColor(Color color1) {
		color = color1;
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
	
	private void updatePointXsYsAndZs() {
		
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
		}
		
		
		
		try {
			center.setPos(centerX, centerY,centerZ);
		}catch(NullPointerException n) {
			center = new point(centerX,centerY,centerZ);
		}
		
	}
	
	protected void updateCenter() {
		try {
			center.setPos(centerX, centerY,centerZ);
		}catch(NullPointerException n) {
			center = new point(centerX,centerY,centerZ);
		}
	}
	
	protected void updatePos() {
		xReal = centerX - xSize/2;
		yReal = centerY - ySize/2;
		zReal = centerZ - zSize/2;
		x = (int) Math.round(xReal);
		y = (int) Math.round(yReal);
		z = (int) Math.round(zReal);
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
	
	protected void updateAreas() {
		updatePolygons();
		
		areaXY = new Area(polyXY);
		areaZY = new Area(polyZY);
		
	}
	
	public String getObjectName() { //gets the name of the object
		return name;
	}


	protected void updateSize() {
		//as z gets bigger, the object gets further away from the viewer, and the object appears to be smaller
		xSizeAppearance = (Settings.distanceFromScreen * xSize ) / (zReal + Settings.distanceFromScreen);
		ySizeAppearance = (Settings.distanceFromScreen * ySize ) / (zReal + Settings.distanceFromScreen);
		zSizeAppearance = (Settings.distanceFromScreen * zSize ) / (zReal + Settings.distanceFromScreen);		
	}
	
	
	//Getter methods
	public point[] getPoints() {
		return points;
	}
	
	public double getCenterX() { //finds the x coordinate of the object's center
		return centerX;
	}


	public double getCenterY() { //finds the y coordinate of the object's center
		return centerY;
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


	public double getAxisThetaXY() {
		return axisThetaXY;
	}
	
	public double getAxisThetaZX() {
		return axisThetaZX;
	}
	
	public double getAxisThetaZY() {
		return axisThetaZY;
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
	
	
	public physics_object copy(physics_object new_object)  { //copies this physics_object to another
		//this method exists because you cannot do object1 = object2 because that will just tell object1 to point to object2 and not create a new object. (Aliasing)
	
		//booleans
		new_object.isVisible = isVisible;
		new_object.isTangible = isTangible;
		new_object.isAnchored = isAnchored;
		new_object.isFilled = isFilled;
		new_object.affectedByBorder = affectedByBorder;
		new_object.hasParentObject = hasParentObject;
		
		//ints
		new_object.x = x;
		new_object.y = y;
		new_object.xSize = xSize;
		new_object.ySize = ySize;
		
		
		//doubles
		new_object.xReal = xReal;
		new_object.yReal = yReal;
		new_object.zReal = zReal;
		new_object.centerX = centerX;
		new_object.centerY = centerY;
		new_object.centerZ = centerZ;
		new_object.xSpeed = xSpeed;
		new_object.ySpeed = ySpeed;
		new_object.zSpeed = zSpeed;
		new_object.xAccel = xAccel;
		new_object.yAccel = yAccel;
		new_object.zAccel = zAccel;
		new_object.xRotation = xRotation;
		new_object.yRotation = yRotation;
		new_object.zRotation = zRotation;
		new_object.axisThetaXY = axisThetaXY;
		new_object.axisThetaZX = axisThetaZX;
		new_object.axisThetaZY = axisThetaZY;
		new_object.mass = mass;
		new_object.friction_coefficient = friction_coefficient;
		new_object.xSizeAppearance = xSizeAppearance;
		new_object.ySizeAppearance = ySizeAppearance;
		new_object.zSizeAppearance = zSizeAppearance;
		
		
		//strings
		new_object.name = name;
		
		//other
		new_object.drawMethod = drawMethod;
		new_object.axis = axis;
		new_object.parent_object = parent_object;
		
		//this for loop probably doesn't work as it hasn't been tested yet
		for (int i=0; i < points.length; i++) {
			point new_point = new point(null, i);
			new_point = (point) points[i].copy(new_object);
			new_object.points[i] = new_point;
		}
		
		
		new_object.pointRenderOrder = pointRenderOrder;
		new_object.color = color;
		
		return new_object;
	}

}

