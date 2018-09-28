package Physics_engine;

import java.awt.Graphics;

public class Vector3D extends Physics_shape {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8467137252307482964L;
	public double i,j,k;
	 double alpha, beta, phi, r;
	
	public Vector3D(object_draw drawer1, double i1, double j1, double k1) {
		super(drawer1);
		i = i1;
		j = j1;
		k = k1;
		rectangularToPolar();
	}
	
	public Vector3D(object_draw drawer1, double r1, double alpha1, double beta1, double phi1) {
		super(drawer1);
		r = r1;
		alpha = alpha1;
		beta = beta1;
		phi = phi1;
		polarToRectangular();
	}
	
	public Vector3D(object_draw drawer1, point pI, point pF) {
		super(drawer1);
		setPos(pI.getXReal(),pI.getYReal(),pI.getZReal());
		i = pF.getXReal() - pI.getXReal();
		j = pF.getYReal() - pI.getYReal();
		k = pF.getZReal() - pI.getZReal();
		rectangularToPolar();
	}
	
	private void rectangularToPolar() {
		alpha = Math.acos(i / Math.sqrt(i*i + j*j + k*k));
		beta = Math.acos(j / Math.sqrt(i*i + j*j + k*k));
		phi = Math.acos(k / Math.sqrt(i*i + j*j + k*k));
		r = Math.sqrt(i*i + j*j + k*k);
	}
	
	public static double[] rectangularToPolar(double i,double j, double k) {
	
		// r , alpha, beta, phi
		return new double[] {
				Math.sqrt(i*i + j*j + k*k),
				Math.acos(i / Math.sqrt(i*i + j*j + k*k)),
				Math.acos(j / Math.sqrt(i*i + j*j + k*k)),
				Math.acos(k / Math.sqrt(i*i + j*j + k*k))
		};
	}
	
	private void polarToRectangular() {
		i = r * Math.cos(alpha);
		j = r * Math.cos(beta);
		k = r * Math.cos(phi);
	}
	
	public void setAngles(double alpha1, double beta1, double phi1) {
		alpha = alpha1;
		beta = beta1;
		phi = phi1;
		polarToRectangular();
	}
	
	public static double[] polarToRectangular(double r, double alpha, double beta, double phi) {
		// i,j,k
		return new double[] {
				 r * Math.cos(alpha),
				 r * Math.cos(beta),
				 r * Math.cos(phi)
				 
		};
	}

	@Override
	public void paint(Graphics Page) {
		Page.drawLine(x , y ,(int) Math.round(x + i), (int) Math.round(y + j) );
	}

}
