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

	public static final String Version = "1.3.0";
	
	
	public static Physics_frame frame = new Physics_frame();
	
	public static File input = new File("HEEA_in.txt");
	
	public static Scanner inStream;

	private static object_draw drawer;
	
	private static ArrayList<Catagory> catagories = new ArrayList<Catagory>();
	
	private static Catagory startCat;

	public static double diagonal = Math.sqrt(Math.pow(Settings.width,2) + Math.pow(Settings.height,2));
	
	
	
	public static void main(String[] args) {
		
		drawer = new object_draw(frame);
		
		drawer.frame.setColor(Color.WHITE);

		drawer.frame.setTitle("HESI Exit Exam Analyzer: V" + Version + "         Programmed by Alec Pannunzio");
	
		setSettings();
		
		
		try {
			inStream = new Scanner(input);
			
			System.out.println("HEEA_in file found");		
			
			drawer.start();
		
			calculate();

			output();
		}catch(FileNotFoundException f) {
			JOptionPane.showMessageDialog(frame, "The input file was not found", "File not found", 0);
		}
		
	}
	

	private static void calculate() {
		
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
						currentCat.setName(currentCat.getName() + inStream.next());
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
	
	
	private static void output() {
	
		Collections.sort( catagories, new Comparator<Catagory>() {
			     
		public int compare(Catagory o1, Catagory o2) {
			      return Integer.compare(o1.getScore(), o2.getScore());
			}	
		});

		Catagory cCad;
		for (int i = 0; i < catagories.size(); i++) {
			cCad = catagories.get(i);
			cCad.setPos(Settings.width * 0.1, 15 * (i+2),0);
			drawer.add(cCad);
			System.out.println(cCad.getName() + "   Score: " + cCad.getScore() + " Questions: " + cCad.getQuestions());
		}
		
	}

	
	public static void setSettings() {
		Settings.collision_algorithm = 5;
		Settings.rotationAlgorithm = 6;
		Settings.timeOutTime = 1000;
		
		Settings.distanceFromScreenMeters = 0.0001;
		
		Settings.width = 1400;
		Settings.height = 1000;
		Settings.depth = 5000;
	}
	
	
	public static void resize() {
		diagonal = Math.sqrt(Math.pow(Settings.width,2) + Math.pow(Settings.height,2));	
	}
	
}
