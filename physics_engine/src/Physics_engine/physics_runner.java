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


		vec1 = new Vector(drawer,40,50,50,0,"thetaZX");
		vec1.setPos(300, 300, 0);
		vec1.setName("vec1", 1);
		drawer.add(vec1);
	
		Physics_frame secondaryFrame = new Physics_frame();
		object_draw drawer2 = new object_draw(secondaryFrame);
		
		Vec = new Vector3D(drawer2,50,3,Math.PI/4);
		Vec.setPos(200, 200, 0);
		drawer2.add(Vec);
		
		
		
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
			drawer.remove(vec1);
			
			
			vec1 = new Vector(drawer,40,0,0,vec1.getThetaZY() + 0.1,"thetaZY");
			vec1.setPos(300, 300, 0);
			
			
			drawer.add(vec1);
			
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
		
     	Square square1 = new Square(drawer,400,600,0,100,10);
		square1.setName("square1", 1);
		square1.setPos(400, 200, 0);
		square1.setSpeed(1, 0, 2);
		square1.setAngularVelocity(0, 0, 0.01);
		square1.isTangible = true;
		drawer.add(square1);
		
		Square square2 = new Square(drawer,400,400,0,100,10);
		square2.setName("square2", 1);
		square2.setParentObject(square1);
		square2.setPointOfRotationPlace(pointOfRotationPlaces.parentsPlace);
		square2.setPos(600, 200, 0);
		square2.setAngularVelocity(0, 0, 0.01);
		square2.setSpeed(5, 3, 0);
		square2.calculatePointValues();
		square2.isTangible = false;
		drawer.add(square2);
		
		Triangle tri1 = new Triangle(drawer,400,800,0,50,100,10);
		tri1.setName("tri1", 1);
		tri1.setPos(600, 400, 0);
		tri1.setAngularVelocity(0, 0, 0.1);
		tri1.setSpeed(-1, 0, 0);
		drawer.add(tri1);
		
		
		
		rectangle rect1 = new rectangle(drawer, 400, 400, 0, 10, 80, 10);
		rect1.setName("rect1", 1);
		drawer.add(rect1);
	
		
		
		

		

		

		
		
		resize();
		
		drawer.start();

	//	frame.dispose();
	}

	public static void resize() {
		//resize stuff
		System.out.println("Resizing");
		try {
			boundries.resize();
		}catch(NullPointerException n) {}
	}

}
