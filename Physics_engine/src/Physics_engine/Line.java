package Physics_engine;

public class Line extends Vector{ 
	
	public Line(double xReal,double yReal,double zReal,double x2,double y2, double z2) {
		super(new point(xReal,yReal,zReal),new point(x2,y2,z2));
		
		setPos(xReal,yReal,zReal);
		updatePoints();
		updatePos();
	
	}
	

}
