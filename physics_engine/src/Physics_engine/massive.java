package Physics_engine;

import java.awt.Component;
import java.util.ArrayList;

import Physics_engine.Physics_engine_toolbox.faces;

public interface massive extends pointed {
		
	public void checkForCollision(massive physics_polygon, ArrayList<massive> objects);
	public void applyComponentForce(double xComponent, double yComponent, double zComponent);
	public void checkForCollisions(ArrayList<massive> objects);
	public void applyComponentImpulse(double d, double e, double f, double time, String string);
	public boolean getIsAffectedByBorder();
	public void isCollided(physics_object cOb, faces side);
	public double[] getTrajectory();
	
	public boolean getIsTangible();
	
	public double getMass();
	public void setMass(double mass);
	
	public double getFrictionCoefficient();
	public void setFrictionCoefficient(double frictionCoefficient);
	public void setAccel(double x, double y, double z);
	public void setSize(double x, double y, double z);
	public double getElasticity();
	public void collision(massive cObject, point cPoint, faces side);

	
	
	

	
}
