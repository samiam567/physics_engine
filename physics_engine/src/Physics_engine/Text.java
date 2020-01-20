package Physics_engine;

import java.awt.Font;
import java.awt.Graphics;

public class Text extends Physics_drawable {
	private String text;
	private Font font = new Font("TimesRoman", Font.BOLD,(int) Math.round(.015 * Settings.height) );
	private double fontSize, textLength;
	private String[] lines;
	public boolean textWrapping = true;
	
	public Text(object_draw drawer1,double x1, double y1, String text1) {
		super(drawer1);
		setPos(x1,y1,0);
		text = text1;
		updateFont();
		isAnchored = true;
	}
	
	public Text(object_draw drawer1,double x1, double y1, String text1, Font font1) {
		super(drawer1);
		setPos(x1,y1,0);
		font = font1;
		text = text1;
		updateFont();
		isAnchored = true;
	}
	
	public void add(String newText) {
		text = "" + text + newText + " ";
		updateFont();
	}
	
	public void setFont(Font font1) {
		font = font1;
		updateFont();
	}
	
	private void updateFont() {
		
		//automatic text wrapping!!
		if (textWrapping) {
			fontSize = font.getSize();
			
			// allow for use of \n
			while (text.contains("\n")) {
				text = text.replaceFirst("\n","`");
			}
			
			//go through the text and add a newline marker (`) when the text gets to the end of the frame
			int xPosOfTheEnd = getX();
			String firstPart;
			String endPart;
			for (int i = 0; i < text.length(); i++) { 
				xPosOfTheEnd += fontSize;
				
				if (text.substring(i, i+1).equals("`")) {
					xPosOfTheEnd = getX();
				}
				
				if (xPosOfTheEnd >= 1.8 * Settings.width) {
					System.out.println("| \n" + text);
					xPosOfTheEnd = getX();
					firstPart = text.substring(0, i);
					endPart = text.substring(i);
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
				page.drawString( lines[i] ,getX(),(int) Math.round(getY() + fontSize * i * 1.1));
			}
		}catch(NullPointerException n) {
			text = "null";
			page.drawString(text, getX(), getY());
		}
		
	}
}
