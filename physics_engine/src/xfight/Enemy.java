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

	private int setSpeed = 15;
	private double speed = setSpeed  * Math.sqrt(Settings.width*Settings.width + Settings.height*Settings.height)/1000;
	public Enemy(object_draw drawer1,point[] constPoints) {
		super(drawer1);
		
		for (point cP : constPoints) {
			addPoint(new point(drawer,cP.getXReal(),cP.getYReal(),cP.getZReal()));
		}
		
		initialize(); 
		finish();
		
		resetPos();
		
		resize();
		
		setName("enemy",1);
		setSize(getXSize(),getYSize(),5);
		
		setColor(Color.gray);
		isFilled = true;
		hasNormalCollisions = false;
		
		setAngularVelocity(0,0, (Math.random()-0.5) * 4);

	}
	
	@Override
	public void tertiaryUpdate() {
		points[0].setPos(xReal, yReal, zReal);
		
		double r = Physics_engine_toolbox.distance(center, XFight_runner.ship.getCenter());
		
		setSpeed(speed*(XFight_runner.ship.getCenterX() - getCenterX() )/(r),speed*(-getCenterY() + XFight_runner.ship.getCenterY())/(r),0);
		
	}
	
	@Override
	public void isCollided(physics_object object, faces side) {
		System.out.println("enemy hit");
		
		if (object.getObjectName() == "pew" ) {
			//creating explosion particles
			for (float i = 0; i < 2*Math.PI; i += (2*Math.PI)/Enemy_explosion_particles.particleNumber) {
				drawer.add(new Enemy_explosion_particles(drawer,getCenterX(),getCenterY(), i));
			}
			resetPos();
			drawer.remove(object);
			XFight_runner.Score++;
			if (XFight_runner.Score % 10 == 0) {
				drawer.add(new Enemy(drawer,XFight_runner.enemyBlueprint));
				speed += 0.2;
			}
			
			
			
		}else if (object.getObjectName().equals("spaceShip")) {
			XFight_runner.game_over = true;
		}
		
		
	}
	
	public void resize() {
		resetPos();
	}
	
	public void resetPos() {
		double rand1 = Math.random();
		setPos( (Math.random()+0.25) * (Settings.width + Settings.height)*Math.cos(rand1*2*Math.PI),(Math.random()+0.25) * (Settings.width + Settings.height)*Math.sin(rand1*2*Math.PI),0);
		
		while(drawer.frame.checkIsInFrame(this)) {
			rand1 = Math.random();
			setPos( (Math.random()+0.25) * (Settings.width + Settings.height)*Math.cos(rand1*2*Math.PI),(Math.random()+0.25) * (Settings.width + Settings.height)*Math.sin(rand1*2*Math.PI),0);
			updatePoints();
		}
		
		speed = (Math.random()+0.5) * setSpeed  * Math.sqrt(Settings.width*Settings.width + Settings.height*Settings.height)/1000;
	}
}
