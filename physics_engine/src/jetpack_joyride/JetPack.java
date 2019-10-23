package jetpack_joyride;

import java.awt.Color;
import java.awt.Graphics;
import Physics_engine.*;
import Physics_engine.Physics_engine_toolbox.faces;


public class JetPack extends rectangle {
	
	public double fireSize = 0.4;
	
	public double power = 800;
	
	public double current_power;
	
	public JetPack(object_draw drawer1, int x, int y, int z, int size, double mass) {
		super(drawer1,x, y, z, size*2,size, mass);
		drawMethod = "paint";
	}
	
	public void tertiaryUpdate() {
		setSpeed(0,ySpeed,0);
		current_power = power + JetPack_JoyRide.jetpack_speed ;
		if (getYReal() < 0) {
			setPos(getCenterX(),40, getCenterZ());
			setSpeed(0,Math.abs(getYSpeed()),0);
		}else if (getYReal() + getYSize() > Settings.height-40) {
			setPos(getCenterX(),Settings.height - 2*getYSize(), getCenterZ());
			setSpeed(0,-Math.abs(getYSpeed()),0);
		}
	}
	
	public void isCollided(physics_object object, faces side) {
		if (object != null) {
			try {
			//this try/catch checks if the object is of the Laser class
			    Laser c = (Laser) object;
			    System.out.println("game over");
			    System.out.println("you hit " + object.getObjectName());
				JetPack_JoyRide.game_over = 1;
				
			} catch (ClassCastException e) {}
			
			try {
				//this try/catch checks if the object is of the Missile class
			    Missile c = (Missile) object;
			    System.out.println("game over");
			    System.out.println("you hit " + object.getObjectName());
				JetPack_JoyRide.game_over = 1;
			} catch (ClassCastException e) {}
			
			//this try/catch checks if the object is of the Coin class
			try {
			    Coin coin = (Coin) object;
			    JetPack_JoyRide.coins++;
			    JetPack_JoyRide.coinsEarned++;
			    coin.coinReLocate();
			    
			    if (side.equals(faces.top) || side.equals(faces.bottom)) {
			    	setSpeed(xSpeed,-ySpeed,zSpeed);
			    }
			    
			} catch (ClassCastException e) {}
			
	
		}
	}
	
	
	
	public void paint(Graphics page) {

		page.setColor(color);
		page.fillRect(getX(), getY(), (int) Math.round(ySize/2), (int) Math.round(ySize));
		page.fillRect((int) Math.round(getX() + ySize/2) - 3, getY(), (int) Math.round(ySize/2), (int) Math.round(ySize));
		
		page.setColor(Color.DARK_GRAY);
		page.drawRect(getX(), getY(), (int) Math.round(ySize/2), (int) Math.round(ySize));
		page.drawRect((int) Math.round(getX() + ySize/2) , getY(), (int) Math.round(ySize/2)-3, (int) Math.round(ySize));
		
		if (fireSize > 0.4) {
			page.setColor(Color.yellow);
			page.fillRect(getX()+3,(int) ( getY()+Math.round(ySize)), (int) Math.round(ySize/2)-6, (int) Math.round(fireSize*ySize/2));
			page.fillRect((int) Math.round(ySize/2)+getX()+1,(int) ( getY()+Math.round(ySize)), (int) Math.round(ySize/2)-6, (int) Math.round(fireSize*ySize/2));
		}
		
		page.setColor(Color.orange);
		page.fillRect(getX()+2,(int) ( getY()+Math.round(ySize)), (int) Math.round(ySize/2)-4, (int) Math.round(0.45*ySize/2));
		page.fillRect((int) Math.round(ySize/2)+getX(),(int) ( getY()+Math.round(ySize)), (int) Math.round(ySize/2)-4, (int) Math.round(0.45*ySize/2));
	
		}

}
