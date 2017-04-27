
public class Player {
	
	private Character character;
	private String name;
	
	public Player (String name, Character character)
	{
		this.character = character;
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public Character getCharacter()
	{
		return this.character;
	}

}
