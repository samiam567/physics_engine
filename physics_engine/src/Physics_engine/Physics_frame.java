package Physics_engine;

import java.awt.Color;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;

public class Physics_frame extends JFrame{
	public Container cp;
	
	public Rectangle boundingRectangle;

	
	public Physics_frame() {
		setVisible(true);
		setSize(Settings.width,Settings.height);
		setTitle("Physics-Engine V" + Settings.version + "           Programmed by Alec Pannunzio");
		cp = getContentPane();
		
		setBackground(Settings.frameColor);		
		cp.setBackground(Settings.frameColor);
		
		boundingRectangle = new Rectangle(0,0,getWidth(),getHeight());
		boundingRectangle.setRect(0,0,getWidth(),getHeight());

		
	}
	
	
	public void setColor(Color newColor) { //doesn't work
		setBackground(newColor);		
		cp.setBackground(newColor);
		
	}


	public void resizeObjects() {
		physics_runner.resize();
		boundingRectangle.setRect(0,0,getWidth(),getHeight());
		
	}
	
	public boolean checkIsInFrame(Physics_drawable current_object) { //this dont work
		 
		return boundingRectangle.intersects(current_object.getX(), current_object.getY(),(int) current_object.getXSize(), (int) current_object.getZSize());
	}
	
}
