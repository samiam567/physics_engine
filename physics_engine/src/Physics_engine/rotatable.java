package Physics_engine;

import Physics_engine.Physics_engine_toolbox.pointOfRotationPlaces;
import Physics_engine.physics_object.object_types;


public interface rotatable extends movable { //the ability to be rotated
	public object_types object_type = object_types.rotatable;
	
	/* variables needed:
	double xRotation=0,yRotation=0,zRotation=0,angularVelocityX=0, angularVelocityY=0, angularVelocityZ=0, angularAccelX=0, angularAccelY=0, angularAccelZ=0;
	public boolean isRotatable = true;
	point pointOfRotation = null; //the point that the object rotates around
	pointOfRotationPlaces pointOfRotationPlace = pointOfRotationPlaces.center;  //the place that that point is
	& more
	*/
	
	public void setVectorRotation(Vector3D rotVec);
	public void setVectorAngularVelocity(Vector3D angVelVec);
	public void setVectorAngularAccel(Vector3D angAccelVec);
	
	public void addVectorRotation(Vector3D rotVec);
	public void addVectorAngularVelocity(Vector3D angVelVec);
	public void addVectorAngularAccel(Vector3D angAccelVec);
	
	public void setRotation(double xRotation1, double yRotation1, double zRotation1);
	public void setAngularVelocity(double angVX, double angVY, double angVZ);
	public void setAngularAccel(double angAccelX, double angAccelY, double angAccelZ);
	public void setPointOfRotation(point newPointOfRotation);
	public void setPointOfRotationPlace(Physics_engine.Physics_engine_toolbox.pointOfRotationPlaces newPlace);
	public void updatePointOfRotation();
	
	public Vector3D getVectorRotation();
	public Vector3D getVectorAngularVelocity();
	public Vector3D getVectorAngularAccel();
	
	public double getXRotation();
	public double getYRotation();
	public double getZRotation();
	
	public double getAngularVelocityX();
	public double getAngularVelocityY();
	public double getAngularVelocityZ();

	public double getAngularAccelX();
	public double getAngularAccelY();
	public double getAngularAccelZ();
	
	public point getCenter();
	public pointOfRotationPlaces getPointOfRotationPlace();
	public point getPointOfRotation();
	
	
	

	public boolean getIsRotatable();
	

	
}
