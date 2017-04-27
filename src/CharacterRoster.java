import java.util.ArrayList;

public class CharacterRoster {
	
	//initialized the collection of characters
	private ArrayList<Character> characterPool = new ArrayList<Character>();
	
	//constructer
	public CharacterRoster()
	{
		
	}
	
	public void addCharacter(Character character)
	{
		this.characterPool.add(character);
		return;
	}
	
	public int size()
	{
		return this.characterPool.size();
	}
	
	
	public Character get(int index)
	{
		return this.characterPool.get(index);
	}
	
	
}


