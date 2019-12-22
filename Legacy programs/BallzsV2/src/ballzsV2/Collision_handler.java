package ballzsV2;

import java.io.Serializable;
import java.util.ArrayList;

public class Collision_handler implements Serializable{
//	public ArrayList<Ball> balls;
//	public ArrayList<Object> boxes;
	
	public Settings settings = new Settings();
	
	public static int width;
	public static int height;
	
	public void setDimensions(int width2, int height2) {
		width = width2;
		height = height2;
	}
	public void checkForCollisions(ArrayList<Ball> balls,ArrayList<Box> boxes,int ball_amount) {
		for (Ball current_ball : balls) {
			checkForCollision(current_ball,balls,boxes,ball_amount);
		}
	}
	
	public int checkForCollision(Ball current_ball,ArrayList<Ball> balls,ArrayList<Box> boxes,int ball_amount) {
		
		//did the ball hit the bounds of the screen?
		if ( (Math.abs(current_ball.centerX - (settings.width-20) )) < ( current_ball.x_speed + current_ball.size/2 ) ) { //right side
			current_ball.x_speed = -current_ball.x_speed;
		}else if ( current_ball.centerX < ( current_ball.x_speed + current_ball.size/2 ) ) { //left side
			current_ball.x_speed = -current_ball.x_speed;
			current_ball.x = (int) Math.round(current_ball.x_speed);
		}else if ( (Math.abs(current_ball.centerY - (settings.height - 40) )) < ( current_ball.y_speed + current_ball.size/2 ) ) { //bottom side
			current_ball.y_speed = -current_ball.y_speed; 
			current_ball.delete(balls);
			ball_amount--;
			//System.out.println(ball_amount + " balls left");
			
		}else if ( current_ball.centerY < ( current_ball.y_speed + current_ball.size/2 ) ) { //top side
			current_ball.y_speed = -current_ball.y_speed;
			current_ball.y = (int) Math.round(current_ball.y_speed);
		}

		//did ball hit box?
		for (Box current_box : boxes) {
			if (current_box.isVisible) { 
				
				// v 1.10 collision algorithm (works ~95% of the time)
				if (Math.abs(current_ball.centerX - current_box.centerX) < (current_box.size/2+current_ball.size/2)) {
					if (Math.abs(current_ball.centerY - current_box.centerY) < (current_box.size/2+current_ball.size/2)) {
					
						current_box.hit();
						//figure out which way the ball bounces
						if (  Math.abs(current_ball.centerX+current_ball.size/2 - current_box.x) < (current_ball.x_speed*1.1)) { //ball hit left face of box
							current_ball.x_speed = - Math.abs(current_ball.x_speed);
							current_ball.x = (int) Math.round(current_ball.xReal + current_ball.x_speed);
			//				System.out.println("Top");
							
						} else if (  Math.abs(current_ball.centerY+current_ball.size/2 - current_box.y) < (current_ball.y_speed*1.1)) { //ball hit top face
							current_ball.y_speed = -Math.abs(current_ball.y_speed);
							current_ball.y = (int) Math.round(current_ball.yReal + current_ball.y_speed);
			//				System.out.println("left");

						} else if (  Math.abs( (current_ball.centerX - current_ball.size/2) - (current_box.x + current_box.size) ) < (current_ball.x_speed*1.1 + current_box.size )) { //ball hit right face
							current_ball.x_speed = Math.abs(current_ball.x_speed);
							current_ball.x = (int) Math.round(current_ball.xReal + Math.abs(1.1 * current_ball.x_speed) );
		//					System.out.println("right");
							
						}else { //if (  Math.abs( (current_ball.centerY - current_ball.size/2) - (current_box.y+current_box.size) ) < (current_box.size + current_ball.y_speed*1.1) ) { // ball hit bottom face
							current_ball.y_speed = Math.abs(current_ball.y_speed);
							current_ball.y = (int) Math.round(current_ball.yReal + Math.abs(1.1 * current_ball.y_speed));
			//				System.out.println("bottom");
						}
						
						
					}
				}
				
				
				if (false) {  //v 2.0 collision algorithm (sadly doesn't work)
				if ( ( Math.abs(current_ball.centerX - current_box.centerX) < current_box.size + current_ball.size + current_ball.x_speed) && ( Math.abs(current_ball.centerY - current_box.centerY) < current_box.size + current_ball.size + current_ball.y_speed) ) {
				//	System.out.println("Inrange");
					if ( Math.abs( (current_ball.centerX+current_ball.size/2) - (current_box.centerX-current_box.size/2) ) < (current_ball.x_speed)   ) { //left face 
						current_ball.y_speed = -Math.abs(current_ball.y_speed);
						current_ball.y = (int) Math.round(current_ball.yReal + current_ball.y_speed);
						System.out.println("left");
					}
					
					if ( Math.abs( (current_ball.centerX-current_ball.size/2) - (current_box.centerX+current_box.size/2) ) < (current_ball.x_speed)   ) { //right face 
						System.out.println("right");
						current_ball.x_speed = Math.abs(current_ball.x_speed);
						current_ball.x = (int) Math.round(current_ball.xReal + Math.abs(1.1 * current_ball.x_speed) );
						System.out.println("right");
					}
					
					if ( Math.abs( (current_ball.centerY+current_ball.size/2) - (current_box.centerY-current_box.size/2) ) < (current_ball.y_speed)   ) { //top face 
						current_ball.x_speed = - Math.abs(current_ball.x_speed);
						current_ball.x = (int) Math.round(current_ball.xReal + current_ball.x_speed);
						System.out.println("Top");
					}
					
					if ( Math.abs( (current_ball.centerX - current_ball.size/2) - (current_box.centerX + current_box.size/2) ) < (current_ball.y_speed)   ) { //bottom face 
						current_ball.y_speed = Math.abs(current_ball.y_speed);
						current_ball.y = (int) Math.round(current_ball.yReal + Math.abs(1.1 * current_ball.y_speed));
						System.out.println("bottom");
					}
				}
				
				
				
				
				}
			
			}	
		}
	
		
		return ball_amount;
		
		
	}
}
