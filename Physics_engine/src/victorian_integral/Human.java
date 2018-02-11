package victorian_integral;

import java.awt.Graphics;

import Physics_engine.physics_object;

public class Human extends physics_object {
	
	public boolean showName = false;
	
	public Human(String name1, double x1, double y1, double z1,double size1, double mass1) {
		setName(name1,1);
		setPos(x1,y1,z1);
		setSize(size1,size1,0);
		setMass(mass1);
	}
	public void paint(Graphics page) {
		updateSize();
		if (showName) {
			page.drawString(name,(int) centerX,(int) (centerY - ySizeAppearance/3));
		}
		int headSize = (int) Math.round(ySizeAppearance/4);
		page.drawOval((int) (x + xSizeAppearance/4),(int) (y + ySizeAppearance/4), headSize, headSize);
		page.drawLine((int) centerX - 6,(int) (centerY + headSize/2),(int) centerX - 6,(int) (centerY + ySizeAppearance));
		page.drawLine((int) (centerX -6), (int) (centerY + ySizeAppearance), (int) ((centerX-6)- 10), (int) (centerY + 1.5* ySizeAppearance));
		page.drawLine((int) (centerX -6), (int) (centerY + ySizeAppearance), (int) ((centerX-6) + 10), (int) (centerY + 1.5* ySizeAppearance));
		page.drawLine((int) (centerX -6), (int) (centerY + .5 * ySizeAppearance), (int) ((centerX-6) + 20), (int) (centerY));
		page.drawLine((int) (centerX -6), (int) (centerY + .5 * ySizeAppearance), (int) ((centerX-6) - 20), (int) (centerY));
	}
}
