package oop_and_contr_labs;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Oop_and_contr_labs_cpu extends Canvas{
	
	
	public double cosLaw(double A, double B, double C) {
		double a = Math.acos( (Math.pow(B, 2) + Math.pow(C, 2) - Math.pow(A, 2))/(2*B*C) );
		return a;
	}
	public void paint(Graphics page) {
		
		//create a jframe for misc use
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
	
	
		//equation class- -------------------------------------------------------------------------------------------------
		class triangle {
			public void Wait(double time) { //Wait(1000) evaluates to about 20 seconds
				double Time = time * 10000000;
				double t = 0;
				while (t<Time)
					t++;
			}
			
			public void drawBackground(Color color) {
				page.setColor(color);
				page.fillRect(0,0,10000,10000);
				page.setColor(Color.BLACK);
			}
			
			public void drawTriangle(int x, int y,int A, int B, int C,Double tri_area) {
				//calculating angles
				double a = cosLaw(A,B,C);
				double b = cosLaw(B,A,C);
				double c = cosLaw(C,B,A);
				
				String tri_area_str_f = "" + String.format("%.3f",tri_area);
				page.drawString("Area: " + tri_area_str_f, x + 20, (int)(y + ( C * Math.sin(c)*25) /2 )  ); //put the area inside the triangle
				
				//draw the triangle (coordinates are calculated with simple unit circle-type trig0
				page.drawLine(x , y, x + (A*50) , y);// side A   NOTE: we multiply all the side lengths by 50 for drawing so it's easier to see
				page.drawLine(x, y, (int) (x + (C * Math.cos(c)*50)) , (int) (y + (C * Math.sin(c)*50)));  //side B
				page.drawLine(x + (A*50), y, (int) (x + (C * Math.cos(c)*50)) , (int) (y + (C * Math.sin(c)*50))); //side C
			}
		}
		
		class equation {
			public double a,b,h;

			public equation(double q,double i,double r) {
				a = q;
				b = i;
				h = r;
			
			}
			
			public double XtoY(double x) {
				return x;
			}
			
		}
		class sq_eq extends equation {
			public double a,b,c;
			public sq_eq(double q,double i,double r) {
				super(q,i,r);
				a = q;
				b = i;
				c = r;
			}
			
			public double XtoY(int x) {
				double y = a*Math.pow(x, 2) + b*x + c;
				return y;
			}
			
			public double[] quadForm() {
				double x1 = ( (-b + Math.sqrt( Math.pow(b, 2) - 4*a*c )) / 2*a );
				double x2 = ( (-b - Math.sqrt( Math.pow(b, 2) - 4*a*c )) / 2*a );
				
				double[] roots = {x1,x2};
				return roots;
			}
		}
		//---------------------------------------------------------------------------------------------------------------------
		
		// program starts here:   ============================
		JOptionPane.showMessageDialog(window, "This simple program will ask questions about how to plot the motion of a triangle on the canvas. In doing this it will \n-calculate the area of the triangle \n-evaluate the quadratic formula on the equation you use to model the triangle's movement \n- calculate the displacement of the triangle (distance formula) \n-calculate miles per hour");
	
		
		
	// TRIANGLE AREA LAB
		//getting the sides of the triangle  NOTE: uppercase = side, lowercase = angle   ex: <a  side A
		String A_str;
		String B_str;
		String C_str;
		try {
			A_str = JOptionPane.showInputDialog("What is the length of the first side of the triangle?");
			B_str = JOptionPane.showInputDialog("What is the length of the second side of the triangle?");
			C_str = JOptionPane.showInputDialog("What is the length of the third side of the triangle?");
			
			int A = Integer.parseInt(A_str);
			int B = Integer.parseInt(B_str);
			int C = Integer.parseInt(C_str);
			
			
		} catch(NumberFormatException problem) {
			
			try {
				A_str = JOptionPane.showInputDialog("What is the length of the first side of the triangle?");
				B_str = JOptionPane.showInputDialog("What is the length of the second side of the triangle?");
				C_str = JOptionPane.showInputDialog("What is the length of the third side of the triangle?");
				
				int A = Integer.parseInt(A_str);
				int B = Integer.parseInt(B_str);
				int C = Integer.parseInt(C_str);
			}
			catch(NumberFormatException problem2) {
				A_str = JOptionPane.showInputDialog("What is the length of the first side of the triangle?");
				B_str = JOptionPane.showInputDialog("What is the length of the second side of the triangle?");
				C_str = JOptionPane.showInputDialog("What is the length of the third side of the triangle?");
				
				
			}
		}
		
		
		int A = Integer.parseInt(A_str);
		int B = Integer.parseInt(B_str);
		int C = Integer.parseInt(C_str);
		
		int perimeter = A + B + C;
		int s = perimeter/2;
		double tri_area = Math.sqrt( s * ( s - A )*( s - B )*(s - C ) );
		
		JOptionPane.showMessageDialog(window,"Area of the triangle: " + tri_area);
		
		triangle tri = new triangle();
		
		tri.drawTriangle(50,300,A,B,C,tri_area);
	
	// QUAD FORM LAB
		String A_eq_str, B_eq_str, C_eq_str;
		
		JOptionPane.showMessageDialog(window,"The triangle will now move on an equation of the form y=ax^2 + bx + c");
		try {
			A_eq_str = JOptionPane.showInputDialog("What is a?");
			B_eq_str = JOptionPane.showInputDialog("What is b?");
			C_eq_str = JOptionPane.showInputDialog("What is c?");
			
			int A_eq = Integer.parseInt(A_eq_str);
			int B_eq = Integer.parseInt(B_eq_str);
			int C_eq = Integer.parseInt(C_eq_str);
			
			
		} catch(NumberFormatException problem) {
			
			try {
				A_eq_str = JOptionPane.showInputDialog("What is a?");
				B_eq_str = JOptionPane.showInputDialog("What is b?");
				C_eq_str = JOptionPane.showInputDialog("What is c?");
				
				int A_eq = Integer.parseInt(A_eq_str);
				int B_eq = Integer.parseInt(B_eq_str);
				int C_eq = Integer.parseInt(C_eq_str);
			}
			catch(NumberFormatException problem2) {
				A_eq_str = JOptionPane.showInputDialog("What is a?");
				B_eq_str = JOptionPane.showInputDialog("What is b?");
				C_eq_str = JOptionPane.showInputDialog("What is c?");
				
				
			}
		}
		
		int A_eq = Integer.parseInt(A_eq_str);
		int B_eq = Integer.parseInt(B_eq_str);
		int C_eq = Integer.parseInt(C_eq_str);
		
		sq_eq tri_eq = new sq_eq(A_eq, B_eq, C_eq);
		
		double[] quad_form_roots = tri_eq.quadForm();
		JOptionPane.showMessageDialog(window,"Quad form roots: \n" + quad_form_roots[0] + "\n" + quad_form_roots[1]);
	//DISTANCE FORMULA LAB
		JOptionPane.showConfirmDialog(window, "Press OK to start the animation");
		double x = 0;
		double y = 0;
		double speed = Math.random();
		while ((x<1920) && (y < 1080)) {
			tri.drawBackground(Color.GRAY);
			
			y = (int) tri_eq.XtoY(x);
			
			tri.drawTriangle((int)x,(int)Math.round(y),A, B, C, tri_area);
			x+=speed;
			tri.Wait(1);
		}
		double displacement = Math.sqrt( Math.pow(x-0, 2) + Math.pow(y-0, 2) );
		JOptionPane.showMessageDialog(window, "The displacement of the triangle was: \n" + displacement);
		
	//MILES PER HOUR LAB
		JOptionPane.showMessageDialog(window, "Now estimate the speed of the triangle");
		
		String miles_str, hours_str, mins_str;		
		
		try {
			miles_str = JOptionPane.showInputDialog("How many miles?");
			hours_str = JOptionPane.showInputDialog("How many hours did it take?");
			mins_str = JOptionPane.showInputDialog("How many mins did it take?");
			
			int miles = Integer.parseInt(miles_str);
			int hours = Integer.parseInt(hours_str);
			int mins = Integer.parseInt(mins_str);
			
			
		} catch(NumberFormatException problem) {
			
			try {
				miles_str = JOptionPane.showInputDialog("How many miles?");
				hours_str = JOptionPane.showInputDialog("How many hours did it take?");
				mins_str = JOptionPane.showInputDialog("How many mins did it take?");
				
				int miles = Integer.parseInt(miles_str);
				int hours = Integer.parseInt(hours_str);
				int mins = Integer.parseInt(mins_str);
			}
			catch(NumberFormatException problem2) {
				miles_str = JOptionPane.showInputDialog("How many miles?");
				hours_str = JOptionPane.showInputDialog("How many hours did it take?");
				mins_str = JOptionPane.showInputDialog("How many mins did it take?");
				
				
			}
		}
		
		int miles = Integer.parseInt(miles_str);
		int hours = Integer.parseInt(hours_str);
		int mins = Integer.parseInt(mins_str);
		
		double estimated_speed = miles / (hours + mins/60);
		
		JOptionPane.showMessageDialog(window, "You estimated that the speed of the triangle was: \n ~" + estimated_speed + "mph");
		
		//==========================================================================================================================
		
	}
}
