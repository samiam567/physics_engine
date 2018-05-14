package Physics_engine;

public abstract class physicsRunner {
	
	public static object_draw drawer;
	public static Physics_frame frame;
	public static border_bounce boundries;
	
	
	public static void setDrawer(object_draw drawer1) {
		drawer = drawer1;
		frame = drawer.frame;
	}
	
	public static void run() {
		Exception e = new Exception("physicsRunner.run() must be overriden by the runner class");
		e.printStackTrace();
	}
	
	
	public static void waitForEnd() {
		while (frame.isShowing()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
	
	public static void resize() {
		//resize stuff
		System.out.println("Resizing");
		
		try {
			drawer.resize();
			boundries.resize();
		}catch(NullPointerException n) {}
	}
}
