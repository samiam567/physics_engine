package Physics_engine;

import java.awt.Graphics;

// DONT FORGET TO ADD CENTERING AROUND A OBJECTTTTTT!!!!!!!!!!   (and point of rotation other than the center of the ob)

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
		setFrameTime(100000000);
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
		
		//setting the map coordinates 
		centerXCoordOfMapPlace = camera_center.getCenterX();
		centerYCoordOfMapPlace = camera_center.getCenterY();
		
		for (Physics_drawable cObject : drawerToMap.getDrawables()) {
			//storing the temps
			object_X_temp = cObject.getCenterX();
			object_Y_temp = cObject.getCenterY();
			object_xSize_temp = cObject.getXSize();
			object_ySize_temp = cObject.getYSize();
			
			//moving and resizing object
			cObject.setPos((((object_X_temp- centerXCoordOfMapPlace) * ratioX) + frameXSize/2), ((object_Y_temp- centerYCoordOfMapPlace) * ratioY + frameYSize/2) , cObject.getCenterZ());
			cObject.setSize(object_xSize_temp * ratioX, object_ySize_temp * ratioY, cObject.getZSize());
			
			try {
				((Physics_polygon)cObject).updatePoints();
			}catch(ClassCastException c) {}
			
			//painting object
			cObject.paint(page);
			
			
			//putting the object back
			cObject.setPos(object_X_temp, object_Y_temp, cObject.getCenterZ());
			cObject.setSize(object_xSize_temp, object_ySize_temp, cObject.getZSize());
			
			try {
				((Physics_polygon)cObject).updatePoints();
			}catch(ClassCastException c) {}
			
			drawerToMap.resume(); //now the drawerToMap can continue doing whatever it was doing now that we're done using it's objects
		}
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
