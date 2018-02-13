package Physics_engine;

import java.util.ArrayList;

import Physics_engine.Physics_engine_toolbox.faces;



public class border_bounce extends rectangle {
	public Physics_frame frame;
	
	public border_bounce(object_draw drawer1) {
		super(drawer1,-50, 0, 0, Settings.width *1.06, Settings.height * 0.975, 1);
		isAnchored = true;
		isRotatable = false;
		frame = drawer1.frame;
		
	}
	
	public void updatePoints() {
		
	}
	

	public void secondaryUpdate() {
		if (Settings.autoResizeFrame) {
			Settings.width = frame.getWidth();
			Settings.height = frame.getHeight();
			setSize( Settings.width * 1.06, Settings.height * 0.975,Settings.depth);
		}
	}
	
	public Object checkForCollision1(Physics_polygon current_object,ArrayList<physics_object> objects) { 
	
		if ( (Math.abs(current_object.centerX - (Settings.width-20) )) < ( current_object.xSpeed + current_object.xSize/2 ) ) { //right side
			current_object.setSpeed(-Settings.elasticity * Math.abs(current_object.xSpeed),current_object.ySpeed,current_object.zSpeed);
			current_object.isCollided(this,faces.right);
			
		}else if ( current_object.centerX < ( current_object.xSpeed + current_object.xSize/2 ) ) { //left side
			current_object.setSpeed(Settings.elasticity * Math.abs(current_object.xSpeed),current_object.ySpeed,current_object.zSpeed);
			current_object.x = (int) Math.round(current_object.xSpeed);
			current_object.isCollided(this,faces.left);
		}else if ( (Math.abs(current_object.centerY - (Settings.height - 70) )) < ( current_object.ySpeed + current_object.ySize/2 ) ) { //bottom side
			current_object.setSpeed(current_object.xSpeed,-Settings.elasticity * Math.abs(current_object.ySpeed),current_object.zSpeed);
//			current_object.y = (int) (Settings.height - Math.round(current_object.ySpeed));
			current_object.isCollided(this,faces.bottom);
		}else if ( current_object.centerY < ( current_object.ySpeed + current_object.ySize/2 ) ) { //top side
			current_object.setSpeed(current_object.xSpeed,Settings.elasticity * Math.abs(current_object.ySpeed),current_object.zSpeed);
//			current_object.y = (int) Math.round(current_object.ySpeed);
			current_object.isCollided(this,faces.top);
		}
		
		if ( current_object.centerZ < ( current_object.zSpeed + current_object.zSize/2 ) ) { //far side
			current_object.setSpeed(current_object.xSpeed,current_object.ySpeed,Math.abs(current_object.zSpeed));
			current_object.isCollided(this,faces.far);
		}else if ( (Math.abs(current_object.centerZ - (Settings.depth - 40) )) < ( current_object.zSpeed + current_object.zSize/2 ) ) { //near side
			current_object.setSpeed(current_object.xSpeed,current_object.ySpeed,-Settings.elasticity * Math.abs(current_object.zSpeed));
			current_object.isCollided(this,faces.near);
		}

		return current_object;
		
	}
		
}