package zortex;

import java.awt.Color;

import Physics_engine.Physics_engine_toolbox.faces;
import jetpack_joyride.JetPack_JoyRide;
import Physics_engine.Physics_engine_toolbox;
import Physics_engine.Physics_shape;
import Physics_engine.object_draw;
import Physics_engine.physics_object;
import Physics_engine.point;
import Physics_engine.rectangle;
import Physics_engine.resizable;
import Physics_engine.Settings;
import Physics_engine.Sphere;
import Physics_engine.Vector3D;
import Physics_engine.massive;
import Physics_engine.movable;

public class Enemy extends Sphere implements resizable, movable {

	public static double zSet = 10*Zortex_runner.wallZLength;
	public static int setSpeed = (int) (zSet/50);
	private double speed = setSpeed  * Math.sqrt(Settings.width*Settings.width + Settings.height*Settings.height)/1000;

	public Enemy(object_draw drawer1, double zDist) {
		super(drawer1,Zortex_runner.endWallRect.getCenterX(),Zortex_runner.endWallRect.getCenterY(),zDist,Settings.width/4,10,Math.PI/10);
	
		resetPos();
		setPos(getCenterX(),getCenterY(),zDist);
		System.out.println("enemy created");
		
		
		
	
		setName("Enemy",1);
		setType("enviro-move");
		
		setSize(Settings.width/4,Settings.width/4, Settings.width/4);
	
	
		setHasNormalCollisions(false);
		setColor(Color.green);
		setPointOfRotation(new point(drawer,Settings.width/2,Settings.height/2,0));
		isTangible = true;
	}
	
	@Override
	public void tertiaryUpdate() {
		
	
		setSpeed(getXSpeed(),getYSpeed(),-speed);
		
		if ((getCenterZ() < 0) && (getCenterX() - Settings.width/2 < Zortex_runner.bodyXSize) && (getCenterX() - 3*Settings.height/4 < Zortex_runner.bodyYSize) ) {
			Zortex_runner.game_over = true;
			resetPos();
		}
		
		
		
		
	}
	
	
	@Override
	public void isCollided(physics_object object, faces side) {
		
		
		if (object.getObjectName().equals("Bullet") ) {
			System.out.println("enemy hit");
			resetPos();
				
			System.out.println("enemy hit");
			//creating explosion particles
			for (float i = 0; i < 2*Math.PI; i += (2*Math.PI)/Enemy_explosion_particles.particleNumber) {
				drawer.add(new Enemy_explosion_particles(drawer,getCenterX(),getCenterY(), i));
			}
			
			drawer.remove(object);
			
			if (Zortex_runner.Score % 5 == 0) {
				drawer.add(new Enemy(drawer,zSet));
				Enemy.setSpeed += 0.2;
			}
			
			
			updateCenter();
			updatePos();
			updatePoints();
			resetPos();
			
			
		}
		
		
	}

	
	public void resize() {
		resetPos();
	}
	
	public void resetPos() {
		
		
		double r = 0.5*Math.sqrt(Zortex_runner.wallXSize*Zortex_runner.wallXSize + Zortex_runner.wallYSize*Zortex_runner.wallYSize) * (Settings.distanceFromScreen) / (zSet + Settings.distanceFromScreen);
		
		double rand1 = Math.random();
		speed = (Math.random()+0.5) * setSpeed  * Math.sqrt(Settings.width*Settings.width + Settings.height*Settings.height)/1000;
		
		setPos( Zortex_runner.endWallRect.getCenterX() +  r * Math.cos(rand1*2*Math.PI), Zortex_runner.endWallRect.getCenterY() + r*Math.sin(rand1*2*Math.PI),zSet);
		
	
	}
}
