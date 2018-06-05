package Physics_engine;

import java.io.Serializable;
import java.util.ConcurrentModificationException;

public class object_draw_update_thread extends Thread implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7763378267389433674L;
	public object_draw objectDrawer;
	public int state = 0; //0 = stopped, 1 = running, 2 = paused
	
	public object_draw_update_thread(object_draw objectDrawer1) {
		objectDrawer = objectDrawer1;
	}
	
	public void run() {
		while (state != 0) {
			try {
				if (state == 1) { //running
					try {
						objectDrawer.doUpdate();
						
						if (objectDrawer.inactivity_timer < Settings.timeOutTime) {
							objectDrawer.inactivity_timer += objectDrawer.frameStep;
						}else {
							System.out.println("Session Timed Out. Terminating...");
							System.exit(0);
						}
					}catch (ConcurrentModificationException c) {}
				}else if (state == 2) { //paused
					try {
						sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else {
					System.out.println("thread stopped.");
					return;
				}
				
			}catch(ConcurrentModificationException c) {}
		}
		
	}
	
	

}
