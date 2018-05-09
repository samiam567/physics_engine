package Physics_engine;


public class Box extends Physics_3DPolygon  {

	
	public Box(object_draw drawer1, double x, double y, double z, int size, int mass1) {
		super(drawer1);
		setPos(x,y,z);
		setSize(size,size,size);
		
		setRotation(0,0,0);
		
		drawMethod = "ListedPointAlgorithm";
		
		pointRenderOrder = new int[] {0,1,2,3,0,4,5,6,7,3,2,6,5,4,7,4,5,1}; //this could probably be improved (more efficient)
		
		points = new point[8];
		
		point[] points = {
				//front face
				new point(drawer,x,y,z), //0
				new point(drawer,x+xSize,y,z),//1 
				new point(drawer,x+xSize,y+ySize,z),//2 
				new point(drawer,x,y+ySize,z),//3
				
				//back face
				new point(drawer,x,y,z+zSize), //4
				new point(drawer,x+xSize,y,z+zSize), //5
				new point(drawer,x+xSize,y+ySize,z+zSize), //6
				new point(drawer,x,y+ySize,z+zSize)//7
				};

		setPoints(points);

		calculateCenter();
		
		updatePointOfRotation();
		
		calculatePointValues(); 

		setPos(x,y,z);
	
		setMass(mass1);
	}

	
}
