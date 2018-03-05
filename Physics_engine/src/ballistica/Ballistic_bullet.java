package ballistica;

import java.awt.Color;

import Physics_engine.Settings;
import Physics_engine.object_draw;
import Physics_engine.rectangle;

public class Ballistic_bullet extends rectangle {
	
	public Ballistic_bullet(object_draw drawer, Ballistic_ship ship) {
		super(drawer,ship.getX() + ship.getXSize()/2,ship.getY() - ship.getYSize()/1.5,ship.getZ(),ship.getXSize()/10,ship.getYSize()/2,1);
		setSpeed(0, -10, 0);
		setColor(Color.red);
		isFilled = true;
		affectedByBorder = true;
		isTangible = true;
		setName(ship.getObjectName() + "_bullet",1);
		drawer.add(this);
	}
	
	public void tertiaryUpdate() {
		if ((false) || (y > Settings.height) ) {
			drawer.remove(this);
		}
	}
}
