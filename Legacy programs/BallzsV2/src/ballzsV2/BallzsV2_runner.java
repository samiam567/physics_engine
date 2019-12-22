package ballzsV2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class BallzsV2_runner {

	public static void main(String[] args) {
		System.out.println("Running");
		Game game = new Game();
		game.initiate();
		
	}
	
	public void saveBZSave(String player_name, int level2,int ball_stash,ArrayList<Ball> balls, ArrayList<Box> boxes,int high_score) throws FileNotFoundException, IOException {
		System.out.println("Saving in progress...");
		ObjectOutputStream saver = new ObjectOutputStream(new FileOutputStream("BallzsV2_save.txt"));
		Save_file save = new Save_file(player_name,level2,ball_stash,balls,boxes,high_score);
		saver.writeObject(save);
		saver.close();
		System.out.println("Save Complete");
	}

	public Save_file loadBZSave() throws ClassNotFoundException, IOException {
		System.out.println("Importing preferances...");
		ObjectInputStream loader = new ObjectInputStream(new FileInputStream("BallzsV2_save.txt"));
		Save_file save_file = (Save_file) loader.readObject();
		loader.close();
		return save_file;
		
	}

}
