package calculator;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Calculator_runner {
	public enum eqTypes {Simple,Algebreic,Calculus,Command,MultiStep,None};
	
	
	public static String version = "4.2.1";
	
	public static int colorCounter = 0;
	public static Color[] colors = {Color.BLACK,Color.blue,Color.CYAN,Color.DARK_GRAY,Color.GREEN,Color.MAGENTA,Color.ORANGE,Color.PINK,Color.RED,Color.YELLOW}; 
	public static String degRadMode = "deg";
	public static eqTypes eqType = eqTypes.None;
	public static String input;
	public static double answer;
	public static String[] operations = {"rev/min","rev/sec","sqrt","pow","sq","sin","cos","tan","*","/","++","+","-"};
	public static String[] commands = {"/degRadMode","/help","/move"};
	public static int[] numbers = {1,2,3,4,5,6,7,8,9,0};
	
	public static JFrame calculatorAnchor = new JFrame();
	
	public static ArrayList<String> errors = new ArrayList<String>();
	
	public static void main(String[] args) {
		
		calculatorAnchor.setVisible(true);
		calculatorAnchor.setSize(300,10);
		calculatorAnchor.setTitle("Calculator V" + version + "   Programmed by Alec Pannunzio");
		
		
		do {
			run();
			displayErrors();
			
		}while (JOptionPane.showConfirmDialog(calculatorAnchor,"Do you want to calculate something else?", "Another?", 1, 1, null) == 0);
		
		Grapher.frame.dispose();
		calculatorAnchor.dispose();
	}
	
	private static void run() {
		
		try {
			input = JOptionPane.showInputDialog(calculatorAnchor,"Type in what you want to solve");
			System.out.println("Input: " + input);
			
			Grapher.graphs.add(new Axis()); //adding the axis onto the graph screen
			
			if (input.substring(0,1).equals("/")) {
				eqType = eqTypes.Command;
			}else if (cont("=")&&cont("y")) {
				eqType = eqTypes.Algebreic;
			}else if (MultiStep_calc.checkForMultiOp(input) > 1) {
				eqType = eqTypes.MultiStep;
			}else {
				eqType = eqTypes.Simple;
			}
			
			if (eqType.equals(eqTypes.Simple)) {
				Simple_calc calc = new Simple_calc(input);
				calc.calculate();
				calc.output();
				if (calc.op == "") return;
			}else if (eqType.equals(eqTypes.Command)){
				runCommand(input);
				return;
			}else if (eqType.equals(eqTypes.MultiStep)) {	
				MultiStep_calc calculation = new MultiStep_calc(input);
				answer = calculation.calculate();
				calculation.output();
			}else if (eqType.equals(eqTypes.Algebreic)){
				boolean error = false;
				double yValue = 0;
				do {
					String yValueStr = JOptionPane.showInputDialog(calculatorAnchor,"What y do you want to test for?");
					try {
						yValue = Double.parseDouble(yValueStr);
						error = false;
					}catch(NumberFormatException e){
						error = true;
					}
				}while (error);
				Grapher drawer = new Grapher();
				algebreic_calc alg_cal = new algebreic_calc(input, yValue);
				drawer.addEquation(new algebreic_calc(input,yValue,"calculate,output"));
				drawer.updateGraph();
			}else {
				errors.add("I'm terribly sorry sir, but it appears that I have not been programmed to undertake that type of calculation yet.");
				return;
			}
		}catch(NullPointerException n) {
			Grapher.frame.dispose();
			calculatorAnchor.dispose();
			System.exit(1);
		}
	
	}
	
	private static void displayErrors() {
		String error;
		for (int i = 0; i < errors.size(); i++) {
			error = errors.get(i);
			JOptionPane.showMessageDialog(calculatorAnchor, error); //display the error
			errors.remove(i); //remove the error from the list
		}
	}
	
	public static void WaitNanotime(long time) { //more accurate wait method for nanotime
		long StartTime = System.nanoTime();
		long CurrentTime;	
		do {
			CurrentTime = System.nanoTime();
		}while ( (CurrentTime-StartTime) <= time);
	}
	
	public static void Wait(double time) { // Wait(1000) evaluates to about 20 seconds
		double Time = time * 10000000;
		double t = 0;
		while (t < Time)
			t++;
	}
	
	private static boolean cont(String seq) { //checks if the input contains a certain string
		return input.contains(seq);
	}
	
	
	
	
	private static void runCommand(String command) {
		switch(command) {
			case("/degRadMode"):
				if (degRadMode == "deg") {
					degRadMode = "rad";
				}else {
					degRadMode = "deg";
				}
				JOptionPane.showMessageDialog(calculatorAnchor, "degRadMode changed to " + degRadMode);
				break;
			case ("/help"):
				array opsArray = new array("String");
				opsArray.setValues(operations);
				array commandsArray = new array("String");
				commandsArray.setValues(commands);
				System.out.println("Output: " + "Possible operations: " + opsArray + "\n Possible commands: " + commandsArray);
				JOptionPane.showMessageDialog(calculatorAnchor,"Possible operations: " + opsArray + "\n Possible commands: " + commandsArray);
			break;
			
			case("/move"):
				JOptionPane.showMessageDialog(calculatorAnchor, "Press ok to be able to move the calculator for a limited amount of time");
			
				try {
					Thread.sleep(3500);
				}catch(InterruptedException i) {
					i.printStackTrace();
				}
				
			break;
			
			default:
				JOptionPane.showMessageDialog(calculatorAnchor, command + " is not a valid command. \n you can use /help to get a list of commands and operations","Error", 0);
		}
	}
	
	
	
	
	private static void output() {
		System.out.println(answer);
		System.out.println("Output " + answer);
		JOptionPane.showMessageDialog(calculatorAnchor, "" + answer);
	}
	
}
