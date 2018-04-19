package calculator;

import java.util.regex.PatternSyntaxException;

import javax.swing.JOptionPane;

public class algebreic_calc {
	int yPos;
	array answers = new array("double");
	array xPoses = new array("int");
	int equalsPos;
	String input;
	String input_original;
	String input_exe;
	private graph eqGraph = new graph();
	double xStep = 0.01;
	double bound = 1000;
	double yTarget;
	
	public algebreic_calc(String input1,double yTarget1) {
		boolean debug = false;
		input_original = input1;
		input = input1;
		xPoses = testFor(input,"x");
		yTarget = yTarget1;
		
		//getting the poses of y and = y=y==y=y=y=y=y=y==y=y=y=y=y=y=y=y=y=y=y=y=y=y=y=y=
		array yPoses = testFor(input,"y");
		if (!(yPoses.getArray(1).length == 1)) {
			try {throw new Error("there are multiple y's in the input");}catch(Error e) { e.printStackTrace();}
		}else {
			yPos = yPoses.getArray(1)[0];
		}
		
		array equalsPoses = testFor(input,"=");
		if (!(equalsPoses.getArray(1).length == 1)) {
			try {throw new Error("there are multiple ='s in the input");}catch(Error e) { e.printStackTrace();}
		}else {
			equalsPos = equalsPoses.getArray(1)[0];
		}
		//y=y==y=y=y=y=y=y==y=y=y=y=y=y=y=y=y=y=y=y=y=y=y=y=y=y==y=y=y=y=y=y==y=y=y=y=y=
		
		input_exe = input.replace("=", "");
		input_exe = input_exe.replace("y", "");
		if (debug) System.out.println("Input: " + input);
		if (debug) System.out.println("Input_exe: " + input_exe);
		
	}

	public algebreic_calc(String input1,double yTarget1,String commands) {
		boolean debug = false;
		input_original = input1;
		input = input1;
		xPoses = testFor(input,"x");
		yTarget = yTarget1;
		
		//getting the poses of y and = y=y==y=y=y=y=y=y==y=y=y=y=y=y=y=y=y=y=y=y=y=y=y=y=
		array yPoses = testFor(input,"y");
		if (!(yPoses.getArray(1).length == 1)) {
			try {throw new Error("there are multiple y's in the input");}catch(Error e) { e.printStackTrace();}
		}else {
			yPos = yPoses.getArray(1)[0];
		}
		
		array equalsPoses = testFor(input,"=");
		if (!(equalsPoses.getArray(1).length == 1)) {
			try {throw new Error("there are multiple ='s in the input");}catch(Error e) { e.printStackTrace();}
		}else {
			equalsPos = equalsPoses.getArray(1)[0];
		}
		//y=y==y=y=y=y=y=y==y=y=y=y=y=y=y=y=y=y=y=y=y=y=y=y=y=y==y=y=y=y=y=y==y=y=y=y=y=
		
		input_exe = input.replace("=", "");
		input_exe = input_exe.replace("y", "");
		if (debug) System.out.println("Input: " + input);
		if (debug) System.out.println("Input_exe: " + input_exe);
		
		if (commands == "calculate,output") {
			calculate();
			output();
		}else if(commands == "calculate") {
			calculate();
		}
		
	}
	
	
	public static array testFor(String eq, String value) { //returns an array object of the indexes of all the indexes of a char
		boolean debug = false;
		array indexes = new array("int");
		String input_temp = eq;
		boolean retest = false;
		int index;
		do {
			if (debug) System.out.print(value + " : " + input_temp +  " > ");
			if (input_temp.contains(value)) {
				
				//adding this op's index to the appropriate list  AND   adding this op's index to the list of all the ops
				index = input_temp.indexOf(value);
				indexes.add(index);
			
				
				//getting ready for next test
				try {
					input_temp = input_temp.replaceFirst(value, " ");
				}catch(PatternSyntaxException e) {
					input_temp = input_temp.replaceFirst("\\" + value, " ");
				}
				if (debug) System.out.println("true");
				retest = true;
			}else { 
				if (debug) System.out.println("false");
				retest = false;
			}
		}while(retest);
		return indexes;
	}
	
	public void calculate() {
		String input_temp = input_exe;
		MultiStep_calc multiCalc = new MultiStep_calc("");
		boolean debug = false;
		double testAns;
		double ans_rounded;
		double x_rounded;
		for (double x = 0; x < bound; x += xStep) {
			input_temp = input_exe;
			while (input_temp.contains("x")) {
				input_temp = input_temp.replace("x", "" + x);
			}
			
			multiCalc.setInput(input_temp);
			
			testAns = multiCalc.calculate();
			
			ans_rounded = Math.round(testAns * 1/(xStep));
			ans_rounded *= xStep;
			
			x_rounded =  Math.round(x * 1/(xStep));
			x_rounded *= xStep;
			
			if (Math.abs(testAns-yTarget) < 0.000001) {
				answers.add(x_rounded);
			}
			
			eqGraph.addPoint(new point(x,ans_rounded));
		}
		
		System.out.println("positives");
		
		for (double x = bound; x > 0; x -= xStep) {
			
			x = (Math.round(x / xStep) * xStep);
			input_temp = input_exe;
			if (debug) System.out.println("x: " + x);
			while (input_temp.contains("x")) {
				input_temp = input_temp.replace("x", "_" + x);
			}
			if (debug) System.out.println("Input w/ x: " + input_temp);
			
			multiCalc.setInput(input_temp);
			
			testAns = multiCalc.calculate();
			
			ans_rounded = Math.round(testAns * 1/(xStep));
			ans_rounded *= xStep;
			
			x_rounded =  Math.round(x * 1/(xStep));
			x_rounded *= xStep;
			x_rounded = Double.parseDouble("-" + x_rounded);
			
			if (Math.abs(testAns-yTarget) < 0.000001) {
				answers.add(x_rounded);
			}
			
			eqGraph.addPoint(new point(x_rounded,testAns));	
			
			if (debug) System.out.println("Ans: " + testAns);
			
		}	
	}
	
	public graph getGraph() {
		return eqGraph;
	}
	public void output() {
		System.out.println("Output " + answers);
		JOptionPane.showMessageDialog(Calculator_runner.calculatorAnchor, answers.toString());
	}
	
}
