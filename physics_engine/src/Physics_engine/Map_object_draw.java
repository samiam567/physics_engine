package Physics_engine;

import java.awt.Graphics;



public class Map_object_draw extends object_draw implements resizable { //uses a frame to create a mini-map of a object_draw environment **This should not be added to an object_draw object**
	private object_draw drawerToMap;
	private Physics_drawable camera_center;
	private int frameXSize,frameYSize,xSize,ySize;
	private double centerXCoordOfMapPlace,centerYCoordOfMapPlace;
	
	public Map_object_draw(Physics_frame frame1,object_draw drawerToMap1,Physics_drawable camera_center1, int xSize1, int ySize1) {
		super(frame1);
		drawerToMap = drawerToMap1;
		camera_center = camera_center1;
		xSize = xSize1;
		ySize = ySize1;
		frameXSize = frame.getWidth();
		frameYSize = frame.getHeight();
		frame.setTitle(drawerToMap.frame.getTitle() + " MiniMap");
		centerXCoordOfMapPlace = camera_center.getX();
		centerYCoordOfMapPlace = camera_center.getY();
	}
	
	public void paint(Graphics page) {
		
		drawerToMap.pause(); //we don't want the drawer to map's updater and collision handling to influence the resizing and moving stuff that we're doing here
		
		double object_X_temp,object_Y_temp,object_xSize_temp,object_ySize_temp;
		
		double ratioX = frameXSize/xSize;
		double ratioY = frameYSize/ySize;
		
		double rotTempX,rotTempY,rotTempZ;
		
		//setting the map coordinates 
		centerXCoordOfMapPlace = camera_center.getCenterX();
		centerYCoordOfMapPlace = camera_center.getCenterY();
		
		for (Physics_drawable cObject : drawerToMap.getDrawables()) {	
			
			//storing the temps
			object_X_temp = cObject.getCenterX();
			object_Y_temp = cObject.getCenterY();
			object_xSize_temp = cObject.getXSize();
			object_ySize_temp = cObject.getYSize();
			
			if (! cObject.equals(camera_center)) {	
				//moving and resizing object
				cObject.setPos((((object_X_temp- camera_center.getX()) * ratioX) + frameXSize/2), ((object_Y_temp- camera_center.getY()) * ratioY + frameYSize/2) , cObject.getCenterZ());
				cObject.setSize(object_xSize_temp * ratioX, object_ySize_temp * ratioY, cObject.getZSize());
				
		
				
				try {
					rotTempX = ((pointed) cObject).getXRotation();
					rotTempY = ((pointed) cObject).getYRotation();
					rotTempZ = ((pointed) cObject).getZRotation();
					
					
					((pointed) cObject).calculatePointValues();
					
		//			((pointed) cObject).setRotation(rotTempX, rotTempY, rotTempZ);
					
					((massive)cObject).updatePoints();
					
					((pointed) cObject).setRotation((((rotatable) camera_center).getXRotation()), (((rotatable) camera_center).getYRotation()), (((rotatable) camera_center).getXRotation()));
					
					double[] coor = rotate(cObject.centerX - camera_center.getCenterX(),cObject.getCenterY() - camera_center.getCenterY(),cObject.getCenterZ() - camera_center.getCenterZ(),((rotatable) camera_center).getXRotation(),((rotatable) camera_center).getYRotation(),((rotatable) camera_center).getZRotation());
					
				}catch(ClassCastException c) {}
				
				//painting object
				cObject.paint(page);
				
				
				//putting the object back
				cObject.setPos(object_X_temp, object_Y_temp, cObject.getCenterZ());
				cObject.setSize(object_xSize_temp, object_ySize_temp, cObject.getZSize());
				
				try {
					
					rotTempX = ((pointed) cObject).getXRotation();
					rotTempY = ((pointed) cObject).getYRotation();
					rotTempZ = ((pointed) cObject).getZRotation();
					
					((pointed) cObject).setRotation(0, 0, 0);
					
					((pointed) cObject).calculatePointValues();
					
					((pointed) cObject).setRotation(rotTempX, rotTempY, rotTempZ);
					
					((massive)cObject).updatePoints();
					
				}catch(ClassCastException c) {}
				
			}else { //painting the camera_center
				cObject.setPos(frameXSize/2,frameYSize/2,0); //moving camera_center to the center
				
				cObject.paint(page);//painting the camera_center
				
				cObject.setPos(object_X_temp, object_Y_temp, cObject.getCenterZ());//putting it back
			}
		}
		
		drawerToMap.resume(); //now the drawerToMap can continue doing whatever it was doing now that we're done using it's objects
	}
	
	private double[] calculateRotation(double x, double y, double angle) {
		double[] polar = Vector2D.rectangularToPolar(x, y);
		return Vector2D.polarToRectangular(polar[0], polar[1] + angle);
	}
	
	private double[] rotate(double x, double y, double z, double xRotation, double yRotation, double zRotation) {
		double[] coordinates = {x,y,z}, rotComponents;
		//zRotation
		rotComponents = calculateRotation(coordinates[0],coordinates[1],zRotation);
		coordinates[0] = rotComponents[0];
		coordinates[1] = rotComponents[1];
			
		//xRotation
		rotComponents = calculateRotation(coordinates[2],coordinates[1],xRotation);
		coordinates[1] = rotComponents[1];
		coordinates[2] = rotComponents[0];
		
	
		//yRotation
		rotComponents = calculateRotation(coordinates[0],coordinates[2],yRotation);
		coordinates[0] = rotComponents[0];
		coordinates[2] = rotComponents[1];
		
		return coordinates;
	
	}

	public void resize() {
		frameXSize = frame.getWidth();
		frameYSize = frame.getHeight();
	}
	
	public void setMapAreaSize(int xSize1, int ySize1) {
		xSize = xSize1;
		ySize = ySize1;
	}
	
}
