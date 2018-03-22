package pong;

import java.awt.Color;
import java.awt.Graphics;

import Physics_engine.Physics_engine_toolbox.faces;
import Physics_engine.Settings;
import Physics_engine.Sphere;
import Physics_engine.Square;
import Physics_engine.border_bounce;
import Physics_engine.object_draw;
import Physics_engine.physics_object;
import Physics_engine.resizable;

public class Ball extends Sphere implements resizable {
	
	public static double ballZSpeed = Pong_runner.gameSetSpeed * 100;
	
	public Ball(object_draw drawer) {
		super(drawer,Settings.width/2,Settings.height/2,Settings.depth/2,Settings.width/15,10,Math.PI/15);
		
		int direction;
		if (Math.random() < 0) {
			direction = -1;
		}else {
			direction = 1;
		}
		
		
		
		setSpeed(Pong_runner.ballSpeed/2 * (Math.random() - 0.5) ,Pong_runner.ballSpeed/2 *(Math.random() - 0.5),ballZSpeed * direction );
		
	}
	
	public void tertiaryUpdate() {
		double size = 100 * Settings.width/(zReal + 500);
		setSize(size,size,size);
		
		if (zSpeed > 0) {
			setColor(Color.yellow);
		}else if (zSpeed < 0) {
			setColor(Color.green);
		}else {
			setColor(Color.red);
		}
	}
	
	public void isCollided (physics_object cOb,faces side) {

		setAccel(0,0,0);
		
		try {
			Paddle cPad = (Paddle) cOb;
			
			System.out.println("PAD");
			
			setSpeed(xSpeed,ySpeed,-zSpeed);
			System.out.println("speed: " + zSpeed);
			
			setPos(centerX,centerY,centerZ + drawer.getFrameStep() + zSpeed);
			updatePoints();
			
			//spin
			setAccel((xSpeed + cPad.getXSpeed())/200,(ySpeed - cPad.getYSpeed())/200,0);
			setAngularVelocity((xSpeed + cPad.getXSpeed())/200,0.01 *(ySpeed - cPad.getYSpeed()),0);
			
			System.out.println((ySpeed - cPad.getYSpeed()));
			
			
			
			
		}catch(ClassCastException c) {
			try {
				border_bounce cBor = (border_bounce) cOb;
				if (side.equals(faces.near)) {
					Pong_runner.fScore.AddScore(1);
					reset();
				}else if (side.equals(faces.far)) {
					Pong_runner.nScore.AddScore(1);
					reset();
				}
					
			}catch(ClassCastException d) {}
		}
		
		
	}
	
	public void reset() {
		setPos(Settings.width/2,Settings.height/2,Settings.depth/2);
		
		int direction;
		if (Math.random() < 0.5) {
			direction = -1;
		}else {
			direction = 1;
		}
		
		setSpeed(Math.round(Pong_runner.ballSpeed)/2 *(Math.random() - 0.5) ,Math.round(Pong_runner.ballSpeed)/2 *(Math.random() - 0.5),ballZSpeed * direction);
	
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	public void paint(Graphics page) {
		super.paint(page);
		
		
		//distance bars
		page.fillRect(0, 0, Settings.width/100, (int) (((Settings.height*0.85) * z)/Settings.depth));
		page.drawRect(0, 0, Settings.width/100, (int) (Settings.height*0.85));
		
		page.fillRect((int) (Settings.width - Settings.width/100 - 20), 0, Settings.width/100, (int) (((Settings.height*0.85) * z)/Settings.depth));
		page.drawRect((int) (Settings.width - Settings.width/100 - 20), 0, Settings.width/100, (int) (Settings.height*0.85));

	}

	public void resize() {
		setSize(Settings.width/50,Settings.width/50,Settings.width/50);

	}
}
