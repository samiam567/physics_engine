package victorian_integral;

import java.awt.Font;
import java.awt.Graphics;
import Physics_engine.Settings;
import Physics_engine.Text;
import Physics_engine.object_draw;
import Physics_engine.physics_object;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Physics_engine.Settings;



public class Victorian_newspaper extends JFrame{
	private JLabel label;
	private JButton closeNewspaperButton, openVictorianTranslatorButton;
	public Container cp;
	public Font newsPaperFont = new Font("BrushScriptMT", Font.BOLD,(int) Math.round(.02 * Settings.height) );
	public String news = "~The Bendersberg Times~ \n \n NOTICE: Today is the first day that the new voting law takes effect! Register to vote now! -- \n"
			+ "\n Hello everyone! As the century is coming to a close this is our final news report for this year. In today’s article, we will be taking a look back at the major headlines of the 19th century in Europe.\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"- In 1832, the First Reform Bill was passed by parliament, which gave all men who had property that had a value of at least 10 pounds in rent the ability to vote. \r\n" + 
			"\r\n" + 
			"\r\n" + 
			"- In 1837, William IV dies, and Victoria comes to the power as the queen of England. \r\n" + 
			"\r\n" + 
			"\r\n" + 
			"- In 1838, a huge advancement was made in history when slavery was officially abolished throughout the British empire!\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"- In 1845, the potato famine in Ireland was occurring, and this event caused the forced emigration of thousands of Irish people. It is thought that about one million Irish have died due to this famine.\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"- In 1847, the Ten Hours Act was passed, and this act prevented children from working for more than 10 hours a day in a textile mill. \r\n" + 
			"\r\n" + 
			"\r\n" + 
			"- In 1867, the Second Reform Act was passed, and this Act made it so that all men in the working-class besides agricultural workers had the ability to vote. \r\n" + 
			"\r\n" + 
			"\r\n" + 
			"- In 1875, Alexander Graham Bell, the inventor of the telephone, made the very first telephone call in history. \r\n" + 
			"\r\n" + 
			"\r\n" + 
			"- In 1879, Thomas Edison invented the light bulb, an electrical appliance that is proving to be very useful and is finding its way in households at lightning speeds. \r\n" + 
			"\r\n" + 
			"\r\n" + 
			"Overall, we look forward to a bright and prosperous 20th century. We are hopeful that the 20th century will bring even more innovation and technological advancement than the 19th century did.  ";
	
	public Text text;
	private object_draw drawer;
	
	private Victorian_Translator trans;
	
	public Victorian_newspaper(object_draw drawer1) {
		drawer = drawer1;
		text = new Text(Settings.width/10,Settings.height/10,news,newsPaperFont);
		trans = new Victorian_Translator();
	}
	
	public void init() {
		
		//clearing out the frame
		for(physics_object pObject : object_draw.objects) {
			pObject.isVisible = false;
		}
		
		text.setName("news", 1);
		object_draw.objects.add(text);
		
	
		setTitle("NewsPaper Gui");
		
		setLocation(Settings.width-10, 20);
		cp = getContentPane();	
		cp.setBackground(Color.GRAY);	
		cp.setLayout(new FlowLayout());
		
		setVisible(true);
		
		closeNewspaperButton = new JButton("Close the Newspaper");
		closeNewspaperButton.addActionListener(new closeNewspaper());
		
		openVictorianTranslatorButton = new JButton("Open the victorian translator");
		openVictorianTranslatorButton.addActionListener(new openVictorianTranslator());
		
		cp.add(closeNewspaperButton);
		cp.add(openVictorianTranslatorButton);
		
		setSize(200,100);
	}
		
	private class openVictorianTranslator implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {	
			String translation = trans.Run();
			JOptionPane.showMessageDialog(null, translation,"Translation to Victorian English:", 1);
		}	
	}
	
	private class closeNewspaper implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {	
			Victorian_adventure.newsPaperOpen = false;
			dispose();
		}	
	}	
}

