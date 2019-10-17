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
		setSize((int) (Settings.width * Settings.pixelConversion), (int) (Settings.height* Settings.pixelConversion));
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
	
	public boolean checkIsInFrame(pointed current_object) { //this dont work
		//return boundingRectangle.intersects((current_object.getPolyXY().getBounds()));

		if ( (current_object.getPoints()[0].getXReal() < getWidth()) && (current_object.getPoints()[0].getXReal() > 0) && (current_object.getPoints()[0].getYReal() < getHeight()) && (current_object.getPoints()[0].getYReal() > 0) || ( (current_object.getPoints()[(current_object.getPoints().length - 1)/2].getXReal() < getWidth()) && (current_object.getPoints()[(current_object.getPoints().length - 1)/2].getXReal() > 0) && (current_object.getPoints()[(current_object.getPoints().length - 1)/2].getYReal() < getHeight()) && (current_object.getPoints()[(current_object.getPoints().length - 1)/2].getYReal() > 0))) {
				return true;
		}
		
		return false;
		
	}
	
}
