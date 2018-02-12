package Physics_engine;

import java.util.ArrayList;

public class Physics_engine_toolbox {
	public enum pointOfRotationPlaces {center,parentCenter,parentsPlace,custom};
	public enum faces {left,right,top,bottom,far,near,none};
	
	public static void Update(physics_engine_compatible current_object,double frames) { //frames is the number of frames the object should update (can be a decimal)
		
		//use try-catch to update each object depending on which interfaces it implements 
		
		try {
			current_object = (drawable) current_object;
			
			
			current_object = (physics_engine_compatible) current_object;
		}catch(ClassCastException c) {
		
		}
		
		
		try {
			current_object = (movable) current_object;
			
			if (! current_object.getIsAnchored()) { //updating the pos and speed based on the accel
				//update speed
				current_object.setSpeed((current_object.getXAccel() * frames),(current_object.getYAccel() * frames),(current_object.getZAccel() * frames));
	
				
				current_object.checkForCollisions(current_object.getDrawer().objects);
				
				//update pos
				current_object.centerX += (current_object.xSpeed * frames);
				current_object.centerY += (current_object.ySpeed * frames);
				current_object.centerZ += (current_object.zSpeed * frames);
			}else { //object is anchored and shouldn't move
				xSpeed = 0;
				ySpeed = 0;
				zSpeed = 0;
			}
			
			current_object = (physics_engine_compatible) current_object;
		}catch(ClassCastException c) {
		
		}
		
		try {
			current_object = (massive) current_object;
			
			
			current_object = (physics_engine_compatible) current_object;
		}catch(ClassCastException c) {
		
		}
		
		
		try {
			current_object = (pointed) current_object;
			
			
			current_object = (physics_engine_compatible) current_object;
		}catch(ClassCastException c) {
		
		}
		
		
		try {
			current_object = (rotatable) current_object;
			
			
			current_object = (physics_engine_compatible) current_object;
		}catch(ClassCastException c) {
		
		}
		
		
	
		
		
		
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
		
		current_object.updatePointXsYsAndZs();
		
		current_object.secondaryUpdate(); //this is a subclass-specific update method that can be overridden to allow for each child class to be updated differently
		current_object.tertiaryUpdate();
	}
	
	public static double distance(point point1, point point2) {
		return Math.sqrt( Math.pow(( point2.getXReal() - point1.getXReal() ), 2) + Math.pow(( point2.getYReal() - point1.getYReal() ), 2) + Math.pow(( point2.getZReal() - point1.getZReal() ), 2) );
	}
	
}