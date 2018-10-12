package encryption;

import javax.swing.JOptionPane;

import Physics_engine.array;

public class Encryption_runner {
	
	
	private static final String version = "2.1.1";
	
	private static String[] chars = {" ","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",".",",","'",";",":","<",">","?","/","!","@","#","$","%","^","&","*","(",")","-","_","+","=","[","]","{","}","1","2","3","4","5","6","7","8","9","0"};
	
	private static int codingNumber = 0;
	
	private static array list = new array("String");
	
	public static void main(String[] args) {
		String message = "";
		
		do {
			try {
				message = JOptionPane.showInputDialog("Type what you want to encode/decode");
				System.out.println(message);
				message = crypt(message);
				System.out.println(message);
				JOptionPane.showMessageDialog(null, message);
				list = new array("String");
			}catch(StringIndexOutOfBoundsException s) {
				JOptionPane.showMessageDialog(null, "Invalid message", "Error: Bad Input", 0);
			}
			
		}while (JOptionPane.showConfirmDialog(null,"Do you want to do another?", "Another?", 1, 1, null) == 0);
		
	}
	
	public static String crypt(String input) {
		String[] options = {"encrypt","decrypt","crack","randEncryption"};
		
		int mode = ( JOptionPane.showOptionDialog(null, "Encrypt or decrypt?", "Choose type", 1, 1, null, options, 0));
		
		
		if  (mode == 0) {//encrypt     //(input.substring(0, 1).equals("{")) { 
			String message = encrypt(input);
			message = encode(message);
	//		message = decrypt(message);
			return message;
		}else if (mode == 1) { //decrypt
			String message = encrypt(input);
			message = decode(message);
			message = decrypt(message);
			return message;
		}else if (mode == 2) { //crack
			String cMessage,message = "";
			for (int i = 0; i < chars.length-1; i++) {
				codingNumber = i;
				list = new array("String");
				cMessage = encrypt(input);
				cMessage = decode(cMessage);
				cMessage = i + ": " + decrypt(cMessage);
				System.out.println(cMessage);
				message += cMessage + "\n";
			}
			return message;
		}else {
			codingNumber = (int) (Math.random() * chars.length);
			String message = encrypt(input);
			message = encode(message);
			message = decrypt(message);
			return message;
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
	
		for (String str : list.getArray(" ")) {
			try {
				translatedMessage += (chars[Integer.parseInt(str)]);
			}catch(NumberFormatException n) {}
		}
		return translatedMessage;
	}
	
	private static String encode(String input) {
		list.getValues(input.substring(1, input.length()-1), ",");
		
		String[] valuesStr = list.getArray("");
		int[] values = new int[valuesStr.length];
		
		for (int q=0; q < values.length; q++) values[q] = Integer.parseInt(valuesStr[q]);
		
		
		for (int i = 0; i < values.length; i++) {
			if (values[i]+codingNumber < (chars.length)) {
				values[i] += codingNumber;
			}else {
				values[i] = values[i] + codingNumber - chars.length  ;
			}
			


		}
	
	
		
		for (int q=0; q < values.length; q++) valuesStr[q] = "" + values[q];
		
		list.setValues(valuesStr);
		
		return list.toString();
	}
	
	private static String decode(String input) {
		list.getValues(input.substring(1, input.length()-1), ",");

	
		String[] valuesStr = list.getArray("");
		int[] values = new int[valuesStr.length];
	
		for (int q=0; q < values.length; q++) values[q] = Integer.parseInt(valuesStr[q]);
		
		for (int i = 0; i < values.length; i++) {
			if ((values[i]-codingNumber) >= 0) {
				values[i] -= codingNumber;
			}else {
				values[i] =  (chars.length ) + (values[i] - codingNumber);
			}
			if (values[i] < 0) System.out.println(values[i] + " " + codingNumber);
		}
		
		for (int q=0; q < values.length; q++) valuesStr[q] = "" + values[q];
		
		list.setValues(valuesStr);
		
		return list.toString();
	}
}
