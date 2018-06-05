package Physics_engine;

import java.awt.Canvas;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class object_draw extends Canvas {
	
	private ArrayList<physics_object> objects = new ArrayList<physics_object>();
	public ArrayList<Physics_drawable> drawables = new ArrayList<Physics_drawable>();
	public ArrayList<massive> tangibles = new ArrayList<massive>();
	public ArrayList<resizable> resizables = new ArrayList<resizable>();
	
	private long frameTime = Settings.frameTime;
	public double frameStep = Settings.frameStep;

	public double current_frame = 0; //what frame we are on
	private int frameCount;
	
	private long frameStartTime,updateStartTime;
	private long frameEndTime,updateEndTime;
	private long wait_time = 100,wait_time_temp;
	
	private double frameTimeMultiplier = 300;
	
	public double inactivity_timer = 0;
	
	private int threadState = 0;
	
	public Physics_frame frame;

	public object_draw_thread threader;
	
	public object_draw_update_thread updateThreader;
	
	public point lightSource = new point(this,0,0,0);

	public object_draw(Physics_frame frame1) {
		frame = frame1;
		frame.getContentPane().add(this);
		frame.drawer = this;
		threader = new object_draw_thread(this);
		updateThreader = new object_draw_update_thread(this);
	}
	
	public object_draw() {
		frame = new Physics_frame();
		frame.getContentPane().add(this);
		frame.drawer = this;
		threader = new object_draw_thread(this);
		updateThreader = new object_draw_update_thread(this);
	}
	
	public void setFrame(Physics_frame frame1) {
		frame = frame1;
		frame.getContentPane().add(this);
		frame.drawer = this;
	}
	
	public void boot() {
		threader = new object_draw_thread(this);
		updateThreader = new object_draw_update_thread(this);
	}
	
	public void start() {
		threader.state = 1;
		updateThreader.state = 1;
		threadState = 1;
		threader.start();
		updateThreader.start();
		setSize(Settings.width, Settings.height);
	}
	
	@SuppressWarnings("deprecation")
	public void end() {
		threader.suspend();
		updateThreader.suspend();
		threadState = 0;
		while (! ((threader.state == 0) && (updateThreader.state == 0) )  ) {
			threader.state = 0;
			updateThreader.state = 0;
		}
	}
	
	public void stop() {
		end();
	}
	
	public void Void() {
		end();
		frame.remove(this);
	}
	
	public void pause() {
		threadState = 2;
		while (! ((threader.state == 2) && (updateThreader.state == 2) )  ) {
			threader.state = 2;
			updateThreader.state = 2;
		}
	}
	
	public void resume() {
		threadState = 1;
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
		
		try {
			resizables.remove((resizable) removeOb);
		}catch(ClassCastException e) {
			//ob is not resizable
		}
		
	}
	
	public void clearObjects() {
		objects.clear();
		drawables.clear();
		tangibles.clear();
		resizables.clear();
	}

	private void updateObjects(double frames) {
		for (physics_object current_object : objects) {				
			Physics_engine_toolbox.Update(current_object,frames);
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

	/*
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
	

	
	public void doFrame(String key) {
		assert key == "step";
		for (double i = 0; i < 1; i+= frameStep) {
			checkForResize();
			doFrame(frameStep);
		}
	}
	
	*/

	public void doThreadedFrame() {
		try {
			if (threadState == 1) {
				frameStartTime = System.nanoTime();
				repaint(); 
				frameEndTime = System.nanoTime();
				
				wait_time_temp = (long) (frameTimeMultiplier * (frameEndTime - frameStartTime)/100000);
				
				//use machine learning to adjust to the right wait_time
				if (wait_time_temp > wait_time) {
					wait_time ++;
				}else if (wait_time_temp < wait_time) {
					wait_time --;
				}
				sleepThread(wait_time);
			}
		}catch(ConcurrentModificationException c) {}
		
	}
	
	public void doUpdate() { //for update thread. Updates the objects
		if (threadState == 1) {
			try {
				while ( frameCount < 1) {
					updateStartTime = System.nanoTime();
					checkForResize();
					updateObjects(frameStep); //update the objects
					current_frame += frameStep;
					frameCount += frameStep;
					updateEndTime = System.nanoTime();
					
					frameStep = ((double) (updateEndTime - updateStartTime)) / 100000000; //automatically set the accuracy of the subCalculations depending on how fast the cpu is going
				}
		
				sleepThread(1);
		
				frameCount = 0;
			}catch(ConcurrentModificationException c) {	
			}catch(NullPointerException e) {
				System.out.println("nullPointer");
			}catch(NoSuchElementException n) {} //if the element was deleted while this process was being run
			
		}
		
	}
	
	public void paint(Graphics page)  {
	
		//sorting objects by z distance ----------------------------------
		Collections.sort( drawables, new Comparator<drawable>() {
	     
	        public int compare(drawable o1, drawable o2) {
	            return Double.compare(o2.getZReal(), o1.getZReal());
	        }

	
	    });
		//----------------------------------------------------------------

			for (drawable current_object : drawables) {
			
				
				if ( current_object.getIsVisible() ) {
					try {
						if ( frame.checkIsInFrame((pointed) current_object)) {
							
							page.setColor(current_object.getColor());
		
							switch (current_object.getDrawMethod()) {
							
								case("pointDraw"):
									
									for (point cPoint : ((pointed) current_object).getPoints()) {
										cPoint.paint(page);
									}
								
									break;
								case("ListedPointAlgorithm"):
							
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
												break;
												
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
							} 
						}
							
					}catch(ClassCastException c) {
						page.setColor(current_object.getColor());
						
						switch (current_object.getDrawMethod()) {
						
							case("pointDraw"):
									for (point cPoint : ((pointed) current_object).getPoints()) {
										cPoint.paint(page);
									}
								
								break;
							case("ListedPointAlgorithm"):
						
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
											break;
											
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
						} 
					}
				}
			}
		
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

	public void setFrameTimeMultiplier(int frameTime) {
		frameTimeMultiplier = frameTime;
	}
	
	public long getWaitTime() {
		return wait_time;
	}

	public double getFrameStep() {
		return frameStep;
	}
}
