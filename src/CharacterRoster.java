import java.util.ArrayList;

/**
 * This class organizes a group of character objects into 
 * one object consisting of a list of all characters for the 
 * users to choose from when battling.
 * 
 * @author P. Cavan Gary-O'Toole
 *
 */
public class CharacterRoster {
	
	/** The ArrayList of character objects to be used as a Character Roster **/
	private ArrayList<Character> characterPool = new ArrayList<Character>();
	
	/**
	 * Constructor for the class
	 */
	public CharacterRoster()
	{}
	
	/**
	 * Adds a character object to the CharacterRoster object
	 * @param character
	 * 		The character being added
	 */
	public void addCharacter(Character character)
	{
		this.characterPool.add(character);
	}
	
	/**
	 * The size of the CharacterRoster objects or
	 * number of characters in the list
	 * @return
	 */
	public int size()
	{
		return this.characterPool.size();
	}
	
	//getter
	/**
	 * Gets the character at the specific index in the array
	 * @param index
	 * 		Location of the character to get
	 * @return
	 * 		The character at the index location in the array
	 */
	public Character get(int index)
	{
		return this.characterPool.get(index);
	}
	
	
}


