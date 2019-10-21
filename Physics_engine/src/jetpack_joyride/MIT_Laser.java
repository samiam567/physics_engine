package jetpack_joyride;

import java.awt.Color;
import java.awt.Graphics;

import Physics_engine.Settings;
import Physics_engine.object_draw;
import Physics_engine.physics_object;
import Physics_engine.Physics_engine_toolbox.faces;

public class MIT_Laser extends Laser {
	
	private static double laserXSize = Settings.width/100, laserYSize = Settings.width/10;
	
	public MIT_Laser(object_draw drawer1, int x, int y) {
		super(drawer1,400,200,(19*laserXSize),laserYSize);
		setSize(15*laserXSize,laserYSize,1);
	}
	
	public void tertiaryUpdate() {
		JetPack jp = JetPack_JoyRide.jetpack;
		if ((jp.getXReal()< getXReal() + getXSize()) && (jp.getXReal() + jp.getXSize()  > getXReal()) && (jp.getYReal() < getYReal() + getYSize()) && (jp.getYReal() + jp.getYSize() > getYReal()) ) {
		
			jp.isCollided(this, faces.none);
		}
	}
	
	@Override
	public void paint(Graphics page) {
		
		laserXSize = (getXSize())/19;
		laserYSize = getYSize();
	
		
		page.setColor(Color.red);
		int x = getX(),y = getY();
		
		//drawing M
			//first
		page.fillRect(x,y, (int) laserXSize,(int)laserYSize);
		
			//second
		x += 3*laserXSize;
		page.fillRect(x,y, (int) laserXSize,(int)laserYSize);
		
			//third
		x += 3*laserXSize;
		page.fillRect(x,y, (int) laserXSize,(int)laserYSize);
		
		//drawing I
		x += 6 * laserXSize;
		page.fillRect(x,y, (int) laserXSize,(int)laserYSize);
		
		//drawing T
		//I
		x += 7 * laserXSize;
		page.fillRect(x,y, (int) laserXSize,(int)laserYSize);
		
		//--
		page.fillRect((int) (x-laserYSize/4 + laserXSize/2),(int) (y),(int) (laserYSize/2),(int) (laserXSize));
		
		
	
	
	}
	

}
