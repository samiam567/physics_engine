package Physics_engine;

import java.awt.Color;
import java.awt.Graphics;

public interface drawable { //the ability to be drawn on the screen
	int x=0,y=0,z=0;
	double xReal=0,yReal=0,zReal=0;
	boolean isVisible = true,isFilled = false;
	Color color = Color.BLACK;
	public String drawMethod = "paint";
	public String name = "unnamed";
	
	public void paint(Graphics Page);
	
	public double getXReal();
	public double getYReal();
	public double getZReal();
	
	public int getX();
	public int getY();
	public int getZ();
	
	public Color getColor();
	public void setColor(Color color1);
	
	public void setName(String new_name, int i);
	public String getObjectName();

	
	
}
