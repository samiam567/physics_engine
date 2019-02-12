package physics_sims;

public class SuperCannonLaunchVelocity {

	
	private static double Lp = .051;
	private static double Lpc = .135;
	private static double r = 0.072;
	private static double Y2 = .3;
	private static double Y4 = .19;
	private static double z = .075;
	private static double Px = -0.075;
	
	
	//settings
	private static double deltaX = Lp/100;
	private static double preassure = 115; //psi
	private static double Mball = .056;
	
	
	  //piston
	private static double plungerRadiusInches = .125;
	private static double Fpiston; 
	
	private static double Gm = 980/100;
	private static double ThetaPis,ThetaBar,ThetaCan;
	private static double q,Tbar,x,Dball,FbarIball;
	private static double Trb = 0;
	
	
	public static void main(String[] args) {
		System.out.println(calculateSuperLaunchVelocity(30));
	}
	
	private static double calculateThetaPis(double x) { //y5
	  double Beta = Math.sqrt( Px*Px + Y4*Y4 );
	  
	  ThetaPis = Math.atan(Y4/(-Px)) - Math.acos( ( Math.pow(Lpc+x,2) + Beta*Beta - r*r ) / (2 * (Lpc+x) * Beta));
	  return ThetaPis;
	}
	
	private static double calculateThetaBar(double x, double ThetaPis) { //y6
	  ThetaBar = Math.atan( Px/Y4 + 1/Math.tan(ThetaPis) );
	  return ThetaBar;
	}
	
	private static double calculateQ(double ThetaCan, double ThetaBar) { //y7
	  q = ( (z*Math.tan(ThetaCan) + Y2) / (Math.tan(ThetaCan) + 1/Math.tan(ThetaBar)) ) / Math.sin(ThetaBar);
	  return q;
	}
	
	
	
	private static double calculateTBar(double ThetaPis, double ThetaBar) { //y8
	  Tbar = r*Fpiston*Math.cos(ThetaPis-ThetaBar);
	  return Tbar;
	}
	
	private static double calculateFbarIball(double Tbar, double ThetaBar, double ThetaCan) { //y9
	  FbarIball = (Tbar/q) * Math.cos(ThetaBar-ThetaCan);
	  return FbarIball;
	}
	
	private static double calculateDBall(double ThetaCan, double ThetaBar) {  //y0
	  Dball = ( ( (z*Math.tan(ThetaCan) + Y2) / (Math.tan(ThetaCan) + 1/Math.tan(ThetaBar)) ) - z) / Math.cos(ThetaCan);
	  System.out.println("Dball: " + Dball);
	  return Dball;
	}
	
	private static double calculateSuperLaunchVelocity(double cannonAngle) {
	  ThetaCan = Math.PI * cannonAngle/180;
	  
	  Fpiston = preassure * (plungerRadiusInches*plungerRadiusInches)*Math.PI * 4.448222; //25N
		
	
	  double MomentOfForce = 0;
	  for (double x = 0; x < (Lp); x+=deltaX) {
		 System.out.println("x: " + x);
	     System.out.println("ThetaPis:" + calculateThetaPis(x));
	     System.out.println("ThetaBar:" + calculateThetaBar(x,ThetaPis));
	     System.out.println("q:" + calculateQ(ThetaCan,ThetaBar));
	
	     System.out.println("Tbar:" + calculateTBar(ThetaPis,ThetaBar));
	     System.out.println("FbarIball:" + calculateFbarIball(Tbar, ThetaBar,ThetaCan));
	     MomentOfForce += FbarIball*deltaX;
	     System.out.println("Mforce:" + MomentOfForce);
	     System.out.println("-");
	  }
	
	  double WIball = (MomentOfForce / Lp) * .21;///( calculateDBall(calculateThetaPis(Lp),calculateThetaBar(Lp,calculateThetaPis(Lp))) - calculateDBall(calculateThetaPis(0),calculateThetaBar(0,calculateThetaPis(0))) );
	
	  double Vball = Math.sqrt(2*WIball/Mball);
	
	  return Vball;
	}
}
