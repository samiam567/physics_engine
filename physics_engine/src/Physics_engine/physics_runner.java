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
		
		
		drawer.add(boundries);
		
		Square square1 = new Square(drawer,200,200,0,100,1);
		square1.setAngularVelocity(0,0.1,0);
		drawer.add(square1);
		
		
		
		Box box1 = new Box(drawer,400,400,400,100,1);
		box1.setRotation(0, 1, 0);
		box1.setAngularVelocity(0.01, 0, 0);
		
		drawer.add(box1);
		
		
		Triangle tri1 = new Triangle(drawer,500,500,500,100,100,1);
		tri1.setRotation(0, 1, 0);
		tri1.setAngularVelocity(0.01, 0.03, 0.02);
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
