package hesi_exit_exam_analyzer;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Physics_engine.Physics_frame;
import Physics_engine.Settings;
import Physics_engine.Text;
import Physics_engine.object_draw;

public class HEEA_Runner {

	public static final String Version = "1.0.0";
	
	
	public static Physics_frame frame = new Physics_frame();
	public static boolean cheatMode = false;
	
	public static File input = new File("HEEA_in.txt");
	
	public static Scanner inStream;

	private static object_draw drawer;
	
	private static Catagory[] catagories;
	
	private static String[] yesOrNo = {"yes","no"};
	
	public static double diagonal = Math.sqrt(Math.pow(Settings.width,2) + Math.pow(Settings.height,2));
	
	public static Text output;
	
	public static void main(String[] args) {
		
		drawer = new object_draw(frame);
		
		drawer.frame.setColor(Color.BLUE);

		drawer.frame.setTitle("HESI Exit Exam Analyzer: V" + Version + "         Programmed by Alec Pannunzio");
	
	
		setSettings();
		
		resize();
		
		output = new Text(drawer,Settings.width *0.1, Settings.height * 0.1,"Output Here");
		
	
		drawer.add(output);
		
		
		try {
			inStream = new Scanner(input);
			
			System.out.println("HEEA_in file found");		
			
			calculate();

			drawer.start();
		}catch(FileNotFoundException f) {
			JOptionPane.showMessageDialog(frame, "The input file was not found", "File not found", 0);
		}
		
	}
	
	public static void calculate() {
		while (inStream.hasNext()) {
			
			
			
		}
		
	}
	


	
	public static void setSettings() {
		Settings.collision_algorithm = 5;
		Settings.rotationAlgorithm = 6;
		Settings.timeOutTime = 300;
		
		
		Settings.distanceFromScreenMeters = 0.0001;
		
		Settings.width = 1400;
		Settings.height = 1000;
		Settings.depth = 5000;
	}
	
	public static void resize() {
	
		
		diagonal = Math.sqrt(Math.pow(Settings.width,2) + Math.pow(Settings.height,2));	
	}
}
