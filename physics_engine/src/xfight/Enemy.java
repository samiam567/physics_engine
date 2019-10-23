package xfight;

import java.awt.Color;

import Physics_engine.Physics_engine_toolbox.faces;
import jetpack_joyride.JetPack_JoyRide;
import Physics_engine.PointSet;
import Physics_engine.object_draw;
import Physics_engine.physics_object;
import Physics_engine.point;
import Physics_engine.resizable;
import Physics_engine.Settings;

public class Enemy extends PointSet implements resizable{

	public Enemy(object_draw drawer1,point[] constPoints) {
		super(drawer1);
		setPos(Settings.width * 0.5,Settings.height * 0.1,0);
		for (point cP : constPoints) {
			addPoint(cP);
		}
		
		initialize(); 
		finish();
		
		setPos(Settings.width * Math.random(), Math.random() * Settings.height/4 - Settings.height/4,0);
		
		resize();
		
		setName("enemy",1);
		
		hasNormalCollisions = false;

	}
	
	public void tertiaryUpdate() {
		points[0].setPos(xReal, yReal, zReal);
		if (getYReal() >Settings.height) {
			setPos(Settings.width * Math.random(), Math.random() * Settings.height/4 - Settings.height/4,0);
			setSpeed(0,XFight_runner.speed,0);
		}
		
		setSpeed(0,XFight_runner.speed,0);
	}
	
	public void isCollided(physics_object object, faces side) {
		System.out.println("enemy hit");
		
		if (object.getObjectName() == "pew" ) {
			setPos(Settings.width * Math.random(), Math.random() * Settings.height/4 - Settings.height/4,0);
			drawer.remove(object);
			XFight_runner.Score++;
		}
		
	}
	
	public void resize() {
	
	}

}
