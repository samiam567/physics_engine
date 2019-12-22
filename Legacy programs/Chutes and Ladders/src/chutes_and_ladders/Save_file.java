package chutes_and_ladders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;



public class Save_file  implements Serializable{
		public String player_num;
		public Object board_eq;
		public boolean game_on = true;
		public int start, player_num_int, i_plus1, games;
		
		//create player names array
		public String[] players_names1 = {};
		public ArrayList<String> players_names = new ArrayList<String>(Arrays.asList(players_names1));
		
		// create players array
		public Object[] players1 = {};
		public ArrayList<Object> players;

		public Save_file(String player_num2, Object board_eq2, boolean game_on2,int start2, int player_num_int2, int i_plus12, int games2, Object[] players12, ArrayList<Object> players2, String[] players_names12,ArrayList<String> players_names2) { 
			
			player_num = player_num2;
			board_eq = board_eq2;
			game_on = game_on2;
			start = start2;
			player_num_int = player_num_int2;
			i_plus1 = i_plus12;
			games = games2;
			players1 = players12;
			players = players2;
			players_names1 = players_names12;
			players_names = players_names2;
		}


	

		}