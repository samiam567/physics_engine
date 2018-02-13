package Physics_engine;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;

public class object_draw extends Canvas {
	
	private ArrayList<physics_object> objects = new ArrayList<physics_object>();
	
	public ArrayList<Physics_drawable> drawables = new ArrayList<Physics_drawable>();
	
	public ArrayList<force> scheduled_forces = new ArrayList<force>(); //list of forces for the maintenance bot to apply
	
	public double current_frame = 0; //what frame we are on
	private long frameStartTime;
	private long frameEndTime;
	private long wait_time;
	
	public int inactivity_timer = 0;
	
	public Physics_frame frame;

	public object_draw_thread threader;

	public object_draw(Physics_frame frame1) {
		frame = frame1;
		frame.getContentPane().add(this);
		threader = new object_draw_thread(this);
	}
	
	public void boot() {
		threader = new object_draw_thread(this);
	}
	
	public void start() {
		threader.start();
	}
	
	public void end() {
		threader.state = 0;
	}
	
	public void stop() {
		threader.state = 0;
	}
	
	public void pause() {
		threader.state = 2;
	}
	
	public void resume() {
		threader.state = 1;
	}
	
	public void add(physics_object newOb) {
		objects.add(newOb);
		
		try {
			drawables.add((Physics_drawable) newOb);
		}catch(ClassCastException c) {
			//ob is not drawable
		}
	}
	
	private void updateObjects(double frames) {
		for (physics_object current_object : objects) {				
			Physics_engine_toolbox.Update(current_object,frames);
		}
		
		if ( Math.abs(current_frame - (int)current_frame ) < 0.00001) {
			for (force current_force : scheduled_forces) { //applying the scheduled forces
				current_force.apply();
			}
		}
	}
	
	public void sleepThread(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void Wait(double time) { // Wait(1000) evaluates to about 20 seconds
		double Time = time * 10000000;
		double t = 0;
		while (t < Time)
			t++;
	}
	
	public static void WaitNanotime(long time) { //more accurate wait method for nanotime
		long StartTime = System.nanoTime();
		long CurrentTime;	
		do {
			CurrentTime = System.nanoTime();
		}while ( (CurrentTime-StartTime) <= time);
	}
	

	
	public void doFrame() {
		frameStartTime = System.nanoTime();
		current_frame++;
		updateObjects(1);
		repaint();
		frameEndTime = System.nanoTime();
		wait_time = Settings.frameTime - (frameEndTime - frameStartTime);
		WaitNanotime(wait_time);
	
	}
	
	public void doFrame(double frames) {
	
		frameStartTime = System.nanoTime();
		current_frame += frames;
		updateObjects(frames);
		
		if ( Math.abs(current_frame - (int)current_frame ) < Settings.frameStep) { 
	
			repaint(); 
			frameEndTime = System.nanoTime();
			wait_time = Settings.frameTime - (frameEndTime - frameStartTime);
			
			if (wait_time < 0) {
				Exception ex = new Exception("Wait time is less than 0! wait_time: " + wait_time);
				ex.printStackTrace();
			}
			
			WaitNanotime(wait_time);
		}
	}
	
	public void doThreadedFrame(double frames) {
		
		frameStartTime = System.nanoTime();
		current_frame += frames;
		updateObjects(frames);
		
		if ( Math.abs(current_frame - (int) current_frame ) < Settings.frameStep) { 
	
			repaint(); 
			frameEndTime = System.nanoTime();
			wait_time = Settings.frameTime - (frameEndTime - frameStartTime);
			
			if (wait_time < 0) {
				Exception ex = new Exception("Wait time is less than 0! wait_time: " + wait_time);
				ex.printStackTrace();
				wait_time = 0;
			}
			
			sleepThread(wait_time/1000000);
		}
	}
	
	public void doFrame(String key) {
		assert key == "step";
		for (double i = 0; i < 1; i+= Settings.frameStep) {
			doFrame(Settings.frameStep);
		}
	}
	
	public void paint(Graphics page)  {
		
		//sorting objects by z distance ----------------------------------
		Collections.sort( drawables, new Comparator<Physics_drawable>() {
	     
	        public int compare(Physics_drawable o1, Physics_drawable o2) {
	            return Double.compare(o2.getZReal(), o1.getZReal());
	        }

	
	    });
		//----------------------------------------------------------------
		
		
		
		
		
		try {
			for (drawable current_object : drawables) {
				
				if (current_object.getIsVisible()) {
					
					page.setColor(current_object.getColor());
					
					
					
					switch (current_object.getDrawMethod()) {
						case("ListedPointAlgorithm"):
							try {
								current_object = (pointed) current_object;
								
								int pointKey;
								int next_pointKey;
								point current_point;
								point next_point;
								point[] points = ((pointed) current_object).getPoints();
								if (Settings.displayObjectNames) page.drawString(current_object.getObjectName(),(int) Math.round(((Physics_drawable) current_object).getCenterX()), (int) Math.round(((Physics_drawable) current_object).getCenterY()));
								for (int i = 0; i < current_object.getPointRenderOrder().length-1 ; i++) {
								
									//calculate the pointkeys
									pointKey = current_object.getPointRenderOrder()[i];
									
									if (i == current_object.getPointRenderOrder().length-1) {
										next_pointKey = 0;
										
									}else {
										next_pointKey = current_object.getPointRenderOrder()[i+1];
									}
									
									
							//		System.out.println(pointKey + "," + next_pointKey);
									
									//get the points
									current_point = points[pointKey];
									next_point = points[next_pointKey];
									
									if (Settings.showPointNumbers) page.drawString("" + i, current_point.getX(), current_point.getY()); //display the point numbers								
								
									//draw line between points
									page.drawLine(current_point.x, current_point.y, next_point.x, next_point.y);
								}
								break;
							}catch(ClassCastException c) {
								System.out.println("Drawable not a pointed object");
							}
						
						case("paint"):
							current_object.paint(page);
							break;
							
						default:
							current_object.paint(page);
							break;
					} //switch drawmethod
				}
			}
		}catch(ConcurrentModificationException c) {}
		
		
	}

	public Thread getThread() {
		return threader;
	}
}
