package chutes_and_ladders;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Chutes_and_ladders_cpu extends Canvas {
	public boolean debug = false;

	public void paint(Graphics page) {

		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		
		// Start screen
		page.setColor(Color.MAGENTA);
		page.fillRect(0, 0, 10000, 10000);
		page.setColor(Color.GREEN);
		page.setFont(new Font("TimesRoman", Font.BOLD, 70));
		page.drawString("Chutes and Ladders", 600, 300);

		// objects--------------------
		class point  {
			int x, y, z; // Z=0 is at the screen and as z increases, the object moves further away.

			public point(int a, int b, int c) {
				x = a;
				y = b;
				z = c;
			}
		}

		class three_d_object  {
			int x;
			int y;
			int z; // Z=0 is at the screen and as z increases, the object moves further away.
			int ob_size; // the size of the object
			int rotation_X; // how much the object is rotated in the x direction. (degrees) (about the Y
							// axis)
			int rotation_Y; // how much the object is rotated in the y direction. (degrees) (about the X
							// axis)

			public three_d_object(int ob_x, int ob_y, int ob_z, int new_ob_size, int new_ob_rotation_X,
					int new_ob_rotation_Y) {
				x = ob_x;
				y = ob_y;
				z = ob_z;
				ob_size = new_ob_size;
				rotation_X = new_ob_rotation_X;
				rotation_Y = new_ob_rotation_Y;

			}

		}

		class box extends three_d_object  {

			public box(int ob_x, int ob_y, int ob_z, int new_ob_size, int new_ob_rotation_X, int new_ob_rotation_Y) {
				super(ob_x, ob_y, ob_z, new_ob_size, new_ob_rotation_X, new_ob_rotation_Y);
			}

			public int drawV2(int x, int y, int z, int rotationX, int rotationY) { // second version of draw (cannot do
																					// rotation in multiple dimensions

				if (debug) {
					System.out.println("RotX" + rotationX);
					System.out.println("RotY" + rotationY);
				}

				int size = ob_size - z;
				double rotationX_double = (double) rotationX; // cast rotationX as a double (must be a double to do
																// division)
				double rotation_X_rad = (rotationX_double / 180) * Math.PI; // convert rotation_X to radians
				double width_double = size * (Math.cos(rotation_X_rad));
				int width = (int) width_double;

				if (debug)
					System.out.println("Width: " + width);

				double rotationY_double = (double) rotationY; // cast rotationY as a double (must be a double to do
																// division)
				double rotation_Y_rad = (rotationY_double / 180) * Math.PI; // convert rotation_Y to radians
				double height_double = size * (Math.cos(rotation_Y_rad));
				int height = (int) height_double;

				if (debug)
					System.out.println("height: " + height);

				width = Math.abs(width);
				height = Math.abs(height);

				int rotate_deviation_X = size - width;
				int rotate_deviation_Y = size - height;

				if (debug) {
					System.out.println("RDEVX: " + rotate_deviation_X);
					System.out.println("RDEVY: " + rotate_deviation_Y);
				}

				page.drawRect(x, y, width, height); // front edges

				page.drawRect(x + rotate_deviation_X, y - rotate_deviation_Y, width, height); // back edges

				page.drawLine(x, y, x + (rotate_deviation_X), y - (rotate_deviation_Y)); // top left edge
				page.drawLine(x, y + height, x + (rotate_deviation_X), y - (rotate_deviation_Y) + height); // top right
																											// edge

				page.drawLine(x + width, y, x + (rotate_deviation_X) + width, y - (rotate_deviation_Y)); // bottom left
																											// edge
				page.drawLine(x + width, y + height, x + (rotate_deviation_X) + width,
						y - (rotate_deviation_Y) + height); // bottom right edge

				// ----------------------------------------
				return rotate_deviation_X;
			}
		}

		class question  { // unfinished

		}

		// --------------------------------------------
		// Abstract stuff -------------------------------------------
		class equation implements Serializable {
			public double a, b, h, k;

			public equation(double q, double i, double r, double z) {
				a = q;
				b = i;
				h = r;
				k = z;
			}

			public double XtoY(double x) {
				return x;
			}

		}
		class sq_eq extends equation implements Serializable {
			public double a, b, h, k;

			public sq_eq(double q, double i, double r, double z) {
				super(q, i, r, z);
				a = q;
				b = i;
				h = r;
				k = z;

			}

			public double XtoY(int x) {
				if (debug)
					System.out.println("a,b,h,k " + a + b + h + k);

				double y = (double) (a * Math.pow((b * x - h), 2) + k);
				return y;
			}
		}

		class sqrt_eq extends equation implements Serializable {
			public double a, b, h, k;

			public sqrt_eq(double q, double i, double r, double z) {
				super(q, i, r, z);
				a = q;
				b = i;
				h = r;
				k = z;

			}

			public double XtoY(int x) {
				if (debug)
					System.out.println("a,b,h,k " + a + b + h + k);

				double y = (double) (a * Math.sqrt(b * x - h) + k);
				return y;
			}
		}

		class sin_eq extends equation implements Serializable {
			public double a, b, h, k;

			public sin_eq(double q, double d, double r, double z) {
				super(q, d, r, z);
				a = q;
				b = d;
				h = r;
				k = z;
				if (debug)
					System.out.println("new sin_eq");
			}

			public double XtoY(int x) {
				if (debug) {
					System.out.println("a,b,h,k " + a + b + h + k);
				}

				double y = (double) (a * Math.sin(b * x - h) + k);
				return y;
			}

		}

	

		// starts here
		// ===========================================================================================================================

		class Game  implements Serializable{
			public String player_num;
			public sin_eq board_eq = new sin_eq((int) ((Math.random() + 1) * 50), 0.01, 1000, 500);
			public boolean game_on = true;
			public int start, player_num_int, i_plus1, games;
			public Font bigFont = new Font("TimesRoman", Font.BOLD, 60);
			//create player names array
			public String[] players_names1 = {};
			public ArrayList<String> players_names = new ArrayList<String>(Arrays.asList(players_names1));
			
			// create players array
			public Object[] players1 = {};
			public ArrayList<Object> players = new ArrayList<Object>(Arrays.asList(players1));
			
			public void setup(int games) { // setup the game

				start = 4;
				while (start != 0)
					start = JOptionPane.showConfirmDialog(window, "Start?", "Press YES to start", 1, 1, null);

				// use try/catch statements to find the number of players and make sure it's a
				// valid int
				String player_num = JOptionPane.showInputDialog("How many players are there?");

				try {
					setPlayerNum(player_num);
					setPlayerNumInt(Integer.parseInt(player_num));

				} catch (NumberFormatException problem) {

					try {
						setPlayerNum(JOptionPane.showInputDialog("How many players are there? \n MUST BE A VALID NUMBER"));
						setPlayerNumInt(Integer.parseInt(player_num));
					} catch (NumberFormatException problem2) {

						setPlayerNum(JOptionPane.showInputDialog("How many players are there? \n MUST BE A VALID NUMBER \n 1,2,3,4,5,6,etc. "));
					}
				}

				setPlayerNumInt(Integer.parseInt(player_num)); // change the String return of JOptionPane to an int that we can use
																

				// get the names of all the players
				String player_name;
				int i_plus1;
				for (int i = 0; i < player_num_int; i++) {
					i_plus1 = i + 1;
					player_name = JOptionPane.showInputDialog("What is player" + i_plus1 + "'s name?");
					
					players_names.add(player_name);
					
					players.add(new Player(player_name, i_plus1));
					((Player) players.get(i)).setSpace(1);
				}

				if (debug) {
					System.out.print("players:");
					System.out.println(players);
				}
			}

			public void run(int player_num_int) {

				drawBoard();
				
				int spaces;
				int player_key = 0;
				int player_key_plus1;
				game_on = true;
				Player current_player = (Player) players.get(player_key);
				;

				// moving all players back to space 1
				Player reset_player = null;
				for (int reset_player_key = 0; reset_player_key < player_num_int; reset_player_key++) {
					reset_player = (Player) players.get(reset_player_key);
					reset_player.setSpace(1);
				}

				while (game_on) {
					if (debug)
						System.out.println("player_num: " + player_num);

					if (player_key == player_num_int)
						player_key = 0; // if we have reached the end of the list of players, cycle back to player1.

					current_player = (Player) players.get(player_key);

					player_key_plus1 = player_key + 1;
					page.setColor(Color.GREEN);
					page.setFont(new Font("TimesRoman", Font.BOLD, 70));
					page.drawString(current_player.name + "'s turn", 1300, 200);
					JOptionPane.showMessageDialog(window, "Press OK to Roll", current_player.name, 1);
					spaces = roll();

					current_player.setSpace(current_player.space + spaces); // move the player forward the number of spaces that they rolled
					drawBoard();

					player_key++;

					if (current_player.space == 18) { // is the player on the winning square?
						game_on = false;
						JOptionPane.showMessageDialog(window, current_player.name + " won!", "Game Over", 1);
						current_player.addWin(); // add one win to the winning player's score
						
						
						// display how many wins the  players have
						Player i;
						for (Object i_o : players) {
							i = (Player) i_o;
							if (i.wins == 1) {
								System.out.println(i.name + " now has 1 win!");
							} else {
								System.out.println(i.name + " now has " + i.wins + " wins!");
							}
						}

					} else if (current_player.space > 18) {
						JOptionPane.showMessageDialog(window,current_player.name + " has overshot the finish and slid back 5 spaces!",current_player.name + " hit a chute!", 1);
						current_player.setSpace(current_player.space - 5); // move the current player back 5 spaces
						drawPlayers();

					} else if (current_player.space == 12) { // has the player hit the chute on space 12?
						JOptionPane.showMessageDialog(window,current_player.name + " has hit a chute and slid back 6 spaces!",current_player.name + " hit a chute!", 1);
						current_player.setSpace(current_player.space - 6); // move the current player back 5 spaces
						chute12to6((Player) current_player, board_eq); // falling down the chute animation 
						drawPlayers();
					}
				}
				Animation1(current_player);

			}

			

			public void setPlayerNum(String num) {
				player_num = num;
			}

			public void setPlayerNumInt(int num) {
				player_num_int = num;
			}
			
			public void drawPlayer(Player current_player,sin_eq draw_board_eq,int player_x,int player_y,int rotation_x, int rotation_y) {
				
				page.setColor(current_player.color);
				if (debug) {
					System.out.println("player" + current_player.id + "x: " + player_x);
					System.out.println("player" + current_player.id + "y: " + player_y);
				}

				(new box(player_x, 50, 50, 50, 50, 50)).drawV2(player_x, player_y + 3 * current_player.id, 0, rotation_x, rotation_y); // creating a new box and drawing it

				page.setFont(new Font("TimesRoman", Font.PLAIN, 20));
				page.drawString(current_player.name, player_x + 10, player_y + 25); // putting the player's name inside their box
				
			}
			
			public void drawPlayerOnSpace(Player current_player,sin_eq draw_board_eq) {
				
				
				int player_x = (current_player.space) * 100;
				int player_y = ((int) draw_board_eq.XtoY(player_x)) + 10;
				
				page.setColor(current_player.color);
				(new box(player_x, 50, 50, 50, 50, 50)).drawV2(player_x, player_y + 3 * current_player.id, 0, 30, 30); // creating a new box and drawing it

				page.setFont(new Font("TimesRoman", Font.PLAIN, 20));
				page.drawString(current_player.name, player_x + 10, player_y + 25); // putting the player's name inside their box
				
			}
			
			public void drawPlayers() {
				Player i;
				sin_eq draw_board_eq = (sin_eq) board_eq; // this needs to be changed with equation
				for (Object i_o : players) {
					i = (Player) i_o;
					drawPlayerOnSpace(i,draw_board_eq);

				}
			}

			public void drawBoard() {
				int x = 10, y = 0;
				int x_prev = 0, y_prev = 10;
				double board600 = board_eq.XtoY(600);
			

			

				drawBackground(Color.MAGENTA);
				page.setColor(Color.GREEN);
				String x_str;
				
				while (x < 1920) {
					y = (int) board_eq.XtoY(x); // calculate y

					if (debug) page.drawLine(x_prev, y_prev, x, y);

					if (x % 100 == 0) {
						// draw the spaces
						page.setColor(Color.GREEN);
						page.fillRect(x, y, 100, 100);
						page.setColor(Color.RED);
						page.drawRect(x, y, 100, 100); // outline in red

						// put numbers inside the spaces
						page.setFont(bigFont);
						x_str = String.valueOf(x / 100);
						page.drawString(x_str, x + 30, y + 70);
					}

					
				//	page.drawArc(700, (int) board600 - 50, 500, 100, 0, 180);  //these arcs add around 20 ms to the time it takes to run this method! (only takes ~12 without them)
				//	page.drawArc(600, (int) board600 - 100, 700, 200, 0, 180);
					x+= 10;
					y_prev = y;
					x_prev = x;


				} // <<while (x < 1920) {

				// draw start and finish
				page.setColor(Color.GREEN);

				page.drawString("Start", 0, (int) board_eq.XtoY(0));
				page.drawString("Finish", x - 220, y - 30);
				drawPlayers();
				
			}

			public void Wait(double time) { // Wait(1000) evaluates to about 20 seconds

				double Time = time * 10000000;
				double t = 0;
				while (t < Time)
					t++;
			}

			public void Animation1(Player current_player) {
				page.setColor(Color.GREEN);
				int rotationX = 0;
				int x1 = (1920 / 2 - 100), x2 = (1920 / 2 - 100), y1 = 500, y2 = 500;

				box spinbox1 = new box(0, 0, 0, 100, 0, 0);
				spinbox1.drawV2(x1, y1, 0, rotationX, 30);

				box spinbox2 = new box(0, 0, 0, 100, 0, 0);
				spinbox2.drawV2(x2, y2, 0, -rotationX, 30);

				sqrt_eq anim1_eq = new sqrt_eq(0.1, 1, 1, 1);
				while (x1 < 2000) {
					y2 = (int) (50 * anim1_eq.XtoY(-x2));
					y1 = (int) (50 * anim1_eq.XtoY(-x1));
					rotationX++;
					x2--;
					x1++;
					drawBackground(Color.MAGENTA);
					page.setColor(current_player.color);
					page.setFont(new Font("TimesRoman", Font.PLAIN, 20));

					spinbox1.drawV2(x1, y1 + 100, 0, rotationX, 30);
					page.drawString(current_player.name, x1 + 10, y1 + 125); // putting the player's name inside their box

					spinbox2.drawV2(x2, y2 + 100, 0, -rotationX, -30);
					page.drawString(current_player.name, x2 + 10, y2 + 125); // putting the player's name inside their
																				// box

					Wait(1);
				}
			}

			public void chute12to6(Player current_player, equation board_eq) {
				long duration = 0;
				int board_y = (int)board_eq.XtoY(590);
				
				sq_eq chute_eq = new sq_eq(0.001, 1, 950,-250);
				int x_prev = 550, y_prev = board_y, x_initial, y_initial;
			
				y_initial = (int) chute_eq.XtoY(0);
				x_initial = 1250;
				
				y_prev = y_initial;
				page.setColor(Color.GREEN);
				int x = x_initial;
				int y;
				int rotation_x = 0;
				while (x > 600) {
					
					y =  (int)( chute_eq.XtoY(x) + board_y ); // calculate y
					page.drawLine(x_prev, y_prev, x, y);
					x-= 5;
					rotation_x-= 2;
					y_prev = y;
					x_prev = x;
					
					drawBoard();				
					drawPlayer(current_player,(sin_eq) board_eq,x,y,rotation_x,30);
					
					Wait(1);		
					
				}
	
				
			}

			public void drawBackground(Color color) {
				page.setColor(color);
				page.fillRect(0, 0, 10000, 10000);
			}

			public int roll() {
				
				boolean fastRollingMode = false;
				
				int side_int = 1;
				if (fastRollingMode) {
					side_int = (int) Math.round((((Math.random() * 100) % 3)+1));
					System.out.println("You rolled: " + side_int);
							
				}else{
					box box_1 = new box(100, 10, 0, 100, 50, 30);
					page.setFont(bigFont);
					int rotation__X = 0;
					int rotation__Y = 0;
					int time = -100;
					int x, y;
					x = 100;
					y = 100;
					String side = "";
					double speed = 10 * (1 + Math.random());
					double friction = 0.2;
					int RDEVX;
					while (speed > 0) {
						time++;
						rotation__X += speed;
						rotation__Y += speed;
						speed -= friction;
	
						// reset background
						if (false) { // manually change this to switch between fancy and fast rolling modes
							drawBoard();
						} else {
							drawBackground(Color.GREEN);
						}
						
						page.setColor(Color.BLACK);
						RDEVX = box_1.drawV2(500, 50, 0, rotation__X, 30);
	
						side_int = (((rotation__X) / 45) % 4) + 1; // calculate the value on the dice based on the angle of
																	// rotation
	
						switch (side_int) { // change the int of side_int into a string to be displayed
						case (1):
							side = "1";
							break;
						case (2):
							side = "2";
							break;
						case (3):
							side = "3";
							break;
						case (4):
							side = "4";
							break;
						}
	
						if (debug)
							System.out.println("side: " + side_int);
	
						if (side_int == 1) {
							RDEVX = 30 - RDEVX;
						} else if (side_int == 2) {
							RDEVX = RDEVX - 20;
						} else if (side_int == 3) {
							RDEVX = 100 - RDEVX;
						}
	
						page.setFont(bigFont);
						page.drawString(side, 500 + RDEVX, 100);
	
						Wait(1.5);
					}
	
					
				} //if fastrollingmode
				return side_int;

			}

		}
		
		
		int games = 0;
		Save_file save = null;
		Game game = new Game();
		Chutes_And_Ladders_Main main = new Chutes_And_Ladders_Main();
		if (JOptionPane.showConfirmDialog(window, "Load Game from save file?", "Load Game?", 1, 1, null) == 0) {
			
			// loading game from the save file
			
			try {
				save = main.loadCLSave();
			} catch (ClassNotFoundException e) {
				System.out.println("ClassNotFoundException in load process");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("IOException in load process");
				e.printStackTrace();
			}
			
			//loading vars from save file
			game.player_num = save.player_num;
			game.board_eq = (sin_eq) save.board_eq;
			game.game_on = save.game_on;
			game.start = save.start;
			game.player_num_int = save.player_num_int;
			game.player_num = save.player_num;
			game.i_plus1 = save.i_plus1;
			game.games = save.games;
			game.players1 = save.players1;
			game.players = save.players;
			
			
		} else {
			
			game.setup(games);
		}
		do {
			
			game.run(game.player_num_int);
			games++;

			//Save the game -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
			 int save_option = 2; // for the jOptionPane, 2 would be the cancel option 
			 while(save_option == 2) { 
				 save_option = JOptionPane.showConfirmDialog(window,"Do you want to save your progress?", "Save?", 1, 1, null); 
				 if (save_option == 0) {
					
						try {
							main.saveCLSave( game.player_num,game.board_eq,game.game_on ,game.start, game.player_num_int, game.i_plus1, game.games,game.players1,game.players,game.players_names1,game.players_names);
						}catch(IOException e) {
							System.out.println("IOException in the save process");
						}
					
				 }
			 }
			//-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
			 
		} while (JOptionPane.showConfirmDialog(window, "Another Game?", "Continue?", 1, 1, null) == 0);
		window.setVisible(false);

	}

}
