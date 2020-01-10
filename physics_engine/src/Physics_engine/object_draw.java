package Physics_engine;

import java.awt.Canvas;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import javax.swing.JPanel;

public class object_draw extends JPanel {
	
	private ArrayList<physics_object> objects = new ArrayList<physics_object>();
	private ArrayList<Physics_drawable> drawables = new ArrayList<Physics_drawable>();
	private ArrayList<massive> tangibles = new ArrayList<massive>();
	private ArrayList<resizable> resizables = new ArrayList<resizable>();
	
	private long frameTime = Settings.frameTime;
	public double frameStep = Settings.frameStep;
	

	public double current_frame = 0; //what frame we are on
	
	private double frameCount;
	
	private long frameStartTime,updateStartTime;
	private long frameEndTime,updateEndTime;
	private long wait_time = 1000,wait_time_temp,subCalcTime = 100000,repaintTime;
	
	private long frameTimeMultiplier = 30000;
	
	public double inactivity_timer = 0;
	
	private int threadState = 0;
	
	public Physics_frame frame;

	private object_draw_update_thread updateThreader;
	
	public point lightSource = new point(this,0,0,0);


	public object_draw(Physics_frame frame1) {
		frame = frame1;
		frame.getContentPane().add(this);
		frame.drawer = this;
		updateThreader = new object_draw_update_thread(this);
		setFocusable(true);
	}
	
	public object_draw() {
		frame = new Physics_frame();
		frame.getContentPane().add(this);
		frame.drawer = this;
		updateThreader = new object_draw_update_thread(this);
		setFocusable(true);
	}
	
	public void setFrame(Physics_frame frame1) {
		frame = frame1;
		frame.getContentPane().add(this);
		frame.drawer = this;
	}
	
	public void boot() {
		updateThreader = new object_draw_update_thread(this);
	}
	
	public void start() {
		frameStep = Settings.frameStep;
		frameTime = Settings.frameTime;
		updateThreader.state = 1;
		threadState = 1;
		updateThreader.start();
		setSize(Settings.width, Settings.height);
	}
	
	@SuppressWarnings("deprecation")
	public void end() {
		updateThreader.suspend();
		threadState = 0;
		while (! (updateThreader.state == 0) )  {
		
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
		while (! (updateThreader.state == 2) )  {
			updateThreader.state = 2;
		}
	}
	
	public void resume() {
		threadState = 1;
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
		
		if ( (Settings.width * Settings.pixelConversion != frame.getWidth()) || (Settings.height * Settings.pixelConversion != frame.getHeight())) {
			if (Settings.autoResizeFrame) {
				Settings.width = (int) (frame.getWidth()/Settings.pixelConversion);
				Settings.height = (int) (frame.getHeight()/Settings.pixelConversion);
				
				resize();
				
				for (resizable cObject : resizables) cObject.resize(); //resize resizable objects
				
				frame.resizeObjects(); //do package-specific resizes through the frame
			}
		}
	}
	
	public void resize() {
		//this is extended by child classes such as Mab_object_draw
	}
	
	@Override
	public void update(Graphics page) {
		paint(page);
	}


	
	
	public void doUpdate() { //for update thread. Updates the objects
		if (threadState == 1) {
			try {
						
				if (Settings.fixedFPS_FStep) {
					
					
					for (int i = 0; i < Settings.FPS; i++) {
						frameStartTime = System.nanoTime();
						repaint();
						
						while(frameCount <= 1) {
							checkForResize();
							updateObjects(frameStep);
							current_frame += frameStep;
							frameCount += frameStep;
							WaitNanotime(wait_time);
							
						}
						frameCount = 0;
						frameEndTime = System.nanoTime();
						wait_time = (int) (frameStep*(1000000000 - (((double)(frameEndTime-frameStartTime))-(((double)wait_time)/frameStep))))/Settings.FPS;
						
						
					}
					
				}else {
					while ( frameCount < 1) {
						
						updateStartTime = System.nanoTime();
						
						frameStartTime = System.nanoTime();
						repaint();
						frameEndTime = System.nanoTime();		
					
						checkForResize();
						updateObjects(frameStep); //update the objects
						current_frame += frameStep;
						
						frameCount += frameStep;
						updateEndTime = System.nanoTime();
						
						subCalcTime = ((int)( (updateEndTime - updateStartTime)));
	
						frameStep = ((double)subCalcTime/1000000)/(Settings.frameTime);
					
					}
																		
			
				
					frameCount = 0;
				}
			}catch(ConcurrentModificationException c) {	
			}catch(NullPointerException e) {
				System.out.println("nullPointer in object_draw.java");
				if (Settings.debugMode) e.printStackTrace();
			}catch(NoSuchElementException n) {} //if the element was deleted while this process was being run			
		}
		
	}
	
	public void paint(Graphics page)  {
		
		page.setColor(frame.getBackground());
		page.fillRect(0, 0, Settings.width, Settings.height);
		try {
		//sorting objects by z distance ----------------------------------
		Collections.sort( drawables, new Comparator<drawable>() {
	     
	        public int compare(drawable o1, drawable o2) {
	            return Double.compare(o2.getCenterZ(), o1.getCenterZ());
	        }

	
	    });
		//----------------------------------------------------------------

			for (drawable current_object : drawables) {
			
				
				if ( current_object.getIsVisible() && current_object.getCenterZ()+current_object.getZSize() >= 0) {
					try {
						
						if ( frame.checkIsInFrame((pointed) current_object) || ((drawable) current_object).getIsAlwaysVisible()) {
							
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
										if (Settings.displayObjectNames) page.drawString(current_object.getObjectName(),(int) Math.round(((Physics_drawable) current_object).getCenterX() * Settings.pixelConversion), (int) Math.round(((Physics_drawable) current_object).getCenterY() * Settings.pixelConversion));
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
											page.drawLine((int)(current_point.getX() * Settings.pixelConversion), (int)(current_point.getY()* Settings.pixelConversion), (int)(next_point.getX()* Settings.pixelConversion), (int)(next_point.getY()* Settings.pixelConversion));
										}
										
										
										if (Settings.showPointNumbers) { //display the point numbers	
											for (int i = 0; i < points.length; i++) {
												page.drawString("" + i, (int) (points[i].getX()* Settings.pixelConversion),(int)( points[i].getY()* Settings.pixelConversion)); 
											}
										}
										
										break;
								
								case("paint"):
									current_object.paint(page);
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
									if (Settings.displayObjectNames) page.drawString(current_object.getObjectName(),(int) Math.round(((Physics_drawable) current_object).getCenterX()* Settings.pixelConversion), (int) Math.round(((Physics_drawable) current_object).getCenterY()* Settings.pixelConversion));
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
										page.drawLine((int)(current_point.getX() * Settings.pixelConversion), (int)(current_point.getY()* Settings.pixelConversion), (int)(next_point.getX()* Settings.pixelConversion), (int)(next_point.getY()* Settings.pixelConversion));
									}
									
									
									if (Settings.showPointNumbers) { //display the point numbers	
										for (int i = 0; i < points.length; i++) {
											page.drawString("" + i, (int) (points[i].getX()* Settings.pixelConversion),(int)( points[i].getY()* Settings.pixelConversion)); 
										}
									}
									
									break;
							
							case("paint"):
								current_object.paint(page);
								break;
							
							default:
								current_object.paint(page);
								break;
						} 
					}
				}
			}
		}catch(ConcurrentModificationException c) {
		
		}
		
	}

	public Thread getThread() {
		return updateThreader;
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

	public void setWaitTime(long wait_time) {
		this.wait_time = (int) wait_time;
	}

	public double getFrameStep() {
		return frameStep;
	}
}
