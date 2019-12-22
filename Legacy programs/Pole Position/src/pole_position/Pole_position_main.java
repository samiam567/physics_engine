package pole_position;

import javax.swing.JFrame;


public class Pole_position_main  extends JFrame {

	public static void main(String[] args) {
		//Setup the canvas
		
			JFrame jFrame = new JFrame();
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jFrame.setVisible(true);
			
			jFrame.setSize(1920,1080);
			
			jFrame.getContentPane().add( new Pole_position_cpu() ); 


	}
}
