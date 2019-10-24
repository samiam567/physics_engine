package xfight;

import java.awt.Color;

import Physics_engine.Physics_engine_toolbox.faces;
import jetpack_joyride.JetPack_JoyRide;
import Physics_engine.Physics_engine_toolbox;
import Physics_engine.PointSet;
import Physics_engine.object_draw;
import Physics_engine.physics_object;
import Physics_engine.point;
import Physics_engine.resizable;
import Physics_engine.Settings;

public class Enemy extends PointSet implements resizable {

	private int speed = 30;
	public Enemy(object_draw drawer1,point[] constPoints) {
		super(drawer1);
		setPos(Settings.width * 0.5,Settings.height * 0.1,0);
		for (point cP : constPoints) {
			addPoint(new point(drawer,cP.getXReal(),cP.getYReal(),cP.getZReal()));
		}
		
		initialize(); 
		finish();
		
		setPos(Settings.width * Math.random(), Math.random() * Settings.height/4 - Settings.height/4,0);
		
		resize();
		
		setName("enemy",1);
		setSize(getXSize(),getYSize(),5);
		
		hasNormalCollisions = false;

	}
	
	@Override
	public void tertiaryUpdate() {
		points[0].setPos(xReal, yReal, zReal);
		if (getYReal() >Settings.height) {
			setPos(Settings.width * Math.random(), Math.random() * Settings.height/4 - Settings.height/4,0);
			setSpeed(0,XFight_runner.speed,0);
		}
		
		double r = Physics_engine_toolbox.distance(center, XFight_runner.ship.getCenter());
		
		setSpeed(speed*(XFight_runner.ship.getCenterX() - getCenterX() )/(r),speed*(-getCenterY() + XFight_runner.ship.getCenterY())/(r),0);
		
	}
	
	@Override
	public void isCollided(physics_object object, faces side) {
		System.out.println("enemy hit");
		
		if (object.getObjectName() == "pew" ) {
			
			double rand1 = Math.random(), rand2 = Math.random();
			setPos(2*(Math.random()-0.5) * (Settings.width + Settings.height)*Math.cos(rand1*2*Math.PI),2*(Math.random()-0.5) * (Settings.width + Settings.height)*Math.sin(rand1*2*Math.PI),0);
			drawer.remove(object);
			XFight_runner.Score++;
		}
		
	}
	
	public void resize() {
	
	}

}
