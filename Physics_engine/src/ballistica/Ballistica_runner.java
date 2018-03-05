package ballistica;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Physics_engine.Settings;
import Physics_engine.SpeedTimer;
import Physics_engine.Square;
import Physics_engine.Vector;
import Physics_engine.border_bounce;
import Physics_engine.object_draw;
import Physics_engine.point;
import Physics_engine.rectangle;
import jetpack_joyride.JetPack_fire;

public class Ballistica_runner{
	
	public static String version = "1.0.2";
	
	public static Ballistic_frame frame = new Ballistic_frame();
	public static Ballistic_frame map = new Ballistic_frame();
	
	public static object_draw mapDrawer = new object_draw(map);
	public static object_draw frameDrawer = new object_draw(frame);
	
	public static border_bounce borders;

	public static Ballistic_ship ship;
	public static double shipSpeed = 10;
	
	public static SpeedTimer keyStrokeTimer;
	
	public static void main(String[] args) {
		init();
	}
	
	public static void init() {
		ship = new Ballistic_ship(frameDrawer,200,200,50);
		ship.isFilled = true;
		ship.setColor(Color.LIGHT_GRAY);
		ship.setName("ballistica_battleship", 1);
		frameDrawer.add(ship);
		
		borders = new border_bounce(frameDrawer);
		frameDrawer.add(borders);
		

		
		frameDrawer.start();
		
		
		//mouseListener +==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+
		MouseAdapter mouse =  new MouseAdapter() {

		public void mouseClicked(MouseEvent arg0) { //please make this more efficient Demitri.. :|
			frameDrawer.inactivity_timer = 0;
			
			int mouseX = arg0.getX();
			int mouseY = arg0.getY();
			
			Vector mouseAimVec = new Vector(frameDrawer,ship.getCenter(),new point(frameDrawer,mouseX,mouseY,0));
			Ballistic_bullet bullet = new Ballistic_bullet(frameDrawer,ship);
			bullet.setSpeed(mouseAimVec.getXComponent()/mouseAimVec.getR(), mouseAimVec.getYComponent()/mouseAimVec.getR(), 0);
			
		}
		
		public void mousePressed(MouseEvent arg0) {
			frameDrawer.inactivity_timer = 0;
		}


		public void mouseReleased(MouseEvent arg0) {
			frameDrawer.inactivity_timer = 0;
	
		}};
		
		
	//==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+
		
	frameDrawer.addMouseListener(mouse);

	//key listener
	frameDrawer.addKeyListener(new KeyListener() {
		   
         @Override
         public void keyPressed(KeyEvent e) {
        	  frameDrawer.inactivity_timer = 0;      	     	
        	  
        	  frameDrawer.remove(keyStrokeTimer);
        	  
        	  switch (e.getKeyCode()) {
        	  	case(87):
        	  		System.out.println("w");
        	  		ship.setSpeed(0, -shipSpeed, 0);
        	  	break;
        	  	
        	  	case(65):
        	  		System.out.println("a");
        	  		ship.setSpeed(-shipSpeed, 0, 0);
        	  	break;
        	  	
        	  	case(83):
        	  		System.out.println("s");
        	  		ship.setSpeed(0, shipSpeed, 0);
        	  	break;
        	  	
        	  	case(68):
        	  		System.out.println("d");
        	  		ship.setSpeed(shipSpeed, 0, 0);
        	  	break;
        	  	
        	  	case(32):
        	  		System.out.println("space");
        	  		Ballistic_bullet bullet = new Ballistic_bullet(frameDrawer,ship);
        	  	break;	  
        	  }
          }

		@Override
		public void keyReleased(KeyEvent arg0) {
			frameDrawer.inactivity_timer = 0;
			
			keyStrokeTimer = new SpeedTimer(frameDrawer,0.2,"seconds",0,0,0,ship);
			frameDrawer.add(keyStrokeTimer);
		}
		
		@Override
		public void keyTyped(KeyEvent arg0) {
			frameDrawer.inactivity_timer = 0;
			
		}
		
      });
		
		
	}
	
	public static void resize() {
		double diagonal = Math.sqrt(Math.pow(Settings.width, 2) + Math.pow(Settings.height, 2));
	}
}
