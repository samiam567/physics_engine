package Physics_engine;

import java.util.ArrayList;

public class Square extends Physics_3DPolygon {
	
	public Square(object_draw drawer1,double x, double y, double z, double size, double mass) {
		super(drawer1);
		setPos(x,y,z);
		setSize(size,size,0.00000001);
		
		setRotation(0,0,0);
		
		
		pointRenderOrder = new int[] {0,1,2,3,0};
		
		if (Settings.rotationAlgorithm != 5) {
			point[] points1 = new point[4];
			
			points1[0] = new point(drawer,xReal,yReal,zReal); 
			points1[0].setAngle(3*Math.PI/4, 0,Math.PI/2);
			
			points1[1] = new point(drawer,xReal+xSize,yReal,zReal);
			points1[1].setAngle(Math.PI/4, Math.PI,Math.PI/2);
			
			points1[2] = new point(drawer,xReal+xSize,yReal+ySize,zReal);
			points1[2].setAngle(-Math.PI/4, Math.PI,-Math.PI/2);
			
			points1[3] = new point(drawer,xReal,yReal+ySize,zReal);
			points1[3].setAngle(-3*Math.PI/4, 0,-Math.PI/2);
		
			
			setPoints(points1);
			

			
			
		}else if (Settings.rotationAlgorithm == 5) {		
			points = new point[4];
			
			points[0] = new point(drawer,xReal,yReal,zReal); 
			points[0].setAngle(Math.PI/2,0);
			
			points[1] = new point(drawer,xReal+xSize,yReal,zReal);
			points[1].setAngle(Math.PI/2,Math.PI);
			
			points[2] = new point(drawer,xReal+xSize,yReal+ySize,zReal);
			points[2].setAngle(-Math.PI/2,Math.PI);
			
			points[3] = new point(drawer,xReal,yReal+ySize,zReal);
			points[3].setAngle(-Math.PI/2,0);
		}
		
	
		double r = Math.sqrt(Math.pow(xSize,2) + Math.pow(ySize,2) + Math.pow(zSize,2))/2;
		for (point cP : points) {
			cP.setR(r);
		}
		
		
		calculateCenter();
		
		updatePointOfRotation();
		
		calculatePointValues(); 

		setPos(x,y,z);
	
		setMass(mass);
	}
	
	/*
	public Object checkForCollision1(massive current_object1,ArrayList<massive> objects) {
		
*		try {
*			Physics_3DPolygon current_object = (Physics_3DPolygon) current_object1;
*			
			if ((current_object.getIsTangible()) && (! this.equals(current_object))) {
				Physics_engine.Physics_engine_toolbox.faces side = Physics_engine.Physics_engine_toolbox.faces.none;
				
*				if (Settings.collision_algorithm == 4) {// v 4.2 collision algorithm (simplified)
					
					/is there a collision?
/*					if ( (Math.abs(getCenterX() - current_object.getCenterX()) < (current_object.xSize/2+xSize/2)) && (Math.abs(getCenterY() - current_object.getCenterY()) < (current_object.ySize/2+ySize/2))  ) {	
/*					
*						//set up some vars
*						double[] trajectory_this = getTrajectory();
*						double[] trajectory_current_ob = current_object.getTrajectory();
						
						double xyTragThis = trajectory_this[0];
						double xyTragCOb = trajectory_current_ob[0];
						
						
						//which face did it collide with?
						if (  Math.abs(getCenterX()+xSize/2 - current_object.x) < (xSize * 0.1)) { //object hit left face of object
							side = Physics_engine.Physics_engine_toolbox.faces.left;
						} else if (  Math.abs(getCenterY()+ySize/2 - current_object.y) < (ySize * 0.1)) { //object hit top face
							side = Physics_engine.Physics_engine_toolbox.faces.top;
						} else if (  Math.abs( (getCenterX() - xSize/2) - (current_object.x + current_object.xSize) ) < (xSize * 0.1 )) { //ball hit right face
							side = Physics_engine.Physics_engine_toolbox.faces.right;
						}else if (  Math.abs( (getCenterY() - ySize/2) - (current_object.y+current_object.ySize) ) < (ySize * 0.1) ) {
						 side = Physics_engine.Physics_engine_toolbox.faces.bottom;
						}
						
						if ( !(side.equals(Physics_engine.Physics_engine_toolbox.faces.none)) ) {
							isCollided(current_object,side);
						}
							
					//output
						if (! side.equals(Physics_engine.Physics_engine_toolbox.faces.none)) System.out.println(side);
					
					}
				}
			}
			}
	}		

	
	*/
	
	
}
