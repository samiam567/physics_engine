package pong;

import java.awt.Color;
import java.awt.Graphics;

import Physics_engine.Physics_drawable;
import Physics_engine.Physics_engine_toolbox;
import Physics_engine.Settings;
import Physics_engine.Text;
import Physics_engine.object_draw;
import Physics_engine.rectangle;

public class PongStartScreen extends rectangle {
	
	Text text;
	
	public PongStartScreen(object_draw drawer1) {
		super(drawer1,Settings.width/2,Settings.height/2,10,Settings.width,Settings.height,10);
		
		setColor(Color.white);
		
		isTangible = false;
		isRotatable = false;
		isVisible = false;
		
		for (Physics_drawable cD : drawer.getDrawables()) {
			cD.isVisible = false;
		}
	
		
	
		
		
		Pong_runner.ball.setAngularVelocity(0.2, 0.3, 0.2);
		Pong_runner.ball.setPos(Settings.width/2, Settings.height/1.5, Pong_runner.ball.getZReal());
		Pong_runner.ball.isTangible = false;
		Pong_runner.ball.affectedByBorder = false;
		Pong_runner.ball.isVisible = true;
		
		Settings.perspective = false;
	
		text = new Text(drawer,0.1 * Settings.width,0.2 * Settings.height,"      3D Pong V" + Pong_runner.Version + "\nProgrammed By Alec Pannunzio",Physics_engine_toolbox.bigFont); 
		text.setColor(Color.WHITE);
		drawer.add(text);
		
		drawer.add(this);
	
		
	}
	
	
	public void goAway() {
		Settings.perspective = true;
		
		for (Physics_drawable cD : drawer.getDrawables()) {
			cD.isVisible = true;
		}
		
		Pong_runner.ball.isTangible = true;
		Pong_runner.ball.affectedByBorder = true;

		
		Pong_runner.borders.isVisible = false;
		
		Pong_runner.ball.setRotation(0,0,0);
		Pong_runner.ball.setAngularVelocity(0, 0, 0);
		
		Pong_runner.resize();
		
		drawer.remove(text);
		drawer.remove(this);
	}

}
