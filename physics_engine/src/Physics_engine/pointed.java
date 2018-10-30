package Physics_engine;

import java.awt.Component;
import java.awt.Polygon;

import Physics_engine.Physics_engine_toolbox.pointOfRotationPlaces;
import Physics_engine.physics_object.object_types;

public abstract interface pointed extends movable { //made up of points
	public object_types object_type = object_types.pointed;
	

	public void setRotation(double xRot, double yRot, double zRot);
	
	public void setPoints(point[] points1);
	void updatePoints();
	void updatePointXsYsAndZs();
	public point[] getPoints();
	
	public void setAngularVelocity(double angularVelocityX, double angularVelocityY, double angularVelocityZ);

	public double getAngularAccelX();
	public double getAngularAccelY();
	public double getAngularAccelZ();
	
	public double getXRotation();
	public double getYRotation();
	public double getZRotation();
	
	public boolean getIsRotatable();
	
	public void setPointOfRotation(point point);
	public void setPointOfRotationPlace(pointOfRotationPlaces custom);
	public pointOfRotationPlaces getPointOfRotationPlace();
	

		
	public void updatePolygons();

	public void calculatePointValues();
	
	public Polygon getPolyXY();

	
	public void updatePointConstants();

	public void updateAreas();
	
	
}
