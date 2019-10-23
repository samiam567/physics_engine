package Physics_engine;

import java.awt.Color;
import java.awt.Graphics;

import Physics_engine.physics_object.object_types;

public interface drawable extends physics_engine_compatible { //the ability to be drawn on the screen
	public object_types object_type = object_types.drawable;
	
	/*vars needed
	int x=0,y=0,z=0;
	double xReal=0,yReal=0,zReal=0,centerX=0,centerY=0,centerZ=0;
	boolean isVisible = true,isFilled = false;
	Color color = Color.BLACK;
	public String drawMethod = "paint";
	public String name = "unnamed";
	*/
	
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

	public boolean getIsVisible();
	
	public boolean getIsAlwaysVisible(); //paints the object even if it's not in frame

	public String getDrawMethod();

	public int[] getPointRenderOrder();
	
	public void setCenter(double centerX1,double centerY1, double centerZ1);

	public boolean hasParentObject();

	public double getXSizeAppearance();
	public double getYSizeAppearance();
	public double getZSizeAppearance();

	public double getXSize();
	public double getYSize();
	public double getZSize();
	
	public double getCenterX();
	public double getCenterY();
	public double getCenterZ();
	
	
}
