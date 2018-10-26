package jetpack_joyride;

import java.awt.Color;
import java.awt.Graphics;

import Physics_engine.Physics_engine_toolbox.faces;
import Physics_engine.Physics_shape;
import Physics_engine.Settings;
import Physics_engine.Square;
import Physics_engine.object_draw;
import Physics_engine.physics_object;

public class JetPack_fire extends Physics_shape {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8777888153902679660L;

	public JetPack_fire(object_draw drawer1, double x, double y,double AngularVelocity,int xSpeed) {
		super(drawer1);
		setPos(x, y, 0);
		setSize(JetPack_JoyRide.jetpack.getXSize()/5, JetPack_JoyRide.jetpack.getXSize()/5, 0.0001);
  	  	isFilled = true;
  	  	setColor(Color.ORANGE);
  	  	setSpeed(xSpeed * 10, 200, 0);
  	  	drawer.add(this);
	}
	
	public void tertiaryUpdate() {
		if (getY() > (Settings.height)) {
			drawer.remove(this);
		}else if (getY() < 0) {
			drawer.remove(this);
		}
	}
	


	@Override
	public void paint(Graphics page) {
		page.setColor(Color.ORANGE);
		page.fillRect(getX(), getY(),(int) xSize,(int) ySize);
		
	}

}
