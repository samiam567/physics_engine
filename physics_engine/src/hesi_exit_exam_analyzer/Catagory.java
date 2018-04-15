package hesi_exit_exam_analyzer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;

import Physics_engine.Physics_drawable;
import Physics_engine.Settings;
import Physics_engine.object_draw;

public class Catagory extends Physics_drawable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6676940761459458494L;
	public String name;
	public int questions;
	public int score;
	private Catagory nextCat;
	private static Font catFont = new Font("TimesRoman", Font.BOLD, 15);
	
	public Catagory() {} //for serialization
	
	
	public Catagory(object_draw drawer1, String name1) {
		super(drawer1);
		name = name1;
	}
	
	public Catagory(object_draw drawer1,String name1, int questions1, int score1) {
		super(drawer1);
		name = name1;
		questions = questions1;
		score = score1;
	}

	
	public void setQuestions(int questions1) {
		questions = questions1;
	}
	
	public void setScore(int score1) {
		score = score1;
	}

	public void setNextCat(Catagory next) {
		nextCat = next;
	}
	
	public Catagory getNextCat() {
		return nextCat;
	}
	
	public String getName() {
		return name;
	}
	
	public int getQuestions() {
		return questions;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	public void paint(Graphics page) {
		
		page.setFont(catFont);
		
		page.setColor(color);
		page.fillRect(x, y-20,(int) (Settings.width/10 * Math.sqrt(questions)), 20);
		
		page.setColor(Color.black);
		page.drawString(name + "   Score: " + getScore() + "    Questions: " + getQuestions(), x, y-5);
		
	}
}
