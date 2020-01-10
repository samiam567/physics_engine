package zortex;

import java.awt.Color;

import Physics_engine.Physics_shape;
import Physics_engine.Settings;
import Physics_engine.massive;
import Physics_engine.movable;
import Physics_engine.object_draw;
import Physics_engine.physics_object;
import Physics_engine.point;
import Physics_engine.rectangle;

public class WallRect extends rectangle implements movable{
	public WallRect(object_draw drawer1,int i) {
		super(drawer1,Settings.width/2,Settings.height/2,i,Zortex_runner.wallXSize,Zortex_runner.wallYSize,1000);
		setHasNormalCollisions(false);
		setColor(Color.blue);
		setPointOfRotation(new point(drawer,Settings.width/2,Settings.height/2,0));
		isTangible = false;
		setType("enviro-move");
		drawer.add(this);
		setSize(Zortex_runner.wallXSize,Zortex_runner.wallYSize,Zortex_runner.wallZDist);
	}
	
	
}
