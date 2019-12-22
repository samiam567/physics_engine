package chutes_and_ladders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JFrame;


public class Chutes_And_Ladders_Main  extends JFrame {
	public static Chutes_and_ladders_cpu cpu = new Chutes_and_ladders_cpu();
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		//Setup the canvas
		
			JFrame jFrame = new JFrame();
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jFrame.setVisible(true);
			System.out.println("Jarvis is now online");
			jFrame.setSize(1920,1080);

			jFrame.getContentPane().add( cpu ); 
			
			

	}
	public  void saveCLSave(String player_num2, Object board_eq2, boolean game_on2,int start2, int player_num_int2, int i_plus12, int games2, Object[] players12,ArrayList<Object> players2, String[] players_names12,ArrayList<String> players_names2) throws FileNotFoundException, IOException {
		System.out.println("Saving in progress...");
		ObjectOutputStream saver = new ObjectOutputStream(new FileOutputStream("chutes_and_ladders_save.txt"));
		Save_file save = new Save_file(player_num2,board_eq2,game_on2 ,start2, player_num_int2, i_plus12, games2,players12,players2,players_names12,players_names2);
		saver.writeObject(save);
		saver.close();
		System.out.println("Save Complete");
	}

	public Save_file loadCLSave() throws ClassNotFoundException, IOException {
		System.out.println("Importing preferances...");
		ObjectInputStream loader = new ObjectInputStream(new FileInputStream("chutes_and_ladders_save.txt"));
		Save_file save_file = (Save_file) loader.readObject();
		loader.close();
		return save_file;
		
	}
}
	


