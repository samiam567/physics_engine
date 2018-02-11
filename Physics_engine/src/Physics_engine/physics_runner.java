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
	
	public static void main(String[] args) {
	
		border_bounce boundries = new border_bounce();

		object_draw drawer = new object_draw(frame);
	
		
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
		
		
	
		
     	Square square1 = new Square(400,400,0,100,1);
		square1.setName("square1", 1);
		square1.setPos(400, 200, 0);
	//	square1.setAngularVelocity(0.01, 0, 0);
		
		
		Square square2 = new Square(400,400,0,100,1);
		square2.setName("square2", 1);
		square2.setPos(600, 200, 0);
		square2.setAngularVelocity(0, 0, 0.01);
		
		Triangle tri1 = new Triangle(400,8000,0,50,100,1);
		tri1.setName("tri1", 1);
		tri1.setPos(600, 400, 0);
		tri1.setAngularVelocity(0, 0, 0);
		object_draw.objects.add(tri1);
		
		object_draw.objects.add(square2);
	
		Vector vec1 = new Vector(40,50,50,0,"thetaZX");
		vec1.setPos(300, 300, 0);
		object_draw.objects.add(vec1);
		
		
		

		
//		object_draw.objects.add(boundries);

		object_draw.objects.add(vec1);
		object_draw.objects.add(square1);

		
		
		
		drawer.start();

	//	frame.dispose();
	}

}
