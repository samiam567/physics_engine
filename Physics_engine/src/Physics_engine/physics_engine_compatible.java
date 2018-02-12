package Physics_engine;

import java.util.ArrayList;

import Physics_engine.physics_object.object_types;

public interface physics_engine_compatible {
	public object_types object_type = object_types.physics_object;
	
	public object_draw getDrawer();

	public void secondaryUpdate();

	public void tertiaryUpdate();

	
	
}
