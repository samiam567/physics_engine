package three_d_graphics_prototype;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Scanner;
import javax.swing.JFrame;

public class Three_d_graphics_prototype extends Applet{
	
	public void paint(Graphics page) {
		
		class object {
			int x;
			int y;
			int z;  // Z=0 is at the screen and as z increases, the object moves further away.
			int ob_size; // the size of the object
			int rotation_X; // how much the object is rotated in the x direction. (degrees) (about the Y axis)
			int rotation_Y; // how much the object is rotated in the y direction. (degrees) (about the X axis)
			
			public object(int ob_x,int ob_y,int ob_z,int new_ob_size, int new_ob_rotation_X, int new_ob_rotation_Y) {
				x = ob_x;
				y = ob_y;
				z = ob_z;
				ob_size = new_ob_size;
				rotation_X = new_ob_rotation_X;
				rotation_Y = new_ob_rotation_Y;
				
				}
			
			
		}
		
		class box extends object {
		
			
			
			public box(int ob_x,int ob_y,int ob_z,int new_ob_size, int new_ob_rotation_X,int new_ob_rotation_Y) {
				super(ob_x,ob_y,ob_z,new_ob_size, new_ob_rotation_X,new_ob_rotation_Y);
			}
			
			public void draw() {  // First version of the draw (has errors in the way it calculates rotation) 
				int size = ob_size-z;
			
				
				double rotate_deviation_double_X = size*(Math.sin(2*rotation_X)); 
				int rotate_deviation_X = (int) rotate_deviation_double_X;
				
				
				double rotate_deviation_double_Y = size*(Math.sin(2*rotation_Y)); 
				int rotate_deviation_Y = (int) rotate_deviation_double_Y;
				
				page.drawRect(x, y, size, size);  //front edges
				
				page.drawRect(x+rotate_deviation_X,y-rotate_deviation_Y,size,size); // back edges
				
				page.drawLine(x, y, x+(rotate_deviation_X), y-(rotate_deviation_Y));    //top left edge
				page.drawLine(x, y+size, x+(rotate_deviation_X), y-(rotate_deviation_Y)+size);	//top right edge
				
				page.drawLine(x+size, y, x+(rotate_deviation_X)+size, y-(rotate_deviation_Y));			//bottom left edge	
				page.drawLine(x+size, y+size, x+(rotate_deviation_X)+size, y-(rotate_deviation_Y)+size); 	//bottom right edge
				
				
				 
			}
			
			public void drawV2(int x,int y,int z,int rotationX,int rotationY) {
				
				System.out.println("RotX" + rotationX);
				System.out.println("RotY" + rotationY);
				
				int size = ob_size-z;
				double rotationX_double = (double) rotationX; //cast rotationX as a double (must be a double to do division)
				double rotation_X_rad = (rotationX_double/180) * Math.PI; //convert rotation_X to radians
				double width_double = size * ( Math.cos(rotation_X_rad) );
				int width = (int) width_double;
				System.out.println("Width: " + width);
				
				
				double rotationY_double = (double) rotationY; //cast rotationY as a double (must be a double to do division)
				double rotation_Y_rad = (rotationY_double/180) * Math.PI; //convert rotation_Y to radians
				double height_double = size * ( Math.cos(rotation_Y_rad) );
				int height = (int) height_double;
				System.out.println("height: " + height);
		
				width = Math.abs(width);
				height = Math.abs(height);
				
			
				
				
				//------------------------------------------
			/*
				System.out.println("RotXRad: " + rotation_X_rad);
				double rotate_deviation_double_X = width*(Math.cos( (Math.PI/2) -rotation_X_rad)); 
				int rotate_deviation_X = (int) rotate_deviation_double_X;	//distance in-between the back and the front face in the X dimension
				System.out.println("RDEVX: " + rotate_deviation_X);
				
				double rotate_deviation_double_Y  = height*(Math.cos( (Math.PI/2) -rotation_Y_rad)); 
				int rotate_deviation_Y = (int) rotate_deviation_double_Y;
				System.out.println("RDEVY: " + rotate_deviation_Y);
				*/
				int rotate_deviation_X = size-width;
				int rotate_deviation_Y = size-height; 
				
				System.out.println("RDEVX: " + rotate_deviation_X);
				System.out.println("RDEVY: " + rotate_deviation_Y);
				
				
		
				
				page.drawRect(x, y, width, height);  //front edges
				
				page.drawRect(x+rotate_deviation_X,y-rotate_deviation_Y,width,height); // back edges
		
				page.drawLine(x, y, x+(rotate_deviation_X), y-(rotate_deviation_Y));    //top left edge
				page.drawLine(x, y+height, x+(rotate_deviation_X), y-(rotate_deviation_Y)+height);	//top right edge
				
				page.drawLine(x+width, y, x+(rotate_deviation_X)+width, y-(rotate_deviation_Y));			//bottom left edge	
				page.drawLine(x+width, y+height, x+(rotate_deviation_X)+width, y-(rotate_deviation_Y)+height); 	//bottom right edge
				
				
				
				//----------------------------------------
			}
		}
		
		
		
		
		
		box box_1 = new box(100,10,0,100,50,30);
	//	box_1.drawV2(100,50,0,30,30);
		
		
		box box_2 = new box(100,100,0,100,70,30);
	//	box_2.drawV2(100,150,0,70,30);

//		box_2.drawV2(50, 10, 0, 0, 30);
//		box_2.drawV2(50, 50, 0, 0, 0);
		int rotation__X = 0;
		int rotation__Y = 0;
		int time = -100;
		int x,y;
		x = 100;
		y = 100;
		double pause = 0;
		while (time < 1000) {
			time++;
			rotation__X++;
			rotation__Y++;
			page.setColor(Color.WHITE);
			page.fillRect(0,0,1000,1000);
		
			
			page.setColor(Color.black);
			
			box_1.drawV2(50,50,0,rotation__X,30);
		
			pause = 0;
			while (pause< 10000000  /*100000000 */  ) {
				pause++;
			}
			
		}
	}
	
}
