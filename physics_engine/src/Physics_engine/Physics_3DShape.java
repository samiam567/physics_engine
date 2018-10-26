package Physics_engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.io.Serializable;
import java.util.ArrayList;


import Physics_engine.Physics_engine_toolbox.faces;
import Physics_engine.Physics_engine_toolbox.pointOfRotationPlaces;

public class Physics_3DShape extends Physics_drawable implements rotatable, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2841230654298992068L;

	private Physics_polygon[] polygons;
	
	//for massive
	private double mass = 10;
	private double friction_coefficient,momentOfInertia;
		
	
	public double elasticity = Settings.elasticity;
	
	private double maxSize; //the distance from the center that the furthest point is 
	
	private double prevXRotation = 0, prevYRotation = 0, prevZRotation = 0;
	double xRotation,yRotation,zRotation,angularVelocityX, angularVelocityY, angularVelocityZ, angularAccelX, angularAccelY, angularAccelZ;

	point pointOfRotation = null; //the point that the object rotates around
	pointOfRotationPlaces pointOfRotationPlace = pointOfRotationPlaces.center;  //the place that that point is
	
	double xSizePrev,ySizePrev,zSizePrev,xSizeInit,ySizeInit,zSizeInit; //used to see if the size has changed
	
	private double initialXDistanceFromPointOfRot,initialYDistanceFromPointOfRot,initialZDistanceFromPointOfRot;
	
	private Polygon_point polyPointsStart;
	
	private int numberOfPoints;
	
	private class Physics_polygon implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 7468997318392934264L;
		private int[] pointIndexes;
		private point[] pPoints;
		
		private Color color;
		private int brightness,index;
		private Vector3D normalVec;
		
		private double centerX,centerY,centerZ;
		
		public Physics_polygon(int[] pointIndexes1) {
			pointIndexes = pointIndexes1;
			index = pointIndexes[0];
		
			for (int i=0; i < pointIndexes.length; i++) {
				pPoints[i] = points[pointIndexes[i]];
			}
			
			Update();
		}
		
		private void Update() {
			calculateCenter();
			calculateNormalVec();
			calculateBrightness();
			calculateColor();
		}
		
		
		private void calculateCenter() {
			centerX = 0;
			centerY = 0;
			centerZ = 0;
			for (point cPoint : pPoints) {
				centerX += cPoint.getXReal();
				centerY += cPoint.getYReal();
				centerZ += cPoint.getZReal();
			}
			centerX /= pPoints.length;
			centerY /= pPoints.length;
			centerZ /= pPoints.length;
		}
		
		
		
		private void calculateNormalVec() {
			normalVec.setIJK(Vector3D.cross(pPoints[0].getXReal() - pPoints[1].getXReal(), pPoints[0].getYReal() - pPoints[1].getYReal(), pPoints[0].getZReal() - pPoints[1].getZReal(), pPoints[0].getXReal() - pPoints[2].getXReal(), pPoints[0].getYReal() - pPoints[2].getYReal(), pPoints[0].getZReal() - pPoints[2].getZReal()));
			normalVec.setPos(centerX,centerY,centerZ);
		}
		
		private void calculateBrightness() {
			brightness = (int) Vector3D.proj(normalVec, new Vector3D(drawer,normalVec.getCenterX()-Settings.lightSource[0],normalVec.getCenterY()-Settings.lightSource[1],normalVec.getCenterZ()-Settings.lightSource[2])).getR();
		}

		private void calculateColor() {
			if (brightness >= 3.3) {
				color = Color.WHITE;
			}else if (brightness >= 3.1) {
				color = Color.LIGHT_GRAY;
			}else if (brightness >= 2.8) {
				color = Color.GRAY;
			}else if (brightness >= 2.6) {
				color = Color.DARK_GRAY;
			}else if (brightness >= 0) {
				color = Color.BLACK;
			}else {
				System.out.println("logic Error for lightLevel: " + brightness);
			}
		}
		
		private Polygon get2DPolygon() {
			Polygon poly = new Polygon();
			for (point cP : pPoints) {
				poly.addPoint(cP.getX(),cP.getY());
			}
			return poly;
		}

		public Color getColor() {
			return color;
		}
	}
	
	private class Polygon_point extends point implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = -2615566272725032727L;
		private double initialXComponent, initialYComponent, initialZComponent;
		
		private Polygon_point nextPoint;

		public Polygon_point(Physics_3DShape physics_3dShape, double x1, double y1, double z1) {
			super(physics_3dShape.drawer, x1, y1, z1);
			xReal = x1;
			yReal = y1;
			zReal = z1;
		}
		
		public void setNext(Polygon_point next) {
			nextPoint = next;
		}
	}
	
	
	private double[] calculateRotation(double x, double y, double angle) {
		double[] polar = Vector2D.rectangularToPolar(x, y);
		return Vector2D.polarToRectangular(polar[0], polar[1] + angle);
	}
	
	public void updatePoints() {	
		Polygon_point cPoint = polyPointsStart;
		int pointCounter = 0;
		double[] rotComponents;
		
			
		double xI = pointOfRotation.getXReal();
		double yI = pointOfRotation.getYReal();
		double zI = pointOfRotation.getZReal();
		
		double shiftX = (centerX-xI) - initialXDistanceFromPointOfRot;
		double shiftY = (centerY-yI) - initialYDistanceFromPointOfRot;
		double shiftZ = (centerZ-zI) - initialZDistanceFromPointOfRot;
	
		updatePointOfRotation();
		
			
		updateSize();
			
		
		if (pointOfRotationPlace != pointOfRotationPlaces.center) {
		//Center Rotation
			center.setPos( (center.getXReal() - xI) , (center.getYReal() - yI) ,(center.getZReal() - zI) );
				
			//zRotation
			rotComponents = calculateRotation(center.getXReal(),center.getYReal(),zRotation - prevZRotation);
			center.setPos(rotComponents[0],rotComponents[1],center.getZReal() );
				
				
			//xRotation
			rotComponents = calculateRotation(center.getZReal(),center.getYReal(),xRotation - prevXRotation);
			center.setPos(center.getXReal(),  rotComponents[1], rotComponents[0]);
			 
			
			//yRotation
			rotComponents = calculateRotation(center.getXReal(),center.getZReal(),yRotation - prevYRotation);
			center.setPos(xI + rotComponents[0]  ,yI + center.getYReal(),zI +  rotComponents[1] );
			setPos(center.getXReal(), center.getYReal(), center.getZReal());
		}
		
		//points rotation

		updateCenter();
			
		double xCI = center.getXReal();
		double yCI = center.getYReal();
		double zCI = center.getZReal();		
		
		try {
			do {
				cPoint.setPos( cPoint.initialXComponent * xSizeAppearance/xSizeInit,cPoint.initialYComponent * ySizeAppearance/ySizeInit,cPoint.initialZComponent * zSizeAppearance/zSizeInit);
		
					
				//zRotation
				rotComponents = calculateRotation(cPoint.getXReal(),cPoint.getYReal(),zRotation);
				cPoint.setPos( rotComponents[0],rotComponents[1],cPoint.getZReal() );
				
					
				//xRotation
				rotComponents = calculateRotation(cPoint.getZReal(),cPoint.getYReal(),xRotation);
				cPoint.setPos(cPoint.getXReal(),  rotComponents[1], rotComponents[0]);
				
				
				//yRotation
				rotComponents = calculateRotation(cPoint.getXReal(),cPoint.getZReal(),yRotation);
				cPoint.setPos(xCI + rotComponents[0],yCI + cPoint.getYReal()  , zCI + rotComponents[1] );
				
				points[pointCounter].setPos(cPoint.getXReal() , cPoint.getYReal(), cPoint.getZReal() );			

				cPoint = cPoint.nextPoint;
					
				pointCounter++;	
			} while (cPoint != null);
			
		}catch(NullPointerException n) {
			System.out.println("bad point updatePoints");
		}
		setPos(xCI,yCI,zCI);
			
		pointOfRotation.setPos(xI, yI, zI);
		
		prevXRotation = xRotation;
		prevYRotation = yRotation;
		prevZRotation = zRotation;
		
		for (Physics_polygon cPoly : polygons) {
			cPoly.Update();
		}
	}

	@Override
	public void paint(Graphics page) {
		for (Physics_polygon cPoly : polygons) {
			page.setColor(cPoly.getColor());
			page.drawPolygon(cPoly.get2DPolygon());
		}
	}
	
public void calculatePointValues() { 
		
		pointConstCenterInitial = new point(drawer,centerX,centerY,centerZ);
				
		Polygon_point cPoint;
		numberOfPoints = points.length;
		try {
			@SuppressWarnings("unused")
			int pointOfRotationx = pointOfRotation.getX();
		}catch(NullPointerException n) {
			pointOfRotation = center;
		}
		
		xSizeInit = xSize;
		ySizeInit = ySize;
		zSizeInit = zSize;
		
		polyPointsStart = new Polygon_point(this,points[0].getXReal(),points[0].getYReal(),points[0].getZReal());
		
		cPoint = polyPointsStart;
		
		cPoint.initialXComponent = cPoint.getXReal() - center.getXReal();
		cPoint.initialYComponent = cPoint.getYReal() - center.getYReal();
		cPoint.initialZComponent = cPoint.getZReal() - center.getZReal();
		
	
		initialXDistanceFromPointOfRot = centerX - center.getXReal();
		initialYDistanceFromPointOfRot = centerY - center.getYReal();
		initialZDistanceFromPointOfRot = centerZ - center.getZReal();
		
		points[0].setPos(cPoint.getXReal(), cPoint.getYReal(), cPoint.getZReal());
		
		double distanceCenter = Physics_engine_toolbox.distance(getCenter(), points[0]);
		
		for (int i = 1; i < points.length; i++) {
			
			if (distanceCenter > maxSize) {
				maxSize = distanceCenter;
			}
			
			cPoint.setNext(new Polygon_point(this,points[i].getXReal(),points[i].getYReal(),points[i].getZReal()));
			
			cPoint = cPoint.nextPoint;
			
			cPoint.initialXComponent = cPoint.getXReal() - center.getXReal();
			cPoint.initialYComponent = cPoint.getYReal() - center.getYReal();
			cPoint.initialZComponent = cPoint.getZReal() - center.getZReal();
			
			
			points[i].setPos(cPoint.xReal, cPoint.yReal, cPoint.zReal);
			
			distanceCenter = Physics_engine_toolbox.distance(getCenter(), points[i]);
			
		}	
	}
	

	@Override
	public void setRotation(double xRotation1, double yRotation1, double zRotation1) {
		xRotation = xRotation1;
		yRotation = yRotation1;
		zRotation = zRotation1;
	}

	public void setAngularVelocity(double angVX, double angVY, double angVZ) {
		angularVelocityX = angVX;
		angularVelocityY = angVY;
		angularVelocityZ = angVZ;
	}
	
	public void setAngularAccel(double angAccelX, double angAccelY, double angAccelZ) {
		angularAccelX = angAccelX;
		angularAccelY = angAccelY;
		angularAccelZ = angAccelZ;
	}
	
	public void setPoints(point[] points1) {
		points = points1;
	}
	

	@Override
	public void setPointOfRotation(point newPointOfRotation) {
		pointOfRotation = newPointOfRotation;
		
		pointOfRotationPlace = pointOfRotationPlaces.custom;
		
		instantiatePointOfRotation();
	
		
	}
	
	private void instantiatePointOfRotation() {
		double xRot = xRotation;
		double yRot = yRotation;
		double zRot = zRotation;
		
		setRotation(0,0,0);
		
		calculatePointValues();
		
		setRotation(xRot,yRot,zRot);
		
	}
	

	@Override
	public void setPointOfRotationPlace(pointOfRotationPlaces newPlace) {
		pointOfRotationPlace = newPlace;

		updatePointOfRotation();
		
		instantiatePointOfRotation();
	
	}

	@Override
	public void updatePointOfRotation() { //this doesn't need to be updated every time the pointOfRotation changes because the pointOfRotation is an alias of the original point. (unless it's a custom pointOfRotation)
		try {
			parent_object = (rotatable) parent_object;
			if (pointOfRotationPlace.equals(pointOfRotationPlaces.center)) {
				pointOfRotation = center;		
			}else if(pointOfRotationPlace.equals(pointOfRotationPlaces.parentCenter)) {
				pointOfRotation = ((rotatable) parent_object).getCenter();
			}else if(pointOfRotationPlace.equals(pointOfRotationPlaces.parentsPlace)) {
				pointOfRotation = ((rotatable) parent_object).getPointOfRotation();
			}
		}catch(ClassCastException c) {
			System.out.println("Parent object not rotatable for child: " + name);
		}
		
		
	}


	public double getXRotation() {
		return xRotation;
	}
	
	public double getYRotation() {
		return yRotation;
	}
	
	public double getZRotation() {
		return zRotation;
	}
	@Override
	public point getCenter() {
		return center;
	}

	public pointOfRotationPlaces getPointOfRotationPlace() {
		return pointOfRotationPlace;
	}

	@Override
	public point getPointOfRotation() {
		return pointOfRotation;
	}

}