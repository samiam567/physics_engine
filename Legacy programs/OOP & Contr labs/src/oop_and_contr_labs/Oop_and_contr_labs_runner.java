package oop_and_contr_labs;

import javax.swing.JFrame;

import oop_and_contr_labs.Oop_and_contr_labs_cpu;

public class Oop_and_contr_labs_runner {
	
	public static void main(String[] args) {
		
		JFrame jFrame = new JFrame();
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.setSize(1920,1080);
		jFrame.getContentPane().add( new Oop_and_contr_labs_cpu() ); 
	
	}

}
