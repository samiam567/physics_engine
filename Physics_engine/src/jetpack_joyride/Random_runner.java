package jetpack_joyride;

import java.util.ArrayList;

public class Random_runner {
	public static void main(String[] args) {
		ArrayList<It> x;

		x = new ArrayList<It>();

		x.add( new It( 5, "dog" ) );

		x.add( new It( 3, "cat" ) );

		System.out.println( x.get( 0 ).getStuff()) ;
	}
}
