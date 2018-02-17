package Physics_engine;

import java.util.ArrayList;

import Physics_engine.Physics_engine_toolbox.faces;



public class border_bounce extends rectangle {
	public Physics_frame frame;
	
	public border_bounce(object_draw drawer1) {
		super(drawer1,0, 0, 0, Settings.width *1.06, Settings.height * 0.975, 1);
		isAnchored = true;
		isRotatable = false;
		isVisible = true;
		frame = drawer1.frame;
		
	}
	
	public void updatePoints() {
		
	}
	
	public void resize() {
		System.out.println("resizeborder");
		setSize( Settings.width * 1.06, Settings.height * 0.975,Settings.depth);
	}

	
	public void checkForCollision (massive current_object1,ArrayList<massive> objects) { 
		Physics_polygon current_object = (Physics_polygon) current_object1;
		if (current_object.affectedByBorder) {
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

		}
		
		
	}
		
}