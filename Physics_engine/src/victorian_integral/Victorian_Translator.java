package victorian_integral;

import Physics_engine.object_draw;
import javax.swing.JOptionPane;


public class Victorian_Translator {
	private Player player;
	private object_draw drawer;
	String Newspaper_Input = "";
	
	public Victorian_Translator() {
		
	}
	
	public String Run() {
		Newspaper_Input = (String) JOptionPane.showInputDialog(null, "What would you like to write?", "The drunk coward went on the bus to have some fun because he thought that he was smart.");
		return Translate(Newspaper_Input);
	}

	public String Translate(String input) 
		{
		String[] Replaced = {"the", "car", "bus", "smart", "drunk", "coward", "fun"};
		String[] Replacer = {"thy", "horseless carriage", "horseless carriage", "Afternoonified", "Arfarfan'arf", "Meater", "Nanty Narking"};
		for (int i = 0; i<Replaced.length; i++)
		{
			String Replaced_string = Replaced[i];
			String Replacer_string = Replacer[i];
			while (input.contains(Replaced_string))
			{
				input = input.replaceFirst(Replaced_string, Replacer_string);
			}
		}
		return input;
		
		}
}
