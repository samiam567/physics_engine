package Physics_engine;

import java.io.Serializable;
import java.awt.Polygon;
import java.awt.geom.Area;

public class PArea extends Area implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3706635267575516552L;
	
	public PArea(Polygon polyXX) {
		super(polyXX);
	}
}
