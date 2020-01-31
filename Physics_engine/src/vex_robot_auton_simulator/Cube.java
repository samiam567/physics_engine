package vex_robot_auton_simulator;

import java.awt.Color;
import java.awt.Point;
import Physics_engine.Physics_engine_toolbox;
import Physics_engine.Rectangular_prism;
import Physics_engine.object_draw;
import Physics_engine.point;
import Physics_engine.rectangle;

public class Cube extends rectangle {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8547178716774924550L;

	public static double cubeSize = Auton_simulator_runner.squareSize/4;
	
	public boolean isPickedUp = false;
	
	public static int cubesStacked = 0;
	public static int cubeTotal = 0;
	
	public static point intakePoint = new point(null,0,0,0);
	public int cubeNum;
	public Cube(object_draw drawer1, double x, double y) {
		super(drawer1, x, y, -0.001, cubeSize,cubeSize, 10);

		switch(cubeTotal % 3) {
			case(0):
				setColor(Color.orange);
			break;
			
			case(1):
				setColor(Color.MAGENTA);
			break;
			
			case(2):
				setColor(Color.GREEN);
			break;
		}
		cubeNum = cubeTotal;
		cubeTotal++;
		
		isTangible = false;
		isFilled = true;

	}
	
	public void pickUp() {
		isPickedUp = true;
	}
	
	@Override
	public void tertiaryUpdate() {
		if (Auton_simulator_runner.robot.isPickingUpCubes == 1) {
			if (! isPickedUp) {
				intakePoint.setPos(Auton_simulator_runner.robot.getIntakeX(), Auton_simulator_runner.robot.getIntakeY(), -0.001);
				if (Physics_engine_toolbox.distance(getCenter(), intakePoint) < cubeSize/2) {
					isPickedUp = true;
					setPos(getX(),getY(),-cubeSize*(cubesStacked));
					cubesStacked++;
					System.out.println("cube picked up");
				}
			}
		}else if (Auton_simulator_runner.robot.isPickingUpCubes == 2){
			if(isPickedUp) {
				isPickedUp = false;
				cubesStacked--;
				setPos(getCenterX(),getCenterY(), -0.001);
			}
		}
		
		if (isPickedUp) {
			setPos(Auton_simulator_runner.robot.getIntakeX()+cubeNum,Auton_simulator_runner.robot.getIntakeY()+cubeNum,getCenterZ());
			updateCenter();
		}
	}

}
