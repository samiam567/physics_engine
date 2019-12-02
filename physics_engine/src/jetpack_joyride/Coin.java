package jetpack_joyride;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import Physics_engine.*;
import Physics_engine.Physics_engine_toolbox.faces;

public class Coin extends Square {
	
	public Coin(object_draw drawer1, int x, int y) {
		super(drawer1, x, y, 1, 20, 0.1);
		affectedByBorder = false;
		name = "_coin";
		setColor(Color.YELLOW);
		drawMethod = "paint";
		
	}

	public void coinReLocate() {
		setPos(Settings.width * 20 * Math.random() + (Settings.width+100), Math.random() * (Settings.height-xSize-150), getZReal());
		setSpeed(-JetPack_JoyRide.jetpack_speed, 0,0);
	}	
	
	public void isCollided(physics_object object, faces side) {
		coinReLocate();
	}
	public void secondaryUpdate() {
		
			if (getXReal() < 0) {
				setPos(Settings.width+100, Math.random() * (Settings.height-getXSize()-150), getZReal());
				setSpeed(-JetPack_JoyRide.jetpack_speed, 0,0);
			}else if ( (getXReal()+10 < JetPack_JoyRide.jetpack.getXReal()) || (getXReal()-50 >JetPack_JoyRide.jetpack.getXReal()) ) {
				isTangible = false;
			}else {
				isTangible = true;
			}
		
			setSpeed(-JetPack_JoyRide.jetpack_speed, 0,0);
		}
		

	
	public void paint(Graphics page) {
		if (JetPack_JoyRide.pictureGraphics) {
		
		}else {
			page.setColor(color);
			page.fillOval(getX(), getY(), (int) Math.round(xSize),(int) Math.round(ySize));
		}
	}
}
