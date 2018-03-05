package Physics_engine;


public class Box extends Physics_polygon  {

	
	public Box(object_draw drawer1, double ob_x, double ob_y, double ob_z, int size, int mass1) {
		super(drawer1);
		xReal = ob_x;
		yReal = ob_y;
		zReal = ob_z;
		xSize = size;
		ySize = size;
		zSize  = size;
		mass = mass1;
		drawMethod = "paint";
		points = new point[8];
		

		updateSize();
		
		updatePos();
		
		point[] points = {
				//front face
				new point(drawer,new double[] {x,y,z},0), //0
				new point(drawer,new double[] {x+xSize,y,z},0),//1 
				new point(drawer,new double[] {x+xSize,y+ySize,z},0),//2 
				new point(drawer,new double[] {x,y+ySize,z},0),//3
				
				//back face
				new point(drawer,new double[] {x,y,z+zSize},0), //4
				new point(drawer,new double[] {x+xSize,y,z+zSize},0), //5
				new point(drawer,new double[] {x+xSize,y+ySize,z+zSize},0), //6
				new point(drawer,new double[] {x,y+ySize,z+zSize},0)//7
				};
		
		// These point angles are wrong  -_=- +- =_ = - =dg-r=g ga=g-a=g-=g-=z -=g-s=g-1=1- =-3=-2 = -4=3- =sa-f
		
		points[0].setAngle(5*Math.PI/4,5*Math.PI/4,3*Math.PI/4);
		points[1].setAngle(-Math.PI/4, 7*Math.PI/4, 3*Math.PI/4);
		points[2].setAngle(Math.PI/4, 7*Math.PI/4, 5*Math.PI/4);
		points[3].setAngle(3*Math.PI/4,5*Math.PI/4,5*Math.PI/4);
		
		points[4].setAngle(5*Math.PI/4,3*Math.PI/4,Math.PI/4);
		points[5].setAngle(-Math.PI/4,Math.PI/4,7*Math.PI/4);
		points[6].setAngle(Math.PI/4, Math.PI/4, 7*Math.PI/4);
		points[7].setAngle(4*Math.PI/4, 3*Math.PI/4, Math.PI/4);
		
		setPoints(points);
	
		
		
		double r = Math.sqrt(Math.pow(xSize,2) + Math.pow(ySize,2) + Math.pow(zSize,2))/2;
		for (point cP : points) {
			cP.setR(r);
		}
		
		calculateCenter();
	
		updatePointOfRotation();
		
		calculatePointValues();  //THIS METHOD IS EVIL SPAWN OF SATAN! |:<
	
		updatePoints();
	
		pointRenderOrder = new int[] {0,1,2,3,0,4,5,6,7,4,1,2,5,6,2,3};
	
	}

	
}
