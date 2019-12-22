package spanish_verb_conjugator;

import java.applet.Applet;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Spanish_verb_conjugator_cpu extends Applet{
	
	public enum subjects {yo,tú,él,ellos,nosotros,vosotros,empty};
	public enum endings {ar,er,ir};
	
	public static boolean stringIsIn(String value,String[] list) {
		return Arrays.asList(list).contains(value);
	}
	
	public static String[] checkForStemChanger(subjects subject,String verb,String verb_infinitive,String tense,int tense_key,int subject_key,int ending_key,String verb_ending,String[][][] current_verb) {
		String[] present_ir_stem_changers = {"consentir","advertir","convertir","divertir","hervir","mentir","preferir","referir","sentir","pedir","despedir","gemir","impedir","repetir","servir","medir","reír","reñir","sonreír","vestir","seguir","conseguir","perseguir","proseguir"};
		
		//STEM CHANGERS
		
		if (tense == "present") {
			System.out.println("Verb_inf: " + verb_infinitive);
			if ((verb_infinitive.length() > 3) && (verb_infinitive.substring(verb_infinitive.length()-3, verb_infinitive.length())).equals("cer") || (verb_infinitive.substring(verb_infinitive.length()-3, verb_infinitive.length())).equals("cir") && ( subject.equals(subjects.yo)  ) ) {
				verb = verb.substring(0,verb.length()-1) + "zc";
			}
		}else if (tense == "preterite") {  //preterite
			
			// <el & ellos> i to y (normal) if the verb ends in uir
				if ( (verb_infinitive.substring(verb_infinitive.length()-3, verb_infinitive.length())).equals("uir") && ( subject.equals(subjects.él) || subject.equals(subjects.ellos) ) ) {
					verb_ending = verb_ending.replace("i", "y");
				}
			
		
			// <el & ellos> i to y (accented nosotros and tu)
				//ends in eer or is oír 
				if ( (verb_infinitive.substring(verb_infinitive.length()-3, verb_infinitive.length())).equals("eer") )  {
					if  ( subject.equals(subjects.él) || subject.equals(subjects.ellos) ) {
						verb_ending = verb_ending.replace("i", "y");
						
					}else if ( subject.equals(subjects.tú) ) {
						verb_ending = current_verb[tense_key][subject_key][ending_key];
						verb_ending = "í" + verb_ending.substring(1,verb_ending.length()); //putting an accent of the i in iste
						
					}else if (subject.equals(subjects.nosotros)) {
						verb_ending = current_verb[tense_key][subject_key][ending_key];
						verb_ending = "í" + verb_ending.substring(1,verb_ending.length()); //putting an accent of the i in imos
					}
				}
			
			//car,gar,zar
				
				if ( (verb_infinitive.substring(verb_infinitive.length()-3, verb_infinitive.length())).equals("car") && ( subject.equals(subjects.yo) ) ) {
					verb = verb.substring(0, verb.length()-1);
					verb = verb + "qu";
					
				}else if ( (verb_infinitive.substring(verb_infinitive.length()-3, verb_infinitive.length())).equals("gar") && ( subject.equals(subjects.yo) ) ) {
					verb = verb.substring(0, verb.length()-1);
					verb = verb + "gu";
					
				}else if ( (verb_infinitive.substring(verb_infinitive.length()-3, verb_infinitive.length())).equals("zar") && ( subject.equals(subjects.yo) ) ) {
					verb = verb.substring(0, verb.length()-1);
					verb = verb + "c";
				}
				
			//<el & ellos> e to i (only ir verbs, same as present tense stem changers) (Oof XP)
				if ( (subject.equals(subjects.él) || subject.equals(subjects.ellos) ) && stringIsIn(verb_infinitive,present_ir_stem_changers)) {
					
					//it's always the second "e" that changes, so we must invert the verb then use the .replace() method
					String verb_inverted = "";
					for (int i = verb.length() ; i > 0; i--) {
						verb_inverted += verb.substring(i-1,i);
					}
					
					verb_inverted = verb_inverted.replaceFirst("e", "i"); //replace e with i
					
					//invert back!
					verb = "";
					for (int i = verb_inverted.length() ; i > 0; i--) { 
						verb += verb_inverted.substring(i-1,i);
					}
					
				
				}
				
		}else if (tense == "imperfect") {
			
		}
		
		 String[] return_package = {verb,verb_ending};
		return return_package;
	}
	
	public static void main(String[] args) {
		//instantiating vars
		String tense;
		String conj_verb = null;
		int subject_key = 100;
		int ending_key = 100;
		int tense_key = 100;
		boolean reflexive = false;
		String verb_infinitive;
		String subject_in;
		String verb;
		subjects subject;
		
		String[] reflexive_endings = {"me","te","se","se","nos"};
		String[] tenses = {"present","preterite","imperfect","future"};
		
		
		//regular verbs
		String[][] present = new String[5][3];
		                                   		
		present[0][0] = "o";	present[0][1] = "o";	present[0][2] = "o";
		present[1][0] = "as";	present[1][1] = "es";	present[1][2] = "es";
		present[2][0] = "a";	present[2][1] = "e";	present[2][2] = "e";
		present[3][0] = "an";	present[3][1] = "en";	present[3][2] = "en";
		present[4][0] = "amos"; present[4][1] = "emos";	present[4][2] = "imos";
		
		
		String[][] preterite = new String[5][3];
			
		preterite[0][0] = "é";		preterite[0][1] = "í";		preterite[0][2] = "í";
		preterite[1][0] = "aste";	preterite[1][1] = "iste";	preterite[1][2] = "iste";
		preterite[2][0] = "ó";		preterite[2][1] = "ió";		preterite[2][2] = "ió";
		preterite[3][0] = "aron";	preterite[3][1] = "ieron";	preterite[3][2] = "ieron";
		preterite[4][0] = "amos";	preterite[4][1] = "imos";	preterite[4][2] = "imos";
		
		
		String[][] imperfect = new String[5][3];
		
		imperfect[0][0] = "aba";	imperfect[0][1] = "ía";		imperfect[0][2] = "ía";
		imperfect[1][0] = "abas";	imperfect[1][1] = "ías";	imperfect[1][2] = "ías";
		imperfect[2][0] = "aba";	imperfect[2][1] = "ía";		imperfect[2][2] = "ía";
		imperfect[3][0] = "aban";	imperfect[3][1] = "ían";	imperfect[3][2] = "ían";
		imperfect[4][0] = "ábamos";	imperfect[4][1] = "íamos";	imperfect[4][2] = "íamos";
		
		String[][] future = new String[5][3];
		
		future[0][0] = "é"; 	 future[0][1] = "é";		future[0][2] = "é";
		future[1][0] = "ás";	 future[1][1] = "ás";	future[1][2] = "ás";
		future[2][0] = "á";	 future[2][1] = "á";		future[2][2] = "á";
		future[3][0] = "án";	 future[3][1] = "án";	future[3][2] = "án";
		future[4][0] = "emos";future[4][1] = "emos";	future[4][2] = "emos";
		
		String[][][] regular_endings = {present,preterite,imperfect,future};  //put all the regular endings into 1 huge list
		
		//irregulars
				//ser
				String[][] present_ser = new String[5][3];	String[][] preterite_ser = new String[5][3];	String[][] imperfect_ser = new String[5][3]; 
				                                   		
				present_ser[0][1] = "soy";					preterite_ser[0][1] = "fuí";					imperfect_ser[0][1] = "era";
				present_ser[1][1] = "eres";					preterite_ser[1][1] = "fuiste";					imperfect_ser[1][1] = "eras";
				present_ser[2][1] = "es";					preterite_ser[2][1] = "fue";					imperfect_ser[2][1] = "era";
				present_ser[3][1] = "son";					preterite_ser[3][1] = "fueron";					imperfect_ser[3][1] = "eran";
				present_ser[4][1] = "somos";				preterite_ser[4][1] = "fuimos";					imperfect_ser[4][1] = "éramos";	
					String[][][] ser = {present_ser,preterite_ser,imperfect_ser};  //put all the regular endings into 1 huge list
				
				//ir
				String[][] present_ir = new String[5][3];	String[][] preterite_ir = new String[5][3];	String[][] imperfect_ir = new String[5][3];
           		
				present_ir[0][2] = "voy";					preterite_ir[0][2] = "fui";					imperfect_ir[0][2] = "iba";
				present_ir[1][2] = "vas";					preterite_ir[1][2] = "fuiste";				imperfect_ir[1][2] = "ibas";
				present_ir[2][2] = "va";					preterite_ir[2][2] = "fue";					imperfect_ir[2][2] = "iba";
				present_ir[3][2] = "van";					preterite_ir[3][2] = "fueron";				imperfect_ir[3][2] = "iban";
				present_ir[4][2] = "vamos";					preterite_ir[4][2] = "fuimos";				imperfect_ir[4][2] = "íbamos";	
					String[][][] ir = {present_ir,preterite_ir,imperfect_ir};  //put all the regular endings into 1 huge list
				
				//oír
				String[][] present_oír = new String[5][3];	String[][] preterite_oír = new String[5][3];	String[][] imperfect_oír = new String[5][3];
	           		
				present_oír[0][2] = "oigo";					preterite_oír[0][2] = "oí";					imperfect_oír[0][2] = "oía";
				present_oír[1][2] = "oyes";					preterite_oír[1][2] = "oíste";				imperfect_oír[1][2] = "oías";
				present_oír[2][2] = "oye";					preterite_oír[2][2] = "oyó";				imperfect_oír[2][2] = "oía";
				present_oír[3][2] = "oyen";					preterite_oír[3][2] = "oyeron";				imperfect_oír[3][2] = "oían";
				present_oír[4][2] = "oímos";				preterite_oír[4][2] = "oímos";				imperfect_oír[4][2] = "oíamos";	
					String[][][] oír = {present_oír,preterite_oír,imperfect_oír};  //put all the regular endings into 1 huge list			
					
				
				//dormir
				String[][] present_dormir = new String[5][3];	String[][] preterite_dormir = new String[5][3];	String[][] imperfect_dormir = new String[5][3];
		           		
				present_dormir[0][2] = "duermo";																
				present_dormir[1][2] = "duermes";																
				present_dormir[2][2] = "duerme";					preterite_dormir[2][2] = "durmió";			
				present_dormir[3][2] = "duermen";					preterite_dormir[3][2] = "durmieron";		
						
					String[][][] dormir = {present_dormir,preterite_dormir,imperfect_dormir};  //put all the regular endings into 1 huge list			
						
						
				//morir
				String[][] present_morir = new String[5][3];	String[][] preterite_morir = new String[5][3];	String[][] imperfect_morir = new String[5][3];
		          		
				present_morir[0][2] = "muero";																	
				present_morir[1][2] = "mueres";																	
				present_morir[2][2] = "muere";					preterite_morir[2][2] = "murió";				
				present_morir[3][2] = "mueren";					preterite_morir[3][2] = "murieron";	
					String[][][] morir = {present_morir,preterite_morir,imperfect_morir};  //put all the regular endings into 1 huge list			
							
				//caber
				String[][] present_caber = new String[5][3];	String[][] preterite_caber = new String[5][3];	String[][] imperfect_caber = new String[5][3];
		           		
				present_caber[0][1] = "quepo";					preterite_caber[0][1] = "cupe";					
																preterite_caber[1][1] = "cupiste";				
																preterite_caber[2][1] = "cupo";				
																preterite_caber[3][1] = "cupieron";			
																preterite_caber[4][1] = "cupimos";				
						String[][][] caber = {present_caber,preterite_caber,imperfect_caber};  //put all the regular endings into 1 huge list			
					
				//decir	(unfinished)
				String[][] present_decir = new String[5][3];	
				present_decir[0][2] = "digo";
				present_decir[1][2] = "dices";
				present_decir[2][2] = "dice";
				present_decir[3][2] = "dicen";
				present_decir[3][2] = "decimos";
				String[][][] decir = {present_decir};
				
				//hacer (unfinished)	
				String[][] present_hacer = new String[5][3];	String[][] preterite_hacer = new String[5][3];
				present_hacer[0][1] = "hago";					preterite_hacer[0][1] = "hizo";
				String[][][] hacer = {present_hacer,preterite_hacer};
				
				//cocer	 (unfinished)
				String[][] present_cocer = new String[5][3];	
				present_cocer[0][1] = "cuezo";
				String[][][] cocer = {present_cocer};
				
				//dar	 (unfinished)
				String[][] present_dar = new String[5][3];	
				present_dar[0][0] = "doy";
				String[][][] dar = {present_dar};
				
				//poner	 (unfinished)
				String[][] present_poner = new String[5][3];	
				present_poner[0][1] = "pongo";
				String[][][] poner = {present_poner};
				
				//caer	 (unfinished)
				String[][] present_caer = new String[5][3];	
				present_caer[0][1] = "caigo";
				String[][][] caer = {present_caer};
				
				//saber	 (unfinished)
				String[][] present_saber = new String[5][3];	
				present_saber[0][1] = "sé";
				String[][][] saber = {present_saber};
				
				//salir	 (unfinished)
				String[][] present_salir = new String[5][3];	
				present_salir[0][2] = "salgo";
				String[][][] salir = {present_salir};
				
				//traer	 (unfinished)
				String[][] present_traer = new String[5][3];	
				present_traer[0][1] = "traigo";
				String[][][] traer = {present_traer};
				
				//valer	 (unfinished)
				String[][] present_valer = new String[5][3];	
				present_valer[0][1] = "valgo";
				String[][][] valer = {present_valer};
				
				//haber	 (unfinished)
				String[][] present_haber = new String[5][3];	
				present_haber[0][1] = "he";
				present_haber[1][1] = "has";
				present_haber[2][1] = "ha";
				present_haber[3][1] = "han";
				present_haber[4][1] = "hemos";
				String[][][] haber = {present_haber};
				
				String[][][][] verb_endings = {regular_endings,ser,ir,oír,dormir,morir,caber,decir,hacer,cocer,dar,poner,caer,saber,salir,traer,valer,haber};
				String[] verb_endings_names = {"regular_endings","ser","ir","oír","dormir","morir","caber","decir","hacer","cocer","dar","poner","caer","saber","salir","traer","valer","haber"};		

		
		boolean problem =  false; //this will be used for input while loops. if the input is invalid, this will be changed to true. That will tell the loop to go back and ask for the input again.
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		
		window.setFont(new Font("TimesRoman",Font.PLAIN,100));
	

	
		
		int do_another;
		do {
			
			problem = false;
			do  {
				
				problem = false;
				subject_in = JOptionPane.showInputDialog(window, "What's the subject?");
				verb = JOptionPane.showInputDialog(window, "What's the verb?");
				
		
				
				tense = (String) JOptionPane.showInputDialog(window, "what is the tense?", "tense", 3, null, tenses, null);
				while (tense == null) {
					tense = (String) JOptionPane.showInputDialog(window, "what is the tense? (Choose 1)", "tense", 3, null, tenses, null);
				}
				
				subject_in = subject_in.toLowerCase();
				verb = verb.toLowerCase();
				
	
				
				//finding our subject_key
				subject = subjects.empty;
				
				if ((subject_in.equals("yo")) || (subject_in.equals("i"))) {
					subject = subjects.yo;
					subject_key = 0;
				}else if ((subject_in.equals("tú")) || (subject_in.equals("tu")) || (subject_in.equals("you"))) {
					subject = subjects.tú;	
					subject_key = 1;
				}else if ((subject_in.equals("él")) || (subject_in.equals("el")) || (subject_in.equals("ella")) || (subject_in.equals("he")) || (subject_in.equals("she")) || (subject_in.equals("usted")) ) {
					subject = subjects.él;
					subject_key = 2;
				}else if ((subject_in.equals("ellos")) || (subject_in.equals("ellas")) || (subject_in.equals("they")) || (subject_in.equals("ustedes")) ) {
					subject = subjects.ellos;
					subject_key = 3;
				}else if ((subject_in.equals("nosotros")) || (subject_in.equals("nosotras")) || (subject_in.equals("we"))) {
					subject = subjects.nosotros;
					subject_key = 4;
				}else {
					JOptionPane.showMessageDialog(window, "invalid subject (must be a pronoun)","invalid input error:", 1);
					problem = true;
					subject = subjects.empty;
				}
				
				boolean again = false;
				do {
					
					//figuring out ending_key
					
					char[] ending_lst = {'A','B'};
					verb.getChars(verb.length() - 2, verb.length(), ending_lst, 0); //putting the last 2 chars in the verb input into the ending_lst array
					
						//convert list into a string 
					String ending_str = "";
					endings ending;
					for (char i : ending_lst) {  
						ending_str += i;
					}
				
					
						//change ending to an enum for easy if statements in the future and checking if the verb is reflexive. (If the verb is reflexive the value of ending should be "se")
					verb_infinitive = verb; //we assign verb infinitive to verb here and not right after receiving the input because if we assign it here the verb wont have the "se" after it if it's reflexive
					again = false;
					switch(ending_str) {
						case("ar"):
							ending_key = 0;
							ending = endings.ar;
							verb = verb.substring(0, verb.length()-2);
							break;
						case("er"):
							ending_key = 1;
							ending = endings.er;
							verb = verb.substring(0, verb.length()-2);
							break;
						case("ir"):
							ending_key = 2;
							ending = endings.ir;
							verb = verb.substring(0, verb.length()-2);
							break;
							
						case("se"): //if the verb if reflexive, go back 2 more chars
							again = true; //this will cause the while loop to loop around 1 more time ending will be the 2 chars before "se"
							reflexive = true;
							verb = verb.substring(0, verb.length()-2);
							break;
						default: 
							if ( verb_infinitive.equalsIgnoreCase("oír") ) { //oír exception. (wooo :|)
								ending_key = 2;
								ending = endings.ir;
								verb = verb.substring(0, verb.length()-2);
							}else{
								JOptionPane.showMessageDialog(window, "invalid verb! (doesn't have ar,er,or ir at the end","invalid input error:", 1);
								problem = true;
							}
							break;
					}
		
				} while (again == true);
				
			} while (problem == true); 
			
			//find out tense_key
			String verb_ending = "";
			switch (tense) {
				case ("present"):
					tense_key = 0;
					break;
				case ("preterite"):
					tense_key = 1;
					break;
				case ("imperfect"):
					tense_key = 2;
					break;
				case ("future"):
					tense_key = 3;
					verb = verb_infinitive;  // ex hablar -> hablaré and not hablé
				break;
			}
			
			String[][][] current_verb = null;
			conj_verb = verb;
			for (int i=0; i < verb_endings.length ; i++) {
				if (verb_infinitive.equals(verb_endings_names[i])) {
					current_verb = verb_endings[i];
					verb_ending = null;
				}
			}
			if (false) {
				if (current_verb.length == 0) { //this will run if the verb is regular
					current_verb = regular_endings;
				
				
					//----------------------------- check if the verb is a compound verb and change accordingly (comPONER)
					String sub_verb = null;
					String sub_verb_inf = null;
					String[][][] sub_current_verb = null;
					String sub_verb_conj = null;
					String stem_verb = null;
					String sub_verb_ending = null;
					String verb__ = verb;
					for (int i = (verb__.length()) ; i >= 0 ; i--) {
						System.out.println("for LOOOP");
						sub_verb = verb__.substring(i);
					
						sub_verb_inf = verb_infinitive.substring(i+2);
					
						stem_verb = verb_infinitive.substring(0,i);
						
						System.out.println("Verb: " + verb);
						System.out.println("sub_verb: " + sub_verb);
						System.out.println("sub_verb_infinitive: " + sub_verb_inf);
						System.out.println("stem_verb: " + stem_verb);
						
						
						for (int a=0; a < verb_endings.length ; a++) {
							if (sub_verb_inf.equals(verb_endings_names[a])) {
								sub_current_verb = verb_endings[a];
							}
						}
					
						try {
							System.out.println("tkey,skey,ekey: " +tense_key+","+subject_key+","+ending_key);
							System.out.println("sub_current_verb: " + sub_current_verb);
							
							if ( !(sub_verb_conj.equals(null))) {
								sub_verb_conj = sub_current_verb[tense_key][subject_key][ending_key];
								verb__ = "lol_im_messed_up_:P";
							}
						}catch (NullPointerException sub_problem) { //sub_verb is not an irregular
							System.out.println("caught!");
							sub_verb_ending = regular_endings[tense_key][subject_key][ending_key];
							//check for stem-changer
							
							String[] return_package = null;
							if (sub_verb_inf.length() >= 3) {
								return_package = checkForStemChanger(subject,sub_verb,sub_verb_inf,tense,tense_key,subject_key,ending_key,sub_verb_ending,sub_current_verb);
								verb__ = stem_verb + return_package[0];
								sub_verb_ending = return_package[1];
								sub_verb_conj = verb + sub_verb_ending;
							}
							
							
						}
						
						if (! verb__.equals(verb)) { //if verb changed at all (meaning it was a compound verb) then apply the changes
							System.out.println("sub_verb_conj: " + sub_verb_conj);
							break;
							
						}
						
					} // for verbinf.len brak
					conj_verb = stem_verb + sub_verb_conj;
					System.out.println("sub_verb_conj: " + sub_verb_conj);
				}
			
			
					//----------------------------------------		
					
				}else{  
					
					
					
					try {
						System.out.println("Verb: " + verb);
						conj_verb = current_verb[tense_key][subject_key][ending_key];
						if (! conj_verb.equals(null)) {
						}
					}catch (NullPointerException problem1) {  //if the verb isn't irregular in this form, conj_verb will be null, which will throw a NullPointerExeption
						System.out.println("Verb: " + verb);
						conj_verb = verb;
						current_verb = regular_endings;
						verb_ending = current_verb[tense_key][subject_key][ending_key];
						
						//check for stem changers
						String[] return_package = checkForStemChanger(subject,verb,verb_infinitive,tense,tense_key,subject_key,ending_key,verb_ending,current_verb); //stem changer? :o
						verb = return_package[0];
						verb_ending = return_package[1];
						System.out.println("Verb: " + verb);
						conj_verb = verb + verb_ending;
						
					}
					
				
			}
			
			
		
			
			
			if (reflexive) 
				conj_verb = reflexive_endings[subject_key] + " " + conj_verb;
			
	
			JOptionPane.showMessageDialog(window, conj_verb,"Conjugated verb:", 1); // display the conjugated verb
			do_another = JOptionPane.showConfirmDialog(window, "do another?");
		} while (do_another == 0);
		
	}

}
