package calculator;

public class point {
	int x,y,z;
	double xReal,yReal,zReal;
	
	public point() {
	}
	
	public point(double x1,double y1) {
		xReal = x1;
		yReal = y1;
		zReal = 0;
		
		if (Settings.translateGraphsToCenter) {
			xReal += Settings.width/2;
			yReal = -yReal;
			yReal += Settings.height/2;
		}
		
		updateXYZ();
	}
	
	public void updateXYZ() {
		x = (int) Math.round(xReal);
		y = (int) Math.round(yReal);
		z = (int) Math.round(zReal);
	}
}
