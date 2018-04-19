package encryption;

import javax.swing.JOptionPane;

import Physics_engine.array;

public class Encryption_runner {
	private static String[] chars = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",".",",","'",";",":","<",">"," ","?","/","!","@","#","$","%","^","&","*","(",")","-","_","+","=","[","]","{","}","1","2","3","4","5","6","7","8","9","0"};
	
	private static array list = new array("String");
	
	public static void main(String[] args) {
		String message = "";
		
		do {
			try {
				message = crypt(JOptionPane.showInputDialog("Type what you want to encode/decode"));
				System.out.println(message);
				JOptionPane.showMessageDialog(null, message);
			}catch(StringIndexOutOfBoundsException s) {
				JOptionPane.showMessageDialog(null, "Invalid message", "Error: Bad Input", 0);
			}
			
		}while (JOptionPane.showConfirmDialog(null,"Do you want to calculate something else?", "Another?", 1, 1, null) == 0);
		
	}
	
	public static String crypt(String input) {
		if (input.substring(0, 1).equals("{")) {
			return decrypt(input);
		}else {
			return encrypt(input);
		}
	}
	
	private static String encrypt(String input) {
		
		boolean charFound = false; //if the char has been matched yet
		for (int i = 0; i < input.length(); i++) {
			
			charFound = false; //reset the charFound for the next char
			
			String str = input.substring(i, i+1); //getting the char
	
			for (int q = 0; q < chars.length; q++) { //searching for the char in the chars list to get the index
				
				if (chars[q].equals(str)) {
					list.add("" + q); //adding the index to the output array
					charFound = true;
					break;
				}
			}
			
			if (! charFound) {
				System.out.println("Char " + str + " was not found in the char list and is therefore not a valid char");
			}
			
			
		}
		
		return list.toString();
	}
	
	private static String decrypt(String input) {
		list.getValues(input.substring(1, input.length()-1), ",");
		String translatedMessage = "";
		System.out.println(list.toString());
		for (String str : list.getArray(" ")) {
			try {
				translatedMessage += (chars[Integer.parseInt(str)]);
			}catch(NumberFormatException n) {}
		}
		return translatedMessage;
	}
}
