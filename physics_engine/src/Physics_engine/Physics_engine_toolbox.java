package Physics_engine;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.colorchooser.ColorSelectionModel;

public class Physics_engine_toolbox {
	public enum pointOfRotationPlaces {center,parentCenter,parentsPlace,custom};
	public enum faces {left,right,top,bottom,far,near,none};
	public static String[] colorNames = {"black","blue","cyan","gray","green","magenta","orange","pink","red","white","yellow"};
	
	
	public static void Update(physics_engine_compatible current_object,double frames) { //frames is the number of frames the object should update (can be a decimal)
		
		//use try-catch to update each object depending on which interfaces it implements 
		
		try {
			current_object = (massive) current_object;
			
			((massive) current_object).checkForCollisions(current_object.getDrawer().getTangibles());
			
			((pointed) current_object).updatePoints();
			

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
					((massive)current_object).setAngularVelocity(((massive)parent_object).getAngularVelocityX(), ((massive)parent_object).getAngularVelocityY(),((massive)parent_object).getAngularVelocityZ());
	
					((massive)current_object).setMass(((massive)parent_object).getMass());
					((massive)current_object).setFrictionCoefficient(((massive)parent_object).getFrictionCoefficient());
				
					//updating angular velocity
					((massive)current_object).setAngularVelocity(((massive)current_object).getAngularVelocityX() + ((massive)current_object).getAngularAccelX() * frames,((massive)current_object).getAngularVelocityY() + ((massive)current_object).getAngularAccelY() * frames,((massive)current_object).getAngularVelocityZ() + ((massive)current_object).getAngularAccelZ() * frames );

					
					//updating rotation
					((massive)current_object).setRotation( 
							((massive)current_object).getXRotation() +  ((massive)current_object).getAngularVelocityX() * frames,
							((massive)current_object).getYRotation() +  ((massive)current_object).getAngularVelocityY() * frames,
							((massive)current_object).getZRotation() +  ((massive)current_object).getAngularVelocityZ() * frames
					);
					
					((massive)current_object).updatePoints();//set the points based on the x and y values and calculate rotation
					
					
					
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
				
					if (((massive)current_object).getIsRotatable()) { //rotation shouldn't be updated if the object isn't rotatable
						
						
						//updating angular velocity
						((massive)current_object).setAngularVelocity(((massive)current_object).getAngularVelocityX() + ((massive)current_object).getAngularAccelX() * frames,((massive)current_object).getAngularVelocityY() + ((massive)current_object).getAngularAccelY() * frames,((massive)current_object).getAngularVelocityZ() + ((massive)current_object).getAngularAccelZ() * frames );

						
						//updating rotation
						((massive)current_object).setRotation( 
								((massive)current_object).getXRotation() +  ((massive)current_object).getAngularVelocityX() * frames,
								((massive)current_object).getYRotation() +  ((massive)current_object).getAngularVelocityY() * frames,
								((massive)current_object).getZRotation() +  ((massive)current_object).getAngularVelocityZ() * frames
						);
						
						((massive) current_object).updatePointXsYsAndZs();
					}	
					
					((massive)current_object).updatePoints();//set the points based on the x and y values and calculate rotation
					
			
				
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
//			System.out.println("catch: " + current_object.getObjectName());
		}
		
		//these are subclass-specific update methods that can be overridden to allow for each child class to be updated differently
		current_object.secondaryUpdate(); 
		current_object.tertiaryUpdate();
		current_object.frameUpdate2(frames);
		current_object.frameUpdate3(frames);
		
	}
	
	public static Color getColorFromUser(Physics_frame frame) {
		Color color;
		String colorStr = (String) JOptionPane.showInputDialog(frame, "What Color?", "Color Select", 3, null, colorNames, null);
		
		switch(colorStr) {
			case("black"):
				color = Color.black;
				break;
				
			case("blue"):
				color = Color.blue;
				break;
				
			case("cyan"):
				color = Color.cyan;
				break;
				
			case("gray"):
				color = Color.gray;
				break;
				
			case("green"):
				color = Color.green;
				break;
				
			case("magenta"):
				color = Color.magenta;
				break;
				
			case("orange"):
				color = Color.orange;
				break;
				
			case("pink"):
				color = Color.pink;
				break;
				
			case("red"):
				color = Color.red;
				break;
				
			case("white"):
				color = Color.white;
				break;
				
			case("yellow"):
				color = Color.yellow;
				break;
				
			default:
				color = Color.WHITE;
				Exception colorEx = new Exception("ERROR: Not a valid color: " + colorStr);
				colorEx.printStackTrace();
				break;
		}
		
		return color;
	}
	
	public static double distance(point point1, point point2) {
		return Math.sqrt( Math.pow(( point2.getXReal() - point1.getXReal() ), 2) + Math.pow(( point2.getYReal() - point1.getYReal() ), 2) + Math.pow(( point2.getZReal() - point1.getZReal() ), 2) );
	}
	
}
