package Physics_engine;

import java.util.ConcurrentModificationException;

public class object_draw_thread extends Thread {
	public object_draw objectDrawer;
	public int state = 1; //0 = stopped, 1 = running, 2 = paused
	
	public object_draw_thread(object_draw objectDrawer1) {
		objectDrawer = objectDrawer1;
	}
	
	public void run() {
		while (state != 0) {
			if (state == 1) { //running
				try {
					objectDrawer.doThreadedFrame(Settings.frameStep);
					
					if (objectDrawer.inactivity_timer < Settings.timeOutTime) {
						objectDrawer.inactivity_timer++;
					}else {
						System.out.println("Session timed out. Terminating program.");
						objectDrawer.frame.dispose();
						System.exit(1);
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
			}
		}
	}
	

}
