package physics_sims;

import java.util.ArrayList;

public class WordScapes_solver {
	
	private static String[] letters = {"t","r","t","i","e","f"};
	private static String[] wordConstraints = {"t","","","",""};
	
	public static void main(String[] args) {
			
		
		ArrayList<String> wordPossibilities = new ArrayList<String>();
		ArrayList<String> lettersLst = new ArrayList<String>();
		
		int wordSize = wordConstraints.length; 
		
		long combinations = factorial(letters.length)/factorial(letters.length - wordSize);
		
		String guess,letter;
		int indx;
		for (int x = 0; x < combinations; x++) {
			
			do{
				do {
					guess = "";
					for (String ltr : letters) lettersLst.add(ltr);
					for(int z = 0; z < wordSize; z++) {
						
						do {
							indx = (int) Math.round(Math.random() *(lettersLst.size()-1));
							letter = lettersLst.get(indx);
							
							lettersLst.remove(indx);
						}while(countLetter(guess,letter) >= countLetter(letters,letter));
						guess += letter;
					}
				}while(! wordFollowsConstraints(guess));
			}while (wordPossibilities.contains(guess));
			System.out.println(guess);
			wordPossibilities.add(guess);
		
		}
	}
	
	
	
	private static boolean wordFollowsConstraints(String guess) {
		for (int i = 0; i < wordConstraints.length; i++) {
			if (wordConstraints[i].length() > 0) {
				if (! (guess.substring(i, i+1).equals(wordConstraints[i]))) return false;
			}
		}
		return true;
	}



	private static int factorial(int x) {
		int q = 1;
		while(x>0) {
			q *= x;
			x--;
		}
		return q;
	}
	
	private static int countLetter(String word, String letter) {
		int count = 0, index;
		while (word.contains(letter)) {
			count++;
			index = word.indexOf(letter);
			word = word.substring(0, index) + word.substring(index+1,word.length());
		}
			
		return count;
	}
	
	private static int countLetter(String[] letterLst, String letter) {
		int count = 0;
		
		for (String character : letterLst) {
			if (character.equals(letter)) count++;
		}
			
		return count;
	}
	}