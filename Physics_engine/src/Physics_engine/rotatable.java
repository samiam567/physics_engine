package Physics_engine;

import Physics_engine.physics_object.object_types;
import Physics_engine.physics_object.pointOfRotationPlaces;

public interface rotatable extends pointed { //the ability to be rotated
	public object_types object_type = object_types.rotatable;
	
	/* variables needed:
	double xRotation=0,yRotation=0,zRotation=0,angularVelocityX=0, angularVelocityY=0, angularVelocityZ=0, angularAccelX=0, angularAccelY=0, angularAccelZ=0;
	public boolean isRotatable = true;
	point pointOfRotation = null; //the point that the object rotates around
	pointOfRotationPlaces pointOfRotationPlace = pointOfRotationPlaces.center;  //the place that that point is
	*/
	
	public void setRotation(double xRotation1, double yRotation1, double zRotation1);
	public void setAngularVelocity(double angVX, double angVY, double angVZ);
	public void setAngularAccel(double angAccelX, double angAccelY, double angAccelZ);
	public void setPointOfRotation(point newPointOfRotation);
	public void setPointOfRotationPlace(pointOfRotationPlaces newPlace);
	public void updatePointOfRotation();
	public double getXRotation();
	public double getYRotation();
	public double getZRotation();
	
}
