package Physics_engine;

public class Physics_shape extends physics_object implements drawable, movable {
	

	protected double centerX,centerY, centerZ;
	protected point center;
	public boolean isAnchored = false;
	double xSpeed,ySpeed,zSpeed,xAccel,yAccel,zAccel;
	
	public Physics_shape(object_draw drawer1) {
		super(drawer1);
	}
	
}