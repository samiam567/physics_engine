package rsa_encryption;

import java.awt.List;

import javax.swing.JOptionPane;

import Physics_engine.Physics_engine_toolbox;
import Physics_engine.array;
/// N: 264 e: 5
public class RSA_encryption_runner {
	private static final String Version = "1.0.3";
	//1279, q = 1283
	private static final int p = 1279, q = 1283; //two prime numbers (these are what are used to derive the private key)
	private static long N = p*q; //N and e make up the public key and are used to encrypt messages
	private static long e,d; //N and d make up the private key which you keep to yourself and is used to decrypt messages
	
	
	private static String[] chars = {"","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"," ",".",",","'",";",":","<",">","?","/","!","@","#","$","%","^","&","*","(",")","-","_","+","=","[","]","{","}","1","2","3","4","5","6","7","8","9","0"};
			
	
	private static int codingNumber = 10;
	
	private static array list = new array("String");
	
	public static void main(String[] args) {
		int c = (p-1) * (q-1);
	
		
	//guessing the rest of the numbers 
		//find e 
		e = 2;
		while ((c % e) == 0) {
			e++;
		}
		
		//find d 
		d = 2;
		while ((e*d) % c != 1) {
			d++;
		}

	//////////////
		
		System.out.println("N: " + N);
		System.out.println("e: " + e);
		System.out.println("d: " + d);
			
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
		String[] options = {"encrypt","decrypt"};
		
		int mode = ( JOptionPane.showOptionDialog(null, "Encrypt or decrypt?", "Choose type", 1, 1, null, options, 0));
		
		
		if  (mode == 0) {//encrypt     //(input.substring(0, 1).equals("{")) { 
			N = (long) Physics_engine_toolbox.getDoubleFromUser(null, "What is the other person's N?");
			e = (long) Physics_engine_toolbox.getDoubleFromUser(null, "What is the other person's e?");	
			String message = encrypt(input);
			message = encode(message);
			return message;
		}else if (mode == 1) { //decrypt
			String message = decode(input);
		
			message = decrypt(message);
			return message;
		}else {
			return "logic error";
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
				System.out.print(str);
				String translatedChar = (chars[Integer.parseInt(str)]);
				System.out.println(" > " + translatedChar);
				translatedMessage += translatedChar;
			}catch(NumberFormatException n) {}
		}
		return translatedMessage;
	}
	
	private static String encode(String input) {
		list.getValues(input.substring(1, input.length()-1), ",");
		
		String[] valuesStr = list.getArray("");
		long[] values = new long[valuesStr.length];
		
		for (int q=0; q < values.length; q++) values[q] = Long.parseLong(valuesStr[q]);
		
		
		for (int i = 0; i < values.length; i++) {
			values[i] = RSA(values[i],e,N); 
			System.out.println(values[i]);
		}
	
	
		
		for (int q=0; q < values.length; q++) valuesStr[q] = "" + values[q];
		
		list.setValues(valuesStr);
		
		return list.toString();
	}
	
	private static long RSA(long b, long e, long m) {
		long answer = 1;
		for (int i = 1; i <= e; i++) {
			answer *= b % m;
			answer = answer % m;
		}
		return answer;
	}
	
	private static String decode(String input) {
		list.getValues(input.substring(1, input.length()-1), ",");

		System.out.println("decode: " + list.toString());
		
		String[] valuesStr = list.getArray("");
		long[] values = new long[valuesStr.length];
	
		for (int q=0; q < values.length; q++) values[q] = Long.parseLong(valuesStr[q]);
		
		for (int i = 0; i < values.length; i++) {
			values[i] = RSA(values[i],d,N);
		}
		
		for (int q=0; q < values.length; q++) valuesStr[q] = "" + values[q];
		
		list.setValues(valuesStr);
		
		System.out.println("decoded: " + list.toString());
		
		return list.toString();
	}
}