package vex_robot_auton_simulator;

public class motor {
	public void move(double speed) {
		System.out.println("intake speed set to " + speed);
		Auton_simulator_runner.console.add("intake speed set to" + speed);
		
		if (speed > 0) {
			Auton_simulator_runner.robot.isPickingUpCubes = 1;
		}else if (speed < 0) {
			Auton_simulator_runner.robot.isPickingUpCubes = 2;
		}else {
			Auton_simulator_runner.robot.isPickingUpCubes = 0;
		}
	}
}
