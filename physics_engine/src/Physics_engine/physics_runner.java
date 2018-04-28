package Physics_engine;


import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ConcurrentModificationException;
import javax.swing.JFrame;

import Physics_engine.Physics_engine_toolbox.pointOfRotationPlaces;
import circle_tessellation.Tessellation_runner;

public class physics_runner {
	
	private static Physics_frame frame = new Physics_frame(), frame2 = new Physics_frame();
	private static boolean mouseIsPressed = false;
	private static int mouseStartX;
	private static int mouseStartY;
	
	private static border_bounce boundries;

	public static Vector vec1;
	
	public static Vector3D Vec;
	
	static object_draw drawer,drawer2;
	
	public static void main(String[] args) {
		
		
	
		drawer = new object_draw(frame);
		drawer2 = new object_draw(frame2);
	
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
			mouseIsPressed = true;
		
		}


		public void mouseReleased(MouseEvent arg0) {
			drawer.inactivity_timer = 0;
			mouseIsPressed = false;
		}
		};
		
		
		//==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+
		
		
		drawer.addMouseListener(mouse);
		
		
		
		resize();
		
		int size = 200;

		for (int y = 0; y <= ((Settings.height - size)/size); y ++) {
			for (int x = 0; x <= ((Settings.width - size)/size); x++) {
				drawer.add(new PolarObject(drawer,x * size + 0.5 * size * (1+Math.sin(Math.PI * 0.5 * y)) ,y * size + size/2,0,size/2,"circle"));
			}
		}
	
		
		
		for (massive pO : drawer.getTangibles()) {
			((pointed) pO).setAngularVelocity(Math.random(),Math.random(),Math.random());
		}
		
		
		drawer2.add(new PolarObject(drawer2,400,400,0,200,"circle"));
		
		
		drawer.start();
		
		drawer2.start();
		
		
		Tessellation_runner.drawer = drawer2;
		Tessellation_runner.drawTessellation("circle", 500, 3, 0, 0, 500, 500);
		
	
		
		
		
		while (frame.isShowing()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.exit(1);
		
		
	}

	public static void resize() {
		//resize stuff
		System.out.println("Resizing");
	
		try {
			boundries.resize();
		}catch(NullPointerException n) {}
	}

}
