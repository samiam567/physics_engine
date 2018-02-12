package Physics_engine;

import java.awt.Color;

public abstract class Physics_shape extends physics_object implements drawable, movable {
	

	protected double centerX,centerY, centerZ;
	protected point center;
	public boolean isAnchored = false;
	double xSpeed,ySpeed,zSpeed,xAccel,yAccel,zAccel;
	
	int x=0,y=0,z=0;
	double xReal=0,yReal=0,zReal=0;
	boolean isVisible = true,isFilled = false;
	Color color = Color.BLACK;
	public String drawMethod = "paint";
	public String name = "unnamed";
	
	protected physics_object parent_object; //this object will move and act relative to it's parent object (usefull for making complex objects out of multiple shapes)
	protected boolean hasParentObject = false; //if the object is linked to a parent object
	
	
	public Physics_shape(object_draw drawer1) {
		super(drawer1);
	}
	
}