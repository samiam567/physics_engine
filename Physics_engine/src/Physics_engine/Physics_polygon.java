package Physics_engine;

import java.awt.Graphics;

public class Physics_polygon extends Physics_shape implements pointed{
	public void paint(Graphics page) {
			
			if (Settings.displayObjectNames) page.drawString(name,(int) Math.round(points[0].getXReal()), (int) Math.round(points[0].getYReal())); //displaying the name of the object
			
			
			if (Settings.showPointNumbers) {
				point current_point;
				for (int i = 0; i < points.length; i++) {
					current_point = points[i];
					page.drawString("" + i, current_point.getX(), current_point.getY()); //display the point numbers
				}
			}		
			
			updatePointXsYsAndZs();
			updateAreas();
		
			if (isFilled) {
				page.fillPolygon(pointXs, pointYs, points.length);
			}else {
				page.drawPolygon(pointXs, pointYs, points.length);
			}
			
	
		}
}
