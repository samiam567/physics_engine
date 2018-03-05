package jetpack_joyride;

import java.awt.Color;
import java.awt.Container;

import Physics_engine.Physics_frame;
import Physics_engine.Settings;

public class JPJR_frame extends Physics_frame{
	
	public Container cp;
	
	public JPJR_frame() {
	
		setTitle("Jetpack Joyride V" + JetPack_JoyRide.version + "  -  By Alec Pannunzio");
		setBackground(Color.black);		
		
		cp = getContentPane();
		cp.setBackground(Color.BLACK);
	
	}
	
	public void resizeObjects() {
		System.out.println("resize");
		JetPack_JoyRide.resize();
	}
}
