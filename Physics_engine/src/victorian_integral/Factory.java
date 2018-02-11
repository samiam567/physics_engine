package victorian_integral;
import java.awt.Color;
import java.awt.Graphics;
import Physics_engine.*;

public class Factory extends physics_object {
	public rectangle base;
	public rectangle chimney1;
	public rectangle chimney2;
	public rectangle chimney3;
	public Factory(double x, double y, double z, double size1, double size2)
	{
		mass = Math.pow(size1, 2);
		setPos(x, y, z);
		setSize(size1, size2, 0);
		setRotation(0,0,0);
		
		base = new rectangle(x, y, z, size1, size2, mass);
		base.isFilled = true;
		base.setParentObject(this);
		

		
	}
}
