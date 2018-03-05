package tanks;

import java.awt.Color;

import Physics_engine.Settings;
import Physics_engine.object_draw;
import Physics_engine.rectangle;


public class Tank_shell extends rectangle{
	public Tank_shell(object_draw drawer, Tank tank) {
		super(drawer,tank.getX() + tank.getXSize()/2,tank.getY() - tank.getYSize()/1.5,tank.getZ(),tank.getXSize()/10,tank.getYSize()/2,1);
		setSpeed(0, -10, 0);
		setColor(Color.red);
		isFilled = true;
		affectedByBorder = true;
		isTangible = true;
		setName(tank.getObjectName() + "_bullet",1);
		drawer.add(this);
	}
	
	public void tertiaryUpdate() {
		if ((false) || (y > Settings.height) ) {
			drawer.remove(this);
		}
	}
}
