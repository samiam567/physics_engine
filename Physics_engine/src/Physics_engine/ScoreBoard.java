package Physics_engine;

import java.awt.Graphics;

public class ScoreBoard extends physics_object {
	
	private String score_phrase,end_phrase = "";
	private double score;
	private double scoreSpeed;
	private double targetScore;
	
	public ScoreBoard(double x, double y, String score_phrase1, double score1) {
		setPos(x,y,0);
		drawMethod = "paint";
		score_phrase = score_phrase1;
		isTangible = false;
		isAnchored = true;
	}
	
	public void AddScore(int addition)
	{
		score = score + addition;
	}
	

	public ScoreBoard() {
		setPos(0.05 * Settings.width,Settings.height-100,0);
		score_phrase = "Score:";
		score = 0;
		drawMethod = "paint";
		
		isTangible = false;
		isAnchored = true;
	}
	
	protected void secondaryUpdate() {
		if (Math.abs(score - targetScore) > 0.01 * Settings.frameStep) {
			if (score < targetScore) {
				score += scoreSpeed * Settings.frameStep;
			}else {
				score -= scoreSpeed * Settings.frameStep;
			}
			
		
			
		}
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
		page.drawString(score_phrase + " " + (int) Math.round(score) + end_phrase, x, y);
	}

	public double getTargetScore() {
		return targetScore;
	}
	
}
