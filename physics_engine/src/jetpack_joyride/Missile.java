package jetpack_joyride;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import Physics_engine.Settings;
import Physics_engine.object_draw;
import Physics_engine.physics_object;
import Physics_engine.rectangle;

public class Missile extends rectangle {

	public static final int missileHomingSpeed = 8;
	
	private static final double missileSpeedMultiplier = 2.5;
	
	public Missile(object_draw drawer1,double d, double y) {
		super(drawer1,d, y, 0, 20, 5, 0);
		drawMethod = "paint";
		setColor(Color.red);
		affectedByBorder = false;
		name = "thing";
	}

	public void secondaryUpdate() {
		if (getXReal() < 0) {
			setPos(Settings.width+1000 + Math.random() * 1500, Math.random() * (Settings.height-getXSize()-150), getZReal());
			setSpeed(-JetPack_JoyRide.jetpack_speed * missileSpeedMultiplier,ySpeed,0);
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
		
		setSpeed(-JetPack_JoyRide.jetpack_speed*missileSpeedMultiplier, ySpeed,0);
		
	}
	
	public void paint(Graphics page) {
		
		
		//fire
		page.setColor(Color.orange);
		page.fillRect(x + (int) Math.round(0.9 * xSizeAppearance) , y + (int)Math.round(ySizeAppearance)/5, (int) Math.round(xSizeAppearance)/2, 2 * (int)Math.round(ySizeAppearance)/3 );
			
		page.setColor(Color.red);	
		
		//body
		page.fillRoundRect(x, y, (int) Math.round(xSizeAppearance), (int)Math.round(ySizeAppearance),  (int)( ySize/2) ,(int)(ySize/2));
			
		//missile warning sign
		if (x > Settings.width) {
			page.fillRoundRect(Settings.width - (int)(2.2 * ySize), getY(), (int)( ySize), (int)( ySize), (int)( ySize/2) ,(int)(ySize/2));
		}
		
			
		
	}

}
