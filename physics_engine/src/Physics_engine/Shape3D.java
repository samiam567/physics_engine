package Physics_engine;

public class Shape3D extends Physics_3DShape {
	/**
	 * 
	 */
	private static final long serialVersionUID = 229461493552349818L;

	public enum shape3dtype {sphere,cylinder,thing1,torus};
	private shape3dtype shape;
	public Shape3D(object_draw drawer1,shape3dtype shape1, double x1, double y1, double z1, double xSize1, double ySize1, double zSize1, double delta1) {
		super(drawer1);
		shape = shape1;
		construct(x1, y1, z1, xSize1, ySize1, zSize1,delta1);

	}

	protected double[] equation(double t, double q) {
		double x1=0,y1=0,z1=0;
		
		if (shape == shape3dtype.sphere) {
			x1 = xSize * Math.cos(t) * Math.sin(q);
			y1 = ySize * Math.sin(t) * Math.sin(q);
			z1 = zSize * Math.cos(q);
		}else if (shape == shape3dtype.cylinder) {
			x1 = xSize * Math.sin(t);
			y1 = ySize * Math.cos(t);
			z1 = zSize * q;
		}else if (shape == shape3dtype.thing1){ 
			x1 = xSize * Math.sin(t);
			y1 = ySize * ( (q*q)/2 - 1);
			z1 = zSize * (t*q/2);
		}else if (shape == shape3dtype.torus){
			x1 = xSize * (10 + 2*Math.cos(Math.PI * t)) * Math.sin(Math.PI * q);
			y1 = ySize * (10 + 2*Math.cos(Math.PI * t)) * Math.cos(Math.PI * q);
			z1 = zSize * 2 * Math.sin(Math.PI * t);
		}else {
			Exception e = new Exception("Logic error for shapeType");
			e.printStackTrace();
		}
	
		return  new double[] {x1,y1,z1};
	}

}
