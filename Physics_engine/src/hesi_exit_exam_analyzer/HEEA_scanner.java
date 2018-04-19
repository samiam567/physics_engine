package hesi_exit_exam_analyzer;

import java.awt.Color;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import javax.swing.JOptionPane;
import Physics_engine.Settings;
import Physics_engine.object_draw;

public class HEEA_scanner {
	
	public static String[] examNames = {"Hesi Exit"};
	
	//specifics of this object
	private String studentName;
	
	private File inFile;
	private Scanner inStream;
	
	PrintStream outStream; 
	
	private object_draw drawer;
	
	private LoadThread dataLoader; //this is a thread that will load the database in the background

	
	//data
	private Database database = new Database();
	
	public boolean databaseLoaded = false;
	private boolean top = true;
	
	private int tokenCount = 0;
	
	private static ArrayList<Catagory> catagories = new ArrayList<Catagory>();
	private static ArrayList<Catagory> catagories_raw = new ArrayList<Catagory>();
	
	private static ArrayList<Catagory> green;
	private static ArrayList<Catagory> yellow;
	private static ArrayList<Catagory> red;
	
	private static Catagory startCat;
		
	public HEEA_scanner(File input_file, File output_file) {
		 inFile = input_file;
		 
		 try {
			outStream = new PrintStream(new FileOutputStream(output_file));
		 } catch (FileNotFoundException e) {
			 JOptionPane.showMessageDialog(drawer.frame, "The output file was not found", "File not found", 0);
		 }
		 
		 dataLoader = new LoadThread(this);		
	}
	
	public void read(object_draw drawer1) {
		drawer = drawer1;
		
		drawer.frame.setVisible(false);		
				
		try {
			inStream = new Scanner(inFile);
			
			System.out.println("HEEA_in file found");		
			
			readInput();
			
			JOptionPane.showMessageDialog(null, "The input was scanned and imported \n The scanner found " + tokenCount + " Tokens and " + catagories.size() + " catagories", "Scan successful", 1);
			
			catagories = sort(catagories);
			
			run(catagories_raw,catagories,"menu");
			
		}catch(FileNotFoundException f) {
			JOptionPane.showMessageDialog(drawer.frame, "The input file was not found", "File not found", 0);
		}
	}
		
	private void run(ArrayList<Catagory> catagories_raw,ArrayList<Catagory> catagories, String menuName) {
			String[] thingsToDo;
		
			if (top) {
				thingsToDo = new String[5];
				thingsToDo[0] = "Print to file";
				thingsToDo[1] = "Summary Analysis Report";
				thingsToDo[2] = "Add Exam to database (must be a specific student's exam)";
				thingsToDo[3] = "Retrieve student from database";
				thingsToDo[4] = "Exit";
			}else {
				thingsToDo = new String[3];
				thingsToDo[0] = "Print to file";
				thingsToDo[1] = "Summary Analysis Report";
				thingsToDo[2] = "Select a different one of this student's exams";
			}
			
			String nextThing = "";
			do {
				nextThing = (String) JOptionPane.showInputDialog(null, "What would you like to do?", menuName, 3, null, thingsToDo, null); //getting the menu input from the user
				drawer.frame.setVisible(false);
				try {
					switch(nextThing) {
					
						case("Summary Analysis Report"):
							System.out.println("printing to the screen");
							drawer.frame.setVisible(true);
							sort(catagories);
							output(nextThing,catagories_raw,catagories);
							break;
							
						case("Add Exam to database (must be a specific student's exam)"):
							System.out.println("Adding this exam to the database");
							addToDatabase();
							break;
							
						case("Retrieve student from database"):
							System.out.println("Retrieving student from database");
							readFromDatabase();
							nextThing = "hi";
							break;
							
						case("Print to file"):
							System.out.println("Printing to file");	
							output(getPrintFormat(),catagories_raw,catagories);
							JOptionPane.showMessageDialog(drawer.frame,"The print was successful. Look inside " +  HEEA_Runner.outName + " For your print", "Print successful", 1);
							break;			
					}
					
				}catch(NullPointerException n) { //if the user pressed the cancel button 
					nextThing = "Exit";
				}
				
			}while(nextThing != "Exit" && nextThing != "Select a different one of this student's exams");
			
			
						
	}
	
	private Student getStudentFromUser() {
		String name = JOptionPane.showInputDialog(null,"What is this student's first and last name? (firstname lastname)").toLowerCase();
		
		int year = -1;
		boolean error = false;
		do {
			try {
				year = Integer.parseInt(JOptionPane.showInputDialog(drawer.frame,"What year does/did this student graduate?"));
				error = false;
			}catch(NumberFormatException n) {
				error = true;
			}
		}while(error);
		
		
		int nameIndx = 0;
		char cChar;
		for (int i = 0; i < name.length(); i++) {
			cChar = name.charAt(i);
			nameIndx += cChar * i + cChar;
		}
		
		Student student = database.getStudent(year,nameIndx);
		
		if (student == null) {
			if (JOptionPane.showOptionDialog(drawer.frame, "The student " + name + " graduating " + year + " was not found. \n Would you like to create a new data-file for this Student?", "Create a new Student?", 2, 2, null, new String[] {"yes","no"}, 1) == 0) {
				student = new Student(name);
				database.addStudent(student, year, nameIndx);
			}else {
				return null;
			}
		}
		
		return student;
	}
	
	private void addToDatabase() {
		System.out.println("Waiting for database to load... please wait");
		
		if (! databaseLoaded) {
			loadDatabase();
			databaseLoaded = true;
		}
		System.out.println("Database loaded");

		
		String ExamName = (String) JOptionPane.showInputDialog(null, "What exam is this?", "Select the exam you scanned", 3, null, HEEA_scanner.examNames, null); //getting the menu input from the user
		
		Student student = getStudentFromUser();
		
		student.addExam(new Exam(ExamName,catagories_raw));
		
		System.out.println("Saving Data");
		saveDatabase();
		
	}
	
	private void readFromDatabase() {
		System.out.println("Waiting for database to load... please wait");
		
		if (! databaseLoaded) {
			loadDatabase();
			databaseLoaded = true;
		}
		System.out.println("Database loaded");
		
		Student student = getStudentFromUser();

		JOptionPane.showMessageDialog(drawer.frame, "Retrieved " + student.getName() + "  with " + student.getExams().length + " exams logged", "Student retrieved", 1);
		
		top = false; //indicate that we are not reading from the scan but are reading from the database
		
		Exam[] exams = student.getExams();
		String[] examNames = new String[exams.length + 1];
		for (int i = 0; i < exams.length; i++) {
			examNames[i] = exams[i].getName();
		}
		examNames[examNames.length - 1] = "exit the database and return to the scan";
		
		String examName;
		Exam currentExam;
		do {
			examName = (String) JOptionPane.showInputDialog(null, "Which of this student's exams would you like to look at?", "Select the exam you want to read", 3, null, examNames, null);
			
			if (examName != "exit the database and return to the scan") {
				currentExam = student.getExam(examName);
				run(currentExam.getCatagories(),sort(currentExam.getCatagories()),student.getName() + " " + examName);
			}
			
		}while (examName != "exit the database and return to the scan");
		
		top = true; //indicate that we are now reading from the scan again
	}

	private String getPrintFormat() {
		String[] printFormats = {"Read-friendly","Copy/Paste-friendly","Copy/Paste-friendly SPE Categories"};
		
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
					catagories_raw.add(currentCat);
					
					while (! inStream.hasNextInt()) {
						currentCat.setName(currentCat.getName() + " " + inStream.next());
					}
					nextInputType++;
					
				}else { //this is the first cat
					startCat = new Catagory(drawer,next);
					currentCat = startCat;
					catagories.add(currentCat);
					catagories_raw.add(currentCat);
				
					
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
	
	
	private ArrayList<Catagory> sort(ArrayList<Catagory> catagories) {
		green = new ArrayList<Catagory>();
		yellow = new ArrayList<Catagory>();
		red = new ArrayList<Catagory>();
		
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
		
		return catagories;
	}
	
	
	private void output(String printFormat,ArrayList<Catagory> catagories_raw, ArrayList<Catagory> catagories) {
		
		System.setOut(outStream);
		
		System.out.println("--------------------------------------------------------------------------------------");
		if ((printFormat == "Summary Analysis Report") || (printFormat == "Read-friendly")) {
			Catagory cCad;
			for (int i = 0; i < red.size(); i++) {
				cCad = red.get(i);
				
				if (printFormat == "Summary Analysis Report")  {
					cCad.setPos(Settings.width * 0.1, 20 * (i+2),0);
					drawer.add(cCad);
				}
				
				if (printFormat == "Read-friendly") System.out.println(cCad.getName() + "   Score: " + cCad.getScore() + " Questions: " + cCad.getQuestions() + " RedZone");
			}
			
			for (int i = 0; i < yellow.size(); i++) {
				cCad = yellow.get(i);
				
				if (printFormat == "Summary Analysis Report")  {
					cCad.setPos(Settings.width * 0.1, 20 * (i+2+red.size()),0);
					drawer.add(cCad);
				}
				
				if (printFormat == "Read-friendly") System.out.println(cCad.getName() + "   Score: " + cCad.getScore() + " Questions: " + cCad.getQuestions()  + " YellowZone");
			}
			
			for (int i = 0; i < green.size(); i++) {
				cCad = green.get(i);
				
				if (printFormat == "Summary Analysis Report")  {
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
			for (Catagory cCad : catagories_raw) {
				System.out.println(cCad.getName());
			}
			System.out.println(" ");
			
			System.out.println("Catagory Scores (in the same order as the names):");
			for (Catagory cCad : catagories_raw) {
				System.out.println(cCad.getScore());
			}
			
			System.out.println(" ");
		}else if (printFormat == "Copy/Paste-friendly SPE Categories") {
			String[] select_catagories_array = {"assessment","analysis","planning","implementation","evaluation","human flourishing","nursing judgment","nursing practice","professional identity","spirit of inquiry","dimensions of patient care","pain and suffering","safety and quality","ethical legal","effective communication","member of team","scope of practice","communication","systems/team functions","research and ebp","quality improvement (QI)","quality improvement","basic safety design principles","culture of safety & safety monitoring","national patient safety resources","nursing informatics","communication","systems/team functions"};
			ArrayList<String> select_catagories = new ArrayList<String>();
			for (String s : select_catagories_array) {
				select_catagories.add(s);
			}
			
			System.out.println("Catagory Names:");
			for (Catagory cCad : catagories_raw) {
				if (select_catagories.contains(cCad.getName().toLowerCase())) {
					System.out.println(cCad.getName());
				}
			}
			System.out.println(" ");
			
			System.out.println("Catagory Scores (in the same order as the names):");
			for (Catagory cCad : catagories_raw) {
				if (select_catagories.contains(cCad.getName().toLowerCase())) {
					System.out.println(cCad.getScore());
				}
				
			}
			
			System.out.println(" ");
		}
		
		System.setOut(System.out);
	}
	
	
	public void loadDatabase() {
		JOptionPane.showMessageDialog(null, "Press ok to load the database (this will take a while)", "Load Database", 1);
		try {
			ObjectInputStream loader = new ObjectInputStream(new FileInputStream("HEEA_data.txt"));
			database = (Database) loader.readObject();
			loader.close();
			
		}catch(InvalidClassException e) {
			System.out.println("HEEA_data is missing or corrupted"); 
			e.printStackTrace();
		}catch(EOFException e) {
			System.out.println("HEEA_data is missing or corrupted");
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			System.out.println("HEEA_data is missing or corrupted");
			e.printStackTrace();
		}catch(IOException e) {
			System.out.println("HEEA_data is missing or corrupted");
			e.printStackTrace();
		}
	}
	
	public void saveDatabase() {
		
		try {
			System.out.println("Saving in progress...");
			ObjectOutputStream saver = new ObjectOutputStream(new FileOutputStream("HEEA_data.txt"));
			saver.writeObject(database);
			saver.close();
			System.out.println("Save Complete");
		}catch(InvalidClassException e) {
			e.printStackTrace();
			System.out.println("HEEA_data is missing or corrupted"); 
		}catch(EOFException e) {
			e.printStackTrace();
			System.out.println("HEEA_data is missing or corrupted");
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println("HEEA_data is missing or corrupted");
		}
	}

	
	
	
}
