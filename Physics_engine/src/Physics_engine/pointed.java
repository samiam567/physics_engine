package Physics_engine;

import Physics_engine.physics_object.object_types;

public abstract interface pointed extends movable { //made up of points
	public object_types object_type = object_types.pointed;
	
	/* vars needed:
	public point[] points = {}; //all of the points in the object
	int[] pointXs = {}; //all of the x coordinates of the points in the object
	int[] pointYs = {}; //all of the y coordinates of the points in the object
	int[] pointZs = {}; //all of the y coordinates of the points in the object
	
	double[] pointXReals = {}; //all of the x coordinates of the points in the object
	double[] pointYReals = {}; //all of the y coordinates of the points in the object
	double[] pointZReals = {}; //all of the y coordinates of the points in the object
	
	int[] pointRenderOrder = {}; //the order in which lines will be drawn from point to point (for listedPointAlgorithm)
	*/
	
	public void setPoints(point[] points1);
	void updatePoints();
	void updatePointXsYsAndZs();
	public point[] getPoints();
	
	
}
