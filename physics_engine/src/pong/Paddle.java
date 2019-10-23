package pong;

import java.awt.Color;
import java.awt.Graphics;

import Physics_engine.Rectangular_prism;
import Physics_engine.Settings;
import Physics_engine.object_draw;
import Physics_engine.resizable;


public class Paddle extends Rectangular_prism implements resizable {
	public String side;
	public static double paddleHomingSpeed = Pong_runner.AI_difficulty * Pong_runner.gameSpeed;
	public int multi;
	
	public Paddle(object_draw drawer, String side1) {
		super(drawer,Settings.width/2,Settings.height/2,10,Settings.width/5,Settings.height/5,Settings.width/25,10);
		
		hasNormalCollisions = false;
		
		side = side1;
		
		drawMethod = "paint";
		setRotation(0.02,0.02,0);
		
		switch (side) {
			case("near"):
				multi = -1;
				setPos(Settings.width/2,Settings.height/2,100);
				setSize(Settings.width/5,Settings.height/5,Settings.width/5);
				setColor(Color.green);
				setRotation(0.02,0.02,0);
			break;
			
			case("far"):
				multi = 1;
				setPos(Settings.width/2,Settings.height/2,Settings.depth-200);
				setSize(Ball.getRectSizeWidth(getCenterZ())/4,Ball.getRectSizeHeight(getCenterZ())/3,Settings.width/10);
				setColor(Color.yellow);
				
			break;
		}
		
		setMass(10);
	}
	
	

	public void paint(Graphics page) {
		if (side.equals("near")) {
			page.drawRect(getX(), getY(), (int) xSize,(int) ySize);
		}else {
			page.fillRect(getX(), getY(), (int) xSize,(int) ySize);
		}
	}

	
	
	public void tertiaryUpdate() {
		
		if (Pong_runner.cheatMode) {
			if (side.equals("near")) {
				if (Pong_runner.ball.getZReal() < (300)) {
					setPos(Pong_runner.ball.getCenterX(),Pong_runner.ball.getCenterY(),100);
				}
			}
		}
		
		
		
		
		//homing in on ball
		if (side.equals("far") && Pong_runner.p2AI) {
			if (multi * Pong_runner.ball.getZSpeed() > 0) {
				
				
					//x
					if (Pong_runner.ball.getCenterX() > centerX) {
						setSpeed(paddleHomingSpeed,ySpeed,zSpeed);
					}else if (Pong_runner.ball.getCenterX() < centerX) {
						setSpeed(-paddleHomingSpeed,ySpeed,zSpeed);
					}else {
						setSpeed(0,ySpeed,zSpeed);
					}
					
					//y
					if (Pong_runner.ball.getCenterY() > centerY) {
						setSpeed(xSpeed,paddleHomingSpeed,zSpeed);
					}else if (Pong_runner.ball.getCenterY() < centerY) {
						setSpeed(xSpeed,-paddleHomingSpeed,zSpeed);
					}else {
						setSpeed(xSpeed,0,ySpeed);
					}
				
			}else {
				setSpeed(xSpeed,ySpeed,zSpeed);
			}

		}
		
		
		if (side.equals("far")) {
			if (Math.sqrt(Math.pow(0.000000000001 + getCenterX()-Settings.width/2,2)) + getXSize() >= Ball.getRectSizeWidth(getCenterZ())/2) {
				if (getXReal()-Settings.width/2 > 0) {
					setSpeed(-Math.sqrt(0.00000001+Math.pow(getXSpeed(),2)),getYSpeed(),getZSpeed());
				}else {
					setSpeed(Math.sqrt(0.00000001+Math.pow(getXSpeed(),2)),getYSpeed(),getZSpeed());
				}
			}
			
			if (Math.sqrt(Math.pow(0.000000000001 + getCenterY()-Settings.height/2,2)) + getYSize() >= Ball.getRectSizeHeight(getCenterZ())/2) {
				if (getYReal()-Settings.height/2 > 0) {
					setSpeed(getXSpeed(),-Math.sqrt(0.00000001+Math.pow(getYSpeed(),2)),getZSpeed());
				}else {
					setSpeed(getXSpeed(),Math.sqrt(0.00000001+Math.pow(getYSpeed(),2)),getZSpeed());
				}
			}
		}
		
	}
	
	public void resize() {
		
		setSize(Settings.width/10,Settings.height/10,1);
		
		switch (side) {
			case("near"):
				setPos(Settings.width/2,Settings.height/1.5,100);
				setSize(Settings.width/5,Settings.height/5,Settings.width/5);
				setColor(Color.green);
				setRotation(0.02,0.02,0);
			break;
			
			case("far"):
				setPos(Settings.width/2,Settings.height/3,Settings.depth-200);
				setSize(Settings.width/20,Settings.height/20,Settings.width/10);
				setColor(Color.yellow);
				
			break;
		}	
	}
	
	
}
