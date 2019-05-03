package essay_writer;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class Essay_Generator {
	
	private static Database_loader databaseLoader;
	
	
	public static void main(String[] args) {
		databaseLoader = new Database_loader("EssayDatabase.txt");
		databaseLoader.getDatabase();
		String[] options = {"write essay","scan in essay","insert direct input","clear database","save database and quit","quit"};
		
		int thingToDo = 0;
		while (! options[thingToDo].equals("quit")) {
			thingToDo = JOptionPane.showOptionDialog(null, "What to do?", "main menu", 0, 0, null, options, 0);
			if (options[thingToDo].equals("write essay")) {
				//write the essay
				JOptionPane.showMessageDialog(null,writeEssay());
			}else if (options[thingToDo].equals("scan in essay")) {	
				databaseLoader.getDatabase().importEssay(JOptionPane.showInputDialog("What is the fileName?"));
			}else if (options[thingToDo].equals("insert direct input")) {
				String input = JOptionPane.showInputDialog("What text would you like to input?");
				Scanner scan = new Scanner(input);
				databaseLoader.getDatabase().importEssay(scan);
			}else if (options[thingToDo].equals("clear database")) { 
				databaseLoader.clearDatabase();
			}else if (options[thingToDo].equals("save database and quit")) {
				databaseLoader.closeDatabase();
				break;
			}else if (options[thingToDo].equals("quit")) {
				System.exit(1);
			}
		}
	
	}


	public static Database_loader getDatabaseLoader() {
		return databaseLoader;
	}
	
	private static String writeEssay() {
		Word nextWord = databaseLoader.getDatabase().getWord((int) (Math.random() * databaseLoader.getDatabase().words.size()));
		System.out.println(databaseLoader.getDatabase().words.size());
		System.out.println(nextWord.getName());
		String Essay = "";
		while (Essay.length() < 500) {
			Essay += nextWord.getName() + " ";
			nextWord = nextWord.getNextWord();
			
			//if the word doesn't have any connections, pick a new random word from the list
			if (nextWord.getName().equals("terminationWord934582934820")) {
				nextWord = databaseLoader.getDatabase().getWord((int) (Math.random() * databaseLoader.getDatabase().words.size()));
				Essay += "\n";
			}
		}
		
		System.out.println(Essay);
		return Essay;
	}
}
