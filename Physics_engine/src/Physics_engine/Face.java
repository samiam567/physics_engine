package Physics_engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Face extends Polygon { // a face for a cube or something    not a standalone object
	private float Brightness;
	private point[] points;
	private Physics_drawable parent;
	private Color color;
	private boolean isVisible = true;
	
	
	private Vector3D normalVec, pointVec1, pointVec2, lightVec;
	
	public Face(Physics_drawable parent, point[] points) {
		this.points = points;
		this.parent = parent;
		npoints = points.length;
		if (points.length < 3) {
			Exception l = new Exception("ERROR IN FACE: <BrightnessVec creation> points list cannot be smaller than 3 points");
			l.printStackTrace();
		}
		pointVec1 = new Vector3D(parent.drawer,points[0],points[1]);
		pointVec2 = new Vector3D(parent.drawer,points[0],points[2]);
		lightVec = new Vector3D(parent.drawer,points[0],Settings.lightSourcePoint);
	}
	
	private void updateColor() {
		if (parent.isShaded) {
			pointVec1.set(points[0], points[1]);
			pointVec1.divide(pointVec1.getR());//make unit vector
			pointVec2.set(points[0], points[2]);
			pointVec2.divide(pointVec1.getR());//make unit vector
			
			
			normalVec = Vector3D.cross(pointVec1,pointVec2); //find the normal vector
			
			lightVec.set(points[0], Settings.lightSourcePoint);
			lightVec.divide(lightVec.getR());
	
			Brightness = (float) Vector3D.proj(lightVec,normalVec).getR();
			
			
			if (Brightness < 0) {
				isVisible = false;
			}else {
				isVisible = true;
			}
			
			
			int alpha = (int) (255/(1+parent.getTransparency())); // 1/transparency of the shape
			color = Color.getHSBColor(parent.getColor().getRGB(),1f, Brightness );
			color = new Color(color.getRed(),color.getGreen(),color.getBlue(),alpha);
			
			
		}else {
			color = parent.getColor();
		}
	}
	
	public void Update() {
		xpoints[0] = points[0].getX();
		xpoints[1] = points[1].getX();
		xpoints[2] = points[2].getX();
		xpoints[3] = points[3].getX();
		
		ypoints[0] = points[0].getY();
		ypoints[1] = points[1].getY();
		ypoints[2] = points[2].getY();
		ypoints[3] = points[3].getY();
		
		updateColor();
	}
	
	public void paint(Graphics page) {
		if (isVisible) {
			page.setColor(color);
			
			if (parent.isFilled) {
				page.fillPolygon(this);
			}else {
				page.drawPolygon(this);
			}
		}
	}
}
