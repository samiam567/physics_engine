package pole_position;

import Physics_engine.Physics_3DPolygon;
import Physics_engine.PolarObject;
import Physics_engine.object_draw;
import calculator.Settings;

public class Car extends PolarObject {
	
	private boolean playerControlled;
	
	public Car(object_draw drawer,double cenX, double cenY,double cenZ, boolean PlayerControlled) {
		super(drawer,cenX,cenY, cenZ,600,"thing1",Math.PI/4,1.5715*Math.PI);
		setPos(cenX,cenY,cenZ);
		setMass(100);
		playerControlled = PlayerControlled;
	}
}
