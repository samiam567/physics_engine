package victorian_integral;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;

import Physics_engine.*;

public class VictorianHouse extends physics_object {
	public Square base;
	public Triangle roof;
	
	public VictorianHouse(double x, double y, double z, double size) {
		mass = Math.pow(size,2);
		setPos(x,y,z);
		setSize(size,size,0);
		setRotation(0,0,0);
		
		base = new Square(x,y,z,size,mass);
		base.isFilled = true;
		base.setParentObject(this);
		
		
		roof = new Triangle(x + size/2, y - size/2 ,0, size, size/2,1);
		roof.isFilled = true;
		roof.setParentObject(this);
		
		
		setColor(Color.orange);
		object_draw.objects.add(this);
		
		double r = Math.sqrt(Math.pow(xSize,2) + Math.pow(ySize,2) + Math.pow(zSize,2))/2;
		for (point cP : points) {
			cP.setR(r);
		}		
		calculateCenter();
//		calculatePointValues();  //THIS METHOD IS EVIL SPAWN OF SATAN! |:<	
		updatePointOfRotation();
		setPos(x,y,z);
		
	}
	
	public void paint(Graphics page) {
		base.paint(page);
		roof.paint(page);
	}

}
