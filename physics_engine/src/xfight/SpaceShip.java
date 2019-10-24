package xfight;

import java.awt.Color;

import Physics_engine.Physics_engine_toolbox.faces;
import Physics_engine.PointSet;
import Physics_engine.object_draw;
import Physics_engine.physics_object;
import Physics_engine.point;
import Physics_engine.pointed;
import Physics_engine.resizable;
import Physics_engine.Settings;
import Physics_engine.massive;

public class SpaceShip extends PointSet implements resizable{

	public static final int turningSpeed = 15;
	
	public SpaceShip(object_draw drawer1,point[] constPoints) {
		super(drawer1);
		
		for (point cP : constPoints) {
			addPoint(new point(drawer,cP.getXReal(),cP.getYReal(),cP.getZReal()));
		}
		
		initialize();
		finish();
		
		isFilled = true;
		setColor(Color.RED);
	
	
		resize();
		
	}
	
	public void isCollided(physics_object object, faces side) {
		System.out.println("game over");
		System.out.println(object.getObjectName());
		
	}
	
	public void resize() {
		setPos(Settings.width*0.5,Settings.height -ySize,0);
	}

}
