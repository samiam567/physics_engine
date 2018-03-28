package jetpack_joyride;

import java.applet.Applet;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.util.ArrayList;

import Physics_engine.Physics_frame;
import Physics_engine.object_draw;

class A{

    public static int x = 0;

    public A(){            

       x++;

    }

    public static int getX()

    {

         return x;

    }

}

class B extends A{

    public B(){

       x++;

    }

}



public class Random_runner extends Applet{
	public static void main(String[] args) {
		 
		A a = new A();

		a = new B();

		System.out.println(A.getX());
	}
	
	
}
