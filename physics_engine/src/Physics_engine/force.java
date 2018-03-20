package Physics_engine;

public class force extends Vector{
	private double frame;
	private double time;
	private String units;
	public boolean isApplied = false, delete = false;
	private physics_object object;
	
	
	
	public force(object_draw drawer1,physics_object object1, double xComponent1, double yComponent1, double zComponent1, double time1,String units1,double frame1) {
		super(drawer1, xComponent1, yComponent1,zComponent1);
		xComponent = xComponent1;
		yComponent = yComponent1;
		zComponent = zComponent1;
		time = time1;
		object = object1;
		frame = frame1;
		units = units1;
	}
	
	public force(object_draw drawer1,physics_object object1, double r, double theta, double zComponent1, double time1,String units1,double frame1, boolean isPolar) { //old angle system
		super(drawer1, r, theta, zComponent1, isPolar);
		assert isPolar == true;
		time = time1;
		object = object1;
		frame = frame1;
		units = units1;
	}
	
	
	
	public void apply() {
		if (! isApplied) {
			
			///Check this line \/
			if ((Math.abs(drawer.current_frame - (int)drawer.current_frame ) < 0.00001) || (frame == -1)) {
				System.out.println("applied");
				((massive) object).applyComponentImpulse(xComponent, yComponent, zComponent,time,units);
				isApplied = true;
				delete = true;
			}
		}
		
	}
	
	
}
