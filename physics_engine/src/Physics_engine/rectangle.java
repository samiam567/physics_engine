package Physics_engine;


import java.awt.Graphics;
import java.util.ArrayList;

import Physics_engine.Physics_engine_toolbox.faces;



public class rectangle extends Physics_3DPolygon {
	
	public rectangle(object_draw drawer1,double x, double y, double z, double e, double f, double mass) {
		super(drawer1);
		setPos(x,y,z);
		setSize(e,f,0.00000001);
		
		updatePos();
		
		setRotation(0,0,0);
				
		pointRenderOrder = new int[] {0,1,2,0};
		point[] points = new point[4];
		
		points[0] = new point(drawer,centerX-xSize/2,centerY-getYSize()/2,zReal); 
		points[0].setAngle(3*Math.PI/4, Math.PI,Math.PI/2);
		
		points[1] = new point(drawer,centerX+xSize/2,centerY-getYSize()/2,zReal);
		points[1].setAngle(Math.PI/4, 0,Math.PI/2);
		
		points[2] = new point(drawer,centerX+xSize/2,centerY+getYSize()/2,zReal);
		points[2].setAngle(-Math.PI/4, 0,-Math.PI/2);
		
		points[3] = new point(drawer,centerX-xSize/2,centerY+getYSize()/2,zReal );
		points[3].setAngle(-3*Math.PI/4, Math.PI,-Math.PI/2);
		
		
		setPoints(points);
		
		double r = Math.sqrt(Math.pow(xSize,2) + Math.pow(getYSize(),2) + Math.pow(zSize,2))/2;
		for (point cP : points) {
			cP.setR(r);
		}
		
		calculateCenter();
		
		calculatePointValues();
		
		updatePointOfRotation();
		
		setPos(x,y,z);
	
		setMass(mass);
		
	}
	
	
	public Object checkForCollision1 (massive current_object1,ArrayList<massive> objects) { 
		massive current_object = (massive) current_object1;
		if ((current_object.getIsTangible()) && (! this.equals(current_object))) {
			faces side = faces.none;
			
			if (Settings.collision_algorithm == 4) {// v 4.2 collision algorithm (simplified)
				
				//is there a collision?
				if ( (Math.abs(getCenterX() - current_object.getCenterX()) < (current_object.getXSize()/2+getXSize()/2)) && (Math.abs(getCenterY() - current_object.getCenterY()) < (current_object.getYSize()/2+getYSize()/2)) /* && (Math.abs(centerZ - current_object.centerZ) < (current_object.zSize/2+zSize/2))*/ ) {	
				
					//set up some vars
					double[] trajectory_this = getTrajectory();
					double[] trajectory_current_ob = current_object.getTrajectory();
					
					double xyTragThis = trajectory_this[0];
					double xyTragCOb = trajectory_current_ob[0];
					
					
					//which face did it collide with?
					if (  Math.abs(getCenterX()+getXSize()/2 - current_object.getXReal()) < (getXSize() * 0.1)) { //object hit left face of object
						side = faces.left;
						current_object.isCollided(this,faces.right);
					} else if (  Math.abs(getCenterY()+getYSize()/2 - current_object.getYReal()) < (getYSize() * 0.1)) { //object hit top face
						side = faces.top;
						current_object.isCollided(this,faces.bottom);
					} else if (  Math.abs( (getCenterX() - getXSize()/2) - (current_object.getXReal() + current_object.getXSize()) ) < (getXSize() * 0.1 )) { //ball hit right face
						side = faces.right;
						current_object.isCollided(this,faces.left);
					}else if (  Math.abs( (getCenterY() - getYSize()/2) - (current_object.getYReal()+current_object.getYSize()) ) < (getYSize() * 0.1) ) {
						 side = faces.bottom;
						 current_object.isCollided(this,faces.top);
					}
					
					isCollided((physics_object) current_object,side);
					
					
					//output
				//	if (! side.equals(faces.none)) System.out.println(side);
				
					if (Settings.forceMethod == 1) {
						// sspeeed method
						//bouncing!  this algorithm needs to be a lot more advanced. This just tests the collision algorithm
						if (side.equals(faces.top) || side.equals(faces.bottom)) {
							setSpeed(getXSpeed(),-elasticity *  getYSpeed(),getZSpeed());
							current_object.setSpeed(current_object.getXSpeed(),-current_object.getYSpeed(),current_object.getZSpeed());
						}else if (side.equals(faces.left) || side.equals(faces.right)) {
							setSpeed(- elasticity * getXSpeed(),getYSpeed(),getZSpeed());
							current_object.setSpeed(-current_object.getXSpeed(),current_object.getYSpeed(),current_object.getZSpeed());
						}else {
							
						}					
					}else if (Settings.forceMethod == 0) {
						//momentum method ->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->
	//					double momentum1 = mass * getXSpeed();
//						double momentum2 = current_object.mass * current_object.getXSpeed();
						
						double time = 0.1;
						
	//					double force = (momentum1+momentum2)/(4*time);
	//					force *= elasticity;
//						System.out.println("Force: " + force);
						
			//			double[] thisDeflectionAnglePack = current_object.calculateDeflectionAngle(this);
//						double[] current_obDeflectionAnglePack = calculateDeflectionAngle(current_object);
						
	//					double thisDeflectionAngle = thisDeflectionAnglePack[0];
	//					double thisZReflect = thisDeflectionAnglePack[1];
						
//						double current_obDeflectionAngle = current_obDeflectionAnglePack[0];
//						double current_obZReflect = current_obDeflectionAnglePack[1];
						
						
	//					drawer.scheduled_forces.add(new force(drawer,this,force,thisDeflectionAngle,thisZReflect,time,"seconds",-1,true));
//						drawer.scheduled_forces.add(new force(drawer,this,force,current_obDeflectionAngle,current_obZReflect,time,"seconds",-1,true));
					}	
					
					
				}
				
			
			}
		}
		return current_object;
	
	}

}
	

