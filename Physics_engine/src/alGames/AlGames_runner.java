package alGames;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;

import Physics_engine.Physics_engine_toolbox;
import Physics_engine.Physics_frame;
import Physics_engine.PolarObject;
import Physics_engine.Text;
import Physics_engine.object_draw;
import Physics_engine.physicsRunner;
import Physics_engine.physics_runner;
import Physics_engine.point;
import calculator.Calculator_runner;
import calculator.Settings;
import jetpack_joyride.JetPack_JoyRide;
import pong.Pong_runner;

public class AlGames_runner extends physicsRunner {
	
	public static String[] games = {"Pong3D","JetPack Collect","Physics Simulator","global thermonuclear warfare","exit"};
	
	private static String thermoWar = "Are you crazy?!?\nI'm not letting you start WWIII! ";

	private static String version = "1.1.4";
	
	public static void main(String[] args) {
		frame = new Physics_frame(); 
		frame.setVisible(false);
		drawer = new object_draw(frame);
		run();
	}
	
	public static void run() {
		int gameToPlay = 0;
		
		
		JOptionPane.showMessageDialog(frame, "Welcome to AlGames!\nAlGames is a collection of games and programs designed to entertain people!\nmake sure you read all messages carefully and completely as they contain valuable information.\nNote: some games will prompt you if you want to exit and for others you simply have to close the window\n--Built on game engine V" + Physics_engine.Settings.version + "--", "AlGames V" + version  + "      Programmed by Alec Pannunzio", 1);
		do { 
			gameToPlay = JOptionPane.showOptionDialog(frame, "Which game would you like to play?", "Choose A Game!", 1, 1,null, games, null);
			
			
			switch(games[gameToPlay]) {
				case("Pong3D"):
					Pong_runner.run();
					break;
				case("JetPack Collect"):
					JetPack_JoyRide.run();
					break;
				case("Calculator"):
					Calculator_runner.run();
					break;
					
				case("Physics Simulator"):
					Physics_engine.Settings.displayObjectNames = true;
					physics_runner.run();
					Physics_engine.Settings.displayObjectNames = false;
					break;
					
				case("global thermonuclear warfare"):
					Physics_engine.Settings.timeSpeed = 3;
					drawer.setFrameTimeMultiplier(999999999);					
					frame.setVisible(true);
					drawer.add(new Text(drawer,Settings.width * 0.1,Settings.height * 0.1,thermoWar, new Font("TimesRoman", Font.BOLD, (int) (Math.sqrt(Math.pow(Settings.width, 2) + Math.sqrt(Math.pow(Settings.height, 2))) / 20  ))));
					PolarObject heart = new PolarObject(drawer,Settings.width/2,Settings.height * 0.5, 5,100,"heart",Math.PI/10,2*Math.PI);
					heart.setAngularVelocity(0.3,0.2,0);
					heart.setColor(Color.red);
					drawer.add(heart);
					
					PolarObject missile = new PolarObject(drawer,Settings.width/2,Settings.height * 0.7, 5,600,"thing1",Math.PI/20,1.5715*Math.PI);
					missile.setRotation(-5*Math.PI/4, Math.PI/4, -Math.PI/4);
					missile.setPos(Settings.width/2, Settings.height * 0.7, 0);
					missile.setAngularVelocity(0,0.05,0.3);
					drawer.add(missile);
					
					drawer.setFrameTimeMultiplier(350);
					
				
					drawer.start();
					
					while (frame.isShowing()) {
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					drawer.end();
					frame.setVisible(false);
					break;
			}
			
			drawer.Void();
			drawer = new object_draw(frame);
	
			
		}while(gameToPlay != games.length-1);
		
		drawer.end();
		frame.dispose();
		System.exit(1);
	}
}
