package calculator;

import java.util.regex.PatternSyntaxException;

import javax.swing.JOptionPane;

public class MultiStep_calc {
	private String input;
	private String input_original;
	private double answer;
	private array ops; //ops in the order of order of operations
	private array allOps = new array("int"); //ops in the order that they appear in the input
	
	
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
		ops = new array("int");
		allOps = new array("int");
		
		String input_temp = input, testStr = "",cOp;  
		int opPriority = 0,addIndx,opIndex;
		
		while (opPriority < Calculator_runner.operations.length ) { //stop when the priority is greater than the number of operations (this works because it is the max value that the priority could get to if each op's priority increased by one each operation)
			
			for (int i = 0; i < input.length(); i++) { //iterate through the eq
				
				for  (String op : Calculator_runner.operations) {
					
					//if (debug) System.out.print("op: " + op + " ~  current priority: " + opPriority + " ~ " );
					
					if (Integer.parseInt(op.substring(0,1)) == opPriority) { //check if the operation is of the current priority level
						
						//if (debug) System.out.println("true");
						
						
						cOp = op.substring(1); //removes the priority indicator from the op (ex: 3+ -> + )
						
						if (i + cOp.length() < input.length()) {
							testStr = input.substring(i, i + cOp.length());
						

							//testing if the testStr is equal to the cOp
							if (debug) System.out.print("if " + testStr + " equals " + cOp + " ~ ");
							if (testStr.equals(cOp)) {
								
								if (debug) System.out.println("true");
								
								//adding this op's index to the appropriate list  AND   adding this op's index to the list of all the ops
								opIndex = input_temp.indexOf(cOp);
								ops.add(opIndex);
								allOps.add(opIndex);					
		
								//getting ready for next test
								try {
									input_temp = input_temp.replaceFirst(cOp, " ");
								}catch(PatternSyntaxException e) {
									input_temp = input_temp.replaceFirst("\\" + cOp, " ");
								}
								
								i += cOp.length();
								
							}else if (debug) System.out.println("false");
						}
						
					}//else if (debug) System.out.println("false");
					
				}
				
			}
			
			opPriority++;
			
		}
		
	
		
		//test output
		if (debug) {
			
			System.out.println(ops);
			
		}
		
		if (debug) System.out.println("AllOps: " + allOps);
		allOps.sort();
		if (debug) System.out.println("AllOps(Sorted): " + allOps);
		
		
		
	}


	public static int checkForMultiOp(String eq) { //returns the number of operations in a string     eq stands for equation
		 
		boolean debug = false;
		
		
		
		if (debug) System.out.println("--checkForMultiOp--");
		
		//op stands for operation and cOp stands for current operation
		
		boolean retest = false;
		String cOp;
		int counter = 0;
		String input_temp = eq;
		for (int i = 0; i < (Calculator_runner.operations).length; i++) {
			
			if (debug) System.out.println("i: " + i);
			
			cOp = (Calculator_runner.operations)[i].substring(1);
			do {
				if (debug) System.out.print(cOp + " : " + input_temp +  " > ");
				
				if (input_temp.contains(cOp)) {
					
			
					counter++; //add to the count
					
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
		
		if (debug) System.out.println("OP COUNT: " + counter);
		
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
			
			for (int a = 0; a < ops.getArray(1).length; a++) {
					
					getOperations();
					
					if (debug) System.out.println(" a: " + a);
					
					if (debug) System.out.println("Array " + ops);
	
					
					//finding the start and end of the sub calculation -=-=-=-=-=-=-=-=-=-

					try {					
						opIndex = ops.getArray(1)[0];
						opIndexInAllOps = allOps.indexOf(opIndex) - 1;
						startOfSubCalc = allOps.getArray(1)[opIndexInAllOps] + 1;
					}catch(ArrayIndexOutOfBoundsException e) {
						startOfSubCalc = 0;
					
					}
					
					try {
						opIndex = ops.getArray(1)[0];
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
					
			
					ans_Str = (String) ( "" + sub_calc_ans).replaceAll("-", "_"); //use replaceAll("-", "_") in case the answer to the subCalculation is negative
					
					if (Calculator_runner.debug) System.out.println("Replace " + cOpSubStr + " in " + input + " with " + ans_Str);
					input = input.replace(cOpSubStr,ans_Str);
					
					if (debug) System.out.println("NewInp " + input);
				}
			
		}while (checkForMultiOp(input) > 0);
		
		input = input.replaceAll("_", "-"); //turn the "_" signs into "-" to allow to be converted into a double
		
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
				
		
		if (Calculator_runner.debug) System.out.println("Output " + answer);
		JOptionPane.showMessageDialog(Calculator_runner.calculatorAnchor, "" + answer);
	}
	
	
}
