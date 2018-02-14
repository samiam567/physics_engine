package Physics_engine;

import java.util.ArrayList;

public class Physics_engine_toolbox {
	public enum pointOfRotationPlaces {center,parentCenter,parentsPlace,custom};
	public enum faces {left,right,top,bottom,far,near,none};
	
	public static void Update(physics_engine_compatible current_object,double frames) { //frames is the number of frames the object should update (can be a decimal)
		
		//use try-catch to update each object depending on which interfaces it implements 
		
		try {
			current_object = (massive) current_object;
			
			((massive) current_object).checkForCollisions(current_object.getDrawer().getTangibles());
			

		}catch(ClassCastException c) {
	//		System.out.println(((drawable) current_object).getObjectName() + " is not massive");
		}
		

		
		
		try {	
			if (((Physics_drawable) current_object).hasParentObject()) {
				Physics_drawable parent_object = (Physics_drawable) ((Physics_drawable)current_object).getParentObject();
				
				((movable) current_object).setSpeed(parent_object.getXSpeed(),((movable) current_object).getYSpeed(), ((movable) current_object).getZSpeed());

				((Physics_drawable)current_object).xAccel = parent_object.xAccel;
				((Physics_drawable)current_object).yAccel = parent_object.yAccel;
				((Physics_drawable)current_object).zAccel = parent_object.zAccel;
				
				try {
					((Physics_polygon)current_object).angularVelocityX = ((Physics_polygon)parent_object).angularVelocityX;
					((Physics_polygon)current_object).angularVelocityY = ((Physics_polygon)parent_object).angularVelocityY;
					((Physics_polygon)current_object).angularVelocityZ = ((Physics_polygon)parent_object).angularVelocityZ;
	
					((Physics_polygon)current_object).setMass(((Physics_polygon)parent_object).mass);
					((Physics_polygon)current_object).friction_coefficient = ((Physics_polygon)parent_object).friction_coefficient;
				
					//updating angular velocity
					((Physics_polygon)current_object).angularVelocityX += (((Physics_polygon)current_object).angularAccelX * frames);
					((Physics_polygon)current_object).angularVelocityY += (((Physics_polygon)current_object).angularAccelY * frames);
					((Physics_polygon)current_object).angularVelocityZ += (((Physics_polygon)current_object).angularAccelZ * frames);
					
					//updating rotation
					((Physics_polygon)current_object).xRotation += (((Physics_polygon)current_object).angularVelocityX * frames);
					((Physics_polygon)current_object).yRotation += (((Physics_polygon)current_object).angularVelocityY * frames);
					((Physics_polygon)current_object).zRotation += (((Physics_polygon)current_object).angularVelocityZ * frames);
					
					((Physics_polygon)current_object).updatePoints();//set the points based on the x and y values and calculate rotation
					
				}catch(ClassCastException c) {
					
				}
				
				//update real pos
				((Physics_drawable)current_object).centerX += (((Physics_drawable)current_object).xSpeed * frames);
				((Physics_drawable)current_object).centerY += (((Physics_drawable)current_object).ySpeed * frames);
				((Physics_drawable)current_object).centerZ += (((Physics_drawable)current_object).zSpeed * frames);
				
			
				 
				//updating relative values
				((Physics_drawable)current_object).updateSize(); //calculate the size of the object based on how far away it is
				((Physics_drawable)current_object).updatePos();//update the xReal,yReal,zReal and x,y,z values
				((Physics_drawable)current_object).updateCenter(); //update the  "center" point
				
				
			}else {
		
				//pointed
				try {
				
					if (((Physics_polygon)current_object).isRotatable) { //rotation shouldn't be updated if the object isn't rotatable
						//updating angular velocity
						((Physics_polygon)current_object).angularVelocityX += (((Physics_polygon)current_object).angularAccelX * frames);
						((Physics_polygon)current_object).angularVelocityY += (((Physics_polygon)current_object).angularAccelY * frames);
						((Physics_polygon)current_object).angularVelocityZ += (((Physics_polygon)current_object).angularAccelZ * frames);
						
						//updating rotation
						((Physics_polygon)current_object).xRotation += (((Physics_polygon)current_object).angularVelocityX * frames);
						((Physics_polygon)current_object).yRotation += (((Physics_polygon)current_object).angularVelocityY * frames);
						((Physics_polygon)current_object).zRotation += (((Physics_polygon)current_object).angularVelocityZ * frames);
						
						((Physics_polygon) current_object).updatePointXsYsAndZs();
					}	
					
					((Physics_polygon)current_object).updatePoints();//set the points based on the x and y values and calculate rotation
				
				}catch(ClassCastException c) {
					
				}
				
				
				///movable
				try {
					
					if (! ((movable) current_object).getIsAnchored()) { //updating the pos and speed based on the accel
						//update speed
						((Physics_drawable)current_object).xSpeed += ( ((Physics_drawable)current_object).xAccel * frames);
						((Physics_drawable)current_object).ySpeed += ( ((Physics_drawable)current_object).yAccel * frames);
						((Physics_drawable)current_object).zSpeed += ( ((Physics_drawable)current_object).zAccel * frames);
						
						//update pos
						((movable) current_object).setCenter(((Physics_drawable) current_object).getCenterX() + (((movable) current_object).getXSpeed() * frames),((Physics_drawable) current_object).getCenterY() + (((movable) current_object).getYSpeed() * frames),((Physics_drawable) current_object).getCenterZ() + (((movable) current_object).getZSpeed() * frames));

						
						
					}else { //object is anchored and shouldn't move
						((movable) current_object).setSpeed(0,0,0);
					}
					
					current_object = (physics_engine_compatible) current_object;
				}catch(ClassCastException c) {
				
				}
				
				
			
				//updating relative values
				((Physics_drawable)current_object).updateSize(); //calculate the size of the object based on how far away it is
				((Physics_drawable)current_object).updatePos();//update the xReal,yReal,zReal and x,y,z values
				
				((Physics_drawable)current_object).updateCenter(); //update the  "center" point
				
			
			}
			
			
			
			current_object = (physics_engine_compatible) current_object;
		}catch(ClassCastException c) {
			System.out.println("catch: " + ((drawable) current_object).getObjectName());
		}
		
		
		current_object.secondaryUpdate(); //this is a subclass-specific update method that can be overridden to allow for each child class to be updated differently
		current_object.tertiaryUpdate();
		
	}
	
	public static double distance(point point1, point point2) {
		return Math.sqrt( Math.pow(( point2.getXReal() - point1.getXReal() ), 2) + Math.pow(( point2.getYReal() - point1.getYReal() ), 2) + Math.pow(( point2.getZReal() - point1.getZReal() ), 2) );
	}
	
}
