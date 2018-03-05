package calculator;

public interface Calculator {
	public enum eqTypes {Simple,Algebreic,Calculus,Command,None};
	double answer = 0;
	String input = "";
	String op = "";
	
	public double calculate();
	public void output();
}
