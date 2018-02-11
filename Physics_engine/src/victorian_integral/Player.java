package victorian_integral;

import java.util.ArrayList;

import Physics_engine.physics_object;

public class Player extends Human {
	String Social_Class = "";
	String Age = "";
	String Gender = "";
	String Marriage_Status = "";
	
	public Player(String name1, double x1, double y1, double z1, double size1, double mass1) {
		super(name1,x1,y1,z1,size1,mass1);
	}
	
	public Object checkForCollision1(physics_object current_object,ArrayList<physics_object> objects) {
		//is there a collision?
		if ( (Math.abs(getCenterX() - current_object.getCenterX()) < (current_object.getXSize()/2+xSize/2)) && (Math.abs(getCenterY() - current_object.getCenterY()) < (current_object.getYSize()/2+ySize/2)) /* && (Math.abs(centerZ - current_object.centerZ) < (current_object.zSize/2+zSize/2))*/ ) {	
			current_object.isCollided(this,faces.none);
		}
		
		return null;
	}
}
