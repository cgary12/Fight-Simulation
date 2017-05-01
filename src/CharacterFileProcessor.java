import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * This class processes a file containing character info separated by lines.
 * Some code was reused and modeled after a project done in CS 2334, and that is 
 * why I included the teacher of the course as one of the authors.
 * Comments will be used to dictate his work.
 * 
 * @author P. Cavan Gary and Dean Hougan
 *
 */
public class CharacterFileProcessor {
	
	/** The collection of characters and their data after the file is processed **/
	private static CharacterRoster allCharacters = new CharacterRoster();
	
	
	/**
	 * Reads the character file designated and ensures that 
	 * all exceptions are caught and presented to the user.
	 * This method was reused from Dean Hougan and his project 2 assignment
	 * @param fileName
	 * 		Name of the file to read the characters from (should be "Charcters.csv")
	 * @return
	 * 		The collection of all the character objects from each line as a CharacterRoster object
	 */
	public static CharacterRoster readCharacterFile(String fileName)
	{
		//initializes file reader to null
		FileReader fr = null;
		try 
		{
			fr = new FileReader(fileName);//assigns to the designated file
		}
		catch (FileNotFoundException e) //catches the file not found exception and terminates program
		{
			System.err.println("Usage error. File not found (" + fileName + ").");
			System.exit(1);
		}
		//initializes a buffered reader
		BufferedReader br = new BufferedReader(fr);
		String nextLine = null;//initializes nextLine to null
		try 
		{
			br.readLine();//reads first line out of the text file
			nextLine = br.readLine();//assignes the next line to nextLine
		} 
		catch (IOException e) 
		{
			System.err.println("IO Exception while reading " + fileName + ".");
			System.exit(1);
		}
		//checks if there is a line containing contents for the BufferedReader
		while (nextLine != null) 
		{
			//reads each line
			processLine(nextLine);
			try 
			{
				//assigns nextLine to the next line of the file
				nextLine = br.readLine();
			}
			catch (IOException e) 
			{
				System.err.println("IO Exception while reading " + fileName + ".");
				System.exit(1);
			}
		}
		try 
		{
			br.close();//closes the buffered reader
		} 
		catch (IOException e) 
		{
			System.err.println("IO Exception while closing " + fileName + ".");
			System.exit(1);
		}
			return allCharacters;//the list of all character objects obtained from the file processor
	}
	
	/**
	 * Private helper method that reads a line from the file 
	 * and assigns values that are separated by commas to their given 
	 * parameter of the character based on the order they are in.
	 * This method was modeled off of Dean Hougan's project 2 assignment 
	 * @param line
	 * 		The line from the file to be decoded into useful information
	 */
	private static void processLine(String line)
	{
		//creates a character for the given line
		Character character;
		
		//creates a new AbilityList for the given line
		AbilityList allAbilities = new AbilityList();
		
		/*
		 * splits each line by commas and places each separation into 
		 * an index of parts array
		 */
		String[] parts = line.split(",");
		
		//checks to see if each character has at least one ability so they can battle with it
		if(parts.length < 12)
		{
			System.err.println("Error in input file. Line is too short.");//tells user line is too short
		}
		
		//assigns index of parts to hp(health points) of a character
		int hp = Integer.parseInt(parts[1]);
		
		//assigns index of parts to mp(mana points) of a character
		int mp = Integer.parseInt(parts[2]);
		
		//assigns index of parts to speed of a character
		int speed = Integer.parseInt(parts[3]);
		
		//assigns index of parts to defense of a character
		int defense = Integer.parseInt(parts[4]);
		
		//assigns index of parts to attack of a character
		int attack = Integer.parseInt(parts[5]);
		
		//calculates the number of abilities a character has 
		int numberOfAbilities = (parts.length - 6) / 5;
		
		/*
		 * allows for infinite abilities to be automatically added because 
		 * ability parts are all located at very end of line after index 5
		 */
		for(int index = 1; index <= numberOfAbilities; ++index)
		{
			/*
			 * combines all parts from index 6(inclusive) to 12(exclusive) of parts array into one 
			 * ability, and increases each number by 6 based on number of abilities
			 */
			String[] abilityInfo = Arrays.copyOfRange(parts, ((index *6)), (((index + 1) * 6)));
			
			/*
			 * turns info from abilityInfo into an ability using a private helper method 
			 * and adds it to the character's ability list
			 */
			allAbilities.addAbility(createAbility(abilityInfo));
		}
		
			/*
			 * creates a new character object based on the first 6 indices from the parts 
			 * array and the previously assigned AbilityList: allAbilities
			 */
			character = new Character(parts[0], allAbilities, hp, mp, speed, defense, attack);
		
		//adds the new character to the existing pool of characters
		allCharacters.addCharacter(character);;
		
		
	}
	
	/**
	 * Private helper method that takes information for an excerpt of the parts
	 * array and creates a new ability out of it.
	 * @param parts
	 * 		The part of parts array that include ability information that is put
	 * 		in its own array
	 * @return
	 * 		The ability the information created
	 */
	private static Ability createAbility (String[] parts)
	{
		//The new ability to be created
		Ability newAbility;
		
		//initializes all the parameters for the ability object
		String name = parts[0];
		String type = parts[1];
		String description = parts[2];
		int damage = Integer.parseInt(parts[3]);
		int heal = Integer.parseInt(parts[4]);
		int mpCost = Integer.parseInt(parts[5]);
		
		//puts those parameters in ability constructor to create a new ability 
		newAbility = new Ability(name, type, description, damage, heal, mpCost);
		
		//resulting ability
		return newAbility;
	}
	
	

}
