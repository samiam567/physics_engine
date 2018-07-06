package xfight;

import java.awt.Color;
import java.awt.Graphics;

import Physics_engine.Physics_engine_toolbox.faces;
import Physics_engine.PointSet;
import Physics_engine.object_draw;
import Physics_engine.physics_object;
import Physics_engine.point;
import Physics_engine.Vector2D;

public class Pew extends PointSet {

	public Pew(object_draw drawer1,point[] constPoints) {
		super(drawer1);
		
		for (point cP : constPoints) {
			addPoint(cP);
		}
		
		initialize();
		finish();
		
		isFilled = true;
		
		setColor(Color.ORANGE);
		
		setName("pew",1);
		
		double[] pewSpeeds = Vector2D.polarToRectangular(XFight_runner.pewSpeed, Math.PI/2 - XFight_runner.ship.getZRotation());
  		setSpeed(pewSpeeds[0] + XFight_runner.ship.getXSpeed(), -pewSpeeds[1], 0);        	  		
  		setRotation(XFight_runner.ship.getXRotation(),XFight_runner.ship.getYRotation(),XFight_runner.ship.getZRotation());
  		setPos(XFight_runner.ship.getPoints()[0].getXReal(), XFight_runner.ship.getPoints()[0].getYReal()-100, 0);
  		
  		setMass(100);
  		
  		hasNormalCollisions = false;
  		
  	
		
	}
	
	public void tertiaryUpdate() {
		if (getYReal() < 0 ) {
			drawer.remove(this);
		}
	}
	
	
	public void paint(Graphics page) {
		
		super.paint(page);
	}
	


}
