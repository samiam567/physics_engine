package physics_sims;

import java.awt.Color;

import javax.swing.JOptionPane;

import Physics_engine.FCPS_display;
import Physics_engine.FPS_display;
import Physics_engine.Physics_3DPolygon;
import Physics_engine.Physics_engine_toolbox;
import Physics_engine.Physics_frame;
import Physics_engine.Sphere;
import Physics_engine.Square;
import Physics_engine.Triangle;
import Physics_engine.border_bounce;
import Physics_engine.object_draw;
import Physics_engine.physicsRunner;
import Physics_engine.rectangle;
import Physics_engine.Physics_engine_toolbox.pointOfRotationPlaces;
import Physics_engine.Settings;

public class Ball_shooter_golf_runner extends physicsRunner{
	private static final String version = "2.0.2";
	
	private static final boolean actualSpeed = true;
	
	private static double g = -980, launchVelocity,launchAngle, launchAngle2, xDistFromTarg,yDistFromTarg,lADeg,lADeg2;
	
	private static Physics_3DPolygon launcher, ball, target;
	
	private static double[] launchSpeeds = {295,450,620};
	
	public static void main(String[] args) {
		
		run();
	}
	
	public static void setDrawer(object_draw drawer1) {
		frame =  new Physics_frame();
		drawer = drawer1;
		drawer.setFrame(frame);
	}
	
	public static void run() {
		
		Settings.timeSpeed = 20;
		
		String[] solveForOptions = {"angle","xDistance","launchVelocity"};
		
		do {
			frame = new Physics_frame();
			Settings.frameColor = Color.orange;
			frame.setBackground(Color.orange);
			
			drawer = new object_draw(frame);
			drawer.setBackground(Color.ORANGE);
			frame.setVisible(true);
			
			FPS_display fps = new FPS_display(drawer,30,30);
			drawer.add(fps);

			FCPS_display fcps = new FCPS_display(drawer,30,50);
			drawer.add(fcps);
			
			
			launcher = new rectangle(drawer, Settings.width * 0.1,Settings.height * 0.7,0,50/Settings.pixelConversion,10/Settings.pixelConversion,1);
			launcher.isTangible = false;
			launcher.isFilled = true;
			launcher.setColor(Color.green);
			drawer.add(launcher);
			drawer.start();
			
			int solvingFor = JOptionPane.showOptionDialog(frame, "What would you like to solve for?", "Select Variable", 0, 0, null, solveForOptions, 0);
	
			boolean Error = true;
			
			target = new Square(drawer,Settings.width * 0.7, Settings.height * 0.7,0,13,1);
			target.setColor(Color.red);
			target.isFilled = true;
			drawer.add(target);
			
			
			while (Error) {
				
				if (solvingFor != 0) {
					do {
					launchAngle = -Physics_engine_toolbox.getDoubleFromUser(frame, "What is the launch angle? (degrees)");
					}while((-launchAngle % 360) >= 90);
					launchAngle = launchAngle % 360;
					launchAngle = Math.PI * launchAngle/180; //convert to radians
					launcher.setRotation(0, 0, launchAngle);	
				}
				
				if (solvingFor != 1) {
					do {	
					xDistFromTarg = Physics_engine_toolbox.getDoubleFromUser(frame, "How far is the launcher from the target in the x dimension? (cm)");
					}while(xDistFromTarg <= 0);
					target.setPos(Settings.width * 0.1 + xDistFromTarg,Settings.height * 0.7 - yDistFromTarg ,0);	
					
					//resizing frame so you can see it
					if (xDistFromTarg + Settings.width * 0.1 + 200 > Settings.width) {
						frame.setSize((int) (target.getCenterX() + 200),Settings.height);
						launcher.setPos(Settings.width * 0.1 ,Settings.height * 0.7  ,0); //repositioning the launcher
						target.setPos(Settings.width * 0.1 + xDistFromTarg,Settings.height * 0.7 - yDistFromTarg ,0); //repositioning the target
					}
				}
				
				yDistFromTarg = Physics_engine_toolbox.getDoubleFromUser(frame, "How far is the launcher from the target in the y dimension? (cm)");
				target.setPos(Settings.width * 0.1 + xDistFromTarg,Settings.height * 0.7 - yDistFromTarg ,0);
				
				if (solvingFor != 2) {
					if (actualSpeed) {
						launchVelocity = Physics_engine_toolbox.getDoubleFromUser(frame,"What is the launch velocity? (cm/s)");
					}else {
						int launchSetting = 0;
						do {
							launchSetting = Physics_engine_toolbox.getIntegerFromUser(frame,"What is the launcher setting");
						}while((0 > launchSetting) || (4 < launchSetting));
						
				
						launchVelocity = launchSpeeds[launchSetting];
					}
				}
				
				target.setPos(Settings.width * 0.1 + xDistFromTarg,Settings.height * 0.7 - yDistFromTarg ,0);

				double xF = xDistFromTarg,Vi = launchVelocity, yF = -yDistFromTarg, theta = launchAngle;
				
				if (solvingFor == 0) { // checking for if the settings are possible (for finding angle)				
					double a = ( g * xF * xF) / (2 * Vi * Vi);
					if ((xF*xF - 4 * a * (yF + a)) < 0) {
						JOptionPane.showMessageDialog(frame, "That is not possible. Try different values", "beyond max range", 1);
						Error = true;
					}else {
						Error = false;
					}
				}else if (solvingFor == 1) {
						double a = -g / (2 * Vi * Vi * Math.pow(Math.cos(theta),2));	
						double b = Math.tan(theta);
						if ((b*b - 4 * a * -yF) < 0) {
							JOptionPane.showMessageDialog(frame, "That is not possible. Try different values", "above maximum height", 1);
							Error = true;
						}else {
							Error = false;
						}
				}else {
					if ( ( g * xF * xF ) / ( (xF * Math.tan(theta) - yF) * 2 * Math.pow(Math.cos(theta), 2) ) < 0) {
						JOptionPane.showMessageDialog(frame, "That is not possible. Try different values", "above maximum height", 1);
						Error = true;		
					}else {
						Error = false;
					}
				}	
			}

			double xF = xDistFromTarg,Vi = launchVelocity, yF = -yDistFromTarg, theta = launchAngle;
			
			if (solvingFor == 0) { //solve for launchAngle
				double a = ( g * xF * xF) / (2 * Vi * Vi);
				launchAngle = Math.atan( ( -xF + Math.sqrt(xF*xF - 4 * a * (yF + a)) ) / (2*a));
				launchAngle2 = Math.atan( ( -xF - Math.sqrt(xF*xF - 4 * a * (yF + a)) ) / (2*a));
				lADeg = 180 * launchAngle/Math.PI;
				lADeg2 = 180 * launchAngle2/Math.PI;
				System.out.println("launch angle: " + lADeg);
						
				launchAngle = -launchAngle;
				launchAngle2 = -launchAngle2;
				launcher.setRotation(0, 0, launchAngle2);
				
				ball = new Square(drawer,Settings.width * 0.1,Settings.height * 0.7, 0, 7,1);
				ball.setColor(Color.blue);
				ball.isFilled = true;
				ball.setSpeed(launchVelocity * Math.cos(launchAngle2),launchVelocity * Math.sin(launchAngle2), 0);
				ball.setAccel(0,-g  , 0);
				
				JOptionPane.showConfirmDialog(frame, "Fire at angle: " + lADeg2);
				
				drawer.add(ball);  //Fire!!
				
				int i = 0;
				while( (target.getCenterX() == Settings.width * 0.1 + xDistFromTarg) && (i < 100) ) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					i++;
				}
				try {
					Thread.sleep(700);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		
				drawer.remove(ball);
			}else if (solvingFor == 1) { //solve for xDistance
				
				double a = -g / (2 * Vi * Vi * Math.pow(Math.cos(theta),2));	
				double b = Math.tan(theta);
				xDistFromTarg = ( -b + Math.sqrt(b*b - 4 * a * -yF) ) / (2*a);

				//placing target
				target.setPos(Settings.width * 0.1 + xDistFromTarg,Settings.height * 0.7 - yDistFromTarg ,0);	
				
				//resizing frame so you can see it
				if (xDistFromTarg + Settings.width * 0.1 + 200 > Settings.width) {
					frame.setSize((int) (target.getCenterX() + 200),Settings.height);
					launcher.setPos(Settings.width * 0.1 ,Settings.height * 0.7  ,0); //repositioning the launcher
					target.setPos(Settings.width * 0.1 + xDistFromTarg,Settings.height * 0.7 - yDistFromTarg ,0); //repositioning the target
				}
			}else { //solve for launchVelocity
				launchVelocity =   Math.sqrt( ( g * xF * xF ) / ( (xF * Math.tan(theta) - yF) * 2 * Math.pow(Math.cos(theta), 2) ) );
			}
			
			
			launcher.setRotation(0, 0, launchAngle);
			
	
			//placing new target at desired location
			drawer.remove(target);
			target = new Square(drawer,Settings.width * 0.1 + xDistFromTarg,Settings.height * 0.7 - yDistFromTarg ,0,13,1);
			target.isFilled = true;
			target.setColor(Color.red);
			drawer.add(target);
			
			
			
			
			if (solvingFor == 0) {
				JOptionPane.showMessageDialog(frame, "Fire at angle: " + lADeg);
			}else if (solvingFor == 1) {
				JOptionPane.showMessageDialog(frame, "Fire at xDistance: " + xDistFromTarg);
			}else {
				JOptionPane.showMessageDialog(frame, "Fire at speed: " + launchVelocity);
			}
			
		
			ball = new Square(drawer,Settings.width * 0.1,Settings.height * 0.7, 0, 7,1);
			ball.isFilled = true;
			ball.setColor(Color.blue);
			ball.setSpeed(launchVelocity * Math.cos(launchAngle),launchVelocity * Math.sin(launchAngle), 0);
			ball.setAccel(0, -g, 0);
			
			drawer.add(ball);
		
		
			resize(frame);
			
			int i = 0;
			while ( (target.getCenterX() == Settings.width * 0.1 + xDistFromTarg) && (i < 100) ) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				i++;
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			frame.dispose();
			drawer.end();
			
		}while(JOptionPane.showConfirmDialog(frame, "Another?") == 0);

	
	
		
		
		
		
	}
	

}
