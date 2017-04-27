import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static void main (String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		
		CharacterRoster allCharacters = CharacterFileProcessor.readCharacterFile(args[0]);
		
		//gets player one information
		System.out.println("Player one, are your ready to begin? \n" + "Type yes or no");
		String response = keyboard.next();
		while(!(response.equalsIgnoreCase("Yes")))
		{
			System.out.println("Are you ready now?");
			response = keyboard.next();
		}
		Player player1 = UserPrompts.getPlayerInfo(allCharacters);
		
		
		//gets player two information
		System.out.println("Now player two, are you ready to begin? \n" + "Type yes or no");
		response = keyboard.next();
		while(!(response.equalsIgnoreCase("Yes")))
		{
			System.out.println("Are you ready now?");
			response = keyboard.next();
		}
		Player player2 = UserPrompts.getPlayerInfo(allCharacters);
		
		
		//enter game
		UserPrompts.battle(player1, player2);
		
		
		
	}

}
