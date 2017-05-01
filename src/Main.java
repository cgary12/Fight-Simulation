import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * This class essentially serves as the
 * driver of all the other classes. The program is 
 * run through this class and various classes are called
 * to allow the program to run.
 * 
 * @author P. Cavan Gary-O'Toole
 *
 */
public class Main {
	
	/** The character roster for players to pick their characters from **/
	private static CharacterRoster allCharacters = new CharacterRoster();
	
	/** The first player playing **/
	private static Player player1 = new Player();
	
	/** The second player (or computer) playing **/
	private static Player player2 = new Player();
	
	/**
	 * Main method
	 * @param args
	 * 		The CharacterRoster "allCharacters" 
	 */
	public static void main (String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		
		/*
		 * gets all the characters from the "characters.csv" 
		 * file and assigns it to a Character Roster
		 */
		allCharacters = CharacterFileProcessor.readCharacterFile(args[0]);
		
		//queries the user for their information and various game info
		Main.beginningQueries();
		
		/*
		 * Checks to see if players have chosen the same character and if so,
		 * creates a new character object so both players don't share the same one
		 * (resulting in shared data like health)
		 */
		player2 = UserPrompts.duplicateCharacterCheck(player1, player2);
				
		
		//enter game
		UserPrompts.battle(player1, player2);
		
	}
	
	/**
	 * Queries the user for information about themselves 
	 * and how they would like to play the game
	 */
	private static void beginningQueries()
	{
		//Queries user to figure out if they want to play against a computer, person, or quit
		int choiceNumber = UserPrompts.BattleOpponentQuery();
	
		if(choiceNumber == 0)//computer
		{
			
			//Prompts user to start
			int selection = JOptionPane.showConfirmDialog(null, "Player one, are your ready to begin?");
		
			//checks their selection and runs accordingly
			while(!(selection == 0))//only exits if they click "yes"
			{
				if(selection == 2)//cancel
				{
					System.exit(1);
				}
				
				//prompt until they choose yes
				selection = JOptionPane.showConfirmDialog(null, "Are you ready now?");
				
			}
			
			//creates player object for first player and gets their character they wish to play as
			player1 = UserPrompts.getPlayerInfo(allCharacters);
			
			//cpu information by using random numbers to select their character
			player2 = new Player ("Computer", allCharacters.get(AI.randomNumber(allCharacters.size())), true);
			
			
			
			
		}
		else if(choiceNumber == 1)//player
		{
			//gets player one information
			//Prompts user to start
			int selection = JOptionPane.showConfirmDialog(null, "Player one, are your ready to begin?");
		
			//checks their selection and runs accordingly
			while(!(selection == 0))//only exits if they click "yes"
			{
				if(selection == 2)//cancel
				{
					System.exit(1);
				}
				
				//prompt until they choose yes
				selection = JOptionPane.showConfirmDialog(null, "Are you ready now?");
				
			}
			
			//creates player object for first player and gets their character they wish to play as
			player1 = UserPrompts.getPlayerInfo(allCharacters);
			
			
			//gets player two information
			//Prompts user to start
			selection = JOptionPane.showConfirmDialog(null, "Player two, are your ready to begin?");
		
			//checks their selection and runs accordingly
			while(!(selection == 0))//only exits if they click "yes"
			{
				if(selection == 2)//cancel
				{
					System.exit(1);
				}
				
				//prompt until they choose yes
				selection = JOptionPane.showConfirmDialog(null, "Are you ready now?");
				
			}
			
			//creates player object for first player and gets their character they wish to play as
			player2 = UserPrompts.getPlayerInfo(allCharacters);
			
		
		
		}
		else//Quit
		{
			System.exit(1);
		}
	}

}
