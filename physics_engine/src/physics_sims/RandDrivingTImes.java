package physics_sims;

import javax.swing.JOptionPane;

public class RandDrivingTImes {
	public static void main(String[] args) {
		int dayTime,nightTime,dayInc;
		do {
			
			dayInc  = (int) (1 + (Math.round(Math.random() * 100) % 4));
			
			
			
			if (Math.random() < .85) {
				dayTime = (int) (5* Math.round( (Math.random() * 25)/5 ));
			}else {
				dayTime = (int) (5 * Math.round((Math.random() * 150)/5 ));
			}
			
			
			if (Math.random() < .55) {
				nightTime = 0;
			}else if (Math.random() < .55){
				nightTime = (int) (5* Math.round( (Math.random() * 15)/5 ));
			}else {
				nightTime = (int) (5* Math.round( (Math.random() * 30)/5 ));
			}
				
		}while(JOptionPane.showConfirmDialog(null, dayInc + " days\n" + dayTime + " dayMins\n" + nightTime + " nightMins") == 0);
	}
	
}