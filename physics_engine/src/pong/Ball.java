package pong;

import java.awt.Color;
import java.awt.Graphics;

import Physics_engine.Physics_engine_toolbox.faces;
import Physics_engine.Settings;
import Physics_engine.Sphere;
import Physics_engine.border_bounce;
import Physics_engine.object_draw;
import Physics_engine.physics_object;
import Physics_engine.resizable;

public class Ball extends Sphere implements resizable {
	
	public static double ballZSpeed = Pong_runner.gameSetSpeed * 100;
	
	public Ball(object_draw drawer) {
		super(drawer,Settings.width/2,Settings.height/2,Settings.depth/2,Settings.width/15,10,Math.PI/25);
		hasNormalCollisions = false;
		int direction;
		if (Math.random() < 0) {
			direction = -1;
		}else {
			direction = 1;
		}
		
		
		
		setSpeed(Pong_runner.ballSpeed/2 * (Math.random() - 0.5) ,Pong_runner.ballSpeed/2 *(Math.random() - 0.5),ballZSpeed * direction );
		
		setMass(10);
		
		setColor(Color.cyan);
	}
	
	public void tertiaryUpdate() {
		ballZSpeed = Pong_runner.gameSetSpeed * 100;
		double size = 100 * Settings.width/(getCenterZ() + 500);
		setSize(size,size,size);
		

		
		if (Math.sqrt(Math.pow(0.000000000001 + getCenterX()-Settings.width/2,2)) + size >= getRectSizeWidth(getCenterZ())/2) {
			if (getXReal()-Settings.width/2 > 0) {
				setSpeed(-Math.sqrt(0.00000001+Math.pow(getXSpeed(),2)),getYSpeed(),getZSpeed());
			}else {
				setSpeed(Math.sqrt(0.00000001+Math.pow(getXSpeed(),2)),getYSpeed(),getZSpeed());
			}
		}
		
		if (Math.sqrt(Math.pow(0.000000000001 + getCenterY()-Settings.height/2,2)) + size >= getRectSizeHeight(getCenterZ())/2) {
			if (getYReal()-Settings.height/2 > 0) {
				setSpeed(getXSpeed(),-Math.sqrt(0.00000001+Math.pow(getYSpeed(),2)),getZSpeed());
			}else {
				setSpeed(getXSpeed(),Math.sqrt(0.00000001+Math.pow(getYSpeed(),2)),getZSpeed());
			}
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
			setAccel(-(xSpeed + cPad.getXSpeed())/200,(ySpeed - cPad.getYSpeed())/200,0);
			setAngularVelocity(0.01 *(ySpeed - cPad.getYSpeed()),(xSpeed + cPad.getXSpeed())/200,0);
			
			System.out.println((ySpeed - cPad.getYSpeed()));
			
			
			
			
		}catch(ClassCastException c) {
			try {
				@SuppressWarnings("unused")
				border_bounce cBor = (border_bounce) cOb;
				if (side.equals(faces.near)) {
					Pong_runner.fScore.setColor(Color.BLUE);
					Pong_runner.fScore.AddScore(1);
					object_draw.Wait(9);
					reset();
					Pong_runner.fScore.setColor(Color.GREEN);
				}else if (side.equals(faces.far)) {
					Pong_runner.nScore.setColor(Color.BLUE);
					Pong_runner.nScore.AddScore(1);
					object_draw.Wait(9);
					
					reset();
					Pong_runner.nScore.setColor(Color.GREEN);
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

	static int getRectSizeWidth(double z) {
		return (int) (1000*Settings.width/(z + 500));
	}
	
	static int getRectSizeHeight(double z) {
		return (int) (1000*Settings.height/(z + 500));
	}
	public void paint(Graphics page) {
		
		//drawing border boxes
		
		int alpha = 150; // 1/transparency of the shape
		Color barsColor = Color.getHSBColor(Color.red.getRGB(),1f, 150 );
		page.setColor(new Color(barsColor.getRed(),barsColor.getGreen(),barsColor.getBlue(),alpha));
		for (int z = 0; z < Settings.depth; z+= 50) {
			page.drawRect(Settings.width/2-getRectSizeWidth(z)/2,Settings.height/2-getRectSizeHeight(z)/2,getRectSizeWidth(z),getRectSizeHeight(z));
			
		}
		
		page.setColor(getColor());
		
		super.paint(page);	
	
		//distance bars
		page.fillRect(0, 0, Settings.width/100, (int) (Settings.height - ((Settings.height) * getZ())/Settings.depth));
		page.drawRect(0, 0, Settings.width/100, (int) (Settings.height));
		
		page.fillRect((int) (Settings.width - Settings.width/100 - 20), 0, Settings.width/100, (int) (Settings.height - ((Settings.height) * getZ())/Settings.depth));
		page.drawRect((int) (Settings.width - Settings.width/100 - 20), 0, Settings.width/100, (int) (Settings.height));
		
		//drawing ball position box
		page.drawRect(Settings.width/2-getRectSizeWidth(getCenterZ())/2,Settings.height/2-getRectSizeHeight(getCenterZ())/2,getRectSizeWidth(getCenterZ()),getRectSizeHeight(getCenterZ()));
		
		
	}

	public void resize() {
		setSize(Settings.width/50,Settings.width/50,Settings.width/50);

	}
}
