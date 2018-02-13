package Physics_engine;

import java.util.ArrayList;

public interface massive extends movable{
	
	
	public boolean getIsTangible();
	public double getMass();
	public double getFrictionCoefficient();
	public void checkForCollision(massive physics_polygon, ArrayList<physics_object> objects);
}
