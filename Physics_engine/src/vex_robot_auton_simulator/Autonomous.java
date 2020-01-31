package vex_robot_auton_simulator;

import java.awt.Color;

import Physics_engine.Settings;
import Physics_engine.object_draw;

public class Autonomous {
	
	public static String[] auton_names = {"backup","blue left","blue right","red left", "red right","blue left 8 stak", "red right 8 stak","stack"};
	public static int auton = 1;
	
	private static int SIDE_LEFT = 1, SIDE_RIGHT = -1;
	
	private static motor left_intake = new motor(), right_intake = new motor();
	private static int getAuton() {
		return auton;
	}

	 public static void autonomous() {
		 autonomous(getAuton(),1);
	 }

	 

  public static void grabAndStackAuton(int side, int mode) {
	  if (mode == 1) {
	      extendRampAndMoveSquares(-0.3);
	    }else{
	      moveSquares(-0.3);
	    }

	    turn(-45 * side,100);
	 
	    left_intake.move(255);
	    right_intake.move(255);
	    moveSquares(1);
	    delay(5);
	    moveSquares(0.5);
	    left_intake.move(0);
	    right_intake.move(0);
	    moveSquares(-1.5);
	    turn(-90 * side,100);
	    moveSquares(0.8);
	    stack(5);
  }
  
  public static void autonomous(int auton_sel,int mode) {
	
	  switch(auton_sel) {
	    case(0)://backup
	    moveSquares(-1.2);
	    delay(700);
	    if (mode ==  1) {
	      extendRampAndMoveSquares(1.2);
	    }else{
	      moveSquares(1.2);
	    }
	
	    break;
	
	    case(1): //blue left
	    extendRampAndMoveSquares(0.3);	  	
	  	left_intake.move(255);
	  	right_intake.move(255);
	  	moveSquares(1.6);
	  	left_intake.move(0);
	  	right_intake.move(0);
	  	moveSquares(-0.9);
	  	turn(-135 ,100);
	  	moveSquares(1.1);
	  	stack(4);
	  	
	    break;
	
	    case(2): //blue right
	  	
	  	extendRampAndMoveSquares(0.2);
	    turn(-90,100);
	    moveSquares(0.33333333333);
	    turn(90,100);
	    left_intake.move(255);
	    right_intake.move(255);
	    moveSquares(1.2);
	    delay(500);
	    moveSquares(0.6);
	    delay(5);
	    left_intake.move(0);
	    right_intake.move(0);
	    moveSquares(-1.8);
	    turn(90,100);
	    moveSquares(1.21);
	    stack(5);
	 

	    break;
	
	    case(3): //red left
	    extendRampAndMoveSquares(0.2);
	    turn(90,100);
	    moveSquares(0.33333333333);
	    turn(-90,100);
	    left_intake.move(255);
	    right_intake.move(255);
	    moveSquares(1.2);
	    delay(500);
	    moveSquares(0.6);
	    delay(5);
	    left_intake.move(0);
	    right_intake.move(0);
	    moveSquares(-1.8);
	    turn(-90,100);
	    moveSquares(1.21);
	    stack(5);
	    break;
	
	    case(4): //red right
	    extendRampAndMoveSquares(0.3);
        turn(70,100);

        left_intake.move(255);
        right_intake.move(255);
        moveSquares(0.39);
        delay(5);
        turn(-70 * SIDE_LEFT,100);
        moveSquares(1.2);
        left_intake.move(0);
        right_intake.move(0);
        moveSquares(-0.8);
        turn(130 ,100);
        moveSquares(0.8);
        stack(4);
	    break;
	    
	    case(5): //blue left 8 stak
	    extendRampAndMoveSquares(0.3);
	  	turn(-55 * SIDE_LEFT,100);
	  	
	  	left_intake.move(255);
	  	right_intake.move(255);
	  	moveSquares(0.5);
	  	delay(5);
	  	turn(55 * SIDE_LEFT,100);
	  	moveSquares(1);
	  	left_intake.move(0);
	  	right_intake.move(0);
	  	moveSquares(-1);
	  	turn(90,100);
	  	moveSquares(1);
	  	turn(-90,100);
	  	left_intake.move(255);
	  	right_intake.move(255);
	  	moveSquares(0.7);
	  	delay(500);
	  	moveSquares(0.3);
	  	turn(-130,100);
	  	moveSquares(2.2);
	  	stack(8);
	    break;
	    	
	    case(6): //red right 8 stak
	    extendRampAndMoveSquares(0.3);
	  	turn(55 * SIDE_LEFT,100);
	  	
	  	left_intake.move(255);
	  	right_intake.move(255);
	  	moveSquares(0.5);
	  	delay(5);
	  	turn(-55 * SIDE_LEFT,100);
	  	moveSquares(1);
	  	left_intake.move(0);
	  	right_intake.move(0);
	  	moveSquares(-1);
	  	turn(-90,100);
	  	moveSquares(1);
	  	turn(90,100);
	  	left_intake.move(255);
	  	right_intake.move(255);
	  	moveSquares(0.7);
	  	delay(500);
	  	moveSquares(0.3);
	  	turn(130,100);
	  	moveSquares(2.2);
	  	stack(8);
	    break;
	    
	    case(7): //stack
	      stack(10);
	      break;
	
	
	  
	    case(8): //skills
	
	    break;
	
	    case(9): //calibration
	
	    break;
	
	    case(10): //none
	
	
	    break;
	
	  }
	    
	  
	  }
	  
	  public static void moveSquares(double squares) {
		  Auton_simulator_runner.robot.move(squares);
	  }
	  
	  public static void turn(double angle, double speed) {
		  Auton_simulator_runner.robot.turn(angle);
	  }
	  
	  public static void stack(int num) {
		  Auton_simulator_runner.robot.isPickingUpCubes = 2;
		  Auton_simulator_runner.console.add(num + " blocks requested unload, " + Cube.cubesStacked + " blocks actually unloaded.\n");
		  moveSquares(-0.7);
		  Auton_simulator_runner.robot.isPickingUpCubes = 0;
	  }
	  
	  public static void bumpWall() {
		  moveSquares(-0.3);
		  delay(100);
		  moveSquares(0.3);
	  }
	  
	  public static void delay(int wait_time) {
		  object_draw.WaitNanotime(wait_time * 1000);
	  }
	  
	  public static void extendRampAndMoveSquares(double distance) {
		  Auton_simulator_runner.drawer.frame.setBackground(Color.white);
		  Auton_simulator_runner.drawer.frame.setBackground(Settings.frameColor);
		  moveSquares(distance);
	  }
	  
	  public static int getAuton(String autonName) {
		  for (int i = 0; i < auton_names.length; i++) {
			  if (auton_names[i].equals(autonName)) {
				  return i;
			  }
		  }
		  
		  return -1;
	  }
	  
}
