import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class CharacterFileProcessor {
	
	private static CharacterRoster allCharacters = new CharacterRoster();
	
	//Used from project 2 by Dean Hougan
	public static CharacterRoster readCharacterFile(String fileName)
	{
		FileReader fr = null;
		try {
			fr = new FileReader(fileName);
		} catch (FileNotFoundException e) {
			System.err.println("Usage error. File not found (" + fileName + ").");
			System.exit(1);
		}
		BufferedReader br = new BufferedReader(fr);
		String nextLine = null;
		try {
			br.readLine();//reads first line out of the text file
			nextLine = br.readLine();
		} catch (IOException e) {
			System.err.println("IO Exception while reading " + fileName + ".");
			System.exit(1);
		}
		while (nextLine != null) {
			processLine(nextLine);
			try {
				nextLine = br.readLine();
			} catch (IOException e) {
				System.err.println("IO Exception while reading " + fileName + ".");
				System.exit(1);
			}
		}
		try {
			br.close();
		} catch (IOException e) {
			System.err.println("IO Exception while closing " + fileName + ".");
			System.exit(1);
		}
			return allCharacters;
	}
	
	public static void processLine(String line)
	{
		Character character;
		AbilityList allAbilities = new AbilityList();
		
		String[] parts = line.split(",");
		
		if(parts.length < 11)
		{
			System.err.println("Error in input file. Line is too short.");
		}
		
		int hp = Integer.parseInt(parts[1]);
		int mp = Integer.parseInt(parts[2]);
		int speed = Integer.parseInt(parts[3]);
		int defense = Integer.parseInt(parts[4]);
		int attack = Integer.parseInt(parts[5]);
		
		//String[] abilityInfo = Arrays.copyOfRange(parts, 6, 11);
		
		
		int numberOfAbilities = (parts.length - 6) / 5;
		
		for(int index = 1; index <= numberOfAbilities; ++index)//allows for infinite abilities to be automatically added
		{
			String[] abilityInfo = Arrays.copyOfRange(parts, ((index *6)), (((index + 1) * 6)));
			
			allAbilities.addAbility(createAbility(abilityInfo));
		}
		
		
			character = new Character(parts[0], allAbilities, hp, mp, speed, defense, attack);
		
		
		allCharacters.addCharacter(character);;
		
		
	}
	
	private static Ability createAbility (String[] parts)
	{
		Ability newAbility;
		
		String name = parts[0];
		String type = parts[1];
		String description = parts[2];
		int damage = Integer.parseInt(parts[3]);
		int heal = Integer.parseInt(parts[4]);
		int mpCost = Integer.parseInt(parts[5]);
		
		newAbility = new Ability(name, type, description, damage, heal, mpCost);
		
		return newAbility;
	}
	
	

}
