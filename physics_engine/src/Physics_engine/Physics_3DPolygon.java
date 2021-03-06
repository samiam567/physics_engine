package Physics_engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.util.ArrayList;
import Physics_engine.Physics_engine_toolbox.faces;
import Physics_engine.Physics_engine_toolbox.pointOfRotationPlaces;

public class Physics_3DPolygon extends Physics_shape implements pointed, rotatable, massive {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -630954532958631113L;
	int[] pointXs = {}; //all of the x coordinates of the points in the object
	int[] pointYs = {}; //all of the y coordinates of the points in the object
	int[] pointZs = {}; //all of the y coordinates of the points in the object
	
	protected double[] pointXReals = {}; //all of the x coordinates of the points in the object
	protected double[] pointYReals = {}; //all of the y coordinates of the points in the object
	protected double[] pointZReals = {}; //all of the y coordinates of the points in the object
	
	protected int[] pointRenderOrder = {}; //the order in which lines will be drawn from point to point (for listedPointAlgorithm)
	
	public Polygon_point polyPointsStart; //the start of the linked list of polyPoints
	
	//for massive
	private double mass = 10;
	private double friction_coefficient,momentOfInertia;
		
	private Polygon polyXY, polyZY;
	private PArea areaXY,areaZY;
	
	public double elasticity = Settings.elasticity;
	
	private double maxSize; //the distance from the center that the furthest point is 
	
	private Vector3D VecRotation, VecAngularVelocity, VecAngularAccel,prevVectorRotation;
	private double prevXRotation = 0, prevYRotation = 0, prevZRotation = 0;
	double xRotation,yRotation,zRotation,angularVelocityX, angularVelocityY, angularVelocityZ, angularAccelX, angularAccelY, angularAccelZ;
	public boolean isRotatable = true,isTangible = true, affectedByBorder = true,calculateCenter = true;
	protected boolean hasNormalCollisions = true;
	private int numberOfPoints;
	
	private boolean momentOfInertiaCalculated = false; 
	
	private point pointConstCenterInitial; //where the center was the last time the point constants were updated
	
	point pointOfRotation = null; //the point that the object rotates around
	pointOfRotationPlaces pointOfRotationPlace = pointOfRotationPlaces.center;  //the place that that point is
	
	double xSizePrev,ySizePrev,zSizePrev,xSizeInit,ySizeInit,zSizeInit; //used to see if the size has changed
	
	@SuppressWarnings("unused")
	private double initialXDistanceFromPointOfRot,initialYDistanceFromPointOfRot,initialZDistanceFromPointOfRot;
	
	class Polygon_point extends point {
		/**
		 * 
		 */
		private static final long serialVersionUID = -2615566272725032727L;
		
		private double initialXComponent, initialYComponent, initialZComponent;
		
		private Polygon_point[] closestPoints = new Polygon_point[2];
		
		private Color color = Color.black;
		
		@SuppressWarnings("unused")
		private Physics_3DPolygon parent;
		
		private Polygon_point nextPoint;
		
		private boolean selectedForClosest = false;
		
		public Polygon_point(Physics_3DPolygon parent, double x1, double y1, double z1) {
			super(parent.drawer, x1, y1, z1);
			xReal = x1;
			yReal = y1;
			zReal = z1;
			
			this.parent = parent;
			
			prevVectorRotation = new Vector3D(drawer,0.0000001,0.0000001,0.0000001);
			VecRotation = new Vector3D(drawer,0.0000001,0.0000001,0.0000001);
			VecRotation.setName("rotationVec", 1);
			VecAngularVelocity = new Vector3D(drawer,0,0,0);
			VecAngularAccel = new Vector3D(drawer,0,0,0);
		}
		
		public void setNext(Polygon_point next) {
			nextPoint = next;
		}
	}
	
	public Physics_3DPolygon(object_draw drawer1) {
		super(drawer1);
		points = new Polygon_point[0]; //all of the points in the object
		updatePolygons();
	}
	
	public boolean getIsTangible() {
		return isTangible;
	}

	public point getCenter() {
		return center;
	}
	
	public double getMass() {
		return mass;
	}
	
	protected void updateIsInFrame() {
		setIsInFrame(drawer.frame.checkIsInFrame(this));
	}
	
	public pointOfRotationPlaces getPointOfRotationPlace() {
		return pointOfRotationPlace;
	}
	
	public void paint(Graphics page) {	
		if (Settings.displayObjectNames) page.drawString(name,(int) Math.round(points[0].getXReal()), (int) Math.round(points[0].getYReal())); //displaying the name of the object
		
		
		if (Settings.showPointNumbers) {
			point current_point;
			for (int i = 0; i < points.length; i++) {
				current_point = points[i];
				page.drawString("" + i, current_point.getX(), current_point.getY()); //display the point numbers
			}
		}		
		
		

		if (isFilled) {
			page.fillPolygon(polyXY);
		}else if (isShaded){
			
			Polygon_point cPoint = polyPointsStart;
			do {
				try {
					
					page.setColor(cPoint.color);
					
					
					try {
						//page.drawLine(cPoint.x,cPoint.y,cPoint.nextPoint.x,cPoint.nextPoint.y);
						page.fillPolygon(new int[] {cPoint.getX(), cPoint.closestPoints[0].getY(), cPoint.closestPoints[1].getX()},new int[] {cPoint.getY(), cPoint.closestPoints[0].getY(), cPoint.closestPoints[1].getY()},3);
					}catch(NullPointerException n) {	
						page.drawLine(cPoint.getX(),cPoint.getY(),polyPointsStart.getX(),polyPointsStart.getY());
					}
					
					cPoint = cPoint.nextPoint;
				}catch(NullPointerException n) {
					System.out.println("bad point paint");
				}
				
				
			} while (cPoint != null);
		}else {
			page.drawPolygon(polyXY);
		}
		
	
	
	}
	
	public void updateAreas() {
		updatePolygons();
		
		areaXY = new PArea(polyXY);
		areaZY = new PArea(polyZY);
	}
	
	public void calculateCenter() {
		if (calculateCenter) {
		
			// the sums of all the x,y,and z coordinates of the points
			double totalX = 0;
			double totalY = 0;
			double totalZ = 0;
			
			for (point cPoint : points) { //loop through the points and add their coordinates to the totals
				totalX += cPoint.getXReal();
				totalY += cPoint.getYReal();
				totalZ += cPoint.getZReal();
			}
	
			//divide by the number of points to get the average
			centerX = totalX / points.length;
			centerY = totalY / points.length;
			centerZ = totalZ / points.length;
		}else {
			centerX = xReal + xSize/2;
			centerY = yReal + ySize/2;
			centerZ = zReal + zSize/2;
		}
		
		updateCenter();
	}
	
	public void updatePolygons() {
		
		try {
			polyXY.xpoints = pointXs;
			polyXY.ypoints = pointYs;
			polyXY.npoints = pointXs.length;
			
			polyZY.xpoints = pointZs;
			polyZY.ypoints = pointYs;
			polyXY.npoints = pointYs.length;
			
		
		}catch(NullPointerException n) { //if the polys don't exist, then create them
			polyXY = new Polygon();
			polyZY = new Polygon();
			
			polyXY.xpoints = pointXs;
			polyXY.ypoints = pointYs;
			polyXY.npoints = pointXs.length;
			
			polyZY.xpoints = pointZs;
			polyZY.ypoints = pointYs;
			polyXY.npoints = pointYs.length;
		}
	}
	
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

	public void updatePointXsYsAndZs() {
		
		try {
			for (int i = 0; i < points.length; i++) {
				pointXs[i] = (int) Math.round(points[i].getX() * Settings.pixelConversion);
				pointYs[i] = (int) Math.round(points[i].getY() * Settings.pixelConversion);
				pointZs[i] = (int) Math.round(points[i].getZ() * Settings.pixelConversion);
				
				pointXReals[i] = points[i].getXReal() * Settings.pixelConversion;
				pointYReals[i] = points[i].getYReal() * Settings.pixelConversion;
				pointZReals[i] = points[i].getZReal() * Settings.pixelConversion;
			}
		}catch(ArrayIndexOutOfBoundsException a) {
			pointXs = new int[points.length];
			pointYs = new int[points.length];
			pointZs = new int[points.length];
			
			pointXReals = new double[points.length];
			pointYReals = new double[points.length];
			pointZReals = new double[points.length];
			
			for (int i = 0; i < points.length; i++) {
				pointXs[i] = (int) Math.round(points[i].getX() * Settings.pixelConversion);
				pointYs[i] = (int) Math.round(points[i].getY() * Settings.pixelConversion);
				pointZs[i] = (int) Math.round(points[i].getZ() * Settings.pixelConversion);
				
				pointXReals[i] = points[i].getXReal() * Settings.pixelConversion;
				pointYReals[i] = points[i].getYReal() * Settings.pixelConversion;
				pointZReals[i] = points[i].getZReal() * Settings.pixelConversion;
			}
		}catch(NullPointerException n) { //object has not finished being made
			
		}
	}
	
	private Vector3D vec1 = new Vector3D(drawer,0,0,0);	
	private Vector3D vec2 = new Vector3D(drawer,0,0,0);
	public void updateShading(Polygon_point cPoint) {
			
		vec1.set(cPoint, cPoint.closestPoints[1]);
		vec2.set(cPoint, cPoint.closestPoints[0]);
		
		Vector3D normalVec = Vector3D.cross(vec1, vec2);
	
		Vector3D lightVec = new Vector3D(drawer,normalVec.getCenterX()-Settings.lightSource[0],normalVec.getCenterY()-Settings.lightSource[1],normalVec.getCenterZ()-Settings.lightSource[2]);
		lightVec.divide(lightVec.getR());
		float lightLevel = (float) Vector3D.proj(lightVec,normalVec).getR();
		
		 
		
	
		cPoint.color = Color.getHSBColor(0.3f,0.7f, (float) (1-lightLevel) );
		
	}



	public void updatePoints() { 
		
		if (isRotatable){
	
			Polygon_point cPoint = polyPointsStart;
			int pointCounter = 0;
			
			double xI = pointOfRotation.getXReal();
			double yI = pointOfRotation.getYReal();
			double zI = pointOfRotation.getZReal();
		
	
			updatePointOfRotation();
			
			updateSize();
			
			
			if (pointOfRotationPlace != pointOfRotationPlaces.center) {
				
				
				//Center Rotation (about the point of rotation)
				center.setPos( (center.getXReal() - xI) , (center.getYReal() - yI) ,(center.getZReal() - zI) );
				
				center.rotate(new double[] {(xRotation-prevXRotation) * parallaxValue,(yRotation-prevYRotation) * parallaxValue,(zRotation-prevZRotation) * parallaxValue});
				
				
				center.rotate(Vector3D.multiply(Vector3D.add(getVectorRotation(),Vector3D.multiply(prevVectorRotation, -1)), parallaxValue));
			
				
				center.setPos(xI + center.getXReal(), yI + center.getYReal(), zI + center.getZReal());	
				
				prevVectorRotation.setIJK(getVectorRotation().getI(),getVectorRotation().getJ(),getVectorRotation().getK());
			}
		
		//points rotation
			
			center.updatePos();
			
			double xCI = center.getXReal();
			double yCI = center.getYReal();
			double zCI = center.getZReal();
			
			//rotation of each point around the center of the object 
			do {
				try {
			
					//rectangularRotation
					cPoint.setPos( cPoint.initialXComponent * xSizeAppearance/xSizeInit,cPoint.initialYComponent * ySizeAppearance/ySizeInit,cPoint.initialZComponent * zSizeAppearance/zSizeInit);	
					cPoint.rotate(new double[] {xRotation,yRotation,zRotation});
					
					
					//vector Rotation
					cPoint.rotate(getVectorRotation());
					
					
					
					cPoint.setPos(xCI + cPoint.getXReal(),yCI + cPoint.getYReal()  , zCI + cPoint.getZReal());
					
					points[pointCounter].setPos(cPoint.getXReal() , cPoint.getYReal(), cPoint.getZReal() );			
					
					
					if (isShaded) {
						updateShading(cPoint);
					}
					
					cPoint = cPoint.nextPoint;
					
					pointCounter++;
					
					
				}catch(NullPointerException n) {
					System.out.println("bad point updatePoints");
				}
				
				
			} while (cPoint != null);
			
			setPos(xCI,yCI,zCI);
			
		
			pointOfRotation.setPos(xI, yI, zI);
	
			
			prevXRotation = xRotation;
			prevYRotation = yRotation;
			prevZRotation = zRotation;
			
		}else {
			double xChange = xReal - points[0].getX();
			double yChange = yReal - points[0].getY();
			double zChange = zReal - points[0].getZ();
		
			
			for (int i = 0; i < points.length; i++) {
				points[i].setPos(points[i].getXReal() + xChange, points[i].getYReal() + yChange, points[i].getZReal() + zChange);
			}
			
		}
		
	
	}

	public void setRotation(double xRotation1, double yRotation1, double zRotation1) { //this is not a wise method to use as it frequently results in impossible rotations.
		xRotation = xRotation1;
		yRotation = yRotation1;
		zRotation = zRotation1;
	}
	
	public void setPointOfRotation(point newPointOfRotation) {
		pointOfRotation = newPointOfRotation;
		
		pointOfRotationPlace = pointOfRotationPlaces.custom;
		
		instantiatePointOfRotation();
	
	}
	
	public void instantiatePointOfRotation() {
		double xRot = xRotation;
		double yRot = yRotation;
		double zRot = zRotation;
		
		setRotation(0,0,0);
		
		calculatePointValues();
		
		setRotation(xRot,yRot,zRot);
		
	}
	
	public void setPointOfRotationPlace(pointOfRotationPlaces newPlace) {
		pointOfRotationPlace = newPlace;

		updatePointOfRotation();
		
		instantiatePointOfRotation();
	
	}
	
	private void updateMomentOfInertia() {
		System.out.println("updating moment of inertia");
		if (isRotatable){
			calculateCenter();

			Polygon_point cPoint = polyPointsStart,nextPoint;

			double dA,dB,dAB,area,theta,momInertia = 0,r,totalArea = 0;
			
			//getting the total area
			do {
				try {
					nextPoint = cPoint.nextPoint;
					if (nextPoint.equals(null)) System.out.println("bad");
				}catch(NullPointerException n) { 
					nextPoint = polyPointsStart;
				}				
				
				
				dA = Math.abs(Physics_engine_toolbox.distance(cPoint, center));
				dB = Math.abs(Physics_engine_toolbox.distance(nextPoint, center));
				dAB =  Math.abs(Physics_engine_toolbox.distance(cPoint, nextPoint));
				
				theta = Math.acos(-((dAB)*(dAB)-(dB)*(dB)-(dA)*(dA))/((2)*(dB)*(dA)));
						
				area = Math.abs(0.5 * (dA) * (dB) * Math.sin(theta));
			
				totalArea += area;
				
				cPoint = cPoint.nextPoint;
				
			} while (cPoint != null);
			
			
			cPoint = polyPointsStart;
			
			
			//calculating moment of inertia
			do {

				try {
					nextPoint = cPoint.nextPoint;
					if (nextPoint.equals(null)) System.out.println("bad");
				}catch(NullPointerException n) { 
					nextPoint = polyPointsStart;
				}				
				
			
				dA = Math.abs(Physics_engine_toolbox.distance(cPoint, center));
				dB = Math.abs(Physics_engine_toolbox.distance(nextPoint, center));
				dAB =  Math.abs(Physics_engine_toolbox.distance(cPoint, nextPoint));
				
				theta = Math.acos(-((dAB)*(dAB)-(dB)*(dB)-(dA)*(dA))/((2)*(dB)*(dA)));
						
				area = 0.5 * (dA) * (dB) * Math.sin(theta);
				
				r = area/theta;
				
				momInertia += Math.abs(area * Math.pow(r/2, 2) * mass / totalArea);
				
				cPoint = cPoint.nextPoint;
				
			} while (cPoint != null);
			momentOfInertia = momInertia;
			
			momentOfInertiaCalculated = true;
			
		}else {
			System.out.println(name + " is not rotatable (updateMomentOfInertia)");
		}
	}
	
	public point[] getPoints() {
		return points;
	}

	public Polygon getPolyXY() {
		return polyXY;
	}
	
	public Polygon getPolyZY() {
		return polyZY;
	}
	
	public Area getAreaXY() {
		
		return areaXY;
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
	
	public Area getAreaZY() {
	
		return areaZY;
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
	
	public void calculateClosestPoints() {
		
		//constructing PolyPoints list
		ArrayList<Polygon_point> polyPoints = new ArrayList<Polygon_point>(numberOfPoints);
		Polygon_point cPoint = polyPointsStart;
		do {
			cPoint.selectedForClosest = false;
			polyPoints.add(cPoint);
			cPoint = cPoint.nextPoint;
		}while(cPoint != null);
		
		cPoint = polyPointsStart;
		
		double p1d,p2d,cPd; //their respective distances from the current point and the sub_current point's distance from the current point
		
		do {
			
			Polygon_point p1 = null,p2 = null; // the smallest points
			p1d = 1000000000;
			p2d = 1000000000;
			
			for (Polygon_point cP : polyPoints) {
				
				cPd = Physics_engine_toolbox.distance(cPoint, cP);
				
				if ((cPd != 0) && (! cP.selectedForClosest)) {
					if (cPd < p1d) { // takes first spot
						
						if (p1d < p2d) {
							p2 = p1;
							p2d = p1d;
						}
						p1 = cP;
						p1d = cPd;
					}else if (cPd < p2d) { // takes second spot
						p2 = cP;
						p2d = cPd;
					}
					
					
				}
			}
			
			
			
			cPoint.closestPoints[0] = p1;
			cPoint.closestPoints[1] = p2;
			
			try {
				p1.equals(null);
			}
			catch(NullPointerException w) {
				
				for (Polygon_point cP : polyPoints) {
					cP.selectedForClosest = false;
				}
				
				try{
					p2.equals(null);
					p2.selectedForClosest = true;
					
					for (Polygon_point cP : polyPoints) {
						cPd = Physics_engine_toolbox.distance(cPoint, cP);
						
						if ((cPd != 0) ) {
							if (cPd < p1d) { // takes first spot
								
								
								p1 = cP;
								p1d = cPd;
							}
						}
					}
				}catch(NullPointerException n) {
				
					for (Polygon_point cP : polyPoints) {
						cPd = Physics_engine_toolbox.distance(cPoint, cP);
						
						if ((cPd != 0) ) {
							if (cPd < p1d) { // takes first spot
								
								if (p1d < p2d) {
									p2 = p1;
									p2d = p1d;
								}
								p1 = cP;
								p1d = cPd;
							}else if (cPd < p2d) { // takes second spot
								p2 = cP;
								p2d = cPd;
							}
							
							
						}
					}
				}		
			}
			
			p1.selectedForClosest = true;
			p2.selectedForClosest = true;
			
		
			cPoint = cPoint.nextPoint;
		}while(cPoint != null);
		
	}
	
	public void updatePointConstants() {
		updateCenter();
		
		Polygon_point cPoint = polyPointsStart;
		double[] rotation = {getXRotation(),getYRotation(),getZRotation()};
		setRotation(0,0,0);
		updatePoints();
		setRotation(rotation[0],rotation[1],rotation[2]);
		do {	
	
			cPoint.initialXComponent = cPoint.getXReal() + (centerX - pointConstCenterInitial.getXReal()) - pointOfRotation.getXReal();
			cPoint.initialYComponent = cPoint.getYReal() + (centerY - pointConstCenterInitial.getYReal()) - pointOfRotation.getYReal();
			cPoint.initialZComponent = cPoint.getZReal()  + (centerZ - pointConstCenterInitial.getZReal()) - pointOfRotation.getZReal();
			
			cPoint = cPoint.nextPoint;
			
		}while(cPoint != null);
		
		pointConstCenterInitial.setPos(centerX,centerY,centerZ);
		
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
		
		if (isShaded) calculateClosestPoints(); 
		
	}
	
	public void collision(massive cObject, point contactPoint, faces side) {
		try {
			cObject = (border_bounce) cObject;
		}catch(ClassCastException c) {
			massive cOb = cObject;
			System.out.println(name + " has hit " + cObject.getObjectName());
			
			
			isCollided((physics_object) cObject,side);
			
			cObject.isCollided(this, side);
	
		
				//bouncing
				if (isRotatable) setAngularVelocity(0,0,(((contactPoint.getXReal() - center.getXReal()) * ( cObject.getYSpeed() * cObject.getMass() - ySpeed * getMass()) / getMomentOfInertia())) * 10 - (((-contactPoint.getYReal() + center.getYReal()) * (cObject.getXSpeed()-xSpeed) / getMomentOfInertia())) * 10);
				
				setSpeed(((mass - cOb.getMass())/(mass+cOb.getMass())) + ((2*cOb.getMass())/(mass + cOb.getMass())) * (cOb.getXSpeed() - getXSpeed()),((mass - cOb.getMass())/(mass+cOb.getMass())) + ((2*cOb.getMass())/(mass + cOb.getMass())) * ( cOb.getYSpeed() - getYSpeed()) ,((mass - cOb.getMass())/(mass+cOb.getMass())) + ((2*cOb.getMass())/(mass + cOb.getMass())) * ( cOb.getZSpeed() - getZSpeed()) );}
			
		}
	
	
	public void checkForCollision(massive current_physics_object,ArrayList<massive> objects) { 
	
		if (Settings.collision_algorithm == 5) {
			
			if ((Physics_engine_toolbox.distance(center, current_physics_object.getCenter()) < maxSize + current_physics_object.getMaxSize() + 1 + 100 * Settings.frameStep)) {
				
				point cPoint;
			
				try {
					
					faces side = faces.none;
					for (int i = 0; i < ((pointed) current_physics_object).getPoints().length; i++) {
						cPoint = ((pointed) current_physics_object).getPoints()[i];
						
						
						if (cPoint.isIn(this)) {
						
							
						
							if (getHasNormalCollisions() && current_physics_object.getHasNormalCollisions()) {
								collision(current_physics_object,cPoint, side);
								current_physics_object.collision(this,cPoint, side);
								
								current_physics_object.setPos(current_physics_object.getXSpeed()*drawer.getFrameStep() + current_physics_object.getCenterX(), current_physics_object.getYSpeed()*drawer.getFrameStep() + current_physics_object.getCenterY(), current_physics_object.getZSpeed()*drawer.getFrameStep() + current_physics_object.getCenterZ());
								
								setPos(getXSpeed()*10*drawer.getFrameStep() + getCenterX(), getYSpeed()*10*drawer.getFrameStep() + getCenterY(), getZSpeed()*10*drawer.getFrameStep() + getCenterZ());
							}else {
								isCollided((physics_object) current_physics_object,side);
								
								current_physics_object.isCollided(this, side);
							}
							
							
							break;
						}
					}
				}catch(ClassCastException c) {
					System.out.println("catch: " + name);
				}catch(NullPointerException n) {				
				}
				
			}
	
			
			
			
		}else {
			checkForCollision1((massive) current_physics_object, objects);
		}
		
	
	}
	
	public boolean getIsAffectedByBorder() {
		return affectedByBorder;
	}

	public void checkForCollisions(ArrayList<massive> objects) { // calls the checkForCollision method for every object in the objects list
		
		if (isTangible) {
			for (massive current_pObject : objects) {
				
				try {
					if ( (! equals(current_pObject) ) && (((massive) current_pObject).getIsTangible()) && (isTangible) ) ((massive) current_pObject).checkForCollision(this,objects);
				}catch(ClassCastException c) {
					try {
						c.printStackTrace();
					}catch(ClassCastException q) {
						System.out.println("not drawable");
					}
				}
			}	
		}
	}
	
	public Object checkForCollision1(massive current_object,ArrayList<massive> objects) {
	//for v1-4 collision
		return null;
	}
		
	public void applyForce(double r, double theta, double z_magn) { //theta is an angle from the eastward direction
		zAccel += z_magn/mass; //updating the zAccel with the z component of the force vector
		
		//calculating the components of the force vectors
		double xComponent = r * Math.cos(theta);
		double yComponent = r * Math.sin(theta);
		
		//updating the x and y Accel with the respective x and y components of the force vector
		xAccel += xComponent/mass; 
		yAccel += yComponent/mass;
	
	}
	
	public void applyImpulse(double r, double theta, double z_magn, double time,String units) { //applies a force to the object
		zAccel += z_magn/mass; //updating the zAccel with the z component of the force vector
		
		//calculating the components of the force vectors
		double xComponent = r * Math.cos(theta);
		double yComponent = r * Math.sin(theta);
		
		//updating the x and y Accel with the respective x and y components of the force vector
		xAccel += xComponent/mass; 
		yAccel += yComponent/mass;
		drawer.add(new ForceTimer(drawer,time,units,xComponent,yComponent,z_magn,this));
	}
	
	public void applyComponentForce(double x_magn, double y_magn, double z_magn) {
		xAccel += x_magn/mass; //updating the xAccel with the x component of the force vector
		yAccel += y_magn/mass; //updating the yAccel with the y component of the force vector
		zAccel += z_magn/mass; //updating the zAccel with the z component of the force vector
	}
	
	public void applyComponentImpulse(double x_magn, double y_magn, double z_magn, double time,String units) {
		xAccel += x_magn/mass; //updating the xAccel with the x component of the force vector
		yAccel += y_magn/mass; //updating the yAccel with the y component of the force vector
		zAccel += z_magn/mass; //updating the zAccel with the z component of the force vector
		drawer.add(new ForceTimer(drawer,time,units,x_magn,y_magn,z_magn,this));
	}
	

	
	public double[] getTrajectory() { //returns a polar representation of the velocity of the object
		double r = Math.sqrt(Math.pow(xSpeed,2) + Math.pow(ySpeed,2) + Math.pow(zSpeed, 2) ); //three-dimensional pythagorean theorem
		double xyTheta = Math.atan(ySpeed/xSpeed);
		double zxTheta = Math.atan(zSpeed/xSpeed);
		double[] trajPolar = {r,xyTheta,zxTheta};
		return trajPolar;
	}
	

	public void isCollided(physics_object object, faces side) { //method that gets called when the object hits something. Useful for things like spikes or bullets in a game
		
	}
	
	public void setMass(double mass1) { //update the mass of the object (kg)
		mass = mass1;
		momentOfInertiaCalculated = false;
	}


	
	@Override
	public int[] getPointRenderOrder() {
		return pointRenderOrder;
	}

	@Override
	public double getFrictionCoefficient() {
		return friction_coefficient;
	}
	
	public double getMomentOfInertia() {
		if (! momentOfInertiaCalculated) updateMomentOfInertia();
		return momentOfInertia;
	}

	@Override
	public point getPointOfRotation() {

		return pointOfRotation;
	}
	
	@Override
	public void setFrictionCoefficient(double frictionCoeff) {
		friction_coefficient = frictionCoeff;
		
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

	@Override
	public double getElasticity() {
		return elasticity;
	}

	@Override
	public boolean getHasNormalCollisions() {
		return hasNormalCollisions;
	}
	
	
	public void setHasNormalCollisions(boolean c) {
		hasNormalCollisions = c;
	}

	@Override
	public void setIsTangible(boolean isTang) {
		isTangible = isTang;
	}

	@Override
	public double getMaxSize() {
		return maxSize;
	}
	
	@Override
	public void setVectorRotation(Vector3D rotVec) {
		VecRotation = rotVec;
		if ( (VecRotation.getI() == 0) || (VecRotation.getJ() == 0) || (VecRotation.getK() == 0) ) {
			VecRotation.setIJK(VecRotation.getI()+0.000001,VecRotation.getJ()+0.000001,VecRotation.getK()+0.000001);
		}
	}

	@Override
	public void setVectorAngularVelocity(Vector3D angVelVec) {
		VecAngularVelocity = angVelVec;
		
	}

	@Override
	public void setVectorAngularAccel(Vector3D angAccelVec) {
		VecAngularAccel = angAccelVec;
		
	}

	@Override
	public void addVectorRotation(Vector3D rotVec) {
		VecRotation.add(rotVec);
		if ( (VecRotation.getI() == 0) || (VecRotation.getJ() == 0) || (VecRotation.getK() == 0) ) {
			VecRotation.setIJK(VecRotation.getI()+0.000001,VecRotation.getJ()+0.000001,VecRotation.getK()+0.000001);
		}
	}

	@Override
	public void addVectorAngularVelocity(Vector3D angVelVec) {
		VecAngularVelocity.add(angVelVec);
		
	}

	@Override
	public void addVectorAngularAccel(Vector3D angAccelVec) {
		VecAngularAccel.add(angAccelVec);
		
	}

	@Override
	public Vector3D getVectorRotation() {
		return VecRotation;
	}

	@Override
	public Vector3D getVectorAngularVelocity() {
		return VecAngularVelocity;
	}

	@Override
	public Vector3D getVectorAngularAccel() {
		return VecAngularAccel;
	}


	
}

