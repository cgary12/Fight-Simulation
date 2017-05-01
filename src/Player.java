/**
 * This class is used to create each player object
 * representing either two users at a time, or one
 * user and one computer.
 * 
 * @author P. Cavan Gary-O'Toole
 *
 */
public class Player {
	
	/** name of the user or computer **/
	private String name;
	
	/** the character chosen to play with from roster **/
	private Character character;
	
	/** value stating of player object is a computer or not **/
	private boolean isComputer;
	
	/**
	 * Constructor for the character class
	 * @param name
	 * 		Name of the user or computer
	 * @param character
	 * 		The character chosen to play with from roster
	 * @param isComputer
	 * 		Boolean value stating of player object is a computer or not
	 */
	public Player (String name, Character character, Boolean isComputer)
	{
		this.character = character;
		this.name = name;
		this.isComputer = isComputer;
	}
	
	/**
	 * Empty constructor for a player object
	 */
	public Player()
	{}
	
	//getters
	/**
	 * Gets the isComputer parameter of a player object
	 * @return
	 * 		A boolean value stating true if player is a computer
	 * 		and false if the player is a user
	 */
	public boolean getIsComputer()
	{
		return this.isComputer;
	}
	
	/**
	 * Gets the name parameter of a player object
	 * @return
	 * 		A string representation of the name of a player
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Gets the character parameter of a player object
	 * @return
	 * 		The character object of the player
	 */
	public Character getCharacter()
	{
		return this.character;
	}
	
	//setters
	/**
	 * Sets the isComputer parameter of a player object to 
	 * either true or false
	 * @param isComputer
	 * 		Boolean value of true if player is computer or
	 * 		false if player is a user
	 */
	public void setIsComputer(Boolean isComputer)
	{
		this.isComputer = isComputer;
	}
	
	/**
	 * Sets the name parameter of a player object to a new name
	 * @param name
	 * 		The string to replace name
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Sets the character parameter of a player 
	 * object to a new character
	 * @param character
	 * 		The character to switch to 
	 */
	public void setCharacter(Character character)
	{
		this.character = character;
	}

}
