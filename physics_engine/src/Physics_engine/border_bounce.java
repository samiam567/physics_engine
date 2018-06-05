package Physics_engine;

import java.io.Serializable;
import java.util.ArrayList;

import Physics_engine.Physics_engine_toolbox.faces;



public class border_bounce extends rectangle implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6603405988501987566L;
	public Physics_frame frame;
	
	
	public border_bounce(object_draw drawer1) {
		super(drawer1,Settings.width/2, Settings.height/2, 0, Settings.width *1.06, Settings.height * 0.975, 1);
		
		frame = drawer.frame;
		isAnchored = true;
		isRotatable = false;
		isVisible = false;
	}
	
	public void updatePoints() {
		
	}
	
	public void resize() {
		System.out.println("resizeborder");
		setSize( Settings.width * 1.06, Settings.height * 0.975,Settings.depth);
		setPos(Settings.width/2, Settings.height/2, 0);
	}

	
	public void checkForCollision (massive current_object,ArrayList<massive> objects) { 
		
		if (current_object.getIsAffectedByBorder()) {
			if ( (Math.abs(current_object.getCenterX() - (Settings.width) )) < ( current_object.getXSpeed() + current_object.getXSize()/2 ) ) { //right side
				current_object.setSpeed(-current_object.getElasticity() * Math.abs(current_object.getXSpeed()),current_object.getYSpeed(),current_object.getZSpeed());
				current_object.isCollided(this,faces.right);
				
			}else if ( current_object.getCenterX() < ( current_object.getXSpeed() + current_object.getXSize()/2 ) ) { //left side
				current_object.setSpeed(current_object.getElasticity() * Math.abs(current_object.getXSpeed()),current_object.getYSpeed(),current_object.getZSpeed());
				current_object.isCollided(this,faces.left);
			}else if ( (Math.abs(current_object.getCenterY() - (Settings.height) )) < ( current_object.getYSpeed() + current_object.getYSize()/2 ) ) { //bottom side
				current_object.setSpeed(current_object.getXSpeed(),-current_object.getElasticity() * Math.abs(current_object.getYSpeed()),current_object.getZSpeed());
				current_object.isCollided(this,faces.bottom);
			}else if ( current_object.getCenterY() < ( current_object.getYSpeed() + current_object.getYSize()/2 ) ) { //top side
				current_object.setSpeed(current_object.getXSpeed(),current_object.getElasticity() * Math.abs(current_object.getYSpeed()),current_object.getZSpeed());	
				current_object.isCollided(this,faces.top);
			}
			
			if ( current_object.getCenterZ() < ( current_object.getZSpeed() + current_object.getZSize()/2 ) ) { //far side
				current_object.setSpeed(current_object.getXSpeed(),current_object.getYSpeed(),Math.abs(current_object.getZSpeed()));
				current_object.isCollided(this,faces.near);
			}else if ( (Math.abs(current_object.getCenterZ() - (Settings.depth) )) < ( current_object.getZSpeed() + current_object.getZSize()/2 ) ) { //near side
				current_object.setSpeed(current_object.getXSpeed(),current_object.getYSpeed(),-current_object.getElasticity()* Math.abs(current_object.getZSpeed()));
				current_object.isCollided(this,faces.far);
			}

		}
		
		
	}
		
}