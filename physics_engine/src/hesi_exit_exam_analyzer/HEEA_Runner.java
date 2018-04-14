package hesi_exit_exam_analyzer;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Physics_engine.Physics_engine_toolbox;
import Physics_engine.Physics_frame;
import Physics_engine.Settings;
import Physics_engine.Text;
import Physics_engine.drawable;
import Physics_engine.object_draw;

public class HEEA_Runner {

	public static final String Version = "1.4.6";
	
	
	public static Physics_frame frame = new Physics_frame();
	
	public static final String inName = "HEEA_in" + ".txt";
	public static final String outName = "HEEA_out" + ".txt";
	
	public static File input = new File(inName);
	public static File output = new File(outName);
	
	private static object_draw drawer;
	
	

	public static double diagonal = Math.sqrt(Math.pow(Settings.width,2) + Math.pow(Settings.height,2));
	
	
	
	public static void main(String[] args) {
		
		drawer = new object_draw(frame);
		
		frame = drawer.frame;
		
		frame.setColor(Color.WHITE);

		frame.setTitle("HESI Exit Exam Analyzer: V" + Version + "         Programmed by Alec Pannunzio");
	
		setSettings();
		
		HEEA_scanner scanner = new HEEA_scanner(input,output);
		
		scanner.run(drawer);
		
		
		while (frame.isShowing()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Terminating program");
		
		System.exit(1);
		
	}
	

	
	public static void setSettings() {
		Settings.collision_algorithm = 5;
		Settings.rotationAlgorithm = 6;
		Settings.timeOutTime = 1000;
		
		Settings.distanceFromScreenMeters = 0.0001;
		
		Settings.width = 1400;
		Settings.height = 1000;
		Settings.depth = 5000;
		
		drawer.setFrameTimeMultiplier(1000);
	}
	
	
	public static void resize() {
		diagonal = Math.sqrt(Math.pow(Settings.width,2) + Math.pow(Settings.height,2));	
	}
	
}
