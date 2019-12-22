package Physics_engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Physics_engine.Physics_engine_toolbox.pointOfRotationPlaces;

public abstract class Physics_3DShape extends Physics_drawable implements rotatable, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2841230654298992068L;

	private Physics_polygon[] polygons;
	private ArrayList<pPolyCompat> polygonsAList = new ArrayList<pPolyCompat>();
	
	//for massive
	private double mass = 10;
	private double friction_coefficient,momentOfInertia;
		
	
	public double elasticity = Settings.elasticity;
	
	private double maxSize; //the distance from the center that the farthest point is 
	
	private double prevXRotation = 0, prevYRotation = 0, prevZRotation = 0;
	double xRotation,yRotation,zRotation,angularVelocityX, angularVelocityY, angularVelocityZ, angularAccelX, angularAccelY, angularAccelZ;

	point pointOfRotation = null; //the point that the object rotates around
	pointOfRotationPlaces pointOfRotationPlace = pointOfRotationPlaces.center;  //the place that that point is
	
	double xSizePrev,ySizePrev,zSizePrev,xSizeInit,ySizeInit,zSizeInit; //used to see if the size has changed
	
	private double initialXDistanceFromPointOfRot,initialYDistanceFromPointOfRot,initialZDistanceFromPointOfRot;
	
	private Polygon_point polyPointsStart;
	
	private int numberOfPoints;
	
	private double delta;
	private boolean isRotatable = true;
	
	private class Physics_polygon implements Serializable,pPolyCompat {
		/**
		 * 
		 */
		private static final long serialVersionUID = 7468997318392934264L;
		private int[] pointIndexes;
		private point[] pPoints;
		
		private Color color;
		private float brightness;
		private Vector3D normalVec;
		
		private double centerX,centerY,centerZ,t,q;
		
		private Physics_3DShape parentshape;
		
		public Physics_polygon(Physics_3DShape parent,point[] points1) {
			parentshape = parent;
			pPoints = points1;
			t = ((Polygon_point)pPoints[0]).getT();
			q = ((Polygon_point)pPoints[0]).getQ();
			normalVec = new Vector3D(drawer,1,1,1);
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
		
		//input partials for calculation of the normal vector
		private Vector3D setNormalVec(Vector3D normalVec, double t, double q) {
				
			//finding partials
			double[] partialsT = new double[3], partialsQ = new double[3];
			
			double[] partials1 = equation(t,q);
			double[] partialsTadj = equation(t+0.0001,q);
			double[] partialsQadj = equation(t,q+0.0001);
			for(int i = 0; i < partials1.length; i++) {
				partialsT[i] =   (partialsTadj[i] - partials1[i])/0.0001;
				partialsQ[i] =  (partialsQadj[i] - partials1[i])/0.0001;
			}
			
			normalVec.setIJK(partialsT[1]*partialsQ[2]-partialsQ[1]*partialsQ[2],-(partialsT[0]*partialsQ[2]-partialsT[2]*partialsQ[0]),partialsT[0]*partialsQ[2]-partialsQ[0]*partialsT[2]);
			
			return normalVec;
		}

		
		private void calculateNormalVec() {
			setNormalVec(normalVec,t,q);
			normalVec.setPos(centerX,centerY,centerZ);
			normalVec.divide(normalVec.getR());
			
		}
		
		private void calculateBrightness() {
			Vector3D lightVec = new Vector3D(drawer,normalVec.getCenterX()-Settings.lightSource[0],normalVec.getCenterY()-Settings.lightSource[1],normalVec.getCenterZ()-Settings.lightSource[2]);
			lightVec.divide(lightVec.getR());
			brightness = (float) Vector3D.proj(lightVec,normalVec).getR();

		}

		private void calculateColor() {
			
			int alpha = 80; // 1/transparency of the shape
			color = Color.getHSBColor(parentshape.getColor().getRGB(),1f, brightness );
			color = new Color(color.getRed(),color.getGreen(),color.getBlue(),alpha);

		}
		
		public Polygon get2DPolygon() {
			Polygon poly = new Polygon();
			for (point cP : pPoints) {
				poly.addPoint(cP.getX(),cP.getY());
			}
			return poly;
		}

		public Color getColor() {
			return color;
		}

		public Physics_drawable getNormalVec() {
			return normalVec;
		}
	}
	
	private class Polygon_point extends point implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = -2615566272725032727L;
		private double initialXComponent, initialYComponent, initialZComponent;
		private double t,q;
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

		public void setTQ(double t2, double q2) {
			t = t2;
			q = q2;
		}
		public double getT() {
			return t;
		}
		public double getQ() {
			return q;
		}
	}
	
	
	protected double[] equation(double t, double z) {
		double x1 = xSize * Math.sin(t);
		double y1 = ySize * Math.cos(t);
		double z1 = zSize *z;
		
		
		return  new double[] {x1,y1 ,z1};
	}
	
	private double[] equation(double x, double y, double dX, double dY) {
		return equation (x+dX,y+dY);
	}
	

	
	public Physics_3DShape(object_draw drawer1) {
		super(drawer1);
	}
	
	public void construct( double x1, double y1, double z1, double xSize1, double ySize1, double zSize1, double delta1) {
		boolean displayProgress = true;
		delta = delta1;
		setPos(x1,y1,z1);
		setSize(xSize1,ySize1,zSize1);
		
		int pointsPerPolygon = 3;
		
		
		if (displayProgress) System.out.println("creatingPoints");
		//creating points
		double[] pointCoord = equation(0,0);
		Polygon_point cPoint = new Polygon_point(this,pointCoord[0],pointCoord[1],pointCoord[2]);
		cPoint.setTQ(0,0);
		polyPointsStart = cPoint;
		int pointCount = 0;
		for (double t = -xSize/2; t < xSize/2; t += delta) {
			for (double q = -ySize/2; q < ySize/2; q+= delta) {
				pointCoord = equation(t,q);
				cPoint.setNext(new Polygon_point(this,pointCoord[0],pointCoord[1],pointCoord[2]));
				cPoint.setTQ(t,q);
				cPoint = cPoint.nextPoint;
				pointCount++;
			}
		}
	
		if (displayProgress) System.out.println("creatingPointsList");
		//creating points list
		points = new Polygon_point[(int) ((pointsPerPolygon) * pointCount)];
		cPoint = polyPointsStart;
		int pointCounter = 0;
		do {
			points[pointCounter] = cPoint;
			cPoint = cPoint.nextPoint;
			pointCounter++;
		}while(cPoint.nextPoint != null);
		
	
		if (displayProgress) System.out.println("creatingPolygons");
		//creating polygons
		int pointCount2 = pointCount;
		Polygon_point[] pPoints;
		polygons = new Physics_polygon[pointCount];
		for (int i = 0; i < pointCount; i++) {
			pPoints = new Polygon_point[pointsPerPolygon];
			pPoints[0] = (Polygon_point) points[i];
			
			
			for (int p = 0; p < pPoints.length-1; p++) {
				try {
					
					pointCoord = equation(((Polygon_point) points[i]).getT(),((Polygon_point) points[i]).getQ(),delta * Math.cos(p * 90/(pointsPerPolygon-1)), delta * Math.sin(p * 90/(pointsPerPolygon-1)));
					pPoints[p+1] = new Polygon_point(this,pointCoord[0],pointCoord[1],pointCoord[2]);
					points[pointCount2] = pPoints[p+1];
					pointCount2++;
				}catch(Exception e) {
					e.printStackTrace();
					Polygon_point[] pPointsTemp = pPoints;
					pPoints = new Polygon_point[pPointsTemp.length-1];
					for (int h = 0; h < pPoints.length; h++) {
						pPoints[h] = pPointsTemp[h];
					}
				}
			}
			
			
			if (pPoints.length >= 3) {
				polygons[i] = new Physics_polygon(this,pPoints);
				polygonsAList.add((pPolyCompat) polygons[i]);
			}else { 
				System.out.println("Invalid polygon. Not enough points.  Points:" + pPoints.length + "  Indx:" + i);
			}
		}
		
		if (displayProgress) System.out.println("re-creating points list");
		//re-creating points list
			point[] points_temp = points;
			points = new Polygon_point[pointCount2];
			
			//do the first one 
			points[0] = points_temp[0];
			((Polygon_point) points[0]).setNext((Polygon_point) points_temp[1]);
	
			//do the rest 
			for (int i = 1; i < points.length; i++) {
				points[i] = points_temp[i];
				((Polygon_point) points[i-1]).setNext((Polygon_point) points_temp[i]); //link em up
			}
			
		setPos(x1,y1,z1);
		
		for (point curentPoint : points) {
			curentPoint.setPos(curentPoint.getXReal() + getCenterX(), curentPoint.getYReal() + getCenterY(), curentPoint.getZReal() + getCenterZ());
		}
			
		calculatePointValues();
	
		System.out.println("Construction finished");
	}
	
	
	public void Update(double frames) {
	
		updatePoints();

		for (Physics_polygon cPoly : polygons) {
			cPoly.Update();
		}
	
		//sorting polygons by z distance ----------------------------------
				Collections.sort( polygonsAList, new Comparator<pPolyCompat>() {
			     
			        public int compare(pPolyCompat o1, pPolyCompat o2) {
			            return Double.compare(o1.getNormalVec().getZReal(), o2.getNormalVec().getZReal());
			        }

			
			    });
			//----------------------------------------------------------------

		
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
		for (pPolyCompat cPoly : polygonsAList) {
	
			page.setColor(cPoly.getColor());
			page.fillPolygon(cPoly.get2DPolygon());
			
		}
	}

	public void calculatePointValues() { 
		
		//pointConstCenterInitial = new point(drawer,centerX,centerY,centerZ);
				
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

	@Override
	public double getAngularVelocityX() {
		return angularVelocityX;
	}

	@Override
	public double getAngularVelocityY() {
		return angularVelocityY;
	}

	@Override
	public double getAngularVelocityZ() {
		return angularVelocityZ;
	}

	@Override
	public double getAngularAccelX() {
		return angularAccelX;
	}

	@Override
	public double getAngularAccelY() {
		return angularAccelY;
	}

	@Override
	public double getAngularAccelZ() {
		return angularAccelZ;
	}

	@Override
	public boolean getIsRotatable() {
		return isRotatable;
	}

}
