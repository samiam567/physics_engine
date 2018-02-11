package Physics_engine;

import java.awt.Font;
import java.awt.Graphics;

public class Text extends physics_object {
	private String text;
	private Font font = new Font("TimesRoman", Font.BOLD,(int) Math.round(.01 * Settings.height) );
	private double fontSize, textLength;
	private String[] lines;
	public boolean textWrapping = true;
	
	public Text(double x1, double y1, String text1) {
		setPos(x1,y1,0);
		text = text1;
		updateFont();
	}
	
	public Text(double x1, double y1, String text1, Font font1) {
		setPos(x1,y1,0);
		font = font1;
		text = text1;
		updateFont();
	}
	
	public void setFont(Font font1) {
		font = font1;
		updateFont();
	}
	
	private void updateFont() {
		
		
		if (textWrapping) {
			fontSize = font.getSize();
			
			// allow for use of \n
			while (text.contains("\n")) {
				text = text.replaceFirst("\n","`");
			}
			
			//go through the text and add a newline marker (`) when the text gets to the end of the frame
			int xPosOfTheEnd = x;
			String firstPart;
			String endPart;
			for (int i = 0; i < text.length(); i++) { 
				xPosOfTheEnd += fontSize;
				
				if (text.substring(i, i+1).equals("`")) {
					xPosOfTheEnd = x;
				}
				
				if (xPosOfTheEnd >= 1.8 * Settings.width) {
					System.out.println("| \n" + text);
					xPosOfTheEnd = x;
					firstPart = text.substring(0, i);
					endPart = text.substring(i+1);
					text = firstPart + "`" + endPart;
					System.out.println(text);
				}
			}
			
			//use an array(physics_engine.array) to add the lines into an array
			array lineReturnHandler = new array("String");
			lineReturnHandler.getValues(text, "`");
			
			lines = lineReturnHandler.getArray("1");
		}else {
			lines = new String[] {text};
		}
	}
	
	public void paint(Graphics page) {
		page.setFont(font);
		
		try {
			for (int i = 0; i < lines.length; i++) {
				page.drawString( lines[i] ,x,(int) Math.round(y + fontSize * i * 1.1));
			}
		}catch(NullPointerException n) {
			text = "null";
			page.drawString(text, x, y);
		}
		
	}
}
