package physics_sims;

import java.util.ArrayList;
import java.util.Scanner;

public class IUPUIMathComp {
	private static long x,c,t; //t is the highest number we test to
	private static int n;
	private static ArrayList<Long> xAns = new ArrayList<Long>();
	private static ArrayList<Long> cAns = new ArrayList<Long>();
	
	public static void main(String[] args) {
		
		Scanner key = new Scanner(System.in);
		System.out.println("How many Answers do you want minimum");
		int numAnsMin = key.nextInt();
		int prevXAnsSize = 0;
		
		t = 1000;
		n = 3;
		x = 0;
		c = 0;
		
		while (xAns.size() < numAnsMin) {
			
			for (c = t-1000; c <= t; c++) {
				for (x = 0; x < t; x++) {
					if (periodic(x,c,n,0) == x) {
						xAns.add(x);
						cAns.add(c);
					}
					
					if (periodic(x,-c,n,0) == x) {
						xAns.add(x);
						cAns.add(-c);
					}
					
					if (periodic(-x,-c,n,0) == -x) {
						xAns.add(-x);
						cAns.add(-c);
					}
				}
			}
		
		
			
						
			if (xAns.size() > prevXAnsSize) {
				System.out.println("t:" + t);
				System.out.print("{");
				for(int i = prevXAnsSize; i < xAns.size(); i++ ) {
					System.out.print(xAns.get(i) + ",");
					System.out.print(cAns.get(i) + ";");
				}
				System.out.println("}");
			}
			
			prevXAnsSize = xAns.size();
			
			t+= 1000;
			
		}
		

	}
	
	private static double periodic(long x,long c, int n, int i) {
		if (i <= n) {
			return Math.pow(periodic(x,c,n,i+1),2) + c;
		}else {
			return x;
		}
	}
}
