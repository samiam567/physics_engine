package ballzsV2;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;



public class BallzsV2_main {
	public static BallzsV2_mainframe cpu = new BallzsV2_mainframe();
	
	public Settings settings = new Settings();
	
	JFrame jFrame = new JFrame();
	
	
	public BallzsV2_main() {
		
		
		
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		System.out.println("Jarvis is now online");
		jFrame.setSize(settings.width,settings.height);
		
		jFrame.setTitle("Ballzs V1.0  -  By Alec Pannunzio");
		jFrame.getContentPane().add( cpu ); 
		
		
		
	}
	
}
