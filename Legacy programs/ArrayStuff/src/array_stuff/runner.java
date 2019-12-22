package array_stuff;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


//This program alphabetizes every line in the arrayStuffInput.txt file.   Was programmed For the 2017-2018 AP Java final.
// (I got a 100% :)  ) 

public class runner {
	public static void main(String[] args) {
		array inputLines = new array("String");
		
		File input = new File("arrayStuffInput.txt");
		inputLines.getArray(" ");
		try {
			Scanner inputStream = new Scanner(input);
			
			while (inputStream.hasNextLine()) {
				
				inputLines.add(inputStream.nextLine());
				
			}
			
			inputStream.close();
			
			array[] inputArrays = new array[inputLines.getArray("").length];
			
			for (int i = 0; i < inputArrays.length; i++) {
				inputArrays[i] = new array("String");
				inputArrays[i].getValues(inputLines.getArray("")[i]," ");
			}
			
			for (array i : inputArrays) {
				i.sort();
				String[] iArray = i.getArray("");
				for (String a : iArray) {
					System.out.println(a);
				}
				
				System.out.println(" ");
			}
			
			
			
		}catch(FileNotFoundException error) {
			System.out.println("File not found");
		}
		
		
		
	
	
	}
}
