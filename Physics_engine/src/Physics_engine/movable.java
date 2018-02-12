package Physics_engine;

import Physics_engine.physics_object.object_types;

public interface movable extends drawable { // the ability to be moved
	public object_types object_type = object_types.movable;
	
	/* vars needed:
	public boolean isAnchored = false;
	double xSpeed=0,ySpeed=0,zSpeed=0,xAccel=0,yAccel=0,zAccel=0;
	*/
	
	public boolean getIsAnchored();
}
