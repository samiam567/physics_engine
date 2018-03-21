package pong;

import java.awt.Color;
import java.awt.Graphics;

import Physics_engine.Rectangular_prism;
import Physics_engine.Settings;
import Physics_engine.object_draw;
import Physics_engine.rectangle;
import Physics_engine.resizable;


public class Paddle extends Rectangular_prism implements resizable {
	public String side;
	public static double paddleHomingSpeed = Pong_runner.AI_difficulty * Pong_runner.gameSpeed;

	
	public Paddle(object_draw drawer, String side1) {
		super(drawer,Settings.width/2,Settings.height/2,10,Settings.width/5,Settings.height/5,Settings.width/25,10);
		side = side1;
		
		drawMethod = "paint";
		setRotation(0.02,0.02,0);
		
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
	
	
	public void paint(Graphics page) {
		page.fillRoundRect(x, y, (int) xSize,(int) ySize,(int) xSize/5,(int) ySize/5);
	
	}
	
	
	
	public void tertiaryUpdate() {
		
		if (Pong_runner.cheatMode) {
			if (side.equals("near")) {
				if (Pong_runner.ball.getZReal() < (200)) {
					setPos(Pong_runner.ball.getCenterX(),Pong_runner.ball.getCenterY(),100);
				}
			}
		}
		
		
		//homing in on ball
		if (side.equals("far") && Pong_runner.p2AI) {
			if (Pong_runner.ball.getZSpeed() > 0) {
				
				
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
