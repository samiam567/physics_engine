package Physics_engine;

import java.awt.Font;
import java.awt.Graphics;

public class ScoreBoard extends Physics_drawable {
	
	private String score_phrase,end_phrase = "";
	private double score;
	private double scoreSpeed;
	private double targetScore;
	private Font font = new Font("TimesRoman", Font.PLAIN, 15);
	public boolean roundScore = true;
	
	public ScoreBoard(object_draw drawer1, double x, double y, String score_phrase1, double score1) {
		super(drawer1);
		setPos(x,y,0);
		drawMethod = "paint";
		score_phrase = score_phrase1;
		isAnchored = true;
	}
	
	public void AddScore(int addition)
	{
		score = score + addition;
	}
	

	public ScoreBoard(object_draw drawer1) {
		super(drawer1);
		setPos(0.05 * Settings.width,Settings.height-100,0);
		score_phrase = "Score:";
		score = 0;
		drawMethod = "paint";
		
		isAnchored = true;
	}
	
	public void secondaryUpdate() {
		if (Math.abs(score - targetScore) > 0.01 * Settings.frameStep) {
			if (score < targetScore) {
				score += scoreSpeed * Settings.frameStep;
			}else {
				score -= scoreSpeed * Settings.frameStep;
			}		
		}
	}
	
	public void setFont(Font font1) {
		font = font1;
	}
	
	public void setScore(double score1) {
		score = score1;
	}
	
	public void setScorePhrase(String score_phrase1) {
		score_phrase = score_phrase1;
	}
	
	public void setEndPhrase(String end_phrase1) {
		end_phrase = end_phrase1;
	}
	
	public void setTargetScore(double targetScore1) {
		targetScore = targetScore1;
	}
	
	public void setScoreSpeed(double scoreSpeed1) {
		scoreSpeed = scoreSpeed1;
	}
	
	public void updatePoints() {
		
	}
	
	public void paint(Graphics page) {
		page.setFont(font);
		
		if (roundScore) {
			page.drawString(score_phrase + " " + (int) Math.round(score) + end_phrase, getX(), getY());
		}else {
			page.drawString(score_phrase + " " + score + end_phrase, getX(), getY());
		}
	}

	public double getTargetScore() {
		return targetScore;
	}
	
}
