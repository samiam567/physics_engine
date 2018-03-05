package tanks;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Physics_engine.Map_object_draw;
import Physics_engine.Settings;
import Physics_engine.SpeedTimer;
import Physics_engine.Vector;
import Physics_engine.border_bounce;
import Physics_engine.object_draw;
import Physics_engine.point;
import ballistica.Ballistic_bullet;
import ballistica.Ballistic_frame;

public class Tanks_runner {
	
	public static String version = "1.0.3";
	
	public static Tanks_frame frame = new Tanks_frame();
	public static Tanks_frame map = new Tanks_frame();
	
	
	public static object_draw frameDrawer = new object_draw(frame);
	public static Map_object_draw mapDrawer;
	
	
	public static border_bounce borders;

	public static Tank tank;
	public static double tankSpeed = 10;
	
	public static SpeedTimer keyStrokeTimer;
	
	public static void main(String[] args) {
		init();
	}
	
	public static void init() {
		frame.setTitle("Tanks V" + version);
		
		tank = new Tank(frameDrawer,200,200,50);
		tank.isFilled = true;
		tank.setColor(Color.green);
		tank.setName("ballistica_battletank", 1);
		frameDrawer.add(tank);
		
		borders = new border_bounce(frameDrawer);
		frameDrawer.add(borders);
		
		
		mapDrawer = new Map_object_draw(map,frameDrawer,tank,200,200);
		

		mapDrawer.start();
		frameDrawer.start();
		
		
		//mouseListener +==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+
		MouseAdapter mouse =  new MouseAdapter() {

		public void mouseClicked(MouseEvent arg0) { //please make this more efficient Demitri.. :|
			frameDrawer.inactivity_timer = 0;
			
			int mouseX = arg0.getX();
			int mouseY = arg0.getY();
			
			Vector mouseAimVec = new Vector(frameDrawer,tank.getCenter(),new point(frameDrawer,mouseX,mouseY,0));
			Tank_shell bullet = new Tank_shell(frameDrawer,tank);
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
        	  		tank.setSpeed(0, -tankSpeed, 0);
        	  	break;
        	  	
        	  	case(65):
        	  		System.out.println("a");
        	  		tank.setSpeed(-tankSpeed, 0, 0);
        	  	break;
        	  	
        	  	case(83):
        	  		System.out.println("s");
        	  		tank.setSpeed(0, tankSpeed, 0);
        	  	break;
        	  	
        	  	case(68):
        	  		System.out.println("d");
        	  		tank.setSpeed(tankSpeed, 0, 0);
        	  	break;
        	  	
        	  	case(32):
        	  		System.out.println("space");
        	  	Tank_shell bullet = new Tank_shell(frameDrawer,tank);
        	  	break;	  
        	  }
          }

		@Override
		public void keyReleased(KeyEvent arg0) {
			frameDrawer.inactivity_timer = 0;
			
			keyStrokeTimer = new SpeedTimer(frameDrawer,0.2,"seconds",0,0,0,tank);
			frameDrawer.add(keyStrokeTimer);
		}
		
		@Override
		public void keyTyped(KeyEvent arg0) {
			frameDrawer.inactivity_timer = 0;
			
		}
		
      });
	
	while(true) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
		
		
	}
	
	public static void resize() {
		double diagonal = Math.sqrt(Math.pow(Settings.width, 2) + Math.pow(Settings.height, 2));
	}
}
