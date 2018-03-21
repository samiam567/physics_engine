package Physics_engine;


import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ConcurrentModificationException;
import javax.swing.JFrame;

import Physics_engine.Physics_engine_toolbox.pointOfRotationPlaces;

public class physics_runner {
	
	private static Physics_frame frame = new Physics_frame();
	private static boolean mouseIsPressed = false;
	private static int mouseStartX;
	private static int mouseStartY;
	
	private static border_bounce boundries;

	public static Vector vec1;
	
	public static Vector3D Vec;
	
	private static object_draw drawer;
	
	public static void main(String[] args) {
	
		drawer = new object_draw(frame);
		
		boundries = new border_bounce(drawer);
		boundries.setName("boundries", 1);
		drawer.add(boundries);


		FPS_display fps = new FPS_display(drawer,30,30);
		drawer.add(fps);

		FCPS_display fcps = new FCPS_display(drawer,30,50);
		drawer.add(fcps);
		
		
		
		
		
		//mouseListener +==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+
		MouseAdapter mouse =  new MouseAdapter() {

		public void mouseClicked(MouseEvent arg0) {
			drawer.inactivity_timer = 0;
			
			
			
		}

		public void mouseEntered(MouseEvent arg0) {
			drawer.inactivity_timer = 0;
			
		}

		
		public void mouseExited(MouseEvent arg0) {
			drawer.inactivity_timer = 0;
		}

		
		public void mousePressed(MouseEvent arg0) {
			drawer.inactivity_timer = 0;
			
		
		}


		public void mouseReleased(MouseEvent arg0) {
			drawer.inactivity_timer = 0;
			mouseIsPressed = false;
		}
		};
		
		
		//==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+
		
		
		drawer.addMouseListener(mouse);

		PolarObject pol1 = new PolarObject(drawer,800,500,0,100,"thing1");
		pol1.setRotation(0, 1, 0);
//		pol1.setAngularVelocity(0.1, 0.05, 0.1);
		pol1.isFilled = false;
		pol1.isVisible = true;
		pol1.setName("thing1", 0);
//		drawer.add(pol1);
		
		
		
		
		Sphere sphere = new Sphere(drawer,200,200,200,100,10,Math.PI/30);
		sphere.setRotation(0, 0.5, 0);
		sphere.setAngularVelocity(0.1, 0.05, 0.1);
		sphere.isFilled = false;
		sphere.isVisible = true;
		sphere.setName("sphere", 1);
		drawer.add(sphere);
		
		Box box1 = new Box(drawer,400,400,400,100,1);
		box1.setRotation(0, 1, 0);
		box1.setAngularVelocity(0.2, 0.1, 0.05);
		box1.setName("box1", 1);
		drawer.add(box1);
		
		
		Triangle tri1 = new Triangle(drawer,700,200,0,100,100,1);
		
		tri1.setRotation(0, 1, 0);
		tri1.setAngularVelocity(0.1, 0.3, 0.2);
		tri1.setName("tri1",1);
		
		New_object_listeners listener = new New_object_listeners(drawer);


		drawer.add(tri1);
		
		
		
		resize();
		

		drawer.start();
		
		
	
		
	}

	public static void resize() {
		//resize stuff
		System.out.println("Resizing");
	
		try {
			boundries.resize();
		}catch(NullPointerException n) {}
	}

}
