package Physics_engine;

import java.awt.Graphics;

public class Vector3D extends Physics_shape {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8467137252307482964L;
	private double i,j,k;
	private double alpha, beta, phi, r;
	
	public Vector3D(object_draw drawer1, double i1, double j1, double k1) {
		super(drawer1);
		i = i1;
		j = j1;
		k = k1;
		super.setSize(i, j, k);
		rectangularToPolar();
	}
	
	public Vector3D(object_draw drawer1, double r1, double alpha1, double beta1, double phi1) {
		super(drawer1);
		r = r1;
		alpha = alpha1;
		beta = beta1;
		phi = phi1;
		polarToRectangular();
		super.setSize(i, j, k);
	}
	
	public Vector3D(object_draw drawer1, point pI, point pF) {
		super(drawer1);
		setPos(pI.getXReal(),pI.getYReal(),pI.getZReal());
		i = pF.getXReal() - pI.getXReal();
		j = pF.getYReal() - pI.getYReal();
		k = pF.getZReal() - pI.getZReal();
		rectangularToPolar();
		super.setSize(i, j, k);
	}
	
	public void setIJK(double i1, double j1, double k1) {
		i = i1;
		j = j1;
		k = k1;
		super.setSize(i, j, k);
		rectangularToPolar();
	}
	
	public void setIJK(double[] dimensions) {
		i = dimensions[0];
		j = dimensions[1];
		k = dimensions[2];
		super.setSize(i, j, k);
		rectangularToPolar();
	}
	
	@Override
	public void setSize(double i1, double j1, double k1) {
		i = i1;
		j = j1;
		k = k1;
		super.setSize(i, j, k);
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
	
	public static double dot(Vector3D u, Vector3D v) {
		return u.i*v.i + u.j*v.j + u.k*v.k;
	}
	
	public static double dot(double uI, double uJ, double uK, double vI, double vJ, double vK) {
		return uI*vI + uJ*vJ + uK*vK;
	}
	
	public void multiply(double mult) {
		setIJK(getI() * mult,getJ() * mult,getK() * mult);
	}
	
	public static Vector3D multiply(Vector3D u, double mult) {
		return new Vector3D(u.drawer,u.getI() * mult, u.getJ() * mult, u.getK() * mult);
	}
	
	public void divide(double div) {
		setIJK(getI() / div,getJ() / div,getK() / div);
	}
	
	public static Vector3D cross(Vector3D u, Vector3D q) {
		return new Vector3D(u.getDrawer(),u.getJ()*q.getK()-u.getK()*q.getJ(),-(u.getI()*q.getK()-u.getK()*q.getI()),u.getI()*q.getJ()-u.getJ()*q.getI());
	}
	
	public static Vector3D proj(Vector3D u, Vector3D v) {
		return Vector3D.multiply(v,dot(u,v) / Math.pow(v.getR(),2));
	}
	
	
	public static double[] cross(double uI, double uJ, double uK, double qI, double qJ, double qK) {
		return new double[] {uJ*qK-uK*qJ,-(uI*qK-uK*qI),uI*qJ-uJ*qI};
	}

	@Override
	public void paint(Graphics Page) {
		Page.drawLine(getX() , getY() ,(int) Math.round(getX() + i), (int) Math.round(getY() + getJ()) );
	}
	
	public double getI() {
		return i;
	}
	
	public double getJ() {
		return j;
	}
	
	public double getK() {
		return k;
	}
	
	public double getR() {
		return r;
	}
	
	public double getAlpha() {
		return alpha;
	}
	
	public double getBeta() {
		return beta;
	}
	
	public double getPhi() {
		return phi;
	}

}
