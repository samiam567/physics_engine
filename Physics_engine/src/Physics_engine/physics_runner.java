package Physics_engine;


import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ConcurrentModificationException;
import javax.swing.JFrame;

public class physics_runner {
	
	private static Physics_frame frame = new Physics_frame();
	private static boolean mouseIsPressed = false;
	private static int mouseStartX;
	private static int mouseStartY;
	
	private static object_draw drawer;
	
	public static void main(String[] args) {
	
		drawer = new object_draw(frame);
		
		border_bounce boundries = new border_bounce(drawer);

		
	
		
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
			
			/*
			if (! mouseIsPressed) {
				mouseStartX = arg0.getX();
				mouseStartY = arg0.getY();
				
			}
			System.out.println(mouseStartX + "," + mouseStartY);
			System.out.println(arg0.getX() + "," + arg0.getY());
			int xRotation = mouseStartX - arg0.getX();
			int yRotation = mouseStartY - arg0.getY();
			*/
			
			int xRotation = arg0.getX();
			int yRotation = arg0.getY();
		
		}


		public void mouseReleased(MouseEvent arg0) {
			drawer.inactivity_timer = 0;
			mouseIsPressed = false;
		}
		};
		
		
		//==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+
		
		
		drawer.addMouseListener(mouse);
		
		
		drawer.add(boundries);
		
     	Square square1 = new Square(drawer,400,400,0,100,1);
		square1.setName("square1", 1);
		square1.setPos(400, 200, 0);
		square1.setSpeed(1, 0, 0);
//		square1.setAngularVelocity(0.01, 0, 0);
	
		
		
		Square square2 = new Square(drawer,400,400,0,100,1);
		square2.setName("square2", 1);
		square2.setPos(600, 200, 0);
		square2.setAngularVelocity(0, 0, 0.01);
		square2.setSpeed(0.5, 0, 0);
		
		Triangle tri1 = new Triangle(drawer,400,8000,0,50,100,1);
		tri1.setName("tri1", 1);
		tri1.setPos(600, 400, 0);
		tri1.setAngularVelocity(0, 0, 0.1);
		tri1.setSpeed(-1, 0, 0);
		drawer.add(tri1);
		
		drawer.add(square2);
		
		rectangle rect1 = new rectangle(drawer, 400, 400, 0, 60, 80, 1);
		drawer.add(rect1);
	
		Vector vec1 = new Vector(drawer,40,50,50,0,"thetaZX");
		vec1.setPos(300, 300, 0);
		vec1.setName("vec1", 1);
		drawer.add(vec1);
		
		
		

		
//		drawer.objects.add(boundries);

		drawer.add(vec1);
		drawer.add(square1);

		
		
		
		drawer.start();

	//	frame.dispose();
	}

	public static void resize() {
		//resize stuff
		System.out.println("Resizing");
	}

}
