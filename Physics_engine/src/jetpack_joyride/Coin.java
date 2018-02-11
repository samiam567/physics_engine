package jetpack_joyride;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import Physics_engine.*;

public class Coin extends Square {
	
	public Coin(int x, int y) {
		super(x, y, 1, 20, 0.1);
		affectedByBorder = false;
		name = "thing";
		setColor(Color.YELLOW);
		drawMethod = "paint";
		
	}

	public void coinReLocate() {
		setPos(Settings.width * 2 * Math.random() + (Settings.width+100), Math.random() * (Settings.height-xSize-150), getZReal());
		setSpeed(-JetPack_JoyRide.jetpack_speed, 0,0);
	}
	
	public void checkForCollision(physics_object current_object,ArrayList<physics_object> objects) {
		if ( (Math.abs(getCenterX() - current_object.getCenterX()) < (current_object.getXSize()/2+xSize/2)) && (Math.abs(getCenterY() - current_object.getCenterY()) < (current_object.getYSize()/2+ySize/2)) /* && (Math.abs(centerZ - current_object.centerZ) < (current_object.getZSize()/2+zSize/2))*/ ) {	
			if (current_object.name == "jetpack") {
				coinReLocate();
				JetPack_JoyRide.coins++;
				JetPack_JoyRide.coinsEarned++;
			}
		}
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
			page.fillOval(x, y, (int) Math.round(xSize),(int) Math.round(ySize));
		}
	}
}
