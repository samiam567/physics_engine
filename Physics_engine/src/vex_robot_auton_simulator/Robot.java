package vex_robot_auton_simulator;

import java.awt.Color;
import java.awt.Graphics;

import Physics_engine.Rectangular_prism;
import Physics_engine.object_draw;

public class Robot extends Rectangular_prism {

	private static final double robotPosErrThreshold = 0.02;
	
	private static final double robotXSize = 13.5 * Auton_simulator_runner.inchToPixelConversion;
	private static final double robotYSize = 15.25 * Auton_simulator_runner.inchToPixelConversion;
	private static final double robotZSize = 17.5 * Auton_simulator_runner.inchToPixelConversion;
	
	
	public static double robSqX = (2) - 0.8;
	public static double robSqY = (6) - 0.3;
	public double angle = 0;
	
	public static double intakeSize = 5.5 * Auton_simulator_runner.inchToPixelConversion;
	public double xPosTarget, yPosTarget;
	
	private int maxCount = 1000;

	public int isPickingUpCubes = 0; //1 = loading, 2 = unloading, 0 = holding
	
	
	public double getIntakeX() {
		return getCenterX() + intakeSize*Math.sin(getZRotation());
	}
	
	public double getIntakeY() {
		return getCenterY() - intakeSize*Math.cos(getZRotation());
	}

	public Robot(object_draw drawer1, double x, double y) {
		super(drawer1, x, y, -0.015, robotXSize, robotYSize, robotZSize, 10);
		
		setRotation(0,0,angle);
		
		
		setPos(x,y,0);
		isTangible = true;
		xPosTarget = x;
		yPosTarget = y;
		
		setColor(Color.blue);
		isFilled = true;
		

	}
	

	
	public void move(double sq) {
		
		Auton_simulator_runner.console.add("moving robot " + sq + " squares  -  ");
		sq *= Auton_simulator_runner.squareSize;
		xPosTarget = getCenterX() + sq*Math.sin(angle);
		yPosTarget = getCenterY() - sq*Math.cos(angle);
		
		int count = 0;
		while ( (Math.abs(getCenterX()-xPosTarget) > robotPosErrThreshold) || (Math.abs(getCenterY()-yPosTarget) > robotPosErrThreshold) ) {
			setSpeed(robotPosErrThreshold*0.1 + 0.51*(Math.abs(xPosTarget)-Math.abs(getCenterX())),robotPosErrThreshold*0.1 + 0.51*(Math.abs(yPosTarget)-Math.abs(getCenterY())),0);
			
			
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			count++;
			
			if (count > maxCount) break;
		}
		
		setSpeed(0,0,0);
		setPos(xPosTarget,yPosTarget,-0.015);
		
		
		Auton_simulator_runner.console.add("done\n");
	}
	
	public void turn(double angleDegrees) {
		
		Auton_simulator_runner.console.add("turning robot " + angleDegrees + " degrees  -  ");
		angle += (angleDegrees * Math.PI/180);
		
		
		int count = 0;
		while (Math.abs(angle-getZRotation()) > robotPosErrThreshold ) {
			
			if (angleDegrees > 0) {	
				setAngularVelocity(0,0,robotPosErrThreshold*0.1 + 0.51*(Math.abs(Math.abs(angle)-Math.abs(getZRotation()))));
			}else {
				setAngularVelocity(0,0,-robotPosErrThreshold*0.1 - 0.51*Math.abs(Math.abs(angle)-Math.abs(getZRotation())));
				
			}
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			count++;
			
			if (count > maxCount) break;
	
		}
		
		setAngularVelocity(0,0,0);
		setRotation(0,0,angle);
		
		Auton_simulator_runner.console.add("done\n");
	}
	
	public void paint(Graphics page) {
		page.fillOval((int)(getIntakeX()-intakeSize),(int)( getIntakeY()-intakeSize), (int)(intakeSize*2), (int)(intakeSize*2));

		super.paint(page);
	}

}
