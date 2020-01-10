package zortex;

import java.awt.Color;
import java.awt.Graphics;

import Physics_engine.Rectangular_prism;
import Physics_engine.Vector3D;
import Physics_engine.object_draw;
import calculator.Settings;

public class Gun extends Rectangular_prism {
	public Gun(object_draw drawer1) {
		super(drawer1, Settings.width*.45,Settings.height*.71,100,Settings.width/100,Settings.width/50,Settings.width/3,1);
		isAnchored = true;
		setType("gun");
		
		Vector3D rotVec = new Vector3D(drawer1,-1,-1,10);
		rotVec.divide(rotVec.getR()); //convert to unit vector
		rotVec.multiply(3.15); //make the vec the length we want to rotate
		setVectorRotation(rotVec);
		
		
		isTangible = false;
		
		isFilled = true;
		setColor(Color.LIGHT_GRAY);
		
	}
	
	public void fire() {
		drawer.add(new Bullet(drawer,points[0].getXReal(),points[0].getYReal() - getYSize()/2,points[0].getZReal() + getZSize()/2));
	}
	
	@Override
	public void paint(Graphics page) {
		super.paint(page);
		page.setColor(Color.red);
		page.drawRect((int)(Settings.width/2-Zortex_runner.bodyXSize/2),(int) (3*Settings.width/4-Zortex_runner.bodyYSize/2),(int) (Zortex_runner.bodyXSize),(int)(Zortex_runner.bodyYSize));
	}
}
