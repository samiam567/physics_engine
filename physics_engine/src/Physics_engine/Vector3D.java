package Physics_engine;

import java.awt.Graphics;

import Physics_engine.point;

public class Vector3D extends Physics_drawable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8467137252307482964L;
	private double i,j,k;
	private double r, alpha, beta, gamma, theta, phi;
	
	private static int h = 0;
	public Vector3D(object_draw drawer1, double i1, double j1, double k1) {
		super(drawer1);
		i = i1;
		j = j1;
		k = k1;
		super.setSize(i, j, k);
		setName("" + h,0);
		
		rectangularToPolar();
		rectangularToSpherical();
		
		h++;
	}
	
	public Vector3D(object_draw drawer1, double r1, double alpha1, double beta1, double gamma1) {
		super(drawer1);
		r = r1;
		alpha = alpha1;
		beta = beta1;
		gamma = gamma1;
		polarToRectangular();
		rectangularToSpherical();
		super.setSize(i, j, k);
	}
	
	public Vector3D(object_draw drawer1, point pI, point pF) {
		super(drawer1);
		setPos(pI.getXReal(),pI.getYReal(),pI.getZReal());
		i = pF.getXReal() - pI.getXReal();
		j = pF.getYReal() - pI.getYReal();
		k = pF.getZReal() - pI.getZReal();
		rectangularToPolar();
		rectangularToSpherical();
		super.setSize(i, j, k);
	}
	
	public void set(point pI, point pF) {
		setPos(pI.getXReal(),pI.getYReal(),pI.getZReal());
		i = pF.getXReal() - pI.getXReal();
		j = pF.getYReal() - pI.getYReal();
		k = pF.getZReal() - pI.getZReal();
		rectangularToPolar();
		rectangularToSpherical();
	}
	
	public void setIJK(double i1, double j1, double k1) {
		i = i1;
		j = j1;
		k = k1;
		super.setSize(i, j, k);
		rectangularToPolar();
		rectangularToSpherical();
	}
	
	public void setIJK(double[] dimensions) {
		i = dimensions[0];
		j = dimensions[1];
		k = dimensions[2];
		super.setSize(i, j, k);
		rectangularToPolar();
		rectangularToSpherical();
	}
	
	@Override
	public void setSize(double i1, double j1, double k1) {
		i = i1;
		j = j1;
		k = k1;
		super.setSize(i, j, k);
		calculateR();
	}
	
	private void calculateR() {
		r = Math.sqrt(i*i + j*j + k*k);
	}
	
	private void rectangularToPolar() {
		calculateR();
		alpha = Math.acos(i / r);
		beta = Math.acos(j / r);
		gamma = Math.acos(k / r);
		
	}
	
	private void rectangularToSpherical() {
		calculateR();
		
		//finding theta
		if (Math.abs(i) < 0.00001) { //vec is on the x,i axis
			if (Math.abs(j) < 0.00001) { //vec is of length zero in the x,y plane
				theta = 0;
			}else if (j > 0) { //vec is on the positive y axis
				theta = Math.PI/2;
			}else if (j < 0) { //vec is on the negative y axis
				theta = 3*Math.PI/2;
			}else {  
				System.out.println("logic error (vector3D) 1 - " + j);
			}
		}else if (i > 0) {
			if (Math.abs(j) < 0.00001) {  // i==0 on the positive x axis
				theta = 0;
			}else if (j > 0) { //quad 1
				theta = Math.acos(i/r);
			}else if (j < 0) { //quad 3
				theta = 3*Math.PI/2 + (90-Math.acos(i/r));
			}else {
				theta = 0;
			}
			
		}else if (i < 0){
			if (Math.abs(j) < 0.00001) { // i==0 on the negative x axis
				theta = Math.PI;
			}else if (j > 0) { //quad 2
				theta = Math.PI - Math.acos(-i/r);
			}else if (j < 0) { //quad 4
				theta = Math.PI + Math.acos(-i/r);
			}else { 
				System.out.println("logic error (vector3D) 2 - " + j);	
			}
		}else { 
			System.out.println("logic error (vector3D) 3 - " + i);

		}
		
		if (k/r > 1) System.out.println("k/r > 1!");
		if (k == 0) {
			phi = Math.PI/2;
		}else {
			phi = Math.acos(k/r);
		}

	}
	
	private void sphericalToRectangular() {
		k = r * Math.cos(phi);
		i = r * Math.cos(theta) * Math.sin(phi);
		j = r * Math.sin(theta) * Math.sin(phi);
		
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
		k = r * Math.cos(gamma);
	}
	
	public void setR(double r) {
		this.r = r;
		sphericalToRectangular();
		rectangularToPolar();
	}
	
	public void setPolar(double r1, double alpha1, double beta1, double phi1) {
		r = r1;
		alpha = alpha1;
		beta = beta1;
		gamma = phi1;
		polarToRectangular();
		rectangularToSpherical();
	}
	
	public void setPolarAngles(double alpha1, double beta1, double phi1) {
		alpha = alpha1;
		beta = beta1;
		gamma = phi1;
		polarToRectangular();
		rectangularToSpherical();
	}
	
	public void setSpherical(double r, double theta, double phi) {
		this.r = r;
		this.theta = theta;
		this.phi = phi;
		sphericalToRectangular();
		rectangularToPolar();
	}
	
	public void setSphericalAngles(double theta, double phi) {
		this.theta = theta;
		this.phi = phi;
		sphericalToRectangular();
		rectangularToPolar();
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
		calculateR();
	}
	
	public static Vector3D multiply(Vector3D u, double mult) {
		return new Vector3D(u.drawer,u.getI() * mult, u.getJ() * mult, u.getK() * mult);
	}
	
	public static Vector3D multiplyIJKs(Vector3D u, Vector3D v) {
		return new Vector3D(u.drawer,u.getI() * v.getI(), u.getJ() * v.getJ(), u.getK() * v.getK());
	}
	
	public void divide(double div) {
		setIJK(getI() / div,getJ() / div,getK() / div);
		calculateR();
	}
	
	public void add(Vector3D addVec) {
		setIJK(getI() + addVec.getI(),getJ() + addVec.getJ(), getK() + addVec.getK());
		rectangularToPolar();
		rectangularToSpherical();
	}
	
	public static Vector3D add(Vector3D vec1, Vector3D vec2) {
		return new Vector3D(vec1.getDrawer(),vec1.getI() + vec2.getI(), vec1.getJ() + vec2.getJ(), vec1.getK() + vec2.getK());
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
	
	public double getGamma() {
		return gamma;
	}
	
	public double getTheta() {
		return theta;
	}
	
	public double getPhi() {
		return phi;
	}

	



}
