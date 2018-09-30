package calculator;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Physics_engine.Physics_engine_toolbox;

public class Calculator_runner {
	public enum eqTypes {Simple,Algebreic,Calculus,Command,MultiStep,None};
	
	
	public static String version = "4.4.0";
	
	public static int colorCounter = -1;
	public static Color[] colors = {Color.BLACK,Color.blue,Color.CYAN,Color.DARK_GRAY,Color.GREEN,Color.MAGENTA,Color.ORANGE,Color.PINK,Color.RED,Color.YELLOW}; 
	public static String degRadMode = "deg";
	public static eqTypes eqType = eqTypes.None;
	public static String input;
	public static double answer;
	public static String[] operations = {"0rev/min","0rev/sec","1sin","1cos","1tan","2sqrt","2pow","2sq","3*","3/","4+","4-"}; //[priority(lower#=highPriority)][operation] PRIORITY CAN ONLY HAVE ONE DIGIT
	public static String[] opsVisual; //ops w/out priority (created in the constructor of this class)
	public static String[] commands = {"/quadForm","/degRadMode","/help","/move"};
	public static int[] numbers = {1,2,3,4,5,6,7,8,9,0};
	
	public static JFrame calculatorAnchor = new JFrame();
	
	public static ArrayList<String> errors = new ArrayList<String>();

	public static String prevCalculation = "0";

	public static final boolean debug = false;
	
	public static void main(String[] args) {
		run();
	}
		
	public static void run() {
		calculatorAnchor.setVisible(true);
		calculatorAnchor.setSize(300,10);
		calculatorAnchor.setTitle("Calculator V" + version + "   Programmed by Alec Pannunzio");
		
		opsVisual = new String[operations.length];
		for (int i = 0; i < operations.length; i++) {
			opsVisual[i] = operations[i].substring(1);
		}
		
		do {
			runCalc();
			displayErrors();
			
		}while (JOptionPane.showConfirmDialog(calculatorAnchor,"Do you want to calculate something else?", "Another?", 1, 1, null) == 0);
		
		Grapher.frame.dispose();
		calculatorAnchor.dispose();
	}
	
	private static void runCalc() {
		
		try {
			Grapher.frame.setVisible(false);
			
			input = "";
			
			while (input.length() == 0) {
				input = JOptionPane.showInputDialog(calculatorAnchor,"Type in what you want to solve");
				System.out.println("Input: " + input);
			}
			
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
		String[] options = {"Next Error","Skip"};
		String error;

		
			
		while(errors.size() > 1) {
			error = errors.get(0);
			
			if (JOptionPane.showOptionDialog(calculatorAnchor, error, "ERROR IN CALCULATION", 1, 1,null, options, null) == 1) { //display the error and check if the user wants to skip the rest
				errors.clear();
				break;
			}
			errors.remove(0); //remove the error from the list
		}
		
		//displaying the last error
		if (errors.size() > 0) {	
			JOptionPane.showOptionDialog(calculatorAnchor, errors.get(errors.size()-1), "ERROR IN CALCULATION", 1, 1,null, options, null);
		}
		
		errors.clear();
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
			case("/quadForm"):
				JOptionPane.showMessageDialog(calculatorAnchor, algebreic_calc.quadraticFormula(Physics_engine_toolbox.getDoubleFromUser(calculatorAnchor,"what is a?"),Physics_engine_toolbox.getDoubleFromUser(calculatorAnchor,"what is b?"),Physics_engine_toolbox.getDoubleFromUser(calculatorAnchor,"what is c?")));
				break;
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
				opsArray.setValues(opsVisual);
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
