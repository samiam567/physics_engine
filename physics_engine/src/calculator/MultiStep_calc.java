package calculator;

import java.util.regex.PatternSyntaxException;

import javax.swing.JOptionPane;

public class MultiStep_calc {
	private String input;
	private String input_original;
	private double answer;
	private array[] ops = new array[(Calculator_runner.operations).length];
	private array allOps = new array("int");
	
	
	public MultiStep_calc(String input1) {
		input_original = input1;
		input = input1;		
		allOps.setValues(new int[] {});
	}
	
	public void setInput(String input1) {
		input_original = input1;
		input = input1;
	}
	
	public void getOperations() {
		
		boolean debug = false;
		
		//resetting arrays
		ops = new array[(Calculator_runner.operations).length];
		allOps = new array("int");
		
		//setting up op arrays
		for (int i = 0; i < ops.length; i++) {
			ops[i] = new array("int");
			ops[i].setValues(new int[] {});
		}
		
		boolean retest = false;
		String cOp;
		int opIndex;
		String input_temp = input;
		for (int i = 0; i < (Calculator_runner.operations).length; i++) {
			cOp = (Calculator_runner.operations)[i];
			do {
				if (debug) System.out.print(cOp + " : " + input_temp +  " > ");
				if (input_temp.contains(cOp)) {
					
					//adding this op's index to the appropriate list  AND   adding this op's index to the list of all the ops
					opIndex = input_temp.indexOf(cOp);
					ops[i].add(opIndex);
					allOps.add(opIndex);
					
					//getting ready for next test
					try {
						input_temp = input_temp.replaceFirst(cOp, " ");
					}catch(PatternSyntaxException e) {
						input_temp = input_temp.replaceFirst("\\" + cOp, " ");
					}
					if (debug) System.out.println("true");
					retest = true;
				}else { 
					if (debug) System.out.println("false");
					retest = false;
				}
			}while(retest);
		}
		
		//test output
		if (debug) {
			for (array cArray : ops) {
				System.out.println(cArray);
			}
		}
		
		if (debug) System.out.println("AllOps: " + allOps);
		allOps.sort();
		if (debug) System.out.println("AllOps(Sorted): " + allOps);
		
		
		
	}


	public static int checkForMultiOp(String eq) { //returns the number of operations in a string
		boolean retest = false;
		boolean debug = false;
		int counter = 0;
		String cOp;
		int opIndex;
		String input_temp = eq;
		for (int i = 0; i < (Calculator_runner.operations).length; i++) {
			cOp = (Calculator_runner.operations)[i];
			do {
				if (debug) System.out.print(cOp + " : " + input_temp +  " > ");
				if (input_temp.contains(cOp)) {
					
					//adding this op's index to the appropriate list  AND   adding this op's index to the list of all the ops
					opIndex = input_temp.indexOf(cOp);
					counter++;
					
					//getting ready for next test
					try {
						input_temp = input_temp.replaceFirst(cOp, " ");
					}catch(PatternSyntaxException e) {
						input_temp = input_temp.replaceFirst("\\" + cOp, " ");
					}
					if (debug) System.out.println("true");
					retest = true;
				}else { 
					if (debug) System.out.println("false");
					retest = false;
				}
			}while(retest);
		}
		return counter;
	}
	
	public double calculate() {
		getOperations(); //getting all the ops lists
		
		boolean debug = false;
		input = input_original; //reseting the input to the original input that the user put in in case of tampering (which there has been from the getOperations Method)
		//setting up vars
		String cOp = "";
		int opIndex;
		int startOfSubCalc = 0;
		int endOfSubCalc;
		int opIndexInAllOps;
		double sub_calc_ans;
		String ans_Str;
		Simple_calc simp_calculator = new Simple_calc("");
		String cOpSubStr = "";
		
		do { //use as do while loop to make sure there are no residual ops that were missed
			for (int i = 0; i < ops.length; i++) {
				cOp = (Calculator_runner.operations)[i];
				if (debug) System.out.println("cOp: " + cOp);
				for (int a = 0; a < ops[i].getArray(1).length; a++) {
					opIndex = ops[i].getArray(1)[a];
					getOperations(); //getting all the ops lists
					if (debug) System.out.println("i: " + i +  " a: " + a);
					
					if (debug) System.out.println("Array " + ops[i]);
	
					//finding the start and end of the sub calculation -=-=-=-=-=-=-=-=-=-
					
					
					
					try {					
						opIndex = ops[i].getArray(1)[a];
						opIndexInAllOps = allOps.indexOf(opIndex) - 1;
						startOfSubCalc = allOps.getArray(1)[opIndexInAllOps] + 1;
					}catch(ArrayIndexOutOfBoundsException e) {
						startOfSubCalc = 0;
					
					}
					
					try {
						opIndex = ops[i].getArray(1)[a];
						opIndexInAllOps = allOps.indexOf(opIndex) + 1;
						endOfSubCalc = (allOps.getArray(1)[opIndexInAllOps]);
					}catch(ArrayIndexOutOfBoundsException q) {
						endOfSubCalc = input.length();
					}
					
					//-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=--=-=-=-=-=
					
					if (debug) System.out.println("StartIndex: " + startOfSubCalc + "  EndIndex: "  + endOfSubCalc);
					cOpSubStr = input.substring(startOfSubCalc, endOfSubCalc); //getting the substring of input that contains the subCalculation
					
					if (debug) System.out.println("cOpSubStr: " + cOpSubStr);
					simp_calculator.setInput(cOpSubStr);
					
					sub_calc_ans = simp_calculator.calculate();
					
			
					ans_Str = (String)( "" + sub_calc_ans);
					
					if (debug) System.out.println("Replace " + cOpSubStr + " in " + input + " with " + ans_Str);
					input = input.replace(cOpSubStr,ans_Str);
					
					if (debug) System.out.println("NewInp " + input);
				}
			}
		}while (checkForMultiOp(input) > 1);
		
		answer = Double.parseDouble(input); // set the answer to the input (which now = the answer)
		input = input_original; //reset the input
		return answer;
	}

	public double getAnswer() {
		return answer;
	}

	public void output() {
		//updating previous answer
		Calculator_runner.prevCalculation = "" + answer;
				
		System.out.println(answer);
		System.out.println("Output " + answer);
		JOptionPane.showMessageDialog(Calculator_runner.calculatorAnchor, "" + answer);
	}
	
	
}
