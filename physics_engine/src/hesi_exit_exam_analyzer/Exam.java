package hesi_exit_exam_analyzer;

import java.io.Serializable;
import java.util.ArrayList;

public class Exam implements Serializable {
	private String name;
	private ArrayList<Catagory> catagories;
	
	public Exam(String examName, ArrayList<Catagory> examCats) {
		name = examName;
		catagories = examCats;
	}
	public String getName() {
		return name;
	}
	
	public ArrayList<Catagory> getCatagories() {
		return catagories;
	}
}
