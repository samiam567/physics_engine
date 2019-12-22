package txt_file_scanner_test;

//FINISHED
//this program grabs text from a txt file located in the package folder


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException; // dont think this is needed but not sure...
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Txt_file_scanner_test {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		/*
		
		class Test implements Serializable{
			public void print() {
				System.out.println("hi");
			}
		}
		
		Test test1 = new Test();
	
		
		ObjectOutputStream saver = new ObjectOutputStream(new FileOutputStream("info.txt"));
		saver.writeObject(test1);
		
		
		
		
		ObjectInputStream loader = new ObjectInputStream(new FileInputStream("info.txt"));
		Test test =  (Test) loader.readObject();
		test.print();
		*/
		
		File file = new File("info.txt");
		String hello_world_contents = "";
		try {
			Scanner hello_world = new Scanner(file);
			
			while (hello_world.hasNextLine()) {
				
				hello_world_contents += hello_world.next() + " ";
				
			}
			System.out.println(hello_world_contents);
			
			hello_world.close();
		}catch(FileNotFoundException error) {
			System.out.println("File not found");
		}
		
	
	}

}
