package Physics_engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;

import Physics_engine.Physics_engine_toolbox.faces;
import Physics_engine.Physics_engine_toolbox.pointOfRotationPlaces;

public class Physics_3DShape extends Physics_drawable implements rotatable {
	
	private Physics_polygon[] polygons;
	
	//for massive
	private double mass = 10;
	private double friction_coefficient,momentOfInertia;
		
	
	public double elasticity = Settings.elasticity;
	
	private double maxSize; //the distance from the center that the furthest point is 
	
	private double prevXRotation = 0, prevYRotation = 0, prevZRotation = 0;
	double xRotation,yRotation,zRotation,angularVelocityX, angularVelocityY, angularVelocityZ, angularAccelX, angularAccelY, angularAccelZ;
	public boolean isRotatable = true,isTangible = true, affectedByBorder = true,isShaded = false,calculateCenter = true;
	protected boolean hasNormalCollisions = true;
	
	point pointOfRotation = null; //the point that the object rotates around
	pointOfRotationPlaces pointOfRotationPlace = pointOfRotationPlaces.center;  //the place that that point is
	
	double xSizePrev,ySizePrev,zSizePrev,xSizeInit,ySizeInit,zSizeInit; //used to see if the size has changed
	
	private double initialXDistanceFromPointOfRot,initialYDistanceFromPointOfRot,initialZDistanceFromPointOfRot;
	
	private class Physics_polygon {
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
	
	
	
	public void updatePoints() {
		
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

	@Override
	public void setRotation(double xRotation1, double yRotation1, double zRotation1) {
		
	}

	@Override
	public void setAngularVelocity(double angVX, double angVY, double angVZ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAngularAccel(double angAccelX, double angAccelY, double angAccelZ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPointOfRotation(point newPointOfRotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPointOfRotationPlace(pointOfRotationPlaces newPlace) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePointOfRotation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getXRotation() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getYRotation() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getZRotation() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public point getCenter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public pointOfRotationPlaces getPointOfRotationPlace() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public point getPointOfRotation() {
		// TODO Auto-generated method stub
		return null;
	}

}