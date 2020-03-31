package physics_sims;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Adder {
	public static void main(String[] args) {
		//File inFile = new File("stuffToAdd.txt");
		String inFile = JOptionPane.showInputDialog(null,"what do you want to add?");

			Scanner scan = new Scanner(inFile);
			
			double total = 0;
			int nums = 0;
			
			while(scan.hasNext()) {
				try {
					total += Double.parseDouble(scan.next());
					nums++;
				}catch(NumberFormatException n ) {
					System.out.println( " bad number");
				}
			}
			
			System.out.println("Total: " + total);
			System.out.println("Average: " + total/nums);
			
			JOptionPane.showMessageDialog(null, "Number of numbers: " + nums);
			JOptionPane.showMessageDialog(null, "Total: " + total);
			JOptionPane.showMessageDialog(null, "Average: " + total/nums);
			
		
		scan.close();
	}
}
