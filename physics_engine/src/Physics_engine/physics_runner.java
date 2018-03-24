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

	
		
		
		New_object_listeners listener = new New_object_listeners(drawer);

		Square square1 = new Square(drawer,500,500,0,100,1);
		square1.setRotation(Math.PI/2,0,0);
		square1.setAngularVelocity(0, 0, 1);
		drawer.add(square1);
		
		
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
