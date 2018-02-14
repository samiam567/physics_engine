package Physics_engine;

import java.util.ArrayList;

public interface massive extends movable{
	
	
	public boolean getIsTangible();
	public double getMass();
	public double getFrictionCoefficient();
	public void checkForCollision(massive physics_polygon, ArrayList<massive> objects);
	public void applyComponentForce(double xComponent, double yComponent, double zComponent);
	public void checkForCollisions(ArrayList<massive> objects);
}
