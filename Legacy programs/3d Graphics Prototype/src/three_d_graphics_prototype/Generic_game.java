package three_d_graphics_prototype;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class Generic_game extends Canvas{
	public boolean debug = false;
	
	public void paint(Graphics page) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		
		//objects--------------------
				class point {
					int x,y,z;  // Z=0 is at the screen and as z increases, the object moves further away.
					
					public point(int a, int b, int c) {
						x = a;
						y = b;
						z = c;
					}
				}
				
				class three_d_object {
					int x;
					int y;
					int z;  // Z=0 is at the screen and as z increases, the object moves further away.
					int ob_size; // the size of the object
					int rotation_X; // how much the object is rotated in the x direction. (degrees) (about the Y axis)
					int rotation_Y; // how much the object is rotated in the y direction. (degrees) (about the X axis)
					
					public three_d_object(int ob_x,int ob_y,int ob_z,int new_ob_size, int new_ob_rotation_X, int new_ob_rotation_Y) {
						x = ob_x;
						y = ob_y;
						z = ob_z;
						ob_size = new_ob_size;
						rotation_X = new_ob_rotation_X;
						rotation_Y = new_ob_rotation_Y;
						
						}
					
					
				}
				
				class box extends three_d_object {
				
					
					
					public box(int ob_x,int ob_y,int ob_z,int new_ob_size, int new_ob_rotation_X,int new_ob_rotation_Y) {
						super(ob_x,ob_y,ob_z,new_ob_size, new_ob_rotation_X,new_ob_rotation_Y);
					}
					
					
					
					public int drawV2(int x,int y,int z,int rotationX,int rotationY) { //second version of draw (cannot do rotation in multiple dimensions
						
						if (debug) {
							System.out.println("RotX" + rotationX);
							System.out.println("RotY" + rotationY);
						}
					
						int size = ob_size-z;
						double rotationX_double = (double) rotationX; //cast rotationX as a double (must be a double to do division)
						double rotation_X_rad = (rotationX_double/180) * Math.PI; //convert rotation_X to radians
						double width_double = size * ( Math.cos(rotation_X_rad) );
						int width = (int) width_double;
						
						if (debug) System.out.println("Width: " + width);
						
						
						double rotationY_double = (double) rotationY; //cast rotationY as a double (must be a double to do division)
						double rotation_Y_rad = (rotationY_double/180) * Math.PI; //convert rotation_Y to radians
						double height_double = size * ( Math.cos(rotation_Y_rad) );
						int height = (int) height_double;
						
						if (debug) 
						System.out.println("height: " + height);
				
						width = Math.abs(width);
						height = Math.abs(height);
					
						int rotate_deviation_X = size-width;
						int rotate_deviation_Y = size-height; 
						
						if (debug) {
							System.out.println("RDEVX: " + rotate_deviation_X);
							System.out.println("RDEVY: " + rotate_deviation_Y);
						}

						page.drawRect(x, y, width, height);  //front edges
						
						page.drawRect(x+rotate_deviation_X,y-rotate_deviation_Y,width,height); // back edges
				
						page.drawLine(x, y, x+(rotate_deviation_X), y-(rotate_deviation_Y));    //top left edge
						page.drawLine(x, y+height, x+(rotate_deviation_X), y-(rotate_deviation_Y)+height);	//top right edge
						
						page.drawLine(x+width, y, x+(rotate_deviation_X)+width, y-(rotate_deviation_Y));			//bottom left edge	
						page.drawLine(x+width, y+height, x+(rotate_deviation_X)+width, y-(rotate_deviation_Y)+height); 	//bottom right edge
						
						
						
						//----------------------------------------
						return rotate_deviation_X;
					}
				}
				
				
				
				
				//--------------------------------------------
				//Abstract stuff  -------------------------------------------
				class equation {
					public double a,b,h,k;

					public equation(double q,double i,double r,double z) {
						a = q;
						b = i;
						h = r;
						k = z;
					}
					
					public double XtoY(double x) {
						return x;
					}
					
				}
				class sq_eq extends equation {
					public double a,b,h,k;
					public sq_eq(double q,double i,double r,double z) {
						super(q,i,r,z);
						a = q;
						b = i;
						h = r;
						k = z;
						
					}
					public double XtoY(int x) {
						if (debug) 
							System.out.println("a,b,h,k " + a+b+h+k);
						
						double y = (double) (a * Math.pow((b*x - h),2) + k);
						return y;
					}
				}
				
				class sqrt_eq extends equation {
					public double a,b,h,k;
					public sqrt_eq(double q,double i,double r,double z) {
						super(q,i,r,z);
						a = q;
						b = i;
						h = r;
						k = z;
						
					}
					public double XtoY(int x) {
						if (debug) 
							System.out.println("a,b,h,k " + a+b+h+k);
						
						double y = (double) (a * Math.sqrt(b*x - h) + k);
						return y;
					}
				}
				
				class sin_eq extends equation {
					public double a,b,h,k;
					
					public sin_eq(double q,double d,double r,double z) {
						super(q,d,r,z);
						a = q;
						b = d;
						h = r;
						k = z;
						if (debug) 
							System.out.println("new sin_eq");
						}
					public double XtoY(int x) {
						if (debug) {
							System.out.println("a,b,h,k " + a+b+h+k);
						}
						
						double y = (double) (a * Math.sin(b*x - h) + k);
						return y;
					}
				
				}
				
				class player {
					public String name;
					public int id;
					public int space;
					public Color color;
					public Color[] Colors = {Color.black,Color.RED,Color.DARK_GRAY,Color.BLUE,Color.YELLOW};
					
					public player(String player_name,int player_id) {
						name = player_name;
						id = player_id;
						
						//use a while loop to cycle through a list of colors and give each player a different color
						int color_inc = 0;
						int color_key = 0;
						while (color_inc != id) {
							if (color_key == Colors.length)
								color_key = 0;
							color = Colors[color_key];
							color_inc++;
							color_key++;
						}
						
						
					}
					
					public void draw(equation board_eq) {
						sin_eq draw_board_eq = (sin_eq) board_eq;  //this needs to be changed with equation
						
						page.setColor(color);
						
						int player_x = (space) * 100;
						int player_y = ((int) draw_board_eq.XtoY(player_x)) + 10;
						
					
						
						if (debug) {
							System.out.println("player" + id + "x: " + player_x);
							System.out.println("player" + id + "y: " + player_y);
						}
						
						(new box(player_x,50,50,50,50,50)).drawV2(player_x, player_y + 3*id, 0, 30, 30);  //creating a new box and drawing it
						
						page.setFont(new Font("TimesRoman",Font.PLAIN,20));
						page.drawString(name,player_x+10,player_y+25); //putting the player's name inside their box
					}
					public void setSpace(int new_space) {
						space = new_space;
						
						System.out.println(name + " has just moved to space " + space);
					}
				}
				
		//starts here ===========================================================================================================================
				
				class Game {
					public String player_num;
					public sin_eq board_eq = new sin_eq((int) ((Math.random()+1) *50 ),0.01,1000,500);
					public boolean game_on = true;
					
					//create players array
					public Object[] players1 = {};
					public ArrayList<Object> players = new ArrayList<Object>(Arrays.asList(players1));
					
					public Game() { //setup the game
						int start = 4;
						while (start != 0)
							start  = JOptionPane.showConfirmDialog(window, "Start?", "Press YES to start", 1, 1, null);	
						
						// use try/catch statements to find the number of players and make sure it's a valid int
						String player_num = "";
						try {
							player_num = JOptionPane.showInputDialog("How many players are there?");
							int player_num_int = Integer.parseInt(player_num);
							
						} catch(NumberFormatException problem) {
							
							try {
								player_num = JOptionPane.showInputDialog("How many players are there? \n MUST BE A VALID NUMBER");
								int player_num_int = Integer.parseInt(player_num);
							}
							catch(NumberFormatException problem2) {
								player_num = JOptionPane.showInputDialog("How many players are there? \n MUST BE A VALID NUMBER \n 1,2,3,4,5,6,etc. ");
							}
						}
			
						int player_num_int = Integer.parseInt(player_num); //change the String return of JOptionPane to an int that we can use
						
						//get the names of all the players
						String player_name;
						int i_plus1;
						for (int i=0; i < player_num_int; i++) {
							i_plus1 = i + 1;
							player_name = JOptionPane.showInputDialog("What is player" + i_plus1 + "'s name?");
							players.add(new player(player_name,i_plus1));
							((player) players.get(i)).setSpace(1);
						}
						
						
					
						if (debug) {
							System.out.print("players:");
							System.out.println(players);
						}
				
					
			

						
					}
					public void drawPlayers() {
						for (Object i : players) {
							((player) i).draw(board_eq);
							
						}
					}
					public void drawBoard() {
						int x = 10,y;
						int x_prev=0,y_prev,x_initial,y_initial;
			
						y_initial = (int) board_eq.XtoY(0); 
						x_initial = 0;
						
						y_prev = y_initial;
						x_prev = x_initial;
				
						
						drawBackground(Color.MAGENTA);
						
						
						
						page.setColor(Color.GREEN);
						String x_str;
						while (x < 1920) {
							y = (int) board_eq.XtoY(x); //calculate y 
							
							
							
							if (debug) 
							page.drawLine(x_prev, y_prev, x, y); 
							
							if (x % 100  == 0) {
								//draw the spaces
								page.setColor(Color.GREEN);
								page.fillRect(x, y, 100, 100);
								page.setColor(Color.RED);
								page.drawRect(x, y, 100, 100); //outline in red
							
								//put numbers inside the spaces
								page.setFont(new Font("TimesRoman",Font.BOLD,60));
								x_str = String.valueOf(x/100);
								page.drawString(x_str, x+30, y+70);
							}
							
				
							page.setColor(Color.GREEN);
							page.drawArc(700, (int) board_eq.XtoY(600)-50, 500, 100, 0, 180);
							page.drawArc(600, (int) board_eq.XtoY(600)-100, 700, 200, 0, 180);
							x++;
							y_prev = y;
							x_prev = x;
							
							if (debug) {
								System.out.println("x " + x);
								System.out.println("xprev " + x_prev);
								System.out.println("y " + y);
								System.out.println("yprev " + y_prev);
							}
							
						} // <<while (x < 1920) { 
						
						
						//draw start and finish
						page.setColor(Color.GREEN);
						y = (int) board_eq.XtoY(x); //recalculate y
						page.setFont(new Font("TimesRoman",Font.BOLD,70));
						page.drawString("Start", x_initial, y_initial - 100);
						page.drawString("Finish", x-220, y-30);
						
						drawPlayers();
					}
					
					public void Wait(double time) { //Wait(1000) evaluates to about 20 seconds
				
						double Time = time * 10000000;
						double t = 0;
						while (t<Time)
							t++;
					}
				
					
					public void Animation1(player current_player) {
						page.setColor(Color.GREEN);
						int rotationX = 0;
						int x1=(1920/2-100),x2=(1920/2-100),y1=500,y2=500;
					
						box spinbox1 = new box(0,0,0, 100, 0, 0);
						spinbox1.drawV2(x1,y1,0,rotationX,30);
						
						box spinbox2 = new box(0,0,0, 100, 0, 0);
						spinbox2.drawV2(x2,y2,0,-rotationX,30);
						
						sqrt_eq anim1_eq = new sqrt_eq(0.1,1,1,1);
						while (x1 < 2000) {
							y2 = (int) (50*anim1_eq.XtoY(-x2));
							y1 = (int) (50*anim1_eq.XtoY(-x1));
							rotationX++;
							x2--;
							x1++;
							drawBackground(Color.MAGENTA);
							page.setColor(current_player.color);
							page.setFont(new Font("TimesRoman",Font.PLAIN,20));
							
							spinbox1.drawV2(x1,y1 + 100,0,rotationX,30);
							page.drawString(current_player.name,x1+10,y1+125); //putting the player's name inside their box
							
							spinbox2.drawV2(x2,y2 + 100,0,-rotationX,-30);
							page.drawString(current_player.name,x2+10,y2+125); //putting the player's name inside their box
							
							Wait(1);
						}
					}
					
					public void chute12to6(player current_player,equation board_eq) { //very unfinished
						sq_eq chute_eq = new sq_eq(-0.01,500,900,300);
						
					}
					public void drawBackground(Color color) {
						page.setColor(color);
						page.fillRect(0,0,10000,10000);
						page.setColor(Color.BLACK);
					}
					
					public int roll() {
						
						box box_1 = new box(100,10,0,100,50,30);
						page.setFont(new Font("TimesRoman",Font.BOLD,70));
						int rotation__X = 0;
						int rotation__Y = 0;
						int time = -100;
						int x,y;
						x = 100;
						y = 100;
						int side_int = 1;
						String side = "";
						double pause = 0;
						double speed = 10* (1+Math.random());
						double friction = 0.2;
						int RDEVX;
						while (speed > 0) {
							time++;
							rotation__X+= speed;
							rotation__Y+= speed;
							speed -= friction;
						
							//reset background
							if (false) {    //manually change this to switch between fancy and fast rolling modes
							drawBoard();
							} else {
								drawBackground(Color.GREEN);
							}
							
							RDEVX = box_1.drawV2(500,50,0,rotation__X,30);
							
							side_int = (((rotation__X)/45)%4)+1;  //calculate the value on the dice based on the angle of rotation
							
							switch (side_int) { //change the int of side_int into a string to be displayed
								case (1):
									side = "1";
									break;
								case(2):
									side = "2";
									break;
								case(3):
									side = "3";
									break;
								case(4):
									side = "4";
									break;
							}
							
							if (debug)
								System.out.println("side: " + side_int);
							
							if (side_int == 1){
								RDEVX =30-RDEVX;
							}else if (side_int == 2) {
								RDEVX = RDEVX-20;
							}else if (side_int == 3) {
								RDEVX = 100-RDEVX;
							}
							
							page.setFont(new Font("TimesRoman",Font.BOLD,70));
							page.drawString(side,500+RDEVX,100);
							
						
							pause = 0;
							while (pause< 11000000) {
								pause++;
							}	
						}
						
						return side_int;
					
					}
					
					
			
				}
		
	
	}
	
}
