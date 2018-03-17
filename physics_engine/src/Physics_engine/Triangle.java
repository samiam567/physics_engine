package Physics_engine;

public class Triangle extends Physics_3DPolygon {

	public Triangle(object_draw drawer1, double x, double y, double z, double width,double height, double mass) {
		super(drawer1);
		
		setPos(x,y,z);
		setSize(width,height,width);
		setMass(mass);
		setRotation(0,0,0);
		
		drawMethod = "ListedPointAlgorithm";
		pointRenderOrder = new int[] {0,1,2,3,1,3,0,2};
		
		//pointRenderOrder = new int[] {0,1,2,0};
		
		/*
		point[] points = new point[3];
		
		points[0] = new point(drawer,xReal,yReal,zReal); 
		points[0].setAngle(Math.PI/2, 0,Math.PI/2);
		
		points[1] = new point(drawer,xReal+xSize/2,yReal + ySize,zReal);
		points[1].setAngle(0, 0,-Math.PI/2);
		
		points[2] = new point(drawer,xReal-xSize/2,yReal+ySize,zReal );
		points[2].setAngle(Math.PI, Math.PI,-Math.PI/2);
		*/
		
		
		//triangular prism
		point[] points = new point[4];
		
		points[0] = new point(drawer,xReal,yReal,zReal); 
		points[0].setAngle(Math.PI/2, 0,Math.PI/2);
		
		points[1] = new point(drawer,xReal+xSize/2,yReal + ySize,zReal);
		points[1].setAngle(0, 0,-Math.PI/2);
		
		points[2] = new point(drawer,xReal-xSize/2,yReal+ySize,zReal );
		points[2].setAngle(Math.PI, Math.PI,-Math.PI/2);
		
		points[3] = new point(drawer,xReal + xSize/2, yReal + ySize/2, zReal + zSize/2);
		
		
		setPoints(points);
		
		double r = Math.sqrt(Math.pow(xSize,2) + Math.pow(ySize,2) + Math.pow(zSize,2))/2;
		for (point cP : points) {
			cP.setR(r);
		}
		
		calculateCenter();
		
		updatePointOfRotation();
		
		calculatePointValues();
		
		
		
		setPos(x,y,z);
		
	
		
	}
}
