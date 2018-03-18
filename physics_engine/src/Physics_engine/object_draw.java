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
	public ArrayList<massive> tangibles = new ArrayList<massive>();
	public ArrayList<resizable> resizables = new ArrayList<resizable>();
	
	private long frameTime = Settings.frameTime;
	public double frameStep = Settings.frameStep;
	
	public ArrayList<force> scheduled_forces = new ArrayList<force>(); //list of forces for the maintenance bot to apply
	
	public double current_frame = 0; //what frame we are on
	private long frameStartTime,updateStartTime;
	private long frameEndTime,updateEndTime;
	private long wait_time;
	
	public int inactivity_timer = 0;
	
	public Physics_frame frame;

	public object_draw_thread threader;
	
	public object_draw_update_thread updateThreader;

	public object_draw(Physics_frame frame1) {
		frame = frame1;
		frame.getContentPane().add(this);
		threader = new object_draw_thread(this);
		updateThreader = new object_draw_update_thread(this);
	}
	
	public void setFrame(Physics_frame frame1) {
		frame = frame1;
		frame.getContentPane().add(this);
	}
	
	public void boot() {
		threader = new object_draw_thread(this);
		updateThreader = new object_draw_update_thread(this);
	}
	
	public void start() {
		threader.start();
		updateThreader.start();
	}
	
	public void end() {
		threader.state = 0;
		updateThreader.state = 0;
	}
	
	public void stop() {
		threader.state = 0;
		updateThreader.state = 0;
	}
	
	public void pause() {
		threader.state = 2;
		updateThreader.state = 2;
	}
	
	public void resume() {
		threader.state = 1;
		updateThreader.state = 1;
	}
	
	public void add(physics_object newOb) {
		objects.add(newOb);
		
		try {
			drawables.add((Physics_drawable) newOb);
		}catch(ClassCastException c) {
			//ob is not drawable
		}
		
		try {
			tangibles.add((massive)newOb);
		}catch(ClassCastException e) {
			//ob is not tangible
		}
		
		try {
			resizables.add((resizable) newOb);
		}catch(ClassCastException e) {
			//ob is not resizable
		}
		
	}
	
	public void remove(physics_object removeOb) {
		objects.remove(removeOb);
		
		try {
			drawables.remove((Physics_drawable) removeOb);
		}catch(ClassCastException c) {
			//ob is not drawable
		}
		
		try {
			tangibles.remove((massive)removeOb);
		}catch(ClassCastException e) {
			//ob is not tangible
		}
	}
	
	private void updateObjects(double frames) {
		try {
			for (physics_object current_object : objects) {				
				Physics_engine_toolbox.Update(current_object,frames);
			}
		}catch(ConcurrentModificationException c) {}
		
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
	
	
	public void checkForResize() {		
		
		if ( (Settings.width != frame.getWidth()) || (Settings.height != frame.getHeight())) {
			if (Settings.autoResizeFrame) {
				Settings.width = frame.getWidth();
				Settings.height = frame.getHeight();
				
				resize();
				
				for (resizable cObject : resizables) cObject.resize(); //resize resizable objects
				
				frame.resizeObjects(); //do package-specific resizes through the frame
			}
		}
	}
	
	public void resize() {
		//this is extended by child classes such as Mab_object_draw
	}

	
	public void doFrame() {
		checkForResize();
		frameStartTime = System.nanoTime();
		current_frame++;
		updateObjects(1);
		repaint();
		frameEndTime = System.nanoTime();
		wait_time = getFrameTime() - (frameEndTime - frameStartTime);
		WaitNanotime(wait_time);
	
	}
	
	public void doFrame(double frames) {
	
		frameStartTime = System.nanoTime();
		current_frame += frames;
		updateObjects(frames);
		
		if ( Math.abs(current_frame - (int)current_frame ) < frameStep) { 
			checkForResize();
			repaint(); 
			frameEndTime = System.nanoTime();
			wait_time = getFrameTime() - (frameEndTime - frameStartTime);
			
			if (wait_time < 0) {
				Exception ex = new Exception("Wait time is less than 0! wait_time: " + wait_time);
				ex.printStackTrace();
			}
			
			WaitNanotime(wait_time);
		}
	}
	
	public void doThreadedFrame() {
		
		frameStartTime = System.nanoTime();
	
		repaint(); 
		frameEndTime = System.nanoTime();
		
		wait_time = (600*(frameEndTime - frameStartTime) + getFrameTime())/1000000;
		sleepThread(wait_time);
	
	}
	
	public void doUpdate() { //for update thread. Updates the objects
		updateStartTime = System.nanoTime();
		checkForResize();
		updateObjects(frameStep); //update the objects
		current_frame += frameStep;
		updateEndTime = System.nanoTime();
		frameStep = ((double) (updateEndTime - updateStartTime)) / 100000000; //automatically set the frameRate depending on how fast the cpu is going
		sleepThread(1);
	}
	
	public void doFrame(String key) {
		assert key == "step";
		for (double i = 0; i < 1; i+= frameStep) {
			checkForResize();
			doFrame(frameStep);
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
				
				
				if ( current_object.getIsVisible() ) {
					
					if (   true  )  { //frame.checkIsInFrame((Physics_drawable) current_object)) {
						if (Settings.displayObjectNames) page.drawString(current_object.getObjectName(),(int) Math.round(current_object.getXReal()), (int) Math.round(current_object.getYReal())); //displaying the name of the object
					
						page.setColor(current_object.getColor());
						
						
						
						switch (current_object.getDrawMethod()) {
						
							case("pointDraw"):
								try {
									for (point cPoint : ((pointed) current_object).getPoints()) {
										cPoint.paint(page);
									}
								}catch(ClassCastException c) {
									System.out.println("Drawable not a pointed object (painting)");
								}
								break;
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
										
																	
									
										//draw line between points
										page.drawLine(current_point.x, current_point.y, next_point.x, next_point.y);
									}
									
									
									if (Settings.showPointNumbers) { //display the point numbers	
										for (int i = 0; i < points.length; i++) {
											page.drawString("" + i, points[i].getX(), points[i].getY()); 
										}
									}
									
									break;
								}catch(ClassCastException c) {
									System.out.println("Drawable not a pointed object (painting)");
								}
							
							case("paint"):
								current_object.paint(page);
								break;
								
							case("drawRect"):
								page.drawRect(current_object.getX(),current_object.getY(),(int) current_object.getXSizeAppearance(), (int) current_object.getYSizeAppearance());
								break;
							case("fillRect"):
								page.fillRect(current_object.getX(),current_object.getY(),(int) current_object.getXSizeAppearance(), (int) current_object.getYSizeAppearance());
								break;
							default:
								current_object.paint(page);
								break;
						} //switch drawmethod
					}
				}
			}
		}catch(ConcurrentModificationException c) {}
		
		
	}

	public Thread getThread() {
		return threader;
	}

	public ArrayList<physics_object> getObjects() {
		return objects;
	}
	
	public ArrayList<massive> getTangibles() {
		return tangibles;
	}

	public ArrayList<Physics_drawable> getDrawables() {
		return drawables;
	}

	public long getFrameTime() {
		return frameTime;
	}

	public void setFrameTime(long frameTime) {
		this.frameTime = frameTime;
	}
	
	public long getWaitTime() {
		return wait_time;
	}

	public double getFrameStep() {
		return frameStep;
	}
}
