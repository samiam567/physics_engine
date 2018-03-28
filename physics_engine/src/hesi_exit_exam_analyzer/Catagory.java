package hesi_exit_exam_analyzer;

import java.awt.Color;
import java.awt.Graphics;

import Physics_engine.Physics_drawable;
import Physics_engine.Physics_engine_toolbox;
import Physics_engine.Settings;
import Physics_engine.object_draw;

public class Catagory extends Physics_drawable {
	public String name;
	public int questions;
	public int score;
	private Catagory nextCat;
	private Color color;
	
	public Catagory(object_draw drawer1, String name1) {
		super(drawer1);
		name = name1;
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
	
	public void paint(Graphics page) {
		page.setFont(Physics_engine_toolbox.littleFont);
		page.drawString(name + "   Score: " + getScore() + "    Questions: " + getQuestions(), x, y);
		page.drawRect(x, y-15,(int) (Settings.width/10 * Math.sqrt(questions)), 15);
	}
}
