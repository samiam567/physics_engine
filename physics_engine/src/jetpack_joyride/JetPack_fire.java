package jetpack_joyride;

import java.awt.Color;

import Physics_engine.Physics_engine_toolbox.faces;
import Physics_engine.Settings;
import Physics_engine.Square;
import Physics_engine.object_draw;
import Physics_engine.physics_object;

public class JetPack_fire extends Square{

	public JetPack_fire(object_draw drawer1, double x, double y,double AngularVelocity,int xSpeed) {
		super(drawer1, x, y, 0, JetPack_JoyRide.jetpack.getXSize()/5, 1);
		setAngularVelocity(0, 0, AngularVelocity);
  	  	isTangible = false;
  	  	isFilled = true;
  	  	affectedByBorder = false;
  	  	setColor(Color.ORANGE);
  	  	setSpeed(xSpeed * 10, 200, 0);
  	  	drawer.add(this);
	}
	
	public void tertiaryUpdate() {
		if (y > (Settings.height)) {
			drawer.remove(this);
		}else if (y < 0) {
			drawer.remove(this);
		}
	}
	
	public void isCollided(physics_object ob, faces side) {
		ySpeed *= 0.3;
	}

}
