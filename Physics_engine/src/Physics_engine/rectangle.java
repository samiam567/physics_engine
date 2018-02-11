package Physics_engine;


import java.awt.Graphics;
import java.util.ArrayList;

import Physics_engine.physics_object.faces;



public class rectangle extends physics_object{
	
	public rectangle(double x, double y, double z, double e, double f, double mass) {
		
		
		setPos(x,y,z);
		setSize(e,f,0);
		setMass(mass);
		
		updatePos();
		
		setRotation(0,0,0);
				
		pointRenderOrder = new int[] {0,1,2,0};
		point[] points = new point[4];
		
		points[0] = new point(centerX-xSize/2,centerY-ySize/2,zReal); 
		points[0].setAngle(3*Math.PI/4, Math.PI,Math.PI/2);
		
		points[1] = new point(centerX+xSize/2,centerY-ySize/2,zReal);
		points[1].setAngle(Math.PI/4, 0,Math.PI/2);
		
		points[2] = new point(centerX+xSize/2,centerY+ySize/2,zReal);
		points[2].setAngle(-Math.PI/4, 0,-Math.PI/2);
		
		points[3] = new point(centerX-xSize/2,centerY+ySize/2,zReal );
		points[3].setAngle(-3*Math.PI/4, Math.PI,-Math.PI/2);
		
		setPoints(points);
		
		double r = Math.sqrt(Math.pow(xSize,2) + Math.pow(ySize,2) + Math.pow(zSize,2))/2;
		for (point cP : points) {
			cP.setR(r);
		}
		
		calculateCenter();
		
		calculatePointValues();
		
		updatePointOfRotation();
		
		setPos(x,y,z);
	
	}
	
	
	public Object checkForCollision1(physics_object current_object,ArrayList<physics_object> objects) {
		if ((current_object.isTangible) && (! this.equals(current_object))) {
			faces side = faces.none;
			
			if (Settings.collision_algorithm == 4) {// v 4.2 collision algorithm (simplified)
				
				//is there a collision?
				if ( (Math.abs(getCenterX() - current_object.getCenterX()) < (current_object.xSize/2+xSize/2)) && (Math.abs(getCenterY() - current_object.getCenterY()) < (current_object.ySize/2+ySize/2)) /* && (Math.abs(centerZ - current_object.centerZ) < (current_object.zSize/2+zSize/2))*/ ) {	
				
					//set up some vars
					double[] trajectory_this = getTrajectory();
					double[] trajectory_current_ob = current_object.getTrajectory();
					
					double xyTragThis = trajectory_this[0];
					double xyTragCOb = trajectory_current_ob[0];
					
					
					//which face did it collide with?
					if (  Math.abs(getCenterX()+xSize/2 - current_object.x) < (xSize * 0.1)) { //object hit left face of object
						side = faces.left;
						current_object.isCollided(this,faces.right);
					} else if (  Math.abs(getCenterY()+ySize/2 - current_object.y) < (ySize * 0.1)) { //object hit top face
						side = faces.top;
						current_object.isCollided(this,faces.bottom);
					} else if (  Math.abs( (getCenterX() - xSize/2) - (current_object.x + current_object.xSize) ) < (xSize * 0.1 )) { //ball hit right face
						side = faces.right;
						current_object.isCollided(this,faces.left);
					}else if (  Math.abs( (getCenterY() - ySize/2) - (current_object.y+current_object.ySize) ) < (ySize * 0.1) ) {
						 side = faces.bottom;
						 current_object.isCollided(this,faces.top);
					}
					
					isCollided(current_object,side);
					
					
					//output
				//	if (! side.equals(faces.none)) System.out.println(side);
				
				if (Settings.forceMethod == 1) {
					// sspeeed method
					//bouncing!  this algorithm needs to be a lot more advanced. This just tests the collision algorithm
					if (side.equals(faces.top) || side.equals(faces.bottom)) {
						ySpeed = -Settings.elasticity *  ySpeed;
						current_object.setSpeed(current_object.xSpeed,-current_object.ySpeed,current_object.zSpeed);
					}else if (side.equals(faces.left) || side.equals(faces.right)) {
						xSpeed = - Settings.elasticity * xSpeed;
						current_object.setSpeed(-current_object.xSpeed,current_object.ySpeed,current_object.zSpeed);
					}else {
						
					}
					
			
					
				}else if (Settings.forceMethod == 0) {
						//momentum method ->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->
						double momentum1 = mass * xSpeed;
						double momentum2 = current_object.mass * current_object.xSpeed;
						
						double time = 0.5;
						
						double force = (momentum1+momentum2)/(4*time);
						force *= Settings.elasticity;
						System.out.println("Force: " + force);
						
						double[] thisDeflectionAnglePack = current_object.calculateDeflectionAngle(this);
						double[] current_obDeflectionAnglePack = calculateDeflectionAngle(current_object);
						
						double thisDeflectionAngle = thisDeflectionAnglePack[0];
						double thisZReflect = thisDeflectionAnglePack[1];
						
						double current_obDeflectionAngle = current_obDeflectionAnglePack[0];
						double current_obZReflect = current_obDeflectionAnglePack[1];
						
//public force(physics_object object1, double r, double theta, double zComponent1, double time1, double frame1, boolean isPolar) {
						
						object_draw.scheduled_forces.add(new force(this,force,thisDeflectionAngle,thisZReflect,time,-1,true));
						object_draw.scheduled_forces.add(new force(this,force,current_obDeflectionAngle,current_obZReflect,time,-1,true));
					}	
					
					
				}
				
			
			}
		}
		return current_object;
				
	}

	
	
}
