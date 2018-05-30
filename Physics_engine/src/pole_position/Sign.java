package pole_position;

import Physics_engine.Box;
import Physics_engine.object_draw;
import calculator.Settings;

public class Sign extends Box {
	
	private Track track;
	
	public Sign(object_draw drawer1, double y, Track trackToFollow) {
		super(drawer1,Settings.height + 100, y, 0, 100,10);
		track = trackToFollow;
		setParentObject(track);
		setRotation(Math.PI/4,Math.PI/16,0);
		setHasNormalCollisions(false);
		isTangible = false;
	}
	
	public void tertiaryUpdate() {
		setSpeed(-track.getXSpeed(),track.getYSpeed(),track.getZSpeed());
		
		if (centerY > Settings.height + 100) {
			setPos(centerX,-100,centerZ);
		}else {
			setPos(track.getXAtY(centerY) + 500,centerY,centerZ);
		}
	}

}
