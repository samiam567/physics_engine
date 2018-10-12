package Physics_engine;

import java.io.Serializable;
import java.util.ConcurrentModificationException;

//OBSOLETE

public class object_draw_thread extends Thread implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1667890055673545138L;
	public object_draw objectDrawer;
	public int state = 0; //0 = stopped, 1 = running, 2 = paused
	
	Exception e = new Exception("This class is obsolete and should not be used");
	
	public object_draw_thread(object_draw objectDrawer1) {
		e.printStackTrace();
		objectDrawer = objectDrawer1;
	}
	
	public void run() {
		while (state != 0) {
			
			try {
				if (state == 1) { //running
					try {
					//	objectDrawer.doThreadedFrame();
						
						
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
			}catch(ConcurrentModificationException c) {
				
			}
		}
	}
	

}
