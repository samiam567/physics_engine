package Physics_engine;

import java.util.ArrayList;

public class PolarObject extends Physics_3DPolygon {
	
	public PolarObject(object_draw drawer1, double x1, double y1, double z1, double size,String shape) {
		super(drawer1);
		drawMethod = "paint";
		setPos(x1,y1,z1);
		setSize(size,size,size);
		setRotation(0,0,0);
		
		points = new point[(int) (2*Math.PI/Settings.thetaStep )];
		
		
		ArrayList<point> pointsAL = new ArrayList<point>();
		
		double t,q;
		if (shape == "heart") {
			for (int i = 0; i < points.length; i ++) {
				t = i * Settings.thetaStep;		
				pointsAL.add(heart(t));
			}

		}else {
			for (int i = 0; i < points.length; i ++) {
				t = i * Settings.thetaStep;		
				
				
				for (int z = 0; z < points.length; z++) {
					q = z * Settings.thetaStep;
					
					switch(shape) {
					
					case("thing1"):
						pointsAL.add(thing1(t,q));
						break;
					
					case("sphere"):
						pointsAL.add(sphere(t,q));
						break;
					case("circle"):
						pointsAL.add(circle(t));
					break;
					
					case ("thing2"):
						pointsAL.add(thing2(t,q));
						break;
					}
				}
				
	
			}
		}
		
		
		points = new point[pointsAL.size()];
		
		for (int i = 0; i < points.length; i++) {
			points[i] = pointsAL.get(i);
		}
		
		setPoints(points);
		
		
		calculateCenter();
		
		updatePointOfRotation();
		
		calculatePointValues(); 

		setPos(x1,y1,z1);
		
		setMass(10);
	}
	
	public PolarObject(object_draw drawer1, double x1, double y1, double z1, double size,String shape,double accuracy,double length) {
		super(drawer1);
		drawMethod = "paint";
		setPos(x1,y1,z1);
		setSize(size,size,size);
		setRotation(0,0,0);
		
		points = new point[(int) (length/accuracy)];
		
		
		ArrayList<point> pointsAL = new ArrayList<point>();
		
		double t,q;
		if (shape == "heart") {
			for (int i = 0; i < points.length; i ++) {
				t = i * accuracy;		
				pointsAL.add(heart(t));
			}

		}else {
			for (int i = 0; i < points.length * 5; i ++) {
				t = i * accuracy/5;		
				
				
				for (int z = 0; z < points.length; z++) {
					q = z * accuracy;
					
					switch(shape) {
					
					case("thing1"):
						pointsAL.add(thing1(t,q));
						break;
					
					case("sphere"):
						pointsAL.add(sphere(t,q));
						break;
					case("circle"):
						pointsAL.add(circle(t));
					break;
					
					case ("thing2"):
						pointsAL.add(thing2(t,q));
						break;
					}
				}
				
	
			}
		}
		
		points = new point[pointsAL.size()];
		
		for (int i = 0; i < points.length; i++) {
			points[i] = pointsAL.get(i);
		}
		
		setPoints(points);
		
		
		calculateCenter();
		
		updatePointOfRotation();
		
		calculatePointValues(); 

		setPos(x1,y1,z1);
		
		setMass(10);
	}
	
	//shapes
	
	public point heart(double t) {
		double x1 =  16 * Math.pow(Math.sin(t), 3);
		double y1 =  13*Math.cos(t) - 5*Math.cos(2*t) - 2*Math.cos(3*t) - Math.cos(4*t);
		return  new point(drawer,centerX + x1,centerY - y1 ,centerZ );
	}
	
	public point circle(double t) {
		double x1 = xSize * Math.cos(t);
		double y1 = ySize * Math.sin(t);
		return  new point(drawer,centerX + x1,centerY - y1 ,centerZ );
	}
	
	
	public point thing1(double t, double z) {
		double x1 =50 * Math.cos(t) * Math.cos(z);
		double y1 =50 * Math.cos(t) * Math.sin(z);
		double z1 =50 * t;
		return  new point(drawer,centerX + x1,centerY - y1 ,centerZ + z1 );
	}
	
	public point sphere(double t, double z) {
		double x1 =xSize * Math.sin(t) * Math.cos(z);
		double y1 =ySize * Math.sin(t) * Math.sin(z);
		double z1 =zSize * Math.cos(t);
		return  new point(drawer,centerX + x1,centerY - y1 ,centerZ + z1 );
	}
	
	public point thing2(double z, double t) {
		double x1 = xSize * Math.cos(z);
		double y1 =ySize *  Math.sin(z);
		double z1 = zSize * Math.sin(t);
		return  new point(drawer,centerX + x1,centerY - y1 ,centerZ + z1 );
	}
}
