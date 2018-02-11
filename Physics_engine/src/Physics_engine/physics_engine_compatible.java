package Physics_engine;

import java.util.ArrayList;

import Physics_engine.physics_object.object_types;

public interface physics_engine_compatible {
	public object_types object_type = object_types.physics_object;
	
	public void Update(ArrayList<physics_object> objects,double frames);
}
