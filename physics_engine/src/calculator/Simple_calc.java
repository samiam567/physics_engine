package calculator;

import java.util.regex.PatternSyntaxException;

import javax.swing.JOptionPane;

public class Simple_calc implements Calculator{

	public String input;
	public String op;
	public double answer;
	
	public Simple_calc(String input1) {
		input = input1;
	}

	public static String getOperation(String eq) {
		String op = "";
		
		for(String cOp : Calculator_runner.operations) {
			if (eq.contains(cOp.substring(1))) {
				if (op == "") {
					op = cOp.substring(1);
				}else{ //there has already been an operation assigned!
					if (op.contains(cOp.substring(1))) { //this means that the real operation has another inside it (atan and tan) and therefore isn't a problem
						
					}else {
						return "multi-op";
					}
					
				}
				
			}
		}
		
		if (op == "") {
			Calculator_runner.errors.add("Either your calculation doesn't have an operation in it, or I have not been programmed to do that operation. \n you can use /help to get a list of commands and operations eq: " + eq);
		}
		return op;
	}
	
	
	
	private double degreesToRadians(double degrees) {
		double radians = (degrees * Math.PI)/180;
		return radians;
	}
	
	private double[] getAB(String eq, String op) {
		boolean debug = false;
		eq = eq.replaceAll(" ", ""); //get rid of annoying spaces
		
		eq = eq.replaceAll("ans", Calculator_runner.prevCalculation.replaceAll("-", "_")); // replacing ans with the answer to the previous calculation (the replaceAll("-","_") is for answers that contain scientific notation ex: 4e-2)
		
		eq = eq.replaceAll("PI", "" + Math.PI);
		
		array eqArray = new array("double");
	
		//avoiding PatternSyntax exception by changing the op to "," so that "," can be the delimiter
		try {
			eq = eq.replaceAll(op,",");
		}catch(PatternSyntaxException e) {
			eq = eq.replaceAll("\\" + op,",");
		}

		if (debug) System.out.println(eq);
		eqArray.getValues(eq, ",");
		eqArray.setSize(2);
		if (debug) System.out.println(eqArray);
		return eqArray.getArray(1.1);
	}
	
	
	public double calculate() {
		String eq = input;
		op = getOperation(eq);
		boolean debug = false;
		if(op == "") return 0;
		double[] AB = getAB(eq,op);
		double A = AB[0];
		double B = AB[1];
		
		if (debug) System.out.print(A);
		if (debug) System.out.print(op);
		if (debug) System.out.print(B);
		if (debug) System.out.print("=");
		
		
		switch(op) {
			case "+":
				answer = A+B;
				break;
				
			case "-":
				answer = A-B;
				break;
			
			case "++":
				answer = A+1;
				break;
				
			case "*":
				answer = A*B;
				break;
			
			case "pow":
				answer = Math.pow(A, B);
				break;
				
			case "/":
				answer = A/B;
				break;
			
			case "sqrt":
				answer = Math.sqrt(A);
				break;
				
			case "sq":
				answer = Math.pow(A,2);
				break;
				
			case "sin":
				if (Calculator_runner.degRadMode == "deg") A = degreesToRadians(A);
				answer = Math.sin(A);
				break;
				
			case "cos":
				if (Calculator_runner.degRadMode == "deg") A = degreesToRadians(A);
				answer = Math.cos(A);
				break;
				
			case "tan":
				if (Calculator_runner.degRadMode == "deg") A = degreesToRadians(A);
				answer = Math.tan(A);
				break;
				
			case "rev/min": //revolutions to radians conversion
				answer = (2 * Math.PI * A) / 60;
				break;
				
			case "rev/sec": //revolutions to radians conversion
				answer = 2 * Math.PI * A;
				break;
				
			default:
				System.out.println("\n ERROR: Operation " + op + " has not been programmed");
				break;
		}
		return answer;
	}
	
	public void setInput(String input1) {
		input = input1;
	}
	
	public void output() {
		
		//updating previous answer
		Calculator_runner.prevCalculation = "" + answer;
		System.out.println(Calculator_runner.prevCalculation);
		
		System.out.println(answer);
		System.out.println("Output " + answer);
		JOptionPane.showMessageDialog(Calculator_runner.calculatorAnchor, "" + answer);
	}
	
}
