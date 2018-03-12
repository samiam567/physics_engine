package pong;

import java.awt.Color;
import java.awt.Graphics;

import Physics_engine.Settings;
import Physics_engine.object_draw;
import Physics_engine.rectangle;
import Physics_engine.resizable;


public class Paddle extends rectangle implements resizable {
	public String side;
	public static double paddleHomingSpeed = Pong_runner.AI_difficulty * Pong_runner.gameSpeed;

	
	public Paddle(object_draw drawer, String side1) {
		super(drawer,Settings.width/10,Settings.height/2,0,Settings.width/90,Settings.height/10,10);
		side = side1;
		isRotatable = false;
		isFilled = true;
		drawMethod = "fillRect";
		setColor(Color.WHITE);
		
		
		switch (side) {
		case("left"):
			setPos(Settings.width/9,Settings.height/2,0);
		break;
		
		case("right"):
			setPos(0.9 * Settings.width,Settings.height/2,0);
			break;
		}
	}
	
	public void tertiaryUpdate() {
		
		if (Pong_runner.cheatMode) {
			if (side.equals("left")) {
				if (Pong_runner.ball.getXReal() < (200)) {
					setPos(150,Pong_runner.ball.getYReal(),0);
				}
			}
		}
		
		if (side.equals("right") && Pong_runner.p2AI) {
			//homing in on balll
			if (Pong_runner.ball.getYReal() > yReal) {
				setSpeed(xSpeed,paddleHomingSpeed,zSpeed);
			}else if (Pong_runner.ball.getYReal() < yReal) {
				setSpeed(xSpeed,-paddleHomingSpeed,zSpeed);
			}else {
				setSpeed(xSpeed,0,zSpeed);
			}
		}
	}
	
	public void resize() {
		
		setSize(Settings.width/90, Settings.height/10,0);
		
		switch (side) {
			case("left"):
				setPos(0.1 * Settings.width,Settings.height/2,0);
				break;
			
			case("right"):
				setPos(0.9 * Settings.width,Settings.height/2,0);
				break;
		}
	}
	
	
}
