package ballzs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Save_file implements Serializable {
	public String player_name;
	public int level, ball_stash, high_score;

	public Ball[] balls1 = {};
	public ArrayList<Ball> balls = new ArrayList<Ball>(Arrays.asList(balls1));
	
	public Box[] boxes1 = {};
	public ArrayList<Box> boxes = new ArrayList<Box>(Arrays.asList(boxes1));
	
	public Save_file(String player_name2, int level2, int ball_stash2,ArrayList<Ball> balls2, ArrayList<Box> boxes2, int high_score1) {
		player_name = player_name2;
		level = level2;
		ball_stash = ball_stash2;
		balls = balls2;
		boxes = boxes2;
		high_score = high_score1;
	}
}
