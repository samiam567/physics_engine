package jetpack_joyride;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import Physics_engine.Settings;
import Physics_engine.physics_object;
import Physics_engine.rectangle;

public class Missile extends rectangle {

	public static final int missileHomingSpeed = 2;
	
	public Missile(double d, double y) {
		super(d, y, 0, 20, 5, 0);
		drawMethod = "paint";
		setColor(Color.red);
		affectedByBorder = false;
		name = "thing";
	}

	public void secondaryUpdate() {
		if (getXReal() < 0) {
			setPos(Settings.width+1000 + Math.random() * 1500, Math.random() * (Settings.height-getXSize()-150), getZReal());
			setSpeed(-JetPack_JoyRide.jetpack_speed * 3.5,ySpeed,0);
		}else if ( (getXReal()+10 < JetPack_JoyRide.jetpack.getXReal()) || (getXReal()-50 >JetPack_JoyRide.jetpack.getXReal()) ) {
			isTangible = false;
		}else {
			isTangible = true;
		}
		

		if (JetPack_JoyRide.jetpack.getXReal() < xReal + 10) { //only activate guidance when the missile is converging on target
			//homing in on jetpack
			if (JetPack_JoyRide.jetpack.getYReal() > yReal) {
				setSpeed(xSpeed,missileHomingSpeed,zSpeed);
			}else if (JetPack_JoyRide.jetpack.getYReal() < yReal) {
				setSpeed(xSpeed,-missileHomingSpeed,zSpeed);
			}else {
				setSpeed(xSpeed,0,zSpeed);
			}
		}else {
			setSpeed(xSpeed,0,0);
		}
		
		setSpeed(-JetPack_JoyRide.jetpack_speed*3.5, ySpeed,0);
		
	}
	
	public void paint(Graphics page) {
		page.fillRect(x, y, (int) Math.round(xSizeAppearance), (int)Math.round(ySizeAppearance) );
		page.setColor(Color.orange);
		page.fillRect(x + (int) Math.round(xSizeAppearance) , y + (int)Math.round(ySizeAppearance)/3, (int) Math.round(xSizeAppearance)/3, 2 * (int)Math.round(ySizeAppearance)/3 );

	}

}
