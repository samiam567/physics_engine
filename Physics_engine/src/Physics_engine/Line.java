package Physics_engine;

public class Line extends Vector{ //pretty much just a more user-friendly way of creating a vector
	
	public Line(object_draw drawer1, double xReal,double yReal,double zReal,double x2,double y2, double z2) {
		super(drawer1,new point(drawer1,xReal,yReal,zReal),new point(drawer1,x2,y2,z2));	
		setPos(xReal,yReal,zReal);
		updatePoints();
		updatePos();
	}
}
