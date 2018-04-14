package hesi_exit_exam_analyzer;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Physics_engine.Settings;
import Physics_engine.object_draw;

public class HEEA_scanner {
	
	
	//specifics of this object
	private String studentName;
	
	private File inFile;
	private Scanner inStream;
	
	PrintStream outStream; 
	
	private object_draw drawer;
	
	
	//data
	private int tokenCount = 0;
	
	private static ArrayList<Catagory> catagories = new ArrayList<Catagory>();
	
	private static ArrayList<Catagory> green = new ArrayList<Catagory>();
	private static ArrayList<Catagory> yellow = new ArrayList<Catagory>();
	private static ArrayList<Catagory> red = new ArrayList<Catagory>();
	
	private static Catagory startCat;
		
	public HEEA_scanner(File input_file, File output_file) {
		 inFile = input_file;
		 
		 try {
			outStream = new PrintStream(new FileOutputStream(output_file));
		 } catch (FileNotFoundException e) {
			 JOptionPane.showMessageDialog(drawer.frame, "The output file was not found", "File not found", 0);
		 }
		 
		
	}
	
	public void run(object_draw drawer1) {
		drawer = drawer1;
		
		drawer.frame.setVisible(false);
		try {
			inStream = new Scanner(inFile);
			
			System.out.println("HEEA_in file found");		
			
			readInput();
			
			JOptionPane.showMessageDialog(drawer.frame, "The input was scanned and imported \n The scanner found " + tokenCount + " Tokens and " + catagories.size() + " catagories", "Scan successful", 1);
			
			
			sort();
			
			
			
			String[] thingsToDo = {"Print to file","Print to Screen","Exit"};
			String nextThing = "";
			do {
				nextThing = (String) JOptionPane.showInputDialog(drawer.frame, "What would you like to do?", "Menu", 3, null, thingsToDo, null); //getting the menu input from the user
				drawer.frame.setVisible(false);
				try {
					switch(nextThing) {
					
						case("Print to Screen"):
							System.out.println("printing to the screen");
							drawer.frame.setVisible(true);
							output(nextThing);
							break;
							
						case("Print to file"):
							System.out.println("Printing to file");	
							output(getPrintFormat());
							JOptionPane.showMessageDialog(drawer.frame,"The print was successful. Look inside " +  HEEA_Runner.outName + " For your print", "Print successful", 1);
							break;			
					}
					
				}catch(NullPointerException n) { //if the user pressed the cancel button 
					nextThing = "Exit";
				}
				
			}while(nextThing != "Exit");
			
			return;
						
			
			
		}catch(FileNotFoundException f) {
			JOptionPane.showMessageDialog(drawer.frame, "The input file was not found", "File not found", 0);
		}
	}
	
	private String getPrintFormat() {
		String[] printFormats = {"Read-friendly","Copy/Paste-friendly","Copy/Paste-friendly select catagories"};
		
		return (String) JOptionPane.showInputDialog(drawer.frame, "What print format?", "Print to File", 3, null, printFormats, null);
	}
	
	private void readInput() {
		
		// (<number>) <catagoryName>  <catagoryScore>  <numberOfQuestions>
		
		int nextInputType = -1; //0 for cat name, 1 for cat score, 2 for cat questions, -1 for none
		
		String next;
		Catagory currentCat = new Catagory(drawer,"nullBlob");
		
		
		boolean skip = false;
		
		System.out.println("reading...");
		
		next = inStream.next();
		
		while (inStream.hasNext()) {
			tokenCount++;
			
			skip = false;
			
			if (next.contains("(") && next.contains(")") ) {  // (<number>)
				try {
					Integer.parseInt(next.substring(1, next.length()-1));
					nextInputType = 0;
				}catch(NumberFormatException n) {}
				
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
	
	
	private void sort() {
		
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
	
	
	private void output(String printFormat) {
		
		System.setOut(outStream);
		
		System.out.println("--------------------------------------------------------------------------------------");
		if ((printFormat == "Print to Screen") || (printFormat == "Read-friendly")) {
			Catagory cCad;
			for (int i = 0; i < red.size(); i++) {
				cCad = red.get(i);
				
				if (printFormat == "Print to Screen")  {
					cCad.setPos(Settings.width * 0.1, 20 * (i+2),0);
					drawer.add(cCad);
				}
				
				if (printFormat == "Read-friendly") System.out.println(cCad.getName() + "   Score: " + cCad.getScore() + " Questions: " + cCad.getQuestions() + " RedZone");
			}
			
			for (int i = 0; i < yellow.size(); i++) {
				cCad = yellow.get(i);
				
				if (printFormat == "Print to Screen")  {
					cCad.setPos(Settings.width * 0.1, 20 * (i+2+red.size()),0);
					drawer.add(cCad);
				}
				
				if (printFormat == "Read-friendly") System.out.println(cCad.getName() + "   Score: " + cCad.getScore() + " Questions: " + cCad.getQuestions()  + " YellowZone");
			}
			
			for (int i = 0; i < green.size(); i++) {
				cCad = green.get(i);
				
				if (printFormat == "Print to Screen")  {
					cCad.setPos(Settings.width * 0.1, 20 * (i+2+red.size() + yellow.size()),0);
					drawer.add(cCad);
				}
				
				if (printFormat == "Read-friendly") System.out.println(cCad.getName() + "   Score: " + cCad.getScore() + " Questions: " + cCad.getQuestions()  + " GreenZone");
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			drawer.pause();
			
			drawer.frame.setSize(Settings.width+1, Settings.height+1);
			
			System.out.println(" ");
			
		}else if (printFormat == "Copy/Paste-friendly") {
			System.out.println("Catagory Names:");
			for (Catagory cCad : catagories) {
				System.out.println(cCad.getName());
			}
			System.out.println(" ");
			
			System.out.println("Catagory Scores (in the same order as the names):");
			for (Catagory cCad : catagories) {
				System.out.println(cCad.getScore());
			}
			
			System.out.println(" ");
		}else if (printFormat == "Copy/Paste-friendly select catagories") {
			String[] select_catagories_array = {"assessment","analysis","planning","implementation","evaluation","human flourishing","nursing judgment","nursing practice","professional identity","spirit of inquiry","dimensions of patient care","pain and suffering","safety & quality","ethical/legal","effective communication","member of a team","scope of practice","team communication","systems/team functions","research and ebp","quality improvement","basic safety design","culture of safety","national patient safety resources","informatics"};
			ArrayList<String> select_catagories = new ArrayList<String>();
			for (String s : select_catagories_array) {
				select_catagories.add(s);
			}
			
			System.out.println("Catagory Names:");
			for (Catagory cCad : catagories) {
				if (select_catagories.contains(cCad.getName().toLowerCase())) {
					System.out.println(cCad.getName());
				}
			}
			System.out.println(" ");
			
			System.out.println("Catagory Scores (in the same order as the names):");
			for (Catagory cCad : catagories) {
				if (select_catagories.contains(cCad.getName().toLowerCase())) {
					System.out.println(cCad.getScore());
				}
				
			}
			
			System.out.println(" ");
		}
		
		System.setOut(System.out);
	}

	
	
	
}
