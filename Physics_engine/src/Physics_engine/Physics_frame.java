package Physics_engine;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;

public class Physics_frame extends JFrame{
	public Container cp;
	
	public Physics_frame() {
		setVisible(true);
		setSize(Settings.width,Settings.height);
		setTitle("Physics-Engine V" + Settings.version + "           Programmed by Alec Pannunzio");
		cp = getContentPane();
		
		setBackground(Settings.frameColor);		
		cp.setBackground(Settings.frameColor);
	}
	
	
	public void setColor(Color newColor) { //doesn't work
		setBackground(newColor);		
		cp.setBackground(newColor);
	}
	
}
