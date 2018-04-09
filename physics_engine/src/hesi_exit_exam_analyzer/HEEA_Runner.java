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

	public static final String Version = "1.4.4";
	
	
	public static Physics_frame frame = new Physics_frame();
	
	public static final String name = "HEEA_in" + ".txt";
	
	public static File input = new File(name);
	
	public static Scanner inStream;

	private static object_draw drawer;
	
	private static ArrayList<Catagory> catagories = new ArrayList<Catagory>();
	
	private static ArrayList<Catagory> green = new ArrayList<Catagory>();
	private static ArrayList<Catagory> yellow = new ArrayList<Catagory>();
	private static ArrayList<Catagory> red = new ArrayList<Catagory>();
	
	private static Catagory startCat;

	public static double diagonal = Math.sqrt(Math.pow(Settings.width,2) + Math.pow(Settings.height,2));
	
	
	
	public static void main(String[] args) {
		
		drawer = new object_draw(frame);
		
		frame = drawer.frame;
		
		frame.setColor(Color.WHITE);

		frame.setTitle("HESI Exit Exam Analyzer: V" + Version + "         Programmed by Alec Pannunzio");
	
		setSettings();
		
		
		try {
			inStream = new Scanner(input);
			
			System.out.println("HEEA_in file found");		
			
//			drawer.start();
		
			readInput();
			
			sort();

			output();
			
		}catch(FileNotFoundException f) {
			JOptionPane.showMessageDialog(frame, "The input file was not found", "File not found", 0);
		}
		
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
	

	private static void readInput() {
		
		// (<number>) <catagoryName>  <catagoryScore>  <numberOfQuestions>
		
		int nextInputType = -1; //0 for cat name, 1 for cat score, 2 for cat questions, -1 for none
		
		String next;
		Catagory currentCat = new Catagory(drawer,"nullBlob");
		
		int tokenCount = 0,testInt;
		String nTemp = "";
		boolean skip = false;
		
		System.out.println("reading...");
		
		next = inStream.next();
		
		while (inStream.hasNext()) {
			tokenCount++;
			
			skip = false;
			
			if (next.contains("(") && next.contains(")") ) {  // (<number>)
				assert nextInputType == -1;
				nextInputType = 0;
			}else if (nextInputType == 0) { // <catagoryName>
				if (currentCat.getName() != "nullBlob") {
					currentCat.setNextCat(new Catagory(drawer,next));
					currentCat = currentCat.getNextCat();
					catagories.add(currentCat);
					
					while (! inStream.hasNextInt()) {
						currentCat.setName(currentCat.getName() + " " + inStream.next());
					}
					nextInputType++;
					
				}else { //this is the first cat
					startCat = new Catagory(drawer,next);
					currentCat = startCat;
					catagories.add(currentCat);
				
					
					while (! inStream.hasNextInt()) {
						currentCat.setName(currentCat.getName() + " " + inStream.next());
					}
					nextInputType++;
				}
				
				
			}else if (nextInputType == 1) { // <catagoryScore>
				try {
					currentCat.setScore(Integer.parseInt(next));
					nextInputType++;
				}catch(NumberFormatException n) {
					n.printStackTrace();
				}
				
			}else if (nextInputType == 2) { // <numberOuestions>
				try {
					currentCat.setQuestions(Integer.parseInt(next));
					nextInputType++;
				}catch(NumberFormatException n) {
					n.printStackTrace();
				}
			}
			
			if (! skip)	next = inStream.next();
		}
		
		System.out.println("Tokens: " + tokenCount);	
	}
	
	
	private static void sort() {
		
		//sorting big list
		Collections.sort( catagories, new Comparator<Catagory>() {
		     
			public int compare(Catagory o1, Catagory o2) {
				      return Integer.compare(o1.getScore(), o2.getScore());
				}	
		});
		
		
		//creating sublists
		Catagory cCad = catagories.get(0);
		int i = 1;
		while( (cCad.getScore() <= 749) &&  (i < catagories.size()) ) {
			red.add(cCad);
			cCad.setColor(Color.red);
			cCad = catagories.get(i);
			i++;
		}
		
		while (cCad.getScore() <= 850 && (i < catagories.size())) {
			yellow.add(cCad);
			cCad.setColor(Color.yellow);
			cCad = catagories.get(i);
			i++;
		}
		
		do { 
			green.add(cCad);
			cCad.setColor(Color.green);
			cCad = catagories.get(i);
			System.out.println("green");
			i++;
		}while( (i < catagories.size()));

		green.add(cCad);
		cCad.setColor(Color.green);
		
		Comparator<Catagory> qComp = new Comparator<Catagory>() {
		     
			public int compare(Catagory o1, Catagory o2) {
				      return Integer.compare(o2.getQuestions(), o1.getQuestions());
				}	
		};
		
		//sorting sublists
		Collections.sort(green,qComp);
		Collections.sort(yellow,qComp);
		Collections.sort(red,qComp);
		
		
	}
	
	
	private static void output() {

		Catagory cCad;
		for (int i = 0; i < red.size(); i++) {
			cCad = red.get(i);
			cCad.setPos(Settings.width * 0.1, 20 * (i+2),0);
			drawer.add(cCad);
			System.out.println(cCad.getName() + "   Score: " + cCad.getScore() + " Questions: " + cCad.getQuestions());
		}
		
		for (int i = 0; i < yellow.size(); i++) {
			cCad = yellow.get(i);
			cCad.setPos(Settings.width * 0.1, 20 * (i+2+red.size()),0);
			drawer.add(cCad);
			System.out.println(cCad.getName() + "   Score: " + cCad.getScore() + " Questions: " + cCad.getQuestions());
		}
		
		for (int i = 0; i < green.size(); i++) {
			cCad = green.get(i);
			cCad.setPos(Settings.width * 0.1, 20 * (i+2+red.size() + yellow.size()),0);
			drawer.add(cCad);
			System.out.println(cCad.getName() + "   Score: " + cCad.getScore() + " Questions: " + cCad.getQuestions());
		}
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		drawer.pause();
		
		frame.setSize(Settings.width+1, Settings.height+1);
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
