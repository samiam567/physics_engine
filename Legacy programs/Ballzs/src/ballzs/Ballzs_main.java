package ballzs;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;



public class Ballzs_main extends JFrame{
	public static Ballzs_cpu cpu = new Ballzs_cpu();
	
	public Settings settings = new Settings();
	
	JFrame jFrame = new JFrame();
	
	
	public Ballzs_main() {
		
		JFrame MousePad = new JFrame();
		MousePad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MousePad.pack();
		
		
		//mouseListener +==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+
		MouseAdapter mouse =  new MouseAdapter() {

		public void mouseClicked(MouseEvent arg0) {
			System.out.println("mouse clicked");
			System.out.println(arg0.getX());
			System.out.println(arg0.getY());
		}

		public void mouseEntered(MouseEvent arg0) {
			System.out.println("mouse entered");
		}

		
		public void mouseExited(MouseEvent arg0) {

			System.out.println("mouse exited");
			
		}

		
		public void mousePressed(MouseEvent arg0) {

			System.out.println("mouse pressed");
			
		}


		public void mouseReleased(MouseEvent arg0) {
			System.out.println("mouse released");
			
		}
		};
		
		
		//==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+
	
	//	MousePad.addMouseListener(mouse);
		
	//	MousePad.setSize(1000,1000);
	
	//	MousePad.setVisible(true);
		
		
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		System.out.println("Jarvis is now online");
		jFrame.setSize(settings.width,settings.height);
		
		jFrame.setTitle("Ballzs V1.0  -  By Alec Pannunzio");
		jFrame.getContentPane().add( cpu ); 
		
		
		
	}
	
}
