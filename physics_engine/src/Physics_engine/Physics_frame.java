package Physics_engine;

import java.awt.Color;
import java.awt.Container;
import java.awt.Rectangle;

import javax.swing.JFrame;

public class Physics_frame extends JFrame{
	public Container cp;
	public object_draw drawer;
	public Rectangle boundingRectangle;

	private static int frameCount;
	
	public Physics_frame() {
		frameCount++;
		setSize(Settings.width,Settings.height);
		setTitle("Physics-Engine V" + Settings.version + "           Programmed by Alec Pannunzio ID:" + frameCount);
		cp = getContentPane();
		
		setBackground(Settings.frameColor);		
		cp.setBackground(Settings.frameColor);
		
		boundingRectangle = new Rectangle(0,0,getWidth(),getHeight());
		boundingRectangle.setRect(0,0,getWidth(),getHeight());

		setVisible(true);
	}
	
	
	public void setColor(Color newColor) { 
		if (newColor != getBackground()) {
			setBackground(newColor);		
			cp.setBackground(newColor);
			drawer.setFrame(this);
		}
	}


	public void resizeObjects() {
		physics_runner.resize();
		boundingRectangle.setRect(0,0,getWidth(),getHeight());
		
	}
	
	public boolean checkIsInFrame(Physics_drawable current_object) { //this dont work
		 
		return boundingRectangle.intersects(current_object.getX(), current_object.getY(),(int) current_object.getXSize(), (int) current_object.getZSize());
	}
	
}
