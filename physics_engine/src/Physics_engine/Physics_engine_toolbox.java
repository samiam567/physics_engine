package Physics_engine;

import java.awt.Color;
import java.awt.Font;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.colorchooser.ColorSelectionModel;

import Physics_engine.Physics_engine_toolbox.pointOfRotationPlaces;
import calculator.Settings;

public class Physics_engine_toolbox {
	public enum pointOfRotationPlaces {center,parentCenter,parentsPlace,custom};
	public enum faces {left,right,top,bottom,far,near,none};
	public static String[] colorNames = {"black","blue","cyan","gray","green","magenta","orange","pink","red","white","yellow"};
	public static Color[] colors = {Color.black,Color.blue,Color.cyan,Color.GRAY,Color.green,Color.MAGENTA,Color.orange,Color.pink,Color.red,Color.white,Color.YELLOW};
	
	public static String[] typesOfObjects = {"square","rectange","box","triangle","circle","sphere","free-form"};
	
	public static String[] stuffToDo = {"position","speed","acceleration","rotation","angular velocity","angular acceleration","color","size","friction Coefficient","mass","name","delete"};

	public static Font bigFont = new Font("TimesRoman", Font.BOLD, (int) (Math.sqrt(Math.pow(Settings.width, 2) + Math.sqrt(Math.pow(Settings.height, 2))) / 20  ));
	
	public static Font littleFont = new Font("TimesRoman", Font.PLAIN, 12);
	
	public static void Update(physics_engine_compatible current_object,double frames) { //frames is the number of frames the object should update (can be a decimal)
		
		//use try-catch to update each object depending on which interfaces it implements 
		
		
		current_object.Update(frames);

		
		try {	
			
	
			if (((Physics_drawable) current_object).hasParentObject()) {
				Physics_drawable parent_object = (Physics_drawable) ((Physics_drawable)current_object).getParentObject();
				
				((movable) current_object).setSpeed(parent_object.getXSpeed(),((movable) current_object).getYSpeed(), ((movable) current_object).getZSpeed());

				((Physics_drawable)current_object).xAccel = parent_object.xAccel;
				((Physics_drawable)current_object).yAccel = parent_object.yAccel;
				((Physics_drawable)current_object).zAccel = parent_object.zAccel;
				
				
				try {
					
				
					
					((massive)current_object).setMass(((massive)parent_object).getMass());
					((massive)current_object).setFrictionCoefficient(((massive)parent_object).getFrictionCoefficient());
				
				}catch(ClassCastException c) {
					
				}	
					//updating rectangular angular velocity
					((rotatable)current_object).setAngularVelocity(((rotatable)current_object).getAngularVelocityX() + ((massive)current_object).getAngularAccelX() * frames,((rotatable)current_object).getAngularVelocityY() + ((massive)current_object).getAngularAccelY() * frames,((rotatable)current_object).getAngularVelocityZ() + ((rotatable)current_object).getAngularAccelZ() * frames );

					
					//updating rectangular rotation
					((rotatable)current_object).setRotation( 
							((rotatable)current_object).getXRotation() +  ((rotatable)current_object).getAngularVelocityX() * frames,
							((rotatable)current_object).getYRotation() +  ((rotatable)current_object).getAngularVelocityY() * frames,
							((rotatable)current_object).getZRotation() +  ((rotatable)current_object).getAngularVelocityZ() * frames
					);
					
					//updating vector angular velocity
					((rotatable)current_object).getVectorAngularVelocity().add(Vector3D.multiply(((rotatable)current_object).getVectorAngularAccel(),frames));
					
					//updating vector rotation
					((rotatable)current_object).getVectorRotation().add(Vector3D.multiply(((rotatable)current_object).getVectorAngularVelocity(),frames) );
					
					if (((drawable)current_object).getIsInFrame()) ((massive)current_object).updatePoints();//set the points based on the x and y values and calculate rotation
	
				
				
				//update real pos
				((Physics_drawable)current_object).centerX += (((Physics_drawable)current_object).xSpeed * frames);
				((Physics_drawable)current_object).centerY += (((Physics_drawable)current_object).ySpeed * frames);
				((Physics_drawable)current_object).centerZ += (((Physics_drawable)current_object).zSpeed * frames);
				
			
				 
				//updating relative values
				((Physics_drawable)current_object).updateSize(); //calculate the size of the object based on how far away it is
				((Physics_drawable)current_object).updatePos();//update the xReal,yReal,zReal and x,y,z values
				((Physics_drawable)current_object).updateCenter(); //update the  "center" point
				
				
			}else {
		
				//rotatable
				try {
				
					if (((rotatable)current_object).getIsRotatable()) { //rotation shouldn't be updated if the object isn't rotatable
		
						
						//updating angular velocity
						((rotatable)current_object).setAngularVelocity(((rotatable)current_object).getAngularVelocityX() + ((rotatable)current_object).getAngularAccelX() * frames,((rotatable)current_object).getAngularVelocityY() + ((rotatable)current_object).getAngularAccelY() * frames,((rotatable)current_object).getAngularVelocityZ() + ((rotatable)current_object).getAngularAccelZ() * frames );
					
						
						//updating rotation
						((rotatable)current_object).setRotation( 
								((rotatable)current_object).getXRotation() +  ((rotatable)current_object).getAngularVelocityX() * frames,
								((rotatable)current_object).getYRotation() +  ((rotatable)current_object).getAngularVelocityY() * frames,
								((rotatable)current_object).getZRotation() +  ((rotatable)current_object).getAngularVelocityZ() * frames
						);
						
						//updating vector angular velocity
						((rotatable)current_object).getVectorAngularVelocity().add(Vector3D.multiply(((rotatable)current_object).getVectorAngularAccel(),frames));
						
						//updating vector rotation
						((rotatable)current_object).getVectorRotation().add(Vector3D.multiply(((rotatable)current_object).getVectorAngularVelocity(),frames) );
					
						if (((drawable)current_object).getIsInFrame())  ((massive)current_object).updatePoints();//set the points based on the x and y values and calculate rotation
		
					
				
					}	
					
					
				
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
		
	
		
		
		try {
			current_object = (massive) current_object;
			
			((massive) current_object).checkForCollisions(current_object.getDrawer().getTangibles());
			
			((pointed) current_object).updatePointXsYsAndZs();
			((pointed) current_object).updateAreas();

			
			((pointed) current_object).updatePoints();
			
			

		}catch(ClassCastException c) {
	//		System.out.println(((drawable) current_object).getObjectName() + " is not massive");
		}catch(NullPointerException n) {
	//		System.out.println("this object doesn't exist (PhysTools checkForCollis)");
			return;
		}
		
		
		//these are subclass-specific update methods that can be overridden to allow for each child class to be updated differently
		current_object.secondaryUpdate(); 
		current_object.tertiaryUpdate();
		current_object.frameUpdate2(frames);
		current_object.frameUpdate3(frames);
		
	}
	
	public static double getDoubleFromUser(JFrame messageAnchor) {
		
		boolean error = false;
		double num = 0;
		
		try {
			String numStr = JOptionPane.showInputDialog(messageAnchor,"what number?");
			num = Double.parseDouble(numStr);
		}catch(NumberFormatException n) {
			error = true;
			while (error) {
				try {
					String numStr = JOptionPane.showInputDialog(messageAnchor,"Invalid Number");
					num = Double.parseDouble(numStr);
					error = false;
				}catch(NumberFormatException t) {
					error = true;
				}
			}
		}
		
		
		return num;
	}
	
	public static double getDoubleFromUser(JFrame messageAnchor, String message) {
		
		boolean error = false;
		double num = 0;
		
		try {
			String numStr = JOptionPane.showInputDialog(messageAnchor,message);
			num = Double.parseDouble(numStr);
		}catch(NumberFormatException n) {
			error = true;
			while (error) {
				try {
					String numStr = JOptionPane.showInputDialog(messageAnchor,"Invalid Number\n" + message);
					num = Double.parseDouble(numStr);
					error = false;
				}catch(NumberFormatException t) {
					error = true;
				}
			}
		}

		return num;
	}
	
	
	
public static int getIntegerFromUser(JFrame messageAnchor) {
		
		boolean error = false;
		int num = 0;
		
		try {
			String numStr = JOptionPane.showInputDialog(messageAnchor,"what number?");
			num = Integer.parseInt(numStr);
		}catch(NumberFormatException n) {
			error = true;
			while (error) {
				try {
					String numStr = JOptionPane.showInputDialog(messageAnchor,"Invalid Number\n" + "what number?");
					num = Integer.parseInt(numStr);
					error = false;
				}catch(NumberFormatException t) {
					error = true;
				}
			}
		}

		return num;
	}
	


public static int getIntegerFromUser(JFrame messageAnchor, String message) {
		
		boolean error = false;
		int num = 0;
		
		try {
			String numStr = JOptionPane.showInputDialog(messageAnchor,message);
			num = Integer.parseInt(numStr);
		}catch(NumberFormatException n) {
			error = true;
			while (error) {
				try {
					String numStr = JOptionPane.showInputDialog(messageAnchor,"Invalid Number\n" + message);
					num = Integer.parseInt(numStr);
					error = false;
				}catch(NumberFormatException t) {
					error = true;
				}
			}
		}

		return num;
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
	
	public static physics_object loadObjectFromFile(String fileName) {
		
		try {
			System.out.println("Loading object from file...");
			
			
			ObjectInputStream loader = new ObjectInputStream(new FileInputStream(fileName));
			
			
			physics_object readOb = (physics_object) loader.readObject();
			
			loader.close();
			
			System.out.println("load successful");
			
			return readOb;
			
		}catch(InvalidClassException e) {
			System.out.println("Corrupted Save_file : " + fileName); 
		}catch(EOFException e) {
			System.out.println("Corrupted Save_file : " + fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(fileName + " not found");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("load failed");
		
		return null;
	}



}
