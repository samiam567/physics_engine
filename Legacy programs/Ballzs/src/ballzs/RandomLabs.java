package ballzs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class RandomLabs
{  
	private static int hi;
	
	public static void main(String[] args) {

		List<String> bigList = new ArrayList<String>();

		bigList.add(0,"one");

		bigList.add("two");

		bigList.add(0,"three");

		bigList.add("four");

		bigList.remove( 0 );

		bigList.add(0,"five");

		bigList.set(2,"six");

		bigList.set(3,"seven");

		bigList.add( "zero" );

		System.out.println( bigList.indexOf("six") );
		
		
	}
}
 
