package Physics_engine;


public abstract class physics_object implements physics_engine_compatible{
	
	public enum object_types {none,physics_object,drawable,movable,massive,pointed,rotatable};
	
	public String name = "unNamed"; //the name of the object

	public object_draw drawer;

	public boolean delete = false; //delete is for the garbage collector (not made yet)
	
	public physics_object(object_draw drawer1) {
		drawer = drawer1;
	}

	public void secondaryUpdate() {
		//this is a subclass-specific update method that can be overridden to allow for each child class to be updated differently
	}
	
	public void tertiaryUpdate() {
		//this is a subclass-specific update method that can be overridden to allow for each child class to be updated differently
	}
	
	public void frameUpdate2(double frames) {
		//this is a subclass-specific update method that can be overridden to allow for each child class to be updated differently
	}
	
	public void frameUpdate3(double frames) {
		//this is a subclass-specific update method that can be overridden to allow for each child class to be updated differently
	}

	public void setName(String new_name, int i) { //sets the name of the object (this will be shown if Settings.displayNames is true)
		name = new_name;
	
	}

	public String getObjectName() { //gets the name of the object
		return name;
	}
	
	//Getter methods
	public object_draw getDrawer() {
		return drawer;
	}
	
	
/*	
	public physics_object copy(physics_object new_object)  { //copies this physics_object to another
		//this method exists because you cannot do object1 = object2 because that will just tell object1 to point to object2 and not create a new object. (Aliasing)
	
		//booleans
		new_object.isVisible = isVisible;
		new_object.isTangible = isTangible;
		new_object.isAnchored = isAnchored;
		new_object.isFilled = isFilled;
		new_object.affectedByBorder = affectedByBorder;
		new_object.hasParentObject = hasParentObject;
		
		//ints
		new_object.x = x;
		new_object.y = y;
		new_object.xSize = xSize;
		new_object.ySize = ySize;
		
		
		//doubles
		new_object.xReal = xReal;
		new_object.yReal = yReal;
		new_object.zReal = zReal;
		new_object.centerX = centerX;
		new_object.centerY = centerY;
		new_object.centerZ = centerZ;
		new_object.xSpeed = xSpeed;
		new_object.ySpeed = ySpeed;
		new_object.zSpeed = zSpeed;
		new_object.xAccel = xAccel;
		new_object.yAccel = yAccel;
		new_object.zAccel = zAccel;
		new_object.xRotation = xRotation;
		new_object.yRotation = yRotation;
		new_object.zRotation = zRotation;
		new_object.axisThetaXY = axisThetaXY;
		new_object.axisThetaZX = axisThetaZX;
		new_object.axisThetaZY = axisThetaZY;
		new_object.mass = mass;
		new_object.friction_coefficient = friction_coefficient;
		new_object.xSizeAppearance = xSizeAppearance;
		new_object.ySizeAppearance = ySizeAppearance;
		new_object.zSizeAppearance = zSizeAppearance;
		
		
		//strings
		new_object.name = name;
		
		//other
		new_object.drawMethod = drawMethod;
		new_object.axis = axis;
		new_object.parent_object = parent_object;
		
		//this for loop probably doesn't work as it hasn't been tested yet
		for (int i=0; i < points.length; i++) {
			point new_point = new point(null, i);
			new_point = (point) points[i].copy(new_object);
			new_object.points[i] = new_point;
		}
		
		
		new_object.pointRenderOrder = pointRenderOrder;
		new_object.color = color;
		
		return new_object;
	}
*/
}

