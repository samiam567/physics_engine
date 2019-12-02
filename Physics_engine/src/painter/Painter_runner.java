package painter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;
import Physics_engine.FCPS_display;
import Physics_engine.FPS_display;
import Physics_engine.New_object_listeners;
import Physics_engine.Physics_frame;
import Physics_engine.PointSet;
import Physics_engine.border_bounce;
import Physics_engine.object_draw;
import Physics_engine.physicsRunner;
import Physics_engine.physics_object;

public class Painter_runner extends physicsRunner {
	
	public static New_object_listeners newObs;
	
	public static void main(String[] args) {
		frame = new Physics_frame();
		drawer = new object_draw(frame);
		run();
	}
	
	public static void setDrawer(object_draw drawer1) {
		frame =  new Physics_frame();
		drawer = drawer1;
		drawer.setFrame(frame);
	}
	
	public static void run() {

		frame.setVisible(true);
		
		resize(frame);
		
			

		newObs = new New_object_listeners(drawer);
		border_bounce borders = new border_bounce(drawer);
		
		drawer.add(borders);
		drawer.start();
		
		
		//key listener
		drawer.addKeyListener(new KeyListener() {
			   
			@Override
	         public void keyPressed(KeyEvent e) {
	        	  drawer.inactivity_timer = 0;      	     	

	        	
	        	  switch (e.getKeyCode()) {
	        	  	
	        	  	case(83): //S
	        	  		System.out.println("saving");
	        	  		savePainting();
	        	  	break;
	        	  		        	  	
	        	  	case(76): // /
	        	  		loadPainting();
	        	  	break;
	        	  		
	        	  }
	        	  
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				drawer.inactivity_timer = 0;		
			

			}
			@Override
			public void keyTyped(KeyEvent arg0) {
				
			}
			
	      });
	      
		waitForEnd();		
		
	}
	
	
	public static void savePainting() {
		if (newObs.createFreeForm) {
			newObs.createFreeForm = false;
  			((PointSet)newObs.objectBeingChanged).initialize();
			System.out.println("free-form force-Created for saving");
			((PointSet)newObs.objectBeingChanged).finish();
		}
		ObjectOutputStream saver;
		try {
			
			String fileName = JOptionPane.showInputDialog(frame,"What is the name of the file you want to save your painting into?");
			
			saver = new ObjectOutputStream(new FileOutputStream(fileName));

			for (physics_object save : drawer.getObjects()) {
				try {
					saver.writeObject(save);
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println(save.getObjectName() +  " has a problem with the saving process");
				}catch (Exception a) {
					a.printStackTrace();
					System.out.println(save.getObjectName() +  " has an unexpected problem with the saving process");
				}
			}
			saver.close();
			
			System.out.println("Save Complete");
			
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
	}
	
	public static void loadPainting() {
		drawer.clearObjects();
		
		try {
			System.out.println("Loading from save_file...");
			
			String fileName = JOptionPane.showInputDialog(frame,"What is the name of the file the painting is stored in?");
			
			ObjectInputStream loader = new ObjectInputStream(new FileInputStream(fileName));
			
			physics_object readOb = (physics_object) loader.readObject();
			
			while(readOb != null) {
				drawer.add(readOb);
				readOb = (physics_object) loader.readObject();
			}
			loader.close();
			
			System.out.println("load successful");
			
		}catch(InvalidClassException e) {
			System.out.println("Corrupted Save_file"); 
		}catch(EOFException e) {
			System.out.println("Corrupted Save_file");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
