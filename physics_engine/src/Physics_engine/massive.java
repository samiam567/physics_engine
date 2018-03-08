package Physics_engine;

import java.util.ArrayList;

public interface massive extends pointed{
	
	
	public boolean getIsTangible();
	public double getMass();
	public double getFrictionCoefficient();
	public void checkForCollision(massive physics_polygon, ArrayList<massive> objects);
	public void applyComponentForce(double xComponent, double yComponent, double zComponent);
	public void checkForCollisions(ArrayList<massive> objects);
	public void applyComponentImpulse(double d, double e, double f, double time, String string);

}
