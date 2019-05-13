package pantechToDo;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Physics_engine.Physics_engine_toolbox;
import jxl.*;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;

public class PantechToDoRunner {
	private static String version = "0.0.2";
	public static void main(String[] args) {
		Workbook workbook = null;
		WritableWorkbook 
		try {
			File file = new File("PanTech Database code test.xls");
			workbook = Workbook.getWorkbook(file);
			
			
		} catch (BiffException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		Sheet database = workbook.getSheet(0);
		WritableSheet databaseOut = workbook.createSheet("newSheet",2);
		String[] options = {"Direct cell edit","quit"};
		
		int thingToDo = 0;
		while (! options[thingToDo].equals("quit")) {
			thingToDo = JOptionPane.showOptionDialog(null, "What to do?", "main menu", 0, 0, null, options, 0);
			if (options[thingToDo].equals("Direct cell edit")) {
				int column = Physics_engine_toolbox.getIntegerFromUser(null, "What it the column #?")-1;
				int row = Physics_engine_toolbox.getIntegerFromUser(null, "What it the row #?")-1;
				String oldCellContents = database.getCell(column, row).getContents();
				String newContents = JOptionPane.showInputDialog(null,"Previous contents: " + oldCellContents + "\nNew cell contents: ");
				try {
					double numberToPutIn = Double.parseDouble(newContents);
				}catch(NumberFormatException n) {
					database.addCell(new Label(row,column,newContents));
					
				}
			}else if (options[thingToDo].equals("quit")) {
				System.exit(1);
			}
		}
	
		
		workbook.close();
		
		
	}
}
