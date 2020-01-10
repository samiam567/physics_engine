package zortex;

import java.awt.Color;

import Physics_engine.Rectangular_prism;
import Physics_engine.object_draw;
import Physics_engine.physics_object;
import Physics_engine.point;
import Physics_engine.Physics_engine_toolbox.faces;
import Physics_engine.Settings;
import Physics_engine.Vector3D;
import Physics_engine.massive;

public class Bullet extends Rectangular_prism {
	
	static double bulletSpeed = 10000;
	public Bullet(object_draw drawer1, double x, double y, double z) {
		super(drawer1, x, y, z, Settings.width/50, Settings.width/50, Settings.width/50, 10);
		setSpeed(-bulletSpeed/2000,-bulletSpeed/200,bulletSpeed);
		
		setColor(Color.LIGHT_GRAY);
		setAccel(0,9.8,0);
	
		
		System.out.println("bullet created");
		setType("enviro-move");
		setName("Bullet",1);
		setPointOfRotation(new point(drawer,Settings.width/2,Settings.height/2,0));
		isTangible = true;
	}
	
	
	@Override
	public void tertiaryUpdate() {
		
		if ( (centerZ <1 * Zortex_runner.wallZLength) && (( (centerX-Settings.width/2) > Zortex_runner.wallXSize*parallaxValue)  ||  ( (centerY-Settings.height/2) > Zortex_runner.wallYSize*parallaxValue)  )) {
			System.out.println("bullet hit tunnel walls");
			drawer.remove(this);
		}else if (! drawer.frame.contains(center.get2DPoint())) {
			System.out.println("bullet off screen");
			drawer.remove(this);
		}
	}
	
	@Override
	public void frameUpdate3(double frames) {
		for (massive cOb : drawer.getTangibles()) {
			if (cOb.getName().equals("Enemy")) {	
				if (Math.abs(cOb.getCenterZ()-getCenterZ())	< 0.5*(getZSpeed()*frames+getZSize()/2+cOb.getZSize()/2)) {		
					if (Math.abs(cOb.getCenterX()-getCenterX())	< 0.5*(getXSpeed()*frames+getXSize()/2+cOb.getXSize()/2)) {
						if (Math.abs(cOb.getCenterY()-getCenterY())	< 0.5*(getYSpeed()*frames+getYSize()/2+cOb.getYSize()/2)) {
							cOb.isCollided(this,faces.none);
							Zortex_runner.Score++;
							
						}
					}
				}
			}
		}
				
	}
	
	
	
	
	
	
	

}
