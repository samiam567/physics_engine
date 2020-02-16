package vex_robot_auton_simulator;

import java.awt.Color;

import javax.swing.JOptionPane;

import Physics_engine.Settings;
import Physics_engine.object_draw;

public class Autonomous {
	
	public static String[] auton_names = {"forwardup","blue left","blue right","red left", "red right","blue left 8 stak", "red right 8 stak","stack","Skills","callibration","none"};
	public static int auton = 8;
	
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
	    case(0)://forward-up
        if (mode ==  1) {
          extendRampAndMoveSquares(1.2);
        }else{
          moveSquares(1.2);
        }
        delay(500);
   	   moveSquares(-1.2);
	    break;

	    case(1): //blue left
	    extendRampAndMoveSquares(0.3);

	  	left_intake.move(255);
	  	right_intake.move(255);

	    //pick up cubes
	    moveSquares(0.6,90);
	    moveSquares(0.6,90);
	    moveSquares(0.4,80);

	    delay(50);
	    left_intake.move(0);
	    right_intake.move(0);
	    delay(100);
	  	moveSquares(-0.9);
	  	turn(-130 ,150);
	  	moveSquares(0.5);
	  	stack(4);


	    break;

	    case(2): //blue right

	  	extendRampAndMoveSquares(0.3);
	    left_intake.move(255);
	    right_intake.move(255);
    moveSquares(1.1);
	    delay(500);
	    moveSquares(0.6);
	    delay(5);
	    left_intake.move(0);
	    right_intake.move(0);
	    moveSquares(-1.85);
	    turn(90,100);
	    moveSquares(1.21);
	    stack(5);


	    break;


	    case(3): //red left
    extendRampAndMoveSquares(0.3);
	    left_intake.move(255);
	    right_intake.move(255);
    moveSquares(1.1);
	    delay(500);
	    moveSquares(0.6);
	    delay(5);
	    left_intake.move(0);
	    right_intake.move(0);
	    moveSquares(-1.85);
	    turn(-90,100);
	    moveSquares(0.7);
	    stack(4);
	    break;

	    case(4): //red right
	     extendRampAndMoveSquares(0.3);

 	  	left_intake.move(255);
 	  	right_intake.move(255);
      moveSquares(1.6);
      delay(10);
      left_intake.move(0);
      right_intake.move(0);
      delay(10);
 	  	moveSquares(-0.9);
 	  	turn(130,130);
 	  	moveSquares(1.1);
 	  	stack(5);
	    break;

	    case(5): //blue left 8 stak
	    extendRampAndMoveSquares(2.35);

	  	delay(5);
	  	turn(90 * SIDE_LEFT,255);
	  	moveSquares(1);
	  	left_intake.move(0);
	  	right_intake.move(0);
	  	turn(90 * SIDE_LEFT,100);
	  	left_intake.move(255);
	  	right_intake.move(255);
	  	moveSquares(2.3);
	  	turn(90 * SIDE_LEFT,255);

	  	moveSquares(1.7);

	  	stack(8);
	    break;

	    case(6): //red right 8 stak
	    	extendRampAndMoveSquares(2.35);

	  	delay(5);
	  	turn(90 * SIDE_RIGHT,255);
	  	moveSquares(1);
	  	left_intake.move(0);
	  	right_intake.move(0);
	    turn(90 * SIDE_RIGHT,100);
	  	left_intake.move(255);
	  	right_intake.move(255);
	  	moveSquares(2.3);
	  	turn(90 * SIDE_RIGHT,255);

	  	moveSquares(1.7);

	  	stack(8);
	    break;

	    case(7): //stack
	      stack(10);
	      break;



	    case(8): //skills
	    extendRampAndMoveSquares(0.3);

	  	left_intake.move(255);
	  	right_intake.move(255);

	    //pick up cubes
	    moveSquares(0.6,90);
	    moveSquares(0.6,90);
	    moveSquares(0.4,80);
	    
	    moveSquares(1);
	    moveSquares(0.6,90);
	    moveSquares(0.6,90);
	    moveSquares(0.4,80);
	    
	    delay(50);
	    left_intake.move(0);
	    right_intake.move(0);
	    delay(100);
	  	
	  	turn(-45, 100);
	  	moveSquares(0.5);
	  	stack(8);

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
	  
	  public static void moveSquares(double squares,double speed) {
		  moveSquares(squares);
	  }
	  
	  public static void turn(double angle, double speed) {
		  Auton_simulator_runner.robot.turn(angle);
	  }
	  
	  public static void stack(int num) {
		  Auton_simulator_runner.robot.isPickingUpCubes = 2;
		  Auton_simulator_runner.console.add(num + " blocks requested unload, " + Cube.cubesStacked + " blocks actually unloaded.\n");
		  if (Cube.cubesStacked != num) {
			  JOptionPane.showMessageDialog(Auton_simulator_runner.drawer.frame, "You didn't stack the same number of cubes you said you did!");
		  }
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
		  
		  try {
			Thread.sleep(100);
		  } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
	
		  Auton_simulator_runner.drawer.frame.setBackground(Settings.frameColor);
		  left_intake.move(255);
		  right_intake.move(255);
		  moveSquares(distance);
		  left_intake.move(0);
		  right_intake.move(0);
		  
		  consoleLogN("Extended ramp");
		  
	  }
	  
	  public static int getAuton(String autonName) {
		  for (int i = 0; i < auton_names.length; i++) {
			  if (auton_names[i].equals(autonName)) {
				  return i;
			  }
		  }
		  
		  return -1;
	  }
	  
	  public static void consoleLogN(String stfToPrint) {
		  Auton_simulator_runner.console.add(stfToPrint + "\n");
		  System.out.println(stfToPrint);
	  }
	  
}
