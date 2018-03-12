package pong;

import java.awt.Graphics;

import Physics_engine.Physics_engine_toolbox.faces;
import Physics_engine.Settings;
import Physics_engine.Square;
import Physics_engine.border_bounce;
import Physics_engine.object_draw;
import Physics_engine.physics_object;
import Physics_engine.resizable;

public class Ball extends Square implements resizable {
	public Ball(object_draw drawer) {
		super(drawer,Settings.width/2,Settings.height/2,0,10,10);
		setSize(Settings.width/50,Settings.width/50,0);
		
		isFilled = true;
		
		int direction;
		if (Math.random() < 0) {
			direction = -1;
		}else {
			direction = 1;
		}
		
		setSpeed( Pong_runner.ballSpeed * direction,Pong_runner.ballSpeed/2 *(Math.random() - 0.5),0);
		
	}
	
	public void isCollided (physics_object cOb,faces side) {
		setAccel(0,0,0);
		try {
			Paddle cPad = (Paddle) cOb;
			setAccel(0,(ySpeed - cPad.getYSpeed())/200,0);
			setAngularVelocity(0,0, -0.01 *(ySpeed - cPad.getYSpeed()));
			
			System.out.println((ySpeed - cPad.getYSpeed()));
			
			//allow for the player to speed up the ball with their paddle 
			setSpeed(xSpeed - cPad.getXSpeed()/2, ySpeed, zSpeed);
			
		}catch(ClassCastException c) {
			try {
				border_bounce cBor = (border_bounce) cOb;
				if (side.equals(faces.left)) {
					Pong_runner.rScore.AddScore(1);
					reset();
				}else if (side.equals(faces.right)) {
					Pong_runner.lScore.AddScore(1);
					reset();
				}
					
			}catch(ClassCastException d) {}
		}
		
	}
	
	public void reset() {
		setPos(Settings.width/2,Settings.height/2,0);
		
		int direction;
		if (Math.random() < 0.5) {
			direction = -1;
		}else {
			direction = 1;
		}
		
		setSpeed( Pong_runner.ballSpeed * direction,Math.round(xSpeed)/2 *(Math.random() - 0.5),0);
	
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/*
	public void paint(Graphics page) {
		page.fillOval(x, y, (int)xSize,(int) ySize);
	}
	*/
	public void resize() {
		setSize(Settings.width/50,Settings.width/50,0);

	}
}
